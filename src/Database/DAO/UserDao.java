package Database.DAO;

import Global.DatabaseHandler;
import Database.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements DAO<User>{

    private static final Connection con = DatabaseHandler.connect();
    private static String query = "";

    @Override
    public boolean add(User item) {//returns true if added successfully
        query = "INSERT INTO music_project.user(id,username,password) VALUES (?,?,?);";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, item.getId());
            preparedStatement.setString(2, item.getUsername());
            preparedStatement.setString(3, String.valueOf(item.getPassword().hashCode()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<User> getAll() {//returns a list full of user-s
        List<User> userList = new ArrayList<>();

        User user = new User(0,null,null);
        query = "SELECT * FROM music_project.user;";

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int indx = 0;
            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getString("password")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean update(User item) {
        query = "UPDATE music_project.user SET username = ? , password = ? WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,item.getUsername());
            preparedStatement.setString(2, String.valueOf(item.getPassword().hashCode()));
            preparedStatement.setInt(3,item.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean remove(User item) {//returns true if removed successfully
        query = "DELETE FROM music_project.user WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,item.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
