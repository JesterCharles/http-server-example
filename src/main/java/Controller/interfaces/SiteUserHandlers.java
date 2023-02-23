package Controller.interfaces;

import com.sun.net.httpserver.HttpExchange;

public interface SiteUserHandlers {

    void updateHandler(HttpExchange httpExchange);
    void deleteHandler();
}
