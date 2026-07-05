/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.BaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author SAHIR
 */
public class reservaBD {
     public String obtenerNombreCliente(String nroDocumento) {
        // Usamos los nombres exactos de tu tabla 'clientes'
        String sql = "SELECT nombre, apellido_paterno, apellido_materno FROM clientes WHERE nro_documento = ?"; 
        
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nroDocumento);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Concatenamos Nombre + Apellido Paterno + Apellido Materno
                    String nombreCompleto = rs.getString("nombre") + " " + 
                                            rs.getString("apellido_paterno") + " " + 
                                            rs.getString("apellido_materno");
                    return nombreCompleto; 
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al buscar el cliente por documento: " + e.getMessage());
        }
        return null; // Retorna null si no existe
    }
     
     
    public String[] obtenerDatosTraje(String nombreTraje, String talla, String genero) {
    // Consulta corregida limpiamente sin errores de comillas ni letras extra
    String sql = "SELECT id_traje, precio_traje FROM traje "
               + "WHERE nombre_traje LIKE ? "
               + "AND talla LIKE ? "
               + "AND (genero LIKE ? OR genero LIKE 'VARON%')";
    
    try (Connection con = conexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Pasamos los parámetros asegurando que no lleven espacios accidentales
        ps.setString(1, nombreTraje.trim());
        ps.setString(2, talla.trim());
        ps.setString(3, genero.trim());
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String id = rs.getString("id_traje");
                String precio = rs.getString("precio_traje");
                return new String[]{id, precio}; 
            }
        }
        
    } catch (Exception e) {
        System.out.println("Error al buscar los datos del traje: " + e.getMessage());
    }
    return null; 
}
}
