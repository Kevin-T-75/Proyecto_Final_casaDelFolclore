/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore;

/**
 *
 * @author OS
 */
public class Traje {
    
    private String idTraje;
    private String nombre_Traje;
    private String danza;
    private String talla;
    private boolean estado;
    private String precioAlquiler;

    public String getIdTraje() {
        return idTraje;
    }

    public void setIdTraje(String idTraje) {
        this.idTraje = idTraje;
    }

    public String getNombre_Traje() {
        return nombre_Traje;
    }

    public void setNombre_Traje(String nombre_Traje) {
        this.nombre_Traje = nombre_Traje;
    }

    public String getDanza() {
        return danza;
    }

    public void setDanza(String danza) {
        this.danza = danza;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

   
    
    public String getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(String precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public Traje(String idTraje, String nombre_Traje, String danza, String talla, boolean estado, String precioAlquiler) {
        this.idTraje = idTraje;
        this.nombre_Traje = nombre_Traje;
        this.danza = danza;
        this.talla = talla;
        this.estado = estado;
        this.precioAlquiler = precioAlquiler;
    }
    
    public void actualizarEstado(){
        
    }
    public void consultarDisponible(){
        
    }

   
    
    

    
}
