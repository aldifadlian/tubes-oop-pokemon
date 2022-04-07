package com.monstersaku.util;

public class StatusCondition extends Stats {
    //atribut
    private boolean isBurn=false;
    private boolean isPoison=false;
    private boolean isSleep=false;
    private boolean isParalyze=false;

    //konstruktor
    public StatusCondition(double hP, double att, double def, double spAtt, double spDeff, double spd){
        super(hP, att, def, spAtt, spDeff, spd);
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
    public void ChangeToBurn(){
        if (this.isBurn == false && this.isPoison == false &&  this.isSleep == false && this.isParalyze==false){
            this.isBurn=true; 
        }//else do nothing
        
    }
    public void ChangeToPoison(){
        if (this.isBurn == false && this.isPoison == false &&  this.isSleep == false && this.isParalyze==false){
            this.isPoison=true;
        }//else do nothing
    }
    public void ChangeToSleep(){
        if (this.isBurn == false && this.isPoison == false &&  this.isSleep == false && this.isParalyze==false){
            this.isSleep=true;
        }//else do nothing
    }
    public void ChangeToParalyze(){
        if (this.isBurn == false && this.isPoison == false &&  this.isSleep == false && this.isParalyze==false){
            this.isParalyze=true;
        }//else do nothing
    }
    public double kenaBurn(){
        return (1/8 * super.getHealthPoint());
    }

    public double kenaPoison(){
        return (1/16 * super.getHealthPoint());
    }

    public double kenaParalyze(){
        return (1/2 * super.getSpeed());
    }
}