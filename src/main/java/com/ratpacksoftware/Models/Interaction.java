package com.ratpacksoftware.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by stijn on 31-5-2017.
 */
public class Interaction implements Serializable{
    public int id;
    public ArrayList<Action> actions;
}
