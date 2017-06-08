package com.ratpacksoftware.Managers;

import com.ratpacksoftware.Models.Beacon;
import com.ratpacksoftware.Models.Interaction;
import com.ratpacksoftware.Models.Vote;
import com.ratpacksoftware.Models.VoteOption;

import java.util.ArrayList;

/**
 * Created by Michel on 1-6-2017.
 */
public class BeaconManager {
    private ArrayList<Beacon> _beacons;

    public BeaconManager() {
        _beacons = new ArrayList<>();
    }

    public Beacon getBeaconById(String id) {
        for(Beacon b : _beacons) {
            if(b.getId().equals(id)) {
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

    public ArrayList<Interaction> getInteractionsByBeaconId(String beaconId) {
        return getBeaconById(beaconId).interactions;
    }

    public ArrayList<Beacon> getInRangeBeacons(int range) {
        ArrayList<Beacon> tempBeacons = new ArrayList<>();
        for(Beacon b : _beacons) {
            if(b.getDiscoveryRangeMeters() <= range) {
                tempBeacons.add(b);
            }
        }
        return tempBeacons;
    }

    public ArrayList<VoteOption> getVoteOptionsByInteractionId(String interactionId) {
        for (Beacon b : _beacons) {
            for(Interaction i : b.interactions) {
                if(i.getId().equals(interactionId)) {
                    return i.getVoteOptions();
                }
            }
        }

        return null;
    }
}
