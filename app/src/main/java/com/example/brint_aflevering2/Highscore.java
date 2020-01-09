package com.example.brint_aflevering2;

public class Highscore {

    private String name;
    int winCounter, lossCounter;

    public Highscore(String name, int winCounter, int lossCounter) {
        this.name = name;
        this.winCounter = winCounter;
        this.lossCounter = lossCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinCounter() {
        return winCounter;
    }

    public void setWinCounter(int winCounter) {
        this.winCounter = winCounter;
    }

    public int getLossCounter() {
        return lossCounter;
    }

    public void setLossCounter(int lossCounter) {
        this.lossCounter = lossCounter;
    }
}
