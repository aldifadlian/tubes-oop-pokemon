public class Monster {
    private String nama;
    private List<ElemenType> elemenTypes = new ArrayList<ElemenTypes>();
    private Stats baseStats;
    private List<Move> moves = new ArrayList<Moves>();

    /*Kontruktor*/
    public Monster (String nama, List<ElemenType> elemenTypes, Stats baseStats, List<Move> moves) {
        this.nama = nama;
        this.elemenTypes = elemenTypes;
        this.baseStats = baseStats;
        this.moves = moves;
    }

    /*Getter*/
    public String getName(){
        return this.nama;
    }
    public List<ElemenType> getElemenTypes(){
        return this.elemenTypes;
    }
    public Stats getStats(){ 
        return (this.baseStats);
    }
    public List<Move> getMoves(){
        return this.moves;
    }

}
