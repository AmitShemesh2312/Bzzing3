package com.example.bzzing_last;

public class Player {
    private int score;
    private String name;

    public Player(String name, int score) {
        this.score = score;
        this.name = name;
    }

    public Player() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
