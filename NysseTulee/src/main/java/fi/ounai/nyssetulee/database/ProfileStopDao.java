package fi.ounai.nyssetulee.database;

import fi.ounai.nyssetulee.domain.Profile;
import fi.ounai.nyssetulee.domain.Stop;
import java.util.List;

public interface ProfileStopDao {
    
    List<Stop> findStopsByProfile(Profile profile) throws Exception;
    
    List<Profile> getProfiles() throws Exception;
    
    void addStopToProfile(Stop stop, Profile profile) throws Exception;
    
    boolean exists(Stop stop, Profile profile) throws Exception;
    
}
