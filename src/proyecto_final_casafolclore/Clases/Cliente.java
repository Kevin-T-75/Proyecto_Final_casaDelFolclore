/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.Clases;

/**
 *
 * @author OS
 */
public class Cliente {
    
    private String telefono_Cliente;
    private String direccion_Cliente;
    private String tipo_Cliente;

    public String getTelefono_Cliente() {
        return telefono_Cliente;
    }

    public void setTelefono_Cliente(String telefono_Cliente) {
        this.telefono_Cliente = telefono_Cliente;
    }

    public String getDireccion_Cliente() {
        return direccion_Cliente;
    }

    public void setDireccion_Cliente(String direccion_Cliente) {
        this.direccion_Cliente = direccion_Cliente;
    }

    public String getTipo_Cliente() {
        return tipo_Cliente;
    }

    public void setTipo_Cliente(String tipo_Cliente) {
        this.tipo_Cliente = tipo_Cliente;
    }

    public Cliente(String telefono_Cliente, String direccion_Cliente, String tipo_Cliente) {
        this.telefono_Cliente = telefono_Cliente;
        this.direccion_Cliente = direccion_Cliente;
        this.tipo_Cliente = tipo_Cliente;
    }
    
    public void registrar(){
        
    }
    public void reserva(){
        
    }
    public void alquilar(){
        
    }

        
    
    
}
