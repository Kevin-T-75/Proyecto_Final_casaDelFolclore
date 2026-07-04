/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.BaseDatos;
import java.sql.PreparedStatement;
import java.sql.Connection;          
import java.sql.ResultSet;
/**
 *
 * @author SAHIR
 */
public class Inicio_de_sesion {
    public boolean validarLoginAdmin(String correo, String contrasena) {
        String sql = "SELECT * FROM Administrador WHERE correo = ? AND contrasena = ?";
        
        // Usamos el bloque try-with-resources que cierra automáticamente la conexión
       try (Connection con = conexionBD.getConexion(); 
        PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Retorna true si encontró coincidencia, false si no
            }
            
        } catch (Exception e) {
            System.out.println("Error al validar administrador: " + e.getMessage());
            return false;
        }
    }
}
