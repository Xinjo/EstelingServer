package com.ratpacksoftware.EstelingServer;

import com.ratpacksoftware.Managers.BeaconManager;
import com.ratpacksoftware.Managers.VoteManager;
import com.ratpacksoftware.Managers.VoterManager;
import com.ratpacksoftware.Models.Beacon;
import com.ratpacksoftware.Models.Interaction;
import com.ratpacksoftware.Web.RequestHandlers.BeaconHandler;
import com.ratpacksoftware.Web.RequestHandlers.RootHandler;
import com.ratpacksoftware.Web.RequestHandlers.VoteHandler;
import com.ratpacksoftware.Web.RequestHandlers.VoterHandler;
import com.ratpacksoftware.database.Database;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by Michel on 18-5-2017.
 */
public class EstelingServer {
    private HttpServer _httpServer;

    private VoteManager _voteManager;
    private VoterManager _voterManager;
    private BeaconManager _beaconManager;

    private RootHandler _rootHandler;
    private VoteHandler _voteHandler;
    private VoterHandler _voterHandler;
    private BeaconHandler _beaconHandler;

    private Database _db;

    public EstelingServer(int port, String dbPath) throws IOException {
        _voteManager = new VoteManager();
        _voterManager = new VoterManager();
        _beaconManager = new BeaconManager();

        Beacon testBeacon = new Beacon(0, new ArrayList<>());
        testBeacon.interactions.add(new Interaction(0, "Lamp"));
        testBeacon.interactions.add(new Interaction(1, "Fontijn"));
        testBeacon.interactions.add(new Interaction(2, "Kiezel"));
        testBeacon.interactions.add(new Interaction(3, "Achtbaan"));
        testBeacon.interactions.add(new Interaction(4, "Lamp 3"));

        _rootHandler = new RootHandler();
        _voteHandler = new VoteHandler(_voteManager, _beaconManager);
        _voterHandler = new VoterHandler(_voterManager);
        _beaconHandler = new BeaconHandler();

        _httpServer = HttpServer.create(new InetSocketAddress(port), 0);

        _httpServer.createContext("/", _rootHandler);
        _httpServer.createContext("/api/vote/cast", _voteHandler);
        _httpServer.createContext("/api/vote/get", _voteHandler);
        _httpServer.createContext("/api/request/id", _voterHandler);
        _httpServer.createContext("/api/voter/get", _voterHandler);
        _httpServer.createContext("/api/beacons", _beaconHandler);
        _httpServer.createContext("/api/beacon/interactions", _beaconHandler);

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
