/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package From;

import Clase.conectar;
import Clase.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class FrmMenuRegistrar extends javax.swing.JFrame {

    /**
     * Creates new form FrmMenuRegistrar
     */
    public FrmMenuRegistrar() {
        initComponents();
        this.setLocationRelativeTo(null);// Iniciamos la pantalla al centro
        mostrarProveedoresTabla();
        mostrarProductosTabla();
    }

    /*Proveedor*/
    public ArrayList<ClsProveedor> getUserList() {
        ArrayList<ClsProveedor> usersList = new ArrayList<ClsProveedor>();
        String query = "SELECT * FROM `proveedor`";
        Statement st;
        ResultSet rs;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(query);
            ClsProveedor clsprov;
            while (rs.next()) {
                clsprov = new ClsProveedor(rs.getInt("RucProv"), rs.getString("NomProv"), rs.getString("ApelProv"), rs.getString("DirProv"), rs.getInt("TlfProv"), rs.getString("EmailProv"), rs.getInt("Activo"));
                usersList.add(clsprov);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    /*Producto*/
    public ArrayList<ClsProducto> getProductoList() {
        ArrayList<ClsProducto> prodList = new ArrayList<ClsProducto>();
        String query = "SELECT * FROM `producto`";
        Statement st;
        ResultSet rs;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(query);
            ClsProducto clsprod;
            while (rs.next()) {
                clsprod = new ClsProducto(rs.getInt("IdProd"), rs.getString("Descripcion"), rs.getInt("Cantidad"), rs.getDouble("CostoUnit"), rs.getDouble("PrecioUnit"), rs.getInt("RucProv"));
                prodList.add(clsprod);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodList;
    }

    public void mostrarProveedoresTabla() {
        ArrayList<ClsProveedor> list = getUserList();
        DefaultTableModel model = (DefaultTableModel) tablaProveedor.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getRucProv();
            row[1] = list.get(i).getNomProv();
            row[2] = list.get(i).getApelProv();
            row[3] = list.get(i).getDirProv();
            row[4] = list.get(i).getTlfProv();
            row[5] = list.get(i).getEmailProv();
            row[6] = list.get(i).getActivo();

            model.addRow(row);

        }
    }

    public void mostrarProductosTabla() {
        ArrayList<ClsProducto> list = getProductoList();
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdProd();
            row[1] = list.get(i).getDescripcion();
            row[2] = list.get(i).getCantidad();
            row[3] = list.get(i).getCostoUnit();
            row[4] = list.get(i).getPrecioUnit();
            row[5] = list.get(i).getRucProv();

            model.addRow(row);

        }
    }

    public void executeSqlQuery(String query, String message) {
        Statement st;
        try {
            st = cn.createStatement();
            if (st.executeUpdate(query) == 1) {
                /*refrescar la tabla*/
                DefaultTableModel model = (DefaultTableModel) tablaProveedor.getModel();
                model.setRowCount(0);
                mostrarProveedoresTabla();
                ////
                JOptionPane.showMessageDialog(null, "Data" + message + "Succefully");
            } else {
                JOptionPane.showMessageDialog(null, "Data Not" + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeSqlQueryProducto(String query, String message) {
        Statement st;
        try {
            st = cn.createStatement();
            if (st.executeUpdate(query) == 1) {
                /*refrescar la tabla*/
                DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();
                model.setRowCount(0);
                mostrarProductosTabla();
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
    public void limpiarCampos(){
       
       //datos del proveedor
       txtCedulaProv.setText(null);
       txtNomProv.setText(null);
       txtApelProv.setText(null);
       txtDirProv.setText(null);
       txtTlfProv.setText(null);
       txtEmailProv.setText(null);
       txtActivoProv.setText(null);
       
       //datos del producto
       txtIdProd.setText(null);
       txtDescripcionProd.setText(null);
       txtCantidadPod.setText(null);
       txtCostoUnitProd.setText(null);
       txtPrecioUnitProd.setText(null);
       txtRucProv_prod.setText(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCedulaProv = new javax.swing.JTextField();
        txtNomProv = new javax.swing.JTextField();
        txtApelProv = new javax.swing.JTextField();
        txtDirProv = new javax.swing.JTextField();
        txtEmailProv = new javax.swing.JTextField();
        txtTlfProv = new javax.swing.JTextField();
        btnModificarProv = new javax.swing.JButton();
        btnActivarProv = new javax.swing.JButton();
        btnGuardarDatosProv = new javax.swing.JButton();
        txtActivoProv = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProveedor = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnModificar_producto = new javax.swing.JButton();
        btnGuardarDatos_producto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDescripcionProd = new javax.swing.JTextField();
        txtCantidadPod = new javax.swing.JTextField();
        txtCostoUnitProd = new javax.swing.JTextField();
        txtPrecioUnitProd = new javax.swing.JTextField();
        txtRucProv_prod = new javax.swing.JTextField();
        txtIdProd = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("CLIENTE", jPanel1);

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Cedula Proveedor: ");

        jLabel8.setText("Nombre Proveedor: ");

        jLabel9.setText("Apellido Proveedor: ");

        jLabel10.setText("Dir Proveedor:");

        jLabel11.setText("Email Proveedor: ");

        jLabel12.setText("Telf Proveedor");

        btnModificarProv.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        btnModificarProv.setText("MODIFICAR");
        btnModificarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProvActionPerformed(evt);
            }
        });

        btnActivarProv.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        btnActivarProv.setText("ACTIVAR PROVEEDOR");
        btnActivarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarProvActionPerformed(evt);
            }
        });

        btnGuardarDatosProv.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        btnGuardarDatosProv.setText("GUARDAR DATOS");
        btnGuardarDatosProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDatosProvActionPerformed(evt);
            }
        });

        jLabel13.setText("Activo:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnGuardarDatosProv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActivarProv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(32, 32, 32)
                                        .addComponent(txtCedulaProv))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtApelProv, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNomProv, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDirProv, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmailProv, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTlfProv, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addGap(150, 150, 150)
                            .addComponent(txtActivoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCedulaProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNomProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtApelProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDirProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEmailProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTlfProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtActivoProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActivarProv)
                    .addComponent(btnGuardarDatosProv)
                    .addComponent(btnModificarProv))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ruc", "Nombre", "Apellido", "Direccion", "Telefono", "Correo", "Activo"
            }
        ));
        tablaProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProveedor);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PROVEEDOR", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("EMPLEADO", jPanel3);

        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnModificar_producto.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        btnModificar_producto.setText("MODIFICAR");
        btnModificar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar_productoActionPerformed(evt);
            }
        });

        btnGuardarDatos_producto.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        btnGuardarDatos_producto.setText("GUARDAR DATOS");
        btnGuardarDatos_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDatos_productoActionPerformed(evt);
            }
        });

        jLabel2.setText("Descripción:");

        jLabel3.setText("Cantidad:");

        jLabel4.setText("Costo Unit:");

        jLabel5.setText("Precio Unit:");

        jLabel6.setText("Ruc Proveedor:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadPod)
                            .addComponent(txtCostoUnitProd)
                            .addComponent(txtPrecioUnitProd)
                            .addComponent(txtRucProv_prod)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnGuardarDatos_producto)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(43, 43, 43)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdProd)
                            .addComponent(txtDescripcionProd))))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescripcionProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidadPod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCostoUnitProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPrecioUnitProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtRucProv_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarDatos_producto)
                    .addComponent(btnModificar_producto))
                .addGap(48, 48, 48))
        );

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripción", "Cantidad", "Costo Unit", "Precio Unit", "Ruc Proveedor"
            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 263, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab("PRODUCTO", jPanel4);

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarDatosProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDatosProvActionPerformed
        String query = "INSERT INTO `proveedor`(`RucProv`, `NomProv`, `ApelProv`, `DirProv`, `TlfProv`, `EmailProv`, `Activo `) VALUES ('" + txtCedulaProv.getText() + "','" + txtNomProv.getText() + "','" + txtApelProv.getText() + "','" + txtDirProv.getText() + "','" + txtTlfProv.getText() + "','" + txtEmailProv.getText() + "','" + txtActivoProv.getText() + "')";

        executeSqlQuery(query, "Inserted");

        limpiarCampos();
    }//GEN-LAST:event_btnGuardarDatosProvActionPerformed

    private void btnActivarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActivarProvActionPerformed

    private void btnModificarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProvActionPerformed
        String query = "UPDATE `proveedor` SET `RucProv`='" + txtCedulaProv.getText() + "',`NomProv`='" + txtNomProv.getText() + "',`ApelProv`='" + txtApelProv.getText() + "',`DirProv`='" + txtDirProv.getText() + "',`TlfProv`='" + txtTlfProv.getText() + "',`emailProv`='" + txtEmailProv.getText() + "',`Activo`='" + txtActivoProv.getText() + "' WHERE `RucProv`='" + txtCedulaProv.getText() + "'";

        executeSqlQuery(query, "Updated");

        limpiarCampos();
    }//GEN-LAST:event_btnModificarProvActionPerformed

    private void tablaProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedorMouseClicked
        int i = tablaProveedor.getSelectedRow();
        TableModel model = tablaProveedor.getModel();
        txtCedulaProv.setText(model.getValueAt(i, 0).toString());
        txtNomProv.setText(model.getValueAt(i, 1).toString());
        txtApelProv.setText(model.getValueAt(i, 2).toString());
        txtDirProv.setText(model.getValueAt(i, 3).toString());
        txtTlfProv.setText(model.getValueAt(i, 4).toString());
        txtEmailProv.setText(model.getValueAt(i, 5).toString());
        txtActivoProv.setText(model.getValueAt(i, 6).toString());
    }//GEN-LAST:event_tablaProveedorMouseClicked

    private void btnModificar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar_productoActionPerformed

        String query = "UPDATE `producto` SET `IdProd`='" + txtIdProd.getText() + "',`Descripcion`='" + txtDescripcionProd.getText() + "',`Cantidad`='" + txtCantidadPod.getText() + "',`CostoUnit`='" + txtCostoUnitProd.getText() + "',`PrecioUnit`='" + txtPrecioUnitProd.getText() + "',`RucProv`='" + txtRucProv_prod.getText() + "' WHERE `IdProd`='" + txtIdProd.getText() + "'";
        executeSqlQueryProducto(query, "Updated");

        limpiarCampos();
    }//GEN-LAST:event_btnModificar_productoActionPerformed

    private void btnGuardarDatos_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDatos_productoActionPerformed

        String query = "INSERT INTO `producto` (`IdProd`, `Descripcion`, `Cantidad`, `CostoUnit`, `PrecioUnit`, `RucProv`) VALUES (NULL, '" + txtDescripcionProd.getText() + "', '" + txtCantidadPod.getText() + "', '" + txtCostoUnitProd.getText() + "', '" + txtPrecioUnitProd.getText() + "', '" + txtRucProv_prod.getText() + "');";
        executeSqlQueryProducto(query, "Inserted");
        
        limpiarCampos();
    }//GEN-LAST:event_btnGuardarDatos_productoActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        int i = tblProductos.getSelectedRow();
        TableModel model = tblProductos.getModel();
        txtIdProd.setText(model.getValueAt(i, 0).toString());
        txtDescripcionProd.setText(model.getValueAt(i, 1).toString());
        txtCantidadPod.setText(model.getValueAt(i, 2).toString());
        txtCostoUnitProd.setText(model.getValueAt(i, 3).toString());
        txtPrecioUnitProd.setText(model.getValueAt(i, 4).toString());
        txtRucProv_prod.setText(model.getValueAt(i, 5).toString());
    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMenuRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenuRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenuRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenuRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenuRegistrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivarProv;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarDatosProv;
    private javax.swing.JButton btnGuardarDatos_producto;
    private javax.swing.JButton btnModificarProv;
    private javax.swing.JButton btnModificar_producto;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaProveedor;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtActivoProv;
    private javax.swing.JTextField txtApelProv;
    private javax.swing.JTextField txtCantidadPod;
    private javax.swing.JTextField txtCedulaProv;
    private javax.swing.JTextField txtCostoUnitProd;
    private javax.swing.JTextField txtDescripcionProd;
    private javax.swing.JTextField txtDirProv;
    private javax.swing.JTextField txtEmailProv;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtNomProv;
    private javax.swing.JTextField txtPrecioUnitProd;
    private javax.swing.JTextField txtRucProv_prod;
    private javax.swing.JTextField txtTlfProv;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();
}
