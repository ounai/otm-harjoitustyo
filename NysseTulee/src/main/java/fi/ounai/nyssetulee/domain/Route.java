package fi.ounai.nyssetulee.domain;

/**
 * Represents a single transit route
 */

public class Route {
    private String shortName, longName;

    public Route(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }
}
