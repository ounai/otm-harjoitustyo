package fi.ounai.nyssetulee.database;

import fi.ounai.nyssetulee.domain.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseProfileDao implements ProfileDao {
    
    private Database database;

    public DatabaseProfileDao(Database database) {
        this.database = database;
    }
    
    /**
     * Return all profiles saved in the database.
     * @return A list of profiles found in the database
     * @throws Exception 
     */
    @Override
    public List<Profile> findAll() throws Exception {
        List<Profile> result = new ArrayList();
        
        Connection connection = database.connect();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile");
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            
            result.add(new Profile(name));
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Save profile to the database if it is not already present.
     * @param profile The profile to save
     * @throws Exception 
     */
    @Override
    public void create(Profile profile) throws Exception {
        if (!exists(profile)) {
            database.executeUpdate("INSERT INTO Profile (name) VALUES (?)", profile.getName());
        }
    }
    
    /**
     * Checks whether a profile exists in the database.
     * @param profile The profile to check
     * @return true if the profile exists in the database
     * @throws Exception 
     */
    @Override
    public boolean exists(Profile profile) throws Exception {
        Connection connection = database.connect();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile WHERE name = ?");
        
        preparedStatement.setString(1, profile.getName());
                
        ResultSet resultSet = preparedStatement.executeQuery();
        
        // .next() will return true if a row exists
        boolean result = resultSet.next();
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return result;
    }
    
}
