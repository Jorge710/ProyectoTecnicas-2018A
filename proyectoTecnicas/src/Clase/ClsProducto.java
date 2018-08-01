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
public class ClsProducto {
    
    private int IdProd;
    private String Descripcion;
    private int Cantidad;
    private double CostoUnit;
    private double PrecioUnit;
    private int RucProv;
    private int idProdInterno;
    private double totalMaterial;
    
     public ClsProducto(int IdProd, String Descripcion, int Cantidad, double PrecioUnit, int idProdInterno, double totalMaterial) {
        this.IdProd = IdProd;
        this.Descripcion = Descripcion;
        this.Cantidad = Cantidad;
        this.PrecioUnit = PrecioUnit;
        this.idProdInterno = idProdInterno;
        this.totalMaterial = totalMaterial;
    }
    

    public ClsProducto(int IdProd, String Descripcion, double PrecioUnit) {
        this.IdProd = IdProd;
        this.Descripcion = Descripcion;
        this.PrecioUnit = PrecioUnit;
    }

    public ClsProducto(int IdProd, String Descripcion, double PrecioUnit, int idProdInterno) {
        this.IdProd = IdProd;
        this.Descripcion = Descripcion;
        this.PrecioUnit = PrecioUnit;
        this.idProdInterno = idProdInterno;
    }

    public ClsProducto(int IdProd, String Descripcion, int Cantidad, double CostoUnit, double PrecioUnit, int RucProv) {
        this.IdProd = IdProd;
        this.Descripcion = Descripcion;
        this.Cantidad = Cantidad;
        this.CostoUnit = CostoUnit;
        this.PrecioUnit = PrecioUnit;
        this.RucProv = RucProv;
    }

   
    

    public double getTotalMaterial() {
        return totalMaterial;
    }

    public void setTotalMaterial(double totalMaterial) {
        this.totalMaterial = totalMaterial;
    }
    
    

    public int getIdProdInterno() {
        return idProdInterno;
    }

    public void setIdProdInterno(int idProdInterno) {
        this.idProdInterno = idProdInterno;
    }
    
    

    public int getIdProd() {
        return IdProd;
    }

    public void setIdProd(int IdProd) {
        this.IdProd = IdProd;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getCostoUnit() {
        return CostoUnit;
    }

    public void setCostoUnit(double CostoUnit) {
        this.CostoUnit = CostoUnit;
    }

    public double getPrecioUnit() {
        return PrecioUnit;
    }

    public void setPrecioUnit(double PrecioUnit) {
        this.PrecioUnit = PrecioUnit;
    }

    public int getRucProv() {
        return RucProv;
    }

    public void setRucProv(int RucProv) {
        this.RucProv = RucProv;
    }

    @Override
    public String toString() {
        return IdProd + " " + Descripcion +" "+ PrecioUnit  + " "+idProdInterno+" "+totalMaterial;
    }
    
    
    
    
    
}
