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
            //Files.createDirectory(Path.of(toDir));
            //copy(fromDir, toDir);
            copyDir(fromDir, toDir);
            System.out.println("Backup successfully");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Метод, копирующий директорию со всеми фалами и поддиректориями в указанную папку
     * @param fromDir - что скопировать
     * @param toDir - куда скопировать
     * @throws IOException
     */
    public static void copy(String fromDir, String toDir) throws IOException{
        try (DirectoryStream<Path> dir = Files.newDirectoryStream(Path.of(fromDir))) {
            for (Path file : dir) {
                toDir = toDir        +  file.toString().substring(fromDir.length());
                // .\backup          +  \java\ru
                // .\backup\java\ru  +  \java\ru\jucharick
                Files.copy(file, Path.of(toDir));
                System.out.println(file.toString());

                //запуталась с указанием пути
                if (Files.isDirectory(file)) {
                    toDir = toDir  +  file.toString().substring(fromDir.length());
                    //    .\backup +  \java\ru\
                    copy(file.toString(), toDir);
                }
            }
        }
    }

    /**
     * Метод, копирующий директорию со всеми фалами и поддиректориями в указанную папку
     * Files.walk returns a stream that is lazily populated with Path by recursively walking the file tree rooted 
     * at a given starting file. The file tree is traversed depth-first. There are two overloaded Files.walk methods; 
     * one of them takes the maxDepth parameter, which sets the maximum number of levels of directories to visit.
     * @param fromDir - что скопировать 
     * @param toDir - куда скопировать
     * @throws IOException
     */

    public static void copyDir(String fromDir, String toDir) throws IOException {
        Files.walk(Paths.get(fromDir))
            .forEach(from -> {
                Path to = Paths.get(toDir, from.toString().substring(fromDir.length()));
                try {
                    Files.copy(from, to);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }
}
