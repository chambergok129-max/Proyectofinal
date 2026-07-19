package Main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Voucher implements Imprimible {

    private String tipoOperacion;
    private String numeroCuenta;
    private double monto;
    private double saldoResultante;
    private LocalDate fecha;
    private LocalTime hora;
    private String detalle;

    public Voucher(String tipoOperacion, String numeroCuenta,
                   double monto, double saldoResultante, String detalle) {
        this.tipoOperacion   = tipoOperacion;
        this.numeroCuenta    = numeroCuenta;
        this.monto           = monto;
        this.saldoResultante = saldoResultante;
        this.fecha           = LocalDate.now();
        this.hora            = LocalTime.now();
        this.detalle         = detalle;
    }

    @Override
    public void imprimir() {
        String linea = "=".repeat(42);
        System.out.println(linea);
        System.out.println("         VOUCHER DE OPERACIÓN");
        System.out.println(linea);
        System.out.printf("  Fecha     : %s%n",
                fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.printf("  Hora      : %s%n",
                hora.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.printf("  Operación : %s%n", tipoOperacion);
        System.out.printf("  Cuenta    : %s%n", numeroCuenta);
        System.out.printf("  Monto     : S/. %.2f%n", monto);
        System.out.printf("  Saldo     : S/. %.2f%n", saldoResultante);
        if (detalle != null && !detalle.isEmpty())
            System.out.printf("  Detalle   : %s%n", detalle);
        System.out.println(linea);
    }
}
