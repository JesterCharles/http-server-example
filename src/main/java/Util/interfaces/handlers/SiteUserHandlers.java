package Util.interfaces.handlers;

import com.sun.net.httpserver.HttpExchange;

public interface SiteUserHandlers {
    void deleteHandler(HttpExchange httpExchange);
    void loginHandler(HttpExchange httpExchange);
}
