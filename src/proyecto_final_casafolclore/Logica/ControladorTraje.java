/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.Logica;

import java.util.ArrayList;
import proyecto_final_casafolclore.Clases.Traje;


/**
 *
 * @author neyli
 */
public class ControladorTraje {
    private ArrayList<Traje> listaTrajes = new ArrayList<>();
    private static int contador = 1; //este sera para la ID traje
    
    public String generarID() {
        return String.format("T%04d", contador); //numeros automaticos
    }
    
    public void registrarTraje(Traje traje) {
        listaTrajes.add(traje);
        contador++;//despues de registrar empieza a sumar
    }

    public ArrayList<Traje> getListaClientes() {
        return listaTrajes;
    }
}
