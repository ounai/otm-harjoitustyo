package fi.ounai.nyssetulee.api;

import com.google.gson.GsonBuilder;
import fi.ounai.nyssetulee.domain.Route;
import fi.ounai.nyssetulee.domain.TransitDataJsonDeserializer;

public class DigitransitRouteAPI implements RouteAPI {
    
    private String apiUrl;

    public DigitransitRouteAPI(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public Route[] getRoutes(String name) throws Exception {
        String query = "{\n"
                    + "routes(name: \"" + name + "\") {\n"
                        + "gtfsId\n"
                        + "shortName\n"
                        + "longName\n"
                        + "mode\n"
                    + "}\n"
                + "}";
        
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Route[] deserialized = new GsonBuilder()
                .registerTypeAdapter(Route[].class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Route[].class);
        
        return deserialized;
    }

    @Override
    public Route[] getRoutes(String name, String modes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Route getRoute(String gtfsId) throws Exception {
        String query = "{\n"
                    + "route(id: \"" + gtfsId + "\") {\n"
                        + "gtfsId\n"
                        + "shortName\n"
                        + "longName\n"
                        + "mode\n"
                        + "stops {\n"
                            + "name\n"
                            + "desc\n"
                            + "url\n"
                            + "code\n"
                            + "gtfsId\n"
                        + "}\n"
                    + "}\n"
                + "}";
        
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Route deserialized = new GsonBuilder()
                .registerTypeAdapter(Route.class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Route.class);
        
        return deserialized;
    }
    
}
