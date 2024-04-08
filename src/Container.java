public class Container {

    int max_weight;
    int max_volume;
    private int base;
    private int altezza; //OCCHIO! Non ri riferisce all'altezza del container ma alle due dimensioni del piano bidimensionale alla base del container!!

    public Container(int max_weight, int max_volume, int base, int altezza) {
        this.max_weight = max_weight;
        this.max_volume = max_volume;
        this.base = base;
        this.altezza = altezza;
    }

    public int getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(int max_weight) {
        this.max_weight = max_weight;
    }

    public int getMax_volume() {
        return max_volume;
    }

    public void setMax_volume(int max_volume) {
        this.max_volume = max_volume;
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
}
