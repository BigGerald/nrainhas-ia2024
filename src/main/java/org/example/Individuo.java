package org.example;

import java.util.List;

public interface Individuo {
	
	List<Individuo> recombinar(Individuo ind);
	
	Individuo mutar();
	
	double getAvaliacao();

}
