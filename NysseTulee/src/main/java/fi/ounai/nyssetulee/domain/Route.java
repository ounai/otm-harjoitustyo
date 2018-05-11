package fi.ounai.nyssetulee.domain;

/**
 * Represents a single transit route.
 */

public class Route {
    
    private String gtfsId, shortName, longName, mode;
    private Stop[] stops;

    public Route(String gtfsId, String shortName, String longName, String mode) throws UnsupportedAgencyException {
        this.gtfsId = gtfsId;
        this.shortName = shortName;
        this.longName = longName;
        this.mode = mode;
        
        // Routes outside the HSL area are unsupported
        // If the gtfsId indicates the stop is somewhere else, we throw an exception
        if (!gtfsId.startsWith("HSL:")) {
            throw new UnsupportedAgencyException("Stop with gtfsId \"" + gtfsId + "\" is not within the HSL region.");
        }
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getGtfsId() {
        return gtfsId;
    }

    public Stop[] getStops() {
        return stops;
    }

    public String getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return shortName + " | " + mode + " | " + longName;
    }
    
}
