package UI;

import Database.Models.User;
import Global.Color;
import Network.Client.Client;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;
import java.applet.*;



public class HomeUI {

    private static final Scanner scanner = new Scanner(System.in);

    static int parsePlayMusic(String input){//;p 123
        return Integer.parseInt(input.substring(3,input.length()));
    }

    static boolean checkFormat (String input){// ;a 1
        int cnt = 0;
        //check if input format is correct
        //check amount of spaces (should be 2)
        for(int i = 0; i < input.length();i++){
            if(input.charAt(i)==' ') cnt++;
        }

        if(cnt!=1){
            System.out.println(Color.RED + "\n• ;p / ;a / ;d (MUSIC_ID) •\n" + Color.YELLOW_BOLD);
            return false;
        }
        return true;
    }

    static void helpHome(){
        System.out.print(Color.CYAN_BOLD);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("  • Play Music -> ;m -play (MUSIC_ID)        +");
        System.out.println("  • Stop Music -> ;m -stop                   +");
        System.out.println("  • Add new Music -> ;m -add (MUSIC_NAME)    +");
        System.out.println("  • Find currently played Music -> ;m        +");
        System.out.println("  • Friends -> ;f -all                       +");
        System.out.println("  • Check Friends status -> ;f -status       +");
        //                                              ;f (name) -status
        System.out.println("  • Add new Friend -> ;f (USERNAME)          +");
        System.out.println("  • Remove Friend -> ;f -remove (USERNAME)   +");
        System.out.println("  • Exit account -> ;e                       +");
        System.out.println("  • Quit -> ;q                               +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++" + Color.YELLOW_BOLD);
    }


    static void home(User user){

        boolean quit = false;
        String input = null;
        System.out.println(Color.YELLOW + "\n For more help -> ;h" + Color.YELLOW_BOLD);

        Client client = new Client(user);
        try {
            client.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        while (!quit) {//p 1

            System.out.print("\n:");
            input = scanner.nextLine();
            if(input.isEmpty()){
                continue;
            }
            else if(input.charAt(0)!=';'){
                System.out.println("Unknown Command");
            }
            else if(Objects.equals(input, ";h")){//help
                helpHome();
            }
            else if(Objects.equals(input.substring(0,2), ";m")){//play # stop # music
                HomeCommands.music(input);
            }
            else if(Objects.equals(input.substring(0,2), ";f")){//check music
                HomeCommands.friend(input);
            }
            else if(Objects.equals(input, ";e")){//exit account
                helpHome();
            }
            else if(Objects.equals(input, ";q")){//quit
                quit=true;
            }
            else System.out.println("Unknown Command");
        }
    }



}
