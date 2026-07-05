/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_final_casafolclore.GUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import proyecto_final_casafolclore.BaseDatos.conexionBD;

/**
 *
 * @author OS
 */
public class GUI_reserva extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GUI_reserva.class.getName());
    private String origen;
    
    
    
    /**
     * Creates new form GUI_reserva
     */
    public GUI_reserva(String origen) {
        initComponents();
        this.origen= origen;
        setSize(750, 560); //tamaño
        setLocationRelativeTo(null); //centrado
        setResizable(false); //no deja maximizar, mas rapido aqui
        mostrarFechaActual();
        mostrarFechaFin();
        
        cargarTrajes();
    }
    public GUI_reserva() { //este constructor es para que regrese a determinado menu dependiendo de quien inicio sesion
        initComponents();
        this.origen = origen;
        setSize(750, 560); //tamaño
        setLocationRelativeTo(null); //centrado
        setResizable(false); //no deja maximizar, mas rapido aqui
        mostrarFechaActual();
        mostrarFechaFin();
        cargarTrajes();
        
    }
    public void mostrarFechaActual() {
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        jTextField8.setText(hoy.format(formato));
    }
    
    public void mostrarFechaFin() {
        LocalDate hoy = LocalDate.now();
        LocalDate fin = hoy.plusDays(3); //esta funcion es para sumar porque con el + no se puede en fecha qwq
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        jTextField9.setText(fin.format(formato));
    }
    
    public void cargarTrajes() {
        
    cbTrajes.removeAllItems(); 
    cbTrajes.addItem("Seleccione un traje...");

    String sql = "SELECT DISTINCT nombre_traje FROM traje"; 

    // Blindamos la conexión para que se cierre automáticamente
    try (java.sql.Connection con = conexionBD.getConexion()) {
        
        if (con == null) return;
        
        try (java.sql.Statement st = con.createStatement();
             java.sql.ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre_traje");
                System.out.println("Traje encontrado en BD: " + nombre);
                cbTrajes.addItem(nombre); 
            }
        } // Aquí se cierran automáticamente rs y st

    } catch (Exception e) {
        System.out.println("Error al cargar los trajes: " + e.getMessage());
    }
}
    
    
    private void buscarYAlimentarTraje() {
    if (cbTrajes.getSelectedIndex() <= 0 || cbTalla.getSelectedIndex() < 0 || cbGenero.getSelectedIndex() < 0) {
        txtIdTraje.setText("");
        txtMontoAlquiler.setText("");
        return;
    }
    
    String nombreTraje = cbTrajes.getSelectedItem().toString();
    String talla = cbTalla.getSelectedItem().toString();
    String para = cbGenero.getSelectedItem().toString();
    
    proyecto_final_casafolclore.BaseDatos.reservaBD controlReserva = new proyecto_final_casafolclore.BaseDatos.reservaBD();
    String[] datosTraje = controlReserva.obtenerDatosTraje(nombreTraje, talla, para);
    
    if (datosTraje != null) {
        // Si coincide, se llenan los campos automáticamente en image_745483.png
        txtIdTraje.setText(datosTraje[0]);
        txtMontoAlquiler.setText(datosTraje[1]);
    } else {
        // Si no coincide, solo limpiamos los campos SILENCIOSAMENTE sin mandar alertas
        txtIdTraje.setText("");
        txtMontoAlquiler.setText("");
    }
}
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnlPantalla = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtnDocumento = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtIdTraje = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txtMontoAlquiler = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        cbTalla = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbTrajes = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbTDoc = new javax.swing.JComboBox<>();
        lblGenero = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox<>();

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Cliente ID:");

        jPanel4.setBackground(new java.awt.Color(249, 241, 229));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(137, 124, 104), 4));

        jTextField4.setBackground(new java.awt.Color(245, 217, 194));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto_final_casafolclore/GUI/Imagenes/franja.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlPantalla.setBackground(new java.awt.Color(255, 240, 204));

        jPanel1.setBackground(new java.awt.Color(204, 102, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setBackground(new java.awt.Color(249, 241, 229));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RESERVA");

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(73, 43, 12));
        jLabel9.setText("Fecha de inicio de la reserva:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(73, 43, 12));
        jLabel10.setText("Fecha de fin de la reserva:");

        jPanel2.setBackground(new java.awt.Color(249, 241, 229));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(137, 124, 104), 4));

        txtnDocumento.setBackground(new java.awt.Color(245, 217, 194));
        txtnDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnDocumento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnDocumentoFocusLost(evt);
            }
        });
        txtnDocumento.addActionListener(this::txtnDocumentoActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtnDocumento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtnDocumento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(249, 241, 229));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(137, 124, 104), 4));

        txtIdTraje.setEditable(false);
        txtIdTraje.setBackground(new java.awt.Color(245, 217, 194));
        txtIdTraje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdTraje.setEnabled(false);
        txtIdTraje.addActionListener(this::txtIdTrajeActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtIdTraje)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtIdTraje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel7.setBackground(new java.awt.Color(249, 241, 229));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(137, 124, 104), 4));

        txtMontoAlquiler.setEditable(false);
        txtMontoAlquiler.setBackground(new java.awt.Color(245, 217, 194));
        txtMontoAlquiler.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoAlquiler.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtMontoAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtMontoAlquiler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel8.setBackground(new java.awt.Color(249, 241, 229));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(137, 124, 104), 4));

        jTextField8.setBackground(new java.awt.Color(245, 217, 194));
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
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(137, 124, 104), 4));

        jTextField9.setBackground(new java.awt.Color(245, 217, 194));
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

        cbTalla.setBackground(new java.awt.Color(137, 124, 104));
        cbTalla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "XL" }));
        cbTalla.addActionListener(this::cbTallaActionPerformed);

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setFont(new java.awt.Font("Artifakt Element Black", 1, 14)); // NOI18N
        jButton1.setText("CONFIRMAR RESERVA");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setBackground(new java.awt.Color(105, 95, 79));
        jButton2.setFont(new java.awt.Font("Artifakt Element Black", 1, 14)); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto_final_casafolclore/GUI/Imagenes/nari.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto_final_casafolclore/GUI/Imagenes/lliama.png"))); // NOI18N

        cbTrajes.setBackground(new java.awt.Color(137, 124, 104));
        cbTrajes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTrajes.addActionListener(this::cbTrajesActionPerformed);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(73, 43, 12));
        jLabel14.setText("Tipo de documento");

        cbTDoc.setBackground(new java.awt.Color(137, 124, 104));
        cbTDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CE" }));
        cbTDoc.addActionListener(this::cbTDocActionPerformed);

        lblGenero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGenero.setForeground(new java.awt.Color(73, 43, 12));
        lblGenero.setText("Para:");

        cbGenero.setBackground(new java.awt.Color(137, 124, 104));
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Varón", "Mujer" }));
        cbGenero.addActionListener(this::cbGeneroActionPerformed);

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
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(80, 80, 80)
                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPantallaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlPantallaLayout.setVerticalGroup(
            pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPantallaLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPantallaLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTDoc)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(4, 4, 4)
                        .addComponent(cbTrajes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblGenero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPantallaLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlPantallaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel13)
                        .addContainerGap(131, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
         if (origen.equals("admin")) 
         {
            new MenuAdmin().setVisible(true);
         } 
         else if (origen.equals("cliente")) 
         {
            new MenuCliente().setVisible(true);
         }
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      String documento = txtnDocumento.getText().trim();
    
    // Validaciones básicas de campos vacíos
    if (documento.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de documento.", "Campos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (cbTrajes.getSelectedIndex() <= 0 || cbTalla.getSelectedIndex() < 0 || cbGenero.getSelectedIndex() < 0) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione el Nombre, Talla y Género del traje.", "Campos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
        return; 
    }

    // ARREGLADO: Ahora manejamos el ID como String ("T0015") en vez de int
    String idTraje = txtIdTraje.getText().trim();
    if (idTraje.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "No se ha podido recuperar el ID del traje. Verifique los datos.", "Error de Coincidencia", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Consultas SQL utilizando VARCHAR/CHAR para el id_traje
    String sqlBuscarEstado = "SELECT estado FROM traje WHERE id_traje = ?";
    String sqlActualizarEstado = "UPDATE traje SET estado = 'Reservado' WHERE id_traje = ?";

    try (java.sql.Connection con = conexionBD.getConexion();
         java.sql.PreparedStatement pstBuscar = con.prepareStatement(sqlBuscarEstado)) {
        
        if (con == null) return;
        
        // 1. Verificar el estado actual del traje en la BD usando setString
        pstBuscar.setString(1, idTraje);
        try (java.sql.ResultSet rs = pstBuscar.executeQuery()) {
            if (rs.next()) {
                String estadoActual = rs.getString("estado");
                
                if ("Alquilado".equalsIgnoreCase(estadoActual)) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El traje seleccionado está Alquilado. No se puede reservar.", "Traje no disponible", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if ("Reservado".equalsIgnoreCase(estadoActual)) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Este traje ya se encuentra Reservado.", "Traje no disponible", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }

        // 2. Si está disponible, procedemos a cambiar el estado a 'Reservado'
        try (java.sql.PreparedStatement pstActualizar = con.prepareStatement(sqlActualizarEstado)) {
            pstActualizar.setString(1, idTraje); // Usamos setString aquí también
            int filasAfectadas = pstActualizar.executeUpdate();
            
            if (filasAfectadas > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "¡Reserva confirmada! El estado del traje ha cambiado a 'Reservado'.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                
                // Limpia la pantalla o regresa usando tu botón cancelar
                jButton2ActionPerformed(null); 
            }
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al procesar la reserva: " + e.getMessage(), "Error de Base de Datos", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIdTrajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTrajeActionPerformed
        // este codigo debe generarse automatico al el nombre del traje y la talla, esto igual que el dinero
        //quizas cambiar con combo box
    }//GEN-LAST:event_txtIdTrajeActionPerformed

    private void txtnDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnDocumentoActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_txtnDocumentoActionPerformed

    private void cbTrajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTrajesActionPerformed
        // 1. Si se selecciona la opción por defecto, limpiamos y salimos
        if (cbTrajes.getSelectedIndex() <= 0) {
            cbTalla.removeAllItems();
            txtIdTraje.setText("");
            txtMontoAlquiler.setText("");
            return;
        }

        String trajeSeleccionado = cbTrajes.getSelectedItem().toString();

        // 2. Limpiamos el combo de tallas para llenarlo con las correctas
        cbTalla.removeAllItems();

        // 3. Consultamos a la BD las tallas que pertenecen a este traje
        String sql = "SELECT DISTINCT talla FROM traje WHERE nombre_traje = ?";

        try (java.sql.Connection con = conexionBD.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

            if (con == null) return;

            pst.setString(1, trajeSeleccionado);

            try (java.sql.ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    cbTalla.addItem(rs.getString("talla"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al filtrar las tallas de forma dinámica: " + e.getMessage());
        }

        // 4. Ejecutamos tu método para intentar recalcular ID y Monto de inmediato
        buscarYAlimentarTraje();
        
    }//GEN-LAST:event_cbTrajesActionPerformed

    private void cbTDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTDocActionPerformed
    private String nombreClienteActual = "";
    private void txtnDocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnDocumentoFocusLost
       String documento = txtnDocumento.getText().trim(); 

    if (documento.isEmpty()) {
        return;
    }

    proyecto_final_casafolclore.BaseDatos.reservaBD controlReserva = new proyecto_final_casafolclore.BaseDatos.reservaBD();
    
    nombreClienteActual = controlReserva.obtenerNombreCliente(documento);

    if (nombreClienteActual == null) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "El número de documento ingresado no corresponde a ningún cliente registrado.\nPor favor, verifíquelo o registre al cliente primero.", 
            "Cliente no encontrado", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        
        txtnDocumento.setText(""); 
        txtnDocumento.requestFocus(); 
    } else {
       
        System.out.println("Cliente validado con éxito: " + nombreClienteActual);
    }
    }//GEN-LAST:event_txtnDocumentoFocusLost

    private void cbTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTallaActionPerformed
        buscarYAlimentarTraje();
        
    }//GEN-LAST:event_cbTallaActionPerformed

    private void cbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGeneroActionPerformed
        buscarYAlimentarTraje();
    }//GEN-LAST:event_cbGeneroActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new GUI_reserva().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbGenero;
    private javax.swing.JComboBox<String> cbTDoc;
    private javax.swing.JComboBox<String> cbTalla;
    private javax.swing.JComboBox<String> cbTrajes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JPanel pnlPantalla;
    private javax.swing.JTextField txtIdTraje;
    private javax.swing.JTextField txtMontoAlquiler;
    private javax.swing.JTextField txtnDocumento;
    // End of variables declaration//GEN-END:variables
}
