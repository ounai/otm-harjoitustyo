package fi.ounai.nyssetulee.domain;

/**
 * A profile for the application that is used in grouping one's favorite stops.
 */

public class Profile {
    
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
