package com.example.justmaino.barcodebattler;

/**
 * Created by justmaino on 24/10/2017.
 */

public class Monster {

    String name;
    String picture;
    int life;
    int attack;
    int defense;
    String attackItemName;
    String defenseItemName;

    public Monster(){}

    public Monster(String name ,String picture,int life, int attack,int defense, String attackItemName ,String defenseItemName){
        this.name=name;
        this.picture=picture;
        this.life=life;
        this.attack=attack;
        this.defense=defense;
        this.attackItemName=attackItemName;
        this.defenseItemName=defenseItemName;
    }

    public String getName(){
        return this.name;
    }

    public String getPicture(){
        return this.picture;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getDefense(){
        return this.defense;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPicture(String picture){
        this.picture = picture;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setDefense(int defense){
        this.defense=defense;
    }

    public String getAttackItemName() {
        return attackItemName;
    }

    public void setAttackItemName(String attackItemName) {
        this.attackItemName = attackItemName;
    }

    public String getDefenseItemName() {
        return defenseItemName;
    }

    public void setDefenseItemName(String defenseItemName) {
        this.defenseItemName = defenseItemName;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
