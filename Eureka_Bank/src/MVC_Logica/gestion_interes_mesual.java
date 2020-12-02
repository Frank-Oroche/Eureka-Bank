package MVC_Logica;

import MVC_Datos.Interes_Mensual;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_interes_mesual {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrar() {
        String[] titulos = {"Moneda", "Importe"};
        String[] registros = new String[3];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select m.vch_monedescripcion, i.dec_inteimporte from interesmensual as i inner join moneda as m on m.chr_monecodigo = i.chr_monecodigo;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("vch_monedescripcion");
                registros[1] = rs.getString("dec_inteimporte");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean insertar(Interes_Mensual dts) {
        String SQL = "INSERT INTO interesmensual VALUES(?,?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getMoneda_codigo());
            pst.setFloat(2, dts.getImporte());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(Interes_Mensual dts) {
        String SQL = "UPDATE interesmensual SET dec_inteimporte = ? WHERE (chr_monecodigo = ? )";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setFloat(1, dts.getImporte());
            pst.setString(2, dts.getMoneda_codigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        } 
    }
}
