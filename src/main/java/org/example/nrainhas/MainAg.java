package org.example.nrainhas;


import org.example.Individuo;
import org.example.IndividuoFactory;

import static org.example.nrainhas.Utils.imprimirTabuleiro;

public class MainAg {

    public static void main(String[] args) {
        int nRainhas = 25;
        int nPop = 40;
        int nElite = 7;
        int numGer = 5000*nRainhas;

        IndividuoFactory indFact = new IndividuoNRainhasFactory(nRainhas);
        AG ag = new AG();
        Individuo ind = ag.executar(indFact, nPop, nElite, numGer);
        System.out.println("Melhor Indivíduo: " + ind + ", Avaliação: " + ind.getAvaliacao());

        // Imprime o tabuleiro
        imprimirTabuleiro((IndividuoNRainhas) ind);
    }
}
