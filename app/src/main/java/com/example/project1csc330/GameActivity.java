package com.example.project1csc330;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {
    private Player player1;
    private Player player2;
    private Handler handler = new Handler();
    private ImageView p1Image;
    private ImageView p2Image;
    private TextView activePlayerName;

    private int startingPlayer;
    int playTime;
    int[][] gameBoard;
    private int turns = 0;
    private int winner = 0;


    ProgressBar myProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        gameBoard = new int[3][3];

        activePlayerName = findViewById(R.id.active_player_name);

        playTime = this.getIntent().getIntExtra("play time", 0);
        //set up the progress bar
        myProgressBar = findViewById(R.id.progress_bar);
        myProgressBar.setMax(playTime);
        myProgressBar.setProgress(myProgressBar.getMax());

        //set the imageViews to the player profile pics
        p1Image = findViewById(R.id.player_1_profile_pic);
        p2Image = findViewById(R.id.player_2_profile_pic);

        //initialize the players
        player1 = this.getIntent().getParcelableExtra("Player1");
        player2 = this.getIntent().getParcelableExtra("Player2");

        startingPlayer = this.getIntent().getIntExtra("starting_player",1);


        //find starting players
        if(startingPlayer == 1){
            player1.setIsActive(true);
            player2.setIsActive(false);
            activePlayerName.setText(player1.getName());
            p2Image.setVisibility(View.INVISIBLE);
        } else if (startingPlayer == 2) {
            player1.setIsActive(false);
            player2.setIsActive(true);
            activePlayerName.setText(player2.getName());
            p1Image.setVisibility(View.INVISIBLE);
        }
        Log.i("startingPLayer in GameActivity", String.valueOf(startingPlayer));

        //set the imageViews to the player profile pics
        Log.i("Image", String.valueOf(player1.getImageUri()));


        p1Image.setImageURI(player1.getImageUri());

            //p1Image.setImageURI();
        p2Image.setImageURI(player2.getImageUri());


        //run
        runnable.run();
    }

    private final Runnable runnable = new Runnable() {

        @Override
        public void run() {

            if(myProgressBar.getProgress() < 1){
                //change player
                changeActivePlayer();

                //set bar back to full and run again
                myProgressBar.setProgress(playTime);
                handler.postDelayed(runnable, 1000);
            }
            else {
                myProgressBar.setProgress(myProgressBar.getProgress() - 1);
                handler.postDelayed(runnable, 1000);
            }
        }
    };

    //add onclick for game buttons
    public void onClickGameButton(View view){
        int winner = 0;
        switch(view.getId()){
            case R.id.game_button_1:
                markButton(R.id.game_button_1);
                gameBoard[0][0] = player1.getIsActive() ? 1 : 2;
                checkWinner(0,0,getActivePlayer());
                changeActivePlayer();
                break;
            case R.id.game_button_2:
                markButton(R.id.game_button_2);
                gameBoard[0][1] = player1.getIsActive() ? 1 : 2;
                checkWinner(0,1,getActivePlayer());
                changeActivePlayer();
                break;
            case R.id.game_button_3:
                markButton(R.id.game_button_3);
                gameBoard[0][2] = player1.getIsActive() ? 1 : 2;
                checkWinner(0,2,getActivePlayer());
                changeActivePlayer();
                break;
            case R.id.game_button_4:
                markButton(R.id.game_button_4);
                gameBoard[1][0] = player1.getIsActive() ? 1 : 2;
                checkWinner(1,0,getActivePlayer());
                changeActivePlayer();
                break;
            case R.id.game_button_5:
                markButton(R.id.game_button_5);
                gameBoard[1][1] = player1.getIsActive() ? 1 : 2;
                checkWinner(1,1,getActivePlayer());
                changeActivePlayer();
                break;
            case R.id.game_button_6:
                markButton(R.id.game_button_6);
                gameBoard[1][2] = player1.getIsActive() ? 1 : 2;
                checkWinner(1,2,getActivePlayer());
                changeActivePlayer();
                break;
            case R.id.game_button_7:
                markButton(R.id.game_button_7);
                gameBoard[2][0] = player1.getIsActive() ? 1 : 2;
                checkWinner(2,0,getActivePlayer());
                changeActivePlayer();
                break;
            case R.id.game_button_8:
                markButton(R.id.game_button_8);
                gameBoard[2][1] = player1.getIsActive() ? 1 : 2;
                checkWinner(2,1,getActivePlayer());
                changeActivePlayer();
                break;
            case R.id.game_button_9:
                markButton(R.id.game_button_9);
                gameBoard[2][2] =  player1.getIsActive() ? 1 : 2;
                checkWinner(2,2,getActivePlayer());
                changeActivePlayer();
                break;
        }

    }
    public void markButton(int buttonID){
        ImageButton theButton = findViewById(buttonID);
        handler.removeCallbacks(runnable);
        if(player1.getIsActive()){
            //if there was no image selected use the default green or red
            if (player1.getImageUri() == null){
                theButton.setImageResource(R.drawable.player_1_profile_pic);
            }
            else
                theButton.setImageURI(player1.getImageUri());
        }
        //mark location and check winner
        else if(player2.getIsActive()) {
            if (player2.getImageUri() == null){
                theButton.setImageResource(R.drawable.player_2_profile_pic);
            }
            else
                theButton.setImageURI(player2.getImageUri());

        }

        myProgressBar.setProgress(myProgressBar.getMax());
        theButton.setClickable(false);
        runnable.run();
    }

    public void changeActivePlayer(){
        //change player object to an inactive state and set the image to be invisible
        if(player1.getIsActive()){
            player1.setIsActive(false);
            p1Image.setVisibility(View.INVISIBLE);
            p2Image.setVisibility(View.VISIBLE);
            player2.setIsActive(true);
            activePlayerName.setText(player2.getName());
        } else if (player2.getIsActive()) {
            player2.setIsActive(false);
            p2Image.setVisibility(View.INVISIBLE);
            p1Image.setVisibility(View.VISIBLE);
            player1.setIsActive(true);
            activePlayerName.setText(player1.getName());
        }
    }
    public  void checkWinner(int row, int column, int whoPlayed){

        Intent returnIntent = new Intent();
        int rowWin = 0, colWin = 0, diaWin = 0, antiDiaWin = 0, length = gameBoard.length-1;

        turns++;

        for (int index = 0; index < gameBoard.length; index++){
            if(gameBoard[row][index] == whoPlayed){ //check rows
                rowWin++;
            }
            if(gameBoard[index][column] == whoPlayed){ //check column
                colWin++;
            }
            if(gameBoard[index][index] == whoPlayed){ //check diagonal
                diaWin++;
            }
            if(gameBoard[index][length] == whoPlayed){ //check anti diagonal
                antiDiaWin++;
                length--;
            }
        }


        if(rowWin == 3 || colWin == 3|| diaWin == 3 || antiDiaWin == 3){
            winner = whoPlayed;
            Toast.makeText(getApplicationContext(), "Player " + winner + " wins!", Toast.LENGTH_LONG).show();
            stopGame();
            returnIntent.putExtra("winner", winner);
            setResult(RESULT_OK, returnIntent);
            finish();

        } else if(turns == 9){ //if there is no win and we are at 9 turns
            stopGame();
            Toast.makeText(getApplicationContext(), "It is a draw.", Toast.LENGTH_LONG).show();
            returnIntent.putExtra("winner", 0); //return a value of zero for a tie
            setResult(RESULT_OK, returnIntent);
            finish();
        }

    }

    public void freezeButtons(){
        ImageButton button;

        button = findViewById(R.id.game_button_1);
        button.setClickable(false);
        button = findViewById(R.id.game_button_2);
        button.setClickable(false);
        button = findViewById(R.id.game_button_3);
        button.setClickable(false);
        button = findViewById(R.id.game_button_4);
        button.setClickable(false);
        button = findViewById(R.id.game_button_5);
        button.setClickable(false);
        button = findViewById(R.id.game_button_6);
        button.setClickable(false);
        button = findViewById(R.id.game_button_7);
        button.setClickable(false);
        button = findViewById(R.id.game_button_8);
        button.setClickable(false);
        button = findViewById(R.id.game_button_9);
        button.setClickable(false);
    }


    public int getActivePlayer(){
        if(player1.getIsActive())
            return 1;
        return 2;
    }
    public void stopGame(){
        handler.removeCallbacks(runnable);
        freezeButtons();

    }
}