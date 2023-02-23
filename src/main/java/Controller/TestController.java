package Controller;

import Model.SiteUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class TestController {

    private HttpServer server;
    private ObjectMapper mapper;
    public TestController(HttpServer server, ObjectMapper mapper) {
        this.server = server;
        this.mapper = mapper;
    }

    public void setEndpoints() {
        server.createContext("/test", this::testHandler);
        server.createContext("/testObject", this::testObjectHandler);
        server.createContext("/testQuery", this::testQueryParamsHandler);
        server.createContext("/testPath", this::testPathParamsHandler);
    }

    private void testHandler(HttpExchange httpExchange) throws IOException {
        if("GET".equals(httpExchange.getRequestMethod())){
            String response = "Hello from the TestController";
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }
    }

    private void testObjectHandler(HttpExchange httpExchange) throws IOException {
        if("GET".equals(httpExchange.getRequestMethod())){
            SiteUser user = new SiteUser("valid", "valid", "validP", 20);
            String response = mapper.writeValueAsString(user);
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }
    }

    private void testQueryParamsHandler(HttpExchange httpExchange) throws IOException {
        if("GET".equals(httpExchange.getRequestMethod())){
            String query = httpExchange.getRequestURI().getQuery();

            Map<String,String> queryMap = formatQuery(query);

            String response = queryMap.get("hi") + " " + queryMap.get("boo");

            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }
    }

    private void testPathParamsHandler(HttpExchange httpExchange) throws IOException {
        if("GET".equals(httpExchange.getRequestMethod())){
            try {
                String[] pathURI = String.valueOf(httpExchange.getRequestURI()).split("/");
                String response;
                if(pathURI.length != 3){
                    response = "Please add more to the path above";
                    httpExchange.sendResponseHeaders(400, response.getBytes().length);
                } else {
                    response = pathURI[2];
                    httpExchange.sendResponseHeaders(200, response.getBytes().length);
                }

                System.out.println("Path Variable is: " + response);
                OutputStream outputStream = httpExchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

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
}
