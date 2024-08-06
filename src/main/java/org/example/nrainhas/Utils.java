package org.example.nrainhas;

public class Utils {
    public static void imprimirTabuleiro(IndividuoNRainhas individuo) {
        int nRainhas = individuo.getNRainhas();
        int[] posicoes = individuo.getVars();

        // Constrói e imprime o tabuleiro
        for (int i = 0; i < nRainhas; i++) {
            for (int j = 0; j < nRainhas; j++) {
                if (posicoes[i] == j) {
                    System.out.print("R  ");
                } else {
                    System.out.print("-  ");
                }
            }
            System.out.println();
        }
    }
}
