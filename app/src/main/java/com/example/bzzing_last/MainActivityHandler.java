package com.example.bzzing_last;

public interface MainActivityHandler {
    void handleGameRoomData(boolean success);
    void roomExistResult(boolean success, int roomCode);
    void handleFindGameRoomByNumber(String respond, GameRoom gameRoom);
}
