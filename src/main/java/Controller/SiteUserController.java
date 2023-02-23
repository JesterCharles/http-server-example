package Controller;

import Util.interfaces.handlers.GenericHandlers;
import Util.interfaces.handlers.SiteUserHandlers;
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


    private void testSiteUserHandler(HttpExchange httpExchange) throws IOException {
        if("GET".equals(httpExchange.getRequestMethod())){
            SiteUser user = new SiteUser("valid", "valid", "validP", 20);
            String response = mapper.writeValueAsString(user);
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            super.responseJSON(httpExchange, response);
        } else {
            httpExchange.sendResponseHeaders(400, 0);
            super.responseJSON(httpExchange, "Invalid request");
        }
    }

    @Override
    public void createHandler(HttpExchange httpExchange) throws IOException {
        if("POST".equals(httpExchange.getRequestMethod())){
            SiteUser newSiteUser = mapper.readValue(httpExchange.getRequestBody(), SiteUser.class);
            System.out.println(newSiteUser);
            String response = mapper.writeValueAsString(newSiteUser);

            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            super.responseJSON(httpExchange, response);
        } else {
            super.responseJSON(httpExchange, "Invalid request");
        }
    }
    @Override
    public void findAllHandler(HttpExchange httpExchange) {

    }
    @Override
    public void findByIDHandler(HttpExchange httpExchange) {

    }

    @Override
    public void updateByIDHandler(HttpExchange httpExchange) {

    }

    @Override
    public void deleteHandler(HttpExchange httpExchange) {

    }

    @Override
    public void loginHandler(HttpExchange httpExchange) {

    }
}
