package com.example.bzzing_last;

import java.util.ArrayList;
import java.util.HashMap;

public class GameRoom {
    private final int maxPlayers = 4;
    private int roomCode;
    private int playersNum;
    private ArrayList<Player> players;
    private int rounds;


    public GameRoom(int roomCode, int playersNum, int rounds)
    {
        this.roomCode = roomCode;
        this.playersNum = playersNum;
        this.rounds = rounds;
       this.players = new ArrayList<>(4);
    }

    public GameRoom(){} // empty constructor

    public int getMaxPlayers() {
        return maxPlayers;
    }


    public void setPlayersNum(int playersNum) {
        this.playersNum = playersNum;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }


    public int getPlayersNum() {
        return playersNum;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getRounds() {
        return rounds;
    }

    public int getRoomCode(){ return roomCode; }

    public void setRoomCode(int roomCode){ this.roomCode = roomCode; }


    public HashMap<String,Object> GameRoomToHashMap()
    {
        HashMap<String, Object> map = new HashMap<>();

        map.put("playersNum", playersNum);
        map.put("rounds", rounds);


        // players
        for (int i = 0; i < playersNum; i++) {
            map.put("name"+i, players.get(i).getName());
            map.put("score"+i, players.get(i).getScore());
        }

        return map;

    }

    public  GameRoom(HashMap<String,Object> map)
    {
        // hashmap to game room object

        this.playersNum = (int) map.get("playersNum");
        this.rounds = (int) map.get("rounds");
        for (int i = 0; i < playersNum; i++)
        {
            int score= (int)map.get("score"+i);
            String name = (String)map.get("name"+i);
            players.set(i, new Player(score, name));
        }
    }



}
