package DAO_User;

import Models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUser();
    void updateUser(User user);
    void addUser(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByPassword(String password);
    void deleteUser(User user);

}
