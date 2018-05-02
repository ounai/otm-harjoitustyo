package fi.ounai.nyssetulee.database;

import fi.ounai.nyssetulee.domain.Profile;
import java.util.List;

public interface ProfileDao {
    
    List<Profile> findAll() throws Exception;
    
    void create(Profile profile) throws Exception;
    
    boolean exists(Profile profile) throws Exception;
    
}
