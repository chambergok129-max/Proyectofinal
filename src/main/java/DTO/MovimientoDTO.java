package DTO;

import Main.TIPOMOVIMIENTO;
import java.time.LocalDate;
import java.time.LocalTime;

public class MovimientoDTO {
    private int id;
    private LocalDate fecha;
    private LocalTime hora;
    private double monto;
    private TIPOMOVIMIENTO tipo;
    private double saldoDisponible;
    private int cuentaId;
    private String detalle;

    public MovimientoDTO() {}

    public MovimientoDTO(LocalDate fecha, LocalTime hora, double monto,
                         TIPOMOVIMIENTO tipo, double saldoDisponible,
                         int cuentaId, String detalle) {
        this.fecha = fecha;
        this.hora = hora;
        this.monto = monto;
        this.tipo = tipo;
        this.saldoDisponible = saldoDisponible;
        this.cuentaId = cuentaId;
        this.detalle = detalle;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public TIPOMOVIMIENTO getTipo() { return tipo; }
    public void setTipo(TIPOMOVIMIENTO tipo) { this.tipo = tipo; }

    public double getSaldoDisponible() { return saldoDisponible; }
    public void setSaldoDisponible(double saldo) { this.saldoDisponible = saldo; }

    public int getCuentaId() { return cuentaId; }
    public void setCuentaId(int cuentaId) { this.cuentaId = cuentaId; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }

    @Override
    public String toString() {
        return String.format("%-12s %-8s %-15s S/. %10.2f   Saldo: S/. %.2f",
                fecha, hora, tipo, monto, saldoDisponible);
    }
}
