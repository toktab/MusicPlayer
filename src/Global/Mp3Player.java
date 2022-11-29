package Global;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Mp3Player extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Mp3Player.class.getResource("player.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Mp3Player");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
        System.out.println("sex 'now'");
    }

}