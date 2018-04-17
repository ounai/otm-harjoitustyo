package fi.ounai.nyssetulee.domain;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 * Deserializes data returned from Digitransit API
 */

public class TransitDataJsonDeserializer<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonElement data = je.getAsJsonObject().get("data");
        JsonObject obj = data.getAsJsonObject();
        
        // Check that there is exactly one element in the key set of data
        if (obj.keySet().size() != 1) {
            throw new JsonParseException("Expected json object to have exactly one element but has " + obj.keySet().size());
        }
        
        // Get first element of data
        for (String element : obj.keySet()) {
            data = obj.get(element);
            break;
        }
        
        return new Gson().fromJson(data, type);
    }
    
}
