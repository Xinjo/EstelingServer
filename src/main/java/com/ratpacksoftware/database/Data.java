package com.ratpacksoftware.database;

import com.ratpacksoftware.Models.Beacon;
import com.ratpacksoftware.Models.Voter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by stijn on 1-6-2017.
 */
public class Data implements Serializable{
    private ArrayList<Beacon> beacons;
    private ArrayList<Voter> voters;

    public Data() {
    }

    public Data(ArrayList<Beacon> interactions, ArrayList<Voter> voters) {
        this.beacons = interactions;
        this.voters = voters;
    }

    public ArrayList<Beacon> getbeacons() {
        return beacons;
    }

    public ArrayList<Voter> getVoters() {
        return voters;
    }

    public Beacon getInteractionByIndex(int index){
        return this.beacons.get(index);
    }

    public Voter getVoterByIndex(int index){
        return this.voters.get(index);
    }

    public void setbeacons(ArrayList<Beacon> beacons) {
        this.beacons = beacons;
    }

    public void setVoters(ArrayList<Voter> voters) {
        this.voters = voters;
    }

    public void addVoter(Voter voter){
        this.voters.add(voter);
    }

    public void addInteraction(Beacon interaction){
        this.beacons.add(interaction);
    }
}
