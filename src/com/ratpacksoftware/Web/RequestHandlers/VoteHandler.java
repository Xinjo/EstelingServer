package com.ratpacksoftware.Web.RequestHandlers;

import com.ratpacksoftware.Managers.VoteManager;
import com.ratpacksoftware.Utilities.Web.HttpHandlerUtility;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michel on 20-5-2017.
 */
public class VoteHandler implements HttpHandler {
    private VoteManager _voteManager;

    public VoteHandler(VoteManager voteManager) {
        _voteManager = voteManager;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestPath = httpExchange.getRequestURI().getPath();
        String requestQuery = httpExchange.getRequestURI().getQuery();

        Map<String, String> queryParameters = HttpHandlerUtility.getQueryParameters(requestQuery);

        switch(requestPath) {
            case "/api/vote/cast":
                break;
            case "/api/vote/get":
                break;
        }
    }

    private void handleVoteGet(int interactionId, int actionId) {

    }
}
