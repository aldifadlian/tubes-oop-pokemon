public class StatusCondition {
    //atribut
    private boolean burn = false;
    private boolean poison = false;
    private boolean sleep = false;
    private boolean paralyze = false;

    //konstruktor
    public StatusCondition(){
        this.burn = false;
        this.poison = false;
        this.sleep = false;
        this.paralyze = false;
    }

    //getter
    public boolean getBurn(){
        return this.burn;
    }
    public boolean getPoison(){
        return this.poison;
    }
    public boolean getSleep(){
        return this.sleep;
    }
    public boolean getParalyze(){
        return this.paralyze;
    }

}
