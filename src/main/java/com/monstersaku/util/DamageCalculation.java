package com.monstersaku.util;

import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

public class DamageCalculation {
    int min = 85;
    int max = 100;
    int random = ThreadLocalRandom.current().nextInt(min,max+1);
    double finalRandom = 0.01*random;
    public double getDamage (double power, double sourceAttack, double targetDefense, double elementEffectivity, double burn){
        return (double) (Math.floor((power * (sourceAttack/targetDefense) + 2) * finalRandom * elementEffectivity * burn));
    }
    
}
