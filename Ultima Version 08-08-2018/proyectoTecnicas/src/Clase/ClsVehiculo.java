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
public class ClsVehiculo {
    private String placa;
    private String tipo;
    private String modelo;
    private String color;
    private int ruccli;

    public ClsVehiculo(String placa, String tipo, String modelo, String color, int ruccli) {
        this.placa = placa;
        this.tipo = tipo;
        this.modelo = modelo;
        this.color = color;
        this.ruccli = ruccli;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRuccli() {
        return ruccli;
    }

    public void setRuccli(int ruccli) {
        this.ruccli = ruccli;
    }
    
    

}
