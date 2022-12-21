package com.example.bzzing_last;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements GameRoomHandler {
    private int choice = 0;
    private DB database = new DB(this);
    private GameRoom gameRoom;
    private int roomCode = 0;

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
        Button b = (Button) findViewById(R.id.btnNextPage);
        b.setEnabled(false);


        EditText name = findViewById(R.id.typeName);
        String n = name.getText().toString();
        EditText roomCode = findViewById(R.id.typeRoomCode);
        String rC = roomCode.getText().toString();
        if (choice != 0 && !n.equals(""))
        {
            if(choice == 1)
            {
                b.setBackgroundColor(Color.parseColor("#000000"));//לתקן צבע
                createRoom();
            }
            else if (rC.equals("")) {
                Toast.makeText(this, "Enter room code", Toast.LENGTH_SHORT).show();
                b.setEnabled(true);
            }
            else
            {
                b.setBackgroundColor(Color.parseColor("#000000"));//לתקן צבע
                joinRoom();
            }
        }
        else
            Toast.makeText(this, "Enter the required fields", Toast.LENGTH_SHORT).show();
    }

    private void createRoom() {
        gameRoom = new GameRoom();
        gameRoom.setPlayersNum();

        ArrayList<Player> arr = new ArrayList<>();

        EditText userName = findViewById(R.id.typeName);
        String name = userName.getText().toString();
        for (int i = 0; i < gameRoom.getPlayersNum() ; i++)
            arr.add(new Player(name,  i));

        gameRoom.setPlayers(arr);
        randomNumbers();
    }

    public void joinRoom()
    {
        //הצטרפות לחדר
    }
    @Override
    public void handleGameRoomData(boolean success) {
        if (success){
            Intent intent = new Intent(this, WaitingRoom.class);
            intent.putExtra("roomCode", roomCode);
            startActivity(intent);
        }
        else {
            Button b = (Button) findViewById(R.id.btnNextPage);
            b.setEnabled(true);
            Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void roomExistResult(boolean success, int roomCode) {
        if (success)
            randomNumbers();
        else
        {
            gameRoom.setRoomCode(roomCode);
            database.addGameRoom(gameRoom);
        }
    }

    public void randomNumbers()
    {
        Random rnd = new Random();
        int random = rnd.nextInt(899999) + 100000;
        roomCode = random;
        database.roomExist(roomCode);
    }
}