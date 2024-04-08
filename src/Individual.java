import java.util.Random;
import java.util.*;

public class Individual {
    private int[] genes;

    // Default constructor
    public Individual(TotBoxes insiemeDiScatole) {
        int x = insiemeDiScatole.getBoxInArrayList();
        genes = new int[x];

        Random random = new Random();
        for (int i = 0; i < genes.length; i++) {
            genes[i] = random.nextInt(2);
        }
    }

    public int[] getGenes() {
        return genes;
    }

    public double fitness(TotBoxes arraylistBoxes, Container container) {
        int totVolumeIndividual = 0;
        int totWeightIndividual = 0;
        int boxcount = 0;
        double fitness = 0;
        int i;
        for (i = 0; i < genes.length; i++) {
            for (i = 0; i < arraylistBoxes.getBoxInArrayList(); i++) {
                int oneBoxVolume = genes[i] * arraylistBoxes.getBoxArrayList().get(i).getVolume();
                totVolumeIndividual = totVolumeIndividual + oneBoxVolume;
                int oneBoxWeight = genes[i] * arraylistBoxes.getBoxArrayList().get(i).getWeight();
                totWeightIndividual = totWeightIndividual + oneBoxWeight;
            }
            if (totVolumeIndividual > container.getMax_volume() || totWeightIndividual > container.getMax_weight()) {
                fitness = 0;
            } else {
                for (int j = 0; j < genes.length; j++) {
                    if (genes[j] == 1) {
                        boxcount = boxcount + 1;
                    }
                }
                fitness = (double) boxcount / genes.length;

            }



        }
        return fitness;
    }

    //fitness che tiene conto anche della forma delle scatole (2D)
    public float fitness2(TotBoxes scatole, Container container) {
        float fitness = 0;
        boolean boxIsIn = false;
        TotBoxes boxInIndividual = new TotBoxes();
        ArrayList<Box> boxInThisIndividual = boxInIndividual.getBoxArrayList();
        var sortedBoxesbyHeight = boxInIndividual.sortBoxesByHeight();
        ArrayList<ContainerPlane> remainingArea = new ArrayList<ContainerPlane>();
        int baseContainerIniziale = container.getBase();
        int altezzaContainerIniziale = container.getAltezza();
        ContainerPlane areaContainerIniziale = new ContainerPlane(baseContainerIniziale, altezzaContainerIniziale);
        remainingArea.add(areaContainerIniziale);

            for (int i = 0; i < genes.length; i++) {
                    if (genes[i] == 1) {
                        boxInThisIndividual.add(scatole.getBoxArrayList().get(i));
                    }
            }
        System.out.println("Ci sono " + boxInThisIndividual.size() + " scatole in questo individuo.");
            //da ora in poi usiamo solo più le scatole effettivamente presenti in questo Individuo
        //prima controllare che il volume complessivo delle scatole non superi quello del container
            int volumeComplessivo = 0;
            for (int i = 0; i < boxInThisIndividual.size(); i++) {
                volumeComplessivo = volumeComplessivo + boxInThisIndividual.get(i).getVolume();
            }
        System.out.println("Volume complessivo scatole = " + volumeComplessivo);


        //poi controllare che il peso complessivo delle scatole non superi quello del container
            int pesoComplessivo = 0;
            for (int i = 0; i < boxInThisIndividual.size(); i++) {
                pesoComplessivo = pesoComplessivo + boxInThisIndividual.get(i).getWeight();
            }
        System.out.println("Peso complessivo scatole = " + pesoComplessivo);



        if (volumeComplessivo > container.getMax_volume() || pesoComplessivo > container.getMax_weight()) {
            fitness = 0;
            System.out.println("Fitness è 0 perché eccede peso o volume contenitore.");
        }
        //ora controlliamo che le scatole ci stiano per area
        else {

            for (int i = 0; i < sortedBoxesbyHeight.size(); i++) {
                Box scatola = sortedBoxesbyHeight.get(i);

                int latoUnoScatola = scatola.getBase();
                int latoDueScatola = scatola.getAltezza();
                for (int j = 0; j < remainingArea.size(); j++) {
                    ContainerPlane pianoRestante = remainingArea.get(j);
                    var latoUnoPiano = pianoRestante.getBase();
                    var latoDuePiano = pianoRestante.getAltezza();
                    if (latoUnoScatola < latoUnoPiano && latoDueScatola < latoDuePiano) {
                        boxIsIn = true;


                        //primo piano restante
                        int lato1PrimoPianoRestante = latoUnoPiano - latoUnoScatola;
                      //  int lato2PrimoPianoRestante = latoDuePiano;
                        ContainerPlane primoPianoRestante = new ContainerPlane(lato1PrimoPianoRestante, latoDuePiano);

                        //secondo piano restante
                        int lato1SecondoPianoRestante = latoDuePiano - latoDueScatola;
                       // int lato2SecondoPianoRestante = latoUnoPiano;

                        ContainerPlane secondoPianoRestante = new ContainerPlane(lato1SecondoPianoRestante, latoUnoPiano);
                        remainingArea.remove(pianoRestante);
                        remainingArea.add(primoPianoRestante);
                        remainingArea.add(secondoPianoRestante);
                        System.out.println(remainingArea);
                        break;
                    }
                    else  boxIsIn = false;


                }
            }



        }
        int quanteScatoleSonoEntrate = 0;
        for (Box box : sortedBoxesbyHeight) {
            if (boxIsIn) {
                quanteScatoleSonoEntrate++;
            }
        }
        System.out.println("Sono entrate " + quanteScatoleSonoEntrate + " scatole.");
        if (quanteScatoleSonoEntrate == sortedBoxesbyHeight.size()) {
            fitness = (float) quanteScatoleSonoEntrate / scatole.getBoxArrayList().size();
        }
        System.out.println("La fitness è: " + fitness);
        return fitness;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "genes=" + Arrays.toString(genes) +
                '}';
    }
}
