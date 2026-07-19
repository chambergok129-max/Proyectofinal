package Admin;

import Clientes.Cuenta;
import Clientes.Tarjeta;
import Main.TIPOMOVIMIENTO;
import Main.Transaccion;
import Main.Voucher;

import java.sql.SQLException;

public class ConfiguracionTarjeta extends Transaccion {

    private Tarjeta tarjeta;
    private double  nuevoLimiteRetiro;
    private double  nuevoLimiteTransferencia;
    private Cuenta  cuenta;

    public ConfiguracionTarjeta(Tarjeta tarjeta, double nuevoLimiteRetiro,
                                 double nuevoLimiteTransferencia, Cuenta cuenta) {
        this.tarjeta                  = tarjeta;
        this.nuevoLimiteRetiro        = nuevoLimiteRetiro;
        this.nuevoLimiteTransferencia = nuevoLimiteTransferencia;
        this.cuenta                   = cuenta;
    }

    @Override
    public void ejecutar() {
        try {
            if (nuevoLimiteRetiro <= 0 || nuevoLimiteTransferencia <= 0) {
                System.out.println("Error: Los límites deben ser mayores a 0."); return;
            }
            tarjeta.actualizarLimites(nuevoLimiteRetiro, nuevoLimiteTransferencia);
            cuenta.registrarMovimiento(0, TIPOMOVIMIENTO.CONFIGURACION,
                    String.format("Límites actualizados: Retiro S/.%.2f | Transferencia S/.%.2f",
                            nuevoLimiteRetiro, nuevoLimiteTransferencia));
            generarVoucher();
            System.out.println("Configuración actualizada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al configurar tarjeta: " + e.getMessage());
        }
    }

    @Override
    public void generarVoucher() {
        new Voucher("CONFIGURACIÓN DE TARJETA", tarjeta.getNumero(), 0, cuenta.getSaldo(),
                String.format("Límite retiro: S/.%.2f | Límite transferencia: S/.%.2f",
                        nuevoLimiteRetiro, nuevoLimiteTransferencia)).imprimir();
    }

    public double getNuevoLimiteRetiro() {
        return nuevoLimiteRetiro;
    }
    public double getNuevoLimiteTransferencia() {
        return nuevoLimiteTransferencia;
    }
}
