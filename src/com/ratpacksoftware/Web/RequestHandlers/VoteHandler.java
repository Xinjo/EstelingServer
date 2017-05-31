package com.ratpacksoftware.Web.RequestHandlers;

import com.ratpacksoftware.Managers.VoteManager;
import com.ratpacksoftware.Utilities.Web.HttpHandlerUtility;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

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
        HttpHandlerUtility.logRequest(this.getClass().getEnclosingClass().getSimpleName(), httpExchange);

        String requestPath = httpExchange.getRequestURI().getPath();
        String requestQuery = httpExchange.getRequestURI().getQuery();

        System.out.println(requestPath);

        switch(requestPath) {

        }
    }
}
