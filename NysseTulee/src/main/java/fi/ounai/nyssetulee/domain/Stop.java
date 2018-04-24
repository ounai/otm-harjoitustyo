package fi.ounai.nyssetulee.domain;

/**
 * Represents a single transit stop
 */

public class Stop {
    
    private String code, name, desc, url;

    public Stop(String code, String name, String desc, String url) {
        this.code = code;
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

    @Override
    public String toString() {
        String str = getCode() + " " + getName();
        
        if (getDesc() != null && !getDesc().equals(getName())) {
            str += " (" + getDesc() + ")";
        }
        
        return str;
    }
    
}
