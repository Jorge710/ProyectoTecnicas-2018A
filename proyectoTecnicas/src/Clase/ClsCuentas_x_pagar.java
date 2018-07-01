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
public class ClsCuentas_x_pagar {
    private int codCuenta;
    private double importe;
    private int noFactura;

    public ClsCuentas_x_pagar(int codCuenta, double importe, int noFactura) {
        this.codCuenta = codCuenta;
        this.importe = importe;
        this.noFactura = noFactura;
    }

    public int getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(int codCuenta) {
        this.codCuenta = codCuenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(int noFactura) {
        this.noFactura = noFactura;
    }
    
    
    
}
