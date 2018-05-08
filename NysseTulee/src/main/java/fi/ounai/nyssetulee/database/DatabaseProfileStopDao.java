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

    @Override
    public List<Stop> findStopsByProfile(Profile profile) throws Exception {
        List<Stop> result = new ArrayList();
        
        try (Connection connection = database.connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ProfileStop WHERE profile_name = ?")) {
                preparedStatement.setString(1, profile.getName());
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String gtfsId = resultSet.getString("stop_gtfsid");
                        Stop stop = stopDao.findByGtfsId(gtfsId);

                        result.add(stop);
                    }
                }
            }
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
        try (Connection connection = database.connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ProfileStop WHERE stop_gtfsid = ? AND profile_name = ?")) {
                preparedStatement.setString(1, stop.getGtfsId());
                preparedStatement.setString(2, profile.getName());
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // .next() will return true if a row exists
                    return resultSet.next();
                }
            }
        }
    }

    @Override
    public List<Profile> getProfiles() throws Exception {
        return profileDao.findAll();
    }

}
