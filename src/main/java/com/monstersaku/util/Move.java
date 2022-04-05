package com.monstersaku.util;

import java.util.List;
import java.util.ArrayList;

public class Move{
    private int id;
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;

    //konstruktor
    public Move(String name, ElementType elementType, int accuracy, int priority, int ammunition){
        this.name = name;
        this.elementType = elementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
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
    public int getId() {
        return this.id;
    }

    public String getName() {
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
}