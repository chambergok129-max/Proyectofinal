package Movimientos;

import Clientes.Cuenta;
import Main.Transaccion;
import Main.Voucher;

import java.sql.SQLException;

public class Transferencia extends Transaccion {

    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private double limiteTransferencia;

    public Transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino,
                         double limiteTransferencia) {
        this.cuentaOrigen        = cuentaOrigen;
        this.cuentaDestino       = cuentaDestino;
        this.limiteTransferencia = limiteTransferencia;
    }

    @Override
    public void ejecutar() {
        try {
            if (!validarCuentaDestino()) {
                System.out.println("Error: Cuenta destino no válida."); return;
            }
            if (cuentaOrigen.getSaldo() < getMonto()) {
                System.out.println("Error: Saldo insuficiente."); return;
            }
            if (getMonto() > limiteTransferencia) {
                System.out.printf("Error: Supera el límite de transferencia (S/. %.2f).%n",
                        limiteTransferencia); return;
            }

            cuentaOrigen.retirar(getMonto());
            cuentaDestino.depositar(getMonto());
            generarVoucher();

        } catch (SQLException e) {
            System.err.println("Error al procesar transferencia: " + e.getMessage());
        }
    }

    public boolean validarCuentaDestino() {
        return cuentaDestino != null;
    }

    @Override
    public void generarVoucher() {
        new Voucher("TRANSFERENCIA", cuentaOrigen.getNumero(),
                getMonto(), cuentaOrigen.getSaldo(),
                "Destino: " + cuentaDestino.getNumero()).imprimir();
    }

    public Cuenta getCuentaOrigen() { return cuentaOrigen; }
    public Cuenta getCuentaDestino() { return cuentaDestino; }
}
