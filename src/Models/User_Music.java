package Models;

public class User_Music {

    private int user_id;
    private int music_id;

    //Constructor
    public User_Music(int user_id, int music_id) {
        this.user_id = user_id;
        this.music_id = music_id;
    }

    //Getters and Setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    //toString
    @Override
    public String toString() {
        return "User_Music{" +
                "user_id=" + user_id +
                ", music_id=" + music_id +
                '}';
    }
}
