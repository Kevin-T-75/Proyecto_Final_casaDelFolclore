/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.Logica;

import java.util.ArrayList;
import proyecto_final_casafolclore.Clases.Cliente;

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
    
    public void registrarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
}
