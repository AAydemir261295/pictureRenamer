package sample;


import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteAfter {


    public void anySymbols(Path path, String count) {
        int symbolsLengthOfFormat = 4 + Integer.parseInt(count);
        int lastDirectory = 0;
        boolean countOfDeletingIsLong = true;

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path abc : files) {
                StringBuilder stringBuilder = new StringBuilder();
                String filePath = abc.toString();
                String[] splittedPathToPicture = filePath.split("\\\\");
                lastDirectory = splittedPathToPicture.length - 1;
                countOfDeletingIsLong = splittedPathToPicture[lastDirectory].length() <= Integer.parseInt(count) + 4;
                if (countOfDeletingIsLong) {
                    continue;
                } else {
                    if (isItPicture(splittedPathToPicture[splittedPathToPicture.length - 1])) {
                        for (int i = 0; i < splittedPathToPicture.length; i++) {
                            if (i == lastDirectory) {
                                splittedPathToPicture[i] = splittedPathToPicture[i];
                            } else {
                                splittedPathToPicture[i] = splittedPathToPicture[i] + "\\\\";
                            }
                            stringBuilder.append(splittedPathToPicture[i]);
                        }
                        renameIt(stringBuilder, symbolsLengthOfFormat);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isItPicture(String str) {
        return str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".JPG") || str.endsWith(".PNG");
    }

    private void renameIt(StringBuilder str, int formatSymbolsLength) {
        StringBuilder tmp = new StringBuilder(str);

        File oldName = new File(tmp.toString());
        tmp.delete(tmp.length() - formatSymbolsLength, tmp.length() - 4);
        File newName = new File(tmp.toString());
        oldName.renameTo(newName);
    }

}