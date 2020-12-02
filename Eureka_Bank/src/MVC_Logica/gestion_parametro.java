package MVC_Logica;

import MVC_Datos.Parametro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_parametro {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrar() {
        String[] titulos = {"Codigo", "Descripcion", "Valor", "Estado"};
        String[] registros = new String[5];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "SELECT * FROM parametro;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_paracodigo");
                registros[1] = rs.getString("vch_paradescripcion");
                registros[2] = rs.getString("vch_paravalor");
                registros[3] = rs.getString("vch_paraestado");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean insertar(Parametro dts) {
        String SQL = "INSERT INTO parametro VALUES(?,?,?,?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getCodigo());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getValor());
            pst.setString(4, dts.getEstado());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(Parametro dts) {
        String SQL = "UPDATE parametro SET vch_paradescripcion = ?, vch_paravalor = ?, vch_paraestado = ? WHERE (chr_paracodigo = ? )";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getDescripcion());
            pst.setString(2, dts.getValor());
            pst.setString(3, dts.getEstado());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        } 
    }
}
