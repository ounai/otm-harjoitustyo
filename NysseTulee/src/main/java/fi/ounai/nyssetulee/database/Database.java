package fi.ounai.nyssetulee.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles connections and queries to an SQL database.
 */

public class Database {
    
    private String address;

    public Database(String address) {
        this.address = address;
    }
    
    /**
     * Connect to the database.
     * 
     * @return A connection, after it has been established
     * @throws SQLException 
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(address);
    }
    
    /**
     * Executes an SQL update.
     * 
     * @param statement The query in SQL with parameters that may need sanitation marked with ?'s
     * @param parameters The parameters that will be sanitized and inserted into the query
     * @throws SQLException 
     */
    public void executeUpdate(String statement, String... parameters) throws SQLException {
        try (Connection connection = connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
                for (int i = 0; i < parameters.length; i++) {
                    preparedStatement.setString(i + 1, parameters[i]);
                }

                preparedStatement.executeUpdate();
            }
        }
    }
    
}
