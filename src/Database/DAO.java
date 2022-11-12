package Database;

import java.util.List;

public interface DAO <T>{
    //add
    public boolean add(T item);
    //getAll
    public List<T> getAll();
    //update
    public boolean update(T item);
    //remove
    public boolean remove(T item);
}
