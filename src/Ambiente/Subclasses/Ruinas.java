package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ruinas extends Ambiente {
    private String estrutura;
    private String sobreviventes;
    private String clima;

    //Construtor
    public Ruinas() {
        super("Ruínas", "Local marcado por vestígios de uma civilização antiga. Estruturas em ruínas, inscrições enigmáticas e artefatos esquecidos compõem o cenário. Um ambiente misterioso e potencialmente perigoso.", 6, new ArrayList<>(Arrays.asList("Artefatos antigos, Estruturas colapsadas, inscrições misteriosas")), 0.6, "Seco, com fortes ventos");
        this.estrutura = "Estruturas dos antepassados que jaziam nessas terras";
        this.sobreviventes = "Sinais de antigos habitantes ou exploradores";
        this.clima = "Seco e empoeirado, com fortes ventos";

    }



}
