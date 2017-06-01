package com.ratpacksoftware.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by stijn on 31-5-2017.
 */
public class Beacon implements Serializable{
    public Beacon(int id, ArrayList<Interaction> interactions) {
        this.id = id;
        this.interactions = interactions;
    }

    public int id;
    public ArrayList<Interaction> interactions;
}
