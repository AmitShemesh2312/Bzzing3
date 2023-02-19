package com.example.bzzing_last;

import java.io.Serializable;
import java.util.HashMap;

public class Player implements Serializable {
    private String name;
    private int score;
    private int accuracy;



    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.accuracy = 0;
    }


    public Player(HashMap<Integer,Object> map)
    {
        this.name = map.get("name").toString();
        this.score = Integer.valueOf(map.get("score").toString());
        this.accuracy = Integer.valueOf(map.get("accuracy").toString());
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
