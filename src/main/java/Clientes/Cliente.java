package Clientes;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import DTO.CuentaDTO;
import Main.Persona;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private List<Cuenta> cuentas = new ArrayList<>();
    private ClienteDAO clienteDAO = new ClienteDAO();

    public Cliente(int id, String nombre, String dni) {
        super(id, nombre, dni);
    }

    // Carga las cuentas del cliente desde la BD
    public void cargarCuentas() throws SQLException {
        ClienteDTO dto = clienteDAO.buscarPorId(getId());
        if (dto != null) {
            cuentas.clear();
            for (CuentaDTO c : dto.getCuentas()) {
                cuentas.add(new Cuenta(c));
            }
        }
    }

    public List<Cuenta> getCuentas() { return cuentas; }

    public Cuenta getCuenta(int indice) {
        if (indice >= 0 && indice < cuentas.size()) return cuentas.get(indice);
        return null;
    }

    @Override
    public void iniciarsesión() {
        // Gestionado por CajeroAutomatico con validación de PIN
    }
}
