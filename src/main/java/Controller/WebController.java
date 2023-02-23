package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class WebController<T> {

    protected HttpServer server;
    protected ObjectMapper mapper;

    public WebController(HttpServer server, ObjectMapper mapper){
        this.server = server;
        this.mapper = mapper;
    }

    /** formats the response into JSON and establishes the appropriate header information to
     * handle the response to any request given to it
     *
     * @param httpExchange
     * @param response
     * @throws IOException
     */
    public void responseJSON(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); // to Handle CORS in future
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    //TODO: IMPLEMENT ME
    public Map<String, String> formatQuery(String query){
        if(query == null) {
            return null;
        }
        Map<String, String> queryMap = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                queryMap.put(entry[0], entry[1]);
            }else{
                queryMap.put(entry[0], "");
            }
        }
        return queryMap;
    }

    public String formatPathVariable(String path){
        return path.split("/", 2)[2];
    }

    public abstract void setEndpoints();

}
