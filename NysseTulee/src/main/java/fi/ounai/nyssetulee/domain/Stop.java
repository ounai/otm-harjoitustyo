package fi.ounai.nyssetulee.domain;

/**
 * Represents a single transit stop
 */

public class Stop {
    
    private String code, name, desc, url;

    public Stop(String name, String desc, String url) {
        this.name = name;
        this.desc = desc;
        this.url = url;
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
    
}
