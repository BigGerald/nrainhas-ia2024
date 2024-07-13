package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Getter
public class IndividuoNRainhas implements Individuo{

	private int nRainhas;
	private int[] vars;
	private double avaliacao = -1;

	public IndividuoNRainhas(int nRainhas) {
		this(nRainhas, true);
	}

	private IndividuoNRainhas(int nRainhas, boolean r) {
		this.nRainhas = nRainhas;
		vars = new int[nRainhas];
		if (r) {
			// Inicializar o vetor vars aleatoriamente com valores entre 0 e nRainhas -1;
			Random random = new Random();
			for (int i = 0; i < nRainhas; i++) {
				vars[i] = random.nextInt(nRainhas);
			}
		}
	}

	@Override
	public List<Individuo> recombinar(Individuo ind) {
		List<Individuo> filhos = new ArrayList<>();
		if (ind instanceof IndividuoNRainhas) {
			IndividuoNRainhas outro = (IndividuoNRainhas) ind;
			IndividuoNRainhas f1 = new IndividuoNRainhas(this.nRainhas, false);
			IndividuoNRainhas f2 = new IndividuoNRainhas(this.nRainhas, false);

			// Recombinacao com um ponto de corte aleatório
			Random random = new Random();
			int pontoCorte = random.nextInt(nRainhas);
			for (int i = 0; i < nRainhas; i++) {
				if (i < pontoCorte) {
					f1.vars[i] = this.vars[i];
					f2.vars[i] = outro.vars[i];
				} else {
					f1.vars[i] = outro.vars[i];
					f2.vars[i] = this.vars[i];
				}
			}

			filhos.add(f1);
			filhos.add(f2);
		}
		return filhos;
	}

	@Override
	public Individuo mutar() {
		IndividuoNRainhas mutante = new IndividuoNRainhas(this.nRainhas, false);
		// Taxa de mutação
		double txm = 0.2;
		Random random = new Random();
		boolean mutado = false;
		for (int i = 0; i < this.vars.length; i++) {
			if (random.nextDouble() <= txm) {
				mutante.vars[i] = random.nextInt(nRainhas);
				mutado = true;
			} else {
				mutante.vars[i] = this.vars[i];
			}
		}

		// Se o mutante for idêntico ao pai, escolho um gene qualquer e forço a mutação.
		if (!mutado) {
			int pos = random.nextInt(nRainhas);
			mutante.vars[pos] = random.nextInt(nRainhas);
		}

		return mutante;
	}

	@Override
	public double getAvaliacao() {
		if (this.avaliacao < 0) {
			int colisoes = 0;
			for (int i = 0; i < this.nRainhas - 1; i++) {
				for (int j = i + 1; j < this.nRainhas; j++) {
					if (this.vars[i] == this.vars[j] || Math.abs(this.vars[i] - this.vars[j]) == j - i) {
						colisoes++;
					}
				}
			}
			this.avaliacao = colisoes;
		}
		return this.avaliacao;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < vars.length; i++) {
			sb.append(vars[i]);
			if (i < vars.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
