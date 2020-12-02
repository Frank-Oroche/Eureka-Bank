package MVC_Logica;

import MVC_Datos.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_cliente {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrar() {
        String[] titulos = {"Codigo", "A. Paterno", "A. Materno", "Nombres", "DNI", "Ciudad", "Direccion", "Telefono", "E-Mail"};
        String[] registros = new String[10];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "SELECT * FROM cliente;";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_cliecodigo");
                registros[1] = rs.getString("vch_cliepaterno");
                registros[2] = rs.getString("vch_cliematerno");
                registros[3] = rs.getString("vch_clienombre");
                registros[4] = rs.getString("chr_cliedni");
                registros[5] = rs.getString("vch_clieciudad");
                registros[6] = rs.getString("vch_cliedireccion");
                registros[7] = rs.getString("vch_clietelefono");
                registros[8] = rs.getString("vch_clieemail");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }

    public boolean insertar(Cliente dts) {
        String SQL = "insert into cliente values( ?, ?, ?, ?, ?, ?, ?, ?, ? );";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getCliente_codigo());
            pst.setString(2, dts.getApellido_paterno());
            pst.setString(3, dts.getApellido_materno());
            pst.setString(4, dts.getNombres());
            pst.setString(5, dts.getDNI());
            pst.setString(6, dts.getCiudad());
            pst.setString(7, dts.getDireccion());
            pst.setString(8, dts.getTelefono());
            pst.setString(9, dts.getEmail());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean eliminar(Cliente dts) {
        String SQL = "delete from cliente where chr_cliecodigo=?";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getCliente_codigo());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean editar(Cliente dts) {
        String SQL = "{CALL actualiza_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            CallableStatement pst = cn.prepareCall(SQL);
            pst.registerOutParameter(1, Types.VARCHAR);
            pst.setString(2, dts.getCliente_codigo());
            pst.setString(3, dts.getApellido_paterno());
            pst.setString(4, dts.getApellido_materno());
            pst.setString(5, dts.getNombres());
            pst.setString(6, dts.getDNI());
            pst.setString(7, dts.getCiudad());
            pst.setString(8, dts.getDireccion());
            pst.setString(9, dts.getTelefono());
            pst.setString(10, dts.getEmail());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public DefaultTableModel FiltrarDatos(String valor) {
        String[] titulos = {"Codigo", "A. Paterno", "A. Materno", "Nombres", "DNI", "Ciudad", "Direccion", "Telefono", "E-Mail"};
        String[] registros = new String[10];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select * from cliente where vch_clienombre like '%" + valor + "%'";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_cliecodigo");
                registros[1] = rs.getString("vch_cliepaterno");
                registros[2] = rs.getString("vch_cliematerno");
                registros[3] = rs.getString("vch_clienombre");
                registros[4] = rs.getString("chr_cliedni");
                registros[5] = rs.getString("vch_clieciudad");
                registros[6] = rs.getString("vch_cliedireccion");
                registros[7] = rs.getString("vch_clietelefono");
                registros[8] = rs.getString("vch_clieemail");
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
