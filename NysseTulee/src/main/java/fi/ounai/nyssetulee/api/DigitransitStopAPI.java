package fi.ounai.nyssetulee.api;

import com.google.gson.GsonBuilder;
import fi.ounai.nyssetulee.domain.Stop;
import fi.ounai.nyssetulee.domain.Stoptime;
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
                        + "gtfsId\n"
                    + "}\n"
                + "}";
        
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Stop[] deserialized = new GsonBuilder()
                .registerTypeAdapter(Stop[].class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Stop[].class);
        
        return deserialized;
    }

    @Override
    public Stoptime[] getStoptimes(String stopGtfsId) throws Exception {
        String query = "{\n"
                    + "stop(id: \"" + stopGtfsId + "\") {\n"
                        + "stoptimesWithoutPatterns {\n"
                            + "scheduledArrival\n"
                            + "scheduledDeparture\n"
                            + "realtimeArrival\n"
                            + "realtimeDeparture\n"
                            + "arrivalDelay\n"
                            + "departureDelay\n"
                            + "timepoint\n"
                            + "realtime\n"
                            + "trip {\n"
                                + "gtfsId\n"
                                + "tripHeadsign\n"
                                + "directionId\n"
                                + "route {\n"
                                    + "shortName\n"
                                    + "longName\n"
                                + "}"
                            + "}\n"
                        + "}\n"
                    + "}\n"
                + "}";
        
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Stop deserialized = new GsonBuilder()
                .registerTypeAdapter(Stop.class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Stop.class);
        
        return deserialized.getStoptimes();
    }
    
}
