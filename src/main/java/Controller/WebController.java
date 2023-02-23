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

    /**\
     * This WebController Constructor requires a server and mapper for every Model specific controller using it as a parent
     * class. This ensures both protected variables server and mapper to be injected during instantiation to be accessible
     * to both parent and child class.
     * @param server - this is the HTTPServer we've established to handle our HTTPRequests
     * @param mapper - provided by Jackson to marshall and unmarshall our Java Objects
     */
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

    /**
     * This is a formatter for the Query Params inside a URI. This takes the string provided and maps the query params
     * variables & value to the key-value pairs inside a HashMap. Both Key & Value are of String datatype from the URI.
     * @param query - String containing all query params after "?" in a URI. Multiple query params are separated by "&"
     * @return - HashMap<String,String> ; containing the key=value pair from the queries
     */
    public Map<String, String> formatQuery(String query){
        if(query == null) return null;

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

    /**
     * This takes the String value after the initial path defined by our Handlers.
     * @param path - URI path of a String. This starts from the path defined by setEndpoints() method for each Controller
     * @return - String value of anything entered after the initial path defined by setEndpoints()
     */
    public String formatPathVariable(String path){
        return path.split("/", 2)[2];
    }

    /**
     * This method is used to generate the relation between URI path to "http://localhost:8080/" for each Model specific
     * Controller. We must define each endpoint to specific handle our HTTPRequest to our server. The format is as follows:
     *
     * Example: server.createContext("/writePathHere", this::pathHandler);
     */
    public abstract void setEndpoints();

}
