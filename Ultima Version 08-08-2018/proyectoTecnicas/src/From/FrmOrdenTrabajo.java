/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package From;


import Clase.ClsProducto;
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
    public static DefaultTableModel modeloMat;
    ArrayList<ClsServicio> listServicio = new ArrayList<ClsServicio>();
     ArrayList<ClsProducto> listProducto = new ArrayList<ClsProducto>();
    double manoDeObra = 0.0;
    double otros = 0.0;
    double subTotal = 0.0;
    double iva = 0.0;
    double total = 0.0;
    int numIdOrden = 0;
    int idMaterialInterno = 0;
    int numIdOrdenServ = 0;
    int numIdOrdenProd = 0;
    String idNumOrden = "";
    
    public FrmOrdenTrabajo() {
        initComponents();
        this.setLocationRelativeTo(null);// Iniciamos la pantalla al centro
       
        llenarComboServicio();
        llenarComboMateriales();
        
        llenarComboCedulas();
                
        inicializarCampos();
        mostrarDetalleFacturaTabla();
        mostrarDetalleMaterialesOrdenTrab();
        
        
        
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
        
        //INICIALIZAR CAMPOS DE LA SECCION REGISTRAR MATERIALES
        
        txtCantidadProd.setEnabled(false);
        txtCantidadProd.setEditable(false);
        
        cmbProductoOrdenTrab.setEnabled(false);
        cmbProductoOrdenTrab.setEditable(false);
        
        btnAgregarMateriales.setEnabled(false);
        btnBorrrarMaterial.setEnabled(false);
        
        txtCorreoElect.setEnabled(false);
        txtCorreoElect.setEditable(false);
        txtCorreoElect.setText("");
        
        txtPlacVeh.setEnabled(false);
        txtPlacVeh.setEditable(false);
        txtPlacVeh.setText("");
        
        txtModelo.setEnabled(false);
        txtModelo.setEditable(false);
        txtModelo.setText("");
        
        txtTipVehi.setEnabled(false);
        txtTipVehi.setEditable(false);
        txtTipVehi.setText("");
        
        txtColorVeh.setEnabled(false);
        txtColorVeh.setEditable(false);
        txtColorVeh.setText("");
        
        cmbPlacas.setEnabled(false);
        cmbPlacas.setEditable(false);
        
        cmbCedula.setSelectedIndex(-1);
        cmbPlacas.setSelectedIndex(-1);
        
        panelDatosExtrax.setVisible(false);
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

        //INICIALIZAR CAMPOS DE LA SECCION REGISTRAR MATERIALES
        
        txtCantidadProd.setEnabled(true);
        txtCantidadProd.setEditable(true);
        
        cmbProductoOrdenTrab.setEnabled(true);
      
        
        btnAgregarMateriales.setEnabled(true);
        btnBorrrarMaterial.setEnabled(true);

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
            System.out.println("ERROR LLENAR COMBO SERVICIOS "+ex);
        }

    }
    
    public void llenarComboMateriales() {

        String[] datosServicios = new String[1];

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DESCRIPCION FROM PRODUCTO");

            while (rs.next()) {
                datosServicios[0] = rs.getString(1);
                cmbProductoOrdenTrab.addItem(datosServicios[0]);
            }

            cmbProductoOrdenTrab.setSelectedIndex(-1);

        } catch (Exception ex) {
            System.out.println("ERROR LLENAR COMBO MATERIALES" + ex);
        }

    }
    
   
    public void llenarComboCedulas(){
        String[] datosCliente = new String[3];

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT cli.RucCli,NomCli,ApelCli FROM cliente cli, vehiculo veh where cli.RucCli = veh.RucCli");

            while (rs.next()) {
                datosCliente[0] = rs.getString(1);
                datosCliente[1] = rs.getString(2);
                datosCliente[2] = rs.getString(3);
                
                cmbCedula.addItem(datosCliente[0]+" - "+datosCliente[1]+" - "+datosCliente[2]);
            } 

        } catch (Exception ex) {
            System.out.println("ERROR LLENAR COMBO CEDULA "+ex);
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
     
      public void mostrarDetalleMaterialesOrdenTrab(){
        
        modeloMat = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //declarar que las filas y columnas no sean editable
            }
            
        };
        
        modeloMat.addColumn("CANTIDAD");
        modeloMat.addColumn("COD_PRODUCTO ");
        modeloMat.addColumn("DESCRIPCION DEL PRODUCTO ");
        modeloMat.addColumn("PRECIO UNITARIO  ");
        modeloMat.addColumn("PRECIO TOTAL ");
        modeloMat.addColumn("idTrasc");
        tblAgregarMateriales.setModel(modeloMat);

    }
     
     public void calcularPrecioServicio() {
        manoDeObra = 0.0;
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
        txtSubtotal.setText(String.valueOf(subTotal));
        txtIva.setText(String.valueOf(iva));
        txtTotal.setText(String.valueOf(total));
    }
     
     public void calcularPrecioServicioMateriales() {

        otros = 0;
        for (ClsProducto prod : listProducto) {
            otros = otros + prod.getTotalMaterial();
           
        }
        
        txtOtros.setText(String.valueOf(otros));
         System.out.println("Materiales " + otros);

         
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
     
     public void numeroIdDetallServ (){
         
          try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(IdOrdenServi) FROM det_ordenservicio");
            
            while (rs.next()) {
                 numIdOrdenServ  = Integer.parseInt(rs.getString(1));
            }
            
            numIdOrdenServ  = numIdOrdenServ  + 1;
            
        } catch (Exception ex) {
            numIdOrdenServ  =  1;
            
        }
     } 
     
     public void numeroIdDetallProd (){
         
          try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(IdOrdenProducto) FROM det_ordenproducto");
            
            while (rs.next()) {
                 numIdOrdenProd  = Integer.parseInt(rs.getString(1));
            }
            
            numIdOrdenProd  = numIdOrdenProd  + 1;
            
        } catch (Exception ex) {
            numIdOrdenProd  =  1;
            
        }
     } 
     
     public int obtenerIdInternoMateria(){
         for(ClsProducto prod : listProducto){
             idMaterialInterno = prod.getIdProdInterno();
         }
         
         return idMaterialInterno;
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
     
     public String separarPlacatring (String cadena){
        String plac = "";
         System.out.println("CUAL ES LA CADENA "+cadena);
        try {
            String subCadena = cadena.substring(0,8);
            System.out.println("SUBCADE "+ subCadena);
            plac = subCadena;
           
        } catch (Exception e) {
            System.out.println("Error al separar "+e);
        }
        
        return plac;
    }
     
     public void cargarPlacas(int ced){
        
         int select = cmbCedula.getSelectedIndex();
         
         if(select != -1){
        
         String datosVehicul[] = new String [3];
         System.out.println("CED "+ced);
         
         try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT `Placa`,`Modelo`,`RucCli`FROM `vehiculo");

            while (rs.next()) {
                
                datosVehicul[0] = rs.getString(1); 
                datosVehicul[1] = rs.getString(2);
                datosVehicul[2] = rs.getString(3);
               
                
                if (datosVehicul[2].equals(String.valueOf(ced))) {
                    cmbPlacas.addItem(datosVehicul[0]+" - "+datosVehicul[1]);
                }
            }
            
            cmbPlacas.setSelectedIndex(-1);
           
            
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGAR LOS DATOS DEL VEHICULO PLACA AL COMBO" + ex);
        }
         }
     }
     
     public void cargarDetalleServicios(){
         
         for (ClsServicio serv : listServicio) {
             numeroIdDetallServ();
             String idServ = String.valueOf(serv.getIdservicio());
             
            try {
                PreparedStatement pst2 = cn.prepareStatement("INSERT INTO det_ordenservicio (IDORDENSERVI,IDSERV,IDORDEN) VALUES (?,?,?)");

                pst2.setString(1, String.valueOf(numIdOrdenServ));
                pst2.setString(2, idServ);
                pst2.setString(3, idNumOrden);
                pst2.executeUpdate();
            } catch (Exception e) {
                System.out.println("ERROR AL CARGAR LOS DATOS A DETALLE SERVICIO");
            }

        }
    }
     
      public void cargarDetalleProducto(){
         
         for (ClsProducto prod: listProducto) {
             numeroIdDetallProd();
             String idProd = String.valueOf(prod.getIdProd());
             
            try {
                PreparedStatement pst2 = cn.prepareStatement("INSERT INTO det_ordenproducto (IdOrdenProducto,IdProd,IdOrden) VALUES (?,?,?)");

                pst2.setString(1, String.valueOf(numIdOrdenProd));
                pst2.setString(2, idProd);
                pst2.setString(3, idNumOrden);
                pst2.executeUpdate();
                
            } catch (Exception e) {
                System.out.println("ERROR AL CARGAR LOS DATOS A DETALLE PRODUCTO");
            }

        }
    }
     
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNomCli = new javax.swing.JTextField();
        txtDirCli = new javax.swing.JTextField();
        txtTlfCli = new javax.swing.JTextField();
        txtCICli = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbCedula = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        txtCorreoElect = new javax.swing.JTextField();
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
        txtModelo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cmbPlacas = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNumIdOrden = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtManoDeObra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtOtros = new javax.swing.JTextField();
        btnGuardarOrdenTrabajo = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCantidadProd = new javax.swing.JTextField();
        cmbProductoOrdenTrab = new javax.swing.JComboBox<>();
        btnAgregarMateriales = new javax.swing.JButton();
        btnBorrrarMaterial = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAgregarMateriales = new javax.swing.JTable();
        btnCargarDatos = new javax.swing.JButton();
        btnEditarDatos = new javax.swing.JButton();
        panelDatosExtrax = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();

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

        jLabel3.setText("DIRECCION:");

        jLabel4.setText("TELEFONO:");

        jLabel2.setText("C.I CLIENTE: ");

        cmbCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCedulaActionPerformed(evt);
            }
        });

        jLabel20.setText("CORREO: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(51, 51, 51)
                        .addComponent(txtNomCli, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                    .addComponent(cmbCedula, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel20))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCICli)
                            .addComponent(txtDirCli)
                            .addComponent(txtTlfCli, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCorreoElect))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNomCli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCICli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDirCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTlfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(txtCorreoElect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
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

        jLabel21.setText("MODELO");

        cmbPlacas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPlacasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(txtFechRecepVeh)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(txtKilomVeh))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTipVehi, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(txtPlacVeh))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtColorVeh, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(txtModelo))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(57, 57, 57))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(cmbPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 158, Short.MAX_VALUE))
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
                    .addComponent(jLabel9)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKilomVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlacVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cmbPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jLabel5.setText("ORDEN N°:");

        txtNumIdOrden.setText("00000");

        jLabel11.setText("MANO DE OBRA");

        jLabel12.setText("MATERIALES: ");

        btnGuardarOrdenTrabajo.setText("GUARDAR ORDEN ");
        btnGuardarOrdenTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarOrdenTrabajoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(28, 28, 28)
                        .addComponent(txtManoDeObra, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(btnGuardarOrdenTrabajo)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtManoDeObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtOtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnGuardarOrdenTrabajo)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
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
                .addGap(74, 74, 74)
                .addComponent(btnAgregarServicios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(btnBorrarServicio)
                .addGap(50, 50, 50))
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
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
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

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setText("Ingrese la cantidad: ");

        jLabel19.setText("Seleccione el Producto: ");

        cmbProductoOrdenTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductoOrdenTrabActionPerformed(evt);
            }
        });

        btnAgregarMateriales.setText("Agregar");
        btnAgregarMateriales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMaterialesActionPerformed(evt);
            }
        });

        btnBorrrarMaterial.setText("Borrar");
        btnBorrrarMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrrarMaterialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(txtCantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(cmbProductoOrdenTrab, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(btnAgregarMateriales)
                .addGap(30, 30, 30)
                .addComponent(btnBorrrarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbProductoOrdenTrab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarMateriales)
                            .addComponent(btnBorrrarMaterial))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tblAgregarMateriales.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblAgregarMateriales);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("INGRESO DE MATERIALES UTILIZADOS", jPanel11);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCargarDatos.setText("CARGAR DATO");
        btnCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarDatosActionPerformed(evt);
            }
        });

        btnEditarDatos.setText("EDITAR DATOS");
        btnEditarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDatosActionPerformed(evt);
            }
        });

        jLabel22.setText("SUBTOTAL: ");

        jLabel15.setText("IVA 12%");

        jLabel14.setText("TOTAL");

        javax.swing.GroupLayout panelDatosExtraxLayout = new javax.swing.GroupLayout(panelDatosExtrax);
        panelDatosExtrax.setLayout(panelDatosExtraxLayout);
        panelDatosExtraxLayout.setHorizontalGroup(
            panelDatosExtraxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosExtraxLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelDatosExtraxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(panelDatosExtraxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(txtIva)
                    .addComponent(txtSubtotal))
                .addContainerGap())
        );
        panelDatosExtraxLayout.setVerticalGroup(
            panelDatosExtraxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosExtraxLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelDatosExtraxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosExtraxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosExtraxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(341, 341, 341)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addComponent(btnCargarDatos)
                                        .addGap(51, 51, 51)
                                        .addComponent(btnEditarDatos))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(panelDatosExtrax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNumIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegresar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCargarDatos)
                            .addComponent(btnEditarDatos)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDatosExtrax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                
                idNumOrden = txtNumIdOrden.getText();
                String rucCli = txtCICli.getText();
                String placa = txtPlacVeh.getText();
                String fechaRecep = txtFechRecepVeh.getText();
                String kilom = txtKilomVeh.getText();
                String manoObra = txtManoDeObra.getText();
                String otros = txtOtros.getText();
                String subtotal = txtSubtotal.getText();
                String iva = txtIva.getText();
                String total = txtTotal.getText();
                String facturado = "";

                try {

                    PreparedStatement pst = cn.prepareStatement("INSERT INTO ORDEN_TRABAJO (IDORDEN,RUCCLI,PLACA,FECHAHORA_RECEPCION,KILOMETRAJE,MANO_DE_OBRA,OTROS,SUBTOTAL,IVA,TOTAL,FACTURADO) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

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
                    pst.setString(11, "NO");
                    
                    if (listServicio.size() != 0) {
                        pst.executeUpdate();
                        
                        cargarDetalleServicios();
                        cargarDetalleProducto();
                        
                        JOptionPane.showMessageDialog(rootPane, "ORDEN REGISTRADA EXITOSAMENTE");
                        inicializarCampos();
                        cmbServicios.removeAllItems();
                        llenarComboServicio();

                        for (int i = 0; i < listServicio.size(); i++) {
                            modelo.removeRow(0);
                        }
                        
                        for (int i = 0; i < listProducto.size(); i++) {
                            modeloMat.removeRow(0);
                        }
                        
                        
                        listServicio.removeAll(listServicio);
                        listProducto.removeAll(listProducto);
                       
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "**NO A REGISTRADO NINGUN SERVICO O PRODUCTO**");
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

    private void btnAgregarMaterialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMaterialesActionPerformed
        
        String cantidad = txtCantidadProd.getText();
        int numSelectProducto = cmbProductoOrdenTrab.getSelectedIndex();
        
        if(cantidad.equals("") || numSelectProducto == -1  ){
            JOptionPane.showMessageDialog(rootPane, " !LA CANTIDAD O LA SELECCION DE PRODUCTOS INCORRECTOS O FALTANTES! ");
        }else{
            String productoString = (String) cmbProductoOrdenTrab.getSelectedItem();

            try {

                String datosProdcto[] = new String [6];
                
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT IdProd, Descripcion,PrecioUnit  FROM producto");

                while (rs.next()) {
                    datosProdcto[0] = cantidad;
                    datosProdcto[1] = rs.getString(1); //idProd
                    datosProdcto[2] = rs.getString(2); //descripcion
                    datosProdcto[3] = rs.getString(3); //PrecioUnit
                    datosProdcto[4] = String.valueOf(Integer.parseInt(cantidad) *  Double.parseDouble(datosProdcto[3]));//total
//int IdProd, String Descripcion, int Cantidad, double PrecioUnit, int idProdInterno, double totalMaterial

                    if (datosProdcto[2].equals(productoString)) {
                        datosProdcto[5] = String.valueOf(obtenerIdInternoMateria()+1);
                        //ClsProducto producto = new ClsProducto(Integer.parseInt(datosProdcto[1]),  datosProdcto[2] , Double.parseDouble(datosProdcto[3]), Integer.parseInt(datosProdcto[5]),Double.parseDouble(datosProdcto[4]);
                        ClsProducto producto = new ClsProducto(Integer.parseInt(datosProdcto[1]), datosProdcto[2], Integer.parseInt(datosProdcto[0]) , Double.parseDouble(datosProdcto[3]) , Integer.parseInt(datosProdcto[5]), Double.parseDouble(datosProdcto[4]));
                        {
                            listProducto.add(producto);
                            modeloMat.addRow(datosProdcto);
                        }

                    }
                }
                
                System.out.println("list "+listProducto);
                calcularPrecioServicioMateriales();
                txtCantidadProd.setText("");
                cmbProductoOrdenTrab.setSelectedIndex(-1);

            } catch (Exception e) {
                System.out.println("e"+e);
            }
   
        }
        
        
    }//GEN-LAST:event_btnAgregarMaterialesActionPerformed

    private void btnBorrrarMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrrarMaterialActionPerformed
        
        System.out.println("LISTA " + listProducto);
        int filaselect = tblAgregarMateriales.getSelectedRow();
        System.out.println("fila " + filaselect);
        String idMaterial = (String) tblAgregarMateriales.getValueAt(filaselect, 5);
        int posProd = 0;
        int postProdBorar = 0;
        System.out.println("idMa "+idMaterial);

        

        for (ClsProducto prod : listProducto) {
            if (prod.getIdProdInterno() == Integer.parseInt(idMaterial)) {
                postProdBorar = posProd;
            }
            
            posProd = posProd+1;

        }
        modeloMat.removeRow(filaselect);
        listProducto.remove(postProdBorar);
        postProdBorar = 0;   
        System.out.println("Produ "+listProducto);
        calcularPrecioServicioMateriales();
        txtCantidadProd.setText("");
    }//GEN-LAST:event_btnBorrrarMaterialActionPerformed

    private void cmbCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCedulaActionPerformed
    
        cmbPlacas.removeAllItems();
        
        int select = cmbCedula.getSelectedIndex();
        //System.out.println("Index " + select);

        if (select != -1) {

            String datosClienteFact[] = new String[6];

            try {

                String datosCliente = (String) cmbCedula.getSelectedItem();
                //System.out.println("SERVICIO: " + datosCliente);
                boolean verificar = false;
                int ced = separarCedString(datosCliente);

                //System.out.println("DATOS DEL CLIENTE : " + ced);

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
                        
                        txtCICli.setText(datosClienteFact[1]);
                        txtDirCli.setText(datosClienteFact[2]);
                        txtTlfCli.setText(datosClienteFact[3]);
                        txtCorreoElect.setText(datosClienteFact[4]);
                        txtNomCli.setText(datosClienteFact[0]+ " "+datosClienteFact[5]);
                    }
                    
                    txtNomCli.setEnabled(true);
                    txtCICli.setEnabled(true);
                    txtDirCli.setEnabled(true);
                    txtTlfCli.setEnabled(true);
                    txtCorreoElect.setEnabled(true);
                }
                cargarPlacas(ced);
      
                txtPlacVeh.setText("");
                txtModelo.setText("");
                txtTipVehi.setText("");
                txtColorVeh.setText("");
                
                cmbPlacas.setEnabled(true);
                
                txtPlacVeh.setEnabled(false);
                txtModelo.setEnabled(false);
                txtTipVehi.setEnabled(false);
                txtColorVeh.setEnabled(false);
                txtFechRecepVeh.setEnabled(false);
                txtKilomVeh.setEnabled(false);
                
            } catch (Exception ex) {
                System.out.println("ERROR AL CARGAR LOS DATOS DEL CMBCEDULA " + ex);
            }
        }


            
        
    }//GEN-LAST:event_cmbCedulaActionPerformed

    private void cmbPlacasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPlacasActionPerformed
        
       
        int select = cmbPlacas.getSelectedIndex();
        System.out.println("INT "+select);
        
        if(select != -1){
        try {
            
            String datosVehicuString = (String) cmbPlacas.getSelectedItem();
//            System.out.println("YA ESTA "+datosVehicuString);
//            int select = cmbPlacas.getSelectedIndex();
            ///System.out.println("Index " + select);

            String datosVehicul[] = new String[5];

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `vehiculo`");

            System.out.println("DATOS VEHICULO " + datosVehicuString);

            String plac = separarPlacatring(datosVehicuString);
            
            System.out.println("LA PLACA ES "+plac);

            while (rs.next()) {
                
                datosVehicul[0] = rs.getString(1);//plac
                datosVehicul[1] = rs.getString(2);//tipo
                datosVehicul[2] = rs.getString(3);//modelo
                datosVehicul[3] = rs.getString(4);//color
                datosVehicul[4] = rs.getString(5);//ruc
                
                System.out.println("PLACA NBAJBSJ"+datosVehicul[0]);

                if (datosVehicul[0].equals(plac)) {
                   
                    txtPlacVeh.setText(datosVehicul[0]);
                    txtTipVehi.setText(datosVehicul[1]);
                    txtModelo.setText(datosVehicul[2]);
                    txtColorVeh.setText(datosVehicul[3]);

                }
                
                txtPlacVeh.setEnabled(true);
                txtTipVehi.setEnabled(true);
                txtModelo.setEnabled(true);
                txtColorVeh.setEnabled(true);
                
                txtKilomVeh.setEditable(true);
                
            }
            
            txtKilomVeh.setEnabled(true);


        } catch (Exception ex) {
            System.out.println("ERROR CARGAR DATOS DE LA PLACA" + ex);
        }
        
        }
    }//GEN-LAST:event_cmbPlacasActionPerformed

    private void btnCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarDatosActionPerformed
        String ced = (String)cmbCedula.getSelectedItem();
        String placa = (String)cmbPlacas.getSelectedItem();
        int selectCed = cmbCedula.getSelectedIndex();
        int selectPlac = cmbPlacas.getSelectedIndex();
        
        if(selectCed != -1 && selectPlac != -1){
            try {
                 
                int c = separarCedString(ced);
                String p = separarPlacatring(placa);
                
                String fecha = "";
                
                Statement st = cn.createStatement();
                ResultSet rs2 = st.executeQuery("SELECT CURDATE()");

                while (rs2.next()) {
                    fecha = rs2.getString(1);
                }
                
                numeroOrdenTrab();

                txtFechRecepVeh.setText(fecha);
                    
                if(txtKilomVeh.getText().equals("")){
                    JOptionPane.showMessageDialog(rootPane, "INGRESE EL KILOOMETRAJE DEL VEHICULO");
                }else{
                     cmbCedula.setEnabled(false);
                     cmbPlacas.setEnabled(false);
                     
                     txtKilomVeh.setEditable(false);
                     
                     cargarCampos();
                }
                
            } catch (Exception e) {
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "SELECCIONE LA PLACA DEL VEHICULO O DATOS DEL CLIENTE");
        }
       
        
       
    }//GEN-LAST:event_btnCargarDatosActionPerformed

    private void btnEditarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDatosActionPerformed
       inicializarCampos();
       cmbCedula.setEnabled(true);
        System.out.println("LISTA SERVICIOS "+listServicio);
        System.out.println("LISTA SERVICIOS "+listProducto);
       if(listServicio.size()>0){
        for(int i=0;i<listServicio.size();i++){
            modelo.removeRow(0);
        }
       }
       
       listServicio.clear();
       
       txtCantidadProd.setText("");
       cmbProductoOrdenTrab.setSelectedIndex(-1);
       
       if(listProducto.size()>0){
        for(int i=0;i<listProducto.size();i++){
            modeloMat.removeRow(0);
        }
       }
       
       listProducto.clear();
       
       cmbServicios.removeAllItems();
       llenarComboServicio();
        System.out.println("-----------------");
       System.out.println("LISTA SERVICIOS "+listServicio);
        System.out.println("LISTA SERVICIOS "+listProducto);
    }//GEN-LAST:event_btnEditarDatosActionPerformed

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

    private void cmbServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbServiciosActionPerformed

    }//GEN-LAST:event_cmbServiciosActionPerformed

    private void cmbProductoOrdenTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductoOrdenTrabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProductoOrdenTrabActionPerformed

    
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JButton btnAgregarMateriales;
    private javax.swing.JButton btnAgregarServicios;
    private javax.swing.JButton btnBorrarServicio;
    private javax.swing.JButton btnBorrrarMaterial;
    private javax.swing.JButton btnCargarDatos;
    private javax.swing.JButton btnEditarDatos;
    private javax.swing.JButton btnGuardarOrdenTrabajo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbCedula;
    private javax.swing.JComboBox<String> cmbPlacas;
    private javax.swing.JComboBox<String> cmbProductoOrdenTrab;
    private javax.swing.JComboBox<String> cmbServicios;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel panelDatosExtrax;
    private javax.swing.JTable tblAgregarMateriales;
    private javax.swing.JTable tblAgregarServ;
    private javax.swing.JTextField txtCICli;
    private javax.swing.JTextField txtCantidadProd;
    private javax.swing.JTextField txtColorVeh;
    private javax.swing.JTextField txtCorreoElect;
    private javax.swing.JTextField txtDirCli;
    private javax.swing.JTextField txtFechRecepVeh;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtKilomVeh;
    private javax.swing.JTextField txtManoDeObra;
    private javax.swing.JTextField txtModelo;
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
