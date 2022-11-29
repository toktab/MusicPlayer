package Service;

import Database.DAO.IsFriendOfDao;
import Database.DAO.UserDao;
import Database.Models.IsFriendOf;
import Database.Models.User;
import Global.Color;

import java.util.ArrayList;
import java.util.List;

public class IsFriendOfService {

    private IsFriendOfDao isFriendOfDao;
    private UserService userService;

    public IsFriendOfService(IsFriendOfDao isFriendOfDao, UserDao userDao) {
        this.isFriendOfDao = new IsFriendOfDao();
        this.userService = new UserService(userDao);
    }

    public void addFriend(User connectedUser, String username){
        IsFriendOf newFriend = new IsFriendOf(connectedUser.getId(),userService.getUserByUsername(username).getId());
        if(!isFriendOfDao.add(newFriend)){
            System.out.println(Color.RED + "Already Added!" + Color.YELLOW_BOLD);
        }else System.out.println(Color.GREEN + "Successfully Added!" + Color.YELLOW_BOLD);
    }

    public void removeFriend(User connectedUser, String username){
        IsFriendOf newFriend = new IsFriendOf(connectedUser.getId(),userService.getUserByUsername(username).getId());
        if(!isFriendOfDao.remove(newFriend)){
            System.out.println(Color.RED + "Couldn't Remove!" + Color.YELLOW_BOLD);
        }else System.out.println(Color.GREEN + "Successfully Removed!" + Color.YELLOW_BOLD);
    }
    public List<User> getFriendsByUser(User user){
        List<Integer> friendList = isFriendOfDao.getFriendsByUserId(user.getId());
        List<User> userList = new ArrayList<>();
        for(int i = 0 ; i < friendList.size(); i++){
            userList.add(userService.getUserById(friendList.get(i)));
        }
        return userList;
    }

}
