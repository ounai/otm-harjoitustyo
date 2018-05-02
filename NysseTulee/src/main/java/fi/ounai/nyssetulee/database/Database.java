package fi.ounai.nyssetulee.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
     * Execute an SQL query, and return its results.
     * 
     * @param statement The query in SQL with parameters that may need sanitation marked with ?'s
     * @param parameters The parameters that will be sanitized and inserted into the query
     * @return The results of the query
     * @throws SQLException 
     */
    public ResultSet executeQuery(String statement, String... parameters) throws SQLException {
        Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setString(i + 1, parameters[i]);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        return resultSet;
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
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setString(i + 1, parameters[i]);
            }
            
            preparedStatement.executeUpdate();
        }
    }
    
}
