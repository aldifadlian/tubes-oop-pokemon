package com.monstersaku.util;

import java.util.List;
import java.util.ArrayList;

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
        Double effectivityNow = 1.0;
        for (int i=0 ; i < elMonster.getElemenTypes().size(); i++){
            if ((this.getElementType() == ElementType.FIRE) && (elMonster.getElemenTypes().get(i) == ElementType.FIRE)){
                effectivityNow *= 1  ;
            }
            else if ((this.getElementType() == ElementType.FIRE) && (elMonster.getElemenTypes().get(i) == ElementType.WATER)){
                effectivityNow *= 0.5  ;
            }
            else if ((this.getElementType() == ElementType.FIRE) && (elMonster.getElemenTypes().get(i) == ElementType.GRASS)){
                effectivityNow *= 1.5  ;
            }
            else if ((this.getElementType() == ElementType.FIRE) && (elMonster.getElemenTypes().get(i) == ElementType.NORMAL)){
                effectivityNow *= 1.25 ;
            }
            else if ((this.getElementType() == ElementType.WATER) && (elMonster.getElemenTypes().get(i) == ElementType.FIRE)){
                effectivityNow *= 1;
            }
            else if ((this.getElementType() == ElementType.WATER) && (elMonster.getElemenTypes().get(i) == ElementType.WATER)){
                effectivityNow *= 1 ;
            }
            else if ((this.getElementType() == ElementType.WATER) && (elMonster.getElemenTypes().get(i) == ElementType.GRASS)){
                effectivityNow *= 0.25;
            }
            else if ((this.getElementType() == ElementType.WATER) && (elMonster.getElemenTypes().get(i) == ElementType.NORMAL)){
                effectivityNow *= 1.25;
            }
            else if ((this.getElementType() == ElementType.GRASS) && (elMonster.getElemenTypes().get(i) == ElementType.FIRE)){
                effectivityNow *= 1.5;
            }
            else if ((this.getElementType() == ElementType.GRASS) && (elMonster.getElemenTypes().get(i) == ElementType.WATER)){
                effectivityNow *= 1.75  ;
            }
            else if ((this.getElementType() == ElementType.GRASS) && (elMonster.getElemenTypes().get(i) == ElementType.GRASS)){
                effectivityNow *=  1  ;
            }
            else if ((this.getElementType() == ElementType.GRASS) && (elMonster.getElemenTypes().get(i) == ElementType.NORMAL)){
                effectivityNow *= 1.25;
            }
            else if ((this.getElementType() == ElementType.NORMAL) && (elMonster.getElemenTypes().get(i) == ElementType.FIRE)){
                effectivityNow *= 1.25;
            }
            else if ((this.getElementType() == ElementType.NORMAL) && (elMonster.getElemenTypes().get(i) == ElementType.WATER)){
                effectivityNow *= 1.25;
            }
            else if ((this.getElementType() == ElementType.NORMAL) && (elMonster.getElemenTypes().get(i) == ElementType.GRASS)){
                effectivityNow *= 1.25;
            }
            else if ((this.getElementType() == ElementType.NORMAL) && (elMonster.getElemenTypes().get(i) == ElementType.NORMAL)){
                effectivityNow *= 1.25;
            }
            else{
                effectivityNow *= 1;
            }
        }
        return effectivityNow;
    }

    public abstract void damage(Monster monsOwn, Monster monsEnemy);
}