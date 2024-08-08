package org.example.dixonPriceFunction;

import org.example.AG;
import org.example.Individuo;

public class MainDixonPriceFunction {

    public static void main(String[] args) {
        int nVar = 4;
        int nPop = 20; // Tamanho da população
        int elite = 4; // Tamanho da elite
        int nGer = 2000; // Número de gerações

        DixonPriceFunctionIndFactory factory = new DixonPriceFunctionIndFactory(nVar);
        AG ag = new AG();
        Individuo melhorIndividuo = ag.executar(factory, nPop, elite, nGer);
        System.out.println("Dixon Price Function");
        System.out.println("Melhor Indivíduo Final: " + melhorIndividuo);
        System.out.println("Avaliação Final: " + melhorIndividuo.getAvaliacao());
    }
}
