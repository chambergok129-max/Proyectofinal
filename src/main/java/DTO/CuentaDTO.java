package DTO;

import Main.TIPOCUENTA;

public class CuentaDTO {
    private int id;
    private String numero;
    private double saldo;
    private TIPOCUENTA tipo;
    private int clienteId;

    public CuentaDTO() {}

    public CuentaDTO(int id, String numero, double saldo, TIPOCUENTA tipo, int clienteId) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
        this.tipo = tipo;
        this.clienteId = clienteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public TIPOCUENTA getTipo() { return tipo; }
    public void setTipo(TIPOCUENTA tipo) { this.tipo = tipo; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
}
