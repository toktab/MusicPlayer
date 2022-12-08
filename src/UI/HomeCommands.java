package UI;

import Database.DAO.MusicDao;
import Database.Models.Music;
import Database.Models.User;
import Global.Color;
import Network.Client.Client;
import Network.Server.ThreadServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static void music(String input, User user) {

        if (countSpace(input) > 2 || input.equals("")) {
            System.out.println(Color.RED + "\n• Syntax Error •\n" + Color.YELLOW_BOLD);
            return;
        }

        // ;m -play 123

        if (input.equals(";m")) {
            //current music

        } else if (input.substring(0, 8).equals(";m -stop")) {

//            int musicId = Integer.parseInt((input.substring(9, input.length())));

//            if (!(checkMusic(musicId))) {
//                System.out.println(Color.RED + "\n• Wrong Music ID •\n" + Color.YELLOW_BOLD);
//            }

            Client client = new Client(user);
            client.updateCurrentMusic(-1, user.getId(),false);
            MusicDao musicDao = new MusicDao();
            List<Music> musicList = musicDao.getAll();
            Music currentMusic = new Music(-1,null);
            for(int i = 0 ; i < musicList.size(); i++){
                if(musicList.get(i).getId()==-1){
                    currentMusic = musicList.get(i);
                    break;
                }
            }
            System.out.println(Color.CYAN_BOLD + "Your Activity Has been cleared!" + Color.YELLOW_BOLD);
//            Client client = new Client(user);
//            client.updateCurrentMusic(musicId, user.getId(),false);

//            HashMap<User, Music> info = ThreadServer.getInfo();
//            HashMap<Integer,Integer> hash = ThreadServer.getActivity();
//            User currentUser = new User(-1,null,null);
//            Music currentMusic = new Music(-1,null);
//            for(User u : info.keySet()){
//                currentUser = u;
//                currentMusic = info.get(u);
//                System.out.println("Current User: " + currentUser.getId());
//                System.out.println("Current Music: " + currentMusic.getId());
//                System.out.println();
//            }
//            Client client = new Client(user);
//            client.updateCurrentMusic(musicId, user.getId(),false);

            //stop music
        } else if (countSpace(input) == 2 && input.substring(0, 8).equals(";m -play")) {
            int musicId = Integer.parseInt((input.substring(9, input.length())));

            if (!(checkMusic(musicId))) {
                System.out.println(Color.RED + "\n• Wrong Music ID •\n" + Color.YELLOW_BOLD);
            }

            Client client = new Client(user);
            client.updateCurrentMusic(musicId, user.getId(),true);
            MusicDao musicDao = new MusicDao();
            List<Music> musicList = musicDao.getAll();
            Music currentMusic = new Music(-1,null);
            for(int i = 0 ; i < musicList.size(); i++){
                if(musicList.get(i).getId()==musicId){
                    currentMusic = musicList.get(i);
                    break;
                }
            }
            System.out.println("You are Currently listening to:");
            System.out.println(currentMusic.getName());
            //

        } else if (countSpace(input) == 2 && input.substring(0, 7).equals(";m -add")) {
            String musicName = (input.substring(8, input.length()));
            System.out.println();
            MusicDao musicDao = new MusicDao();
            List<Music> musicList = musicDao.getAll();
            for(int i = 0 ; i < musicList.size(); i++){
                if(Objects.equals(musicList.get(i).getName(), musicName)){
                    System.out.println("Music Already exists!");
                    return;
                }
            }
            musicDao.add(new Music(getAvailableId(),musicName));
            System.out.println("Successfully added new Music!");
            //
        }
    }

    static int getAvailableId(){//think it works pretty well :))
        MusicDao musicDao = new MusicDao();
        List<Music> musicList = musicDao.getAll();
        int indx = 1; // 2 4
        for(int i = 0; i < musicList.size(); i++){
            if(musicList.get(i).getId()==indx){
                indx++;
            }else break;
        }
        return indx;
    }

    public static void friend(String input, User user) {

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
