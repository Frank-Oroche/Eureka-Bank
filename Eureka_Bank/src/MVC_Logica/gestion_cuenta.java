package MVC_Logica;

import MVC_Datos.Cuenta;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_cuenta {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrarCuentaCliente(String valor) {
        String[] titulos = {"N° de Cuenta", "Moneda", "Sucursal", "Trabajador", "Creacion", "Estado", "N° de Movimientos"};
        String[] registros = new String[8];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select c.chr_cuencodigo, m.vch_monedescripcion, s.vch_sucunombre, e.chr_emplcodigo, c.dec_cuensaldo, c.dtt_cuenfechacreacion, c.vch_cuenestado, c.int_cuencontmov from cuenta as c\n"
                + "inner join moneda as m on c.chr_monecodigo = m.chr_monecodigo\n"
                + "inner join sucursal as s on c.chr_sucucodigo = s.chr_sucucodigo\n"
                + "inner join empleado as e on c.chr_emplcreacuenta = e.chr_emplcodigo\n"
                + "where c.chr_cliecodigo='" + valor + "';";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("chr_cuencodigo");
                registros[1] = rs.getString("vch_monedescripcion");
                registros[2] = rs.getString("vch_sucunombre");
                registros[3] = rs.getString("chr_emplcodigo");
                registros[4] = rs.getString("dtt_cuenfechacreacion");
                registros[5] = rs.getString("vch_cuenestado");
                registros[6] = rs.getString("int_cuencontmov");
                totalregistros++;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }

    public String consultar_saldo(String cuenta) {
        try {
            CallableStatement cstmt = cn.prepareCall("{call consultar_saldo(?,?)}");
            cstmt.setString(1, cuenta);
            cstmt.registerOutParameter(2, java.sql.Types.FLOAT);
            cstmt.execute();
            String saldo = cstmt.getString(2);
            if (saldo.equals("-1.00")) {
                return "Numero de cuenta invalida.";
            } else {
                return "  " + saldo;
            }
        } catch (HeadlessException | SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    private String buscarEstadoCuenta(Cuenta cuenta) {
        String SQL = "SELECT vch_cuenestado FROM cuenta where chr_cuencodigo='" + cuenta.getN_cuenta() + "';";
        String registros = null;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros = rs.getString("vch_cuenestado");
            }
            return registros;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Consulta Fallida... Error: " + e.getMessage());
            return null;
        }
    }

    public boolean realizar_deposito(Cuenta cuenta, float importe) {
        try {
            if (buscarEstadoCuenta(cuenta).equals("CANCELADO")) {
                return false;
            } else {
                CallableStatement cstmt = cn.prepareCall("{CALL depositar(?, ?, ?)}");
                cstmt.setString(1, cuenta.getN_cuenta());
                cstmt.setFloat(2, importe);
                cstmt.setString(3, cuenta.getEmpleado());
                cstmt.execute();
                return true;
            }
        } catch (HeadlessException | SQLException e) {
            return false;
        }
    }

    public boolean realizar_retiro(Cuenta cuenta, float importe) {
        try {
            if (buscarEstadoCuenta(cuenta).equals("CANCELADO")) {
                return false;
            } else {
                CallableStatement cstmt = cn.prepareCall("{CALL retiro(?, ?, ?, ?)}");
                cstmt.setString(1, cuenta.getN_cuenta());
                cstmt.setFloat(2, importe);
                cstmt.setString(3, cuenta.getClave());
                cstmt.setString(4, cuenta.getEmpleado());
                cstmt.execute();
                return true;
            }
        } catch (HeadlessException | SQLException e) {
            return false;
        }
    }

    public boolean realizar_transferencia(Cuenta cuentaO, Cuenta cuentaD, float importe) {
        try {
            if (buscarEstadoCuenta(cuentaO).equals("CANCELADO")) {
                return false;
            } else {
                CallableStatement cstmt = cn.prepareCall("{CALL realizar_transferencia(?, ?, ?, ?, ?)}");
                cstmt.setString(1, cuentaO.getN_cuenta());
                cstmt.setString(2, cuentaD.getN_cuenta());
                cstmt.setString(3, cuentaO.getClave());
                cstmt.setFloat(4, importe);
                cstmt.setString(5, cuentaO.getEmpleado());
                cstmt.execute();
                return true;
            }
        } catch (HeadlessException | SQLException e) {
            return false;
        }
    }

    public boolean insertar(Cuenta dts) {
        String SQL = "insert into cuenta values(?,?,?,?,?,?,CURDATE(),'ACTIVO',?,?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getN_cuenta());
            pst.setString(2, dts.getMoneda());
            pst.setString(3, dts.getSucursal());
            pst.setString(4, dts.getEmpleado());
            pst.setString(5, dts.getCliente());
            pst.setFloat(6, dts.getSaldo());
            pst.setInt(7, dts.getN_movientos());
            pst.setString(8, dts.getClave());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean cancelar(Cuenta dts) {
        String SQL = "UPDATE cuenta SET vch_cuenestado = 'CANCELADO', dec_cuensaldo = 0 WHERE (chr_cuencodigo = ?);";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, dts.getN_cuenta());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean verificaPassword(String user, String pass) {
        String existe = "";
        String SQL = "select u.chr_cuenclave from cuenta as u where u.chr_cuencodigo='" + user + "' and u.chr_cuenclave='" + pass + "';";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                existe = rs.getString(1);
            }
            return !existe.equals("");
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
