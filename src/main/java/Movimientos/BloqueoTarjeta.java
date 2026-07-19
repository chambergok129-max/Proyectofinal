package Movimientos;

import Clientes.Cuenta;
import Clientes.Tarjeta;
import Main.TIPOMOVIMIENTO;
import Main.Transaccion;
import Main.Voucher;

import java.sql.SQLException;

public class BloqueoTarjeta extends Transaccion {

    private Tarjeta tarjeta;
    private Cuenta  cuenta;

    public BloqueoTarjeta(Tarjeta tarjeta, Cuenta cuenta) {
        this.tarjeta = tarjeta;
        this.cuenta  = cuenta;
    }

    @Override
    public void ejecutar() {
        try {
            tarjeta.bloquear();
            cuenta.registrarMovimiento(0, TIPOMOVIMIENTO.BLOQUEO, "Tarjeta bloqueada por el cliente");
            generarVoucher();
            System.out.println("Tarjeta bloqueada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al bloquear tarjeta: " + e.getMessage());
        }
    }

    @Override
    public void generarVoucher() {
        new Voucher("BLOQUEO DE TARJETA", tarjeta.getNumero(),
                0, cuenta.getSaldo(), "Tarjeta bloqueada").imprimir();
    }
}
