package Database.DAO;

import Database.Models.Music;
import Database.Models.User;
import Global.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDao implements DAO<Music>{
    private static final Connection con = DatabaseHandler.connect();
    private static String query = "";


    @Override
    public boolean add(Music item) {//returns true if added successfully
        query = "INSERT INTO music_project.music(id,name) VALUES (?,?);";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, item.getId());
            preparedStatement.setString(2,item.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Music> getAll() {//returns a list full of themst musers
        List<Music> musicList = new ArrayList<>();

        Music music = new Music(0,"");
        query = "SELECT * FROM music_project.music;";

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int indx = 0;
            while (resultSet.next()) {
                musicList.add(new Music(resultSet.getInt("id"),resultSet.getString("name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicList;
    }

    @Override
    public boolean update(Music item) {//ups the date
        query = "UPDATE music_project.music SET name = ?  WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,item.getName());
            preparedStatement.setInt(2,item.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean remove(Music item) {//returns true if removed successfully
        query = "DELETE FROM music_project.music WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,item.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public String getMusicNameById(int id){
        query = "  SELECT name FROM music_project.music\n" +
                "  WHERE id = " + id + ";";
        String name = null;
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}