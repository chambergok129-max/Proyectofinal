package Admin;

import Main.GestorEfectivo;
import Main.Persona;

import java.sql.SQLException;

public class Administrador extends Persona {

    private String usuario;
    private String contrasenia;
    private GestorEfectivo gestorEfectivo;
    private Auditoria auditoria;

    public Administrador(int id, String nombre, String dni,
                         String usuario, String contrasenia,
                         GestorEfectivo gestorEfectivo) {
        super(id, nombre, dni);
        this.usuario        = usuario;
        this.contrasenia    = contrasenia;
        this.gestorEfectivo = gestorEfectivo;
        this.auditoria      = new Auditoria();
    }

    @Override
    public void iniciarsesión() {
        // Gestionado por menú
    }

    public boolean autenticar(String usuario, String contrasenia) {
        return this.usuario.equals(usuario) && this.contrasenia.equals(contrasenia);
    }

    /**
     * Surtir el cajero con billetes de distintas denominaciones.
     */
    public void surtirCajero(int b200, int b100, int b50, int b20, int b10) {
        gestorEfectivo.recargarBilletes(b200, b100, b50, b20, b10);
        System.out.println("Cajero surtido exitosamente.");
        gestorEfectivo.mostrarInventario();
    }

    public void generarReporte() throws SQLException {
        auditoria.generarReporteDiario();
    }

    public void consultarMovimientos() throws SQLException {
        auditoria.consultarTransacciones();
    }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
}
