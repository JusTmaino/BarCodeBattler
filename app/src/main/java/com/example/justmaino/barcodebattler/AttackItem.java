package com.example.justmaino.barcodebattler;

/**
 * Created by justmaino on 09/02/2018.
 */

public class AttackItem {

    String name;
    String picture;
    int attackValue;

    public AttackItem(){}

    public AttackItem(String name ,String picture, int attackValue){
        this.name=name;
        this.picture=picture;
        this.attackValue=attackValue;
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

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }
}
