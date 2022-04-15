package com.monstersaku.util;

import java.util.List;
import java.util.ArrayList;

public class Monster {
    private int id;
    private String nama;
    private List<ElementType> elemenTypes = new ArrayList<ElementType>();
    private Stats baseStats;
    private List<Move> IDmoves = new ArrayList<Move>();
    protected StatusCondition statusCondition;

    //Kontruktor
    public Monster (int id, String nama, List<ElementType> elemenTypes, Stats baseStats, List<Move> IDmoves) {
        this.id = id;
        this.nama = nama;
        this.elemenTypes = elemenTypes;
        this.baseStats = baseStats;
        this.IDmoves = IDmoves;
        this.statusCondition = new StatusCondition();
    }

    //Getter
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.nama;
    }
    public List<ElementType> getElemenTypes(){
        return this.elemenTypes;
    }
    public Stats getStats(){ 
        return this.baseStats;
    }
    public List<Move> getMoves(){
        return this.IDmoves;
    }
    public StatusCondition getStatusCondition(){
        return this.statusCondition;
    }

    //Setter
    public void setId(int id){
        this.id = id;
    }
    public void setName(String nama){
        this.nama = nama;
    }
    public void setElemenTypes(List<ElementType> elemenTypes){
        this.elemenTypes = elemenTypes;
    }
    public void setStats(Stats baseStats){ 
        this.baseStats = baseStats;
    }
    public void setMoves(List<Move> IDmoves){
        this.IDmoves = IDmoves;
    }

    public void printInfoMove(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("Daftar move pada monster %s%n", getName());
        int i = 0;
        for(Move move : IDmoves){
            i++;
            System.out.println(i+". "+ move.getMoveName()+" Accurancy: "+move.getAccuracy()+" Ammunition: "+move.getAmmunition()+" Priority: "+move.getPriority());
        }
    }
}
