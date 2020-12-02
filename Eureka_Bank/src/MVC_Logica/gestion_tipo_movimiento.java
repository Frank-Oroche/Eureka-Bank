package MVC_Logica;

import MVC_Datos.Tipo_Movimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_tipo_movimiento {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrar() {
        String[] titulos = {"Codigo", "Descripcion", "Accion", "Estado"};
        String[] registros = new String[5];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "SELECT * FROM tipomovimiento;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_tipocodigo");
                registros[1] = rs.getString("vch_tipodescripcion");
                registros[2] = rs.getString("vch_tipoaccion");
                registros[3] = rs.getString("vch_tipoestado");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean insertar(Tipo_Movimiento dts) {
        String SQL = "INSERT INTO tipomovimiento VALUES(?,?,?,?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getCodigo());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getAccion());
            pst.setString(4, dts.getEstado());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }  
    
    public boolean editar(Tipo_Movimiento dts) {
        String SQL = "UPDATE tipomovimiento SET vch_tipodescripcion = ?, vch_tipoaccion = ?, vch_tipoestado = ? WHERE (chr_tipocodigo = ? )";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getDescripcion());
            pst.setString(2, dts.getAccion());
            pst.setString(3, dts.getEstado());
            pst.setString(4, dts.getCodigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        } 
    }
}
