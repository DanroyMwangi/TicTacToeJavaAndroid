package com.example.xandov10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    private int Player1 = 2,Player2 = 2;
    public static int counter = 0;
    public FloatingActionButton restartBtn;
    public static int[][] winStates = {{0,1,2},{0,3,6},{2,5,8},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{1,4,7}};
    public int[] moves = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    public TextView turnDisplayer,scoresPane;
    public int scorePlayer1 = 0,scorePlayer2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoresPane = findViewById(R.id.scoresPane);
        scoresPane.setText(scorePlayer1 + " : " + scorePlayer2);

        AlertDialog.Builder choosePlayer = new AlertDialog.Builder(this);
        choosePlayer.setMessage("Choose one Character.").setPositiveButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Player1 = 0;
                Player2 = 1;

                turnDisplayer.setText("It is the turn of X");
            }
        }).setNegativeButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Player1 = 1;
                Player2 = 0;
                turnDisplayer.setText("It is the turn of O");
            }
        }).show();

        turnDisplayer = findViewById(R.id.turnDisplay);
        restartBtn = findViewById(R.id.restartBtn);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restart();
            }
        });
    }
    public void Tapped(View view){
        if(view.getBackground().getConstantState() == getResources().getDrawable(R.drawable.image_border).getConstantState()){
            //Turn of Player 1
            if(counter%2 == 0 && counter <= 8){
                //Checks if the player1 chose X or O
                //X is 0 while O is 1
                if(Player1 == 0){
                    view.setBackgroundResource(R.mipmap.ic_x);
                    turnDisplayer.setText("It is the turn of O");
                    moves[Integer.parseInt(view.getTag().toString())] = 0;
                    counter++;
                }
                else {
                    view.setBackgroundResource(R.mipmap.ic_o);
                    turnDisplayer.setText("It is the turn of X");
                    moves[Integer.parseInt(view.getTag().toString())] = 1;
                    counter++;
                }
            }
            //Turn of player 2
            else if(counter%2 != 0 && counter <= 8){
                //Checks if the player2 chose X or O
                //X is 0 while O is 1
                if(Player2 == 0){
                    view.setBackgroundResource(R.mipmap.ic_x);
                    turnDisplayer.setText("It is the turn of O");
                    moves[Integer.parseInt(view.getTag().toString())] = 0;
                    counter++;
                }
                else {
                    view.setBackgroundResource(R.mipmap.ic_o);
                    turnDisplayer.setText("It is the turn of X");
                    moves[Integer.parseInt(view.getTag().toString())] = 1;
                    counter++;
                }
            }
            else{
                Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_SHORT).show();
                Restart();
            }
            for (int[] winState : winStates) {
                if (moves[winState[0]] == moves[winState[1]] &&
                        moves[winState[1]] == moves[winState[2]] &&
                        moves[winState[0]] != 2) {
                    int[] winnerArray = new int[3];
                    winnerArray[moves[winState[0]]] = winState[0];
                    winnerArray[moves[winState[1]]] = winState[1];
                    winnerArray[moves[winState[2]]] = winState[2];

                    if(moves[winState[0]] == 0){
                        Toast.makeText(MainActivity.this,"X has won",Toast.LENGTH_SHORT).show();
                        for(int winnerLine:winnerArray){
                            String id = "grid" + winnerLine;
                            int gridIds = MainActivity.this.getResources().getIdentifier(id,"id",MainActivity.this.getPackageName());
                            findViewById(gridIds).setBackgroundColor(Color.BLACK);
                            view.setBackgroundColor(Color.BLACK);
                        }
                        if(Player1 == 0){
                            scorePlayer1+=1;
                            scoresPane.setText(scorePlayer1 + " : " +scorePlayer2);
                        }
                        else if(Player2 == 0){
                            scorePlayer2+=1;
                            scoresPane.setText(scorePlayer1 + " : " +scorePlayer2);
                        }
                        Restart();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"O has won",Toast.LENGTH_SHORT).show();
                        for(int winnerLine:winnerArray){
                            String id = "grid" + winnerLine;
                            int gridIds = MainActivity.this.getResources().getIdentifier(id,"id",MainActivity.this.getPackageName());
                            findViewById(gridIds).setBackgroundColor(Color.BLACK);
                            view.setBackgroundColor(Color.BLACK);
                        }
                        if(Player1 == 1){
                            scorePlayer1+=1;
                            scoresPane.setText(scorePlayer1 + " : " +scorePlayer2);
                        }
                        else if(Player2 == 1){
                            scorePlayer2+=1;
                            scoresPane.setText(scorePlayer1 + " : " +scorePlayer2);
                        }
                        Restart();
                    }
                }
            }
        }
        else{
            Toast.makeText(MainActivity.this,"You cannot play in same place twice",Toast.LENGTH_SHORT).show();
        }
    }
    public void Restart(){
        counter = 0;
        for(int i = 0;i<moves.length;i++){
            moves[i] = 2;
        }
        turnDisplayer.setText("Game has been restarted");
        findViewById(R.id.grid0).setBackgroundResource(R.drawable.image_border);
        findViewById(R.id.grid1).setBackgroundResource(R.drawable.image_border);
        findViewById(R.id.grid2).setBackgroundResource(R.drawable.image_border);
        findViewById(R.id.grid3).setBackgroundResource(R.drawable.image_border);
        findViewById(R.id.grid4).setBackgroundResource(R.drawable.image_border);
        findViewById(R.id.grid5).setBackgroundResource(R.drawable.image_border);
        findViewById(R.id.grid6).setBackgroundResource(R.drawable.image_border);
        findViewById(R.id.grid7).setBackgroundResource(R.drawable.image_border);
        findViewById(R.id.grid8).setBackgroundResource(R.drawable.image_border);

        AlertDialog.Builder choosePlayer = new AlertDialog.Builder(this);
        choosePlayer.setMessage("Choose one Character.").setPositiveButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Player1 = 0;
                Player2 = 1;

                turnDisplayer.setText("It is the turn of X");
            }
        }).setNegativeButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Player1 = 1;
                Player2 = 0;
                turnDisplayer.setText("It is the turn of O");
            }
        }).show();
    }
}