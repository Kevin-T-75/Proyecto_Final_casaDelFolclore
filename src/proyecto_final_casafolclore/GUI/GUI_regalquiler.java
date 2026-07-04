  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_final_casafolclore.GUI;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


import proyecto_final_casafolclore.BaseDatos.conexionBD;


/**
 *
 * @author SAHIR
 */
public class GUI_regalquiler extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GUI_regalquiler.class.getName());

    /**
     * Creates new form GUI_regalquiler
     */
    public GUI_regalquiler() {
        initComponents();
        generarIdCorrelativo();
        mostrarClientesEnTabla();
        cargarTrajesA();
        this.getContentPane().setBackground(new java.awt.Color(249, 241, 229));
        
        this.setLocationRelativeTo(null);
        
            java.util.Date hoy = new java.util.Date();
    java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
    String fechaActual = formato.format(hoy);
    
   
    cargar_nombres_cbTrajes();
    
    }
    public void mostrarClientesEnTabla() {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
        modelo.addColumn("DNI/Nro Doc");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Correo");
        
        
        String sql = "SELECT nro_documento, nombre, apellido_paterno, apellido_materno, telefono, correo FROM clientes";
        String[] datos = new String[5];
        
        try {
            java.sql.Connection con = conexionBD.getConexion();
            java.sql.Statement st = con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                datos[0] = rs.getString("nro_documento");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("apellido_paterno") + " " + rs.getString("apellido_materno");
                datos[3] = rs.getString("telefono");
                datos[4] = rs.getString("correo");
                
                modelo.addRow(datos);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error al cargar la tabla en regalquiler: " + e.getMessage());
        }
    }
        public void cargarTrajesA() {
        
    String sql = "SELECT nombre_traje FROM traje"; 

    try {
        java.sql.Connection con = conexionBD.getConexion();
        
        java.sql.Statement st = con.createStatement();
        
        java.sql.ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            while (rs.next()) {
    String nombre = rs.getString("nombre_traje");
    System.out.println("Traje encontrado en BD: " + nombre); // <-- AGREGA ESTO
    
        }
                    
        }

        rs.close();
        st.close();

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar los trajes: " + e.getMessage());
        e.printStackTrace();
    }
}
        
     public void buscarClienteXDocumento(String nroDoc) {
    String sql = "SELECT tipo_documento, nombre, apellido_paterno FROM clientes WHERE nro_documento = ?";
    
    Connection con = conexionBD.getConexion();
    if (con == null) {
        return;
    }

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, nroDoc);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String tipoDocBD = rs.getString("tipo_documento");
                String nombre_ap = rs.getString("nombre") + " " + rs.getString("apellido_paterno");
                
                System.out.println("Cliente encontrado: " + nombre_ap);
                
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente: " + nombre_ap);
                
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jLabel_nomb.setText(nombre_ap);
                        jLabel_nomb.revalidate();
                        jLabel_nomb.repaint();
                    }
                });
                
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente no encontrado");
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al buscar cliente: " + e.getMessage());
    }
}
        
        
        
        
        
        public void cargarTallasTraje(String nombreTraje)
        {       
            cbTalla.removeAllItems();
            cbTalla.addItem("Selecciona");
            
            String sql = "SELECT DISTINCT talla FROM traje WHERE nombre_traje = ? ";
            
            try (Connection con = conexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, nombreTraje);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cbTalla.addItem(rs.getString("talla"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar tallas: " + e.getMessage());
    }
        }
        
    public void cargarGeneroXtraje_talla(String nombreTraje, String tallaTraje) {
    cbGenero.removeAllItems();
    cbGenero.addItem("Selecciona");
    
    String sql = "SELECT DISTINCT genero FROM traje WHERE nombre_traje = ? AND talla = ?";
    Connection con = conexionBD.getConexion();

    if (con == null) return;

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, nombreTraje);
        ps.setString(2, tallaTraje);
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cbGenero.addItem(rs.getString("genero"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar géneros: " + e.getMessage());
    }
    
    }

   public void mostrarID_Monto(String nombre, String talla, String genero) {
    
    String sql = "SELECT id_traje, precio_traje FROM traje WHERE nombre_traje = ? AND talla = ? AND genero = ?";
    
    try (Connection con = conexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        if (con == null) return; // Si la conexión falló, cancela discretamente

        ps.setString(1, nombre);
        ps.setString(2, talla);
        ps.setString(3, genero);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String idEncontrado = rs.getString("id_traje");
                double montoEncontrado = rs.getDouble("precio_traje");

                // Pintamos los datos en la interfaz gráfica de forma segura
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        txt_idTraje.setText(idEncontrado);
                        txt_monto.setText(String.valueOf(montoEncontrado));
                    }
                });
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener datos finales: " + e.getMessage());
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

        jLabel9 = new javax.swing.JLabel();
        pnlPantalla = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtaDocumento = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_idTraje = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txt_monto = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        cbTalla = new javax.swing.JComboBox<>();
        btnCAlquiler = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbTrajes = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cbTDocu = new javax.swing.JComboBox<>();
        lblGenero = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox<>();
        jLabel_nomb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(249, 241, 229));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto_final_casafolclore/GUI/Imagenes/franja.png"))); // NOI18N
        jLabel9.setText("jLabel1");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlPantalla.setBackground(new java.awt.Color(205, 226, 234));

        jPanel1.setBackground(new java.awt.Color(136, 200, 232));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setBackground(new java.awt.Color(249, 241, 229));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ALQUILER");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto_final_casafolclore/GUI/Imagenes/iconponc.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(73, 43, 12));
        jLabel3.setText("Nro. de documento");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(73, 43, 12));
        jLabel4.setText("ID Traje");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(73, 43, 12));
        jLabel6.setText("Nombre Traje:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(73, 43, 12));
        jLabel7.setText("Talla (Seleccionar):");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(73, 43, 12));
        jLabel8.setText("Monto de alquiler:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(73, 43, 12));
        jLabel10.setText("Fecha de inicio de la reserva:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(73, 43, 12));
        jLabel11.setText("Fecha de fin de la reserva:");

        jPanel2.setBackground(new java.awt.Color(249, 241, 229));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(136, 200, 232), 4));

        txtaDocumento.setBackground(new java.awt.Color(214, 244, 255));
        txtaDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtaDocumento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtaDocumentoFocusLost(evt);
            }
        });
        txtaDocumento.addActionListener(this::txtaDocumentoActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtaDocumento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtaDocumento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(249, 241, 229));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(136, 200, 232), 4));

        txt_idTraje.setEditable(false);
        txt_idTraje.setBackground(new java.awt.Color(214, 244, 255));
        txt_idTraje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idTraje.setEnabled(false);
        txt_idTraje.addActionListener(this::txt_idTrajeActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_idTraje)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_idTraje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel7.setBackground(new java.awt.Color(249, 241, 229));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(136, 200, 232), 4));

        txt_monto.setEditable(false);
        txt_monto.setBackground(new java.awt.Color(214, 244, 255));
        txt_monto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_monto.setEnabled(false);
        txt_monto.addActionListener(this::txt_montoActionPerformed);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_monto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel8.setBackground(new java.awt.Color(249, 241, 229));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(136, 200, 232), 4));

        jTextField8.setBackground(new java.awt.Color(214, 244, 255));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.addActionListener(this::jTextField8ActionPerformed);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField8)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.setBackground(new java.awt.Color(249, 241, 229));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(136, 200, 232), 4));

        jTextField9.setBackground(new java.awt.Color(214, 244, 255));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.addActionListener(this::jTextField9ActionPerformed);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        cbTalla.setBackground(new java.awt.Color(214, 244, 255));
        cbTalla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "XL" }));
        cbTalla.addItemListener(this::cbTallaItemStateChanged);
        cbTalla.addActionListener(this::cbTallaActionPerformed);

        btnCAlquiler.setBackground(new java.awt.Color(136, 200, 232));
        btnCAlquiler.setFont(new java.awt.Font("Artifakt Element Black", 1, 14)); // NOI18N
        btnCAlquiler.setText("CONFIRMAR ALQUILER");
        btnCAlquiler.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCAlquiler.addActionListener(this::btnCAlquilerActionPerformed);

        jButton2.setBackground(new java.awt.Color(59, 105, 145));
        jButton2.setFont(new java.awt.Font("Artifakt Element Black", 1, 14)); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto_final_casafolclore/GUI/Imagenes/nari.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto_final_casafolclore/GUI/Imagenes/lliama.png"))); // NOI18N

        cbTrajes.setBackground(new java.awt.Color(214, 244, 255));
        cbTrajes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTrajes.addItemListener(this::cbTrajesItemStateChanged);
        cbTrajes.addActionListener(this::cbTrajesActionPerformed);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(73, 43, 12));
        jLabel15.setText("Tipo de documento");

        cbTDocu.setBackground(new java.awt.Color(214, 244, 255));
        cbTDocu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CE" }));
        cbTDocu.addActionListener(this::cbTDocuActionPerformed);

        lblGenero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGenero.setForeground(new java.awt.Color(73, 43, 12));
        lblGenero.setText("Para:");

        cbGenero.setBackground(new java.awt.Color(214, 244, 255));
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Varón", "Mujer" }));
        cbGenero.addItemListener(this::cbGeneroItemStateChanged);

        jLabel_nomb.setText("--");

        javax.swing.GroupLayout pnlPantallaLayout = new javax.swing.GroupLayout(pnlPantalla);
        pnlPantalla.setLayout(pnlPantallaLayout);
        pnlPantallaLayout.setHorizontalGroup(
            pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPantallaLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTalla, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(cbTrajes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPantallaLayout.createSequentialGroup()
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTDocu, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel_nomb, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnCAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlPantallaLayout.setVerticalGroup(
            pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPantallaLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPantallaLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTDocu)))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel_nomb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTrajes)
                        .addGap(18, 18, 18)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblGenero)))
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPantallaLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel14))
                            .addGroup(pnlPantallaLayout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnCAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(77, 77, 77))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtaDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaDocumentoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtaDocumentoActionPerformed

    private void txt_idTrajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idTrajeActionPerformed
        // este codigo debe generarse automatico al el nombre del traje y la talla, esto igual que el dinero
        //quizas cambiar con combo box
    }//GEN-LAST:event_txt_idTrajeActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void btnCAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAlquilerActionPerformed
        // TODO add your handling code here:
        javax.swing.JOptionPane.showMessageDialog(this,
            "¡Reserva realizada con éxito!",
            "Confirmación de Reserva",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);

        //validaciones
      
        // 2. Cerrar la ventana actual
        new MenuCliente().setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btnCAlquilerActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // boton cancelar:

    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbTrajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTrajesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTrajesActionPerformed

    private void cbTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTallaActionPerformed

    private void cbTrajesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTrajesItemStateChanged
        // TODO add your handling code here:
        
       if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        String trajeSeleccionado = cbTrajes.getSelectedItem().toString();
        
        if (!trajeSeleccionado.equals("Selecciona un traje")) {
            cargarTallasTraje(trajeSeleccionado);
        }
    }
    }//GEN-LAST:event_cbTrajesItemStateChanged

    private void cbTallaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTallaItemStateChanged

        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        String trajeSeleccionado = cbTrajes.getSelectedItem().toString();
        String tallaSeleccionada = cbTalla.getSelectedItem().toString();
        
        if (!tallaSeleccionada.equals("Selecciona") && !trajeSeleccionado.equals("Selecciona un traje")) {
            cargarGeneroXtraje_talla(trajeSeleccionado, tallaSeleccionada);
        } else {
            cbGenero.removeAllItems();
            cbGenero.addItem("Selecciona");
        }
    }

        // TODO add your handling code here:
    }//GEN-LAST:event_cbTallaItemStateChanged

    private void txt_montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_montoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_montoActionPerformed

    private void cbGeneroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbGeneroItemStateChanged
        


        // TODO add your handling code here:
        
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        String traje = cbTrajes.getSelectedItem().toString();
        String talla = cbTalla.getSelectedItem().toString();
        String genero = cbGenero.getSelectedItem().toString();
        
        if (!genero.equals("Selecciona")) {
            mostrarID_Monto(traje, talla, genero);
        } else {
            txt_idTraje.setText("");
            txt_monto.setText("");
        }
    }
    }//GEN-LAST:event_cbGeneroItemStateChanged

    private void cbTDocuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTDocuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTDocuActionPerformed

    private void txtaDocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtaDocumentoFocusLost
           // TODO add your handling code here:
          String documento = txtaDocumento.getText().trim();
    
   
    if (documento.length() == 8 || (documento.length() >= 9 && documento.length() <= 12)) {
        buscarClienteXDocumento(documento);
    } else if (!documento.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Estructura de documento inválida.\n(DNI: 8 dígitos / CE: 9 a 12 caracteres).", 
            "Formato incorrecto", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_txtaDocumentoFocusLost
    
    public void cargar_nombres_cbTrajes()
    {
        cbTrajes.removeAllItems();
        cbTrajes.addItem("Selecciona un traje");
        cbTrajes.setEditable(true);
        
        String sql = "SELECT nombre_traje FROM traje";
        
        try (Connection con = conexionBD.getConexion(); 
     PreparedStatement ps = con.prepareStatement(sql);
     ResultSet rs = ps.executeQuery()){
        
        while (rs.next()) {
            String nombre = rs.getString("nombre_traje");
            cbTrajes.addItem(nombre);
        }

        AutoCompleteDecorator.decorate(cbTrajes);

    } catch (SQLException e) {
        System.err.println("Error al cargar los trajes: " + e.getMessage());
    }
        
    }
    
    
    private void generarIdCorrelativo() {
    int cantidadActualAlquileres = 0; 
    int nuevoNumero = cantidadActualAlquileres + 1;
    String idFormateado = "T" + String.format("%03d", nuevoNumero);
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GUI_regalquiler().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCAlquiler;
    private javax.swing.JComboBox<String> cbGenero;
    private javax.swing.JComboBox<String> cbTDocu;
    private javax.swing.JComboBox<String> cbTalla;
    private javax.swing.JComboBox<String> cbTrajes;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_nomb;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JPanel pnlPantalla;
    private javax.swing.JTextField txt_idTraje;
    private javax.swing.JTextField txt_monto;
    private javax.swing.JTextField txtaDocumento;
    // End of variables declaration//GEN-END:variables
}
