package com.example.bzzing_last;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements MainActivityHandler {
    private int choice = 0;
    private DB database = new DB(this);
    private ArrayList<Player> arr;
    private GameRoom gameRoom;
    private int roomCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void roomChoice(View view) { //בודקת על איזה כפתור לחץ המשתמש. לפתוח חדר חדש או להתחבר לחדר קיים
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

    public void nextPage(View view) { //אם המשתמש לחץ על המשך, הפעולה תפעל בהתאם לפי הכפתור בו בחר
        Button b = (Button) findViewById(R.id.btnNextPage);
        b.setEnabled(false);

        EditText name = findViewById(R.id.typeName);
        String n = name.getText().toString();
        EditText roomCode = findViewById(R.id.typeRoomCode);
        String rC = roomCode.getText().toString();
        if (choice != 0 && !n.equals(""))
        {
            if(choice == 1)
                createRoom();
            else if (rC.equals("")) {
                Toast.makeText(this, "Enter room code", Toast.LENGTH_SHORT).show();
                b.setEnabled(true);
            }
            else
            {
                this.roomCode = Integer.parseInt(rC);
                database.findGameRoomByNumber(this.roomCode);
            }
        }
        else {
            Toast.makeText(this, "Enter the required fields", Toast.LENGTH_SHORT).show();
            b.setEnabled(true);
        }
    }

    private void createRoom() { //הפעולה יוצרת חדר חדש מסוג GameRoom ומכניסה אליו את השחקן
        gameRoom = new GameRoom();
        gameRoom.setPlayersNum(1);

        arr = new ArrayList<>();

        EditText userName = findViewById(R.id.typeName);
        String name = userName.getText().toString();

        arr.add(new Player(name,0));

        gameRoom.setPlayers(arr);
        randomNumbers();
    }



    @Override
    public void handleFindGameRoomByNumber(String respond, GameRoom g) //במידה והחדר קיים, תקרא לפעולה joinRoom ובמידה שלא תחזיר הודעה מתאימה
    {
        Button b = (Button) findViewById(R.id.btnNextPage);
        if(respond.equals("failed")){
            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
            b.setEnabled(true);
        }
        else if(respond.equals("gameRoomNotExist"))
        {
            Toast.makeText(this, "Room Doesn't Exist!", Toast.LENGTH_SHORT).show();
            b.setEnabled(true);
        }
        else{
            gameRoom = g;
            joinRoom();
        }
    }
    public void joinRoom() //הפעולה מוסיפה שחקן לGameRoom במידה ויש מקום ומעבירה עמוד
    {
        EditText text = findViewById(R.id.typeName);
        String name = text.getText().toString();
        if(!gameRoom.addPlayer(new Player(name,0),gameRoom.getPlayersNum()))
            Toast.makeText(this, "Game Is Already Full!", Toast.LENGTH_SHORT).show();

        else
        {
            Intent intent = new Intent(this, WaitingRoom.class);
            intent.putExtra("gameRoom", (Serializable) gameRoom);
            startActivity(intent);
        }
    }


    public void handleGameRoomData(boolean success) {
        if (success){
            Intent intent = new Intent(MainActivity.this, WaitingRoom.class);
            intent.putExtra("roomCode", roomCode);
            intent.putExtra("arr", arr);
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
        roomCode = rnd.nextInt(899999) + 100000;
        database.roomExist(roomCode);
    }
}