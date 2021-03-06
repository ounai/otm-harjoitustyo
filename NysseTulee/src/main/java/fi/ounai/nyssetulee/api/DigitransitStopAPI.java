package fi.ounai.nyssetulee.api;

import com.google.gson.GsonBuilder;
import fi.ounai.nyssetulee.domain.Stop;
import fi.ounai.nyssetulee.domain.Stoptime;
import fi.ounai.nyssetulee.domain.TransitDataJsonDeserializer;

public class DigitransitStopAPI implements StopAPI {
    
    private String apiUrl;
    
    private String tripQuery = "trip {\n"
                                + "gtfsId\n"
                                + "tripHeadsign\n"
                                + "directionId\n"
                                + "route {\n"
                                    + "shortName\n"
                                    + "longName\n"
                                + "}"
                            + "}\n";
    
    private String getStopTimesQuery(String gtfsId) {
        return "{\n"
                    + "stop(id: \"" + gtfsId + "\") {\n"
                        + "stoptimesWithoutPatterns {\n"
                            + "scheduledArrival\n"
                            + "scheduledDeparture\n"
                            + "realtimeArrival\n"
                            + "realtimeDeparture\n"
                            + "arrivalDelay\n"
                            + "departureDelay\n"
                            + "timepoint\n"
                            + "realtime\n"
                            + tripQuery
                        + "}\n"
                    + "}\n"
                + "}";
    }

    public DigitransitStopAPI(String apiUrl) {
        this.apiUrl = apiUrl;
    }
    
    /**
     * Fetch a list of transit stops that match a given name.
     * 
     * @param name The name (or a part of it) to search for
     * @return An array of stop objects, that match the search.
     * @throws Exception 
     */
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
    
    /**
     * Fetch a list of the next vehicles leaving from a stop, as expressed by stoptimes.
     * 
     * @param gtfsId The unique GTFS id of the stop
     * @return An array of stoptime objects for the stop
     * @throws Exception 
     */
    @Override
    public Stoptime[] getStoptimes(String stopGtfsId) throws Exception {
        String query = getStopTimesQuery(stopGtfsId);
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Stop deserialized = new GsonBuilder()
                .registerTypeAdapter(Stop.class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Stop.class);
        
        return deserialized.getStoptimes();
    }
    
}
