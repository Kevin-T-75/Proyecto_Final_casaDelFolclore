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
 * @author SAHIR
 */
public class registrarCliente {
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
    String sql = "SELECT id_usuario FROM clientes ORDER BY id_usuario ASC";
  
    java.util.HashSet<String> idsExistentes = new java.util.HashSet<>();
    
    try (Connection cn = conexionBD.getConexion();
         PreparedStatement pst = cn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        
        while (rs.next()) {
            idsExistentes.add(rs.getString("id_usuario"));
        }
        
        for (int i = 1; i <= 9999; i++) {
            String idCandidato = String.format("F%04d", i);
            
            if (!idsExistentes.contains(idCandidato)) {
                return idCandidato; 
            }
        }
        
    } catch (SQLException e) {
        System.out.println("Error al generar el siguiente ID automático: " + e);
    }
    
    return "F0001";
    }
    
}
   public boolean modificarCliente(String id, String nombre, String appPaterno, String appMaterno, 
                                String tipoDoc, String nroDoc, String correo, String contra, 
                                String tel, String dir, String tipoCliente) {
    
    // Consulta SQL con los nombres exactos de tus columnas
    String sql = "UPDATE clientes SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, "
               + "tipo_documento = ?, nro_documento = ?, correo = ?, contrasena = ?, "
               + "tipo_cliente = ?, telefono = ?, direccion = ? WHERE id_usuario = ?";
    
    try (Connection cn = conexionBD.getConexion(); 
         PreparedStatement pst = cn.prepareStatement(sql)) {

        // Pasamos los nuevos datos a los parámetros '?'
        pst.setString(1, nombre);
        pst.setString(2, appPaterno);
        pst.setString(3, appMaterno);
        pst.setString(4, tipoDoc);
        pst.setString(5, nroDoc);
        pst.setString(6, correo);
        pst.setString(7, contra);
        pst.setString(8, tipoCliente);
        pst.setString(9, tel);
        pst.setString(10, dir);
        
        // El último '?' es el WHERE para saber a qué cliente modificar
        pst.setString(11, id); 
        
        int filasAfectadas = pst.executeUpdate(); 
        
        // Si filasAfectadas es mayor a 0, significa que se actualizó con éxito
        return filasAfectadas > 0;
        
    } catch (SQLException e) {
        System.out.println("Error al modificar el cliente en la nube: " + e);
        return false;
    }
}
    
}

