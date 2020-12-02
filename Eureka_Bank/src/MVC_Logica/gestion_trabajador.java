package MVC_Logica;

import MVC_Datos.Trabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_trabajador {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrar() {
        String[] titulos = {"Codigo", "Apellidos", "Nombres", "Ciudad", "Direccion"};
        String[] registros = new String[6];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "SELECT * FROM empleado;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_emplcodigo");
                registros[1] = rs.getString("vch_emplpaterno") + " " + rs.getString("vch_emplmaterno");
                registros[2] = rs.getString("vch_emplnombre");
                registros[3] = rs.getString("vch_emplciudad");
                registros[4] = rs.getString("vch_empldireccion");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }

    public boolean insertar(Trabajador dts) {
        String SQL = "INSERT INTO empleado VALUES(?,?,?,?,?,?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getEmpleado_codigo());
            pst.setString(2, dts.getApellido_paterno());
            pst.setString(3, dts.getApellido_materno());
            pst.setString(4, dts.getNombre());
            pst.setString(5, dts.getCiudad());
            pst.setString(6, dts.getDireccion());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean eliminar(Trabajador dts) {
        String SQL = "delete from empleado where chr_emplcodigo=?";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getEmpleado_codigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean editar(Trabajador dts) {
        String SQL = "UPDATE empleado SET vch_emplpaterno = ?, vch_emplmaterno = ?, vch_emplnombre = ?, vch_emplciudad = ?, vch_empldireccion = ? WHERE (chr_emplcodigo = ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getApellido_paterno());
            pst.setString(2, dts.getApellido_materno());
            pst.setString(3, dts.getNombre());
            pst.setString(4, dts.getCiudad());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getEmpleado_codigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public DefaultTableModel FiltrarDatos(String valor) {
        String[] titulos = {"Codigo", "Apellidos", "Nombres", "Ciudad", "Direccion"};
        String[] registros = new String[6];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select * from empleado where vch_emplnombre like '%" + valor + "%'";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_emplcodigo");
                registros[1] = rs.getString("vch_emplpaterno") + " " + rs.getString("vch_emplmaterno");
                registros[2] = rs.getString("vch_emplnombre");
                registros[3] = rs.getString("vch_emplciudad");
                registros[4] = rs.getString("vch_empldireccion");
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
