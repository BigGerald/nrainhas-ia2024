package org.example.permfunction;

import org.example.AG;
import org.example.Individuo;

public class MainPermFunction {

    public static void main(String[] args) {
        int nVar= 8;
        int nPop = 20; // Tamanho da população
        int elite = 4; // Tamanho da elite
        int nGer = 2000; // Número de gerações

        PermFunctionIndFactory factory = new PermFunctionIndFactory(nVar);
        AG ag = new AG();
        Individuo melhorIndividuo = ag.executar(factory, nPop, elite, nGer);
        System.out.println("Perm Function");
        System.out.println("Melhor Indivíduo Final: " + melhorIndividuo);
        System.out.println("Avaliação Final: " + melhorIndividuo.getAvaliacao());
    }
}
