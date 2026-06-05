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
         Administrador admin = new Administrador(
                "Gerente",
                "U001",
                "Carlos Ramos",
                "carlos@gmail.com",
                "12345"
        );

        
        Cliente cliente1 = new Cliente(
                "987654321",
                "Cajamarca",
                "Frecuente"
        );

       
        Traje traje1 = new Traje(
                "T001",
                "Traje de Marinera",
                "Marinera Norteña",
                "M",
                true,
                "80"
        );

       
        Pago pago1 = new Pago(
                1,
                new Date(),
                80.00,
                "Yape"
        );

        
        Alquiler alquiler1 = new Alquiler(
                1001,
                "05/06/2026",
                "10/06/2026",
                80.00,
                "Pendiente",
                cliente1,
                pago1
        );

        
        ReporteAdministrador rep1 = new ReporteAdministrador(
                "R001",
                new Date(),
                "Reporte de alquileres"
        );

        
        admin.iniciarSesion();

        System.out.println(" LA CASA DEL FOLCLORE ");

        
        System.out.println("1. Registrar cliente");
        cliente1.registrar();
        admin.gestionarClientes();

       
        System.out.println("2. Registrar traje");
        admin.gestionarTrajes();

        
        System.out.println("3. Registrar alquiler");
        alquiler1.registrarAlquiler();

        
        System.out.println("4. Registrar pago");
        pago1.registrarPago();

        
        System.out.println("5. Consultar inventario");
        System.out.println("Código: " + traje1.getIdTraje());
        System.out.println("Nombre: " + traje1.getNombre_Traje());
        System.out.println("Danza: " + traje1.getDanza());
        System.out.println("Talla: " + traje1.getTalla());
        System.out.println("Estado: " + traje1.consultarDisponible());
        System.out.println("Precio: S/. " + traje1.getPrecioAlquiler());

        
        cliente1.reserva();

        
        System.out.println("6. Generar reporte");
        rep1.generarReporte();
        admin.gestionarReporte(rep1);

        System.out.println("Lista de reportes:");
        admin.mostrarReportes();

        
        alquiler1.devolverTraje();

        
        System.out.println("7. Cerrar sesión");
        admin.cerrarSesion();
    }
}



