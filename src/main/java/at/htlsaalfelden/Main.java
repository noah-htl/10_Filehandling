package at.htlsaalfelden;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileHandling fileHandling = new FileHandling("input.txt");
        List<CPU> cpus = new ArrayList<>();
        try {
            cpus = fileHandling.readFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(cpus);
    }
}