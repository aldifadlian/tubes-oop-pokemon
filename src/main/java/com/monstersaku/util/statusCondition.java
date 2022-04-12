package com.monstersaku.util;

public class StatusCondition extends Stats {
    //atribut
    private boolean isBurn=false;
    private boolean isPoison=false;
    private boolean isSleep=false;
    private boolean isParalyze=false;
    private int masihSleep;

    //konstruktor
    public StatusCondition(double hP, double att, double def, double spAtt, double spDeff, double spd){
        super(hP, att, def, spAtt, spDeff, spd);
        this.isBurn=false;
        this.isPoison=false;
        this.isSleep=false;
        this.isParalyze=false;
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
    public int getMasihSleep(){
		return this.masihSleep;
	}

    public double kenaBurn(){
        return (1/8 * super.getMaxHP());
    }

    public double kenaPoison(){
        return (1/16 * super.getMaxHP());
    }

    public double kenaParalyze(){
        return (1/2 * super.getSpeed());
    }
}