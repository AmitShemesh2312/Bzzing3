package com.example.bzzing_last;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int choice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void roomChoice(View view) {
        Button button = (Button)view;
        String b = button.getResources().getResourceEntryName(button.getId());
        button.setBackgroundColor(Color.parseColor("#AA00FF"));
        EditText roomCode = findViewById(R.id.typeRoomCode);
        if(b.equals("btnCreateRoom")) {
            choice = 1;
            roomCode.setVisibility(View.INVISIBLE);
            findViewById(R.id.btnJoinRoom).setBackgroundColor(Color.parseColor("#3F51B5"));
        }
        else {
            choice = 2;
            roomCode.setVisibility(View.VISIBLE);
            findViewById(R.id.btnCreateRoom).setBackgroundColor(Color.parseColor("#3F51B5"));
        }
    }

    public void nextPage(View view) {
        EditText name= findViewById(R.id.typeName);
        String n= name.getText().toString();
        EditText roomCode = findViewById(R.id.typeRoomCode);
        String rC = roomCode.getText().toString();
        if (choice != 0 && !n.equals(""))
        {
            if(choice == 1)
            {
                Intent intent = new Intent(this, WaitingRoom.class);
                startActivity(intent);
            }
            else if (rC.equals(""))
                Toast.makeText(this, "Enter room code", Toast.LENGTH_SHORT).show();
            else
                joinRoom();
        }
        else
            Toast.makeText(this, "Enter the required fields", Toast.LENGTH_SHORT).show();
    }
    public void joinRoom()
    {
        Button b = findViewById(R.id.btnNextPage);
        b.setBackgroundColor(Color.parseColor("#000000"));
        //הצטרפות לחדר
    }
}