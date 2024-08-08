package org.example.langermannFunctio;

import org.example.AG;
import org.example.Individuo;


public class MainLangermannFunction {

    public static void main(String[] args) {
        int nVar = 2;
        int nPop = 20; // Tamanho da população
        int elite = 4; // Tamanho da elite
        int nGer = 2000; // Número de gerações

        LangermannFunctionIndFactory factory = new LangermannFunctionIndFactory(nVar);
        AG ag = new AG();
        Individuo melhorIndividuo = ag.executar(factory, nPop, elite, nGer);
        System.out.println("Langernmann Function");
        System.out.println("Melhor Indivíduo Final: " + melhorIndividuo);
        System.out.println("Avaliação Final: " + melhorIndividuo.getAvaliacao());
    }
}
