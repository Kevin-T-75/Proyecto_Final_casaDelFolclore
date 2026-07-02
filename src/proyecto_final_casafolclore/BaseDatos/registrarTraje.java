/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static proyecto_final_casafolclore.BaseDatos.conexionBD.getConexion;

/**
 *
 * @author SAHIR
 */
public class registrarTraje {
    
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
