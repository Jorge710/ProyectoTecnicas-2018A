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
    private String fecha;
    private int empresa;
    private String NomEmpre;
    private int proveedor;
    private String NomProv;
    private String ApelProv;

    public ClsCuentas_x_pagar(int codCuenta, double importe, int noFactura, String fecha, int empresa, String NomEmpre, int proveedor, String NomProv, String ApelProv) {
        this.codCuenta = codCuenta;
        this.importe = importe;
        this.noFactura = noFactura;
        this.fecha = fecha;
        this.empresa = empresa;
        this.NomEmpre = NomEmpre;
        this.proveedor = proveedor;
        this.NomProv = NomProv;
        this.ApelProv = ApelProv;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public String getNomEmpre() {
        return NomEmpre;
    }

    public void setNomEmpre(String NomEmpre) {
        this.NomEmpre = NomEmpre;
    }

    public String getNomProv() {
        return NomProv;
    }

    public void setNomProv(String NomProv) {
        this.NomProv = NomProv;
    }

    public String getApelProv() {
        return ApelProv;
    }

    public void setApelProv(String ApelProv) {
        this.ApelProv = ApelProv;
    }



}
