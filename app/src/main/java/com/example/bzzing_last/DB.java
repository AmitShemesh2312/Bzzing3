package com.example.bzzing_last;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DB {
    FirebaseFirestore db;
    GameRoomHandler activity;
    public DB(MainActivity activity)
    {
        db = FirebaseFirestore.getInstance();
        this.activity = activity;
    }

    public void insertPlayer()
    {

    }

    public void findGameRoomByNumber(int code){
        db.collection("GameRooms")
                .whereEqualTo("roomCode", code)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                            }
                        }

                        else {
                        }
                    }
                });

    }
    public void addGameRoom(GameRoom gameRoom){
        db.collection("GameRooms").document("" +gameRoom.getRoomCode()).set(gameRoom.GameRoomToHashMap())
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
                    } else {
                        activity.roomExistResult(false, roomCode);
                    }
                } else {
                    activity.roomExistResult(false, roomCode);

                }
            }
        });
    }


}
