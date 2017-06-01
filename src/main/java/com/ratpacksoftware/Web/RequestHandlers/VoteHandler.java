package com.ratpacksoftware.Web.RequestHandlers;

import com.ratpacksoftware.Managers.BeaconManager;
import com.ratpacksoftware.Managers.VoteManager;
import com.ratpacksoftware.Models.Beacon;
import com.ratpacksoftware.Models.Interaction;
import com.ratpacksoftware.Web.Parsers.RequestParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Michel on 20-5-2017.
 */
public class VoteHandler implements HttpHandler {
    private VoteManager _voteManager;
    private RequestParser _requestParser;
    private BeaconManager _beaconManager;

    public VoteHandler(VoteManager voteManager, BeaconManager beaconManager) {
        _voteManager = voteManager;
        _beaconManager = beaconManager;
        _requestParser = new RequestParser();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestPath = httpExchange.getRequestURI().getPath();
        String requestQuery = httpExchange.getRequestURI().getQuery();

        switch(requestPath) {
            case "/api/vote/cast":
                Map<String, String> result = _requestParser.parseQueryString(requestQuery);

                String userId = result.get("userId");
                String beaconId = result.get("beaconId");
                String interactionId = result.get("interactionId");

                Beacon b = _beaconManager.getBeaconById(Integer.parseInt(beaconId));
                ArrayList<Interaction> interactions = _beaconManager.getInteractionsByBeaconId(Integer.parseInt(beaconId));
                break;
            case "/api/vote/get":
                break;
        }
    }
}
