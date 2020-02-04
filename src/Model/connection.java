package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class connection {
    private Connection cn;
    public Connection connect() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/document","root","");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could Not Connect: \nReason: "+e.getMessage());
        }
        return cn;
    }
}
