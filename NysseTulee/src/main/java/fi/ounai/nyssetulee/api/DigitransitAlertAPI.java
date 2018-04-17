package fi.ounai.nyssetulee.api;

import com.google.gson.GsonBuilder;
import fi.ounai.nyssetulee.domain.Alert;
import fi.ounai.nyssetulee.domain.TransitDataJsonDeserializer;

public class DigitransitAlertAPI implements AlertAPI {
    
    private final String apiUrl;

    public DigitransitAlertAPI(String apiUrl) {
        this.apiUrl = apiUrl;
    }
    
    @Override
    public Alert[] getAlerts() throws Exception {
        String query = "{\n"
                    + "alerts {\n"
                        + "alertHeaderText\n"
                        + "alertDescriptionText\n"
                    + "}\n"
                + "}";
        
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Alert[] deserialized = new GsonBuilder()
                .registerTypeAdapter(Alert[].class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Alert[].class);
        
        return deserialized;
    }
    
}
