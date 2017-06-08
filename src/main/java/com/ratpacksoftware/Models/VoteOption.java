package com.ratpacksoftware.Models;

import java.io.Serializable;

/**
 * Created by Michel on 8-6-2017.
 */
public class VoteOption implements Serializable{
    private int id;
    private String name;
    public int count;

    public VoteOption(int id, String name) {
        this.id = id;
        this.name = name;
        this.count = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
