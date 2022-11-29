package Database.DAO;

import Database.Models.Music;
import Database.Models.User_Music;
import Global.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User_MusicDao implements DAO<User_Music> {
    private static final Connection con = DatabaseHandler.connect();
    private static String query = "";


    @Override
    public boolean add(User_Music item) {
        query = "INSERT INTO music_project.user_music(User_id,Music_id) VALUES (?,?);";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, item.getUser_id());
            preparedStatement.setInt(2, item.getMusic_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<User_Music> getAll() {
        List<User_Music> user_music_List = new ArrayList<>();

        User_Music user_music = new User_Music(0,0);
        query = "SELECT * FROM music_project.user_music;";

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int indx = 0;
            while (resultSet.next()) {
                user_music_List.add(new User_Music(resultSet.getInt("User_id"),resultSet.getInt("Music_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_music_List;
    }

    @Override
    public boolean update(User_Music item) {
        query = "UPDATE music_project.user_music SET Music_id= ?  WHERE User_id = ? ;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,item.getMusic_id());
            preparedStatement.setInt(2, item.getUser_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean removeByMusic(User_Music item_deletesByMusic) {
        query = "DELETE FROM music_project.user_music WHERE Music_id = ?;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,item_deletesByMusic.getMusic_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean removeByUser(User_Music item_deletesByUser) {
        query = "DELETE FROM music_project.user_music WHERE User_id = ?;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,item_deletesByUser.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean remove(User_Music item_deletesByUser) {
        query = "DELETE FROM music_project.user_music WHERE User_id = ?;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,item_deletesByUser.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}