package DAO;

import DTO.TarjetaDTO;
import Main.ESTADOTARJETA;

import java.sql.*;

public class TarjetaDAO {

    public TarjetaDTO buscarPorNumero(String numero) throws SQLException {
        String sql = "SELECT * FROM tarjetas WHERE numero = ?";
        Connection cn = Conexion.getInstancia();
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, numero);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return mapear(rs);
        return null;
    }

    public void actualizarIntentos(int tarjetaId, int intentos) throws SQLException {
        String sql = "UPDATE tarjetas SET intentos_fallidos = ? WHERE id = ?";
        Connection cn = Conexion.getInstancia();
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, intentos);
        ps.setInt(2, tarjetaId);
        ps.executeUpdate();
    }

    public void bloquear(int tarjetaId) throws SQLException {
        String sql = "UPDATE tarjetas SET estado = 'BLOQUEADA'::estado_tarjeta, intentos_fallidos = 3 WHERE id = ?";
        Connection cn = Conexion.getInstancia();
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, tarjetaId);
        ps.executeUpdate();
    }

    public void actualizarPin(int tarjetaId, String nuevoPin) throws SQLException {
        String sql = "UPDATE tarjetas SET pin = ? WHERE id = ?";
        Connection cn = Conexion.getInstancia();
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, nuevoPin);
        ps.setInt(2, tarjetaId);
        ps.executeUpdate();
    }

    public void actualizarLimites(int tarjetaId, double limiteRetiro,
                                  double limiteTransferencia) throws SQLException {
        String sql = "UPDATE tarjetas SET limite_retiro = ?, limite_transferencia = ? WHERE id = ?";
        Connection cn = Conexion.getInstancia();
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setDouble(1, limiteRetiro);
        ps.setDouble(2, limiteTransferencia);
        ps.setInt(3, tarjetaId);
        ps.executeUpdate();
    }

    public void resetearIntentos(int tarjetaId) throws SQLException {
        actualizarIntentos(tarjetaId, 0);
    }

    private TarjetaDTO mapear(ResultSet rs) throws SQLException {
        return new TarjetaDTO(
                rs.getInt("id"),
                rs.getString("numero"),
                rs.getString("pin"),
                ESTADOTARJETA.valueOf(rs.getString("estado")),
                rs.getInt("intentos_fallidos"),
                rs.getDouble("limite_retiro"),
                rs.getDouble("limite_transferencia"),
                rs.getInt("cliente_id")
        );
    }
}