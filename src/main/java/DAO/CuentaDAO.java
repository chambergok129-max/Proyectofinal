package DAO;

import DTO.CuentaDTO;
import Main.TIPOCUENTA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {

    public List<CuentaDTO> buscarPorCliente(int clienteId) throws SQLException {
        List<CuentaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuentas WHERE cliente_id = ?";
        try (Connection cn = Conexion.getInstancia();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public CuentaDTO buscarPorNumero(String numero) throws SQLException {
        String sql = "SELECT * FROM cuentas WHERE numero = ?";
        try (Connection cn = Conexion.getInstancia();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    public void actualizarSaldo(int cuentaId, double nuevoSaldo) throws SQLException {
        String sql = "UPDATE cuentas SET saldo = ? WHERE id = ?";
        try (Connection cn = Conexion.getInstancia();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, cuentaId);
            ps.executeUpdate();
        }
    }

    private CuentaDTO mapear(ResultSet rs) throws SQLException {
        return new CuentaDTO(
                rs.getInt("id"),
                rs.getString("numero"),
                rs.getDouble("saldo"),
                TIPOCUENTA.valueOf(rs.getString("tipo")),
                rs.getInt("cliente_id")
        );
    }
}
