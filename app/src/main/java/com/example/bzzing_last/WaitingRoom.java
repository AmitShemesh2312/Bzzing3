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

import java.util.ArrayList;

public class    WaitingRoom extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);
        int roomCode = randomNumbers();
        GameRoom gameRoom = new GameRoom();
        gameRoom.setRoomNum(roomCode);
        gameRoom.setPlayersNum(1);

        ArrayList<Player> arr = new ArrayList<>();
        for (int i = 0; i < gameRoom.getPlayersNum() ; i++) {
            arr.add(new Player(0,"player"+i));
        }

        gameRoom.setPlayers(arr);
        gameRoom.setRounds(0);



        db.collection("GameRooms").add(gameRoom.GameRoomToHashMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(WaitingRoom.this,"success  ",Toast.LENGTH_SHORT).show();

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
        int roomCode =(int)(Math.random() * 10000);
        if(roomCode<1000)
            roomCode+=1000;
        Toast.makeText(this, ""+roomCode, Toast.LENGTH_SHORT).show(); // delete
        return roomCode;
    }
}