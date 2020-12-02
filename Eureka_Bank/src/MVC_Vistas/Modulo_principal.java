package MVC_Vistas;

import MVC_Logica.gestion_usuario;
import com.sun.awt.AWTUtilities;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;

public final class Modulo_principal extends javax.swing.JFrame {
    
    gestion_usuario func = new gestion_usuario();
    Mensaje msj  = new  Mensaje();
    private final String CodigoUsuario;

    public String getCodigoUsuario() {
        return CodigoUsuario;
    }

    public gestion_usuario getFunc() {
        return func;
    }

    public Modulo_principal() {
        initComponents();
        setLocationRelativeTo(null);
        Shape forma = new RoundRectangle2D.Double(0, 0, getBounds().width, getBounds().height, 20, 20);
        AWTUtilities.setWindowShape(this, forma);
        CodigoUsuario=func.getCodigo();
        cargar();
        btn0.setVisible(false);
        btn1.setVisible(false);
        btn2.setVisible(false);
    }
    
    public void cargar(){
        String apaterno="", amaterno="", nombre="";
        String SQL = "select e.vch_emplpaterno, e.vch_emplmaterno, e.vch_emplnombre from empleado as e where chr_emplcodigo='"+CodigoUsuario+"'";
        try {
            Statement st = func.getCn().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                apaterno = rs.getString(1);
                amaterno = rs.getString(2);
                nombre = rs.getString(3);
            }
            jLabel1.setText("Bienvenido "+apaterno+" "+amaterno+" "+nombre+"!!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlMenu2 = new javax.swing.JPanel();
        btnGestionarTrabajadores = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        btnSucursales = new javax.swing.JButton();
        btnTransacciones = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnCerrarSesion1 = new javax.swing.JButton();
        btnCerrarSesion2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        pnlCuerpo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        etqTitulo = new javax.swing.JLabel();
        btn0 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        pnlMenu.setBackground(new java.awt.Color(33, 45, 62));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/icons8_Workstation_100px_1.png"))); // NOI18N

        pnlMenu2.setBackground(new java.awt.Color(33, 45, 62));

        btnGestionarTrabajadores.setBackground(new java.awt.Color(73, 181, 172));
        btnGestionarTrabajadores.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btnGestionarTrabajadores.setForeground(new java.awt.Color(255, 255, 255));
        btnGestionarTrabajadores.setText("Trabajadores");
        btnGestionarTrabajadores.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(255, 255, 255), java.awt.Color.white));
        btnGestionarTrabajadores.setContentAreaFilled(false);
        btnGestionarTrabajadores.setFocusPainted(false);
        btnGestionarTrabajadores.setFocusable(false);
        btnGestionarTrabajadores.setRequestFocusEnabled(false);
        btnGestionarTrabajadores.setVerifyInputWhenFocusTarget(false);
        btnGestionarTrabajadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarTrabajadoresActionPerformed(evt);
            }
        });

        btnInicio.setBackground(new java.awt.Color(73, 181, 172));
        btnInicio.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setText("Inicio");
        btnInicio.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(255, 255, 255), java.awt.Color.white));
        btnInicio.setContentAreaFilled(false);
        btnInicio.setFocusPainted(false);
        btnInicio.setFocusable(false);
        btnInicio.setRequestFocusEnabled(false);
        btnInicio.setVerifyInputWhenFocusTarget(false);
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        btnSucursales.setBackground(new java.awt.Color(73, 181, 172));
        btnSucursales.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btnSucursales.setForeground(new java.awt.Color(255, 255, 255));
        btnSucursales.setText("Sucursales");
        btnSucursales.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(255, 255, 255), java.awt.Color.white));
        btnSucursales.setContentAreaFilled(false);
        btnSucursales.setFocusPainted(false);
        btnSucursales.setFocusable(false);
        btnSucursales.setRequestFocusEnabled(false);
        btnSucursales.setVerifyInputWhenFocusTarget(false);
        btnSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSucursalesActionPerformed(evt);
            }
        });

        btnTransacciones.setBackground(new java.awt.Color(73, 181, 172));
        btnTransacciones.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btnTransacciones.setForeground(new java.awt.Color(255, 255, 255));
        btnTransacciones.setText("Transacciones");
        btnTransacciones.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(255, 255, 255), java.awt.Color.white));
        btnTransacciones.setContentAreaFilled(false);
        btnTransacciones.setFocusPainted(false);
        btnTransacciones.setFocusable(false);
        btnTransacciones.setRequestFocusEnabled(false);
        btnTransacciones.setVerifyInputWhenFocusTarget(false);
        btnTransacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaccionesActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(51, 51, 51));
        btnCerrarSesion.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("|| Cerrar Sesion");
        btnCerrarSesion.setToolTipText("");
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setFocusable(false);
        btnCerrarSesion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCerrarSesion.setOpaque(true);
        btnCerrarSesion.setRequestFocusEnabled(false);
        btnCerrarSesion.setVerifyInputWhenFocusTarget(false);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnCerrarSesion1.setBackground(new java.awt.Color(51, 51, 51));
        btnCerrarSesion1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnCerrarSesion1.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion1.setText("|| Contactanos");
        btnCerrarSesion1.setToolTipText("");
        btnCerrarSesion1.setBorder(null);
        btnCerrarSesion1.setBorderPainted(false);
        btnCerrarSesion1.setContentAreaFilled(false);
        btnCerrarSesion1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCerrarSesion1.setFocusPainted(false);
        btnCerrarSesion1.setFocusable(false);
        btnCerrarSesion1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCerrarSesion1.setOpaque(true);
        btnCerrarSesion1.setRequestFocusEnabled(false);
        btnCerrarSesion1.setVerifyInputWhenFocusTarget(false);
        btnCerrarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion1ActionPerformed(evt);
            }
        });

        btnCerrarSesion2.setBackground(new java.awt.Color(51, 51, 51));
        btnCerrarSesion2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnCerrarSesion2.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion2.setText("|| Configuracion");
        btnCerrarSesion2.setToolTipText("");
        btnCerrarSesion2.setBorder(null);
        btnCerrarSesion2.setBorderPainted(false);
        btnCerrarSesion2.setContentAreaFilled(false);
        btnCerrarSesion2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCerrarSesion2.setFocusPainted(false);
        btnCerrarSesion2.setFocusable(false);
        btnCerrarSesion2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCerrarSesion2.setOpaque(true);
        btnCerrarSesion2.setRequestFocusEnabled(false);
        btnCerrarSesion2.setVerifyInputWhenFocusTarget(false);
        btnCerrarSesion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenu2Layout = new javax.swing.GroupLayout(pnlMenu2);
        pnlMenu2.setLayout(pnlMenu2Layout);
        pnlMenu2Layout.setHorizontalGroup(
            pnlMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrarSesion2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrarSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlMenu2Layout.createSequentialGroup()
                .addGroup(pnlMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(btnGestionarTrabajadores, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSucursales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTransacciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        pnlMenu2Layout.setVerticalGroup(
            pnlMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGestionarTrabajadores, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarSesion2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrarSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCuerpo.setBackground(new java.awt.Color(204, 204, 204));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("   \n   EurekaBank era un banco con sede en Foster City,\n   California, con operaciones principales en el área\n   de la Bahía de San Francisco. Anteriormente conocido\n   como Eureka Federal Savings and Loan, con sede en \n   San Carlos, California, y conocido por un escándalo en\n   la década de 1980 que involucraba a John DeLorean.\n\n   Eureka Bank tiene una sucursal que esta ubicada en \n   Estados Unidos, banco en linea (Banco Eureka \n   Bank.online), tiene requerimientos mínimos(DNI) para \n   abrir una cuenta bancaria, solicitar tarjeta de credito,\n   generar transferencias bancarias.");
        jTextArea1.setBorder(null);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        etqTitulo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        etqTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etqTitulo.setText("EUREKA BANK");

        btn0.setBackground(new java.awt.Color(204, 204, 204));
        btn0.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btn0.setText("|| btn0 ||");
        btn0.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), java.awt.Color.darkGray));
        btn0.setContentAreaFilled(false);
        btn0.setFocusPainted(false);
        btn0.setFocusable(false);
        btn0.setRequestFocusEnabled(false);
        btn0.setVerifyInputWhenFocusTarget(false);
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        btn1.setBackground(new java.awt.Color(204, 204, 204));
        btn1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btn1.setText("|| btn1 ||");
        btn1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), java.awt.Color.darkGray));
        btn1.setContentAreaFilled(false);
        btn1.setFocusPainted(false);
        btn1.setFocusable(false);
        btn1.setRequestFocusEnabled(false);
        btn1.setVerifyInputWhenFocusTarget(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(204, 204, 204));
        btn2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btn2.setText("|| btn2 ||");
        btn2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), java.awt.Color.darkGray));
        btn2.setContentAreaFilled(false);
        btn2.setFocusPainted(false);
        btn2.setFocusable(false);
        btn2.setRequestFocusEnabled(false);
        btn2.setVerifyInputWhenFocusTarget(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        jLabel3.setText("© Grupo 109 - Version 4.0.5 - 2020");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(etqTitulo)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(383, 383, 383)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(73, 181, 172));

        jPanel1.setBackground(new java.awt.Color(73, 181, 172));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bienvenido");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(73, 181, 172));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.setVerifyInputWhenFocusTarget(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(75, 75, 75)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        IniciarSesion form1 = new IniciarSesion();
        func.logout();
        this.dispose();
        form1.toFront();
        form1.setVisible(true);
        form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        msj.ContMensaje("¡¡ADIOS!!");
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        func.logout();
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnGestionarTrabajadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarTrabajadoresActionPerformed
        etqTitulo.setText("TRABAJADORES");
        jTextArea1.setText("\n" +
        "    Nuestros asesores te ayuda en tus tramites, \n" +
        "    gestiones y/o consultas brindandote informacion \n" +
        "    para que asi pueda gestionar bien su dinero y\n" +
        "    tenga una buena experiencia con nosotros.\n" +
        "\n" +
        "    Para poder ver la informacion de los trabajadores\n" +
        "    del banco tendra que dirigirse a la opcion \n" +
        "    GESTIONAR. Ahi no solo podra ver la informacion\n" +
        "    de los trabajadores tambien podra agregar,\n" +
        "    eliminar y modificar la lista.");
        btn0.setVisible(true);
        btn0.setText("|| Gestionar ||");
        btn1.setVisible(false);
        btn2.setVisible(false);
    }//GEN-LAST:event_btnGestionarTrabajadoresActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        etqTitulo.setText("EUREKA BANK");
        jTextArea1.setText("   \n" +
        "   EurekaBank era un banco con sede en Foster City,\n" +
        "   California, con operaciones principales en el área\n" +
        "   de la Bahía de San Francisco. Anteriormente conocido\n" +
        "   como Eureka Federal Savings and Loan, con sede en \n" +
        "   San Carlos, California, y conocido por un escándalo en\n" +
        "   la década de 1980 que involucraba a John DeLorean.\n" +
        "\n" +
        "   Eureka Bank tiene una sucursal que esta ubicada en \n" +
        "   Estados Unidos, banco en linea (Banco Eureka \n" +
        "   Bank.online), tiene requerimientos mínimos(DNI) para \n" +
        "   abrir una cuenta bancaria, solicitar tarjeta de credito,\n" +
        "   generar transferencias bancarias.");

        btn0.setVisible(false);
        btn1.setVisible(false);
        btn2.setVisible(false);
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSucursalesActionPerformed
        etqTitulo.setText("SUCURSALES");
        jTextArea1.setText("\n" +
        "    En nuestra sucursales podras realizar los mismos\n" +
        "    tramites que hacias en nuestra agencia central,\n" +
        "    esto a sido elaborado para que nuestros clientes\n" +
        "    tengan una mayor facilidad en pagar, cobrar,\n" +
        "    consultar sobre sus tramites.\n" +
        "\n" +
        "    Si desea enviar un mensaje a un cliente, tiene\n" +
        "    que dirigirse a la opcion de NOTIFICAR y escribir\n" +
        "    el mensaje que desea enviar y luego dar en la \n" +
        "    opcion ENVIAR.\n" +
        "    Si desea tener informacion de nuestras sucursales,\n" +
        "    tendra que dirigirse a la opcion INSPECCIONAR, ahi\n" +
        "    se le mostrará la lista de sucursales y su direccion.");
        btn0.setVisible(true);
        btn0.setText("|| Inspeccionar ||");
        btn1.setVisible(true);
        btn1.setText("|| Notificar ||");
        btn2.setVisible(false);
    }//GEN-LAST:event_btnSucursalesActionPerformed

    private void btnTransaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaccionesActionPerformed
        etqTitulo.setText("TRANSACCIONES");
        jTextArea1.setText("\n" +
        "    En nuestras agencias bancarias usted podra realizar\n" +
        "    transacciones de una cuenta a otra cuenta de \n" +
        "    nuestro mismo banco o de otra entidad bancaria\n" +
        "\n" +
        "    Si esea visualizar a todos los clientes del banco\n" +
        "    primero debe dirigirse a la opcion REALIZAR, ahi\n" +
        "    no solo podra ver la informacion de los clientes\n" +
        "    tambien podra agregar, eliminar y modificar la \n" +
        "    lista de dichos clientes.");
        btn0.setVisible(true);
        btn0.setText("|| Realizar ||");
        btn1.setVisible(true);
        btn1.setText("|| Parametros ||");
        btn2.setVisible(false);
    }//GEN-LAST:event_btnTransaccionesActionPerformed

    private void btnCerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion1ActionPerformed
        Contactos_Desarrolladores fromCont = new Contactos_Desarrolladores();
        fromCont.toFront();
        fromCont.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesion1ActionPerformed

    private void btnCerrarSesion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion2ActionPerformed
        // Boton para que se vea bonito
        msj.ContMensaje("En Mantenimineto :(");
    }//GEN-LAST:event_btnCerrarSesion2ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        switch(etqTitulo.getText()){
            case "TRABAJADORES":
                this.dispose();
                Modulo_Trabajadores formTra = new Modulo_Trabajadores();
                formTra.toFront();
                formTra.setVisible(true);
                break;
            case "SUCURSALES":
                this.dispose();
                Modulo_Sucursales formSuc = new Modulo_Sucursales();
                formSuc.toFront();
                formSuc.setVisible(true);
                break;
            case "TRANSACCIONES":
                this.dispose();
                Modulo_Transacciones formTrans = new Modulo_Transacciones();
                formTrans.toFront();
                formTrans.setVisible(true);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btn0ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        switch(etqTitulo.getText()){
            case "TRABAJADORES":
                // Boton para que se vea bonito
                msj.ContMensaje("En Mantenimineto :(");
                break;
            case "SUCURSALES":
                // Boton para que se vea bonito
                msj.ContMensaje("En Mantenimineto :(");
                break;
            case "TRANSACCIONES":
                Modulo_Parametros formPara = new Modulo_Parametros();
                formPara.toFront();
                formPara.setVisible(true);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnCerrarSesion2;
    private javax.swing.JButton btnGestionarTrabajadores;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnSucursales;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JLabel etqTitulo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlMenu2;
    // End of variables declaration//GEN-END:variables

}
