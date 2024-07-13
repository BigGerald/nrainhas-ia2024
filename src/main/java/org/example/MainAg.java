package org.example;

import static org.example.Utils.imprimirTabuleiro;

public class MainAg {

    public static void main(String[] args) {
        int nRainhas = 20;
        int nPop = 20;
        int nElite = 4;
        int numGer = 250*nRainhas;

        IndividuoFactory indFact = new IndividuoNRainhasFactory(nRainhas);
        AG ag = new AG();
        Individuo ind = ag.executar(indFact, nPop, nElite, numGer);
        System.out.println("Melhor Indivíduo: " + ind + ", Avaliação: " + ind.getAvaliacao());

        // Imprime o tabuleiro
        imprimirTabuleiro((IndividuoNRainhas) ind);
    }
}
