package at.htlsaalfelden;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONConverter implements IConverter<CPU> {
    @Override
    public String toString(List<CPU> object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    @Override
    public List<CPU> fromString(String string) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CPU>>(){}.getType();
        return gson.fromJson(string, listType);
    }
}
