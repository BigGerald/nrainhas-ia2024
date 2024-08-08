package org.example.langermannFunctio;

import org.example.Individuo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LangermannFunctionInd implements Individuo {

    private double[] genes;
    private double avaliacao;
    private static final int M = 5;
    private static final double[] a = {3, 5, 2, 1, 7};
    private static final double[] b = {5, 2, 1, 4, 9};
    private static final double c = Math.PI;

    public LangermannFunctionInd(int n) {
        this.genes = new double[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            genes[i] = rand.nextDouble() * 10 - 5; // Inicializa os genes com valores aleatórios entre -5 e 5
        }
        this.avaliacao = avaliar();
    }

    public LangermannFunctionInd(double[] genes) {
        this.genes = genes;
        this.avaliacao = avaliar();
    }

    @Override
    public List<Individuo> recombinar(Individuo ind) {
        LangermannFunctionInd pai2 = (LangermannFunctionInd) ind;
        double[] genes1 = new double[genes.length];
        double[] genes2 = new double[genes.length];
        Random rand = new Random();
        for (int i = 0; i < genes.length; i++) {
            double alpha = rand.nextDouble();
            genes1[i] = alpha * this.genes[i] + (1 - alpha) * pai2.genes[i];
            genes2[i] = alpha * pai2.genes[i] + (1 - alpha) * this.genes[i];
        }
        List<Individuo> filhos = new ArrayList<>();
        filhos.add(new LangermannFunctionInd(genes1));
        filhos.add(new LangermannFunctionInd(genes2));
        return filhos;
    }

    @Override
    public Individuo mutar() {
        double[] novosGenes = this.genes.clone();
        Random rand = new Random();
        for (int i = 0; i < novosGenes.length; i++) {
            novosGenes[i] += rand.nextGaussian();
        }
        return new LangermannFunctionInd(novosGenes);
    }

    @Override
    public double getAvaliacao() {
        return this.avaliacao;
    }

    private double avaliar() {
        double sum = 0.0;
        for (int i = 0; i < M; i++) {
            double innerSum = 0.0;
            for (int j = 0; j < genes.length; j++) {
                innerSum += Math.pow(genes[j] - a[i], 2);
            }
            sum += b[i] * Math.exp(-c / Math.PI * innerSum) * Math.cos(c * innerSum);
        }
        return -sum;
    }

    @Override
    public String toString() {
        return Arrays.toString(genes);
    }
}
