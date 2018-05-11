package fi.ounai.nyssetulee.database;

import fi.ounai.nyssetulee.domain.Stop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseStopDao implements StopDao {

    private Database database;

    public DatabaseStopDao(Database database) {
        this.database = database;
    }
    
    /**
     * Return a single stop based on its GTFS ID.
     * 
     * @param gtfsId The gtfsId of the stop
     * @return The stop with the gtfsId provided, or null if it doesn't exist in the database
     * @throws Exception 
     */
    @Override
    public Stop findByGtfsId(String gtfsId) throws Exception {
        Stop result = null;
        
        Connection connection = database.connect();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Stop WHERE gtfsid = ?");
        preparedStatement.setString(1, gtfsId);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            String code = resultSet.getString("code"),
                    name = resultSet.getString("name"),
                    desc = resultSet.getString("desc"),
                    url = resultSet.getString("url");
            
            result = new Stop(gtfsId, code, name, desc, url);
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Find all stops in the database.
     * 
     * @return A list of stops in the database
     * @throws Exception 
     */
    @Override
    public List<Stop> findAll() throws Exception {
        List<Stop> result = new ArrayList();
        
        Connection connection = database.connect();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Stop");
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            String gtfsId = resultSet.getString("gtfsid"),
                    code = resultSet.getString("code"),
                    name = resultSet.getString("name"),
                    desc = resultSet.getString("desc"),
                    url = resultSet.getString("url");
            
            result.add(new Stop(gtfsId, code, name, desc, url));
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
            
        return result;
    }
    
    /**
     * Save a stop to the database.
     * 
     * @param stop The stop to save
     * @throws Exception 
     */
    @Override
    public void create(Stop stop) throws Exception {
        if (!exists(stop)) {
            database.executeUpdate("INSERT INTO Stop (gtfsid, code, name, desc, url) VALUES (?, ?, ?, ?, ?)",
                    stop.getGtfsId(), stop.getCode(), stop.getName(), stop.getDesc(), stop.getUrl());
        }
    }
    
    /**
     * Query whether a stop exists in the database.
     * 
     * @param stop The stop to query
     * @return true if the stop exists in the database
     * @throws Exception 
     */
    @Override
    public boolean exists(Stop stop) throws Exception {
        Connection connection = database.connect();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Stop WHERE gtfsid = ?");
        preparedStatement.setString(1, stop.getGtfsId());

        ResultSet resultSet = preparedStatement.executeQuery();
        
        // .next() will return true if a row exists
        boolean result = resultSet.next();
        
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return result;
    }
    
}
