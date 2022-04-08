package com.monstersaku.util;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultMove extends Move {
    public DefaultMove() {
        super(-1, "Default", ElementType.NORMAL, 100, 0, 999999, "Enemy", 120);
    }

    public void damage(Monster monsOwn, Monster monsEnemy){
        int min = 85;
        int max = 100;
        int random = ThreadLocalRandom.current().nextInt(min,max+1);
        double finalRandom = 0.01*random;
        double finalDamage = (double)Math.floor((this.getEffect() * ((monsOwn.getStats().getAttack()) / (monsEnemy.getStats().getDefense())) + 2) * finalRandom * elementEffectivity(monsEnemy));
        
        //efek burn belum

        double finalHp = monsEnemy.getStats().getHealthPoint() - finalDamage;
        monsEnemy.getStats().setHealthPoint(finalHp);
        if (monsEnemy.getStats().getHealthPoint() < 0.0) {
            monsEnemy.getStats().setHealthPoint(0.0);
        }

        monsOwn.getStats().setHealthPoint(monsOwn.getStats().getHealthPoint() - (monsOwn.getStats().getMaxHP())/4);

    }
}