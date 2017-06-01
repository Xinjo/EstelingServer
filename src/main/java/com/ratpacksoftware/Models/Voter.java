package com.ratpacksoftware.Models;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by stijn on 1-6-2017.
 */
public class Voter {
    public UUID id;
    public ArrayList<Vote> votes = new ArrayList<>();

    public Voter(UUID id) {
        this.id = id;
        //send id to client
    }

    public UUID getId() {
        return id;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public Vote getVoteByIndex(int index){
        return votes.get(index);
    }

    public boolean addVote(int interactionId, int actionId) {
        return votes.add(new Vote(interactionId, actionId, false));
    }

    public boolean addPersitantVote(int interactionId, int actionId) {
        return votes.add(new Vote(interactionId, actionId, true));
    }
}
