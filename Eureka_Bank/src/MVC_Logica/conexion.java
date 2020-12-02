package MVC_Logica;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conexion {

    public String db = "eurekabank";
    public String url = "jdbc:mysql://127.0.0.1/" + db;
    public String user = "root";
    public String pass = "";
    private Connection conectar = null;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            //System.out.println("Conexion Exitosa...");
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Fallida... Error: " + e.getMessage());
            System.exit(1);
        }
        return conectar;
    }
}
