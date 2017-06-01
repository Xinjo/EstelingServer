package com.ratpacksoftware.Models;

/**
 * Created by stijn on 1-6-2017.
 */
public class Vote {
    private int interactionId;
    private int actionId;
    private boolean persistant;

    public Vote(int interactionId, int actionId, boolean persistant) {
        this.interactionId = interactionId;
        this.actionId = actionId;
        this.persistant = persistant;
    }

    public int getInteractionId() {
        return interactionId;
    }

    public int getActionId() {
        return actionId;
    }

    public boolean isPersistant() {
        return persistant;
    }

    public void setInteractionId(int interactionId) {
        this.interactionId = interactionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public void setPersistant(boolean persistant) {
        this.persistant = persistant;
    }
}
