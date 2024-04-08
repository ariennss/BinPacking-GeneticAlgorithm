import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        Container container = new Container(20, 20, 100, 100);
        TotBoxes arraylist = new TotBoxes();
        Box.setTotBoxesArray(arraylist);
        Box box1 = new Box(1,2, 3 ,4);
        Box box2 = new Box(2,3, 5 ,7);
        Box box3 = new Box(1,2, 2, 2);
        Box box4 = new Box(2,3, 2, 3);
        Box box5 = new Box(1,2, 3, 4);
        Box box6 = new Box(2,3,3, 1);
        Box box7 = new Box(1,2, 2, 2);
        Box box8 = new Box(2,3, 3,3);
        Box box9 = new Box(1,2, 2, 4);
        Box box10 = new Box(2,3,2, 1);
        Box box11 = new Box(1,2, 3, 1);
        Box box12 = new Box(2,3, 4, 5);
        Box box13 = new Box(1,2, 2, 4);
        System.out.println(arraylist);

        Individual individual1 = new Individual(arraylist);
        Individual individual2 = new Individual(arraylist);
        Individual individual3 = new Individual(arraylist);
        Individual individual4 = new Individual(arraylist);
        Individual individual5 = new Individual(arraylist);
        Individual individual6 = new Individual(arraylist);
        System.out.println("Fitness degli individui 1:");
        System.out.println();
        System.out.println(individual1.fitness(arraylist, container));
        System.out.println(individual2.fitness(arraylist, container));
        System.out.println(individual3.fitness(arraylist, container));
        System.out.println(individual4.fitness(arraylist, container));
        System.out.println(individual5.fitness(arraylist, container));
        System.out.println(individual6.fitness(arraylist, container));
        System.out.println("Fitness degli individui 2: ");
        System.out.println();
        System.out.println(individual1.fitness2(arraylist, container));
        System.out.println(individual2.fitness2(arraylist, container));
        System.out.println(individual3.fitness2(arraylist, container));
        System.out.println(individual4.fitness2(arraylist, container));
        System.out.println(individual5.fitness2(arraylist, container));
        System.out.println(individual6.fitness2(arraylist, container));

        Population population0 = new Population(6);
        population0.addIndividual(individual1);
        population0.addIndividual(individual2);
        population0.addIndividual(individual3);
        population0.addIndividual(individual4);
        population0.addIndividual(individual5);
        population0.addIndividual(individual6);
        System.out.println("pop iniziale = " + population0);
        var primaGen = population0.getPopulation();
        System.out.println("fitness individui prima generazione");
        for (Individual individual : primaGen) {
            System.out.println("fitness = " + individual.fitness(arraylist, container));
        }
        System.out.println();
        System.out.println("Average fitness prima generazione");
        System.out.println(population0.getAverageFitness(arraylist, container));

/*
        Individual[] genitori = population0.selection(arraylist, container);
        System.out.println(Arrays.toString(genitori));
        System.out.println("fitness1 " + genitori[0].fitness(arraylist, container));
        System.out.println("fitness2 " + genitori[1].fitness(arraylist, container));
        for (int i = 0; i < population0.getPopulation().length; i++) {
            System.out.println(population0.getPopulation()[i] + "individuo" + "  fitness: " + population0.getPopulation()[i].fitness(arraylist, container));
        }

        System.out.println(Arrays.toString(genitori));
        Individual[] figli = population0.crossover(genitori);
        System.out.println("I figli sono: " + Arrays.toString(figli));
        double fitnessfiglio1 = figli[0].fitness(arraylist, container);
        double fitnessfiglio2 = figli[1].fitness(arraylist, container);
        System.out.println("fitness dei figli:  " + fitnessfiglio1 + " ;  " + fitnessfiglio2);
        Individual[] figliMutati = population0.mutation(figli);
        System.out.println("Figli mutati = " + Arrays.toString(figliMutati));
        double fitnessMutazione1 = figliMutati[0].fitness(arraylist, container);
        double fitnessMutazione2 = figliMutati[1].fitness(arraylist, container);
        System.out.println("Fitness figli mutati: " + fitnessMutazione1 + "  ,  " + fitnessMutazione2);
*/
        /*
        var nextGen = population0.newGeneration(arraylist, container);
        System.out.println("Nuova generazione = " + nextGen);

        var newPop = nextGen.getPopulation();
        for (Individual individuo : newPop) {
            System.out.println("fitness individuo = " + individuo.fitness(arraylist, container));
        }

         */


        Population ultimaGenerazione = population0.repeatReproduction(arraylist, container);
        System.out.println(ultimaGenerazione);
        var ultimaGen = ultimaGenerazione.getPopulation();
        System.out.println("fitness individui ultima generazione");
        for (Individual individuo : ultimaGen) {
            System.out.println("fitness = " + individuo.fitness(arraylist, container));
        }



        System.out.println();
        System.out.println("Average fitness ultima generazione");
        System.out.println(ultimaGenerazione.getAverageFitness(arraylist, container));


        System.out.println(individual1.fitness2(arraylist, container));
        System.out.println(individual1);



        Container container2 = new Container(100, 100, 10, 10);
        TotBoxes arraylist2 = new TotBoxes();
        Box.setTotBoxesArray(arraylist2);
        Box boxA = new Box(1,2, 3 ,4);
        Box boxB = new Box(2,3, 5 ,7);
        Box boxC = new Box(1,2, 2, 2);
        Individual individualA = new Individual(arraylist2);
        System.out.println(individualA);
        System.out.println(individualA.fitness2(arraylist2, container2));
        System.out.println(arraylist2);
        var sortedarraylist = arraylist2.sortBoxesByHeight();
        System.out.println(sortedarraylist);

        Individual bestFit = ultimaGenerazione.bestFit(arraylist, container);
        System.out.println(bestFit + "è la combinazione di scatole migliore, con una fitness di " + bestFit.fitness(arraylist, container));
    }

}