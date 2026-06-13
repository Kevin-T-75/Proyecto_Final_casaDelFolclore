/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.Clases;

/**
 *
 * @author OS
 */
public abstract class Usuario {
    
    protected String idUsuario;
    protected  String nombre_Usuario; 
    protected  String correo_Usuario; 
    protected  String contraseña_Usuario; 

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre_Usuario() {
        return nombre_Usuario;
    }

    public void setNombre_Usuario(String nombre_Usuario) {
        this.nombre_Usuario = nombre_Usuario;
    }

    public String getCorreo_Usuario() {
        return correo_Usuario;
    }

    public void setCorreo_Usuario(String correo_Usuario) {
        this.correo_Usuario = correo_Usuario;
    }

    public String getContraseña_Usuario() {
        return contraseña_Usuario;
    }

    public void setContraseña_Usuario(String contraseña_Usuario) {
        this.contraseña_Usuario = contraseña_Usuario;
    }

    public Usuario(String idUsuario, String nombre_Usuario, String correo_Usuario, String contraseña_Usuario) {
        this.idUsuario = idUsuario;
        this.nombre_Usuario = nombre_Usuario;
        this.correo_Usuario = correo_Usuario;
        this.contraseña_Usuario = contraseña_Usuario;
    }
    
    
    public abstract void iniciarSesion();
       
    
    public abstract void cerrarSesion();
        
    

    
}
