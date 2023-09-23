package ru.jucharick.lesson5.task_1;


import java.io.File;
import java.io.IOException;
import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;

public class Program {
    /**
     * Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup
     */

    public static void main(String[] args) throws IOException{
        backupDir(currentPath,BACKUP_PATH);
    }

    private static final String BACKUP_PATH = "./backup";
    private static String currentPath = "./src/main";

    private static void backupDir (String fromDir, String toDir) {
        try {
            Files.createDirectory(Path.of(toDir));
            copy(fromDir, toDir);
            //copyFolder(fromDir, toDir);
            System.out.println("Backup successfully");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void copy(String fromDir, String toDir) throws IOException{
        try (DirectoryStream<Path> dir = Files.newDirectoryStream(Path.of(fromDir))) {
            for (Path file : dir) {
                if (file.toString().equals(toDir)) continue;
                Files.copy(file, Path.of(toDir + file.toString().substring(fromDir.length())));
                System.out.println(file.toString());

                // запуталась с указанием пути
//                if (file.toFile().isDirectory()) {
//                    copy(file.toString(), toDir);
//                }
            }
        }
    }
}
