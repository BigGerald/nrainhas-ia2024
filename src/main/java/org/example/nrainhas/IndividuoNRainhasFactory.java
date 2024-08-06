package org.example.nrainhas;

import org.example.Individuo;
import org.example.IndividuoFactory;

public class IndividuoNRainhasFactory implements IndividuoFactory {

	private int nRainhas;

	public IndividuoNRainhasFactory(int nRainhas) {
		this.nRainhas = nRainhas;
	}

	@Override
	public Individuo getIndividuo() {
		return new IndividuoNRainhas(nRainhas);
	}
}
