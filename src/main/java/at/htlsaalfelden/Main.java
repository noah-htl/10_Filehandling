package at.htlsaalfelden;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileHandling fileHandling = new FileHandling("input.txt");
        List<CPU> cpus = new ArrayList<>();
        try {
            cpus = fileHandling.readFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        FileHandling writing = new FileHandling("output.txt");
        writing.write(cpus);
    }
}