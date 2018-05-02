package fi.ounai.nyssetulee.domain;

/**
 * Represents a single transit route.
 */

public class Route {
    
    private String gtfsId, shortName, longName;

    public Route(String gtfsId, String shortName, String longName) throws UnsupportedAgencyException {
        this.gtfsId = gtfsId;
        this.shortName = shortName;
        this.longName = longName;
        
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

    @Override
    public String toString() {
        return getShortName() + " " + getLongName();
    }
    
}
