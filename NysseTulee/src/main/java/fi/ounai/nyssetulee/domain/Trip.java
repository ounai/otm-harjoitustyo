package fi.ounai.nyssetulee.domain;

/**
 * Represents a transit trip, an instance of a route that is physically run by a vehicle.
 */

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
