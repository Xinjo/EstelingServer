package com.ratpacksoftware.database;

import com.ratpacksoftware.Models.Action;
import com.ratpacksoftware.Models.Interaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by stijn on 31-5-2017.
 */
public class WannabeDatabase {
    private Map<Interaction, ArrayList<Action>> _interactionsActionsLink;

    public WannabeDatabase() {
        _interactionsActionsLink = new HashMap<>();
    }

    public ArrayList<Interaction> getInteractions() {


        return null;
    }

    public Interaction getInteractionById(int interactionId) {
        for(Map.Entry<Interaction, ArrayList<Action>> link : _interactionsActionsLink.entrySet()) {
            Interaction key = link.getKey();

        }

        return null;
    }

    public ArrayList<Action> getActionsByInteractionId(int interactionId) {

        return null;
    }
}