package com.example.project1csc330;

public class Player {
    private int score;
    private boolean isActive;

    public Player(int score, boolean isActive){
        this.score = score;
        this.isActive = isActive;
    }
    public boolean getIsActive(){
        return isActive;
    }
    public void setIsActive(boolean value){
        this.isActive = value;
    }
}
