package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Data;
import sample.maket.Controller;


import java.io.IOException;
import java.nio.file.Path;

@Data
public class FileRenamer extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    private Path dirPath;



    public static void main(String[] args) {

        Application.launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FileRenamer");
        showBaseWindow();

    }
    public void showBaseWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FileRenamer.class.getResource("maket/sample.fxml"));
            this.rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            Controller controller = loader.getController();
            controller.setFileRenamer(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
