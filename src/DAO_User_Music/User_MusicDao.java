package DAO_User_Music;

import Models.User_Music;

import java.util.List;

public interface User_MusicDao {

    List<User_Music> getAllUser_Music();
    void updateUser_Music(User_Music user_music);
    void addUser_Music(User_Music user_music);
    User_Music getUser_MusicByUser_id(int User_id);
    User_Music getUser_MusicByMusic_id(int Music_id);
    void deleteUser_Music(User_Music user_music);

}
