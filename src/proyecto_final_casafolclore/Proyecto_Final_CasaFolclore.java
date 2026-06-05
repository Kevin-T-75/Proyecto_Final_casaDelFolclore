/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_final_casafolclore;
import java.util.Date;

/**
 *
 * @author OS
 */
public class Proyecto_Final_CasaFolclore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Administrador admin = new Administrador("Gerente", "U001", "Carlos Ramos", "carlos@gmail.com", "12345");
        ReporteAdministrador rep1 = new ReporteAdministrador("R001", new Date(), "Reporte de alquileres");
     
        //el administrador tiene las siguientes opciones:
        
//        1. Registrar cliente;
//        2. Registrar traje;
//        3. Registrar alquiler;
//        4. Registrar pago;
//        5. Consultar inventario
//        6. Gnerar reporte
//        7. Cerrar sesioon
        
        admin.iniciarSesion();
        System.out.println("hola");
    }
    
}
