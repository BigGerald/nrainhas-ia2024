import org.example.AG;
import org.example.Individuo;
import org.example.dixonPriceFunction.DixonPriceFunctionIndFactory;
import org.example.langermannFunctio.LangermannFunctionIndFactory;
import org.example.permfunction.PermFunctionIndFactory;

public class App {
    public static void main(String[] args) {
        int nVar= 2; //dimensoes
        int nPop = 20; // Tamanho da população
        int elite = 4; // Tamanho da elite
        int nGer = 2000; // Número de gerações

        AG ag = new AG();
        PermFunctionIndFactory permFactory = new PermFunctionIndFactory(nVar);
        LangermannFunctionIndFactory langermannFactory = new LangermannFunctionIndFactory(nVar);
        DixonPriceFunctionIndFactory dixonPricedFactory = new DixonPriceFunctionIndFactory(nVar);
        Individuo melhorIndividuoPerm = ag.executar(permFactory, nPop, elite, nGer);
        Individuo melhorIndividuoLangermann = ag.executar(langermannFactory, nPop, elite, nGer);
        Individuo melhorIndividuoDixon = ag.executar(dixonPricedFactory, nPop, elite, nGer);

        System.out.println("------------------------------------------------------------");
        System.out.println("Perm Function");
        System.out.println("Melhor Indivíduo Final: " + melhorIndividuoPerm);
        System.out.println("Avaliação Final: " + melhorIndividuoPerm.getAvaliacao());
        System.out.println("------------------------------------------------------------");
        System.out.println("Langernmann Function");
        System.out.println("Melhor Indivíduo Final: " + melhorIndividuoLangermann);
        System.out.println("Avaliação Final: " + melhorIndividuoLangermann.getAvaliacao());
        System.out.println("------------------------------------------------------------");
        System.out.println("Dixon Price Function");
        System.out.println("Melhor Indivíduo Final: " + melhorIndividuoDixon);
        System.out.println("Avaliação Final: " + melhorIndividuoDixon.getAvaliacao());
    }
}
