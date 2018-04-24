package fi.ounai.nyssetulee.domain;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransitDataJsonDeserializerTest {
    
    @Test
    public void alertDeserializationWorksWithAlerts() {
        String json = "{\"data\": { \"alerts\": [ { \"alertHeaderText\": \"alertHeaderText test\", \"alertDescriptionText\": \"alertDescriptionText test\" } ] }}";
        
        Alert[] deserialized = new GsonBuilder()
                .registerTypeAdapter(Alert[].class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Alert[].class);
        
        Alert alert = deserialized[0];
        
        assertEquals("alertHeaderText test", alert.getAlertHeaderText());
        assertEquals("alertDescriptionText test", alert.getAlertDescriptionText());
    }
    
    @Test
    public void alertDeserializationWorksWithoutAlerts() {
        String json = "{\"data\": { \"alerts\": [] }}";
        
        Alert[] deserialized = new GsonBuilder()
                .registerTypeAdapter(Alert[].class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Alert[].class);
        
        assertEquals(0, deserialized.length);
    }
    
    @Test(expected = JsonParseException.class)
    public void alertDeserializationThrowsErrorOnBadJSON() {
        String json = "{\"data\": { \"alerts\": [ { \"alertHeaderText\": \"alertHeaderText test\", \"alertDescriptionText\": \"alertDescriptionText test\" } ], \"error\": \"bad data\" }}";
        
        new GsonBuilder()
                .registerTypeAdapter(Alert[].class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Alert[].class);
    }
    
}
