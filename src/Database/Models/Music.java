package Database.Models;

public class Music {

    private int id;
    private String path;

    //Constructor
    public Music(int id, String path) {
        this.id = id;
        this.path = path;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    //toString
    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
