package at.htlsaalfelden;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtConverter implements IConverter<CPU> {
    @Override
    public String toString(List<CPU> object) {
        StringBuilder sb = new StringBuilder();
        for(CPU cpu : object) {
            sb.append("CPU:" + cpu.getId() + "\t\t" + cpu.getSpeed() + "\t\t" + cpu.getRam() + "\t\t" + cpu.getCache())
                    .append("\n");
        }

        return sb.toString();
    }

    @Override
    public List<CPU> fromString(String string) {
        String[] s = string.split("\n");

        List<CPU> result = new ArrayList<>();
        List<String> currentObject = null;

        for (int i = 0; i < s.length; i++) {
            String line = s[i];

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
