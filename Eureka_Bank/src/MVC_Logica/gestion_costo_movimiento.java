package MVC_Logica;

import MVC_Datos.Costo_Movimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_costo_movimiento {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrar() {
        String[] titulos = {"Moneda", "Importe"};
        String[] registros = new String[3];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select m.vch_monedescripcion, c.dec_costimporte from costomovimiento as c inner join moneda as m on m.chr_monecodigo = c.chr_monecodigo;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("vch_monedescripcion");
                registros[1] = rs.getString("dec_costimporte");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean insertar(Costo_Movimiento dts) {
        String SQL = "INSERT INTO costomovimiento VALUES(?,?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getCodigo_moneda());
            pst.setFloat(2, dts.getImporte());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(Costo_Movimiento dts) {
        String SQL = "UPDATE costomovimiento SET dec_costimporte = ? WHERE (chr_monecodigo = ? )";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setFloat(1, dts.getImporte());
            pst.setString(2, dts.getCodigo_moneda());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        } 
    }
}
