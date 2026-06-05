/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore;

/**
 *
 * @author SAHIR
 */
public class Alquiler {
    private int idAlquiler;
    private String fechaInicio;
    private String fechaFin;
    private double monto;
    private String estado;

    private Cliente cliente;
    private Pago pago;

    public Alquiler() {
    }

    public Alquiler(int idAlquiler, String fechaInicio, String fechaFin,
                    double monto, String estado, Cliente cliente, Pago pago) {
        this.idAlquiler = idAlquiler;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.monto = monto;
        this.estado = estado;
        this.cliente = cliente;
        this.pago = pago;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
    
    public void registrarAlquiler() {
        this.estado = "ACTIVO";
        System.out.println("Alquiler registrado correctamente");
    }

    public void devolverTraje() {
        this.estado = "DEVUELTO";
        System.out.println("Traje devuelto correctamente");
    }

}
