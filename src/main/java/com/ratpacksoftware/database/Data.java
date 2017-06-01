package com.ratpacksoftware.database;

import com.ratpacksoftware.Models.Action;
import com.ratpacksoftware.Models.Interaction;
import com.ratpacksoftware.Models.Vote;
import com.ratpacksoftware.Models.Voter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by stijn on 1-6-2017.
 */
public class Data implements Serializable{
    private ArrayList<Interaction> interactions;
    private ArrayList<Voter> voters;

    public Data() {
    }

    public Data(ArrayList<Interaction> interactions, ArrayList<Voter> voters) {
        this.interactions = interactions;
        this.voters = voters;
    }

    public ArrayList<Interaction> getInteractions() {
        return interactions;
    }

    public ArrayList<Voter> getVoters() {
        return voters;
    }

    public Interaction getInteractionByIndex(int index){
        return this.interactions.get(index);
    }

    public Voter getVoterByIndex(int index){
        return this.voters.get(index);
    }

    public void setInteractions(ArrayList<Interaction> interactions) {
        this.interactions = interactions;
    }

    public void setVoters(ArrayList<Voter> voters) {
        this.voters = voters;
    }

    public void addVoter(Voter voter){
        this.voters.add(voter);
    }

    public void addInteraction(Interaction interaction){
        this.interactions.add(interaction);
    }
}
