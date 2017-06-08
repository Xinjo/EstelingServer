package com.ratpacksoftware.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by stijn on 1-6-2017.
 */
public class Voter implements Serializable{
    private UUID id;
    private ArrayList<Vote> votes = new ArrayList<>();

    public Voter(UUID id) {
        this.id = id;
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

    public void addVote(Vote vote) {
        votes.add(vote);
    }

}
