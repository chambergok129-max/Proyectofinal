package DTO;

import Main.ESTADOTARJETA;

public class TarjetaDTO {
    private int id;
    private String numero;
    private String pin;
    private ESTADOTARJETA estado;
    private int intentosFallidos;
    private double limiteRetiro;
    private double limiteTransferencia;
    private int clienteId;

    public TarjetaDTO() {}

    public TarjetaDTO(int id, String numero, String pin, ESTADOTARJETA estado,
                      int intentosFallidos, double limiteRetiro,
                      double limiteTransferencia, int clienteId) {
        this.id = id;
        this.numero = numero;
        this.pin = pin;
        this.estado = estado;
        this.intentosFallidos = intentosFallidos;
        this.limiteRetiro = limiteRetiro;
        this.limiteTransferencia = limiteTransferencia;
        this.clienteId = clienteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public ESTADOTARJETA getEstado() { return estado; }
    public void setEstado(ESTADOTARJETA estado) { this.estado = estado; }

    public int getIntentosFallidos() { return intentosFallidos; }
    public void setIntentosFallidos(int intentosFallidos) { this.intentosFallidos = intentosFallidos; }

    public double getLimiteRetiro() { return limiteRetiro; }
    public void setLimiteRetiro(double limiteRetiro) { this.limiteRetiro = limiteRetiro; }

    public double getLimiteTransferencia() { return limiteTransferencia; }
    public void setLimiteTransferencia(double limiteTransferencia) { this.limiteTransferencia = limiteTransferencia; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
}
