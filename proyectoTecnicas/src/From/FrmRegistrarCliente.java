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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class FrmRegistrarCliente extends javax.swing.JFrame {

    /**
     * Creates new form FrmRegistrarCliente
     */
    public FrmRegistrarCliente() {
        initComponents();
        this.setLocationRelativeTo(null);// Iniciamos la pantalla al centro
        this.setTitle("Registrar Cliente");
        mostrarTablaCliente();
    }

    /*Cliente*/
    public ArrayList<ClsCliente> getUserList() {
        ArrayList<ClsCliente> usersList = new ArrayList<ClsCliente>();
        String query = "SELECT * FROM `cliente`";
        Statement st;
        ResultSet rs;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(query);
            ClsCliente clsCli;
            while (rs.next()) {
                clsCli = new ClsCliente(rs.getInt("RucCli"), rs.getString("NomCli"), rs.getString("ApelCli"), rs.getString("DirCli"), rs.getInt("TlfCli"), rs.getString("EmailCli"), rs.getInt("Activo"));
                usersList.add(clsCli);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void mostrarTablaCliente() {
        ArrayList<ClsCliente> list = getUserList();
        DefaultTableModel model = (DefaultTableModel) tblCliente.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getRucCli();
            row[1] = list.get(i).getNomCli();
            row[2] = list.get(i).getApelCli();
            row[3] = list.get(i).getDirCli();
            row[4] = list.get(i).getTlfCli();
            row[5] = list.get(i).getEmailCli();
            row[6] = list.get(i).getActivo();

            model.addRow(row);

        }
    }

    public void executeSqlQuery(String query, String message) {
        Statement st;
        try {
            st = cn.createStatement();
            if (st.executeUpdate(query) == 1) {
                /*refrescar la tabla*/
                DefaultTableModel model = (DefaultTableModel) tblCliente.getModel();
                model.setRowCount(0);
                mostrarTablaCliente();
                ////
                JOptionPane.showMessageDialog(null, "Data" + message + "Succefully");
            } else {
                JOptionPane.showMessageDialog(null, "Data Not" + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*limpiar campos*/
    public void limpiarCampos() {

        //datos del proveedor
        txtCedulaCli.setText(null);
        txtNomCli.setText(null);
        txtApelCli.setText(null);
        txtDirCli.setText(null);
        txtTlfCli.setText(null);
        txtEmailCli.setText(null);
        txtActivoCli.setText(null);
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
        tblCliente = new javax.swing.JTable();
        btnRegresar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtActivoCli = new javax.swing.JTextField();
        txtDirCli = new javax.swing.JTextField();
        txtEmailCli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTlfCli = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCedulaCli = new javax.swing.JTextField();
        txtNomCli = new javax.swing.JTextField();
        txtApelCli = new javax.swing.JTextField();
        btnGuardarDatos = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Dirección", "Correo", "Telefono", "Activo"
            }
        ));
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        btnRegresar1.setText("REGRESAR");
        btnRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Activo");

        jLabel1.setText("Cedula Cliente: ");

        jLabel2.setText("Nombre Cliente: ");

        jLabel3.setText("Apellido Cliente: ");

        jLabel4.setText("Dir Cliente:");

        jLabel5.setText("Email Cliente: ");

        jLabel6.setText("Telf Cliente");

        btnGuardarDatos.setFont(new java.awt.Font("AR JULIAN", 0, 12)); // NOI18N
        btnGuardarDatos.setText("GUARDAR DATOS");
        btnGuardarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDatosActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("AR JULIAN", 0, 12)); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 75, Short.MAX_VALUE)
                        .addComponent(btnGuardarDatos)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCedulaCli, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(txtNomCli)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtApelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6)))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtActivoCli)
                                    .addComponent(txtEmailCli)
                                    .addComponent(txtDirCli)
                                    .addComponent(txtTlfCli))))
                        .addGap(185, 185, 185))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCedulaCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApelCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDirCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmailCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTlfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtActivoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarDatos)
                    .addComponent(btnModificar)
                    .addComponent(btnCancelar))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegresar1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegresar1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String query = "UPDATE `cliente` SET `RucCli`='" + txtCedulaCli.getText() + "',`NomCli`='" + txtNomCli.getText() + "',`ApelCli`='" + txtApelCli.getText() + "',`DirCli`='" + txtDirCli.getText() + "',`TlfCli`='" + txtTlfCli.getText() + "',`EmailCli`='" + txtEmailCli.getText() + "',`Activo`='" + txtActivoCli.getText() + "' WHERE `RucCli`='" + txtCedulaCli.getText() + "'";

        executeSqlQuery(query, "Updated");
        limpiarCampos();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDatosActionPerformed
        String query = "INSERT INTO `cliente`(`RucCli`, `NomCli`, `ApelCli`, `DirCli`, `TlfCli`, `EmailCli`, `Activo`) VALUES ('" + txtCedulaCli.getText() + "','" + txtNomCli.getText() + "','" + txtApelCli.getText() + "','" + txtDirCli.getText() + "','" + txtTlfCli.getText() + "','" + txtEmailCli.getText() + "','" + txtActivoCli.getText() + "')";

        executeSqlQuery(query, "Inserted");
        limpiarCampos();
    }//GEN-LAST:event_btnGuardarDatosActionPerformed

    private void btnRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar1ActionPerformed
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresar1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        int i = tblCliente.getSelectedRow();
        TableModel model = tblCliente.getModel();
        txtCedulaCli.setText(model.getValueAt(i, 0).toString());
        txtNomCli.setText(model.getValueAt(i, 1).toString());
        txtApelCli.setText(model.getValueAt(i, 2).toString());
        txtDirCli.setText(model.getValueAt(i, 3).toString());
        txtTlfCli.setText(model.getValueAt(i, 4).toString());
        txtEmailCli.setText(model.getValueAt(i, 5).toString());
        txtActivoCli.setText(model.getValueAt(i, 6).toString());
    }//GEN-LAST:event_tblClienteMouseClicked

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
            java.util.logging.Logger.getLogger(FrmRegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegistrarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarDatos;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegresar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtActivoCli;
    private javax.swing.JTextField txtApelCli;
    private javax.swing.JTextField txtCedulaCli;
    private javax.swing.JTextField txtDirCli;
    private javax.swing.JTextField txtEmailCli;
    private javax.swing.JTextField txtNomCli;
    private javax.swing.JTextField txtTlfCli;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();

}
