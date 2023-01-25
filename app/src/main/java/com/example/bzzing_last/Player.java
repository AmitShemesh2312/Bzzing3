package com.example.bzzing_last;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;
    private int accuracy;



    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.accuracy = 0;
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

    public int getAccuracy() {return accuracy;}
    public void setAccuracy(int accuracy) {this.accuracy = accuracy;}

}
