package MMDataAccess.MMDataHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MMDataHelper {
    private static final String mmDBURL = "jdbc:sqlite:dataBase//EcuFauna.sqlite";
    private static Connection mmConnection = null;

    public static synchronized Connection mmConection() {
        try {
            if (mmConnection == null || mmConnection.isClosed()) {
                mmConnection = DriverManager.getConnection(mmDBURL);
            }
            System.out.println("Se conecto a la base de datos");
            return mmConnection;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static synchronized void mmDesconection() {
        try {
            if (mmConnection != null && !mmConnection.isClosed()) {
                mmConnection.close();
                System.out.println("Se desconecto a la base de datos");
                mmConnection = null;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
