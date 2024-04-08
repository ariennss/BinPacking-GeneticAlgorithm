import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Container container = new Container(25, 30, 100, 100);
        TotBoxes totaleScatole = new TotBoxes();
        Box.setTotBoxesArray(totaleScatole);
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
        Box box13 = new Box(1,1, 2, 4);
        Box box14 = new Box(4,3, 3 ,4);
        Box box15 = new Box(2,1, 3 ,4);
        Box box16 = new Box(2,2, 3 ,4);
        Box box17 = new Box(1,3, 3 ,4);
        Box box18 = new Box(2,1, 3 ,4);
        Box box19 = new Box(2,2, 3 ,4);
        Box box20 = new Box(1,2, 3 ,4);
        System.out.println(totaleScatole);

        Individual individual1 = new Individual(totaleScatole);
        Individual individual2 = new Individual(totaleScatole);
        Individual individual3 = new Individual(totaleScatole);
        Individual individual4 = new Individual(totaleScatole);
        Individual individual5 = new Individual(totaleScatole);
        Individual individual6 = new Individual(totaleScatole);




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
            System.out.println("fitness = " + individual.fitness(totaleScatole, container));
        }

        //DECOMMENTARE QUESTA PARTE PER INFO SUL FUNZIONAMENTO DEI METODI NELLO SPECIFICO
 /*
        Individual[] genitori = population0.selection(totaleScatole, container);
        System.out.println("I genitori sono: ");
        System.out.println(Arrays.toString(genitori));
        System.out.println("fitness genitore1 " + genitori[0].fitness(totaleScatole, container));
        System.out.println("fitness genitore2 " + genitori[1].fitness(totaleScatole, container));

        System.out.println(Arrays.toString(genitori));
        Individual[] figli = population0.crossover(genitori, totaleScatole);
        System.out.println("I figli sono: " + Arrays.toString(figli));

        Individual[] figliMutati = population0.mutation(figli);
        System.out.println("Figli mutati = " + Arrays.toString(figliMutati) + " ! attenzione, la mutazione è randomica e può avvenire come può non avvenire !");



        var nextGen = population0.newGeneration(totaleScatole, container);
        System.out.println("Nuova generazione = " + nextGen);

        var newPop = nextGen.getPopulation();
        for (Individual individuo : newPop) {
            System.out.println("fitness individuo = " + individuo.fitness(totaleScatole, container));
        }


*/

        Population ultimaGenerazione = population0.repeatReproduction(totaleScatole, container);
        System.out.println(ultimaGenerazione);
        var ultimaGen = ultimaGenerazione.getPopulation();
        System.out.println("fitness individui ultima generazione");
        for (Individual individuo : ultimaGen) {
            System.out.println("fitness = " + individuo.fitness(totaleScatole, container));
        }



        Individual bestFit = ultimaGenerazione.bestFit(totaleScatole, container);
        System.out.println(bestFit + " è la combinazione di scatole migliore, con una fitness di " + bestFit.fitness(totaleScatole, container));
    }

}