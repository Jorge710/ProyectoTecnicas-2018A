/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package From;

import Clase.ClsServicio;
import Clase.conectar;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdk.management.resource.internal.TotalResourceContext;

/**
 *
 * @author Usuario
 */
public class FrmOrdenTrabajo extends javax.swing.JFrame {

    public static DefaultTableModel modelo;
    ArrayList<ClsServicio> listServicio = new ArrayList<ClsServicio>();
    double manoDeObra = 0.0;
    double otros = 0.0;
    double subTotal = 0.0;
    double iva = 0.0;
    double total = 0.0;
    int numIdOrden =0;
    int numIdOrdenServ =0;
    
    
    public FrmOrdenTrabajo() {
        initComponents();
        this.setLocationRelativeTo(null);// Iniciamos la pantalla al centro
        inicializarCampos();
        llenarComboServicio();
        mostrarDetalleFacturaTabla();
        
    }

    public void inicializarCampos() {
        
        
        txtNomCli.setEnabled(false);
        txtNomCli.setEditable(false);
        txtNomCli.setText("");
        txtCICli.setEnabled(false);
        txtCICli.setEditable(false);
        txtCICli.setText("");
        txtDirCli.setEnabled(false);
        txtDirCli.setEditable(false);
        txtDirCli.setText("");
        txtTlfCli.setEnabled(false);
        txtTlfCli.setEditable(false);
        txtTlfCli.setText("");
        txtNumIdOrden.setEnabled(false);
        txtNumIdOrden.setEditable(false);
        txtNumIdOrden.setText("");

        txtFechRecepVeh.setEditable(false);
        txtFechRecepVeh.setEnabled(false);
        txtFechRecepVeh.setText("");
        txtTipVehi.setEnabled(false);
        txtTipVehi.setEditable(false);
        txtTipVehi.setText("");
        txtColorVeh.setEnabled(false);
        txtColorVeh.setEditable(false);
        txtColorVeh.setText("");
        txtKilomVeh.setEnabled(false);
        txtKilomVeh.setEditable(false);
        txtKilomVeh.setText("");
        
        txtManoDeObra.setEnabled(false);
        txtOtros.setEnabled(false);
        txtSubtotal.setEnabled(false);
        txtIva.setEnabled(false);
        txtTotal.setEnabled(false);
       
        
        txtManoDeObra.setEditable(false);
        txtOtros.setEditable(false);
        txtSubtotal.setEditable(false);
        txtIva.setEditable(false);
        txtTotal.setEditable(false);
        
        cmbServicios.setEnabled(false);
        
        btnAgregarServicios.setEnabled(false);
        btnBorrarServicio.setEnabled(false);
        btnGuardarOrdenTrabajo.setEnabled(false);
        
        
        txtManoDeObra.setText("0.00");
        txtOtros.setText("0.00");
        txtSubtotal.setText("0.00");
        txtIva.setText("0.00");
        txtTotal.setText("0.00");
        
       
        

    }

    public void cargarCampos() {

        txtNomCli.setEnabled(true);
        txtNomCli.setEditable(false);
        txtCICli.setEnabled(true);
        txtCICli.setEditable(false);
        txtDirCli.setEnabled(true);
        txtDirCli.setEditable(false);
        txtTlfCli.setEnabled(true);
        txtTlfCli.setEditable(false);
        txtTipVehi.setEnabled(true);
        txtTipVehi.setEditable(false);
        txtColorVeh.setEnabled(true);
        txtColorVeh.setEditable(false);
        txtFechRecepVeh.setEnabled(true);
        txtFechRecepVeh.setEditable(false);
        txtKilomVeh.setEnabled(true);
        txtKilomVeh.setEditable(true);
        txtNumIdOrden.setEnabled(true);
        txtNumIdOrden.setEditable(false);
         
        
        txtManoDeObra.setEnabled(true);
        txtOtros.setEnabled(true);
        txtSubtotal.setEnabled(true);
        txtIva.setEnabled(true);
        txtTotal.setEnabled(true);
        
        txtManoDeObra.setEditable(false);
        txtOtros.setEditable(false);
        txtSubtotal.setEditable(false);
        txtIva.setEditable(false);
        txtTotal.setEditable(false);
        
        cmbServicios.setEnabled(true);
        
        btnAgregarServicios.setEnabled(true);
        btnBorrarServicio.setEnabled(true);
        btnGuardarOrdenTrabajo.setEnabled(true);


    }
    
    
    public void llenarComboServicio() {

        String[] datosServicios = new String[1];

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DESCRIPCION FROM SERVICIO");

            while (rs.next()) {
                datosServicios[0] = rs.getString(1);
                cmbServicios.addItem(datosServicios[0]);
            }

            cmbServicios.setSelectedIndex(-1);

        } catch (Exception ex) {
            System.out.println("ERROR");
        }

    }

     public void mostrarDetalleFacturaTabla(){
        
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //declarar que las filas y columnas no sean editable
            }
            
        };
        
        modelo.addColumn("OPERACION N° ");
        modelo.addColumn("DESCRIPCION DEL SERVICIO ");
        modelo.addColumn("PRECIO ");
        tblAgregarServ.setModel(modelo);

    }
     
     public void calcularPrecioServicio() {
        manoDeObra = 0.0;
        otros = 0.0;
        subTotal =0.0;
        iva =0.0;
        total =0.0;
        
        for (ClsServicio servicio : listServicio) {
            manoDeObra = manoDeObra + servicio.getPrecio();
            System.out.println("mano de obra " + manoDeObra);
        }
        subTotal = manoDeObra + otros;
        iva = (subTotal*0.12);
        total = subTotal + iva;
        
        txtManoDeObra.setText(String.valueOf(manoDeObra));
        txtOtros.setText(String.valueOf(otros));
        txtSubtotal.setText(String.valueOf(subTotal));
        txtIva.setText(String.valueOf(iva));
        txtTotal.setText(String.valueOf(total));
    }
     
     public void numeroOrdenTrab (){  
         
          try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(IdOrden) FROM orden_trabajo");
            
            while (rs.next()) {
                 numIdOrden  = Integer.parseInt(rs.getString(1));
            }
            
            numIdOrden = numIdOrden+1; 
            
            txtNumIdOrden.setText(String.valueOf(numIdOrden));
            
            
        } catch (Exception ex) {
            numIdOrden = 1;
             txtNumIdOrden.setText(String.valueOf(numIdOrden));
        }
     }
     
      public void numeroOrdenServicio (){
         
          try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(IdOrdenServi) FROM det_ordenservicio");
            
            while (rs.next()) {
                 numIdOrdenServ   = Integer.parseInt(rs.getString(1));
            }
            
            numIdOrdenServ  = numIdOrdenServ +1;
            
            
        } catch (Exception ex) {
            numIdOrdenServ  = 1;
        }
     }
     
     public void cargarServiciosOrdenBDD(ArrayList<ClsServicio> listServicio) {
        int idServicio = 0;
        String idOrden = txtNumIdOrden.getText();

        for (ClsServicio servicio : listServicio) {
            idServicio = servicio.getIdservicio();
            System.out.println("IdServicio "+idServicio);
            
            
            try {
                PreparedStatement pst = cn.prepareStatement("INSERT INTO DET_ORDENSERVICIO (IDORDENSERVI,IDSERV,IDORDEN) VALUES (?,?,?)");
                
                pst.setString(1, String.valueOf(numIdOrdenServ));
                pst.setString(2, String.valueOf(idServicio));
                pst.setString(3, idOrden);
                pst.executeUpdate();
            } catch (Exception e) {
                System.out.println("ERROR "+ e);
            }

            numeroOrdenServicio();
            System.out.println("Numero de Orden de SERVICIO "+numIdOrdenServ);
        }

    }
     
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNomCli = new javax.swing.JTextField();
        txtCICli = new javax.swing.JTextField();
        txtDirCli = new javax.swing.JTextField();
        txtTlfCli = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtFechRecepVeh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtKilomVeh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTipVehi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtColorVeh = new javax.swing.JTextField();
        txtPlacVeh = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumIdOrden = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtManoDeObra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtOtros = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        btnGuardarOrdenTrabajo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnCargarDatos = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cmbServicios = new javax.swing.JComboBox<>();
        btnAgregarServicios = new javax.swing.JButton();
        btnBorrarServicio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAgregarServ = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS CLIENTE"));

        jLabel1.setText("NOMBRES:");

        jLabel2.setText("C.I CLIENTE: ");

        jLabel3.setText("DIRECCION:");

        jLabel4.setText("TELEFONO:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtDirCli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(txtCICli, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomCli, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTlfCli))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCICli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDirCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTlfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS VEHICULO"));

        jLabel6.setText("FECHA DE RECEPCION");

        jLabel7.setText("KILOMETRAJE");

        txtKilomVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKilomVehActionPerformed(evt);
            }
        });

        jLabel8.setText("TIPO");

        jLabel9.setText("PLACA");

        jLabel10.setText("COLOR");

        jLabel16.setText("ejm. PAA-0352");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(txtFechRecepVeh)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKilomVeh))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(txtTipVehi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(txtPlacVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtColorVeh)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechRecepVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipVehi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColorVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKilomVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlacVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        jLabel5.setText("ORDEN N°:");

        txtNumIdOrden.setText("00000");

        jLabel11.setText("MANO DE OBRA");

        jLabel12.setText("OTROS");

        jLabel13.setText("SUBTOTAL");

        jLabel14.setText("TOTAL");

        jLabel15.setText("IVA 12%");

        txtIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIvaActionPerformed(evt);
            }
        });

        btnGuardarOrdenTrabajo.setText("GUARDAR ORDEN ");
        btnGuardarOrdenTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarOrdenTrabajoActionPerformed(evt);
            }
        });

        jButton1.setText("PROBAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(28, 28, 28)
                                .addComponent(txtManoDeObra, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardarOrdenTrabajo)
                        .addContainerGap(455, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(172, 172, 172))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtManoDeObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarOrdenTrabajo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtOtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(13, 13, 13)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnCargarDatos.setText("CARGAR DATOS");
        btnCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarDatosActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("DETALLE SERVICIOS"));

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setText("Seleccione un servicio: ");

        cmbServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbServiciosActionPerformed(evt);
            }
        });

        btnAgregarServicios.setText("Agregar");
        btnAgregarServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarServiciosActionPerformed(evt);
            }
        });

        btnBorrarServicio.setText("Borrar");
        btnBorrarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarServicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(29, 29, 29)
                .addComponent(cmbServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnAgregarServicios)
                .addGap(18, 18, 18)
                .addComponent(btnBorrarServicio)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cmbServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarServicios)
                    .addComponent(btnBorrarServicio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblAgregarServ.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblAgregarServ);

        jScrollPane1.setViewportView(jScrollPane2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("INGRESO DE SERVICIOS", jPanel10);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("INGRESO DE MATERIALES UTILIZADOS", jPanel11);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegresar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCargarDatos)
                                .addGap(33, 33, 33))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtNumIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRegresar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargarDatos)
                        .addGap(151, 151, 151)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGuardarOrdenTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarOrdenTrabajoActionPerformed

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "!DESEA GUARDAR ESTA ORDEN DE TRABAJO!", "Confirmacion", dialogButton);
        
        if (dialogResult == 0) {
            
            System.out.println("Yes option");
            
            if (!txtKilomVeh.getText().equals("")) {
                
                String idNumOrden = txtNumIdOrden.getText();
                String rucCli = txtCICli.getText();
                String placa = txtPlacVeh.getText();
                String fechaRecep = txtFechRecepVeh.getText();
                String kilom = txtKilomVeh.getText();
                String manoObra = txtManoDeObra.getText();
                String otros = txtOtros.getText();
                String subtotal = txtSubtotal.getText();
                String iva = txtIva.getText();
                String total = txtTotal.getText();

                try {

                    PreparedStatement pst = cn.prepareStatement("INSERT INTO ORDEN_TRABAJO (IDORDEN,RUCCLI,PLACA,FECHAHORA_RECEPCION,KILOMETRAJE,MANO_DE_OBRA,OTROS,SUBTOTAL,IVA,TOTAL) VALUES (?,?,?,?,?,?,?,?,?,?)");

                    pst.setString(1, idNumOrden);
                    pst.setString(2, rucCli);
                    pst.setString(3, placa);
                    pst.setString(4, fechaRecep);
                    pst.setString(5, kilom);
                    pst.setString(6, manoObra);
                    pst.setString(7, otros);
                    pst.setString(8, subtotal);
                    pst.setString(9, iva);
                    pst.setString(10, total);
                    
                    
                    if (listServicio.size()!=0) {
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(rootPane, "ORDEN REGISTRADA EXITOSAMENTE");
                        cmbServicios.removeAllItems();
                        llenarComboServicio();
                        cargarServiciosOrdenBDD(listServicio);
                        inicializarCampos();

                        for (int i = 0; i < listServicio.size(); i++) {
                            modelo.removeRow(0);
                        }
                        
                        listServicio.removeAll(listServicio);
                        System.out.println(listServicio);
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "**NO A REGISTRADO NINGUN SERVICO**");
                    }

                } catch (Exception e) {
                    System.out.println("" + e);
                    JOptionPane.showMessageDialog(rootPane, "EN EL CAMPO KILOMETRAJE DATOS INCORRECTOS");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ingrese el kilometraje del vehiculo");
            }

        } else {
            System.out.println("No Option");
        }


          
        
    }//GEN-LAST:event_btnGuardarOrdenTrabajoActionPerformed

    private void txtKilomVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKilomVehActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKilomVehActionPerformed

    private void btnCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarDatosActionPerformed

        String[] datosVehiculo = new String[5];
        String[] datosCliente = new String[7];
        String fecha = "";

        if (!txtPlacVeh.getText().equals("")) {

            String placaVeh = txtPlacVeh.getText();
            System.out.println("PLACA " + placaVeh);

            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM VEHICULO WHERE VEHICULO.PLACA = '" + placaVeh + "'");

                while (rs.next()) {

                    datosVehiculo[0] = rs.getString(1);
                    datosVehiculo[1] = rs.getString(2);
                    datosVehiculo[2] = rs.getString(3);
                    datosVehiculo[3] = rs.getString(4);
                    datosVehiculo[4] = rs.getString(5);

                }

                String verificar = datosVehiculo[0];

                if (verificar != null) {

                    txtTipVehi.setText(datosVehiculo[1]);
                    txtColorVeh.setText(datosVehiculo[3]);

                    String cICli = datosVehiculo[4];

                    ResultSet rs1 = st.executeQuery("SELECT * FROM CLIENTE WHERE CLIENTE.RUCCLI = '" + cICli + "'");
                    

                    while (rs1.next()) {

                        datosCliente[0] = rs1.getString(1);
                        datosCliente[1] = rs1.getString(2);
                        datosCliente[2] = rs1.getString(3);
                        datosCliente[3] = rs1.getString(4);
                        datosCliente[4] = rs1.getString(5);
                        datosCliente[5] = rs1.getString(6);
                        datosCliente[6] = rs1.getString(7);
                    }
                    
                    

                    String nombres = datosCliente[1] + " " + datosCliente[2];
                    txtNomCli.setText(nombres);
                    txtCICli.setText(datosCliente[0]);
                    txtDirCli.setText(datosCliente[3]);
                    txtTlfCli.setText(datosCliente[4]);
                    
                    ResultSet rs2 = st.executeQuery("SELECT CURDATE()");
                     
                    while (rs2.next()) {
                        fecha = rs2.getString(1);
                    }
                    
                    txtFechRecepVeh.setText(fecha);

                    numeroOrdenTrab();
                    numeroOrdenServicio();
                    cargarCampos();
                    
                } else {
                   
                     JOptionPane.showMessageDialog(rootPane, "!NO EXISTE EN REGISTRO!", "ERROR", HIDE_ON_CLOSE);
                     inicializarCampos();
                     cmbServicios.removeAllItems();
                     llenarComboServicio(); 
                     System.out.println("lista "+listServicio.size());
                     System.out.println("----------------");
                    
                     for(int i=0;i<listServicio.size();i++){
                      modelo.removeRow(0);
                    }
                     listServicio.removeAll(listServicio);
                     System.out.println(listServicio);
                     

                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "!LLENE EL CAMPO!", "ERROR", HEIGHT);
        }

    }//GEN-LAST:event_btnCargarDatosActionPerformed

    private void cmbServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbServiciosActionPerformed
        
        
    }//GEN-LAST:event_cmbServiciosActionPerformed

    private void btnAgregarServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServiciosActionPerformed
        String servicioString = (String) cmbServicios.getSelectedItem();
        System.out.println("SERVICIO: "+servicioString);
        boolean verificar = false; 
        
        String datosServicio[] = new String [3];
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM SERVICIO");

            while (rs.next()) {
                datosServicio[0] = rs.getString(1);
                datosServicio[1] = rs.getString(2);
                datosServicio[2] = rs.getString(3);

                if (datosServicio[1].equals(servicioString)) {
                    ClsServicio serv = new ClsServicio(Integer.parseInt(datosServicio[0]), datosServicio[1], Double.parseDouble(datosServicio[2]));
                  {
                        listServicio.add(serv);
                        modelo.addRow(datosServicio);
                        cmbServicios.removeItem(serv.getDescripcion());
    
                    }

                }
            }
            
            
            calcularPrecioServicio();
            cmbServicios.setSelectedIndex(-1);
            System.out.println(listServicio);
            
            

        } catch (Exception ex) {
            System.out.println("ERROR " + ex);
        }

        cmbServicios.setSelectedIndex(-1);
        
        
        
        
        
    }//GEN-LAST:event_btnAgregarServiciosActionPerformed

    private void btnBorrarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarServicioActionPerformed
        try {
            int filaselect = tblAgregarServ.getSelectedRow();
            String descripcion = (String) modelo.getValueAt(filaselect, 1);
            System.out.println("SELECCIONASTE " + descripcion);
            int posServicio = 0;

            String[] datosServicios = new String[1];

            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT DESCRIPCION FROM SERVICIO WHERE SERVICIO.DESCRIPCION = '" + descripcion + "'");

                while (rs.next()) {
                    datosServicios[0] = rs.getString(1);
                    cmbServicios.addItem(datosServicios[0]);
                }

                modelo.removeRow(filaselect);

                for (ClsServicio servicio : listServicio) {
                    if (servicio.getDescripcion().equals(descripcion)) {
                        posServicio = listServicio.indexOf(servicio);
                    }

                }

                listServicio.remove(posServicio);
                cmbServicios.setSelectedIndex(-1);
                System.out.println(listServicio);
                calcularPrecioServicio();

            } catch (Exception ex) {
                System.out.println("ERROR");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "!SELECCIONE UNA FILA DE SERVICIO PARA ELIMINAR!", "ERROR", HEIGHT);
        }


        
    }//GEN-LAST:event_btnBorrarServicioActionPerformed

    private void txtIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIvaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        int idServicio = 0;
        
            
            try {
                PreparedStatement pst1 = cn.prepareStatement("INSERT INTO DET_ORDENSERVICIO (IDORDENSERVI,IDSERV,IDORDEN) VALUES (?,?,?)");
                
                pst1.setString(1,"1");
                pst1.setString(2, "2");
                pst1.setString(3, "2");
                
                
                
            } catch (Exception e) {
                System.out.println("ERROR "+ e);
            }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(FrmOrdenTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmOrdenTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmOrdenTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmOrdenTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                FrmOrdenTrabajo lg = new FrmOrdenTrabajo();
                lg.setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarServicios;
    private javax.swing.JButton btnBorrarServicio;
    private javax.swing.JButton btnCargarDatos;
    private javax.swing.JButton btnGuardarOrdenTrabajo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbServicios;
    private javax.swing.JButton jButton1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblAgregarServ;
    private javax.swing.JTextField txtCICli;
    private javax.swing.JTextField txtColorVeh;
    private javax.swing.JTextField txtDirCli;
    private javax.swing.JTextField txtFechRecepVeh;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtKilomVeh;
    private javax.swing.JTextField txtManoDeObra;
    private javax.swing.JTextField txtNomCli;
    private javax.swing.JTextField txtNumIdOrden;
    private javax.swing.JTextField txtOtros;
    private javax.swing.JTextField txtPlacVeh;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTipVehi;
    private javax.swing.JTextField txtTlfCli;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();

}
