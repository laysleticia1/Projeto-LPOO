package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;

public class Floresta extends Ambiente {
    private String vegetacao;
    private String fauna;
    private String clima;

    //Construtor
    public Floresta () {
        super("Floresta", "Local coberto por uma vegetação densa e viva, repleta de árvores altas e sons constantes da natureza. Criaturas selvagens habitam essa região rica em recursos como frutas, madeira e ervas medicinais.", 2, new ArrayList<>(Arrays.asList("Frutas, Madeira, Ervas Medicinais")), 0.4, "Chuvoso");
        this.vegetacao = "Vegetação densa e úmida";
        this.fauna = "Lobos, cervos, passáros exóticos";
        this.clima = "Chuvoso";

    }
    //Setter e Getters
    public String getVegetacao() {
        return vegetacao;
    }

    public void setVegetacao(String vegetacao) {
        this.vegetacao = vegetacao;
    }

    public String getFauna() {
        return fauna;
    }

    public void setFauna(String fauna) {
        this.fauna = fauna;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
        setCondicaoClimatica(clima);
    }
}

