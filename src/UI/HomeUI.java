package UI;

import Database.Models.*;
import Database.DAO.*;
import Global.*;

import UI.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;



public class HomeUI {

    private static final Scanner scanner = new Scanner(System.in);

    static int parsePlayMusic(String input) {//;p 123
        return Integer.parseInt(input.substring(3, input.length()));
    }

    static boolean checkFormat(String input) {// ;a 1
        int cnt = 0;
        //check if input format is correct
        //check amount of spaces (should be 2)
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') cnt++;
        }

        if (cnt != 1) {
            System.out.println(Color.RED + "\n• ;p / ;a / ;d (MUSIC_ID) •\n" + Color.YELLOW_BOLD);
            return false;
        }
        return true;
    }

    static void helpHome() {
        System.out.print(Color.CYAN_BOLD);
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println("  • Play -> ;p (MUSIC_ID)         +");
        System.out.println("  • Friends -> ;f                 +");
        System.out.println("  • Check Available Music -> ;c   +");
        System.out.println("  • My Music -> ;m                +");
        System.out.println("  • Add Music -> ;a (MUSIC_ID)    +");
        System.out.println("  • Delete Music -> ;d (MUSIC_ID) +");
        System.out.println("  • Exit account -> ;e            +");
        System.out.println("  • Quit -> ;q                    +");
        System.out.println("+++++++++++++++++++++++++++++++++++" + Color.YELLOW_BOLD);
    }


    static void playMusic(String input) {

        if (!checkFormat(input)) return;

        //
        int id = parsePlayMusic(input);

        System.out.println(id);

        //
        play("src/Music/Recording (2).wav");
        //

    }

    static void play(String path) {

    }

    static void home(User user, String[] args) {

        System.out.println("logged in ass : "+user.toString() );
        boolean quit = false;
        String input = null;
        System.out.println(Color.YELLOW + "\n For more help -> ;h" + Color.YELLOW_BOLD);

        while (!quit) {//p 1

            System.out.print("\n:");
            input = scanner.nextLine();
            if (input.isEmpty()) {
                continue;
            } else if (Objects.equals(input, ";h")) {//help
                helpHome();
            } else if (Objects.equals(input.substring(0, 2), ";p")) {//play
                playMusic(input);
            } else if (Objects.equals(input, ";f")) {//friends
                helpHome();
            } else if (Objects.equals(input, ";c")) {//check music
                helpHome();
            } else if (Objects.equals(input, ";m")) {//my music
                helpHome();
            } else if (Objects.equals(input.substring(0, 2), ";a")) {//add music

            } else if (Objects.equals(input.substring(0, 2), ";d")) {//delete music
                helpHome();
            } else if (Objects.equals(input, ";e")) {//exit account
                UI.logout();
            } else if (Objects.equals(input, ";quit")) {//quit
                quit = true;
            } else if (Objects.equals(input, ";p")) {//play mus
                try{UI.Play("s", args);}catch(Exception e){e.printStackTrace();}
            }
            else if(Objects.equals(input,";lo")){//log out
                UI.logout();
            }
            else if (Objects.equals(input.substring(0, 2), ";l")) {//you forgor teh login
                try{UI.login(input);}catch(Exception e){e.printStackTrace();}
            }
            else System.out.println("Unknown Command");
        }


    }
}