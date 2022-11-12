package DAO_Music;

import Models.Music;

import java.util.List;

public interface MusicDao {

    List<Music> getAllMusic();
    void updateMusic(Music music);
    void addMusic(Music music);
    Music getMusicById(int id);
    Music getMusicByPath(String path);
    void deleteMusic(Music music);

}
