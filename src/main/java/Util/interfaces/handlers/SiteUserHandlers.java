package Util.interfaces.handlers;

import com.sun.net.httpserver.HttpExchange;

public interface SiteUserHandlers {

    /**
     * This handler is specifically used for SiteUsers so that we may delete them from our database. This should receive
     * some form of ID from the HTTPRequest.
     * @param httpExchange
     */
    void deleteHandler(HttpExchange httpExchange);
    void loginHandler(HttpExchange httpExchange);
}
