package com.monstersaku.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

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
        if (super.getTarget().equals("ENEMY")){
            double finalHp = monsEnemy.getStats().getHealthPoint() - effectMove;
            monsEnemy.getStats().setHealthPoint(finalHp);
            
            if (!monsEnemy.getStatusCondition().getBurn() && !monsEnemy.getStatusCondition().getPoison() && !monsEnemy.getStatusCondition().getSleep() && !monsEnemy.getStatusCondition().getParalyze()){
                if (attType.equals("BURN")){
                    monsEnemy.getStatusCondition().setBurn(true);
                    monsEnemy.getStats().setHealthPoint(monsEnemy.getStats().getHealthPoint()-(monsEnemy.getStats().getMaxHP()/8));
                    System.out.printf("%s kena efek BURN nih, HP berkurang 1/8.%n", monsEnemy.getName());
                }
                else if (attType.equals("POISON")){
                    monsEnemy.getStatusCondition().setPoison(true);
                    monsEnemy.getStats().setHealthPoint(monsEnemy.getStats().getHealthPoint()-(monsEnemy.getStats().getMaxHP()/16));
                    System.out.printf("%s kena efek POISON nih, HP berkurang 1/16.%n", monsEnemy.getName());
                }
                else if (attType.equals("SLEEP")){
                    monsEnemy.getStatusCondition().setSleep(true);
                    Random random = new Random();
                    int urutan = random.nextInt(7) + 1;
                    monsEnemy.getStatusCondition().setMasihSleep(urutan);
                    System.out.printf("%s kena efek SLEEP nih, monster tertidur selama %s ronde.%n", monsEnemy.getName(),monsEnemy.getStatusCondition().getMasihSleep());
                }
                else if (attType.equals("PARALYZE")){
                    monsEnemy.getStatusCondition().setParalyze(true);
                    monsEnemy.getStats().setHealthPoint(monsEnemy.getStats().getSpeed()-(monsEnemy.getStats().getSpeed()/2));
                    System.out.printf("%s kena efek PARALYZE nih, speed berkurang 1/2.%n", monsEnemy.getName(),monsEnemy.getStatusCondition().getMasihSleep());
                    Random random = new Random();
                    int urutan = random.nextInt(4) + 1;
                    if (urutan == 1){
                        monsEnemy.getStatusCondition().setDiam(true);
                        System.out.printf("%s tidak dapat bergerak selama 1 giliran.%n", monsEnemy.getName());
                    }
                    else {
                        monsEnemy.getStatusCondition().setDiam(false);
                        System.out.printf("%s masih bisa gerak, tapi speednya turun nih.%n", monsEnemy.getName());
                        System.out.printf("Speed %s sekarang sebesar %f.%n", monsEnemy.getName(),(monsEnemy.getStats().getSpeed() * 0.5));
                    }
                }
                if (monsEnemy.getStats().getHealthPoint() < 0.0) {
                    monsEnemy.getStats().setHealthPoint(0.0);
                }
                if (monsOwn.getStats().getHealthPoint() < 0.0) {
                    monsOwn.getStats().setHealthPoint(0.0);
                }
            }
            else {
                System.out.printf("%s sudah mendapatkan efek lain.%n", monsEnemy.getName());
            }
        }
        else if(super.getTarget().equals("OWN")){
            double finalHp = monsOwn.getStats().getHealthPoint() + ((effectMove * monsOwn.getStats().getMaxHP())/100);
            monsOwn.getStats().setHealthPoint(finalHp);
            System.out.println("Melakukan HEAL, HP bertambah");
        }
        super.setAmmunition(super.getAmmunition()-1);
    }
}