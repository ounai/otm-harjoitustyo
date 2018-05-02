package fi.ounai.nyssetulee.database;

import fi.ounai.nyssetulee.domain.Profile;
import fi.ounai.nyssetulee.domain.Stop;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseProfileStopDao implements ProfileStopDao {
    
    private Database database;
    private ProfileDao profileDao;
    private StopDao stopDao;

    public DatabaseProfileStopDao(Database database, ProfileDao profileDao, StopDao stopDao) {
        this.database = database;
        this.profileDao = profileDao;
        this.stopDao = stopDao;
    }

    @Override
    public List<Stop> findStopsByProfile(Profile profile) throws Exception {
        List<Stop> result = new ArrayList();
        
        ResultSet resultSet = database.executeQuery("SELECT * FROM ProfileStop WHERE profile_name = ?", profile.getName());
        
        while (resultSet.next()) {
            String gtfsId = resultSet.getString("stop_gtfsid");
            Stop stop = stopDao.findByGtfsId(gtfsId);
            
            result.add(stop);
        }
        
        return result;
    }

    @Override
    public List<Profile> findProfilesByStop(Stop stop) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addStopToProfile(Stop stop, Profile profile) throws Exception {
        stopDao.create(stop);
        profileDao.create(profile);
        
        if (!exists(stop, profile)) {
            database.executeUpdate("INSERT INTO ProfileStop (stop_gtfsid, profile_name) VALUES (?, ?)", stop.getGtfsId(), profile.getName());
        }
    }

    @Override
    public boolean exists(Stop stop, Profile profile) throws Exception {
        ResultSet resultSet = database.executeQuery("SELECT * FROM ProfileStop WHERE stop_gtfsid = ? AND profile_name = ?",
                stop.getGtfsId(), profile.getName());
        
        // .next() will return true if a row exists
        
        return resultSet.next();
    }

}
