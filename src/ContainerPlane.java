import java.util.ArrayList;
public class ContainerPlane {



        private int base;
        private int altezza;

        public ContainerPlane(int base, int altezza) {
            this.base = base;
            this.altezza = altezza;
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
        return "ContainerPlane{" +
                "base=" + base +
                ", altezza=" + altezza +
                '}';
    }
}
