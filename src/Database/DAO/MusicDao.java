package Database.DAO;

import Database.Models.Music;

import java.util.List;

public class MusicDao implements DAO<Music>{
    @Override
    public boolean add(Music item) {
        return false;
    }

    @Override
    public List<Music> getAll() {
        return null;
    }

    @Override
    public boolean update(Music item) {
        return false;
    }

    @Override
    public boolean remove(Music item) {
        return false;
    }
}
