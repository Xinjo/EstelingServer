package com.ratpacksoftware.Managers;

import com.ratpacksoftware.Models.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Michel on 19-5-2017.
 */
public class VoteManager {
    private ArrayList<Vote> votes;

    private BeaconManager beaconManager;
    private VoterManager voterManager;

    public VoteManager(BeaconManager beaconManager, VoterManager voterManager) {
        votes = new ArrayList<>();

        this.beaconManager = beaconManager;
        this.voterManager = voterManager;
    }

    public boolean castVote(UUID voterId, String beaconId, String interactionId, int optionId) {
        if(voteExists(voterId, beaconId, interactionId, optionId)) {
            return false;
        } else {
            Vote v = new Vote(voterId, beaconId, interactionId, optionId);

            Beacon b = beaconManager.getBeaconById(beaconId);
            Interaction i = b.getInteractionById(interactionId);
            VoteOption vo = i.getVoteOptionById(optionId);

            vo.count++;

            votes.add(v);

            return true;
        }
    }

    public boolean voteExists(UUID voterId, String beaconId, String interactionId, int optionId) {
        for (Vote v : votes) {
            if (v.getVoterId().equals(voterId)
                    && v.getBeaconId().equals(beaconId)
                    && v.getInteractionId().equals(interactionId)) {
                return true;
            }
        }

        return false;
    }

    public boolean removeVote(UUID userId, String beaconId, String interactionId, int voteOptionId) {
        for(Vote v : votes) {
            if(voteExists(userId, beaconId, interactionId, voteOptionId)) {
                Beacon b = beaconManager.getBeaconById(beaconId);
                Interaction i = b.getInteractionById(interactionId);
                VoteOption vo = i.getVoteOptionById(voteOptionId);

                vo.count--;

                Voter voter = voterManager.getVoterById(userId);

                votes.remove(v);
                return true;
            }
        }

        return false;
    }
}
