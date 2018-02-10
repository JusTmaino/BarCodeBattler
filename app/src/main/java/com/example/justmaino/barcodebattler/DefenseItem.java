package com.example.justmaino.barcodebattler;

/**
 * Created by justmaino on 09/02/2018.
 */

public class DefenseItem {

    String name;
    String picture;
    int defenseValue;

    public DefenseItem(){}

    public DefenseItem(String name ,String picture, int defenseValue){
        this.name=name;
        this.picture=picture;
        this.defenseValue=defenseValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }
}
