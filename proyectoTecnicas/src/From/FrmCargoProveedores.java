/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package From;

import Clase.*;
import Clase.conectar;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class FrmCargoProveedores extends javax.swing.JFrame {

    /**
     * Creates new form FrmCargoProveedores
     */
    DefaultTableModel model;
    Statement sent;
    Connection conn;

    String prove = "";

    public FrmCargoProveedores() {
        initComponents();
        this.setLocationRelativeTo(null);// Iniciamos la pantalla al centro
        this.setTitle("Cargo Proveedor");
        //txtProveedor.setEditable(false);
        txtFecha.setText(fechaActual());
        mostrarCargosProvTabla();
        conexion_consulta.conectar();
    }


    /*Producto*/
    public ArrayList<ClsCuentas_x_pagar> getProductoList() {
        ArrayList<ClsCuentas_x_pagar> prodList = new ArrayList<ClsCuentas_x_pagar>();
        String query = "SELECT `CodCuenta`,`Importe`,`NoFact`,`fecha_registro`,cuenta_x_pagar.`RucEmpre`,empresa.`NomEmpre`,cuenta_x_pagar.`RucProv`,proveedor.`NomProv`,proveedor.`ApelProv` FROM `cuenta_x_pagar`,`empresa`,`proveedor` WHERE cuenta_x_pagar.`RucProv` = proveedor.`RucProv`";
        Statement st;
        ResultSet rs;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(query);
            ClsCuentas_x_pagar clsPagar;
            while (rs.next()) {
                clsPagar = new ClsCuentas_x_pagar(rs.getInt("CodCuenta"),
                        rs.getDouble("Importe"),
                        rs.getInt("NoFact"),
                        rs.getString("fecha_registro"),
                        rs.getInt("RucEmpre"),
                        rs.getString("NomEmpre"),
                        rs.getInt("RucProv"),
                        rs.getString("NomProv"),
                        rs.getString("ApelProv"));
                prodList.add(clsPagar);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodList;
    }

    public void mostrarCargosProvTabla() {
        ArrayList<ClsCuentas_x_pagar> list = getProductoList();
        DefaultTableModel model = (DefaultTableModel) tblCargoProveedor.getModel();
        Object[] row = new Object[9];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCodCuenta();
            row[1] = list.get(i).getImporte();
            row[2] = list.get(i).getNoFactura();
            row[3] = list.get(i).getFecha();
            row[4] = list.get(i).getEmpresa();
            row[5] = list.get(i).getNomEmpre();
            row[6] = list.get(i).getProveedor();
            row[7] = list.get(i).getNomProv();
            row[8] = list.get(i).getApelProv();

            model.addRow(row);

        }
    }

    public void executeSqlQuery(String query, String message) {
        Statement st;
        try {
            st = cn.createStatement();
            if (st.executeUpdate(query) == 1) {
                /*refrescar la tabla*/
                DefaultTableModel model = (DefaultTableModel) tblCargoProveedor.getModel();
                model.setRowCount(0);
                mostrarCargosProvTabla();
                ////
                JOptionPane.showMessageDialog(null, "Data" + message + "Succefully");
            } else {
                JOptionPane.showMessageDialog(null, "Data Not" + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd");

        return formatoFecha.format(fecha);
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

        txtFecha.setText(null);
        txtImporte.setText(null);
        txtNumeroFactura.setText(null);
        txtProveedor.setText(null);
        txtReferencia.setText(null);
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
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jDialog1 = new javax.swing.JDialog();
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
        txtActivoProv = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        btnGuardarDatosProv = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        txtReferencia = new javax.swing.JTextField();
        txtNumeroFactura = new javax.swing.JTextField();
        txtImporte = new javax.swing.JTextField();
        btnNuevoProveedor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCargoProveedor = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRucEmpre = new javax.swing.JTextField();
        txtCedulaProveedor = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblEmpresa = new javax.swing.JTable();
        txtCedulaEmpresa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnAgregar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnCancelar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnRegresar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Cedula Proveedor: ");

        jLabel8.setText("Nombre Proveedor: ");

        jLabel9.setText("Apellido Proveedor: ");

        jLabel10.setText("Dir Proveedor:");

        jLabel11.setText("Email Proveedor: ");

        jLabel12.setText("Telf Proveedor");

        txtCedulaProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaProvKeyTyped(evt);
            }
        });

        txtEmailProv.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailProvFocusLost(evt);
            }
        });

        txtTlfProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTlfProvKeyTyped(evt);
            }
        });

        txtActivoProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtActivoProvKeyTyped(evt);
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
                        .addComponent(txtActivoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar2.setRollover(true);

        btnGuardarDatosProv.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        btnGuardarDatosProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save.png"))); // NOI18N
        btnGuardarDatosProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDatosProvActionPerformed(evt);
            }
        });
        jToolBar2.add(btnGuardarDatosProv);

        jLabel15.setFont(new java.awt.Font("AR JULIAN", 1, 24)); // NOI18N
        jLabel15.setText("REGISTRAR PROVEEDOR");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel15))
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(16, 16, 16)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("No. FACTURA");

        jLabel2.setText("REFERENCIA");

        jLabel5.setText("BUSCAR PROVEEDOR (Por Nombre):");

        jLabel6.setText("IMPORTE");

        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorKeyReleased(evt);
            }
        });

        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
            }
        });

        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/document_add.png"))); // NOI18N
        btnNuevoProveedor.setText("Nuevo Proveedor");
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });

        tblCargoProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Importe", "factura", "Fecha", "Ruc Empre", "Nombre empresa", "Ruc Prov", "Nombre Proveedor", "Apellido Proveedor"
            }
        ));
        jScrollPane3.setViewportView(tblCargoProveedor);

        jLabel3.setText("FECHA:");

        jLabel4.setText("BUSCRA EMPRESA (Por nombre):");

        txtRucEmpre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRucEmpreKeyReleased(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl);

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

        jLabel14.setFont(new java.awt.Font("AR JULIAN", 1, 24)); // NOI18N
        jLabel14.setText("CARGO PROVEEDOR");

        jToolBar1.setRollover(true);

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAgregar);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(36, 36, 36)
                                    .addComponent(txtRucEmpre, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCedulaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 711, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                            .addComponent(txtReferencia)
                                            .addComponent(txtNumeroFactura)
                                            .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnNuevoProveedor)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCedulaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel14)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoProveedor)
                    .addComponent(txtCedulaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRucEmpre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedulaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (!txtNumeroFactura.getText().equals("") && !txtReferencia.getText().equals("") && !txtFecha.getText().equals("") && !txtCedulaProveedor.getText().equals("") && !txtCedulaEmpresa.getText().equals("")) {
            if (!txtImporte.getText().equals("")) {

                String query = "INSERT INTO `cuenta_x_pagar` VALUES ('" + txtReferencia.getText() + "','" + txtImporte.getText() + "','" + txtNumeroFactura.getText() + "','" + txtFecha.getText() + "','" + txtCedulaEmpresa.getText() + "','" + txtCedulaProveedor.getText() + "');";

                executeSqlQuery(query, "Inserted");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "FALTAN EL IMPORTE $$", "ERROR!!", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "FALTAN DATOS DEL CARGO DE LA FACTURA PROVEEDOR", "ERROR!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
        jDialog1.setVisible(true);
        jDialog1.setTitle("Registrar Proveedor");
        jDialog1.setLocationRelativeTo(null);// Iniciamos la pantalla al centro
        jDialog1.setSize(400, 485);//ancho-alto
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyReleased
        String Nombre = txtProveedor.getText();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Cédula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Dirección");
        modelo.addColumn("Telefono");
        modelo.addColumn("Correo");
        modelo.addColumn("Activo");
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        datos = conexion_consulta.buscar_caracter_tabla(Nombre);
        for (int i = 0; i < datos.size(); i++) {
            modelo.addRow(datos.get(i));
        }
        tbl.setModel(modelo);

    }//GEN-LAST:event_txtProveedorKeyReleased

    private void txtRucEmpreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucEmpreKeyReleased
        String Nombre = txtRucEmpre.getText();
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
    }//GEN-LAST:event_txtRucEmpreKeyReleased

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        int i = tbl.getSelectedRow();
        TableModel model = tbl.getModel();
        txtCedulaProveedor.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_tblMouseClicked

    private void tblEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpresaMouseClicked
        int i = tblEmpresa.getSelectedRow();
        TableModel model = tblEmpresa.getModel();
        txtCedulaEmpresa.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_tblEmpresaMouseClicked

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO NUMEROS", "ERROR!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtImporteKeyTyped

    private void txtCedulaProvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaProvKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO NUMEROS", "ERROR!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCedulaProvKeyTyped

    private void txtEmailProvFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailProvFocusLost
        if (isEmail(txtEmailProv.getText())) {

        } else {
            JOptionPane.showMessageDialog(null, "EMAIL INCORRECTO.", "ERROR!!", JOptionPane.ERROR_MESSAGE);
            txtEmailProv.setText("");
        }
    }//GEN-LAST:event_txtEmailProvFocusLost

    private void txtTlfProvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTlfProvKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO NUMEROS", "ERROR!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtTlfProvKeyTyped

    private void txtActivoProvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtActivoProvKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO NUMEROS", "ERROR!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtActivoProvKeyTyped

    private void btnGuardarDatosProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDatosProvActionPerformed
        try {

            String nom = txtNomProv.getText();
            String apell = txtApelProv.getText();
            String ci = txtCedulaProv.getText();

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
                    String query = "INSERT INTO `proveedor`(`RucProv`, `NomProv`, `ApelProv`, `DirProv`, `TlfProv`, `EmailProv`, `Activo `) VALUES ('" + txtCedulaProv.getText() + "','" + txtNomProv.getText() + "','" + txtApelProv.getText() + "','" + txtDirProv.getText() + "','" + txtTlfProv.getText() + "','" + txtEmailProv.getText() + "','" + txtActivoProv.getText() + "')";

                    executeSqlQuery(query, "Inserted");

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
            java.util.logging.Logger.getLogger(FrmCargoProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCargoProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCargoProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCargoProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCargoProveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarDatosProv;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTable tbl;
    private javax.swing.JTable tblCargoProveedor;
    private javax.swing.JTable tblEmpresa;
    private javax.swing.JTextField txtActivoProv;
    private javax.swing.JTextField txtApelProv;
    private javax.swing.JTextField txtCedulaEmpresa;
    private javax.swing.JTextField txtCedulaProv;
    private javax.swing.JTextField txtCedulaProveedor;
    private javax.swing.JTextField txtDirProv;
    private javax.swing.JTextField txtEmailProv;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNomProv;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtRucEmpre;
    private javax.swing.JTextField txtTlfProv;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();
}
