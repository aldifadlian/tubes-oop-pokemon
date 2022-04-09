package com.monstersaku.util;

public class StatusMove extends Move{
    private String attType; 
    
    public StatusMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, String target, int effect, String attType){
        super(id, name, elementType, accuracy, priority, ammunition, target, effect);
        this.attType = attType;
    }

    public void setAttType(String attType){
        this.attType = attType;
    }

    public String getAttType(){
        return this.attType;
    }

    public void damage(Monster monsOwn, Monster monsEnemy){
        //if (this.getAttType().equals("Burn")){
            //double finalHp = monsEnemy.getMaxHP() - monsEnemy.getStatusCondition().kenaBurn;
            //monsEnemy.getStatusCondition().setHealthPoint(finalHp);
            //monsEnemy.getStatusCondition().changeToBurn
        //}
    }
}