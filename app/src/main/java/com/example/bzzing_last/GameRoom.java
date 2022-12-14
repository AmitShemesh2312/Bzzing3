package com.example.bzzing_last;

import java.util.ArrayList;
import java.util.HashMap;

public class GameRoom{
    private final int maxPlayers = 4;
    private int playersNum = 0;
    private int rounds = 0;
    private ArrayList<Player> players;
    private int roomCode;


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

    public void setPlayersNum() {
        this.playersNum++;
    }
    public int getPlayersNum() {
        return playersNum;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setRounds() {
        this.rounds++;
    }
    public int getRounds() {
        return rounds;
    }

    public void setRoomCode(int roomCode){ this.roomCode = roomCode; }
    public int getRoomCode(){ return roomCode; }


    public HashMap<String,Object> GameRoomToHashMap()
    {
        HashMap<String, Object> map = new HashMap<>();

        map.put("roomCode", roomCode);
        map.put("playersNum", playersNum);
        map.put("rounds", rounds);
        map.put("players",players);
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
            players.set(i, new Player(name, score));
        }
    }
}
