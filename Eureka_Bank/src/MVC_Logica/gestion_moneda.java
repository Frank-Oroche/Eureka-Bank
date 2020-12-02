package MVC_Logica;

import MVC_Datos.Moneda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_moneda {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrar() {
        String[] titulos = {"Codigo", "Nombre"};
        String[] registros = new String[3];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "SELECT * FROM moneda;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_monecodigo");
                registros[1] = rs.getString("vch_monedescripcion");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean insertar(Moneda dts) {
        String SQL = "INSERT INTO moneda VALUES(?,?,?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getCodigo());
            pst.setString(2, dts.getNombre());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(Moneda dts) {
        String SQL = "UPDATE moneda SET vch_monedescripcion = ? WHERE (chr_monecodigo = ? )";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getCodigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    
    /*public String[] MostrarMonedasComboBox(){//AREGLARRRRRRRRRRRR
        String SQL = "SELECT vch_monedescripcion FROM moneda;";
        String monedas[] = null;
        int i=0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                monedas[i] = rs.getString("vch_monedescripcion");
                i++;
            }
            return monedas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: "+e.getMessage());
            return null;
        }
    }*/
}
