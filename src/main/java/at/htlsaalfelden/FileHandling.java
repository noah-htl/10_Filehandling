package at.htlsaalfelden;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandling {
    private final String filename;
    private final File file;

    public FileHandling(String filename) {
        this.filename = filename;
        this.file = new File(filename);
    }

    public List<CPU> readFile(IConverter<CPU> converter) throws IOException {
        return converter.fromString(Files.readString(this.file.toPath()));
    }

    public void write(IConverter<CPU> converter, List<CPU> cpus) throws IOException {
        FileWriter fileWriter = new FileWriter(this.file);
        fileWriter.write(converter.toString(cpus));

        fileWriter.close();
    }
}
