package Main;

public class GestorEfectivo {

    private int billetes200;
    private int billetes100;
    private int billetes50;
    private int billetes20;
    private int billetes10;

    public GestorEfectivo() {}

    public GestorEfectivo(int b200, int b100, int b50, int b20, int b10) {
        this.billetes200 = b200;
        this.billetes100 = b100;
        this.billetes50  = b50;
        this.billetes20  = b20;
        this.billetes10  = b10;
    }

    public boolean hayFondos(double monto) {
        return calcularTotalDisponible() >= monto;
    }

    /**
     * Entrega billetes usando el algoritmo greedy (mayor a menor).
     * Retorna false si no puede cubrir el monto exacto.
     */
    public boolean entregarBilletes(double montoDouble) {
        int monto = (int) montoDouble;

        // Calcular cuántos billetes de cada denominación usar
        int usar200 = Math.min(monto / 200, billetes200);
        monto -= usar200 * 200;
        int usar100 = Math.min(monto / 100, billetes100);
        monto -= usar100 * 100;
        int usar50  = Math.min(monto / 50, billetes50);
        monto -= usar50 * 50;
        int usar20  = Math.min(monto / 20, billetes20);
        monto -= usar20 * 20;
        int usar10  = Math.min(monto / 10, billetes10);
        monto -= usar10 * 10;

        if (monto != 0) return false; // No se puede dar el cambio exacto

        // Descontar del inventario
        billetes200 -= usar200;
        billetes100 -= usar100;
        billetes50  -= usar50;
        billetes20  -= usar20;
        billetes10  -= usar10;

        System.out.println("  Billetes entregados:");
        if (usar200 > 0) System.out.printf("    S/.200 x %d%n", usar200);
        if (usar100 > 0) System.out.printf("    S/.100 x %d%n", usar100);
        if (usar50  > 0) System.out.printf("    S/. 50 x %d%n", usar50);
        if (usar20  > 0) System.out.printf("    S/. 20 x %d%n", usar20);
        if (usar10  > 0) System.out.printf("    S/. 10 x %d%n", usar10);
        return true;
    }

    public void recargarBilletes(int b200, int b100, int b50, int b20, int b10) {
        this.billetes200 += b200;
        this.billetes100 += b100;
        this.billetes50  += b50;
        this.billetes20  += b20;
        this.billetes10  += b10;
        System.out.printf("Cajero recargado. Total disponible: S/. %.2f%n",
                calcularTotalDisponible());
    }

    public double calcularTotalDisponible() {
        return (billetes200 * 200.0)
             + (billetes100 * 100.0)
             + (billetes50  *  50.0)
             + (billetes20  *  20.0)
             + (billetes10  *  10.0);
    }

    public void mostrarInventario() {
        System.out.println("  Inventario del cajero:");
        System.out.printf("    S/.200 : %d unidades%n", billetes200);
        System.out.printf("    S/.100 : %d unidades%n", billetes100);
        System.out.printf("    S/. 50 : %d unidades%n", billetes50);
        System.out.printf("    S/. 20 : %d unidades%n", billetes20);
        System.out.printf("    S/. 10 : %d unidades%n", billetes10);
        System.out.printf("    Total  : S/. %.2f%n", calcularTotalDisponible());
    }

    // Getters y setters
    public int getBilletes200() { return billetes200; }
    public void setBilletes200(int billetes200) { this.billetes200 = billetes200; }
    public int getBilletes100() { return billetes100; }
    public void setBilletes100(int billetes100) { this.billetes100 = billetes100; }
    public int getBilletes50() { return billetes50; }
    public void setBilletes50(int billetes50) { this.billetes50 = billetes50; }
    public int getBilletes20() { return billetes20; }
    public void setBilletes20(int billetes20) { this.billetes20 = billetes20; }
    public int getBilletes10() { return billetes10; }
    public void setBilletes10(int billetes10) { this.billetes10 = billetes10; }
}
