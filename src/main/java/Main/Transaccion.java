package Main;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Transaccion {

    private int       codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private double    monto;

    public Transaccion() {
        this.fecha = LocalDate.now();
        this.hora  = LocalTime.now();
    }

    public Transaccion(int codigo, LocalDate fecha, LocalTime hora, double monto) {
        this.codigo = codigo;
        this.fecha  = fecha;
        this.hora   = hora;
        this.monto  = monto;
    }

    public abstract void ejecutar();

    // Cada subclase genera su propio voucher
    public void generarVoucher() {}

    public int       getCodigo() { return codigo; }
    public void      setCodigo(int codigo) { this.codigo = codigo; }
    public LocalDate getFecha()  { return fecha; }
    public void      setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora()   { return hora; }
    public void      setHora(LocalTime hora) { this.hora = hora; }
    public double    getMonto()  { return monto; }
    public void      setMonto(double monto) { this.monto = monto; }
}
