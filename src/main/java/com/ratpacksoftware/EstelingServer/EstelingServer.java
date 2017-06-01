package com.ratpacksoftware.EstelingServer;

import com.ratpacksoftware.Managers.VoteManager;
import com.ratpacksoftware.Managers.VoterManager;
import com.ratpacksoftware.Web.RequestHandlers.RootHandler;
import com.ratpacksoftware.Web.RequestHandlers.VoteHandler;
import com.ratpacksoftware.Web.RequestHandlers.VoterHandler;
import com.ratpacksoftware.database.Database;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Path;

/**
 * Created by Michel on 18-5-2017.
 */
public class EstelingServer {
    private HttpServer _httpServer;

    private VoteManager _voteManager;
    private VoterManager _voterManager;

    private RootHandler _rootHandler;
    private VoteHandler _voteHandler;
    private VoterHandler _voterHandler;

    private Database _db;

    public EstelingServer(int port, String dbPath) throws IOException {
        _voteManager = new VoteManager();
        _voterManager = new VoterManager();

        _rootHandler = new RootHandler();
        _voteHandler = new VoteHandler(_voteManager);
        _voterHandler = new VoterHandler(_voterManager);

        _httpServer = HttpServer.create(new InetSocketAddress(port), 0);

        _httpServer.createContext("/", _rootHandler);
        _httpServer.createContext("/api/vote/cast", _voteHandler);
        _httpServer.createContext("/api/vote/get", _voteHandler);
        _httpServer.createContext("/api/request/id", _voterHandler);

        new File(dbPath).mkdir();
        _db = new Database(dbPath);
        _db.loadData();
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
        _db.saveData();
    }
}
