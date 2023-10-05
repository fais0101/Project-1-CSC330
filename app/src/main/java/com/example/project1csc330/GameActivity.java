package com.example.project1csc330;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class GameActivity extends AppCompatActivity {
    private Player player1;
    private Player player2;
    private Handler handler = new Handler();
    private ImageView p1Image;
    private ImageView p2Image;
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
                } else if (player2.getIsActive()) {
                    player2.setIsActive(false);
                    p2Image.setVisibility(View.INVISIBLE);
                    p1Image.setVisibility(View.VISIBLE);
                    player1.setIsActive(true);
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
}