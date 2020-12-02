package MVC_Logica;

import MVC_Datos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_usuario {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;
    private static String codigo;

    public conexion getMysql() {
        return mysql;
    }

    public Connection getCn() {
        return cn;
    }

    public String getCodigo() {
        return codigo;
    }

    public gestion_usuario() {
    }

    public DefaultTableModel mostrar() {
        String[] titulos = {"Codigo", "Uuario", "Estado"};
        String[] registros = new String[4];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "SELECT * FROM usuario;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_emplcodigo");
                registros[1] = rs.getString("vch_emplusuario");
                registros[2] = rs.getString("vch_emplestado");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }
    


    public boolean verificarUsuario(String usuario) {
        String existe = "";
        String SQL = "select u.vch_emplusuario, u.chr_emplcodigo from usuario as u where u.vch_emplusuario='" + usuario + "' and u.vch_emplestado='ACTIVO'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                existe = rs.getString(1);
                codigo = rs.getString(2);
            }
            return existe.equals(usuario);
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean verificaPassword(String pass) {
        String existe = "";
        String SQL = "select u.vch_emplclave from usuario as u where u.vch_emplclave=SHA('" + pass + "') and u.vch_emplestado='ACTIVO'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                existe = rs.getString(1);
            }
            return !existe.equals("");
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    

    public void login() {
        String SQL = "INSERT INTO logsession(chr_emplcodigo,fec_ingreso,fec_salida) VALUES(?,now(),null)";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, codigo);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    

    public void logout() {
        String SQL = "UPDATE logsession SET fec_salida = now() WHERE chr_emplcodigo = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, codigo);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public boolean insertar(Usuario dts) {
        String SQL = "INSERT INTO usuario VALUES(?,?,SHA(?),?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getEmpleado_codigo());
            pst.setString(2, dts.getUsuario());
            pst.setString(3, dts.getClave());
            pst.setString(4, dts.getEstado());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
        public boolean editar(Usuario dts) {
        String SQL = "UPDATE usuario SET vch_emplestado = ? WHERE (chr_emplcodigo = ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getEstado());
            pst.setString(2, dts.getEmpleado_codigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
