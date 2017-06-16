package com.ratpacksoftware.Web.RequestHandlers;

import com.ratpacksoftware.Managers.BeaconManager;
import com.ratpacksoftware.Managers.VoteManager;
import com.ratpacksoftware.Managers.VoterManager;
import com.ratpacksoftware.Models.Vote;
import com.ratpacksoftware.Models.Voter;
import com.ratpacksoftware.Web.Parsers.RequestParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
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

        String response = "";

        String userId = "";
        String beaconId = "";
        String interactionId = "";
        int voteOptionId = 0;

        switch(requestPath) {
            case "/api/vote/cast":
                Map<String, String> voteResult = _requestParser.parseQueryString(requestQuery);

                userId = voteResult.get("userId");
                beaconId = voteResult.get("beaconId");
                interactionId = voteResult.get("interactionId");
                voteOptionId = Integer.parseInt(voteResult.get("voteOptionId"));

                Voter vVoter = _voterManager.getVoterById(UUID.fromString(userId));

                if(_voterManager.voterExists(vVoter)) {
                    if(_voteManager.castVote(vVoter.getId(), beaconId, interactionId, voteOptionId)) {
                        response += "{ \"success\" : \"Vote has been cast\" }";
                    } else {
                        response +=  "{ \"error\" : \"Vote has already been cast for this option\" }";
                    }
                } else {
                    response +=  "{ \"error\" : \"No voter exists with that ID\" }";
                }

                break;
            case "api/vote/remove":
                Map<String, String> removeResult = _requestParser.parseQueryString(requestQuery);

                userId = removeResult.get("userId");
                beaconId = removeResult.get("beaconId");
                interactionId = removeResult.get("interactionId");
                voteOptionId = Integer.parseInt(removeResult.get("voteOptionId"));

                Voter rVoter = _voterManager.getVoterById(UUID.fromString(userId));

                if(_voterManager.voterExists(rVoter)) {
                    if(_voteManager.removeVote(rVoter.getId(), beaconId, interactionId, voteOptionId)) {
                        response += "{ \"success\" : \"Vote removed\" }";
                    } else {
                        response +=  "{ \"error\" : \"Failed to remove vote\" }";
                    }
                } else {
                    response +=  "{ \"error\" : \"No voter exists with that ID\" }";
                }
                break;
        }

        httpExchange.sendResponseHeaders(200, response.length());

        try(OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.toString().getBytes());
            os.flush();
        }
    }
}
