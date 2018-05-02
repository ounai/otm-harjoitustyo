package fi.ounai.nyssetulee.database;

import fi.ounai.nyssetulee.domain.Stop;
import java.util.List;

public interface StopDao {
    
    Stop findByGtfsId(String gtfsId) throws Exception;
    
    List<Stop> findAll() throws Exception;
    
    void create(Stop stop) throws Exception;
    
    boolean exists(Stop stop) throws Exception;
    
}
