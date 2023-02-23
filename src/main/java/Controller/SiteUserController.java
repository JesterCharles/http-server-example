package Controller;

import Controller.interfaces.GenericHandlers;
import Controller.interfaces.SiteUserHandlers;
import Model.SiteUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

public class SiteUserController extends WebController<SiteUser> implements GenericHandlers, SiteUserHandlers {

    public SiteUserController(HttpServer server, ObjectMapper mapper) {
        super(server, mapper);
    }

    @Override
    public void setEndpoints() {
        server.createContext("/siteUser", this::testSiteUserHandler);
        server.createContext("/register", this::createHandler);
    }

    @Override
    public void findAllHandler() {

    }

    private void testSiteUserHandler(HttpExchange httpExchange) throws IOException {
        if("GET".equals(httpExchange.getRequestMethod())){
            SiteUser user = new SiteUser("valid", "valid", "validP", 20);
            String response = mapper.writeValueAsString(user);
            super.responseJSON(httpExchange, response);
        } else {
            super.responseJSON(httpExchange, "Invalid request");
        }
    }

    @Override
    public void createHandler(HttpExchange httpExchange) throws IOException {
        if("POST".equals(httpExchange.getRequestMethod())){
            SiteUser newSiteUser = mapper.readValue(httpExchange.getRequestBody(), SiteUser.class);
            System.out.println(newSiteUser);
            String response = mapper.writeValueAsString(newSiteUser);
            super.responseJSON(httpExchange, response);
        } else {
            super.responseJSON(httpExchange, "Invalid request");
        }
    }

    @Override
    public void findByIDHandler(HttpExchange httpExchange) {

    }

    @Override
    public void updateByIDHandler(HttpExchange httpExchange) {

    }

    @Override
    public void updateHandler(HttpExchange httpExchange) {

    }

    @Override
    public void deleteHandler() {

    }
}
