package Movimientos;

import Clientes.Cuenta;
import DTO.MovimientoDTO;
import Main.TIPOMOVIMIENTO;
import Main.Transaccion;
import Main.Voucher;

import java.sql.SQLException;
import java.util.List;

public class ConsultaMovimiento extends Transaccion {

    private Cuenta cuenta;

    public ConsultaMovimiento(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void ejecutar() {
        try {
            List<MovimientoDTO> movimientos = cuenta.obtenerMovimientos();
            System.out.println("=".repeat(62));
            System.out.println("         ESTADO DE CUENTA - " + cuenta.getNumero());
            System.out.printf("         Saldo actual: S/. %.2f%n", cuenta.getSaldo());
            System.out.println("=".repeat(62));
            System.out.printf("%-12s %-8s %-18s %10s%n", "Fecha", "Hora", "Tipo", "Monto");
            System.out.println("-".repeat(62));
            for (MovimientoDTO m : movimientos) {
                System.out.println(m);
            }
            System.out.println("=".repeat(62));

            cuenta.registrarMovimiento(0, TIPOMOVIMIENTO.CONSULTA,
                    "Consulta de movimientos");
            generarVoucher();
        } catch (SQLException e) {
            System.err.println("Error al consultar movimientos: " + e.getMessage());
        }
    }

    @Override
    public void generarVoucher() {
        new Voucher("CONSULTA DE SALDO", cuenta.getNumero(),
                0, cuenta.getSaldo(), "").imprimir();
    }
}
