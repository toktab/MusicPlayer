package UI;

import Database.DAO.MusicDao;
import Database.Models.Music;
import Global.Color;

import java.util.List;
import java.util.Objects;

public class HomeCommands {

    private static int countSpace(String input){
        int cnt = 0;
        //check if input format is correct
        //check amount of spaces (should be 2)
        for(int i = 0; i < input.length();i++){
            if(input.charAt(i)==' ') cnt++;
        }
        return cnt;
    }


    public static boolean checkMusic(int id){
        MusicDao musicDao = new MusicDao();
        List<Music> musicList = musicDao.getAll();
        for(int i = 0; i < musicList.size();i++){
//            System.out.println(musicList.get(i).getId() +  " " + musicList.get(i).getName());
            if(id==musicList.get(i).getId()){
                return true;
            }
        }

        return false;
    }

    public static void music(String input) {

        if (countSpace(input) > 2 || input.equals("")) {
            System.out.println(Color.RED + "\n• Syntax Error •\n" + Color.YELLOW_BOLD);
            return;
        }

        // ;m -play 123

        if (input.equals(";m")) {
            //current music
        } else if (input.equals(";m -stop")) {
            //stop music
        } else if (countSpace(input) == 2 && input.substring(0, 8).equals(";m -play")) {
            int musicId = Integer.parseInt((input.substring(9, input.length())));

            if (!(checkMusic(musicId))) {
                System.out.println(Color.RED + "\n• Wrong Music ID •\n" + Color.YELLOW_BOLD);
            }
            //

        } else if (countSpace(input) == 2 && input.substring(0, 7).equals(";m -add")) {
            int musicId = Integer.parseInt((input.substring(8, input.length())));
            System.out.println(musicId);

            //
        }
    }

    public static void friend(String input) {

        if (countSpace(input) > 2 || input.equals("")) {
            System.out.println(Color.RED + "\n• Syntax Error •\n" + Color.YELLOW_BOLD);
            return;
        } else if (input.equals(";f")) {
            //check currently online friends

        } else if (input.equals(";f -all")) {
            //check all friends
        } else if (input.equals(";f -status")) {
            //check what online friends are listening to
        } else if (countSpace(input) == 1 && input.substring(0, 2).equals(";f")) {
            System.out.println("add f");
            String usernameToAdd = input.substring(3, input.length());
            System.out.println(usernameToAdd);
        } else if (countSpace(input) == 2 && input.substring(0, 10).equals(";f -remove")) {
            System.out.println("remove f");
            String usernameToRemove = input.substring(11, input.length());
            System.out.println(usernameToRemove);
        }

    }
}
