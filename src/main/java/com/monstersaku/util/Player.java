package com.monstersaku.util;

import java.util.*;

public class Player {
    private String name;
    private int jumlahMons;
    private List<Monster> monsters = new ArrayList<Monster>(6);

    public Player(String name, List<Monster> monsters) {
        this.name = name;
        this.jumlahMons = 6;
        this.monsters = monsters;
    }

    //getter
    public String getPlayerName(){
        return this.name;
    }
    public List<Monster> getPlayerMonster(){
        return this.monsters;
    }
    public int getJumlahMons(){
        return this.jumlahMons;
    }

    //setter
    public void setPlayerName(String name){
        this.name = name;
    }
    public void setPlayerMonster(List<Monster> monsters){
        this.monsters = monsters;
    }
    public void setJumlahMons(){
        this.jumlahMons = jumlahMons;
    }

    public void printNamaMonster(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.printf("%s's Monsters%n", getPlayerName());
        System.out.printf("Nama monster :%n");
        int i = 0;
        for(Monster monster : monsters){
            i++;
            System.out.printf("%s. %s \tHP: %s%n", i,  monster.getName(), monster.getStats().getHealthPoint());
        }
    }
}
