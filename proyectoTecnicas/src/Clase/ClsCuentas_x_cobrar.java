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
public class ClsCuentas_x_cobrar {
    
    private int CodCuenta;
    private int Importe;
    private int NoFact;
    private int RucCli;
    private String NomCli;
    private String ApelCli;
    private String fecha_registro;

    public ClsCuentas_x_cobrar(int CodCuenta, int Importe, int NoFact, int RucCli, String NomCli, String ApelCli, String fecha_registro) {
        this.CodCuenta = CodCuenta;
        this.Importe = Importe;
        this.NoFact = NoFact;
        this.RucCli = RucCli;
        this.NomCli = NomCli;
        this.ApelCli = ApelCli;
        this.fecha_registro = fecha_registro;
    }


    public int getCodCuenta() {
        return CodCuenta;
    }

    public void setCodCuenta(int CodCuenta) {
        this.CodCuenta = CodCuenta;
    }

    public int getImporte() {
        return Importe;
    }

    public void setImporte(int Importe) {
        this.Importe = Importe;
    }

    public int getNoFact() {
        return NoFact;
    }

    public void setNoFact(int NoFact) {
        this.NoFact = NoFact;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getRucCli() {
        return RucCli;
    }

    public void setRucCli(int RucCli) {
        this.RucCli = RucCli;
    }

    public String getNomCli() {
        return NomCli;
    }

    public void setNomCli(String NomCli) {
        this.NomCli = NomCli;
    }

    public String getApelCli() {
        return ApelCli;
    }

    public void setApelCli(String ApelCli) {
        this.ApelCli = ApelCli;
    }
    
    
    
}
