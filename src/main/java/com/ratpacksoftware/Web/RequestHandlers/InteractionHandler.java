package com.ratpacksoftware.Web.RequestHandlers;

import com.ratpacksoftware.Managers.BeaconManager;
import com.ratpacksoftware.Models.VoteOption;
import com.ratpacksoftware.Web.Parsers.RequestParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by stijn on 8-6-2017.
 */
public class InteractionHandler implements HttpHandler {
    private RequestParser _requestParser;
    private BeaconManager beaconManager;

    public InteractionHandler(BeaconManager beaconManager) {
        _requestParser = new RequestParser();

        this.beaconManager = beaconManager;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestPath = httpExchange.getRequestURI().getPath();
        String requestQuery = httpExchange.getRequestURI().getQuery();

        switch(requestPath) {
            case "/interaction":
                Map<String, String> result = _requestParser.parseQueryString(requestQuery);

                ArrayList<VoteOption> voteOptions = beaconManager.getVoteOptionsByInteractionId(result.get("interactionId"));
                break;
        }

    }
}
