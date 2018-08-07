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
public class ClsEmpleado {

    private int RucEmpl;
    private String NomEmpl;
    private String ApelEmpl;
    private String DirEmpl;
    private int TlfEmpl;
    private String EmailEmpl;
    private int Activo;
    private int RucEmpre;
    private String NomEmpre;

    public ClsEmpleado(int RucEmpl, String NomEmpl, String ApelEmpl, String DirEmpl, int TlfEmpl, String EmailEmpl, int Activo, int RucEmpre) {
        this.RucEmpl = RucEmpl;
        this.NomEmpl = NomEmpl;
        this.ApelEmpl = ApelEmpl;
        this.DirEmpl = DirEmpl;
        this.TlfEmpl = TlfEmpl;
        this.EmailEmpl = EmailEmpl;
        this.Activo = Activo;
        this.RucEmpre = RucEmpre;
    }

    
    public ClsEmpleado(int RucEmpl, String NomEmpl, String ApelEmpl, String DirEmpl, int TlfEmpl, String EmailEmpl, int Activo, int RucEmpre, String NomEmpre) {
        this.RucEmpl = RucEmpl;
        this.NomEmpl = NomEmpl;
        this.ApelEmpl = ApelEmpl;
        this.DirEmpl = DirEmpl;
        this.TlfEmpl = TlfEmpl;
        this.EmailEmpl = EmailEmpl;
        this.Activo = Activo;
        this.RucEmpre = RucEmpre;
        this.NomEmpre = NomEmpre;
    }



    
    public int getRucEmpl() {
        return RucEmpl;
    }

    public void setRucEmpl(int RucEmpl) {
        this.RucEmpl = RucEmpl;
    }

    public String getNomEmpl() {
        return NomEmpl;
    }

    public void setNomEmpl(String NomEmpl) {
        this.NomEmpl = NomEmpl;
    }

    public String getApelEmpl() {
        return ApelEmpl;
    }

    public void setApelEmpl(String ApelEmpl) {
        this.ApelEmpl = ApelEmpl;
    }

    public String getDirEmpl() {
        return DirEmpl;
    }

    public void setDirEmpl(String DirEmpl) {
        this.DirEmpl = DirEmpl;
    }

    public int getTlfEmpl() {
        return TlfEmpl;
    }

    public void setTlfEmpl(int TlfEmpl) {
        this.TlfEmpl = TlfEmpl;
    }

    public String getEmailEmpl() {
        return EmailEmpl;
    }

    public void setEmailEmpl(String EmailEmpl) {
        this.EmailEmpl = EmailEmpl;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }

    public int getRucEmpre() {
        return RucEmpre;
    }

    public void setRucEmpre(int RucEmpre) {
        this.RucEmpre = RucEmpre;
    }

    public String getNomEmpre() {
        return NomEmpre;
    }

    public void setNomEmpre(String NomEmpre) {
        this.NomEmpre = NomEmpre;
    }
    
    
    
    
}
