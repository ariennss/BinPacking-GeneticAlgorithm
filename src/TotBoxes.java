import java.util.ArrayList;
import java.util.Comparator;

public class TotBoxes {

    private int boxInArrayList;
    private ArrayList<Box> allBoxes;

    public TotBoxes() {
        allBoxes = new ArrayList<Box>();
        boxInArrayList = 0;
    }

    public void addBox(Box x) {
        allBoxes.add(x);
        boxInArrayList++;
    }


    public int getBoxInArrayList() {
        return boxInArrayList;
    }

    public ArrayList<Box> getBoxArrayList() {
        return allBoxes;
    }

    public void setBoxInArrayList(int boxInArrayList) {
        this.boxInArrayList = boxInArrayList;
    }

    public ArrayList<Box> sortBoxesByHeight() {
        this.getBoxArrayList().sort(new Comparator<Box>() {
            @Override
            public int compare(Box box1, Box box2) {
                // Sort in descending order based on height
                return Integer.compare(box2.getAltezza(), box1.getAltezza());
            }
        });
        return this.getBoxArrayList();
    }

    @Override
    public String toString() {
        return "TotBoxes{" +
                "boxInArrayList=" + boxInArrayList +
                ", allBoxes=" + allBoxes +
                '}';
    }
}
