package com.onehoeyman.battle.Service.BL;

import com.onehoeyman.battle.Entity.Character;

import java.util.ArrayList;

public class Combat {
    private Character winner;
    private ArrayList<String> messages;

    public Combat(Character winner, ArrayList<String> messages) {
        this.winner = winner;
        this.messages = messages;
    }

    public Character getWinner() {
        return winner;
    }

    public void setWinner(Character winner) {
        this.winner = winner;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
}
