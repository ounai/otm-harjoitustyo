package fi.ounai.nyssetulee.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    
    private String address;

    public Database(String address) {
        this.address = address;
    }
    
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(address);
    }
    
    public ResultSet executeQuery(String statement, String... parameters) throws SQLException {
        Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setString(i + 1, parameters[i]);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        return resultSet;
    }
    
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
