package Clientes;

import DAO.TarjetaDAO;
import DTO.TarjetaDTO;
import Main.ESTADOTARJETA;

import java.sql.SQLException;

public class Tarjeta {

    private static final int MAX_INTENTOS = 3;
    private TarjetaDTO datos;
    private TarjetaDAO tarjetaDAO = new TarjetaDAO();

    public Tarjeta(TarjetaDTO datos) {
        this.datos = datos;
    }

    public TarjetaDTO getDatos() { return datos; }

    public boolean estaActiva() {
        return datos.getEstado() == ESTADOTARJETA.ACTIVA;
    }

    /**
     * Valida el PIN. Si falla 3 veces bloquea la tarjeta.
     * @return true si el PIN es correcto
     */
    public boolean validarPin(String pinIngresado) throws SQLException {
        if (!estaActiva()) return false;

        if (datos.getPin().equals(pinIngresado)) {
            tarjetaDAO.resetearIntentos(datos.getId());
            datos.setIntentosFallidos(0);
            return true;
        }

        // PIN incorrecto: incrementar intentos
        int intentos = datos.getIntentosFallidos() + 1;
        datos.setIntentosFallidos(intentos);
        tarjetaDAO.actualizarIntentos(datos.getId(), intentos);

        if (intentos >= MAX_INTENTOS) {
            bloquear();
        }
        return false;
    }

    public void bloquear() throws SQLException {
        datos.setEstado(ESTADOTARJETA.BLOQUEADA);
        tarjetaDAO.bloquear(datos.getId());
    }

    public void cambiarPin(String nuevoPin) throws SQLException {
        datos.setPin(nuevoPin);
        tarjetaDAO.actualizarPin(datos.getId(), nuevoPin);
    }

    public void actualizarLimites(double limiteRetiro,
                                   double limiteTransferencia) throws SQLException {
        datos.setLimiteRetiro(limiteRetiro);
        datos.setLimiteTransferencia(limiteTransferencia);
        tarjetaDAO.actualizarLimites(datos.getId(), limiteRetiro, limiteTransferencia);
    }

    public int getIntentosRestantes() {
        return MAX_INTENTOS - datos.getIntentosFallidos();
    }

    public double getLimiteRetiro() { return datos.getLimiteRetiro(); }
    public double getLimiteTransferencia() { return datos.getLimiteTransferencia(); }
    public String getNumero() { return datos.getNumero(); }
    public int getClienteId() { return datos.getClienteId(); }
}
