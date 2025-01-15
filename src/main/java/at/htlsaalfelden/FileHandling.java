package at.htlsaalfelden;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public List<CPU> readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(this.file));

        List<CPU> result = new ArrayList<>();
        List<String> currentObject = null;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(line.equals("BEGIN")) {
                currentObject = new ArrayList<>();
            } else if(line.equals("END")) {
                try {
                    result.add(this.transform(currentObject));
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException | NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
                currentObject = null;
            } else {
                currentObject.add(line.substring(1));
            }
        }

        return result;
    }

    private CPU transform(List<String> strings) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        int id = Integer.parseInt(strings.getFirst().substring(4));

        Field[] fields = CPU.class.getDeclaredFields();

        Constructor<CPU> c = CPU.class.getDeclaredConstructor();
        c.setAccessible(true);
        CPU cpu = c.newInstance();

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            String n = fields[i].getName().toLowerCase();
            Class<?> type = fields[i].getType();

            Method method = type.getDeclaredMethod("valueOf", String.class);

            for(String s : strings) {
                String[] l = s.toLowerCase().strip().split("=");
                if(l[0].equals(n)) {
                    String v = l[1];

                    fields[i].set(cpu, method.invoke(null, v));
                }
            }
        }

        Field fid = CPU.class.getDeclaredField("id");
        fid.setAccessible(true);
        fid.set(cpu, id);

        return cpu;
    }
}
