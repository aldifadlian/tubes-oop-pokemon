public class DefaultMove extends Move {
    private int basePower;
    
    public DefaultMove(String name, ElementType elementType, int accuracy, int priority, int ammunition, int basePower) {
        super("Default", "NORMAL", 100, 0, 999999);
        this.basePower = 50;
    }


    public void setBasePower(int basePower) {
        this.basePower = basePower;
    }

    public double getBasePower() {
        return this.basePower;
    }
}