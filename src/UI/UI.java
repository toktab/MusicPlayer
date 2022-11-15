package UI;

import Database.DAO.UserDao;
import Global.Color;
import Global.SQL;
import Database.Models.User;

import java.util.List;
import java.util.Objects;

public class UI {
    private static UserDao userDao = new UserDao();

    private static int indx = 0;

    private static String parseUsername(String input){
        String username = "";
        for(int i = 3; i < input.length(); i++){
            if(input.charAt(i)==' '){
                indx = i + 1;
                break;

            }else username = username + input.charAt(i);
        }
        return username;
    }

    private static String parsePassword(String input){
        String password = "";
        for(int i = indx; i < input.length(); i++){
            if(input.charAt(i)==' '){

            }else password = password + input.charAt(i);
        }
        return password;
    }

    private static boolean checkFormat(String input){
        int cnt = 0;
        //check if input format is correct
        //check amount of spaces (should be 2)
        for(int i = 0; i < input.length();i++){
            if(input.charAt(i)==' ') cnt++;
        }

        if(cnt!=2){
            System.out.println(Color.RED + "\n• ;l / ;r (USERNAME) (PASSWORD) •\n" + Color.YELLOW_BOLD);
            return false;
        }
        return true;
    }

    static void help(){
        System.out.print(Color.CYAN_BOLD);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+  • Register -> ;r (USERNAME) (PASSWORD) +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+  • Login -> ;l (USERNAME) (PASSWORD)    +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+  • Quit -> ;q                           +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+  • Info -> ;i                           +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++" + Color.YELLOW_BOLD);
    }

    static void register(String input){

        if(!checkFormat(input)) return;

        String username = parseUsername(input);
        String password = parsePassword(input);

//        System.out.println("regUsername:"+username);
//        System.out.println("regPassword:"+password);

        User user = new User(getAvailableId(),username,password);

        if(!userDao.add(user)){
            System.out.println(Color.CYAN + "Username Already Registered" + Color.YELLOW_BOLD);
        }
    }
    static User userExist(String username, String password){
        List<User> userList = userDao.getAll();

        for(int i = 0; i < userList.size(); i++){
            if(Objects.equals(userList.get(i).getUsername(), username) && Objects.equals(userList.get(i).getPassword(), password.hashCode() + "")){
                return userList.get(i);
            }
        }
        return null;
    }

    static void login(String input){

        if(!checkFormat(input)) return;

        String username = parseUsername(input);
        String password = parsePassword(input);

        User user = userExist(username,password);

        if(user==null){
            System.out.println(Color.RED_BOLD + "Wrong Credentials\n" + Color.YELLOW_BOLD);
            return;
        }

        //todo Login System

        System.out.println("Logged In");
        //aq after login iwyeeba wesit

        //
    }

    static void info(){
        System.out.println(Color.GREEN_BOLD);
        System.out.println("Custom Built Music Player\n12:52AM 11/7/2022\n" + Color.YELLOW_BOLD);
    }

    static int getAvailableId(){//think it works pretty well :))
        List<User> userList = userDao.getAll();
        int indx = 1; // 2 4
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getId()==indx){
                indx++;
            }else break;
        }
        return indx;
    }

}
