/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_final_casafolclore;
//import proyecto_final_casafolclore.Clases.Traje;
//import proyecto_final_casafolclore.Clases.Pago;

import proyecto_final_casafolclore.BaseDatos.conexionBD;

//import proyecto_final_casafolclore.Clases.Cliente;
//import proyecto_final_casafolclore.Clases.Alquiler;
//import proyecto_final_casafolclore.Clases.Administrador;
//import java.util.Date;
//import java.util.Scanner;

/**
 *
 * @author OS
 */
public class Proyecto_Final_CasaFolclore {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
       System.out.println("Iniciando Proyecto_Final_CasaFolclore...");
        
        java.sql.Connection conexionActiva = conexionBD.getConexion();
        
        if (conexionActiva != null) {
            System.out.println("¡Todo listo para empezar a programar el sistema!");
   }
   }
}


