package Database.DAO;

import Database.Models.IsFriendOf;
import Database.Models.User;
import Global.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IsFriendOfDao implements DAO<IsFriendOf> {
    private static final Connection con = DatabaseHandler.connect();
    private static String query = "";

    @Override
    public boolean add(IsFriendOf item) {
        query = "INSERT INTO music_project.isfriendof(firstUser,secondUser) VALUES (?,?);";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, item.getFirstUser());
            preparedStatement.setInt(2, item.getSecondUser());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<IsFriendOf> getAll() {
        List<IsFriendOf> isFriendOfList = new ArrayList<>();

        IsFriendOf isFriendOf = new IsFriendOf(0,0);
        query = "SELECT * FROM music_project.isfriendof;";

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int indx = 0;
            while (resultSet.next()) {
                isFriendOfList.add(new IsFriendOf(resultSet.getInt("firstUser"),resultSet.getInt("secondUser")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isFriendOfList;
    }

    @Override
    public boolean update(IsFriendOf item) {
        query = "UPDATE music_project.isfriendof SET firstUser = ? , secondUser = ? ;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,item.getFirstUser());
            preparedStatement.setInt(2, item.getSecondUser());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean remove(IsFriendOf item) {
        query = "DELETE FROM music_project.isfriendof WHERE firstUser = ?;";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,item.getFirstUser());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Integer> getFriendsByUserId(int id){
        query = "  SELECT secondUser FROM music_project.isfriendof\n" +
                "  WHERE firstUser = " + id + ";";
        List<Integer> returnList = new ArrayList<>();

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                returnList.add(resultSet.getInt("secondUser"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;
    }

}
