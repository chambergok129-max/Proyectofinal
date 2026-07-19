package Movimientos;

import Clientes.Cuenta;
import Main.Transaccion;
import Main.Voucher;

import java.sql.SQLException;

public class Deposito extends Transaccion {

    private Cuenta cuenta;

    public Deposito(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void ejecutar() {
        try {
            if (!validarMonto()) {
                System.out.println("Error: El monto debe ser mayor a 0."); return;
            }
            cuenta.depositar(getMonto());
            generarVoucher();
        } catch (SQLException e) {
            System.err.println("Error al procesar depósito: " + e.getMessage());
        }
    }

    public boolean validarMonto() {
        return getMonto() > 0;
    }

    @Override
    public void generarVoucher() {
        new Voucher("DEPÓSITO", cuenta.getNumero(),
                getMonto(), cuenta.getSaldo(), "").imprimir();
    }
}
