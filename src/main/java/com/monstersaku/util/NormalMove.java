package com.monstersaku.util;
import java.util.concurrent.ThreadLocalRandom;

public class NormalMove extends Move {
    Double basePower;

    public NormalMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, String target, Double basePower){
        super(id, name, elementType, accuracy, priority, ammunition, target);
        this.basePower = basePower;
    }

    public void damage(Monster monsOwn, Monster monsEnemy){
        int min = 85;
        int max = 100;
        int random = ThreadLocalRandom.current().nextInt(min,max+1);
        double finalRandom = 0.01*random;
        double finalDamage = (double)Math.floor((basePower * ((monsOwn.getStats().getAttack()) / (monsEnemy.getStats().getDefense())) + 2) * finalRandom * elementEffectivity(monsEnemy));
        if (monsOwn.getStatusCondition().getBurn()){
           finalDamage = finalDamage * 0.5;
        }

        double finalHp = monsEnemy.getStats().getHealthPoint() - finalDamage;
        monsEnemy.getStats().setHealthPoint(finalHp);
        if (monsEnemy.getStats().getHealthPoint() < 0.0) {
            monsEnemy.getStats().setHealthPoint(0.0);
        }
        super.setAmmunition(super.getAmmunition()-1);
        System.out.printf("Damage yang diberikan oleh %s sebesar %s.%n",monsOwn.getName(),finalDamage);
    }
}