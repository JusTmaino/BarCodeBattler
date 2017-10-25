package com.example.justmaino.barcodebattler;

/**
 * Created by justmaino on 24/10/2017.
 */

public class Player {

    int attack;
    int defense;

    public Player(int attack,int defense){
        this.attack=attack;
        this.defense=defense;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getDefense(){
        return this.defense;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setDefense(int defense){
        this.defense=defense;
    }
}
