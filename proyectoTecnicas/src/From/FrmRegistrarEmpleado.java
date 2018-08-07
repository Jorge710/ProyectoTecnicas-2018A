/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package From;

import Clase.*;
import Clase.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class FrmRegistrarEmpleado extends javax.swing.JFrame {

    /**
     * Creates new form FrmRegistrarEmpleado
     */
    public FrmRegistrarEmpleado() {
        initComponents();
        this.setLocationRelativeTo(null);// Iniciamos la pantalla al centro
        this.setTitle("Registrar Empleado");
        txtRucEmpre.setEditable(false);
        conexion_consulta.conectar();
    }

    /*Empleado*/
    public ArrayList<ClsEmpleado> getUserList() {
        ArrayList<ClsEmpleado> usersList = new ArrayList<ClsEmpleado>();
        String query = "SELECT * FROM `empleado`";
        Statement st;
        ResultSet rs;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(query);
            ClsEmpleado clsEmpl;
            while (rs.next()) {
                clsEmpl = new ClsEmpleado(rs.getInt("RucEmpl"),
                        rs.getString("NomEmpl"),
                        rs.getString("ApelEmpl"),
                        rs.getString("DirEmpl"),
                        rs.getInt("TlfEmpl"),
                        rs.getString("EmailEmpl"),
                        rs.getInt("Activo"),
                        rs.getInt("RucEmpre"));
                usersList.add(clsEmpl);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void executeSqlQuery(String query, String message) {
        Statement st;
        try {
            st = cn.createStatement();
            if (st.executeUpdate(query) == 1) {

                JOptionPane.showMessageDialog(null, "Data" + message + "Succefully");
            } else {
                JOptionPane.showMessageDialog(null, "Data Not" + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        /*validar correo*/
    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);

        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }
    
    /*limpiar campos*/
    public void limpiarCampos() {

        //datos del proveedor
        txtCedulaEmpl.setText(null);
        txtNomEmpl.setText(null);
        txtApelEmpl.setText(null);
        txtDirEmpl.setText(null);
        txtTlfEmpl.setText(null);
        txtEmailEmpl.setText(null);
        txtActivoEmpl.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCedulaEmpl = new javax.swing.JTextField();
        txtNomEmpl = new javax.swing.JTextField();
        txtApelEmpl = new javax.swing.JTextField();
        txtDirEmpl = new javax.swing.JTextField();
        txtEmailEmpl = new javax.swing.JTextField();
        txtTlfEmpl = new javax.swing.JTextField();
        txtActivoEmpl = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtRucEmpre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombre_empresa = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblEmpresa = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnGuardarDatosProv = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnCancelar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Cedula Empleado: ");

        jLabel8.setText("Nombre Empleado: ");

        jLabel9.setText("Apellido Empleado:");

        jLabel10.setText("Dir Empleado:");

        jLabel11.setText("Email Empleado: ");

        jLabel12.setText("Telf Empleado:");

        txtCedulaEmpl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaEmplKeyTyped(evt);
            }
        });

        txtEmailEmpl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailEmplFocusLost(evt);
            }
        });

        txtTlfEmpl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTlfEmplKeyTyped(evt);
            }
        });

        txtActivoEmpl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtActivoEmplKeyTyped(evt);
            }
        });

        jLabel13.setText("Activo:");

        jLabel4.setText("BUSCAR EMPRESA (Por Nombre)");

        txtNombre_empresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombre_empresaKeyReleased(evt);
            }
        });

        tblEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpresaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblEmpresa);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(32, 32, 32)
                                    .addComponent(txtCedulaEmpl))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtApelEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNomEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDirEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmailEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTlfEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(txtActivoEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)
                                .addComponent(jLabel13))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(txtRucEmpre, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCedulaEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNomEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtApelEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDirEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEmailEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTlfEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtActivoEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRucEmpre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("AR JULIAN", 1, 24)); // NOI18N
        jLabel14.setText("REGISTRAR EMPLEADO");

        jToolBar1.setRollover(true);

        btnGuardarDatosProv.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        btnGuardarDatosProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save.png"))); // NOI18N
        btnGuardarDatosProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDatosProvActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardarDatosProv);
        jToolBar1.add(jSeparator1);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCancelar);
        jToolBar1.add(jSeparator2);

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh.png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRegresar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(153, 153, 153))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarDatosProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDatosProvActionPerformed
        try {

            //------- generacion de numeros randomicos -------//
            Random num = new Random();
            int N = num.nextInt(1000);

            String nom = txtNomEmpl.getText();
            String apell = txtApelEmpl.getText();
            String ci = txtCedulaEmpl.getText();

            if (nom.equals("") || apell.equals("") || ci.equals("")) {
                JOptionPane.showMessageDialog(null, "INGRESE DATOS.", "ERROR!!", JOptionPane.ERROR_MESSAGE);

            } else if (ci.length() == 10) {
                int coef = 0, mult = 0, sum = 0, verificador = 0;
                for (int i = 0; i < ci.length() - 1; i++) {
                    if (i % 2 == 0) {
                        coef = 2;
                    } else {
                        coef = 1;
                    }
                    mult = coef * Character.getNumericValue(ci.charAt(i));

                    if (mult > 9) {
                        mult = mult - 9;

                    }

                    sum += mult;
                }

                verificador = 10 - (sum % 10);

                if (verificador == Character.getNumericValue(ci.charAt(ci.length() - 1))) {
                    JOptionPane.showMessageDialog(null, "GUARDANDO USUARIO");
                    System.out.println("registra");

                    String query = "INSERT INTO `empleado`(`RucEmpl`, `NomEmpl`, `ApelEmpl`, `DirEmpl`, `TlfEmpl`, `EmailEmpl`, `Activo`, `RucEmpre`)VALUES ('" + txtCedulaEmpl.getText() + "','" + txtNomEmpl.getText() + "','" + txtApelEmpl.getText() + "','" + txtDirEmpl.getText() + "','" + txtTlfEmpl.getText() + "','" + txtEmailEmpl.getText() + "','" + txtActivoEmpl.getText() + "','" + txtRucEmpre.getText() + "')";

                    executeSqlQuery(query, "Inserted");

                    limpiarCampos();

                    FrmMenu mr = new FrmMenu();
                    mr.setVisible(true);
                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, " # CEDULA NO VALIDO veri", "ERROR!!", JOptionPane.WARNING_MESSAGE);//num Verificador
                }

            } else {
                JOptionPane.showMessageDialog(null, " # CEDULA NO VALIDO.", "ERROR!!", JOptionPane.WARNING_MESSAGE);// longitud cedula
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, nfe);
            //nfe.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("No ingresaste un numero." + e.getMessage());
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_btnGuardarDatosProvActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpresaMouseClicked
        int i = tblEmpresa.getSelectedRow();
        TableModel model = tblEmpresa.getModel();
        txtRucEmpre.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_tblEmpresaMouseClicked

    private void txtNombre_empresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre_empresaKeyReleased
        String Nombre = txtNombre_empresa.getText();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Cédula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dirección");
        modelo.addColumn("Telefono");
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        datos = conexion_consulta.buscar_empresa_tabla(Nombre);
        for (int i = 0; i < datos.size(); i++) {
            modelo.addRow(datos.get(i));
        }
        tblEmpresa.setModel(modelo);
    }//GEN-LAST:event_txtNombre_empresaKeyReleased

    private void txtCedulaEmplKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaEmplKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO NUMEROS", "ERROR!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCedulaEmplKeyTyped

    private void txtTlfEmplKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTlfEmplKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO NUMEROS", "ERROR!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtTlfEmplKeyTyped

    private void txtActivoEmplKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtActivoEmplKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO NUMEROS", "ERROR!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtActivoEmplKeyTyped

    private void txtEmailEmplFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailEmplFocusLost
        if (isEmail(txtEmailEmpl.getText())) {

        } else {
            JOptionPane.showMessageDialog(null, "EMAIL INCORRECTO.", "ERROR!!", JOptionPane.ERROR_MESSAGE);
            txtEmailEmpl.setText("");
        }
    }//GEN-LAST:event_txtEmailEmplFocusLost

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
            java.util.logging.Logger.getLogger(FrmRegistrarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegistrarEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarDatosProv;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblEmpresa;
    private javax.swing.JTextField txtActivoEmpl;
    private javax.swing.JTextField txtApelEmpl;
    private javax.swing.JTextField txtCedulaEmpl;
    private javax.swing.JTextField txtDirEmpl;
    private javax.swing.JTextField txtEmailEmpl;
    private javax.swing.JTextField txtNomEmpl;
    private javax.swing.JTextField txtNombre_empresa;
    private javax.swing.JTextField txtRucEmpre;
    private javax.swing.JTextField txtTlfEmpl;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();

}
