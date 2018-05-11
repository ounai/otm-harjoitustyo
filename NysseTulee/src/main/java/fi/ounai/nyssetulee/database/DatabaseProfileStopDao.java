package fi.ounai.nyssetulee.database;

import fi.ounai.nyssetulee.domain.Profile;
import fi.ounai.nyssetulee.domain.Stop;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    /**
     * Find all stops that are part of the profile provided.
     * 
     * @param profile The profile in question
     * @return List of stops that are a part of the profile
     * @throws Exception 
     */
    @Override
    public List<Stop> findStopsByProfile(Profile profile) throws Exception {
        List<Stop> result = new ArrayList();
        
        Connection connection = database.connect();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ProfileStop WHERE profile_name = ?");
        
        preparedStatement.setString(1, profile.getName());
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            String gtfsId = resultSet.getString("stop_gtfsid");
            Stop stop = stopDao.findByGtfsId(gtfsId);

            result.add(stop);
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Add a stop to a profile.
     * 
     * @param stop The stop which to add
     * @param profile The profile which to add to
     * @throws Exception 
     */
    @Override
    public void addStopToProfile(Stop stop, Profile profile) throws Exception {
        stopDao.create(stop);
        profileDao.create(profile);
        
        if (!exists(stop, profile)) {
            database.executeUpdate("INSERT INTO ProfileStop (stop_gtfsid, profile_name) VALUES (?, ?)", stop.getGtfsId(), profile.getName());
        }
    }
    
    /**
     * Query whether a stop is a part of a profile.
     * 
     * @param stop The stop to query
     * @param profile The profile to query
     * @return true if the stop is a part of the profile
     * @throws Exception 
     */
    @Override
    public boolean exists(Stop stop, Profile profile) throws Exception {
        Connection connection = database.connect();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ProfileStop WHERE stop_gtfsid = ? AND profile_name = ?");
        
        preparedStatement.setString(1, stop.getGtfsId());
        preparedStatement.setString(2, profile.getName());
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        // .next() will return true if a row exists
        boolean result = resultSet.next();
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Find all profiles that exist.
     * 
     * @return A list of profiles
     * @throws Exception 
     */
    @Override
    public List<Profile> getProfiles() throws Exception {
        return profileDao.findAll();
    }

}
