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




public class FrmRegistroFactura extends javax.swing.JFrame {
    
    public static String FechaFact = "";
    public static DefaultTableModel modelo;

    
    public static int numFact = 0;
    
    double subTotal = 0.00;
    double iva = 0.00;
    double total = 0.00;
    

   
    public FrmRegistroFactura() {
       initComponents();
       numeroFactura();
       cargarComboDatosCLiente();
       inicializarCampos();
       obtenerFecha();
       numeroFactura();
        
    }
    
   public void inicializarCampos(){
       
       txtCedCli.setEditable(true);
       txtNomCli.setEditable(false);
        txtApelCLi.setEditable(false);
        txtDirCli.setEditable(false);
        txtEmailCli.setEditable(false);
        txtTlfCli.setEditable(false);
        txtFechaFact.setEditable(false);
        txtNumeroFactura.setEditable(false);
        
        txtCedCli.setText("");
        txtNomCli.setText("");
        txtApelCLi.setText("");
        txtDirCli.setText("");
        txtEmailCli.setText("");
        txtTlfCli.setText("");
        txtFechaFact.setText("");
        txtNumeroFactura.setText("");
        
        txtSubtotalFact.setText("");
        txtIVAFact.setText("");
        txtTotalFact.setText("");

        txtFechaFact.setEditable(false);
        txtNumeroFactura.setEditable(false);
        
       
        btnGuardarFactura.setEnabled(false);
        btnLimpiarDatos.setEnabled(false);
       
        btnRegresar.setEnabled(true);
        
        txtSubtotalFact.setEditable(false);
        txtIVAFact.setEditable(false);
        txtTotalFact.setEditable(false);
        
        
        cmbDatosClien.setSelectedIndex(-1);
        
        cmbOrdenTrabajo.setEnabled(false);
        cmbOrdenTrabajo.setSelectedIndex(-1);
        
        
   }
   
   public void LimpiarCampos(){
       
       txtCedCli.setText("");
       txtNomCli.setText("");
       txtApelCLi.setText("");
       txtDirCli.setText("");
       txtEmailCli.setText("");
       txtTlfCli.setText("");
       txtFechaFact.setText("");
       txtNumeroFactura.setText("");
        
        txtSubtotalFact.setText("");
        txtIVAFact.setText("");
        txtTotalFact.setText("");
        
       
        
   }

    public void mostrarDatos(String valor){
        
       
        try {
            
            
            String sql = "SELECT * FROM CLIENTE WHERE RUCCLI = '" + valor + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            String datos[] = new String[7];

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                
            }
            
            if(datos[0] != null ){
                
                // lleno los datos del cliente en los combobox correspondientes
                
                txtNomCli.setText(datos[1]);
                txtApelCLi.setText(datos[2]);
                txtDirCli.setText(datos[3]);
                txtEmailCli.setText(datos[5]);
                txtTlfCli.setText(datos[4]);

                // activo los campos de fecha y numero de factura 
                
                txtFechaFact.setEditable(true);
                txtNumeroFactura.setEditable(true);
                txtCedCli.setEditable(false);
                
                btnBuscarCli.setEnabled(false);
                btnLimpiarDatos.setEnabled(true);
               
                cmbOrdenTrabajo.setEnabled(true);
                cmbOrdenTrabajo.setSelectedIndex(-1);
                //cargarDatosComboOrdenTrabajo();
      
            }else{
                JOptionPane.showMessageDialog(null, "!!    CLIENTE NO REGISTRADO   !!");
                inicializarCampos();
                txtCedCli.setText("");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR "+ ex);
        }
        
    }
    
     public void mostrarDetalleFacturaTabla(String idOrden){
         double subTotal = 0.00;
         
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //declarar que las filas y columnas no sean editable
            }
            
        };
        
        modelo.addColumn("CANTIDAD ");
        modelo.addColumn("DETALLE PRODUCTO O SERVICIO ");
        modelo.addColumn("PRECIO UNITARIO");   
        modelo.addColumn("TOTAL");
        tblFactura.setModel(modelo);
        
         try {
             String sql = "SELECT ser.Descripcion, ser.PrecioServ,Otros,Subtotal,Iva,Total FROM orden_trabajo ord, det_ordenservicio dor, servicio ser\n"
                     + "where ord.IdOrden = dor.IdOrden and ser.IdServ = dor.IdServ and ord.IdOrden = '" + idOrden + "'";
             
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql);
             
            String datos[] = new String[4];
            String datosProd[] = new String[4];
            String valores[] = new String[4];

            while (rs.next()) {
                
                datos[0] = "1";
                datos[1] = rs.getString(1);
                datos[2] = rs.getString(2);
                datos[3] = rs.getString(2);
                valores[0] = rs.getString(3);
                valores[1] = rs.getString(4);
                valores[2] = rs.getString(5);
                valores[3] = rs.getString(6);
                modelo.addRow(datos);
               
            }
            
            String sql2 = "SELECT prod.Cantidad,prod.Descripcion, prod.PrecioUnit FROM orden_trabajo ord, det_ordenproducto dop, producto prod\n" +
                            " where ord.IdOrden = dop.IdOrden and prod.IdProd = dop.IdProd and ord.IdOrden ='"+idOrden+"'";
             Statement st2 = cn.createStatement();
             ResultSet rs2 = st.executeQuery(sql2);
             System.out.println("MAMA");
             while (rs2.next()) {
                
                datosProd[0] = rs2.getString(1);
                datosProd[1] = rs2.getString(2);
                datosProd[2] = rs2.getString(3);
                datosProd[3] = String.valueOf((Double.parseDouble(rs2.getString(3))*Double.parseDouble(rs2.getString(1))));
                modelo.addRow(datosProd);
            }
             
             
             
         } catch (Exception e) {
             System.out.println("EROROR"+e);
         }

  
    }
     
     public void crearFactura(){
        try{
            
            String numFact = txtNumeroFactura.getText();
            String fechaFact = txtFechaFact.getText();
            String cedCli = txtCedCli.getText();
        
        PreparedStatement pst = cn.prepareStatement("INSERT INTO CAB_FACT (NUMFACT,CEDCLI,FECHAVENTA) VALUES (?,?,?)");

            
                pst.setString(1,numFact);
                pst.setString(2, cedCli);
                pst.setString(3, fechaFact);
                System.out.println("FACTURA CREADA");
                
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, " !! ERROR EN EL INGRESO DE DATOS DE LA FACTURA !! ");

        }

    }
     
    
    
    public void cargarDatosComboOrdenTrabajo(int ced){
        
        String numCed = String.valueOf(ced);
        
        try {
            
            String sql = "SELECT IDORDEN FROM ORDEN_TRABAJO WHERE RUCCLI = '" + numCed + "'  AND `facturado` = 'NO'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            String datos[] = new String[1];

            while (rs.next()) {
                datos[0] = rs.getString(1);
                cmbOrdenTrabajo.addItem(datos[0]);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FrmRegistroFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarComboDatosCLiente(){
           String[] datosCliente = new String[3];

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT ord.RucCli,NomCli,ApelCli FROM orden_trabajo ord, cliente cli\n" +
            "where ord.RucCli = cli.RucCli");

            while (rs.next()) {
                datosCliente[0] = rs.getString(1);
                datosCliente[1] = rs.getString(2);
                datosCliente[2] = rs.getString(3);
                
                cmbDatosClien.addItem(datosCliente[0]+" - "+datosCliente[1]+" - "+datosCliente[2]);
            } 

        } catch (Exception ex) {
            System.out.println("ERROR LLENAR COMBO CEDULA "+ex);
        }

    }
    
    public void numeroFactura (){  
         
          try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(NOFACT) FROM CAB_FACT");
            
            while (rs.next()) {
                 numFact  = Integer.parseInt(rs.getString(1));
            }
            
            numFact = numFact+1; 
            
            txtNumeroFactura.setText(String.valueOf(numFact));
            
            
        } catch (Exception ex) {
            numFact = 1;
            txtNumeroFactura.setText(String.valueOf(numFact));
        }
     }
    
      public int separarCedString (String cadena){
        int cedul = 0;
        try {
            String subCadena = cadena.substring(0,10);
            cedul = Integer.parseInt(subCadena);
        } catch (Exception e) {
            System.out.println(e);;
        }
        
        return cedul;
    }
      
     public void obtenerFecha() {
         try {

             String fecha = "";
             Statement st3 = cn.createStatement();
             ResultSet rs3 = st3.executeQuery("SELECT CURDATE()");

             while (rs3.next()) {
                 fecha = rs3.getString(1);
             }

             txtFechaFact.setText(fecha);
         } catch (Exception e) {
         }
    }
     
     public void calcularTotales(){
         
         subTotal = 0.0;
         iva = 0.0;
         total = 0.0;
         
         int filas = modelo.getRowCount();
         
         for (int i=0; i<filas;i++) {
             String subFila  = (String) modelo.getValueAt(i, 3);
             subTotal = subTotal+ Double.valueOf(subFila); 
         }
    
         
         txtSubtotalFact.setText(String.valueOf(subTotal));
         
         iva = (subTotal * 12)/100;
         
         total = subTotal + iva;
         
         txtIVAFact.setText(String.valueOf(iva));
         txtTotalFact.setText(String.valueOf(total));
         
         System.out.println("YA PASE");
         
     }
     
     
    
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomCli = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApelCLi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCedCli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDirCli = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTlfCli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmailCli = new javax.swing.JTextField();
        btnBuscarCli = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtFechaFact = new javax.swing.JTextField();
        btnLimpiarDatos = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cmbOrdenTrabajo = new javax.swing.JComboBox<>();
        cmbDatosClien = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtSubtotalFact = new javax.swing.JTextField();
        txtIVAFact = new javax.swing.JTextField();
        txtTotalFact = new javax.swing.JTextField();
        btnGuardarFactura = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNumeroFactura = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFactura = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Nombre: ");

        jLabel2.setText("Apellido: ");

        jLabel3.setText("Cedula: ");

        txtCedCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedCliActionPerformed(evt);
            }
        });

        jLabel4.setText("Direccion: ");

        jLabel5.setText("Telefono: ");

        jLabel6.setText("Email: ");

        btnBuscarCli.setText("BUSCAR DATOS CLIENTE");
        btnBuscarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCliActionPerformed(evt);
            }
        });

        jLabel13.setText("Fecha: ");

        txtFechaFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFactActionPerformed(evt);
            }
        });

        btnLimpiarDatos.setText("LIMPIAR DATOS");
        btnLimpiarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarDatosActionPerformed(evt);
            }
        });

        jLabel8.setText("Orden Trab N°: ");

        cmbOrdenTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOrdenTrabajoActionPerformed(evt);
            }
        });

        cmbDatosClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDatosClienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtCedCli))
                    .addComponent(cmbDatosClien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaFact, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDirCli))))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTlfCli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApelCLi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailCli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrdenTrabajo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCli, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiarDatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbDatosClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtApelCLi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtCedCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(txtTlfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscarCli))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDirCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtEmailCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addComponent(btnLimpiarDatos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cmbOrdenTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel13)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel19.setText("SUBTOTAL: ");

        jLabel20.setText("IVA 12%: ");

        jLabel21.setText("TOTAL: ");

        txtSubtotalFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalFactActionPerformed(evt);
            }
        });

        btnGuardarFactura.setText("GUARDAR FACTURA");
        btnGuardarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarFacturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSubtotalFact, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(txtIVAFact)
                    .addComponent(txtTotalFact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnGuardarFactura)
                .addGap(29, 29, 29))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtSubtotalFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtIVAFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarFactura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("R.U.C : 20248454753");

        jLabel14.setText("FACTURA");

        jLabel15.setText("N° ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel15)
                .addGap(29, 29, 29)
                .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(78, 78, 78))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(108, 108, 108))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12)
                .addGap(32, 32, 32)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("MECANICA AUTOMOTRIZ AG");

        jLabel9.setText("AV del Inca y Guarumos N70-150");

        jLabel10.setText("Telf (02) 3412653 - Movil: 0984556312");

        jLabel11.setText("Email: mecanicaAG@hotmail.com");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(jLabel9)))
                .addGap(78, 78, 78))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(27, 27, 27)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logoEmpresa.jpg"))); // NOI18N

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
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegresar)
                        .addGap(9, 9, 9))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnRegresar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCliActionPerformed
        
        String cedula = txtCedCli.getText();
        String idOrden = (String) cmbOrdenTrabajo.getSelectedItem();
        if(cmbDatosClien.getSelectedIndex() != -1 && cmbOrdenTrabajo.getSelectedIndex() != -1){
             
            mostrarDetalleFacturaTabla(idOrden);
            cmbDatosClien.setEnabled(false);
            cmbOrdenTrabajo.setEnabled(false);
            btnLimpiarDatos.setEnabled(true);

            btnGuardarFactura.setEnabled(true);

            calcularTotales();

        }else{
            JOptionPane.showMessageDialog(rootPane, "SELECCIONE LOS CAMPOS DE DATOS DEL CLIENTE O ORDEN DE TRABAJO");
        }
       
       
    }//GEN-LAST:event_btnBuscarCliActionPerformed

    
    
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        
        FrmMenu venMenu = new FrmMenu();
        venMenu.setSize(500, 500);
        venMenu.setResizable(false);
        venMenu.setDefaultCloseOperation(venMenu.EXIT_ON_CLOSE);
        venMenu.setLocation(500, 100);
        venMenu.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGuardarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarFacturaActionPerformed

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "!DESEA GUARDAR ESTA FACTURA!", "Confirmacion", dialogButton);

        if (dialogResult == 0) {

            System.out.println("Yes option");

            String numFact = txtNumeroFactura.getText();
            String rucCli = txtCedCli.getText();
            String fechaEmision = txtFechaFact.getText();
            String idOrden= (String) cmbOrdenTrabajo.getSelectedItem();
            String Subtotal = txtSubtotalFact.getText();
            String iva = txtIVAFact.getText();
            String total = txtTotalFact.getText();
            String rucEmpre = "1723036552";
          

            try {

                PreparedStatement pst = cn.prepareStatement("INSERT INTO `cab_fact`(`NoFact`, `RucCli`, `fechaFact`, `IdOrden`, `RucEmpre`, `Subtotal`, `Iva`, `Total`) VALUES (?,?,?,?,?,?,?,?)");

                pst.setString(1, numFact);
                pst.setString(2, rucCli);
                pst.setString(3, fechaEmision);
                pst.setString(4, idOrden);
                pst.setString(5, rucEmpre );
                pst.setString(6, Subtotal);
                pst.setString(7, iva);
                pst.setString(8, total);
                
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(rootPane, "LA FACTURA SE HA REGISTRADO EXITOSAMENTE");
                
                inicializarCampos();
                int filas = modelo.getRowCount();
                for (int i = 0; i < filas; i++) {
                    modelo.removeRow(0);
                }
               
                cmbDatosClien.setEnabled(true);
                obtenerFecha();
                numeroFactura();
                try {
                    String sql = "UPDATE `ORDEN_TRABAJO` SET `facturado`='SI' WHERE IdOrden = '"+idOrden+"'";
                    PreparedStatement pst2 = cn.prepareStatement(sql);
                    pst2.executeUpdate();
                } catch (Exception e) {
                    System.out.println("NO SE AUTUALIZO LOS DATOS");
                }
                

            } catch (Exception e) {
                System.out.println("" + e);
            }

        } else {
            System.out.println("No Option");
        }


    }//GEN-LAST:event_btnGuardarFacturaActionPerformed

    private void btnLimpiarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarDatosActionPerformed
        
        btnBuscarCli.setEnabled(true);
        cmbDatosClien.setEnabled(true);
        LimpiarCampos();
        inicializarCampos();
       
        int filas = modelo.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }

        obtenerFecha();
        numeroFactura();

        
        
    }//GEN-LAST:event_btnLimpiarDatosActionPerformed

     
      
    private void txtCedCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedCliActionPerformed

    private void txtFechaFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFactActionPerformed

    private void cmbOrdenTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOrdenTrabajoActionPerformed
       
        int select = cmbDatosClien.getSelectedIndex();
        if (select != -1) {
            cmbOrdenTrabajo.setEnabled(true);
            
            if (cmbOrdenTrabajo.getSelectedIndex() != -1) {
                String idOrden = (String) cmbOrdenTrabajo.getSelectedItem();
                
            } else {

            }
        }else{
             cmbOrdenTrabajo.setEnabled(false);
        }
        
           
    }//GEN-LAST:event_cmbOrdenTrabajoActionPerformed

    private void cmbDatosClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDatosClienActionPerformed
        
        int select = cmbDatosClien.getSelectedIndex();
       
        if (select != -1) {
             String datosClienteFact[] = new String[6];
            try {
                
                String datosCliente = (String) cmbDatosClien.getSelectedItem();
                int ced = separarCedString(datosCliente);

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT `NomCli`,`RucCli`,`DirCli`,`TlfCli`,`EmailCli`,`ApelCli` FROM `cliente");

                while (rs.next()) {
                    datosClienteFact[0] = rs.getString(1);
                    datosClienteFact[1] = rs.getString(2);
                    datosClienteFact[2] = rs.getString(3);
                    datosClienteFact[3] = rs.getString(4);
                    datosClienteFact[4] = rs.getString(5);
                    datosClienteFact[5] = rs.getString(6);

                    if (datosClienteFact[1].equals(String.valueOf(ced))) {

                        txtCedCli.setText(datosClienteFact[1]);
                        txtDirCli.setText(datosClienteFact[2]);
                        txtTlfCli.setText(datosClienteFact[3]);
                        txtEmailCli.setText(datosClienteFact[4]);
                        txtNomCli.setText(datosClienteFact[0]);
                        txtApelCLi.setText(datosClienteFact[5]);
                    }
                    
                    
                    txtNomCli.setEnabled(true);
                    txtCedCli.setEnabled(true);
                    txtDirCli.setEnabled(true);
                    txtTlfCli.setEnabled(true);
                    txtEmailCli.setEnabled(true);
                    txtApelCLi.setEnabled(true);
                    
                }
                
                cmbOrdenTrabajo.removeAllItems();
                cargarDatosComboOrdenTrabajo(ced);

               

            } catch (Exception e) {
            }

        }
 
    }//GEN-LAST:event_cmbDatosClienActionPerformed

    private void txtSubtotalFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalFactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalFactActionPerformed

    
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
            java.util.logging.Logger.getLogger(FrmRegistroFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegistroFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegistroFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistroFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegistroFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCli;
    private javax.swing.JButton btnGuardarFactura;
    private javax.swing.JButton btnLimpiarDatos;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbDatosClien;
    private javax.swing.JComboBox<String> cmbOrdenTrabajo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblFactura;
    private javax.swing.JTextField txtApelCLi;
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
