package sample.maket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Data;
import javafx.scene.control.Button;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.stage.DirectoryChooser;
import sample.DeleteAfter;
import sample.DeleteBefore;
import sample.FileRenamer;


@Data
public class Controller {

    @FXML
    private TextField pathField;
    @FXML
    private TextField beforeCount;
    @FXML
    private TextField afterCount;

    private FileRenamer fileRenamer;

    private DeleteAfter deleteAfter = new DeleteAfter();
    private DeleteBefore deleteBefore = new DeleteBefore();

    private String directoryName;
    private String before;
    private String after;

    @FXML
    private void directoryChoose() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выбери папку, мам");
        File dir = directoryChooser.showDialog(fileRenamer.getPrimaryStage());
        fileRenamer.setDirPath(dir.toPath());
        directoryName = dir.toPath().toString();
        pathField.setText(directoryName);

    }

    public void setFileRenamer(FileRenamer appFX) {
        this.fileRenamer = appFX;
    }


    @FXML
    private void accept() {
        before = beforeCount.getText();
        after = afterCount.getText();

        if (before.equalsIgnoreCase("") || before.equalsIgnoreCase("0")) {
            if (after != null) {
                deleteAfter.anySymbols(fileRenamer.getDirPath(), after);
            }
        }
        else if (after.equalsIgnoreCase("") || after.equalsIgnoreCase("0")) {
            if (before != null) {
                deleteBefore.anySymbols(fileRenamer.getDirPath(), before);
            }
        }
    }
}
