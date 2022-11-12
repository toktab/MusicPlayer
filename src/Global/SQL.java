package Global;

import Models.User;

import java.sql.*;

public class SQL {
    private static String query = "";
    private final static String CONN_STRING = "jdbc:mysql://localhost:3306/music_project";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

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

    protected static int getUserSize() {
        Connection con = connect();
        int size = 0;
        query = "SELECT id FROM music_project.user;";

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                size++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return size;
    }

    protected static String getUser() {
        Connection con = connect();
        String users = "";
        query = "SELECT * FROM music_project.user;";

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                users = users + resultSet.getInt("id") + " " + resultSet.getString("username") + " " + resultSet.getString("password") + "\n";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void addUser(User user) {//1, toko, 12nfdjnj4
        Connection con = connect();
        query = "INSERT INTO music_project.user(id,username,password) VALUES (?,?,?);";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, String.valueOf(user.getPassword().hashCode()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNextId() {
        Connection con = connect();
        query = "SELECT MAX(id) FROM music_project.user;";

        int id = 0;
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM music_project.user;");
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id + 1;
    }

}
