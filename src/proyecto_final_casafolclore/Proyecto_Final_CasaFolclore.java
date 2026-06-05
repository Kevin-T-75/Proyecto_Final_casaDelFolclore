/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_final_casafolclore;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author OS
 */
public class Proyecto_Final_CasaFolclore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try ( // TODO code application logic here
                Scanner sc = new Scanner(System.in)) {
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
            
            int opcion;
            
            do {
                System.out.println("\n LA CASA DEL FOLCLORE ");
                System.out.println("1. Registrar cliente");
                System.out.println("2. Registrar traje");
                System.out.println("3. Registrar alquiler");
                System.out.println("4. Registrar pago");
                System.out.println("5. Consultar inventario");
                System.out.println("6. Generar reporte");
                System.out.println("7. Cerrar sesión");
                System.out.print("Seleccione una opción: ");
                
                opcion = sc.nextInt();
                
                switch (opcion) {
                    
                    case 1 -> {
                        System.out.println("\nCLIENTE");
                        System.out.println("Telefono: " + cliente1.getTelefono_Cliente());
                        System.out.println("Direccion: " + cliente1.getDireccion_Cliente());
                        System.out.println("Tipo: " + cliente1.getTipo_Cliente());
                        admin.gestionarClientes();
                    }
                        
                    case 2 -> {
                        System.out.println("\nTRAJE");
                        System.out.println("Codigo: " + traje1.getIdTraje());
                        System.out.println("Nombre: " + traje1.getNombre_Traje());
                        admin.gestionarTrajes();
                    }
                        
                    case 3 -> alquiler1.registrarAlquiler();
                        
                    case 4 -> pago1.registrarPago();
                        
                    case 5 -> {
                        System.out.println("\nINVENTARIO");
                        System.out.println("Codigo: " + traje1.getIdTraje());
                        System.out.println("Nombre: " + traje1.getNombre_Traje());
                        System.out.println("Estado: " + traje1.consultarDisponible());
                        System.out.println("Precio: S/. " + traje1.getPrecioAlquiler());
                    }
                        
                    case 6 -> {
                        rep1.generarReporte();
                        admin.gestionarReporte(rep1);
                        admin.mostrarReportes();
                    }
                        
                    case 7 -> {
                        admin.cerrarSesion();
                        System.out.println("Sesión finalizada.");
                    }
                        
                    default -> System.out.println("Opción no válida.");
                }
                
            } while (opcion != 7);
        }
    }
}

