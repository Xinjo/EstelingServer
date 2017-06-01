package com.ratpacksoftware.Web.RequestHandlers;

import com.ratpacksoftware.Managers.VoteManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
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

        switch(requestPath) {
            case "/api/vote/cast":
                handleVoteGet(1, 1);
                break;
            case "/api/vote/get":
                break;
        }
    }

    private void handleVoteGet(int interactionId, int actionId) {

    }
}
