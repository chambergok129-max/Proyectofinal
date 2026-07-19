package Clientes;

import DAO.CuentaDAO;
import DAO.MovimientoDAO;
import DTO.CuentaDTO;
import DTO.MovimientoDTO;
import Main.TIPOCUENTA;
import Main.TIPOMOVIMIENTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Cuenta {

    private CuentaDTO datos;
    private CuentaDAO cuentaDAO = new CuentaDAO();
    private MovimientoDAO movimientoDAO = new MovimientoDAO();

    public Cuenta(CuentaDTO datos) {
        this.datos = datos;
    }

    public CuentaDTO getDatos() { return datos; }

    public double getSaldo() { return datos.getSaldo(); }

    public String getNumero() { return datos.getNumero(); }

    public TIPOCUENTA getTipo() { return datos.getTipo(); }

    public boolean depositar(double monto) throws SQLException {
        if (monto <= 0) return false;
        double nuevoSaldo = datos.getSaldo() + monto;
        cuentaDAO.actualizarSaldo(datos.getId(), nuevoSaldo);
        datos.setSaldo(nuevoSaldo);
        registrarMovimiento(monto, TIPOMOVIMIENTO.DEPOSITO, "Depósito en cuenta " + datos.getNumero());
        return true;
    }

    public boolean retirar(double monto) throws SQLException {
        if (monto <= 0 || datos.getSaldo() < monto) return false;
        double nuevoSaldo = datos.getSaldo() - monto;
        cuentaDAO.actualizarSaldo(datos.getId(), nuevoSaldo);
        datos.setSaldo(nuevoSaldo);
        registrarMovimiento(monto, TIPOMOVIMIENTO.RETIRO, "Retiro de cuenta " + datos.getNumero());
        return true;
    }

    public List<MovimientoDTO> obtenerMovimientos() throws SQLException {
        return movimientoDAO.buscarPorCuenta(datos.getId());
    }

    public void registrarMovimiento(double monto, TIPOMOVIMIENTO tipo,
                                    String detalle) throws SQLException {
        MovimientoDTO m = new MovimientoDTO(
                LocalDate.now(), LocalTime.now(),
                monto, tipo, datos.getSaldo(),
                datos.getId(), detalle
        );
        movimientoDAO.registrar(m);
    }
}
