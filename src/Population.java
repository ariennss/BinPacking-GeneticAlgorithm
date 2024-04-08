import java.util.*;

public class Population {

    private Individual[] population;
    private int howManyIndividuals;
    private static double mutationRate = 0.02;

    public Population(int populationLength) {
        this.population = new Individual[populationLength];
        howManyIndividuals = 0;
    }

    public Individual[] getPopulation() {
        return population;
    }

    public double getAverageFitness(TotBoxes totBoxes, Container container) {
        double averageFitness = 0;
        double totFitness = 0;
        int quanti = 0;
        Individual[] pop = this.getPopulation();
        for (Individual individual : pop) {
            quanti++;
            double fitnessIndividuo = individual.fitness(totBoxes, container);
            totFitness = totFitness + fitnessIndividuo;

        }
        averageFitness = totFitness / quanti;
        return averageFitness;
    }

    public void setPopulation(Individual[] population) {
        this.population = population;
    }

    public int getHowManyIndividuals() {
        return howManyIndividuals;
    }

    public void addIndividual(Individual x) {
        if (howManyIndividuals < population.length) {
            this.population[howManyIndividuals] = x;
            howManyIndividuals++;
        }
        else System.out.println("Non è possibile aggiungere " + x);
    }

    public Individual[] selection(TotBoxes totBoxes, Container container) {
        Individual[] population = this.getPopulation();
        Individual[] genitori = new Individual[2];
        Individual vincitore1 = null;
        Individual vincitore2 = null;
        List<Individual> popList = new ArrayList<>(Arrays.asList(population));
        Collections.shuffle(popList);
        popList.toArray(population);

        Individual partecipante1 = population[0];
        Individual partecipante2 = population[1];
        Individual partecipante3 = population[2];
        Individual partecipante4 = population[3];

        if (partecipante1.fitness(totBoxes, container) > partecipante2.fitness(totBoxes, container)) {
            vincitore1 = partecipante1;
        } else {vincitore1 = partecipante2;}

        if (partecipante3.fitness(totBoxes, container) > partecipante4.fitness(totBoxes, container)) {
            vincitore2 = partecipante3;
        } else {vincitore2 = partecipante4;}

        genitori[0] = vincitore1;
        genitori[1] = vincitore2;

        return genitori;
    }
    //trova un individuo con fitness bassa :(

    //seleziona due individui con maggiore fitness: saranno i genitori.
    /*
    public Individual[] selection(TotBoxes totBoxes, Container container) {
        Individual[] popolazione = this.getPopulation();
        double fitness1 = 0;
        double fitness2 = 0;
        Individual genitore1 = null;
        Individual genitore2 = null;
        Individual[] genitori = new Individual[2];

        for (int i = 0; i < popolazione.length; i++) {
            if (popolazione[i].fitness(totBoxes, container) > fitness1) {
                fitness1 = popolazione[i].fitness(totBoxes, container);
                genitore1 = popolazione[i];
            }
        }

        for (int j = 0; j < popolazione.length; j++) {
            if (popolazione[j] != genitore1 && popolazione[j].fitness(totBoxes, container) > fitness2) {
                fitness2 = popolazione[j].fitness(totBoxes, container);
                genitore2 = popolazione[j];
            }
        }

        genitori[0] = genitore1;
        genitori[1] = genitore2;

        return genitori;
    }


     */
    //prendiamo i genitori generati prima, e creiamo dei figli che abbiano parte dei
    //geni di entrambi i genitori.
    public Individual[] crossover(Individual[] genitori, TotBoxes scatole) {
        Individual[] figli = new Individual[2];
        Individual genitore1 = genitori[0];
        Individual genitore2 = genitori[1];
        Individual figlio1 = new Individual(scatole);
        Individual figlio2 = new Individual(scatole);
        int[] geniGenitore1 = genitore1.getGenes();
        int[] geniGenitore2 = genitore2.getGenes();
        int[] geniFiglio1 = figlio1.getGenes();
        int[] geniFiglio2 = figlio2.getGenes();
        int nGeni = geniGenitore1.length;
        int crossoverPoint = nGeni / 2;
        /*
         if (nGeni % 2 != 0) {
            crossoverPoint = crossoverPoint -1;
        }
         */
        for (int i = 0; i < crossoverPoint; i++) {
            geniFiglio1[i] = geniGenitore1[i];
            geniFiglio2[i] = geniGenitore2[i];
        }

        for (int j = crossoverPoint; j < nGeni; j++) {
            geniFiglio1[j] = geniGenitore2[j];
            geniFiglio2[j] = geniGenitore1[j];
        }

        figli[0] = figlio1;
        figli[1] = figlio2;

        return figli;
    }


    public Individual[] mutation(Individual[] figli) {
        Individual[] childMutation = new Individual[2];
        Individual figlio1 = figli[0]; //figli[0].copy();
        Individual figlio2 = figli[1]; //figli[1].copy(); vedere se meglio fare copia
        int[] geniFiglio1 = figlio1.getGenes();
        int[] geniFiglio2 = figlio2.getGenes();

        Random random = new Random();
        for (int i = 0; i < geniFiglio1.length; i++) {
            if (random.nextDouble() < mutationRate) {
                if (geniFiglio1[i] == 1) {
                    geniFiglio1[i] = 0;
                } else geniFiglio1[i] = 1;

                if (geniFiglio2[i] == 1) {
                    geniFiglio2[i] = 0;
                } else geniFiglio2[i] = 1;
            }
        }

        childMutation[0] = figlio1;
        childMutation[1] = figlio2;
        return childMutation;
    }


    public Population newGeneration(TotBoxes totBoxes, Container container) {
        Individual[] population = this.getPopulation();
        int populationSize = population.length;
        Population newGen = new Population(populationSize);
        Individual[] newGeneration = newGen.getPopulation();

        for (int i = 0; i < populationSize; i += 2) {
            // Select two parents from the initial population
            Individual[] parents = selection(totBoxes, container);
           // System.out.println("genitori = " + parents[0] + " and " + parents[1]);
            // Apply crossover to generate children
            Individual[] children = crossover(parents, totBoxes);
           // System.out.println("children = " + children[0] + " and " + children[1]);
          //  System.out.println();

            Individual[] childrenMutation = mutation(children);
            // Add children to the new generation
            newGeneration[i] = childrenMutation[0];
            newGeneration[i + 1] = childrenMutation[1];
        }

        return newGen;
    }

    public Population repeatReproduction(TotBoxes totBoxes, Container container) {
        int howManyGenerations = 10000;
        Population myGeneration = this;
        for (int i = 0; i < howManyGenerations; i++) {
            Individual[] newGen = myGeneration.newGeneration(totBoxes, container).getPopulation();
            myGeneration.setPopulation(newGen);
        }
        return myGeneration;
    }

    public Individual bestFit(TotBoxes totboxes, Container container) {
        Individual bestFit = null;
        double highestFitness = 0;
        Individual[] lastPopulation = this.getPopulation();
        for ( Individual individuo : lastPopulation) {
            double fitness = individuo.fitness(totboxes, container);
            if (fitness > highestFitness) {
                highestFitness = fitness;
                bestFit = individuo;
            }
        }

        return bestFit;
    }


    @Override
    public String toString() {
        return "Population{" +
                "population=" + Arrays.toString(population) +
                '}';
    }
}
