package appregistro.bodegaventas.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/venta_java";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexionBD() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion BD Exitosamente");
        } catch (SQLException ex) {
            System.err.println("Error conectar a la BD" + ex.getMessage());
        }
        return conexion;
    }

    public static void closeConexion(Connection conexion){
        if(conexion != null){
            try {
                conexion.close();
                System.out.println("Conexion cerrada");
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexion" + ex.getMessage());
            }
        }
    }

}
