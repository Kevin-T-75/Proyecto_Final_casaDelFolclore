package proyecto_final_casafolclore.BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    private static final String HOST = "bkftbu5lrsswz4pdhwld-mysql.services.clever-cloud.com"; 
    private static final String PORT = "3306";
    private static final String DB_NAME = "bkftbu5lrsswz4pdhwld";
    private static final String USER = "uoywlsfq7buhjj43";
    private static final String PASSWORD = "GPoXgZ3t1h9wdDGiZuKf";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

    public static Connection getConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡CONEXIÓN EXITOSA! Te has conectado a la base de datos en la nube.");
            
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: No se encontró el Driver de MySQL (Revisa el Paso 1).");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("ERROR: Error de SQL. Revisa las credenciales o tu conexión a internet.");
            e.printStackTrace();
        }
        return conexion;
    }
}
