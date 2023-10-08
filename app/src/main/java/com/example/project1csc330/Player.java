package com.example.project1csc330;

public class Player {
    private int score;
    private boolean isActive;
    private String name;

    public Player(){

    }
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

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
