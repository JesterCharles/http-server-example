package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;

public abstract class WebController<T> {

    protected HttpServer server;
    protected ObjectMapper mapper;

    public WebController(HttpServer server, ObjectMapper mapper){
        this.server = server;
        this.mapper = mapper;
    }

    public void responseJSON(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); // to Handle CORS in future
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    public abstract void setEndpoints();
    public abstract void findAllHandler();
}
