/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package From;

import Clase.ClsEmpleado;
import Clase.conectar;
import Clase.conexion_consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class FrmListaEmpleado extends javax.swing.JFrame {

    /**
     * Creates new form FrmListaEmpleado
     */
    public FrmListaEmpleado() {
        initComponents();
        this.setLocationRelativeTo(null);// Iniciamos la pantalla al centro
        this.setTitle("Lista Empleado");
        txtRucEmpre.setEditable(false);
        mostrarTablaEmpleado();
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
                clsEmpl = new ClsEmpleado(rs.getInt("RucEmpl"), rs.getString("NomEmpl"), rs.getString("ApelEmpl"), rs.getString("DirEmpl"), rs.getInt("TlfEmpl"), rs.getString("EmailEmpl"), rs.getInt("Activo"), rs.getInt("RucEmpre"));
                usersList.add(clsEmpl);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void mostrarTablaEmpleado() {
        ArrayList<ClsEmpleado> list = getUserList();
        DefaultTableModel model = (DefaultTableModel) tblEmpleado.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getRucEmpl();
            row[1] = list.get(i).getNomEmpl();
            row[2] = list.get(i).getApelEmpl();
            row[3] = list.get(i).getDirEmpl();
            row[4] = list.get(i).getTlfEmpl();
            row[5] = list.get(i).getEmailEmpl();
            row[6] = list.get(i).getActivo();
            row[7] = list.get(i).getRucEmpre();

            model.addRow(row);

        }
    }

    public void executeSqlQuery(String query, String message) {
        Statement st;
        try {
            st = cn.createStatement();
            if (st.executeUpdate(query) == 1) {
                /*refrescar la tabla*/
                DefaultTableModel model = (DefaultTableModel) tblEmpleado.getModel();
                model.setRowCount(0);
                mostrarTablaEmpleado();
                ////
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
        txtRucEmpre.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();
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
        btnCancelar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblEmpresa = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtNombre_empresa = new javax.swing.JTextField();
        btnModificarProv = new javax.swing.JButton();
        btnRegresar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ruc", "Nombre", "Apellido", "Direccion", "Telefono", "Correo", "Activo", "Empresa"
            }
        ));
        tblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleado);

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

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
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

        jLabel4.setText("RUC EMPRESA");

        txtNombre_empresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombre_empresaKeyReleased(evt);
            }
        });

        btnModificarProv.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        btnModificarProv.setText("MODIFICAR");
        btnModificarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(txtActivoEmpl, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(36, 36, 36)
                                        .addComponent(txtNombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtRucEmpre, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 10, Short.MAX_VALUE))
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
                            .addComponent(jLabel9)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel13))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addComponent(btnModificarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addComponent(btnCancelar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarProv)
                    .addComponent(btnCancelar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnRegresar1.setText("REGRESAR");
        btnRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoMouseClicked
        int i = tblEmpleado.getSelectedRow();
        TableModel model = tblEmpleado.getModel();
        txtCedulaEmpl.setText(model.getValueAt(i, 0).toString());
        txtNomEmpl.setText(model.getValueAt(i, 1).toString());
        txtApelEmpl.setText(model.getValueAt(i, 2).toString());
        txtDirEmpl.setText(model.getValueAt(i, 3).toString());
        txtTlfEmpl.setText(model.getValueAt(i, 4).toString());
        txtEmailEmpl.setText(model.getValueAt(i, 5).toString());
        txtActivoEmpl.setText(model.getValueAt(i, 6).toString());
        txtRucEmpre.setText(model.getValueAt(i, 7).toString());
    }//GEN-LAST:event_tblEmpleadoMouseClicked

    private void btnModificarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProvActionPerformed
        String query = "UPDATE `empleado` SET `RucEmpl`='" + txtCedulaEmpl.getText() + "',`NomEmpl`='" + txtNomEmpl.getText() + "',`ApelEmpl`='" + txtApelEmpl.getText() + "',`DirEmpl`='" + txtDirEmpl.getText() + "',`TlfEmpl`='" + txtTlfEmpl.getText() + "',`EmailEmpl`='" + txtEmailEmpl.getText() + "',`Activo`='" + txtActivoEmpl.getText() + "' WHERE `RucEmpl`='" + txtCedulaEmpl.getText() + "'";

        executeSqlQuery(query, "Updated");

        limpiarCampos();

        FrmMenu mr = new FrmMenu();
        mr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnModificarProvActionPerformed

    private void txtCedulaEmplKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaEmplKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO NUMEROS", "ERROR!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCedulaEmplKeyTyped

    private void txtEmailEmplFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailEmplFocusLost
        if (isEmail(txtEmailEmpl.getText())) {

        } else {
            JOptionPane.showMessageDialog(null, "EMAIL INCORRECTO.", "ERROR!!", JOptionPane.ERROR_MESSAGE);
            txtEmailEmpl.setText("");
        }
    }//GEN-LAST:event_txtEmailEmplFocusLost

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

    private void btnRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar1ActionPerformed
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresar1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmListaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmListaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmListaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmListaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmListaEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificarProv;
    private javax.swing.JButton btnRegresar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tblEmpleado;
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