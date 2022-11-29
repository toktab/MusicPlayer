package Service;

import Database.DAO.UserDao;
import Database.Models.User;

import java.util.List;
import java.util.Objects;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = new UserDao();
    }

    public User getUserByUsername(String username){
        List<User> userList = userDao.getAll();

        for(int i =0; i < userList.size(); i++){
            if(Objects.equals(userList.get(i).getUsername(), username)){
                return userList.get(i);
            }
        }
        return null;
    }
    public User getUserById(int id){
        List<User> userList = userDao.getAll();

        for(int i =0; i < userList.size(); i++){
            if(userList.get(i).getId()==id){
                return userList.get(i);
            }
        }
        System.out.println("return null getuserByID");
        return null;
    }

}
