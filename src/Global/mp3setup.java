package Global;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javafx.scene.control.Label;
import java.net.*;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class mp3setup  implements Initializable {


    @FXML
    private Pane pane;
    @FXML
    private Button play_button,pause_button,reset_button,exitbutton;
    @FXML
    private Label song_label;
    @FXML
    private ProgressBar progress_bar;
    @FXML
    private Slider volume;

    private Media media;
    private MediaPlayer mediaPlayer;
    private File direct;
    private Timer timer;
    private TimerTask timertask;
    private boolean running;


    public File getDirect(){
        return direct;
    }
    public void setDirect(File direct){
        this.direct=direct;
    }

    @Override
    public void initialize(URL url, ResourceBundle arg1){
        direct=new File("C:\\Users\\Administrator\\Desktop\\MusicPlayer-toko\\src\\Database\\music\\Daft Punk – Veridis Quo.mp3");

        media=new Media(direct.toURI().toString());
        mediaPlayer=new MediaPlayer(media);
        song_label.setText(direct.getName());
    }
    public void initialize(URL url, ResourceBundle arg1,String path){
        direct=new File(path);

        media=new Media(direct.toURI().toString());
        mediaPlayer=new MediaPlayer(media);
        song_label.setText(direct.getName());
    }

    public void playMedia(){
        mediaPlayer.play();
        begin_timer();
    }
    public void pauseMedia(){
        mediaPlayer.pause();
        stop_timer();
    }
    public void resetMedia(){
        mediaPlayer.seek(Duration.seconds(0.0));
    }
    public void exitNOW(){

    }
    public void begin_timer(){
        timer =new Timer();
        timertask=new TimerTask(){
            public void run(){
                boolean running;
                double current =mediaPlayer.getCurrentTime().toSeconds();
                double end=media.getDuration().toSeconds();
                //System.out.println(current/end);
                progress_bar.setProgress(current/end);
                if(current/end==1){
                    stop_timer();
                }
            }
        };
        timer.scheduleAtFixedRate(timertask,1000,1000);

    }
    public void stop_timer(){
        running=false;
        timer.cancel();
    }

    public static void main(String[] args) {



    }

}