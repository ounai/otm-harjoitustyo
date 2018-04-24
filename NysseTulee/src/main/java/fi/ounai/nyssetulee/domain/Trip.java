package fi.ounai.nyssetulee.domain;

public class Trip {
    
    private String gtfsId, tripHeadsign, directionId;
    private Route route;

    @Override
    public String toString() {
        return route.getShortName() + " -> " + tripHeadsign;
    }
    
}
