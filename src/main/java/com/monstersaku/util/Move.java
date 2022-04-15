package com.monstersaku.util;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Move{
    private int id;
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;
    private String target;

    //konstruktor
    public Move(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, String target){
        this.id = id;
        this.name = name;
        this.elementType = elementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
        this.target = target;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    //getter 
    public int getMoveId() {
        return this.id;
    }

    public String getMoveName() {
        return this.name;
    }

    public ElementType getElementType() {
        return this.elementType;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getAmmunition() {
        return this.ammunition;
    }

    public String getTarget() {
        return this.target;
    }

    public Double elementEffectivity(Monster elMonster){
        List<ElementType> elMonsterList = elMonster.getElemenTypes();
        Double effectivityValue = 1.0;
        HashMap<Pair<ElementType, ElementType>, Double> effectivityMap = new HashMap<>();
        for (int i = 0; i < elMonsterList.size(); i++) {
            Pair<ElementType, ElementType> searchKey = new Pair<ElementType, ElementType>(this.getElementType(), elMonsterList.get(i));
            if (effectivityMap.containsKey(searchKey)) {
                effectivityValue = effectivityValue * effectivityMap.get(searchKey);
            }
        }
        return effectivityValue;
    }

    public abstract void damage(Monster monsOwn, Monster monsEnemy);
}