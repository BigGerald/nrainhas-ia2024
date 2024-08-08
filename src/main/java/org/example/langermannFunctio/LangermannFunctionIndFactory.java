package org.example.langermannFunctio;

import org.example.Individuo;
import org.example.IndividuoFactory;

public class LangermannFunctionIndFactory implements IndividuoFactory {

    private int n;

    public LangermannFunctionIndFactory(int n) {
        this.n = n;
    }

    @Override
    public Individuo getIndividuo() {
        return new LangermannFunctionInd(n);
    }
}
