package com.example.project1csc330;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int playTime;
//---------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playTime = 5; //this signifies 5 seconds... may need to change later
    }
//---------------------------------------------------------------------------------------------------------------------------
    //create an activity result launcher and register for activity result
    ActivityResultLauncher<Intent> launchGame = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

        }
    });
    //---------------------------------------------------------------------------------------------------------------------------
    public void onClickPlayButton(View view){
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        intent.putExtra("play time", playTime);
        launchGame.launch(intent);
    }


}