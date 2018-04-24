package fi.ounai.nyssetulee.domain;

public class Trip {
    
    private String gtfsId, tripHeadsign, directionId;
    private Route route;

    public Trip(String gtfsId, String tripHeadsign, String directionId, Route route) {
        this.gtfsId = gtfsId;
        this.tripHeadsign = tripHeadsign;
        this.directionId = directionId;
        this.route = route;
    }
    
    @Override
    public String toString() {
        return route.getShortName() + " -> " + tripHeadsign;
    }
    
}
