package fi.ounai.nyssetulee.database;

import fi.ounai.nyssetulee.domain.Stop;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseStopDao implements StopDao {

    private Database database;

    public DatabaseStopDao(Database database) {
        this.database = database;
    }
    
    @Override
    public Stop findByGtfsId(String gtfsId) throws Exception {
        ResultSet resultSet = database.executeQuery("SELECT * FROM Stop WHERE gtfsid = ?", gtfsId);
        
        if (resultSet.next()) {
            String code = resultSet.getString("code"),
                    name = resultSet.getString("name"),
                    desc = resultSet.getString("desc"),
                    url = resultSet.getString("url");
            
            return new Stop(gtfsId, code, name, desc, url);
        } else {
            return null;
        }
    }
    
    @Override
    public List<Stop> findAll() throws Exception {
        List<Stop> result = new ArrayList();
        
        ResultSet resultSet = database.executeQuery("SELECT * FROM Stop");

        while (resultSet.next()) {
            String gtfsId = resultSet.getString("gtfsid"),
                    code = resultSet.getString("code"),
                    name = resultSet.getString("name"),
                    desc = resultSet.getString("desc"),
                    url = resultSet.getString("url");

            result.add(new Stop(gtfsId, code, name, desc, url));
        }
        
        return result;
    }

    @Override
    public void create(Stop stop) throws Exception {
        if (!exists(stop)) {
            database.executeUpdate("INSERT INTO Stop (gtfsid, code, name, desc, url) VALUES (?, ?, ?, ?, ?)",
                    stop.getGtfsId(), stop.getCode(), stop.getName(), stop.getDesc(), stop.getUrl());
        }
    }

    @Override
    public boolean exists(Stop stop) throws Exception {
        ResultSet resultSet = database.executeQuery("SELECT * FROM Stop WHERE gtfsid = ?", stop.getGtfsId());
        
        // .next() will return true if a row exists
        
        return resultSet.next();
    }
    
}
