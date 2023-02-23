package Util.interfaces.handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface GenericHandlers<T> {

    /**
     *This handler should take in informatiom from the httpExchange.getRequestBody() and leverage Jackson to parse the
     * JSON information into the appropriate Model in correspondence to the specific ModelController implementing this
     * method
     * @param httpExchange
     * @throws IOException
     */
    void createHandler(HttpExchange httpExchange) throws IOException;

    /**
     * This handler should be able to take in a HttpRequest from a user and parse information from the URI to pass an
     * ID down to the appropriate classes to find the Model with that particular ID.
     *
     * @param httpExchange
     */
    void findByIDHandler(HttpExchange httpExchange);

    /**
     *This handler should be able to take in a HttpRequest from a user and response to the User all the SiteUsers stored
     * within the databased as an JSON.
     *
     * @param httpExchange
     */
    void findAllHandler(HttpExchange httpExchange);

    /**
     *This handler should be able to take in a HttpRequest from a user and parse information from the URI to pass an
     *ID down to the appropriate classes to update the Model with that particular ID.
     *
     * @param httpExchange
     */
    void updateByIDHandler(HttpExchange httpExchange);

}
