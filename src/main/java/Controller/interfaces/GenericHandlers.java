package Controller.interfaces;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface GenericHandlers<T> {

    void createHandler(HttpExchange httpExchange) throws IOException;
    void findByIDHandler(HttpExchange httpExchange);
    void updateByIDHandler(HttpExchange httpExchange);

}
