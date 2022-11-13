package UI;

import Global.Color;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

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