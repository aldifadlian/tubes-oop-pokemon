package com.monstersaku.util;

public class StatusMove extends Move{
    private String attType; 
    private Double effectMove;
    
    public StatusMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, String target, String attType, Double effectMove){
        super(id, name, elementType, accuracy, priority, ammunition, target);
        this.attType = attType;
        this.effectMove = effectMove;
    }

    public void setAttType(String attType){
        this.attType = attType;
    }

    public String getAttType(){
        return this.attType;
    }

    public void setEffectMove(Double effectMove){
        this.effectMove = effectMove;
    }

    public Double getEffectMove(){
        return this.effectMove;
    }

    public void damage(Monster monsOwn, Monster monsEnemy){
        //if (this.getAttType().equals("Burn")){
            //double finalHp = monsEnemy.getMaxHP() - monsEnemy.getStatusCondition().kenaBurn;
            //monsEnemy.getStatusCondition().setHealthPoint(finalHp);
            //monsEnemy.getStatusCondition().changeToBurn
        //}
    }
}