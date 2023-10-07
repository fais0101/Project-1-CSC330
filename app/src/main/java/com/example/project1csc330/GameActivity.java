package com.example.project1csc330;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private Player player1;
    private Player player2;
    private Handler handler = new Handler();
    private ImageView p1Image;
    private ImageView p2Image;
    private TextView activePlayerName;

    private int startingPlayer;
    int playTime;

    ProgressBar myProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        playTime = this.getIntent().getIntExtra("play time", 0);
        //set up the progress bar
        myProgressBar = findViewById(R.id.progress_bar);
        myProgressBar.setMax(playTime);
        myProgressBar.setProgress(myProgressBar.getMax());

        //set the imageViews to the player profile pics
        p1Image = findViewById(R.id.player_1_profile_pic);
        p2Image = findViewById(R.id.player_2_profile_pic);

        //set starting player
        startingPlayer = this.getIntent().getIntExtra("starting_player",1);

        if(startingPlayer == 1){
            //initialize the players
            player1 = new Player(0,true);
            player2 = new Player(0, false);
            p2Image.setVisibility(View.INVISIBLE);
        } else if (startingPlayer == 2) {
            player1 = new Player(0,false);
            player2 = new Player(0, true);
            p1Image.setVisibility(View.INVISIBLE);
        }
        Log.i("startingPLayer in GameActivity", String.valueOf(startingPlayer));

        //set the imageViews to the player profile pics
        p1Image = findViewById(R.id.player_1_profile_pic);
        p2Image = findViewById(R.id.player_2_profile_pic);
        activePlayerName = findViewById(R.id.active_player_name);



        //run
        runnable.run();
    }

    private final Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if(myProgressBar.getProgress() < 1){
                //change player
                if(player1.getIsActive()){
                    player1.setIsActive(false);
                    p1Image.setVisibility(View.INVISIBLE);
                    p2Image.setVisibility(View.VISIBLE);
                    player2.setIsActive(true);
                    activePlayerName.setText("Player 2");
                } else if (player2.getIsActive()) {
                    player2.setIsActive(false);
                    p2Image.setVisibility(View.INVISIBLE);
                    p1Image.setVisibility(View.VISIBLE);
                    player1.setIsActive(true);
                    activePlayerName.setText("Player 1");
                }

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
        ImageButton theButton;
        switch(view.getId()){
            case R.id.game_button_1:
                markButton(R.id.game_button_1);
                break;
            case R.id.game_button_2:
                markButton(R.id.game_button_2);
                break;
            case R.id.game_button_3:
                theButton = findViewById(R.id.game_button_3);
                if(player1.getIsActive()) {
                    theButton.setImageResource(R.mipmap.player_1_profile_pic);
                    player1.setIsActive(false);
                }
                else {
                    theButton.setImageResource(R.mipmap.player_2_profile_pic);
                    player2.setIsActive(false);
                }
                myProgressBar.setProgress(0);
                theButton.setClickable(false);

                break;
            case R.id.game_button_4:
                theButton = findViewById(R.id.game_button_4);
                if(player1.getIsActive()) {
                    theButton.setImageResource(R.mipmap.player_1_profile_pic);
                    player1.setIsActive(false);
                }
                else {
                    theButton.setImageResource(R.mipmap.player_2_profile_pic);
                    player2.setIsActive(false);
                }
                myProgressBar.setProgress(0);
                theButton.setClickable(false);

                break;
        }

    }
    public void markButton(int buttonID){
        ImageButton theButton = findViewById(buttonID);
        if(player1.getIsActive()){
            theButton.setImageResource(R.mipmap.player_1_profile_pic);
            player1.setIsActive(false);
        }
        //mark location and check winner
        else {
            theButton.setImageResource(R.mipmap.player_2_profile_pic);
            player2.setIsActive(false);
        }
        myProgressBar.setProgress(0);
        theButton.setClickable(false);
    }
    public void checkWinner(){

    }
}