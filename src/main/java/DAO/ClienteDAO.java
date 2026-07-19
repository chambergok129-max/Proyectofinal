package DAO;

import DTO.ClienteDTO;
import java.sql.*;

public class ClienteDAO {

    private CuentaDAO cuentaDAO = new CuentaDAO();

    public ClienteDTO buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Connection cn = Conexion.getInstancia();
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ClienteDTO c = new ClienteDTO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("dni")
            );
            c.setCuentas(cuentaDAO.buscarPorCliente(c.getId()));
            return c;
        }
        return null;
    }
}