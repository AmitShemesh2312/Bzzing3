package com.example.bzzing_last;

import android.app.Activity;

public interface GameRoomHandler {
    void handleGameRoomData(boolean success);
    void roomExistResult(boolean success, int roomCode);
    void handleFindGameRoomByNumber(boolean success, String respond);
}
