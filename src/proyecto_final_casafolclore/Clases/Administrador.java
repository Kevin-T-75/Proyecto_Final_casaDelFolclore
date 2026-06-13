/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.Clases;

//import java.util.ArrayList; //por si acaso, solo lo dejare comentado lo del reporte


/**
 *
 * @author neyli
 */
public class Administrador extends Usuario{
    
    private String cargo;

    public Administrador(String cargo, String idUsuario, String nombre_Usuario, String correo_Usuario, String contraseña_Usuario) {
        super(idUsuario, nombre_Usuario, correo_Usuario, contraseña_Usuario);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    @Override
    public void iniciarSesion() {
        System.out.println("Administrador inicio sesion");
    }

    @Override
    public void cerrarSesion() {
        System.out.println("Administrador cerro sesion");
    }
    
//    public void gestionarReporte(ReporteAdministrador r) {
//        reportes.add(r);
//        System.out.println("Reporte agregado correctamente");
//    }

    public void gestionarTrajes() {
        System.out.println("Gestion de trajes completada");
    }

    public void gestionarClientes() {
        System.out.println("Gestion de clientes completada");
    }
    
//    public void mostrarReportes() {
//        System.out.println("Lista de reportes actual: ");
//        
//        for (int i = 0; i < reportes.size(); i++) {
//            ReporteAdministrador r = reportes.get(i);
//            r.mostrarReporte();
//            System.out.println("----------------");
//        }
//    }
}
