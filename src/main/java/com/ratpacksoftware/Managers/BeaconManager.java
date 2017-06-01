package com.ratpacksoftware.Managers;

import com.ratpacksoftware.Models.Beacon;
import com.ratpacksoftware.Models.Interaction;

import java.util.ArrayList;

/**
 * Created by Michel on 1-6-2017.
 */
public class BeaconManager {
    private ArrayList<Beacon> _beacons;

    public BeaconManager() {
        _beacons = new ArrayList<>();
    }

    public Beacon getBeaconById(int id) {
        for(Beacon b : _beacons) {
            if(b.id == id) {
                return b;
            }
        }

        return null;
    }

    public ArrayList<Beacon> getBeacons() {
        return _beacons;
    }

    public void addBeacon(Beacon beacon) {
        _beacons.add(beacon);
    }

    public ArrayList<Interaction> getInteractionsByBeaconId(int beaconId) {
        Beacon b = getBeaconById(beaconId);

        return b.interactions;
    }
}
