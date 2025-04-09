package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;

public class LagoRio extends Ambiente {
    private String hidratacao;
    private String pesca;
    private String terreno;

    //Construtor
    public LagoRio () {
        super("Lago/Rio", "Local de águas calmas e cristalinas. Um lago ou rio que oferece oportunidades de hidratação, pesca e travessia. A presença de peixes e algas torna o ambiente fértil e estratégico.", 2, new ArrayList<>(Arrays.asList("Água, peixes e algas")), 0.4, "Úmido e fresco");
        this.hidratacao = "Água potável, mas existem regiões contaminadas...";
        this.pesca = "Rica fonte de peixes";
        this.terreno = "Plano, bonito e escorregadio nas margens";

    }

    //Getters e Setters específicos

}
