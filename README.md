Questo è un classico problema di ottimizzazione il cui scopo è quello di riempire un contenitore con il massimo numero di item, senza eccedere in certi parametri prestabiliti. <br>
In questo caso specifico il contenitore ha un certo peso e un certo volume prestabiliti e ogni "box" ha un suo peso e volume prestabiliti. <br>
Lo scopo è quindi, dato un certo insieme iniziale di scatole, inserire quante più scatole all'interno del contenitore senza che il peso e volume complessivi delle scatole superi peso e volume del contenitore. <br>
Gli algoritmi genetici sono una classe di algoritmi ispirati al processo di selezione naturale, utili nel caso in cui serva trovare una soluzione ai problemi di ottimizzazione. <br>
Secondo la teoria dell'evoluzione di Darwin, gli individui più adatti in un ambiente sopravvivono e trasmettono i loro tratti alle generazioni future, mentre i tratti deboli muoiono lungo il percorso.
Quando risolviamo un problema con un algoritmo genetico, dobbiamo modellarlo in modo che subisca l'evoluzione attraverso operazioni di mutazione, crossover, riproduzione e selezione.
<br>

Il problema viene risolto nel modo seguente. <br>
Ogni <strong>"Individuo"</strong> è una potenziale soluzione al problema di packing delle scatole. <br>
Ciascun individuo è un  <strong>array binario </strong> lungo quanto l'insieme totale delle scatole: lo 0 nell'array rappresenza l'assenza di una scatola, l'1 la sua presenza. <br>
Supponiamo di avere 10 scatole: <br> <br>

Individuo1 = [0,0,1,1,0,1,0,0,1,1] <br>
Individuo2 = [0,1,1,0,0,0,1,0,0,1]  <br> <br>
 <br>
Nella prima potenziale soluzione, si tenta di inserire nel container le scatole 3,4,6,9 e 10; nella seconda soluzione si inseriscono le scatole 2,3,7 e 10.  <br>
Ogni individuo ha una sua <strong>"fitness"</strong>, che calcola quanto quella specifica soluzione è buona.  <br>
Se l'insieme di scatole Individuo1 eccede in peso e/o volume il peso/volume massimo del contenitore, allora la fitness di quell'individuo sarà 0, siccome non presenta una soluzione fattibile. <br>
Se il peso / volume totali delle scatole nell'individuo1 non eccedono quelli del contenitore, la fitness verrà calcolata come numeroScatoleNellIndividuo / totaleScatole.  <br>
Supponendo che entrambi gli individui non eccedano il peso e volume totale del contenitore, il primo individuo avrà una fitness di 5/10 mentre il secondo avrà fitness 4/10.
Più la fitness è alta, più l'individuo è ottimale: in questo caso, il migliore tra i due sarebbe l'Individuo1. <br> <br>

Si procede creando una <strong>Popolazione</strong>, cioè un insieme di Individui (= un insieme di possibili soluzioni). Questa sarà la prima generazione.
 <br>
Esempio: <br> <br>
PrimaGenerazione { <br>
  Individuo1 [0,0,1,1,0,0,1,0,1,1],  <br>
  Individuo2 [1,1,0,0,1,1,0,1,0,1],  <br>
  Individuo3 [1,1,1,0,0,0,0,1,0,0],  <br>
  Individuo4 [1,1,0,0,0,0,1,1,1,1],  <br>
  Individuo5 [0,0,1,1,1,1,0,0,0,1],  <br>
  Individuo6 [1,0,0,1,1,0,0,1,1,0] <br>
} <br> <br>

Il processo di <strong>selezione</strong> degli individui migliori avviene simulando un torneo in cui vince chi ha fitness più alta. <br>
Vengono scelti a random quattro individui che si "batteranno" due a due: i due vincitori saranno i "genitori" che contribuiranno a formare una nuova generazione che, seguendo la logica dell'evoluzione, presenterà dei livelli di fitness più alti di quelli delle generazioni precedenti.
 <br>
Da due genitori vincitori, vengono generati due figli tramite il metodo del <strong>crossover</strong>: <br>
Simulando il processo genetico, ogni individuo ha un certo set di geni (gli 0 e 1 all'interno dell'array). Supponiamo che nel nostro caso, i genitori saranno Individuo2 e Individuo5: <br>
Individuo2 ha i geni [1,1,0,0,1,1,0,1,0,1] e Individuo5 ha i geni [0,0,1,1,1,1,0,0,0,1] <br>
Il metodo del crossover fa sì che ogni figlio acquisirà metà geni da un genitore e metà geni dall'altro, combinando così i due array precedenti. <br>
- il primo figlio prenderà dal genitore 1 i geni [1,1,0,0,1] e dal genitore 2 i geni [1,0,0,0,1], diventando così un individuo [1,1,0,0,1,1,0,0,0,1] <br>
- il secondo figlio prenderà dal genitore 1 i restanti geni [1,0,1,0,1] e dal genitore 2 i restanti geni [0,0,1,1,1] diventando così un individuo [1,0,1,0,1,0,0,1,1,1] <br>
 <br>
Si ripete questo processo di riproduzione da genitori a figli fino ad ottenere una nuova generazione con tanti individui al suo interno quanti ce ne sono nella prima generazione. <br>
 <br>
Come avviene in realtà, il progetto simula anche la possibilità che, una volta tanto, avvenga anche una <strong>mutazione casuale</strong> dei geni. Una volta tanto, quindi, uno 0 può divenare un 1 o viceversa. <br>
 <br>
Il metodo <strong>newGeneration()</strong> ripete questi processi di selezione e riproduzione fino a creare una nuova generazione di tanti individui quanti la popolazione iniziale. <br>
Con il metodo <strong>repeatReproduction()</strong> la creazione di una nuova generazione viene ripetuta di un numero di volte arbitrario (10.000, nel mio caso). <br>
Probabilisticamente, l'ultima popolazione presenterà certi individui con una fitness più alta degli individui in partenza. <br>
 <br>
Il metodo <strong>bestFit()</strong> infine seleziona, all'interno della popolazione finale, l'Individuo con fitness più alta, che rappresenta la soluzione di packing migliore.
 <br>
Si noti che, essendo un problema altamente randomico e probabilistico, NON è comunque mai detto al 100% che l'ultima generazione presenterà fitness sicuramente più alte di quelle iniziali. <br> <br>

La classe ContainerPlane è stata creata per creare un secondo modo alternativo di calcolare la fitness di ogni individuo, un calcolo che tenga conto non solo del fatto che le scatole non dovrebbero eccedere il peso e il volume totale del contenitore, ma che tenga conto anche del fatto che le scatole riescano a entrare nel contenitore data la loro forma.
Infatti, è possibile nella realtà che un insieme di scatole abbia volume tale da poter rientrare in un contenitore, ma che non riescano a rientrarci a causa della loro forma. <br>
Il metodo fitness2 <br>
- controlla che peso e volume complessivo delle scatole in un individuo non eccedano peso e volume del contenitore <br>
- per ogni scatola, controlla che la sua base e altezza non superino la base e l'altezza del contenitore <br>
    - se la scatola ci sta, l'area del contenitore viene aggiornata rimuovendo l'area ormai occupata dalla scatola appena inserita <br>
    - si passa alla scatola successiva controllando che la sua base e altezza non superino le dimensioni dell'area rimasta disponibile, e così via per ogni scatola <br>
 <br> <br>
Il metodo è da migliorare perché innanzitutto vorrei poter simulare la rotazione delle scatole; ma soprattutto ho in mente di modificarlo in modo da creare una simulazione in 3D del container e delle scatole da inserire, mentre per ora il container da riempire è considerato come un piano 2D e per ogni scatola considero soltanto le dimensioni della sua base: il problema attuale è quindi quello di riempire un rettangolo con tanti rettangoli più piccoli.
