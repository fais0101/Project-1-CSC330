package com.example.project1csc330;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.net.URI;

public class Player implements Parcelable {
    private int score;
    private boolean isActive;
    private String name;
    private boolean usingCustomImage;
    private Uri imageUri;

    public Player(){

    }
    public Player(int score, boolean isActive){
        this.score = score;
        this.isActive = isActive;
    }

    //For parcelable
    protected Player(Parcel in) {
        score = in.readInt();
        isActive = in.readByte() != 0;
        name = in.readString();
        usingCustomImage = in.readByte() != 0;
        imageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

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

    public boolean isUsingCustomImage() {
        return usingCustomImage;
    }

    public void setUsingCustomImage(boolean usingCustomImage) {
        this.usingCustomImage = usingCustomImage;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    //For Parcelable
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(score);
        parcel.writeByte((byte) (isActive ? 1 : 0));
        parcel.writeString(name);
        parcel.writeByte((byte) (usingCustomImage ? 1 : 0));
        parcel.writeParcelable(imageUri, i);
    }


}
