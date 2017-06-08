package com.ratpacksoftware.EstelingServer;

import com.ratpacksoftware.Managers.BeaconManager;
import com.ratpacksoftware.Managers.VoteManager;
import com.ratpacksoftware.Managers.VoterManager;
import com.ratpacksoftware.Models.Beacon;
import com.ratpacksoftware.Models.Interaction;
import com.ratpacksoftware.Models.VoteOption;
import com.ratpacksoftware.Web.RequestHandlers.*;
import com.ratpacksoftware.database.Database;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
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
        _voterManager = new VoterManager();
        _beaconManager = new BeaconManager();
        _voteManager = new VoteManager(_beaconManager, _voterManager);

        Beacon testBeacon = new Beacon("asd", 20);
        Interaction lampInteraction = new Interaction("asd1", "Lamp");
        lampInteraction.addVoteOption(new VoteOption(0, "Rood"));
        lampInteraction.addVoteOption(new VoteOption(1, "Blauw"));
        lampInteraction.addVoteOption(new VoteOption(2, "Groen"));
        testBeacon.interactions.add(lampInteraction);
        testBeacon.interactions.add(new Interaction("asd2", "Fontijn"));
        testBeacon.interactions.add(new Interaction("asd3", "Kiezel"));
        testBeacon.interactions.add(new Interaction("asd4", "Achtbaan"));
        testBeacon.interactions.add(new Interaction("asd5", "Lamp 3"));
        _beaconManager.addBeacon(testBeacon);

        _rootHandler = new RootHandler();
        _voteHandler = new VoteHandler(_voteManager, _beaconManager, _voterManager);
        _voterHandler = new VoterHandler(_voterManager);
        _beaconHandler = new BeaconHandler(_beaconManager);

        _httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        _httpServer.createContext("/", _rootHandler);
        _httpServer.createContext("/api/vote/cast", _voteHandler);
        _httpServer.createContext("/api/vote/get", _voteHandler);
        _httpServer.createContext("/api/request/id", _voterHandler);
        _httpServer.createContext("/api/voter/get", _voterHandler);
        _httpServer.createContext("/api/beacons", _beaconHandler);
        _httpServer.createContext("/api/beacon", _beaconHandler);
        _httpServer.createContext("/api/beacon/interactions", _beaconHandler);
        _httpServer.createContext("/interactions", new InteractionHandler());

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
