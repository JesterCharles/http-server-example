import Controller.SiteUserController;
import Controller.TestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {

        // Dependencies for injection
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        ObjectMapper mapper = new ObjectMapper();

        // Dependency Injection to provide server & mapper
        new TestController(server, mapper).setEndpoints();
        new SiteUserController(server, mapper).setEndpoints();

        server.setExecutor(null);
        System.out.println("Server is now running...");
        server.start();
    }
}