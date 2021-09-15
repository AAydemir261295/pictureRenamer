package sample;


import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteAfter {


    public void anySymbols(Path path, String count) {
        File oldName;
        File newName;
        int formatOfFileSymbols = 4 + Integer.parseInt(count);
        int lastDirectory = 0;
        boolean countOfDeletingIsLong = true;

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path abc : files) {

                StringBuilder stringBuilder = new StringBuilder();
                String tmp = abc.toString();
                String[] splittedPathToPicture = tmp.split("\\\\");
                lastDirectory = splittedPathToPicture.length - 1;
                countOfDeletingIsLong = splittedPathToPicture[splittedPathToPicture.length - 1].length() <= Integer.parseInt(count) + 4;

                if (countOfDeletingIsLong) {
                    continue;
                    }
                else {
                    if (splittedPathToPicture[splittedPathToPicture.length - 1].endsWith(".jpg")
                            || splittedPathToPicture[splittedPathToPicture.length - 1].endsWith(".png")
                                || splittedPathToPicture[splittedPathToPicture.length - 1].endsWith(".JPG")
                                    || splittedPathToPicture[splittedPathToPicture.length - 1].endsWith(".PNG")) {
                        for (int i = 0; i < splittedPathToPicture.length; i++) {
                            if (i == lastDirectory) {
                                splittedPathToPicture[i] = splittedPathToPicture[i];
                            } else {
                                splittedPathToPicture[i] = splittedPathToPicture[i] + "\\\\";
                            }
                            stringBuilder.append(splittedPathToPicture[i]);
                        }
                        oldName = new File(stringBuilder.toString());
                        stringBuilder.delete(stringBuilder.length() - formatOfFileSymbols, stringBuilder.length() - 4);
                        newName = new File(stringBuilder.toString());
                        oldName.renameTo(newName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}