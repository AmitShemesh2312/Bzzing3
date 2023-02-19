package com.example.bzzing_last;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class GameRoom{
    private final int maxPlayers = 3;
    private ArrayList<Player> players;
    private int playersNum;
    private int rounds = 0;
    private int roomCode;


    public GameRoom(int roomCode, int rounds)
    {
        this.roomCode = roomCode;
        this.rounds = rounds;
        this.players = new ArrayList<>(4);
        playersNum = players.size()-1;
    }

    public GameRoom(){} //פעולה בונה ריקה

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setPlayersNum(int num) {
        this.playersNum+=num;
    }
    public int getPlayersNum() {
        return playersNum;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public ArrayList<Player> getPlayers() {return players;}

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
        this.players = new ArrayList<>(4);
        this.playersNum = Integer.parseInt(map.get("playersNum").toString());
        this.rounds = Integer.parseInt(map.get("rounds").toString());

        ArrayList<HashMap<Integer,Object>> hm = (ArrayList<HashMap<Integer,Object>>)map.get("players");
        for (int i = 0; i < hm.size(); i++) {
            this.players.add(new Player(hm.get(i)));

        }
        this.roomCode = Integer.parseInt(map.get("roomCode").toString());
    }

    public void addPlayer(Player p)
    {
        this.players.add(p);
    }

}
