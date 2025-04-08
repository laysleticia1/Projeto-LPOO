package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;

public class Floresta extends Ambiente {
    private String vegetacao;
    private String fauna;
    private String clima;

    //Construtor
    public Floresta (String nome, String descricao, int dificuldadeDeExploracao, ArrayList<String> recursosDisponiveis, double probabilidadeDeEventos, String condicaoClimatica) {
        super("Floresta", "Floresta densa e habitada", 2, new ArrayList<>(Arrays.asList("Frutas, Madeira, Ervas Medicinais")), 0.4, "Chuvoso");
        this.vegetacao = "Vegetação densa e úmida";
        this.fauna = "Lobos, cervos, passáros exóticos";
        this.clima = "Chuvoso";

        //Atributos herdados da superclasse
        setNome("Floresta");
        setDescricao("Uma floresta com " + vegetacao + ", habitada por " + fauna + ".");
        setDificuldadeExploracao(2);
        setRecursosDisponiveis(new ArrayList<>(Arrays.asList("Frutas, Madeira, Ervas Medicinais ")));
        setProbabilidadeEventos(0.4);
        setCondicaoClimatica(clima);
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

