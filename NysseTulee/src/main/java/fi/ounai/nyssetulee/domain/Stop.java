package fi.ounai.nyssetulee.domain;

/**
 * Represents a single transit stop
 */

public class Stop {
    
    private String code, name, desc, url, gtfsId;
    private Stoptime[] stoptimesWithoutPatterns;

    public Stop(String code, String name, String desc, String url, String gtfsId) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.gtfsId = gtfsId;
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
