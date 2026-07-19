package Main;

import Admin.Administrador;
import Admin.ConfiguracionTarjeta;
import Clientes.Cuenta;
import Clientes.Cliente;
import Clientes.Tarjeta;
import DAO.ClienteDAO;
import DAO.CuentaDAO;
import DAO.TarjetaDAO;
import DTO.ClienteDTO;
import DTO.CuentaDTO;
import DTO.TarjetaDTO;
import Movimientos.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CajeroAutomatico {

    private String codigo;
    private String ubicacion;
    private GestorEfectivo gestorEfectivo;
    private Scanner sc = new Scanner(System.in);

    // DAOs
    private TarjetaDAO tarjetaDAO = new TarjetaDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private CuentaDAO  cuentaDAO  = new CuentaDAO();

    // Sesión actual
    private Tarjeta tarjetaActual;
    private Cliente clienteActual;
    private Cuenta  cuentaActual;

    public CajeroAutomatico(String codigo, String ubicacion) {
        this.codigo         = codigo;
        this.ubicacion      = ubicacion;
        this.gestorEfectivo = new GestorEfectivo(10, 20, 20, 30, 50);
    }

    // ══════════════════════════════════════════════════════════════════
    //  INICIO
    // ══════════════════════════════════════════════════════════════════
    public void iniciar() {
        System.out.println("=".repeat(42));
        System.out.println("   CAJERO AUTOMÁTICO — " + ubicacion);
        System.out.println("=".repeat(42));

        while (true) {
            System.out.println("\n[1] Ingresar tarjeta");
            System.out.println("[2] Módulo Administrador");
            System.out.println("[0] Salir");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1" -> autenticarCliente();
                case "2" -> menuAdministrador();
                case "0" -> { System.out.println("Hasta luego."); return; }
                default  -> System.out.println("Opción no válida.");
            }
        }
    }

    // ══════════════════════════════════════════════════════════════════
    //  AUTENTICACIÓN (máx 3 intentos → bloqueo)
    // ══════════════════════════════════════════════════════════════════
    private void autenticarCliente() {
        System.out.print("\nNúmero de tarjeta: ");
        String numeroTarjeta = sc.nextLine().trim();

        try {
            TarjetaDTO dto = tarjetaDAO.buscarPorNumero(numeroTarjeta);
            if (dto == null) { System.out.println("Tarjeta no encontrada."); return; }

            tarjetaActual = new Tarjeta(dto);

            if (!tarjetaActual.estaActiva()) {
                System.out.println("Tarjeta BLOQUEADA. Consulte en ventanilla."); return;
            }

            // Validar PIN con máx 3 intentos
            while (tarjetaActual.estaActiva()) {
                System.out.printf("PIN (%d intento(s) restante(s)): ",
                        tarjetaActual.getIntentosRestantes());
                String pin = sc.nextLine().trim();

                if (tarjetaActual.validarPin(pin)) {
                    System.out.println("\nAcceso concedido. Bienvenido.");
                    cargarCliente();
                    return;
                } else if (!tarjetaActual.estaActiva()) {
                    System.out.println("Tarjeta BLOQUEADA por exceder intentos.");
                    return;
                } else {
                    System.out.printf("PIN incorrecto. Quedan %d intento(s).%n",
                            tarjetaActual.getIntentosRestantes());
                }
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            System.err.println("Error de conexión: " + e.getSQLState());
            System.err.println("Error de conexión: " + e.getStackTrace());
            System.err.println("Error de conexión: " + e.toString());
        }
    }

    // ══════════════════════════════════════════════════════════════════
    //  CARGAR CLIENTE Y SELECCIONAR CUENTA
    // ══════════════════════════════════════════════════════════════════
    private void cargarCliente() throws SQLException {
        ClienteDTO dto = clienteDAO.buscarPorId(tarjetaActual.getClienteId());
        if (dto == null) { System.out.println("Error al cargar cliente."); return; }

        clienteActual = new Cliente(dto.getId(), dto.getNombre(), dto.getDni());
        clienteActual.cargarCuentas();

        System.out.println("Cliente: " + clienteActual.getNombre());
        seleccionarCuenta();
    }

    private void seleccionarCuenta() throws SQLException {
        List<Cuenta> cuentas = clienteActual.getCuentas();
        if (cuentas.isEmpty()) { System.out.println("No tiene cuentas asociadas."); return; }

        if (cuentas.size() == 1) {
            cuentaActual = cuentas.get(0);
        } else {
            System.out.println("\n── Seleccione cuenta ──");
            for (int i = 0; i < cuentas.size(); i++) {
                Cuenta c = cuentas.get(i);
                System.out.printf("[%d] %s - %s  S/. %.2f%n",
                        i + 1, c.getNumero(), c.getTipo(), c.getSaldo());
            }
            System.out.print("Opción: ");
            int idx = Integer.parseInt(sc.nextLine().trim()) - 1;
            cuentaActual = clienteActual.getCuenta(idx);
        }

        if (cuentaActual == null) { System.out.println("Cuenta inválida."); return; }
        menuPrincipal();
    }

    // ══════════════════════════════════════════════════════════════════
    //  MENÚ PRINCIPAL DEL CLIENTE
    // ══════════════════════════════════════════════════════════════════
    private void menuPrincipal() throws SQLException {
        while (true) {
            System.out.println("\n══════════════════════════════════");
            System.out.printf("  Cuenta: %s  Saldo: S/. %.2f%n",
                    cuentaActual.getNumero(), cuentaActual.getSaldo());
            System.out.println("══════════════════════════════════");
            System.out.println("[1] Retiro");
            System.out.println("[2] Depósito");
            System.out.println("[3] Transferencia");
            System.out.println("[4] Consulta de saldo y movimientos");
            System.out.println("[5] Cambio de clave");
            System.out.println("[6] Configuración de tarjeta");
            System.out.println("[7] Bloquear tarjeta");
            System.out.println("[8] Cambiar cuenta");
            System.out.println("[0] Salir");
            System.out.print("Opción: ");

            switch (sc.nextLine().trim()) {
                case "1" -> realizarRetiro();
                case "2" -> realizarDeposito();
                case "3" -> realizarTransferencia();
                case "4" -> consultarMovimientos();
                case "5" -> cambiarClave();
                case "6" -> configurarTarjeta();
                case "7" -> { bloquearTarjeta(); return; }
                case "8" -> seleccionarCuenta();
                case "0" -> { System.out.println("Sesión cerrada."); return; }
                default  -> System.out.println("Opción no válida.");
            }
        }
    }

    // ══════════════════════════════════════════════════════════════════
    //  OPERACIONES
    // ══════════════════════════════════════════════════════════════════
    private void realizarRetiro() {
        System.out.print("Monto a retirar: S/. ");
        double monto = Double.parseDouble(sc.nextLine().trim());
        Retiro retiro = new Retiro(cuentaActual, gestorEfectivo,
                tarjetaActual.getLimiteRetiro());
        retiro.setMonto(monto);
        retiro.ejecutar();
    }

    private void realizarDeposito() {
        System.out.print("Monto a depositar: S/. ");
        double monto = Double.parseDouble(sc.nextLine().trim());
        Deposito deposito = new Deposito(cuentaActual);
        deposito.setMonto(monto);
        deposito.ejecutar();
    }

    private void realizarTransferencia() throws SQLException {
        System.out.print("Número de cuenta destino: ");
        String numeroDest = sc.nextLine().trim();
        CuentaDTO destDTO = cuentaDAO.buscarPorNumero(numeroDest);
        if (destDTO == null) { System.out.println("Cuenta destino no encontrada."); return; }

        Cuenta cuentaDest = new Cuenta(destDTO);
        System.out.print("Monto a transferir: S/. ");
        double monto = Double.parseDouble(sc.nextLine().trim());

        Transferencia t = new Transferencia(cuentaActual, cuentaDest,
                tarjetaActual.getLimiteTransferencia());
        t.setMonto(monto);
        t.ejecutar();
    }

    private void consultarMovimientos() {
        new ConsultaMovimiento(cuentaActual).ejecutar();
    }

    private void cambiarClave() {
        System.out.print("PIN actual: ");
        String pinActual = sc.nextLine().trim();
        System.out.print("Nuevo PIN (4 dígitos): ");
        String nuevoPin  = sc.nextLine().trim();
        new CambioClave(tarjetaActual, pinActual, nuevoPin, cuentaActual).ejecutar();
    }

    private void configurarTarjeta() {
        System.out.print("Nuevo límite de retiro: S/. ");
        double limRet  = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Nuevo límite de transferencia: S/. ");
        double limTrans = Double.parseDouble(sc.nextLine().trim());
        new ConfiguracionTarjeta(tarjetaActual, limRet, limTrans, cuentaActual).ejecutar();
    }

    private void bloquearTarjeta() {
        System.out.print("¿Confirma el bloqueo? (s/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("s")) {
            new BloqueoTarjeta(tarjetaActual, cuentaActual).ejecutar();
        }
    }

    // ══════════════════════════════════════════════════════════════════
    //  MÓDULO ADMINISTRADOR
    // ══════════════════════════════════════════════════════════════════
    private void menuAdministrador() {
        System.out.print("\nUsuario admin: ");
        String user = sc.nextLine().trim();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine().trim();

        Administrador admin = new Administrador(
                1, "Admin", "00000000", "admin", "1234", gestorEfectivo);

        if (!admin.autenticar(user, pass)) {
            System.out.println("Credenciales incorrectas."); return;
        }

        System.out.println("Acceso administrador concedido.");
        while (true) {
            System.out.println("\n── MENÚ ADMINISTRADOR ──");
            System.out.println("[1] Surtir cajero");
            System.out.println("[2] Ver inventario");
            System.out.println("[3] Generar reporte diario");
            System.out.println("[0] Volver");
            System.out.print("Opción: ");

            switch (sc.nextLine().trim()) {
                case "1" -> surtirCajero(admin);
                case "2" -> gestorEfectivo.mostrarInventario();
                case "3" -> {
                    try { admin.generarReporte(); }
                    catch (SQLException e) { System.err.println("Error: " + e.getMessage()); }
                }
                case "0" -> { return; }
                default  -> System.out.println("Opción no válida.");
            }
        }
    }

    private void surtirCajero(Administrador admin) {
        System.out.println("Ingrese cantidad de billetes:");
        System.out.print("  S/.200: "); int b200 = Integer.parseInt(sc.nextLine().trim());
        System.out.print("  S/.100: "); int b100 = Integer.parseInt(sc.nextLine().trim());
        System.out.print("  S/. 50: "); int b50  = Integer.parseInt(sc.nextLine().trim());
        System.out.print("  S/. 20: "); int b20  = Integer.parseInt(sc.nextLine().trim());
        System.out.print("  S/. 10: "); int b10  = Integer.parseInt(sc.nextLine().trim());
        admin.surtirCajero(b200, b100, b50, b20, b10);
    }

    // Getters
    public String getCodigo()   { return codigo; }
    public String getUbicacion(){ return ubicacion; }
}
