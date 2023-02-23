package Controller;

import Model.SiteUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;

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

}
