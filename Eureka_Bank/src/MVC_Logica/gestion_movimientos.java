package MVC_Logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gestion_movimientos {

    private final conexion mysql = new conexion();
    private final Connection cn = mysql.conectar();
    public Integer totalregistros;

    public DefaultTableModel mostrarMovimientos(String valor) {
        String[] titulos = {"ID", "Fecha", "Tipo", "Descripcion", "Accion", "Importe"};
        String[] registros = new String[7];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "{CALL moviminetos_cuenta('" + valor + "')}";
        totalregistros = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("nromov");
                registros[1] = rs.getString("fecha");
                registros[2] = rs.getString("tipo");
                registros[3] = rs.getString("descripcion");
                registros[4] = rs.getString("accion");
                registros[5] = rs.getString("importe");
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
