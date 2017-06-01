package com.ratpacksoftware.Managers;

import com.ratpacksoftware.Models.Voter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by Michel on 1-6-2017.
 */
public class VoterManager {
    private ArrayList<Voter> _voters;
    public VoterManager() {
        _voters = new ArrayList<>();
    }

    public Voter createNewVoter() {
        UUID newVoterUUID = UUID.randomUUID();

        while(idExists(_voters, newVoterUUID)) {
            newVoterUUID = UUID.randomUUID();
        }

        Voter v = new Voter(newVoterUUID);
        _voters.add(v);
        return v;
    }

    public Voter getVoterById(UUID voterId) {
        for(Voter v : _voters) {
            if(v.id.equals(voterId)) {
                return v;
            }
        }

        return null;
    }

    private boolean idExists(Collection<Voter> voters, UUID voterUUID) {
        boolean exists = false;

        for(Voter v : voters) {
            if(v.id.equals(voterUUID)) {
                exists = true;
            }
        }

        return exists;
    }

    public boolean clearVoters() {
        _voters.clear();

        if(_voters.size() < 1) {
            return true;
        }

        return false;
    }
}
