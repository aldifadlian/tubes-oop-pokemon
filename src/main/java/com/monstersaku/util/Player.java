package com.monstersaku.util;

import java.util.*;

public class Player {
    private String name;
    private List<Monster> monsters = new ArrayList<Monster>(6);

    public Player(String name, List<Monster> monsters) {
        this.name = name;
        this.monsters = monsters;
    }

    public Player(String name){
        this.name = name;
    }

    //getter
    public String getPlayerName(){
        return this.name;
    }
    public String getPlayerMonster(){
        return this.monsters;
    }

    //setter
    public void setPlayerName(String name){
        this.name = name;
    }
    public void setPlayerMonster(List<Monster> monsters){
        this.monsters = monsters;
    }
}
