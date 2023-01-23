package com.example.bzzing_last;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class WaitingRoom extends AppCompatActivity/* implements WaitingRoomHandler*/ {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);
        showRoomCode();

    }
    public void showRoomCode()
    {
        String roomCode = getIntent().getExtras().get("roomCode").toString();
        for (int i = 0; i < 3; i++) {

        }

        TextView textView = findViewById(R.id.roomCodeText);
        textView.setText(roomCode);
    }

    @Override
    public void onBackPressed(){
        return;
    }

}