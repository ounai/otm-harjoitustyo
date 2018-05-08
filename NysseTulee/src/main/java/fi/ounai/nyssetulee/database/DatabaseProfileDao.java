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
    
    @Override
    public List<Profile> findAll() throws Exception {
        List<Profile> result = new ArrayList();
        
        try (Connection connection = database.connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("name");

                        result.add(new Profile(name));
                    }

                    return result;
                }
            }
        }
    }

    @Override
    public void create(Profile profile) throws Exception {
        if (!exists(profile)) {
            database.executeUpdate("INSERT INTO Profile (name) VALUES (?)", profile.getName());
        }
    }

    @Override
    public boolean exists(Profile profile) throws Exception {
        //ResultSet resultSet = database.executeQuery(, profile.getName());
        try (Connection connection = database.connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile WHERE name = ?")) {
                preparedStatement.setString(1, profile.getName());
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // .next() will return true if a row exists
                    return resultSet.next();
                }
            }
        }
    }
    
}
