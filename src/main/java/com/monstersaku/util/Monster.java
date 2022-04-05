package com.monstersaku.util;

import java.util.List;
import java.util.ArrayList;

public class Monster {
    private String nama;
    private List<ElementType> elemenTypes = new ArrayList<ElementType>();
    private Stats baseStats;
    private List<Move> moves = new ArrayList<Move>();

    /*Kontruktor*/
    public Monster (String nama, List<ElementType> elemenTypes, Stats baseStats, List<Move> moves) {
        this.nama = nama;
        this.elemenTypes = elemenTypes;
        this.baseStats = baseStats;
        this.moves = moves;
    }

    /*Getter*/
    public String getName(){
        return this.nama;
    }
    public List<ElementType> getElemenTypes(){
        return this.elemenTypes;
    }
    public Stats getStats(){ 
        return (this.baseStats);
    }
    public List<Move> getMoves(){
        return this.moves;
    }

}
