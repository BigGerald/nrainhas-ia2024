package org.example.dixonPriceFunction;

import org.example.Individuo;
import org.example.IndividuoFactory;

public class DixonPriceFunctionIndFactory implements IndividuoFactory {

    private int n;

    public DixonPriceFunctionIndFactory(int n) {
        this.n = n;
    }

    @Override
    public Individuo getIndividuo() {
        return new DixonPriceFunctionInd(n);
    }
}
