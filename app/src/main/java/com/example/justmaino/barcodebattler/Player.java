package com.example.justmaino.barcodebattler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justmaino on 07/02/2018.
 */

public class Player {
    public String name;
    public ArrayList<Monster> monsterList;

    public Player(String name){
        this.name = name;
        monsterList = new ArrayList<Monster>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(ArrayList<Monster> monsterList) {
        this.monsterList = monsterList;
    }
}
