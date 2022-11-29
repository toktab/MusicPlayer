package UI;

import Database.DAO.UserDao;
import Global.Color;


import java.util.Objects;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        if(UI.getlogin()==null){
            String input="";
            for(int i=0; i<1; i=0) {
                System.out.println("go on LOG INN : ");
                input = scanner.nextLine();
                UI.login(";; " + input);
                if(UI.getlogin()!=null){
                    i=2;
                    break;
                }
                System.out.println("wrong log in try again!!!111!!!");
            }
            System.out.println("LOGGED in successfully");
            HomeUI.home(UI.getlogin(),args);
        }
        else
        HomeUI.home(UI.getlogin(),args);
        /*System.out.println(Color.YELLOW_BOLD);

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
            }
            else if(Objects.equals(input, ";q")){//quit
                quit=true;
            }else if(Objects.equals(input, ";i")){//info
                UI.info();
            }
            else if(Objects.equals(input, ";p ")){
                try{UI.Play("",args);}catch(Exception e){e.printStackTrace();}
            }
            else if(Objects.equals(input,"")){

            }

            else System.out.println("Unknown Command");
        }*/
    }
}