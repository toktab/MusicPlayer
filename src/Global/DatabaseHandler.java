package Global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    protected static String CONN_STRING = "jdbc:mysql://localhost:3306/music_project";
    protected static String USERNAME = "root";
    protected static String PASSWORD = "root";

    public static Connection connect() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Could Not Connect to Server!");
            e.getCause();
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
        return connection;
    }

}
