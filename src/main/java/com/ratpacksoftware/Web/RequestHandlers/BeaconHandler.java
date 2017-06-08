package com.ratpacksoftware.Web.RequestHandlers;

import com.google.gson.Gson;
import com.ratpacksoftware.Managers.BeaconManager;
import com.ratpacksoftware.Web.Parsers.RequestParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Michel on 1-6-2017.
 */
public class BeaconHandler implements HttpHandler {
    private BeaconManager _beaconManager;
    private RequestParser _requestParser;

    public BeaconHandler(BeaconManager beaconManager) {
        _beaconManager = beaconManager;
        _requestParser = new RequestParser();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";

        String requestPath = httpExchange.getRequestURI().getPath();
        String requestQuery = httpExchange.getRequestURI().getQuery();

        switch(requestPath) {
            case "/api/beacons":
                Map<String, String> queryArguments = _requestParser.parseQueryString(requestQuery);

                //int range = Integer.parseInt(queryArguments.get("range"));
                int range = 10;

                response += new Gson().toJson(_beaconManager.getBeacons().get(0));
                System.out.println(response);
                break;
            case "/api/beacon":
                Map<String, String> result = _requestParser.parseQueryString(requestQuery);

                String beaconId = result.get("beaconId");

                response += new Gson().toJson(_beaconManager.getBeaconById(beaconId));
                break;
        }

        httpExchange.sendResponseHeaders(200, response.length());

        try(OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.toString().getBytes());
            os.flush();
        }
    }
}
