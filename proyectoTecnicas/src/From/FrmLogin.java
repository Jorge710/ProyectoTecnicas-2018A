package From;

import Clase.conectar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Administrador
 */
public class FrmLogin extends javax.swing.JFrame {

    private Timer tiempo;//variable global Timer
    int cont;//contador
    public final static int TWO_SECOND = 10;//duracion de 2 seg. de las variable

    public FrmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);// Iniciamos la pantalla al centro

    }

    public void esconderProgresBar() {
        this.setVisible(false);
    }

    public void activarProgresBar() {
        tiempo.start();/*hacemos referencia al netodo start() ya esxistente en esta clase
                        *inicializa el tiempo
         */
    }


    void acceder(String usuario, String pass) {
        String cap = "";
        String cap1 = "";
        String sql = "SELECT * FROM usuario WHERE email='" + usuario + "' && passadmin='" + pass + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cap = rs.getString("tipousuario");
                cap1 = rs.getString("nombre");
            }
            if (cap.equals("Administrador")) {
                barPro.setVisible(true);
                cont = -1;
                barPro.setValue(0);
                barPro.setStringPainted(true);
                tiempo = new Timer(TWO_SECOND, new TimerListener());
                activarProgresBar();
            }
            if (cap.equals("Invitado")) {
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bienvenido " + cap1);

            }
            if ((!cap.equals("Administrador")) && (!cap.equals("Invitado"))) {
                JOptionPane.showMessageDialog(this, "No existe sus datos");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        barPro = new javax.swing.JProgressBar();
        btnsalir = new javax.swing.JButton();
        txtusuario = new javax.swing.JTextField();
        txtcontra = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnaceptar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acceso al Sistema");
        getContentPane().setLayout(null);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/login.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 20, 256, 260);
        getContentPane().add(barPro);
        barPro.setBounds(27, 452, 330, 20);

        btnsalir.setBackground(new java.awt.Color(0, 0, 51));
        btnsalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(255, 255, 255));
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnsalir);
        btnsalir.setBounds(260, 30, 100, 25);

        txtusuario.setBackground(new java.awt.Color(0, 0, 51));
        txtusuario.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        txtusuario.setForeground(new java.awt.Color(255, 255, 255));
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtusuario);
        txtusuario.setBounds(140, 290, 220, 40);

        txtcontra.setBackground(new java.awt.Color(0, 0, 51));
        txtcontra.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtcontra);
        txtcontra.setBounds(140, 350, 220, 40);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 300, 70, 22);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 360, 85, 17);

        btnaceptar.setBackground(new java.awt.Color(0, 0, 51));
        btnaceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnaceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnaceptar.setText("INGRESAR");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnaceptar);
        btnaceptar.setBounds(140, 400, 220, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoLogin.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, -1, 370, 490);

        setSize(new java.awt.Dimension(387, 528));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        // TODO add your handling code here:
        String usu = txtusuario.getText();
        String pas = new String(txtcontra.getPassword());
        acceder(usu, pas);
    }//GEN-LAST:event_btnaceptarActionPerformed

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
        txtusuario.requestFocus();
    }//GEN-LAST:event_txtusuarioActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnsalirActionPerformed

        /**
     * Clase para el JbarProgres
     */
    class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            cont++;
            barPro.setValue(cont);
            if (cont == 50) {

            }
            if (cont == 100) {
                tiempo.stop();
                esconderProgresBar();
                FrmMenu adm = new FrmMenu();
                adm.setVisible(true);
                dispose();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barPro;
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtcontra;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
    conectar cc = new conectar();
    Connection cn = cc.conexion();
}
