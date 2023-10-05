package com.example.project1csc330;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public int playTime;
    public double playerChanceSplit;
    public Player player1;
    public Player player2;
//---------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playTime = 5; //this signifies 5 seconds... may need to change later
        playerChanceSplit = 0.5;
    }
//---------------------------------------------------------------------------------------------------------------------------
    //create an activity result launcher and register for activity result
    ActivityResultLauncher<Intent> launchGame = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            //check winner... update the stars
        }
    });
    //---------------------------------------------------------------------------------------------------------------------------
    public void onClickPlayButton(View view){
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        double randomNum = Math.random();
        int startingPlayer = 0;
        if(randomNum < playerChanceSplit ){
            //player 1 starts
            startingPlayer = 1;
            intent.putExtra("starting_player", 1);
        } else if (randomNum > playerChanceSplit) {
            startingPlayer = 2;
            intent.putExtra("starting_player", 2);
        }
        Log.i("starting player", String.valueOf(startingPlayer));


        intent.putExtra("play time", playTime);
        launchGame.launch(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    public void onMenuItemSelected(MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.one_second:
                playTime = 1;
                break;
            case R.id.two_seconds:
                playTime = 2;
                break;
            case R.id.five_seconds:
                playTime = 5;
                break;
            case R.id.ten_seconds:
                playTime = 10;
                break;
            default:
                playTime = 5;
                break;
        }

    }


}