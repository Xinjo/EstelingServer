package com.ratpacksoftware.EstelingServer;

import com.ratpacksoftware.Managers.VoteManager;
import com.ratpacksoftware.Web.RequestHandlers.RootHandler;
import com.ratpacksoftware.Web.RequestHandlers.VoteHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Michel on 18-5-2017.
 */
public class EstelingServer {
    private HttpServer _httpServer;

    private VoteManager _voteManager;

    private VoteHandler _voteHandler;

    public EstelingServer(int port) throws IOException {
        _voteManager = new VoteManager();

        _voteHandler = new VoteHandler(_voteManager);

        _httpServer = HttpServer.create(new InetSocketAddress(port), 0);

        _httpServer.createContext("/", new RootHandler());
        _httpServer.createContext("/api/vote/cast", _voteHandler);
        _httpServer.createContext("/api/vote/get", _voteHandler);
        //_httpServer.createContext("/api/beacons", null);
        //_httpServer.createContext("/api/", null);
    }

    public void start() {
        try {
            _httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Running EstelingServer at address: " + _httpServer.getAddress().getAddress().getCanonicalHostName() + " on port: " + _httpServer.getAddress().getPort());


    }

    public void stop() {
        _httpServer.stop(1);
    }
}
