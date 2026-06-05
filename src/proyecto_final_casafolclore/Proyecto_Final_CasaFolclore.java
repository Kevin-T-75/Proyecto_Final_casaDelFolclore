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

        ReporteAdministrador rep1 = new ReporteAdministrador(
                "R001",
                new Date(),
                "Reporte de alquileres"
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

        admin.iniciarSesion();

        System.out.println("1. Registrar cliente");
        cliente1.registrar();
        admin.gestionarClientes();

        System.out.println("2. Registrar traje");
        admin.gestionarTrajes();

        System.out.println("3. Registrar alquiler");
        cliente1.alquilar();

        System.out.println("4. Registrar pago");
        System.out.println("Pago registrado correctamente");

        System.out.println("5. Consultar inventario");
        System.out.println("Estado del traje: " + traje1.consultarDisponible());

        System.out.println("6. Generar reporte");
        rep1.generarReporte();
        admin.gestionarReporte(rep1);

        System.out.println("Lista de reportes:");
        admin.mostrarReportes();

        System.out.println("7. Cerrar sesión");
        admin.cerrarSesion();
    }
}



