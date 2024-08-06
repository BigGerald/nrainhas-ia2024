package org.example.permfunction;

import org.example.Individuo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PermFunctionInd implements Individuo {

    private double[] genes;
    private double avaliacao;
    private static final double BETA = 0.5; // Parâmetro beta para a função Perm Function

    public PermFunctionInd(int n) {
        this.genes = new double[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            genes[i] = rand.nextDouble() * 10 - 5; // Inicializa os genes com valores aleatórios entre -5 e 5
        }
        this.avaliacao = avaliar();
    }

    // Construtor para criar um novo indivíduo a partir de um array de genes
    public PermFunctionInd(double[] genes) {
        this.genes = genes;
        this.avaliacao = avaliar();
    }

    // Crossover Aritmético
    @Override
    public List<Individuo> recombinar(Individuo ind) {
        PermFunctionInd pai2 = (PermFunctionInd) ind;
        double[] genes1 = new double[genes.length];
        double[] genes2 = new double[genes.length];
        Random rand = new Random();
        for (int i = 0; i < genes.length; i++) {
            double alpha = rand.nextDouble();
            genes1[i] = alpha * this.genes[i] + (1 - alpha) * pai2.genes[i];
            genes2[i] = alpha * pai2.genes[i] + (1 - alpha) * this.genes[i];
        }
        List<Individuo> filhos = new ArrayList<>();
        filhos.add(new PermFunctionInd(genes1));
        filhos.add(new PermFunctionInd(genes2));
        return filhos;
    }

    // Mutação Gaussiana
    @Override
    public Individuo mutar() {
        double[] novosGenes = this.genes.clone();
        Random rand = new Random();
        for (int i = 0; i < novosGenes.length; i++) {
            novosGenes[i] += rand.nextGaussian(); // Adiciona ruído gaussiano
        }
        return new PermFunctionInd(novosGenes);
    }

    @Override
    public double getAvaliacao() {
        return this.avaliacao;
    }

    private double avaliar() {
        double sum = 0.0;
        for (double gene : genes) {
            sum += Math.pow(gene, 2);
        }
        return sum;
    }


    @Override
    public String toString() {
        return Arrays.toString(genes);
    }
}
