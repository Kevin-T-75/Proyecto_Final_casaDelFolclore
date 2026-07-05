/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author OS
 */
public class PagoBD {
    
   // Tu método para obtener datos del cliente...
    public String[] obtenerDatosCliente(String nroDocumento) {
        String sql = "SELECT nombre, apellido_paterno, apellido_materno, telefono, correo, nro_documento FROM clientes WHERE nro_documento = ?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nroDocumento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido_paterno") + " " + rs.getString("apellido_materno");
                    return new String[]{nombreCompleto, rs.getString("nro_documento"), rs.getString("telefono"), rs.getString("correo")};
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    // Tu método para obtener datos del traje...
    public String[] obtenerDatosTraje(String idTraje) {
        String sql = "SELECT id_traje, talla, precio_traje FROM traje WHERE id_traje = ?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idTraje);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new String[]{rs.getString("id_traje"), rs.getString("talla"), rs.getString("precio_traje")};
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar traje: " + e.getMessage());
        }
        return null;
    }
    
    // EL MÉTODO QUE ACTUALIZA EL ESTADO DEL TRAJE
    public boolean actualizarEstadoTraje(String idTraje, String nuevoEstado) {
        String sql = "UPDATE traje SET estado = ? WHERE id_traje = ?";
        
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nuevoEstado);
            ps.setString(2, idTraje);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; 
            
        } catch (SQLException e) { // Se cambió a SQLException que es más preciso
            System.out.println("Error al actualizar estado del traje: " + e.getMessage());
            return false;
        }
    }
    
    
}

