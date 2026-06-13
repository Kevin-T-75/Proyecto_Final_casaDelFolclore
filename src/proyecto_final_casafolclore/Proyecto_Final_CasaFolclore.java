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
    
    Scanner sc = new Scanner(System.in);

    System.out.println("1. Iniciar como Administrador");
    System.out.println("2. Iniciar como Cliente");
    System.out.print("Seleccione una opción: ");

    int tipoUsuario = sc.nextInt();
    sc.nextLine();

    if (tipoUsuario == 1) {

        System.out.println("\n--- DATOS DEL ADMINISTRADOR ---");

        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();

        System.out.print("Cargo: ");
        String cargo = sc.nextLine();

        Administrador admin = new Administrador(
                cargo,
                id,
                nombre,
                correo,
                contraseña
        );

        admin.iniciarSesion();

        int opcion;

        do {

        System.out.println("\n===== MENÚ ADMINISTRADOR =====");
        System.out.println("1. Registrar Cliente");
        System.out.println("2. Registrar Traje");
        System.out.println("3. Registrar Alquiler");
        System.out.println("4. Registrar Pago");
        System.out.println("5. Consultar Inventario");
//        System.out.println("6. Generar Reporte"); //para ver si borramos
        System.out.println("0. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");

        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {

            case 1:

                System.out.println("\n--- REGISTRO CLIENTE ---");

                System.out.print("Teléfono: ");
                String telefono = sc.nextLine();

                System.out.print("Dirección: ");
                String direccion = sc.nextLine();

                System.out.print("Tipo de cliente: ");
                String tipoCliente = sc.nextLine();

                Cliente cliente = new Cliente(
                        telefono,
                        direccion,
                        tipoCliente
                );

                System.out.println("Cliente registrado correctamente.");
                admin.gestionarClientes();

                break;

            case 2:

                System.out.println("\n--- REGISTRO TRAJE ---");

                System.out.print("Código: ");
                String idTraje = sc.nextLine();

                System.out.print("Nombre: ");
                String nombreTraje = sc.nextLine();

                System.out.print("Danza: ");
                String danza = sc.nextLine();

                System.out.print("Talla: ");
                String talla = sc.nextLine();

                System.out.print("Precio alquiler: ");
                String precio = sc.nextLine();

                Traje traje = new Traje(
                        idTraje,
                        nombreTraje,
                        danza,
                        talla,
                        true,
                        precio
                );

                System.out.println("Traje registrado correctamente.");
                admin.gestionarTrajes();

              

            case 3:

                System.out.println("\n--- REGISTRO ALQUILER ---");

                System.out.print("ID alquiler: ");
                int idAlquiler = sc.nextInt();
                sc.nextLine();

                System.out.print("Fecha inicio: ");
                String fechaInicio = sc.nextLine();

                System.out.print("Fecha fin: ");
                String fechaFin = sc.nextLine();

                System.out.print("Monto: ");
                double monto = sc.nextDouble();
                sc.nextLine();

                Cliente clienteAlquiler = new Cliente(
                        "999999999",
                        "Sin dirección",
                        "Normal"
                );

                Pago pagoAlquiler = new Pago(
                        1,
                        new Date(),
                        monto,
                        "Pendiente"
                );

                Alquiler alquiler = new Alquiler(
                        idAlquiler,
                        fechaInicio,
                        fechaFin,
                        monto,
                        "ACTIVO",
                        clienteAlquiler,
                        pagoAlquiler
                );

                alquiler.registrarAlquiler();

                break;

            case 4:

                System.out.println("\n--- REGISTRO PAGO ---");

                System.out.print("ID Pago: ");
                int idPago = sc.nextInt();

                System.out.print("Monto: ");
                double montoPago = sc.nextDouble();
                sc.nextLine();

                System.out.print("Método de pago: ");
                String metodo = sc.nextLine();

                Pago pago = new Pago(
                        idPago,
                        new Date(),
                        montoPago,
                        metodo
                );

                pago.registrarPago();

                break;

            case 5:

                System.out.println("\n--- CONSULTA INVENTARIO ---");
                System.out.println("Funcionalidad disponible.");
                break;

//            case 6:
//
////                ReporteAdministrador reporte =
////                        new ReporteAdministrador(
////                                "R001",
////                                new Date(),
////                                "Reporte General"
////                        );
////
////                reporte.generarReporte();
////                admin.gestionarReporte(reporte);
////                admin.mostrarReportes();
//
//                break;

            case 0:

                admin.cerrarSesion();
                System.out.println("Sesión finalizada.");
                break;

            default:

                System.out.println("Opción inválida.");

        }

    } while (opcion != 7);

    } else if (tipoUsuario == 2) {

        System.out.println("\n--- DATOS DEL CLIENTE ---");

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Tipo de cliente: ");
        String tipoCliente = sc.nextLine();

        Cliente cliente = new Cliente(
                telefono,
                direccion,
                tipoCliente
        );

        int opcion;

        do {

            System.out.println("\n===== MENÚ CLIENTE =====");
            System.out.println("1. Ver Datos");
            System.out.println("2. Reservar");
            System.out.println("3. Alquilar");
            System.out.println("4. Salir");
            System.out.print("Seleccione: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

            case 1:

                System.out.println("Teléfono: "
                        + cliente.getTelefono_Cliente());

                System.out.println("Dirección: "
                        + cliente.getDireccion_Cliente());

                System.out.println("Tipo: "
                        + cliente.getTipo_Cliente());

            break;

                case 2:

                    cliente.reserva();
                    System.out.println("Reserva realizada.");
                    break;

                case 3:

                    cliente.alquilar();
                    System.out.println("Alquiler realizado.");
                    break;

                case 4:

                    System.out.println("Gracias por usar el sistema.");
                    break;

                default:

                    System.out.println("Opción inválida.");

            }

        } while (opcion != 4);

    } else {

        System.out.println("Opción de usuario inválida.");

    }

     sc.close();
    }
}

