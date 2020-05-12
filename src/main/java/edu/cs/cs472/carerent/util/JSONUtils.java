package edu.cs.cs472.carerent.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JSONUtils {

    public static JsonSerializer<LocalDate> getJsonLocalDateSerializer() {
        return new JsonSerializer<LocalDate>() {
            @Override
            public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                return null;
            }
        };
    }

    public static JsonDeserializer<LocalDate> getJsonLocalDateDeserializer() {
        return new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException, DateTimeParseException {
                return json == null ? null : LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_DATE);
            }
        };
    }
}
