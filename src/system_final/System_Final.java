
package system_final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class System_Final {
    
    private static final String SUrl = "jdbc:mysql://localhost:3306/jfdb";
    private static final String SUser = "root";
    private static final String SPass = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(SUrl, SUser, SPass);
    }

    public static void main(String[] args) {
        
        Login_Final LoginFrame = new Login_Final();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        // TODO code application logic here
        
        
    }
    
}
