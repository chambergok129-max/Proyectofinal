package Movimientos;

import Clientes.Cuenta;
import Main.GestorEfectivo;
import Main.Transaccion;
import Main.Voucher;

import java.sql.SQLException;

public class Retiro extends Transaccion {

    private Cuenta cuenta;
    private GestorEfectivo gestorEfectivo;
    private double limiteRetiro;

    public Retiro(Cuenta cuenta, GestorEfectivo gestorEfectivo, double limiteRetiro) {
        this.cuenta         = cuenta;
        this.gestorEfectivo = gestorEfectivo;
        this.limiteRetiro   = limiteRetiro;
    }

    @Override
    public void ejecutar() {
        try {
            double monto = getMonto();

            if (!validarSaldo()) {
                System.out.println("Error: Saldo insuficiente."); return;
            }
            if (!validarLimite()) {
                System.out.printf("Error: Supera el límite de retiro (S/. %.2f).%n", limiteRetiro); return;
            }
            if (!gestorEfectivo.hayFondos(monto)) {
                System.out.println("Error: El cajero no tiene fondos suficientes."); return;
            }
            if (!gestorEfectivo.entregarBilletes(monto)) {
                System.out.println("Error: No se puede dar el monto exacto con los billetes disponibles."); return;
            }

            cuenta.retirar(monto);
            generarVoucher();

        } catch (SQLException e) {
            System.err.println("Error al procesar retiro: " + e.getMessage());
        }
    }

    public boolean validarSaldo() {
        return cuenta.getSaldo() >= getMonto();
    }

    public boolean validarLimite() {
        return getMonto() <= limiteRetiro;
    }

    @Override
    public void generarVoucher() {
        new Voucher("RETIRO", cuenta.getNumero(),
                getMonto(), cuenta.getSaldo(), "").imprimir();
    }
}
