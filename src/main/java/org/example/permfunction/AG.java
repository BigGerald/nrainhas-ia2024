package org.example.permfunction;

import org.example.Individuo;
import org.example.IndividuoFactory;

import java.util.*;

public class AG {

    // Método principal para executar o algoritmo genético
    public Individuo executar(IndividuoFactory indFact, int nPop, int nElite, int numGer) {
        // Cria a população inicial
        List<Individuo> popIni = new ArrayList<>();
        for (int i = 0; i < nPop; i++) {
            popIni.add(indFact.getIndividuo());
        }

        Random rand = new Random();

        for (int g = 0; g < numGer; g++) {
            // Cria uma lista auxiliar para armazenar a população atual
            List<Individuo> popAux = new ArrayList<>(popIni);
            List<Individuo> filhos = new ArrayList<>();

            // Gera os filhos através do processo de recombinação
            for (int i = 0; i < nPop / 2; i++) {
                // Verifica se há pelo menos dois indivíduos em popAux para recombinação
                if (popAux.size() >= 2) {
                    Individuo pai1 = popAux.remove(rand.nextInt(popAux.size())); // Seleciona pai1 aleatoriamente
                    Individuo pai2 = popAux.remove(rand.nextInt(popAux.size())); // Seleciona pai2 aleatoriamente

                    List<Individuo> doisFilhos = pai1.recombinar(pai2); // Recombina os pais para gerar filhos
                    filhos.addAll(doisFilhos); // Adiciona os filhos à lista
                }
            }

            // Gera os mutantes a partir da população inicial
            List<Individuo> mutantes = new ArrayList<>();
            for (Individuo pai1 : popIni) {
                mutantes.add(pai1.mutar()); // Adiciona o mutante à lista
            }

            // Combina a população inicial, os filhos e os mutantes
            // Usar Set para remover duplicatas
            Set<Individuo> joinSet = new HashSet<>(popIni); // Cria um HashSet com os elementos de popIni
            joinSet.addAll(filhos); // Adiciona os filhos ao HashSet, removendo duplicatas automaticamente
            joinSet.addAll(mutantes); // Adiciona os mutantes ao HashSet, removendo duplicatas automaticamente
            List<Individuo> joinList = new ArrayList<>(joinSet); // Converte o HashSet de volta para uma List

            // Seleciona a nova população
            List<Individuo> newPop = new ArrayList<>();
            newPop.addAll(selecionarElite(joinList, nElite, true)); // Seleciona a elite
            roleta(joinList, nPop - nElite, true, newPop); // Seleciona o restante utilizando a roleta viciada

            popIni.clear();
            popIni.addAll(newPop);

            // Imprime informações sobre a geração atual
            Individuo melhor = encontrarMelhor(popIni);
            System.out.println("Geração: " + g + ", Melhor Indivíduo: " + melhor + ", Avaliação: " + melhor.getAvaliacao());
        }

        return encontrarMelhor(popIni); // Seleciona o melhor indivíduo da população final
    }

    // Método para selecionar a elite dos melhores indivíduos
    private List<Individuo> selecionarElite(List<Individuo> joinList, int nElite, boolean isMinimizacao) {
        joinList.sort(Comparator.comparingDouble(Individuo::getAvaliacao)); // Ordena os indivíduos
        if (!isMinimizacao) {
            Collections.reverse(joinList); // Inverte a ordem dos indivíduos
        }
        return new ArrayList<>(joinList.subList(0, nElite)); // Retorna os nElite melhores indivíduos
    }

    // Método para encontrar o melhor indivíduo
    private Individuo encontrarMelhor(List<Individuo> pop) {
        return pop.stream().min(Comparator.comparingDouble(Individuo::getAvaliacao)).orElse(null);
    }

    // Método da roleta viciada para seleção
    private void roleta(List<Individuo> joinList, int nSelecao, boolean isMinimizacao, List<Individuo> newPop) {
        double soma1 = 0.0;

        // Calcula a soma das avaliações
        for (Individuo ind : joinList) {
            soma1 += isMinimizacao ? 1.0 / ind.getAvaliacao() : ind.getAvaliacao();
        }

        Random rand = new Random();

        for (int i = 0; i < nSelecao; i++) {
            double alfa = rand.nextDouble() * soma1; // Gera um número aleatório entre 0 e soma1
            double soma2 = 0.0;

            // Encontra o indivíduo correspondente ao alfa
            for (Individuo ind : joinList) {
                soma2 += isMinimizacao ? 1.0 / (Math.pow(ind.getAvaliacao(), 4) + 0.1) : ind.getAvaliacao();
                if (soma2 >= alfa) {
                    newPop.add(ind);
                    joinList.remove(ind);
                    soma1 -= isMinimizacao ? 1.0 / ind.getAvaliacao() : ind.getAvaliacao();
                    break;
                }
            }
        }
    }
}
