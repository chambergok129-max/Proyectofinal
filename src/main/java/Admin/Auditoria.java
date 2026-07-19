package Admin;

import DAO.MovimientoDAO;
import DTO.MovimientoDTO;
import Main.Imprimible;
import Main.TIPOMOVIMIENTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Auditoria implements Imprimible {

    private MovimientoDAO movimientoDAO = new MovimientoDAO();
    private List<MovimientoDTO> movimientosDelDia;
    private LocalDate fecha;

    public Auditoria() {
        this.fecha = LocalDate.now();
    }

    public void consultarTransacciones() throws SQLException {
        movimientosDelDia = movimientoDAO.buscarPorFecha(fecha);
        System.out.println("Transacciones cargadas: " + movimientosDelDia.size());
    }

    public void generarReporteDiario() throws SQLException {
        if (movimientosDelDia == null) consultarTransacciones();
        imprimir();
    }

    @Override
    public void imprimir() {
        String linea = "=".repeat(66);
        System.out.println(linea);
        System.out.println("           REPORTE DIARIO DE OPERACIONES");
        System.out.printf("           Fecha: %s%n",
                fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(linea);

        if (movimientosDelDia == null || movimientosDelDia.isEmpty()) {
            System.out.println(" No hay operaciones ");
        } else {
            // Resumen por tipo
            Map<TIPOMOVIMIENTO, Long> conteo = movimientosDelDia.stream()
                    .collect(Collectors.groupingBy(MovimientoDTO::getTipo, Collectors.counting()));
            Map<TIPOMOVIMIENTO, Double> totales = movimientosDelDia.stream()
                    .collect(Collectors.groupingBy(MovimientoDTO::getTipo,
                            Collectors.summingDouble(MovimientoDTO::getMonto)));

            System.out.println(" Resumen: ");
            System.out.printf("  %-20s %10s %15s%n", "Tipo", "Cantidad", "Total S/.");
            System.out.println("  " + "-".repeat(47));
            for (TIPOMOVIMIENTO tipo : conteo.keySet()) {
                System.out.printf("  %-20s %10d %15.2f%n",
                        tipo, conteo.get(tipo),
                        totales.getOrDefault(tipo, 0.0));
            }

            System.out.println(linea);
            System.out.println(" Detalle: ");
            System.out.printf("  %-12s %-8s %-18s %10s%n",
                    "Fecha", "Hora", "Tipo", "Monto");
            System.out.println("  " + "-".repeat(50));
            for (MovimientoDTO m : movimientosDelDia) {
                System.out.println("  " + m);
            }
        }
        System.out.println(linea);
    }
}
