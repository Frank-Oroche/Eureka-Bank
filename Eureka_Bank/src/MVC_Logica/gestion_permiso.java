package MVC_Logica;

import MVC_Datos.Permiso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class gestion_permiso {
    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();

    public gestion_permiso() {
    }

    public conexion getMysql() {
        return mysql;
    }

    public Connection getCn() {
        return cn;
    }
    
        
    public String[] mostrar(String codigo) {
        String[] permisos=new String[6];;
        String SQL = "SELECT int_moducodigo, vch_permestado FROM permiso WHERE chr_emplcodigo='" + codigo + "';";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            int i=0;
            while (rs.next()) {
                permisos[i] = rs.getString("vch_permestado");
                i++;
            }
            if(i==0){
                permisos=null;
            }
            return permisos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }
      
    public boolean insertar(Permiso dts) {
        String SQL = "INSERT INTO permiso VALUES(?,?,?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getEmpleado_codigo());
            pst.setInt(2, dts.getModulo_codigo());
            pst.setString(3, dts.getEstado());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(Permiso dts) {
        String SQL = "UPDATE permiso SET vch_permestado = ? WHERE (chr_emplcodigo = ? && int_moducodigo = ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getEstado());
            pst.setString(2, dts.getEmpleado_codigo());
            pst.setInt(3, dts.getModulo_codigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
}
