package MVC_Logica;

import MVC_Datos.Sucursal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_sucursal {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrar() {
        String[] titulos = {"Codigo", "Nombre", "Ciudad", "Direccion", "N° de cuentas"};
        String[] registros = new String[6];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "SELECT * FROM sucursal;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_sucucodigo");
                registros[1] = rs.getString("vch_sucunombre");
                registros[2] = rs.getString("vch_sucuciudad");
                registros[3] = rs.getString("vch_sucudireccion");
                registros[4] = rs.getString("int_sucucontcuenta");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }

    public boolean editar(Sucursal dts) {
        String SQL = "UPDATE sucursal SET vch_sucunombre = ?, vch_sucudireccion = ?  WHERE (chr_sucucodigo = ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDireccion());
            pst.setString(3, dts.getSucursal_codigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public DefaultTableModel FiltrarDatos(String valor) {
        String[] titulos = {"Codigo", "Nombre", "Ciudad", "Direccion", "N° de cuentas"};
        String[] registros = new String[6];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select * from sucursal where vch_sucunombre like '%" + valor + "%'";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_sucucodigo");
                registros[1] = rs.getString("vch_sucunombre");
                registros[2] = rs.getString("vch_sucuciudad");
                registros[3] = rs.getString("vch_sucudireccion");
                registros[4] = rs.getString("int_sucucontcuenta");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }
}
