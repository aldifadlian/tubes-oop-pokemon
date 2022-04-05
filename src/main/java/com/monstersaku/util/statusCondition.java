package com.monstersaku.util;

public class StatusCondition {
    //atribut
    private boolean isBurn=false;
    private boolean isPoison=false;
    private boolean isSleep=false;
    private boolean isParalyze=false;

    //konstruktor
    public StatusCondition(){
        this.isBurn=false;
        this.isPoison=false;
        this.isSleep=false;
        this.isParalyze=false;
    }
    //getter
    public boolean getBurn(){
        return this.isBurn;
    }
    public boolean getPoison(){
        return this.isPoison;
    }
    public boolean getSleep(){
        return this.isSleep;
    }
    public boolean getParalyze(){
        return this.isParalyze;
    }
}