package com.example.project1csc330;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    public int playTime;
    public double playerChanceSplit;
    public Player player1;
    public Player player2;
    public int winner;
    public RatingBar player1Rating;
    public RatingBar player2Rating;
    Button playButton;
    EditText editText1;
    EditText editText2;
//---------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize our players and our variables needed in other methods
        player1 = new Player();
        player2 = new Player();
        playTime = 5; //this signifies 5 seconds
        playerChanceSplit = 0.5;
        player1Rating = findViewById(R.id.player_1_rating_bar);
        player2Rating = findViewById(R.id.player_2_rating_bar);
        editText1 = (EditText) findViewById(R.id.player_1_edit_text);
        editText2 = (EditText) findViewById(R.id.player_2_edit_text);

    }
//---------------------------------------------------------------------------------------------------------------------------
    //create an activity result launcher and register for activity result
    ActivityResultLauncher<Intent> launchGame = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            //check winner... update the stars
            Intent resultData;
            playButton = findViewById(R.id.play_button);
            if (result.getResultCode() == Activity.RESULT_OK){ //Returns RESULT_OK if there was a winner
                resultData = result.getData();
                winner = resultData.getIntExtra("winner", 0);
                if(winner == 1){

                    player1Rating.setRating(player1Rating.getRating() + 1);
                    if(player1Rating.getRating() == 5)
                        playButton.setVisibility(View.INVISIBLE);

                }
                else if (winner == 2) {
                    player2Rating.setRating(player2Rating.getRating() + 1);
                    if(player2Rating.getRating() == 5)
                        playButton.setVisibility(View.INVISIBLE);
                }
            }
            //In the case of RESULT_CANCELLED we do nothing

        }
    });
    //---------------------------------------------------------------------------------------------------------------------------
    public void onClickPlayButton(View view){
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        double randomNum = Math.random();
        int startingPlayer = 0;

        //determine starter and adjust the player chance split
        if(randomNum < playerChanceSplit ){
            //player 1 starts
            startingPlayer = 1;
            intent.putExtra("starting_player", 1);
            playerChanceSplit -= 0.1;
        } else if (randomNum > playerChanceSplit) {
            //player 2 starts
            startingPlayer = 2;
            intent.putExtra("starting_player", 2);
            playerChanceSplit += 0.1;
        }
        Log.i("starting player", String.valueOf(startingPlayer));

        player1.setName(editText1.getText().toString());
        player2.setName(editText2.getText().toString());

//        if(player1.getImageUri() != null ){
//            player1.setImageUri(Uri.parse("android.resource://" + this.getPackageName() + "/" + String.valueOf(R.drawable.player_1_profile_pic)));
//        }
        Log.i("player1 name", player1.getName());
        Log.i("player2 name", player2.getName());


        //send player objects as parcelable
        intent.putExtra("Player1", player1);
        intent.putExtra("Player2", player2);
        intent.putExtra("play time", playTime);

        launchGame.launch(intent);
    }

    public void onImageClick(View view){

        switch(view.getId()){
            case R.id.player_1_image:
                player1.setUsingCustomImage(true);
                startGallery.launch("image/*");
                break;
            case R.id.player_2_image:
                player2.setUsingCustomImage(true);
                startGallery.launch("image/*");
                break;
        }
    }

    ActivityResultLauncher<String> startGallery = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {

                    if(result != null){
                        if(player1.isUsingCustomImage()){
                            //check which player is calling it then set player request to false
                            ((ImageView)findViewById(R.id.player_1_image)).setImageURI(result);
                            player1.setImageUri(result);
                            //using this value as a request.. so we reset it to false after we are done setting the image
                            //this is just to see who is actually making the request
                            player1.setUsingCustomImage(false);
                        }
                        else if(player2.isUsingCustomImage()){
                            ((ImageView)findViewById(R.id.player_2_image)).setImageURI(result);
                            player2.setImageUri(result);
                            Log.i("setImageUri", String.valueOf(player2.getImageUri()));
                            player2.setUsingCustomImage(false);
                        }
                    }

                }

    });


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
            case R.id.reset_button:
                //reset the ratings and restart the tournament
                playButton.setVisibility(View.VISIBLE);
                player1Rating.setRating(0);
                player2Rating.setRating(0);

        }

    }


}