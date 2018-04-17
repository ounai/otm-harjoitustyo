package fi.ounai.nyssetulee.api;

import com.google.gson.GsonBuilder;
import fi.ounai.nyssetulee.domain.Route;
import fi.ounai.nyssetulee.domain.TransitDataJsonDeserializer;

public class DigitransitRouteAPI implements RouteAPI {
    
    private final String apiUrl;

    public DigitransitRouteAPI(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public Route[] getRoutes(String name) throws Exception {
        String query = "{\n"
                    + "routes(name: \"" + name + "\") {\n"
                        + "shortName\n"
                        + "longName\n"
                    + "}\n"
                + "}";
        
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Route[] serialized = new GsonBuilder()
                .registerTypeAdapter(Route[].class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Route[].class);
        
        return serialized;
    }

    @Override
    public Route[] getRoutes(String name, String modes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
