/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package From;

import Clase.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class FrmFacturacion extends javax.swing.JFrame {

    public static String FechaFact = "";
    public static DefaultTableModel modelo;
    public static float total = 0;
    public static float totalFacturaSinIVA = 0;
    public static float totalFacturaConIVA = 0;
    public static float totalPorProducto = 0;
    public static int contador = 0;

    /**
     * Creates new form FrmFacturacion
     */
    public FrmFacturacion() {
        initComponents();
        this.setLocationRelativeTo(null);// Iniciamos la pantalla al centro
        this.setTitle("Facturación");

        mostrarDetalleFacturaTabla();
        mostrarProductosComboProd();
        //inicializarCampos();
        obtenerNummerodeRegustros();
    }

    public void inicializarCampos() {

        txtCedCli.setEditable(true);
        txtNomCli.setEditable(false);
        txtApelCLi.setEditable(false);
        txtDirCli.setEditable(false);
        txtEmailCli.setEditable(false);
        txtTlfCli.setEditable(false);
        txtFechaFact.setEditable(false);
        txtNumeroFactura.setEditable(false);
        txtCantProdFact.setEditable(false);
        txtFechaFact.setEditable(false);
        txtNumeroFactura.setEditable(false);

        btnAgregarProdFact.setEnabled(false);
        btnGuardarFactura.setEnabled(false);
        btnLimpiarDatos.setEnabled(false);
        btnCrearFactura.setEnabled(false);
        btnRegresar.setEnabled(true);

        txtSubtotalFact.setEditable(false);
        txtIVAFact.setEditable(false);
        txtTotalFact.setEditable(false);

        cmbListaProdFact.setEnabled(false);
        cmbListaProdFact.setSelectedIndex(-1);
    }

    public void LimpiarCampos() {

        txtCedCli.setText("");
        txtNomCli.setText("");
        txtApelCLi.setText("");
        txtDirCli.setText("");
        txtEmailCli.setText("");
        txtTlfCli.setText("");
        txtFechaFact.setText("");
        txtNumeroFactura.setText("");
        txtCantProdFact.setText("");

        txtSubtotalFact.setText("");
        txtIVAFact.setText("");
        txtTotalFact.setText("");
    }

    public void mostrarDatos(String valor) {

        try {

            String sql = "SELECT * FROM cliente WHERE RucCli = '" + valor + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            String datos[] = new String[6];

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);

            }

            if (datos[0] != null) {

                // lleno los datos del cliente en los combobox correspondientes
                txtNomCli.setText(datos[1]);
                txtApelCLi.setText(datos[2]);
                txtDirCli.setText(datos[3]);
                txtEmailCli.setText(datos[4]);
                txtTlfCli.setText(datos[5]);

                // activo los campos de fecha y numero de factura 
                txtFechaFact.setEditable(true);
                txtNumeroFactura.setEditable(true);
                txtCedCli.setEditable(false);

                btnBuscarCli.setEnabled(false);
                btnLimpiarDatos.setEnabled(true);
                btnCrearFactura.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "!!    CLIENTE NO REGISTRADO   !!");
                inicializarCampos();
                txtCedCli.setText("");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }

    }

    public void mostrarDetalleFacturaTabla() {

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //declarar que las filas y columnas no sean editable
            }

        };

        modelo.addColumn("CANTIDAD ");
        modelo.addColumn("DETALLE PRODUCTO ");
        modelo.addColumn("PRECIO UNITARIO");
        modelo.addColumn("TOTAL");
        tblFactura.setModel(modelo);

    }

    public static boolean validarFecha(String fecha) {

        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);

            String[] partesFecha = fecha.split("/");
            int año = Integer.parseInt(partesFecha[2]);

            if (año >= 1990 && año <= 2020) {
                FechaFact = partesFecha[2] + "-" + partesFecha[1] + "-" + partesFecha[0];

                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            return false;
        }
    }

    public void crearFactura() {
        try {

            String numFact = txtNumeroFactura.getText();
            String fechaFact = txtFechaFact.getText();
            String cedCli = txtCedCli.getText();

            PreparedStatement pst = cn.prepareStatement("INSERT INTO CAB_FACT (NUMFACT,CEDCLI,FECHAVENTA) VALUES (?,?,?)");

            pst.setString(1, numFact);
            pst.setString(2, cedCli);
            pst.setString(3, fechaFact);
            System.out.println("FACTURA CREADA");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, " !! ERROR EN EL INGRESO DE DATOS DE LA FACTURA !! ");

        }

    }

    public void mostrarProductosComboProd() {

        String[] datosProd = new String[3];

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM producto");
            while (rs.next()) {

                datosProd[0] = rs.getString(1);
                datosProd[1] = rs.getString(3);
                datosProd[2] = rs.getString(2);

                cmbListaProdFact.addItem(datosProd[2]);

            }

        } catch (SQLException ex) {

        }

    }

    public void agregarProductosAlaTablaDetalleFact() {

        String detalleProdSelect = (String) cmbListaProdFact.getSelectedItem();

        String[] datosProdDetalleFact = new String[6];

        try {
            if (!txtCantProdFact.getText().equals("") && cmbListaProdFact.getSelectedIndex() != -1) {

                totalPorProducto = 0;
                Statement st = cn.createStatement();

                PreparedStatement pst = cn.prepareStatement("INSERT INTO DET_FACT (IDDETALLE,NUMFACT,CODPROD,CANTIDAD,PRECIO,TOTAL) VALUES (?,?,?,?,?,?)");

                ResultSet rs = st.executeQuery("SELECT * FROM PRODUCTOS");

                while (rs.next()) {

                    datosProdDetalleFact[0] = txtCantProdFact.getText();
                    datosProdDetalleFact[1] = rs.getString(3); //detalleProd
                    datosProdDetalleFact[2] = rs.getString(2); //precioProd
                    datosProdDetalleFact[4] = rs.getString(1); //codg producto

                    if (detalleProdSelect.equals(datosProdDetalleFact[1])) {
                        contador = contador + 1;

                        total = Float.parseFloat(datosProdDetalleFact[2]) * Integer.parseInt(txtCantProdFact.getText());

                        totalPorProducto = Float.parseFloat(datosProdDetalleFact[2]) * Integer.parseInt(txtCantProdFact.getText());

                        totalFacturaSinIVA = totalFacturaSinIVA + total;
                        txtSubtotalFact.setText(String.valueOf(totalFacturaSinIVA));
                        datosProdDetalleFact[3] = String.valueOf(total);

                        //agrego a la tabla DET_FACT en my base de datos los productos que compro con esta factura
                        pst.setString(1, String.valueOf(contador)); //agrego a DET_FACT el numero de detalle de factura
                        pst.setString(2, txtNumeroFactura.getText()); // agrego a DET_FACT el numero de factura
                        pst.setString(3, datosProdDetalleFact[4]); // agrego a DET_FAT el codigo de producto
                        pst.setString(4, txtCantProdFact.getText()); // agrego a DET_FAT la cantidad
                        pst.setString(5, datosProdDetalleFact[2]); // agrego a DET_FAT el precio del producto
                        pst.setString(6, String.valueOf(totalPorProducto)); // agrefo a DET_FAT el total de la factura
                        System.out.println("ya pase ");
                        pst.executeUpdate();

                        modelo.addRow(datosProdDetalleFact);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "    !!   INGRESE UN CANTIDAD Y SELECCIONE UN PRODUCTO   !!     ");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "    !!   ERROR   !!     ");
        }

    }

    public void obtenerNummerodeRegustros() {

        String sumCont = "";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM DET_FACT");
            if (rs.next()) {
                sumCont = rs.getString(1);
            }

            contador = Integer.parseInt(sumCont);
            System.out.println("Cont " + contador);

        } catch (SQLException ex) {
            Logger.getLogger(FrmFacturacion.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNomCli = new javax.swing.JTextField();
        txtCedCli = new javax.swing.JTextField();
        txtDirCli = new javax.swing.JTextField();
        txtFechaFact = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtApelCLi = new javax.swing.JTextField();
        txtTlfCli = new javax.swing.JTextField();
        txtEmailCli = new javax.swing.JTextField();
        btnBuscarCli = new javax.swing.JButton();
        btnCrearFactura = new javax.swing.JButton();
        btnLimpiarDatos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCantProdFact = new javax.swing.JTextField();
        cmbListaProdFact = new javax.swing.JComboBox<>();
        btnAgregarProdFact = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtSubtotalFact = new javax.swing.JTextField();
        txtIVAFact = new javax.swing.JTextField();
        txtTotalFact = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnGuardarFactura = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNumeroFactura = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFactura = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Nombre: ");

        jLabel3.setText("Cedula: ");

        jLabel4.setText("Direccion: ");

        jLabel13.setText("Fecha: ");

        txtCedCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedCliActionPerformed(evt);
            }
        });

        txtFechaFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFactActionPerformed(evt);
            }
        });

        jLabel23.setText("dd/mm/yyyy");

        jLabel2.setText("Apellido: ");

        jLabel5.setText("Telefono: ");

        jLabel6.setText("Email: ");

        btnBuscarCli.setText("BUSCAR DATOS CLIENTE");
        btnBuscarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCliActionPerformed(evt);
            }
        });

        btnCrearFactura.setText("CREAR FACTURA");
        btnCrearFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFacturaActionPerformed(evt);
            }
        });

        btnLimpiarDatos.setText("LIMPIAR DATOS");
        btnLimpiarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3))
                    .addComponent(jLabel13))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomCli, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(txtCedCli)
                            .addComponent(txtDirCli)
                            .addComponent(txtFechaFact))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5))
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmailCli)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtTlfCli, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                            .addComponent(txtApelCLi)))
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpiarDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrearFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtApelCLi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCedCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtTlfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDirCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(txtEmailCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnBuscarCli)
                        .addGap(18, 18, 18)
                        .addComponent(btnCrearFactura)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFechaFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiarDatos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setText("SELECCIONAR PRODUCTO");

        jLabel16.setText("CANTIDAD: ");

        jLabel18.setText("PRODUCTO: ");

        cmbListaProdFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbListaProdFactActionPerformed(evt);
            }
        });

        btnAgregarProdFact.setText("AGREGAR PRODUCTO");
        btnAgregarProdFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProdFactActionPerformed(evt);
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
                        .addGap(0, 158, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAgregarProdFact)
                                .addGap(67, 67, 67))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbListaProdFact, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantProdFact)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtCantProdFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cmbListaProdFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarProdFact)
                .addContainerGap())
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel19.setText("SUBTOTAL: ");

        jLabel21.setText("TOTAL: ");

        jLabel20.setText("IVA 12%: ");

        btnGuardarFactura.setText("GUARDAR FACTURA");
        btnGuardarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarFacturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSubtotalFact, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(txtIVAFact)
                            .addComponent(txtTotalFact)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnGuardarFactura)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtSubtotalFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtIVAFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtTotalFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(btnGuardarFactura)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("MECANICA AUTOMOTRIZ AG");

        jLabel9.setText("AV del Inca y Guarumos N70-150");

        jLabel10.setText("Telf (02) 3412653 - Movil: 0984556312");

        jLabel11.setText("Email: mecanicaAG@hotmail.com");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11)
                        .addComponent(jLabel10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel7)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel9)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel10)
                    .addGap(27, 27, 27)
                    .addComponent(jLabel11)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("R.U.C : 20248454753");

        jLabel14.setText("FACTURA");

        jLabel15.setText("N° ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel15)
                .addGap(29, 29, 29)
                .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(78, 78, 78))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(108, 108, 108))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(32, 32, 32)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        tblFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblFactura);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegresar)))
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedCliActionPerformed

    private void txtFechaFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFactActionPerformed

    private void btnBuscarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCliActionPerformed

        String cedula = txtCedCli.getText();

        if (!cedula.equals("")) {

            mostrarDatos(cedula);

        } else {
            JOptionPane.showMessageDialog(null, "  !!  INGRESE UN NUMERO DE CEDULA  !!  ");
        }

    }//GEN-LAST:event_btnBuscarCliActionPerformed

    private void btnCrearFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFacturaActionPerformed

        String fecha = txtFechaFact.getText();
        String numeroFact = txtNumeroFactura.getText();
        String cedulaCli = txtCedCli.getText();

        boolean fechaValidar = validarFecha(fecha);

        if (!fecha.equals("") && !numeroFact.equals("")) {

            if (fechaValidar == true) {

                System.out.println("si entre");

                try {
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO CAB_FACT (NUMFACT,CEDCLI,FECHAVENTA) VALUES (?,?,?)");

                    pst.setString(1, numeroFact);
                    pst.setString(2, cedulaCli);
                    pst.setString(3, FechaFact);
                    pst.executeUpdate();

                    //JOptionPane.showMessageDialog(null, "     !!  FACTURA CREADA EXITODAMENTE  !! ");
                    btnCrearFactura.setEnabled(false);
                    btnLimpiarDatos.setEnabled(false);
                    btnRegresar.setEnabled(false);
                    btnAgregarProdFact.setEnabled(true);

                    txtFechaFact.setEditable(false);
                    txtNumeroFactura.setEditable(false);
                    txtCantProdFact.setEditable(true);

                    cmbListaProdFact.setEnabled(true);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "  !!  NUMERO DE FACTURA YA REGISTRADO  !!   ");
                }

            } else {
                JOptionPane.showMessageDialog(null, "  !!  FORMATO DE FECHA INCORRECTA dd/mm/yyyy  !!  ");
            }

        } else {
            JOptionPane.showMessageDialog(null, "    !!  LLENE LOS DATOS DE FECHA Y NUMERO DE FACTURA  !!    ");
        }

    }//GEN-LAST:event_btnCrearFacturaActionPerformed

    private void btnLimpiarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarDatosActionPerformed
        
        btnBuscarCli.setEnabled(true);
        LimpiarCampos();
        inicializarCampos();
         
    }//GEN-LAST:event_btnLimpiarDatosActionPerformed

    private void cmbListaProdFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbListaProdFactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbListaProdFactActionPerformed

    private void btnAgregarProdFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProdFactActionPerformed

        String numFact = txtNumeroFactura.getText();
        String detalleProd = (String) cmbListaProdFact.getSelectedItem();
        String cantidadProd = txtCantProdFact.getText();

        agregarProductosAlaTablaDetalleFact();

        txtCantProdFact.setText("");
        cmbListaProdFact.setSelectedIndex(-1);

        btnGuardarFactura.setEnabled(true);
         
    }//GEN-LAST:event_btnAgregarProdFactActionPerformed

    private void btnGuardarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarFacturaActionPerformed
        
        btnAgregarProdFact.setEnabled(false);

        float IVA = (totalFacturaSinIVA*12)/100;
        txtIVAFact.setText(String.valueOf(IVA));

        totalFacturaConIVA = totalFacturaSinIVA+IVA;
        txtTotalFact.setText(String.valueOf(totalFacturaConIVA));

        txtCantProdFact.setEditable(false);
        cmbListaProdFact.setEnabled(false);

        btnRegresar.setEnabled(true);
        btnGuardarFactura.setEnabled(false);

        totalFacturaConIVA = 0;
        totalFacturaSinIVA = 0;
         
    }//GEN-LAST:event_btnGuardarFacturaActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

        FrmMenu venMenu = new FrmMenu();
        venMenu.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFacturacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProdFact;
    private javax.swing.JButton btnBuscarCli;
    private javax.swing.JButton btnCrearFactura;
    private javax.swing.JButton btnGuardarFactura;
    private javax.swing.JButton btnLimpiarDatos;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbListaProdFact;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblFactura;
    private javax.swing.JTextField txtApelCLi;
    private javax.swing.JTextField txtCantProdFact;
    private javax.swing.JTextField txtCedCli;
    private javax.swing.JTextField txtDirCli;
    private javax.swing.JTextField txtEmailCli;
    private javax.swing.JTextField txtFechaFact;
    private javax.swing.JTextField txtIVAFact;
    private javax.swing.JTextField txtNomCli;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtSubtotalFact;
    private javax.swing.JTextField txtTlfCli;
    private javax.swing.JTextField txtTotalFact;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();

}
