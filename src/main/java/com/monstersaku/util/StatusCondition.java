package com.monstersaku.util;
import java.util.List;
import java.util.ArrayList;

public class StatusCondition {
    //atribut
    private boolean isBurn;
    private boolean isPoison;
    private boolean isSleep;
    private boolean isParalyze;
    private boolean isDiam;
    private int masihSleep;

    //konstruktor
    public StatusCondition(){
        this.isBurn=false;
        this.isPoison=false;
        this.isSleep=false;
        this.isParalyze=false;
        this.isDiam = false;
    }
    //setter
    public void setBurn(boolean isBurn) {
        this.isBurn = isBurn;
    }

	public void setPoison(boolean isPoison) {
        this.isPoison = isPoison;
    }

	public void setSleep(boolean isSleep) {
        this.isSleep = isSleep;
    }

	public void setParalyze(boolean isParalyze) {
        this.isParalyze = isParalyze;
    }

    public void setDiam(boolean isDiam){
        this.isDiam = isDiam;
    }

	public void setMasihSleep(int masihSleep){
		this.masihSleep = masihSleep;
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
    public boolean getDiam(){
        return this.isDiam;
    }
    public int getMasihSleep(){
		return this.masihSleep;
	}
}
