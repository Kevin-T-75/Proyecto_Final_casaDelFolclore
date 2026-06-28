/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.Clases;

/**
 *
 * @author OS
 */
public abstract class Persona {
    
    protected String idPersona;
    protected  String nombre; 
    protected  String apellidoPaterno; 
    protected  String apellidoMaterno; 
    protected  String tipoDocumento; 
    protected  String nroDocumento; 
    protected  String contraseña_Usuario; 
    protected  String correo_Usuario; 

    public Persona() {}
    
    public Persona(String idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumento, String nroDocumento, String contraseña_Usuario, String correo_Usuario) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.contraseña_Usuario = contraseña_Usuario;
        this.correo_Usuario = correo_Usuario;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getContraseña_Usuario() {
        return contraseña_Usuario;
    }

    public void setContraseña_Usuario(String contraseña_Usuario) {
        this.contraseña_Usuario = contraseña_Usuario;
    }

    public String getCorreo_Usuario() {
        return correo_Usuario;
    }

    public void setCorreo_Usuario(String correo_Usuario) {
        this.correo_Usuario = correo_Usuario;
    }
    
    
   

    public abstract void iniciarSesion();    
    
    public abstract void cerrarSesion();
 
}
