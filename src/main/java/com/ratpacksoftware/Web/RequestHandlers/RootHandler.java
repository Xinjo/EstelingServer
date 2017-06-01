package com.ratpacksoftware.Web.RequestHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Michel on 18-5-2017.
 */
public class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Lucky you! :)";
        httpExchange.sendResponseHeaders(200, response.length());
        try(OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.toString().getBytes());
            os.flush();
        }
    }
}
