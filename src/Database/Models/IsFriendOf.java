package Database.Models;

public class IsFriendOf {
    private int firstUser;
    private int secondUser;

    public IsFriendOf(int firstUser, int secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    public int getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(int firstUser) {
        this.firstUser = firstUser;
    }

    public int getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(int secondUser) {
        this.secondUser = secondUser;
    }

    @Override
    public String toString() {
        return "IsFriendOf{" +
                "firstUser=" + firstUser +
                ", secondUser=" + secondUser +
                '}';
    }
}
