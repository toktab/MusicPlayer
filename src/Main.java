import java.util.Objects;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean quit = false;
        String input = null;
        System.out.println("\n For help -> ;h");

        while (!quit) {

            System.out.print(":");
            input = scanner.nextLine();

            if(Objects.equals(input, ";h")){//help
                UI.help();
            }else if(Objects.equals(input, ";r")){//register
                UI.register();
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