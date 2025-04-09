package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;

public class Montanha extends Ambiente {
    private String terreno;
    private String clima;
    private String vegetacao;

    //Construtor
    public Montanha () {
        super("Montanha", "Local elevado e desafiador. A montanha apresenta terreno acidentado, variações bruscas de temperatura e ventos intensos. Esconde valiosos minérios e pedras preciosas entre os riscos naturais.", 8, new ArrayList<>(Arrays.asList("Minérios e pedras preciosas")), 0.6, "Frio e com ventos fortes");
        this.terreno = "Rochoso e íngreme";
        this.clima = "Grandes variações de temperatura";
        this.vegetacao = "Escassa mas resiste";
    }
}
