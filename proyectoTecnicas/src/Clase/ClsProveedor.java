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
public class ClsProveedor {
    
    private int RucProv;
    private String NomProv;
    private String ApelProv;
    private String DirProv;
    private int TlfProv;
    private String EmailProv;
    private int Activo;

    public ClsProveedor(int RucProv, String NomProv, String ApelProv, String DirProv, int TlfProv, String EmailProv, int Activo) {
        this.RucProv = RucProv;
        this.NomProv = NomProv;
        this.ApelProv = ApelProv;
        this.DirProv = DirProv;
        this.TlfProv = TlfProv;
        this.EmailProv = EmailProv;
        this.Activo = Activo;
    }

    public int getRucProv() {
        return RucProv;
    }

    public void setRucProv(int RucProv) {
        this.RucProv = RucProv;
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

    public String getDirProv() {
        return DirProv;
    }

    public void setDirProv(String DirProv) {
        this.DirProv = DirProv;
    }

    public int getTlfProv() {
        return TlfProv;
    }

    public void setTlfProv(int TlfProv) {
        this.TlfProv = TlfProv;
    }

    public String getEmailProv() {
        return EmailProv;
    }

    public void setEmailProv(String EmailProv) {
        this.EmailProv = EmailProv;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }

    
    
    
}
