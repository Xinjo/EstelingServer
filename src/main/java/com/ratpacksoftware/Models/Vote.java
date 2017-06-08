package com.ratpacksoftware.Models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Michel on 8-6-2017.
 */
public class Vote implements Serializable {
    private UUID voterId;
    private String beaconId;
    private String interactionId;
    private int voteOptionId;

    public Vote(UUID voterId, String beaconId, String interactionId, int voteOptionId) {
        this.voterId = voterId;
        this.beaconId = beaconId;
        this.interactionId = interactionId;
        this.voteOptionId = voteOptionId;
    }


    public UUID getVoterId() {
        return voterId;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public String getInteractionId() {
        return interactionId;
    }

    public int getVoteOptionId() {
        return voteOptionId;
    }
}
