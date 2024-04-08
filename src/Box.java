public class Box {
    private int weight;
    private int volume;
    private int base;
    private int altezza;
    private static int totNumberBoxes;
    private static TotBoxes totBoxesArray;

    public Box(int weight, int volume, int base, int altezza) {
        this.weight = weight;
        this.volume = volume;
        this.base = base;
        this.altezza = altezza;
        totNumberBoxes++;
        if (totBoxesArray != null) {totBoxesArray.addBox(this);}
        //aggiungere metodo che aggiunga la scatola appena creata all'array TotBoxes (nuova classe da creare)
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public static int getTotNumberBoxes() {
        return totNumberBoxes;
    }

    public static TotBoxes getTotBoxesArray() {
        return totBoxesArray;
    }

    public static void setTotBoxesArray(TotBoxes totBoxesArray) {
        Box.totBoxesArray = totBoxesArray;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    @Override
    public String toString() {
        return "Box{" +
                "weight=" + weight +
                ", volume=" + volume +
                ", base=" + base +
                ", altezza=" + altezza +
                '}';
    }
}
