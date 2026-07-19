package Movimientos;

import Clientes.Cuenta;
import Clientes.Tarjeta;
import Main.Transaccion;
import Main.Voucher;

import java.sql.SQLException;

public class CambioClave extends Transaccion {

    private Tarjeta tarjeta;
    private String pinActual;
    private String nuevoPIN;
    private Cuenta cuenta;

    public CambioClave(Tarjeta tarjeta, String pinActual,
                       String nuevoPIN, Cuenta cuenta) {
        this.tarjeta   = tarjeta;
        this.pinActual = pinActual;
        this.nuevoPIN  = nuevoPIN;
        this.cuenta    = cuenta;
    }

    @Override
    public void ejecutar() {
        try {
            if (!validarPIN()) {
                System.out.println("Error: PIN actual incorrecto."); return;
            }
            if (nuevoPIN == null || nuevoPIN.length() != 4 || !nuevoPIN.matches("\\d+")) {
                System.out.println("Error: El nuevo PIN debe tener exactamente 4 dígitos."); return;
            }
            tarjeta.cambiarPin(nuevoPIN);
            cuenta.registrarMovimiento(0,
                    Main.TIPOMOVIMIENTO.CAMBIO_CLAVE, "Cambio de PIN");
            generarVoucher();
            System.out.println("PIN cambiado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al cambiar PIN: " + e.getMessage());
        }
    }

    public boolean validarPIN() {
        return tarjeta.getDatos().getPin().equals(pinActual);
    }

    @Override
    public void generarVoucher() {
        new Voucher("CAMBIO DE CLAVE", tarjeta.getNumero(),
                0, cuenta.getSaldo(), "PIN actualizado").imprimir();
    }

    public String getNuevoPIN() { return nuevoPIN; }
    public void setNuevoPIN(String nuevoPIN) { this.nuevoPIN = nuevoPIN; }
}
