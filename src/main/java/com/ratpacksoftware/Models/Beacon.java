package com.ratpacksoftware.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Michel on 8-6-2017.
 */
public class Beacon implements Serializable{
    private String id;
    private double discoveryRangeMeters;

    public ArrayList<Interaction> interactions;


    public Beacon(String id, double discoveryRangeMeters) {
        this.id = id;
        this.interactions = new ArrayList<>();
        this.discoveryRangeMeters = discoveryRangeMeters;
    }

    public String getId() {
        return id;
    }

    public Interaction getInteractionById(String id) {
        for (Interaction i : interactions) {
            if(i.getId().equals(id))
                return i;

        }

        return null;
    }

    public double getDiscoveryRangeMeters() {
        return discoveryRangeMeters;
    }
}
