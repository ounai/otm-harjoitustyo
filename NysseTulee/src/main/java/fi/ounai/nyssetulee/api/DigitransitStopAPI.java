package fi.ounai.nyssetulee.api;

import com.google.gson.GsonBuilder;
import fi.ounai.nyssetulee.domain.Stop;
import fi.ounai.nyssetulee.domain.TransitDataJsonDeserializer;

public class DigitransitStopAPI implements StopAPI {
    
    private final String apiUrl;

    public DigitransitStopAPI(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public Stop[] getStops(String name) throws Exception {
        String query = "{\n"
                    + "stops(name: \"" + name + "\") {\n"
                        + "name\n"
                        + "desc\n"
                        + "url\n"
                        + "code\n"
                    + "}\n"
                + "}";
        
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Stop[] serialized = new GsonBuilder()
                .registerTypeAdapter(Stop[].class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Stop[].class);
        
        return serialized;
    }
    
}
