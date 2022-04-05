public class DamageCalculation {
    public double getDamage (double power, double sourceAttack, double targetDefense, double elementEffectivity, double burn){
        return (double) (math.floor((power * (sourceAttack/targetDefense) + 2) * random(0.85, 1) * elementEffectivity * burn));
    }
    
}
