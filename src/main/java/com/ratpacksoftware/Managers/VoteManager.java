package com.ratpacksoftware.Managers;

import com.ratpacksoftware.Models.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Michel on 19-5-2017.
 */
public class VoteManager {
    // AtomicInteger -> https://stackoverflow.com/questions/4157972/how-to-update-a-value-given-a-key-in-a-java-hashmap
    // Map<Vote, voteCount>
    private ArrayList<Vote> votes;

    private BeaconManager beaconManager;
    private VoterManager voterManager;

    public VoteManager(BeaconManager beaconManager, VoterManager voterManager) {
        votes = new ArrayList<>();

        this.beaconManager = beaconManager;
        this.voterManager = voterManager;
    }

    public Vote castVote(UUID voterId, String beaconId, String interactionId, int optionId) {
        if(voteExists(voterId, beaconId, interactionId, optionId)) {
            return null;
        } else {
            Vote v = new Vote(voterId, beaconId, interactionId, optionId);

            Beacon b = beaconManager.getBeaconById(beaconId);
            Interaction i = b.getInteractionById(interactionId);
            VoteOption vo = i.getVoteOptionById(optionId);

            vo.count++;

            votes.add(v);

            return v;
        }
    }

    public boolean voteExists(UUID voterId, String beaconId, String interactionId, int optionId) {
        for (Vote v : votes) {
            if (v.getVoterId().equals(voterId)
                    && v.getBeaconId().equals(beaconId)
                    && v.getInteractionId().equals(interactionId)
                    && v.getVoteOptionId() == optionId) {
                return true;
            }
        }

        return false;
    }
}
