/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Inventario {
    private String idInventario;
    private int CantidadDisponible;
    private int CantidadTotal;

    public ArrayList<Traje> listaTraje = new ArrayList();
    
    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public int getCantidadDisponible() {
        return CantidadDisponible;
    }

    public void setCantidadDisponible(int CantidadDisponible) {
        this.CantidadDisponible = CantidadDisponible;
    }

    public int getCantidadTotal() {
        return CantidadTotal;
    }

    public void setCantidadTotal(int CantidadTotal) {
        this.CantidadTotal = CantidadTotal;
    }
    
    public void AgregarTraje(Traje trajes)
    {
         listaTraje.add(trajes);
        CantidadTotal++;
        CantidadDisponible++;
    }
    
    public void EliminarTraje(Traje trajes)
    {
        if(listaTraje.remove(trajes))
        {
            CantidadTotal--;
            CantidadDisponible--;
        }
    }
    
    
}
