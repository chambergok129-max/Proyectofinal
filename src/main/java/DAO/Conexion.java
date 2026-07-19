package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:5432/postgres" +
                    "?user=postgres.zdnzgkavqnzpfzwvgcvd" +   // ← agregado el project-ref
                    "&password=Sha\"HK015975" +
                    "&sslmode=require";

    private static Connection instancia = null;

    private Conexion() {}

    public static Connection getInstancia() throws SQLException {
        if (instancia == null || instancia.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                instancia = DriverManager.getConnection(URL);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver no encontrado: " + e.getMessage());
            }
        }
        return instancia;
    }

    public static void cerrar() {
        try {
            if (instancia != null && !instancia.isClosed()) {
                instancia.close();
                instancia = null;
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar: " + e.getMessage());
        }
    }
}