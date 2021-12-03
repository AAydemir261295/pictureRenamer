package sample;


import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteBefore {

    public void anySymbols(Path path, String count) {
        int endOfDeleting = Integer.parseInt(count);
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
                        StringBuilder resultPathString = new StringBuilder();
                        for (int i = 0; i < splittedPathToPicture.length; i++) {
                            if (i == lastDirectory) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(splittedPathToPicture[i]);
                                sb.delete(0, endOfDeleting);
                                resultPathString.append(stringBuilder + sb.toString());
                            } else {
                                splittedPathToPicture[i] = splittedPathToPicture[i] + "\\\\";
                            }
                            stringBuilder.append(splittedPathToPicture[i]);

                        }
                        renameIt(stringBuilder, resultPathString);
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

    private void renameIt(StringBuilder oldPath, StringBuilder newPath) {
        File oldName = new File(oldPath.toString());
        File newName = new File(newPath.toString());
        oldName.renameTo(newName);
    }
}