package com.ratpacksoftware.Web.RequestHandlers;

import com.ratpacksoftware.Managers.BeaconManager;
import com.ratpacksoftware.Managers.VoteManager;
import com.ratpacksoftware.Managers.VoterManager;
import com.ratpacksoftware.Models.Beacon;
import com.ratpacksoftware.Models.Interaction;
import com.ratpacksoftware.Models.Vote;
import com.ratpacksoftware.Models.Voter;
import com.ratpacksoftware.Web.Parsers.RequestParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Michel on 20-5-2017.
 */
public class VoteHandler implements HttpHandler {
    private VoteManager _voteManager;
    private RequestParser _requestParser;
    private BeaconManager _beaconManager;
    private VoterManager _voterManager;

    public VoteHandler(VoteManager voteManager, BeaconManager beaconManager, VoterManager voterManager) {
        _voteManager = voteManager;
        _beaconManager = beaconManager;
        _voterManager = voterManager;
        _requestParser = new RequestParser();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestPath = httpExchange.getRequestURI().getPath();
        String requestQuery = httpExchange.getRequestURI().getQuery();

        String response = ":)";

        switch(requestPath) {
            case "/api/vote/cast":
                Map<String, String> result = _requestParser.parseQueryString(requestQuery);

                String userId = result.get("userId");
                String beaconId = result.get("beaconId");
                String interactionId = result.get("interactionId");
                int voteOptionId = Integer.parseInt(result.get("voteOptionId"));

                Voter voter = _voterManager.getVoterById(UUID.fromString(userId));

                if(_voterManager.voterExists(voter)) {
                    Vote vote = _voteManager.castVote(voter.getId(), beaconId, interactionId, voteOptionId);
                    voter.addVote(vote);
                }

                break;
            case "/api/vote/get":
                break;
        }

        httpExchange.sendResponseHeaders(200, response.length());

        try(OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.toString().getBytes());
            os.flush();
        }
    }
}
