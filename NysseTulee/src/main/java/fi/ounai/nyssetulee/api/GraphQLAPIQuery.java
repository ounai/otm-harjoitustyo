package fi.ounai.nyssetulee.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * A query to a GraphQL API.
 */

public class GraphQLAPIQuery {
    
    private String apiUrl;
    private String queryString;

    public GraphQLAPIQuery(String apiUrl, String queryString) {
        this.apiUrl = apiUrl;
        this.queryString = queryString;
    }
    
    /**
     * Execute a GraphQL API call.
     * 
     * @return the response of the call as a string.
     * @throws Exception 
     */
    public String execute() throws Exception {
        HttpURLConnection connection = createConnection();
        
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(queryString.getBytes(StandardCharsets.UTF_8));
            
            try (InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                
                StringBuilder response = new StringBuilder();
                String responseLine;
                
                while ((responseLine = bufferedReader.readLine()) != null) {
                    response.append(responseLine);
                }
                
                return response.toString();
            }
        }
    }
    
    private HttpURLConnection createConnection() throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.addRequestProperty("Content-Type", "application/graphql");
        connection.setFixedLengthStreamingMode(queryString.length());
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        
        return connection;
    }
    
}
