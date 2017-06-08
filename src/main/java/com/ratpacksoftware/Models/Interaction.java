package com.ratpacksoftware.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Michel on 8-6-2017.
 */
public class Interaction implements Serializable {
    private String id;
    private String description;
    private ArrayList<VoteOption> voteOptions = new ArrayList<>();

    public Interaction(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public VoteOption getVoteOptionById(int id) {
        for (VoteOption vo : voteOptions) {
            if(vo.getId() == id)
                return vo;
        }

        return null;
    }

    public ArrayList<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void addVoteOption(VoteOption voteOption) {
        voteOptions.add(voteOption);
    }
}
