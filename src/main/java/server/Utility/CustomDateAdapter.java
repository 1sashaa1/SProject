package server.Utility;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomDateAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.forLanguageTag("ru"));

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return dateFormat.parse(json.getAsString());
        } catch (ParseException e) {
            throw new JsonParseException("Failed to parse date: " + json.getAsString(), e);
        }
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(dateFormat.format(src));
    }
}
