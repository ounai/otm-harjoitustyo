package fi.ounai.nyssetulee.database;

import fi.ounai.nyssetulee.domain.Profile;
import java.sql.ResultSet;
import java.util.List;

public class DatabaseProfileDao implements ProfileDao {
    
    private Database database;

    public DatabaseProfileDao(Database database) {
        this.database = database;
    }
    
    @Override
    public List<Profile> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Profile profile) throws Exception {
        if (!exists(profile)) {
            database.executeUpdate("INSERT INTO Profile (name) VALUES (?)", profile.getName());
        }
    }

    @Override
    public boolean exists(Profile profile) throws Exception {
        ResultSet resultSet = database.executeQuery("SELECT * FROM Profile WHERE name = ?", profile.getName());
        
        // .next() will return true if a row exists
        
        return resultSet.next();
    }
    
}
