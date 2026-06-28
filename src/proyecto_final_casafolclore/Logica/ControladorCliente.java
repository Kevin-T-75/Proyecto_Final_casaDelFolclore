/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.Logica;

import java.util.ArrayList;
import proyecto_final_casafolclore.Clases.Cliente;

import proyecto_final_casafolclore.BaseDatos.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author neyli
 */
public class ControladorCliente {
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static int contador = 1; //este sera poara la ID usuario

    public String generarID() {
        return String.format("F%04d", contador++); //numeros automaticos
    }
    
    private void sincronizarContadorConBD() {
        String sql = "SELECT MAX(id_usuario) FROM clientes";
        try (Connection cn = conexionBD.getConexion();
             PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            if (rs.next() && rs.getString(1) != null) {
                String idMaximo = rs.getString(1); // Ej: "F0015"
                // Extraemos el número quitando la 'F'
                int ultimoNumero = Integer.parseInt(idMaximo.substring(1));
                // El contador debe ser el siguiente número disponible
                contador = ultimoNumero + 1; 
            } else {
                contador = 1; // Si la tabla está vacía, empieza en 1
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al sincronizar el contador de IDs: " + e);
            contador = 1; // Respaldo por si falla la red
        }
    }

    
    public void registrarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
}
