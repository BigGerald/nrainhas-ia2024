package org.example.dixonPriceFunction;

import org.example.Individuo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DixonPriceFunctionInd implements Individuo {

    private double[] genes;
    private double avaliacao;

    public DixonPriceFunctionInd(int n) {
        this.genes = new double[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            genes[i] = rand.nextDouble() * 10 - 5;
        }
        this.avaliacao = avaliar();
    }

    public DixonPriceFunctionInd(double[] genes) {
        this.genes = genes;
        this.avaliacao = avaliar();
    }

    @Override
    public List<Individuo> recombinar(Individuo ind) {
        DixonPriceFunctionInd pai2 = (DixonPriceFunctionInd) ind;
        double[] genes1 = new double[genes.length];
        double[] genes2 = new double[genes.length];
        Random rand = new Random();
        for (int i = 0; i < genes.length; i++) {
            double alpha = rand.nextDouble();
            genes1[i] = alpha * this.genes[i] + (1 - alpha) * pai2.genes[i];
            genes2[i] = alpha * pai2.genes[i] + (1 - alpha) * this.genes[i];
        }
        List<Individuo> filhos = new ArrayList<>();
        filhos.add(new DixonPriceFunctionInd(genes1));
        filhos.add(new DixonPriceFunctionInd(genes2));
        return filhos;
    }

    @Override
    public Individuo mutar() {
        double[] novosGenes = this.genes.clone();
        Random rand = new Random();
        for (int i = 0; i < novosGenes.length; i++) {
            novosGenes[i] += rand.nextGaussian();
        }
        return new DixonPriceFunctionInd(novosGenes);
    }

    @Override
    public double getAvaliacao() {
        return this.avaliacao;
    }

    private double avaliar() {
        double sum = Math.pow(genes[0] - 1, 2);
        for (int i = 1; i < genes.length; i++) {
            sum += i * Math.pow(2 * Math.pow(genes[i], 2) - genes[i - 1], 2);
        }
        return sum;
    }

    @Override
    public String toString() {
        return Arrays.toString(genes);
    }
}
