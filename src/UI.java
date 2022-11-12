import Database.UserDao;
import Global.Color;
import Global.SQL;
import Models.User;

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

        System.out.println("regUsername:"+username);
        System.out.println("regPassword:"+password);

        User user = new User(SQL.getNextId(),username,password);

//        SQL.addUser(user);
        userDao.add(user);
    }

    static void login(String input){

        if(!checkFormat(input)) return;

        String username = parseUsername(input);
        String password = parsePassword(input);

        System.out.println("Username:"+username);
        System.out.println("Password:"+password);

    }

    static void info(){
        System.out.println(Color.GREEN_BOLD);
        System.out.println("Custom Built Music Player\n12:52AM 11/7/2022\n" + Color.YELLOW_BOLD);
    }

}
