package com.ratpacksoftware.Web.RequestHandlers;

import com.ratpacksoftware.Utilities.Web.HttpHandlerUtility;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Michel on 18-5-2017.
 */
public class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        HttpHandlerUtility.logRequest(this.getClass().getEnclosingClass().getSimpleName(), httpExchange);

        Headers he = httpExchange.getRequestHeaders();
        Set<Map.Entry<String, List<String>>> entries = he.entrySet();
        String response = "";
        for(Map.Entry<String, List<String>> entry : entries) {
            response += entry.toString() + "\r\n";
        }
        response += "Lucky you! :)";
        httpExchange.sendResponseHeaders(200, response.length());
        try(OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.toString().getBytes());
            os.flush();
        }
    }
}
