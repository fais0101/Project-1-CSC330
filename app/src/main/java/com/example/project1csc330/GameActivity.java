package com.example.project1csc330;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class GameActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    int playTime;

    ProgressBar myProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        playTime = this.getIntent().getIntExtra("play time", 0);

        myProgressBar = findViewById(R.id.progress_bar);
        myProgressBar.setMax(playTime);
        myProgressBar.setProgress(myProgressBar.getMax());
        runnable.run();
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(myProgressBar.getProgress() < 1){
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