package org.example.permfunction;

import org.example.Individuo;
import org.example.IndividuoFactory;

public class PermFunctionIndFactory implements IndividuoFactory {

    private int n;

    public PermFunctionIndFactory(int n) {
        this.n = n;
    }

    @Override
    public Individuo getIndividuo() {
        return new PermFunctionInd(n);
    }
}
