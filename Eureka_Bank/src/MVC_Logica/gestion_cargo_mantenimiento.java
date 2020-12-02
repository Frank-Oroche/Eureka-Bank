package MVC_Logica;

import MVC_Datos.Cargo_Mantenimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_cargo_mantenimiento {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrar() {
        String[] titulos = {"Moneda", "Monto Max", "Importe"};
        String[] registros = new String[4];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select m.vch_monedescripcion, c.dec_cargMontoMaximo, c.dec_cargImporte from cargomantenimiento as c inner join moneda as m on m.chr_monecodigo = c.chr_monecodigo;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("vch_monedescripcion");
                registros[1] = rs.getString("dec_cargMontoMaximo");
                registros[2] = rs.getString("dec_cargImporte");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean insertar(Cargo_Mantenimiento dts) {
        String SQL = "INSERT INTO cargomantenimiento VALUES(?,?,?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getMoneda_codigo());
            pst.setFloat(2, dts.getCargMontoMaximo());
            pst.setFloat(3, dts.getCargImporte());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(Cargo_Mantenimiento dts) {
        String SQL = "UPDATE cargomantenimiento SET dec_cargMontoMaximo = ?, dec_cargImporte = ? WHERE (chr_monecodigo = ? )";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setFloat(1, dts.getCargMontoMaximo());
            pst.setFloat(2, dts.getCargImporte());
            pst.setString(3, dts.getMoneda_codigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }  
    }

}
