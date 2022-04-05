package com.monstersaku.util;

public class NormalMove extends Move {
    private int basePower;

    public NormalMove(String name, ElementType elementType, int accuracy, int priority, int ammunition, int basePower){
        super(name, elementType, accuracy, priority, ammunition);
        this.basePower = basePower;
    }

    public void setBasePower(int basePower) {
        this.basePower = basePower;
    }

    public int getBasepower() {
        return basePower;
    }
}