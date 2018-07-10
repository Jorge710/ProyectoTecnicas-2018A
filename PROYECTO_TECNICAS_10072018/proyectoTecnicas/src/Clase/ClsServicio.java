/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clase;

/**
 *
 * @author User
 */
public class ClsServicio {
    private int idservicio;
    private String Descripcion;
    private double precio;

    public ClsServicio(int idservicio, String Descripcion, double precio) {
        this.idservicio = idservicio;
        this.Descripcion = Descripcion;
        this.precio = precio;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return getIdservicio()+" "+getDescripcion()+" "+getPrecio();
    }
    
    
    
    
}
