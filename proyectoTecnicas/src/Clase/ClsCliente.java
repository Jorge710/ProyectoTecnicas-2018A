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
public class ClsCliente {

    private int RucCli;
    private String NomCli;
    private String ApelCli;
    private String DirCli;
    private int TlfCli;
    private String EmailCli;
    private int Activo;

    public ClsCliente(int RucCli, String NomCli, String ApelCli, String DirCli, int TlfCli, String EmailCli, int Activo) {
        this.RucCli = RucCli;
        this.NomCli = NomCli;
        this.ApelCli = ApelCli;
        this.DirCli = DirCli;
        this.TlfCli = TlfCli;
        this.EmailCli = EmailCli;
        this.Activo = Activo;
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

    public String getDirCli() {
        return DirCli;
    }

    public void setDirCli(String DirCli) {
        this.DirCli = DirCli;
    }

    public int getTlfCli() {
        return TlfCli;
    }

    public void setTlfCli(int TlfCli) {
        this.TlfCli = TlfCli;
    }

    public String getEmailCli() {
        return EmailCli;
    }

    public void setEmailCli(String EmailCli) {
        this.EmailCli = EmailCli;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }

    
}
