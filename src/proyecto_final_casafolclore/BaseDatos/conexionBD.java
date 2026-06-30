package proyecto_final_casafolclore.BaseDatos;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    
 // regitrar clientes a base de datos
    public boolean registrarCliente(String id, String nombre, String appPaterno, String appMaterno, 
                                    String tipoDoc, String nroDoc, String correo, String contra, 
                                    String tel, String dir, String tipoCliente) {
        
        String sql = "INSERT INTO clientes (id_usuario, nombre, apellido_paterno, apellido_materno, "
                   + "tipo_documento, nro_Documento, correo, contrasena, Tipo_Cliente, Telefono, Direccion) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection cn = conexionBD.getConexion(); 
        PreparedStatement pst = cn.prepareStatement(sql)) {

            
            pst.setString(1, id);
            pst.setString(2, nombre);
            pst.setString(3, appPaterno);
            pst.setString(4, appMaterno);
            pst.setString(5, tipoDoc);
            pst.setString(6, nroDoc);
            pst.setString(7, correo);
            pst.setString(8, contra);
            pst.setString(9, tipoCliente);
            pst.setString(10, tel);
            pst.setString(11, dir);
            
            pst.executeUpdate(); 
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar en la nube: " + e);
            return false;
        }
    }
    
   public String obtenerSiguienteID() {
    // Cambiado a "F0001" (4 dígitos para coincidir con tu controlador)
    String siguienteID = "F0001"; 
    
    String sql = "SELECT MAX(id_usuario) FROM clientes";
    
    try (Connection cn = conexionBD.getConexion();
         PreparedStatement pst = cn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        
        if (rs.next() && rs.getString(1) != null) {
            String idMaximo = rs.getString(1); // Recupera el de la nube, ej: "F0015"
            
            // Extrae el número quitando la 'F' de la posición 0
            int numero = Integer.parseInt(idMaximo.substring(1)); 
            
            // Incrementa en 1 para el siguiente cliente
            numero++; 
            
            // Vuelve a armar el formato manteniendo los 4 dígitos (Ej: "F0016")
            siguienteID = String.format("F%04d", numero); 
        }
    } catch (SQLException e) {
        System.out.println("Error al generar el siguiente ID en la nube: " + e);
    }
    
    return siguienteID; 
    
}
     public boolean registrarTraje(String id, String nombre, String genero, String talla, String precio) {
        String sql = "INSERT INTO traje (id_traje, nombre_traje, genero, talla, precio_traje) VALUES (?, ?, ?, ?, ?)";
        
        // Usamos tu método getConexion() para abrir el enlace a la nube
        try (Connection con = getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, id);
            ps.setString(2, nombre);
            ps.setString(3, genero);
            ps.setString(4, talla);
            ps.setString(5, precio);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al registrar traje en la BD: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    } 

    public String obtenerSiguienteIDTraje() {
        String siguienteID = "T0001"; // ID por defecto si la tabla está vacía
        String sql = "SELECT MAX(id_traje) FROM traje";
        
        try (Connection cn = getConexion();
             PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            if (rs.next() && rs.getString(1) != null) {
                String idMaximo = rs.getString(1); // Recupera el más alto, ej: "T0001"
                
                // Extrae el número quitando la 'T' de la posición 0
                int numero = Integer.parseInt(idMaximo.substring(1)); 
                
                // Incrementa en 1 para el nuevo traje (Se volverá 2)
                numero++; 
                
                // Lo vuelve a formatear con 4 dígitos (Ej: "T0002")
                siguienteID = String.format("T%04d", numero); 
            }
        } catch (SQLException e) {
            System.out.println("Error al generar el siguiente ID de traje en la nube: " + e);
        }
        
        return siguienteID; 
    }
}         

