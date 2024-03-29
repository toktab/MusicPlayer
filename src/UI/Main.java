package UI;

import Global.Color;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {//delete problem
        //todo add after login

//        ThreadServer threadServer = new ThreadServer();
//        ThreadClient threadClient = new ThreadClient();
//        ThreadClient threadClient1 = new ThreadClient();
//        threadServer.start();
//        threadClient.start();
//        threadClient1.start();



//        UserService userService = new UserService(new UserDao());
//        User user = userService.getUserById(1);
//        IsFriendOfService isFriendOfService = new IsFriendOfService(new IsFriendOfDao(),new UserDao());
//        System.out.println(isFriendOfService.getFriendsByUser(user));


        System.out.println(Color.YELLOW_BOLD);

        boolean quit = false;
        String input = null;
        System.out.println(Color.YELLOW + "\n For help -> ;h" + Color.YELLOW_BOLD);

        while (!quit) {

            System.out.print("\n:");
            input = scanner.nextLine();
            if(input.isEmpty()){
                continue;
            }else if(Objects.equals(input, ";h")){//help
                UI.help();
            }else if(Objects.equals(input.substring(0,2), ";r")){//register
                UI.register(input);
            }else if(Objects.equals(input.substring(0,2), ";l")){//login
                UI.login(input);
            }else if(Objects.equals(input, ";q")){//quit
                quit=true;
            }else if(Objects.equals(input, ";i")){//info
                UI.info();
            }
            else System.out.println("Unknown Command");
        }
    }
}