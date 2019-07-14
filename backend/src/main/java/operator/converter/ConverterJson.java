package operator.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConverterJson {
    public static Object jsonToObjectDefault(String json, Class classObject){
        Gson gson = new GsonBuilder().create();
        return toObjectAndValidation(gson, json, classObject);

    }

    private static Object toObjectAndValidation(Gson gson, String json, Class classObject){

        Class<classObject> a = gson.fromJson(json, classObject);
       return
    }
}
