/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore;

import java.util.Date;

/**
 *
 * @author neyli
 */
public class ReporteAdministrador {
    private String idReporte;
    private Date fechaGeneracion;
    private String tipoReporte;

    public ReporteAdministrador(String idReporte, Date fechaGeneracion, String tipoReporte) {
        this.idReporte = idReporte;
        this.fechaGeneracion = fechaGeneracion;
        this.tipoReporte = tipoReporte;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

   
    
}
