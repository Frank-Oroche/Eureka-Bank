package MVC_Vistas;

import MVC_Datos.Permiso;
import MVC_Datos.Usuario;
import MVC_Logica.gestion_permiso;
import MVC_Logica.gestion_usuario;
import com.sun.awt.AWTUtilities;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class Modulo_Usuarios extends javax.swing.JFrame {
    gestion_usuario func= new gestion_usuario();
    gestion_permiso func_perm= new gestion_permiso();
    Mensaje msj = new Mensaje();

    public Modulo_Usuarios() {
        initComponents();
        setLocationRelativeTo(null);
        Shape forma = new RoundRectangle2D.Double(0, 0, getBounds().width, getBounds().height, 20, 20);
        AWTUtilities.setWindowShape(this, forma);
        MostrarDatos();
    }
    
    public void Limpiesa(JPanel panel) {
        for (int i = 0; panel.getComponents().length > i; i++) {
            if (panel.getComponents()[i] instanceof JTextField) {
                ((JTextField) panel.getComponents()[i]).setText("");
            } else if (panel.getComponents()[i] instanceof JPasswordField) {
                ((JPasswordField) panel.getComponents()[i]).setText("");
            }
        }
    }
    
    public void MostrarDatos(){
        try {
            DefaultTableModel modelo;
            gestion_usuario func = new gestion_usuario();
            modelo = func.mostrar();
            tblUsuarios.setModel(modelo);
            etqTotal.setText("Total Registros " + Integer.toString(func.totalregistros));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    
    public void Limpiar_permiso(){
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        jCheckBox5.setSelected(false);
        jCheckBox6.setSelected(false);
    }
    
    
    public void AgregarPermiso(int modulo_permiso,String perm_estado){
        //Agregar permisos
        if (!txtCodigo.getText().equals("")) {
            Permiso perm  = new Permiso();
            perm.setEmpleado_codigo(txtCodigo.getText());
            perm.setModulo_codigo(modulo_permiso);
            perm.setEstado(perm_estado);
            if (func_perm.insertar(perm)) {
            }else{
                msj.ContMensaje("ERROR: Error al registrar el permiso: "+perm.getModulo_codigo());
            }
        }else{
        }
    }
    
    public void agregar_permisos(){
        if(jCheckBox1.isSelected()){
            AgregarPermiso(1,"ACTIVO");
        }else{
            AgregarPermiso(1,"CANCELADO");
        }
        if(jCheckBox2.isSelected()){
            AgregarPermiso(2,"ACTIVO");
        }else{
            AgregarPermiso(2,"CANCELADO");
        }
        if(jCheckBox3.isSelected()){
            AgregarPermiso(3,"ACTIVO");
        }else{
            AgregarPermiso(3,"CANCELADO");
        }
        if(jCheckBox4.isSelected()){
            AgregarPermiso(4,"ACTIVO");
        }else{
            AgregarPermiso(4,"CANCELADO");
        }
        if(jCheckBox5.isSelected()){
            AgregarPermiso(5,"ACTIVO");
        }else{
            AgregarPermiso(5,"CANCELADO");
        }
        if(jCheckBox6.isSelected()){
            AgregarPermiso(6,"ACTIVO");
        }else{
            AgregarPermiso(6,"CANCELADO");
        }
    }
    
    public void ModificarPermiso(int modulo_permiso,String perm_estado){
        if (!txtCodigo.getText().equals("")) {
            Permiso perm  = new Permiso();
            perm.setEmpleado_codigo(txtCodigo.getText());
            perm.setModulo_codigo(modulo_permiso);
            perm.setEstado(perm_estado);
            if (func_perm.editar(perm)){
                }else{
                    msj.ContMensaje("ERROR: Error al modificar el permiso: "+perm.getModulo_codigo());
                }
        }else{
        }
    }
    
    public void modificar_permisos(){
        if(jCheckBox1.isSelected()){
            ModificarPermiso(1,"ACTIVO");
        }else{
            ModificarPermiso(1,"CANCELADO");
        }
        if(jCheckBox2.isSelected()){
            ModificarPermiso(2,"ACTIVO");
        }else{
            ModificarPermiso(2,"CANCELADO");
        }
        if(jCheckBox3.isSelected()){
            ModificarPermiso(3,"ACTIVO");
        }else{
            ModificarPermiso(3,"CANCELADO");
        }
        if(jCheckBox4.isSelected()){
            ModificarPermiso(4,"ACTIVO");
        }else{
            ModificarPermiso(4,"CANCELADO");
        }
        if(jCheckBox5.isSelected()){
            ModificarPermiso(5,"ACTIVO");
        }else{
            ModificarPermiso(5,"CANCELADO");
        }
        if(jCheckBox6.isSelected()){
            ModificarPermiso(6,"ACTIVO");
        }else{
            ModificarPermiso(6,"CANCELADO");
        }
    }
    

    public void Modificar(){
        if (!txtCodigo.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Estás seguro de Modificar Usuario?", "Confirmar", 2);
            //msj.ConfMensaje("¿Seguro que desea eliminar Trabajador?");
            if (confirmacion == 0) {
                Usuario u  = new Usuario();
                u.setEmpleado_codigo(txtCodigo.getText());
                u.setUsuario(txtUsuario.getText());
                u.setClave(txtContraseña.getText());
                u.setEstado((String)comboEstado.getSelectedItem());
                if (func.editar(u)) {
                    modificar_permisos();
                    Limpiar_permiso();
                    msj.ContMensaje("¡¡Trabajador Modificado!!");
                    MostrarDatos();
                    Limpiesa(jPanel1);
                }else{
                    msj.ContMensaje("ERROR: Error al modificar");
                }
            }
        }else{
            msj.ContMensaje("¡¡Ingrese campos!!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        etqTotal = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        btnAtras = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        btnClose.setBackground(new java.awt.Color(153, 153, 153));
        btnClose.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        btnClose.setForeground(new java.awt.Color(33, 45, 62));
        btnClose.setText("X");
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setFocusPainted(false);
        btnClose.setFocusable(false);
        btnClose.setOpaque(true);
        btnClose.setRequestFocusEnabled(false);
        btnClose.setVerifyInputWhenFocusTarget(false);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(33, 45, 62));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel1.setText("Codigo del trabajador:");

        jLabel2.setBackground(new java.awt.Color(33, 45, 62));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel2.setText("Usuario del trabajador:");

        jLabel3.setBackground(new java.awt.Color(33, 45, 62));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel3.setText("Contraseña:");

        jLabel4.setBackground(new java.awt.Color(33, 45, 62));
        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel4.setText("Estado del trabajador:");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "ANULADO" }));

        jLabel5.setBackground(new java.awt.Color(33, 45, 62));
        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel5.setText("Permisos:");

        jCheckBox1.setText("Procesos");

        jCheckBox2.setText("Tablas");

        jCheckBox3.setText("Consultas");

        jCheckBox4.setText("Reportes");

        jCheckBox5.setText("Util");

        jCheckBox6.setText("Seguridad");

        etqTotal.setText("Registros");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(27, 27, 27)
                                    .addComponent(txtContraseña))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(comboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(btnClose))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox5)
                                .addGap(48, 48, 48)
                                .addComponent(jCheckBox6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(33, 33, 33)
                                .addComponent(jCheckBox2)
                                .addGap(33, 33, 33)
                                .addComponent(jCheckBox3)))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(etqTotal)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(etqTotal))
        );

        btnAtras.setBackground(new java.awt.Color(153, 153, 153));
        btnAtras.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btnAtras.setText("Atras");
        btnAtras.setToolTipText("");
        btnAtras.setBorderPainted(false);
        btnAtras.setContentAreaFilled(false);
        btnAtras.setFocusPainted(false);
        btnAtras.setFocusable(false);
        btnAtras.setOpaque(true);
        btnAtras.setRequestFocusEnabled(false);
        btnAtras.setVerifyInputWhenFocusTarget(false);
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(153, 153, 153));
        btnAgregar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setToolTipText("");
        btnAgregar.setBorderPainted(false);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setFocusable(false);
        btnAgregar.setOpaque(true);
        btnAgregar.setRequestFocusEnabled(false);
        btnAgregar.setVerifyInputWhenFocusTarget(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(153, 153, 153));
        btnModificar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setToolTipText("");
        btnModificar.setBorderPainted(false);
        btnModificar.setContentAreaFilled(false);
        btnModificar.setFocusPainted(false);
        btnModificar.setFocusable(false);
        btnModificar.setOpaque(true);
        btnModificar.setRequestFocusEnabled(false);
        btnModificar.setVerifyInputWhenFocusTarget(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel19.setText("© Grupo 109 - Version 4.0.5 - 2020");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAtras, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // Boton para que se vea bonito
        if (!txtCodigo.getText().equals("")) {
            Usuario u  = new Usuario();
            u.setEmpleado_codigo(txtCodigo.getText());
            u.setUsuario(txtUsuario.getText());
            u.setClave(txtContraseña.getText());
            u.setEstado((String)comboEstado.getSelectedItem());
            if (func.insertar(u)) {
                agregar_permisos();
                Limpiar_permiso();
                msj.ContMensaje("¡¡Usuario Registrado!!");
                MostrarDatos();
                Limpiesa(jPanel1);
            }else{
                msj.ContMensaje("ERROR: Error al registrar");
            }
        }else{
            msj.ContMensaje("¡¡Ingrese campos!!");
        }
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String user = txtUsuario.getText();
        String pass = txtContraseña.getText();
        if (user.length() == 0 || pass.length() == 0) {
            msj.ContMensaje("¡INGRESAR USUARIO Y CONTRASEÑA!");
        } else {
            gestion_usuario func = new gestion_usuario();
            if (func.verificarUsuario(user)) {
                if (func.verificaPassword(pass)) {
                    Modificar();
                } else {
                    msj.ContMensaje("¡LA CONTRASEÑA NO ES CORRECTA!");
                }
            } else {
                msj.ContMensaje("¡EL USUARIO NO EXISTE!");
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        Limpiar_permiso();
        int selec = tblUsuarios.rowAtPoint(evt.getPoint());
        txtCodigo.setText(String.valueOf(tblUsuarios.getValueAt(selec, 0)));
        txtUsuario.setText(String.valueOf(tblUsuarios.getValueAt(selec, 1)));
        comboEstado.setSelectedItem(tblUsuarios.getValueAt(selec, 2));
        String permisos[]= new String[6];
        permisos=func_perm.mostrar(String.valueOf(tblUsuarios.getValueAt(selec, 0)));
        if(permisos!=null){
            for(int i=0;i<6;i++){
                if(permisos[i].equalsIgnoreCase("ACTIVO")){
                    switch(i){
                        case 0: jCheckBox1.setSelected(true);break;
                        case 1: jCheckBox2.setSelected(true);break;
                        case 2: jCheckBox3.setSelected(true);break;
                        case 3: jCheckBox4.setSelected(true);break;
                        case 4: jCheckBox5.setSelected(true);break;
                        case 5: jCheckBox6.setSelected(true);break;
                    }
                }
            } 
        }else{
        }
        

    }//GEN-LAST:event_tblUsuariosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JLabel etqTotal;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
