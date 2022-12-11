package com.example.bzzing_last;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Random;
import java.util.ArrayList;

public class WaitingRoom extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);
        int roomCode = randomNumbers();
        GameRoom gameRoom = new GameRoom();
        gameRoom.setRoomCode(roomCode);
        gameRoom.setPlayersNum(1);

        ArrayList<Player> arr = new ArrayList<>();
        for (int i = 0; i < gameRoom.getPlayersNum() ; i++) {
            arr.add(new Player(0,"player" + i));
        }

        gameRoom.setPlayers(arr);
        gameRoom.setRounds(0);


        db.collection("GameRooms").document("" + randomNumbers()).set(gameRoom.GameRoomToHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(WaitingRoom.this,"fail "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public int randomNumbers()
    {
        Random rnd = new Random();
        int roomCode = rnd.nextInt(899999) + 100000;
      //  if (db.collection("GameRooms").whereEqualTo())
    //        roomCode = randomNumbers();
        return roomCode;
    }
}