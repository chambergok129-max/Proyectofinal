package DTO;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {
    private int id;
    private String nombre;
    private String dni;
    private List<CuentaDTO> cuentas = new ArrayList<>();
    private List<TarjetaDTO> tarjetas = new ArrayList<>();

    public ClienteDTO() {}

    public ClienteDTO(int id, String nombre, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public List<CuentaDTO> getCuentas() { return cuentas; }
    public void setCuentas(List<CuentaDTO> cuentas) { this.cuentas = cuentas; }

    public List<TarjetaDTO> getTarjetas() { return tarjetas; }
    public void setTarjetas(List<TarjetaDTO> tarjetas) { this.tarjetas = tarjetas; }
}
