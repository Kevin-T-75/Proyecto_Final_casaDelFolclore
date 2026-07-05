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
       String sql = "SELECT id_traje FROM traje ORDER BY id_traje ASC";
  
    java.util.HashSet<String> idsExistentes = new java.util.HashSet<>();
    
    try (Connection cn = getConexion();
         PreparedStatement pst = cn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        
      
        while (rs.next()) {
            idsExistentes.add(rs.getString("id_traje"));
        }
        
       
        for (int i = 1; i <= 9999; i++) {
            // Formateamos el número actual (ej: si i=6, se vuelve "T0006")
            String idCandidato = String.format("T%04d", i);
            
            // Si este ID NO existe en la base de datos, ¡encontramos el hueco disponible!
            if (!idsExistentes.contains(idCandidato)) {
                return idCandidato; 
            }
        }
        
    } catch (SQLException e) {
        System.out.println("Error al generar el siguiente ID automático: " + e);
    }
    
    return "T0001"; 

    }
    
    
      public boolean registrarDevolucion(String idTraje) {
   String sqlContrato = "UPDATE contrato_alquiler SET estado_contrato = 'Disponible' "
                       + "WHERE id_traje = ? AND UPPER(TRIM(estado_contrato)) IN ('ALQUILADO', 'ALQUILER', 'RESERVADO')";
    
    String sqlTraje = "UPDATE traje SET estado = 'Disponible' WHERE id_traje = ?";
    
    Connection con = null;
    try {
        con = conexionBD.getConexion(); 
        con.setAutoCommit(false); 
        
        // PASO 1: Cambiar estado del contrato a Disponible
        int filasContrato = 0;
        try (PreparedStatement psContrato = con.prepareStatement(sqlContrato)) {
            psContrato.setString(1, idTraje);
            filasContrato = psContrato.executeUpdate();
        }
        
        // PASO 2: Liberar el traje a Disponible
        int filasTraje = 0;
        try (PreparedStatement psTraje = con.prepareStatement(sqlTraje)) {
            psTraje.setString(1, idTraje);
            filasTraje = psTraje.executeUpdate();
        }
        
        // Si no se afectó ninguna fila, cancelamos todo el proceso de forma segura
        if (filasContrato == 0 || filasTraje == 0) {
            con.rollback();
            return false;
        }
        
        con.commit(); 
        return true;
        
    } catch (SQLException e) {
        if (con != null) {
            try { con.rollback(); } catch (SQLException ex) { /* Silencioso */ }
        }
        return false;
    } finally {
        if (con != null) {
            try { con.close(); } catch (SQLException e) { /* Silencioso */ }
        }
    }
  }  
    
}
