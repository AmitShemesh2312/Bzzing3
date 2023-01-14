package com.example.bzzing_last;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DB {
    FirebaseFirestore db;
    MainActivityHandler activity;
    public DB(MainActivity activity)
    {
        db = FirebaseFirestore.getInstance();
        this.activity = activity;
    }

    public void updateGameRoom(GameRoom gameRoom)
    {
        db.collection("GameRooms")
                .document("" + gameRoom.getRoomCode())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isComplete())
                        {
                    //        activity.
                        }
                      //  else

                    }
                });
    }

    public void findGameRoomByNumber(int code) {
        db.collection("GameRooms")
                .document("" + code)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                activity.handleFindGameRoomByNumber("", document.toObject(GameRoom.class));
                            } else
                                activity.handleFindGameRoomByNumber( "gameRoomNotExist", null);
                        } else {
                            activity.handleFindGameRoomByNumber("failed", null);
                        }
                    }
                });

    }
    public void addGameRoom(GameRoom gameRoom) {
        db.collection("GameRooms").document("" + gameRoom.getRoomCode()).set(gameRoom.GameRoomToHashMap())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        activity.handleGameRoomData(task.isSuccessful());
                    }
                });
    }
    public void roomExist(int roomCode){
        DocumentReference docRef = db.collection("GameRooms").document(""+roomCode);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        activity.roomExistResult(true, roomCode);
                    }
                    else
                        activity.roomExistResult(false, roomCode);
                }
                else
                    activity.roomExistResult(false, roomCode);
            }
        });
    }
}
