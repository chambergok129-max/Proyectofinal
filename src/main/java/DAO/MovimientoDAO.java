package DAO;

import DTO.MovimientoDTO;
import Main.TIPOMOVIMIENTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAO {

    public void registrar(MovimientoDTO m) throws SQLException {
        String sql = """
            INSERT INTO movimientos(fecha, hora, monto, tipo, saldo_disponible, cuenta_id, detalle)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection cn = Conexion.getInstancia();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(m.getFecha()));
            ps.setTime(2, Time.valueOf(m.getHora()));
            ps.setDouble(3, m.getMonto());
            ps.setString(4, m.getTipo().name());
            ps.setDouble(5, m.getSaldoDisponible());
            ps.setInt(6, m.getCuentaId());
            ps.setString(7, m.getDetalle());
            ps.executeUpdate();
        }
    }

    public List<MovimientoDTO> buscarPorCuenta(int cuentaId) throws SQLException {
        List<MovimientoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimientos WHERE cuenta_id = ? ORDER BY fecha DESC, hora DESC";
        try (Connection cn = Conexion.getInstancia();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, cuentaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    // Para el reporte diario de auditoría
    public List<MovimientoDTO> buscarPorFecha(LocalDate fecha) throws SQLException {
        List<MovimientoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimientos WHERE fecha = ? ORDER BY hora";
        try (Connection cn = Conexion.getInstancia();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private MovimientoDTO mapear(ResultSet rs) throws SQLException {
        MovimientoDTO m = new MovimientoDTO();
        m.setId(rs.getInt("id"));
        m.setFecha(rs.getDate("fecha").toLocalDate());
        m.setHora(rs.getTime("hora").toLocalTime());
        m.setMonto(rs.getDouble("monto"));
        m.setTipo(TIPOMOVIMIENTO.valueOf(rs.getString("tipo")));
        m.setSaldoDisponible(rs.getDouble("saldo_disponible"));
        m.setCuentaId(rs.getInt("cuenta_id"));
        m.setDetalle(rs.getString("detalle"));
        return m;
    }
}
