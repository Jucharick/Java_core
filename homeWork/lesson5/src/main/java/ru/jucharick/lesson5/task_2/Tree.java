package ru.jucharick.lesson5.task_2;
import java.io.File;
public class Tree {
    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    /**
     * @param file
     * @param indent
     * @param isLast
     */
    public static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else{
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int counter = 0;
        for (int i = 0; i < files.length; i++){
            counter++;
            print(files[i], indent, counter == files.length);
        }
    }
}
