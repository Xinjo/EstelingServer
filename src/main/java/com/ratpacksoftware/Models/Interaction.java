package com.ratpacksoftware.Models;

import java.io.Serializable;

/**
 * Created by stijn on 31-5-2017.
 */
public class Interaction implements Serializable{
    public Interaction(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int id;
    public String description;
}
