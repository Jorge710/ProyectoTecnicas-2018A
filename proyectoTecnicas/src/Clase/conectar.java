package Clase;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;


public class conectar {
Connection conect = null;
   public Connection conexion()
    {
      try {
             
           Class.forName("com.mysql.jdbc.Driver");
           conect = DriverManager.getConnection("jdbc:mysql://localhost/tallerMecanico","root","");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
        return conect;
     
}}
