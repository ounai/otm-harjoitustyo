package fi.ounai.nyssetulee.domain;

/**
 * Represents a single transit stop.
 */

public class Stop {
    
    private String code, name, desc, url, gtfsId;
    private Stoptime[] stoptimesWithoutPatterns;

    public Stop(String gtfsId, String code, String name, String desc, String url) throws UnsupportedAgencyException {
        this.gtfsId = gtfsId;
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.url = url;
        
        // Stops outside the HSL area are unsupported
        // If the gtfsId indicates the stop is somewhere else, we throw an exception
        if (!gtfsId.startsWith("HSL:")) {
            throw new UnsupportedAgencyException("Stop with gtfsId \"" + gtfsId + "\" is not within the HSL region.");
        }
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public String getCode() {
        return code;
    }

    public String getGtfsId() {
        return gtfsId;
    }

    public Stoptime[] getStoptimes() {
        return stoptimesWithoutPatterns;
    }

    @Override
    public String toString() {
        String str = getCode() + " " + getName();
        
        if (getDesc() != null && !getDesc().equals(getName())) {
            str += " (" + getDesc() + ")";
        }
        
        return str;
    }
    
}
