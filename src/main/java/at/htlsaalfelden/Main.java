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


        FileHandling jsonOut = new FileHandling("output.json");
        jsonOut.writejson(cpus);

        List<CPU> cpus2 = jsonOut.readJson();

        if(cpus.size() != cpus2.size()) {
            throw new RuntimeException("Not same size");
        }

        for (CPU cpu : cpus) {
            if(!cpus2.contains(cpu)) {
                throw new RuntimeException(cpu + " not in cpus2");
            }
        }

        for (CPU cpu : cpus2) {
            if(!cpus.contains(cpu)) {
                throw new RuntimeException(cpu + " not in cpus");
            }
        }

        System.out.println("Same List :)");
    }
}