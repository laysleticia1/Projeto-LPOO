package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;

public class LagoRio extends Ambiente {
    private String hidratacao;
    private String pesca;
    private String terreno;

    //Construtor
    public LagoRio (String nome, String descricao, int DificuldaDeExploracao, ArrayList<String> recursosDisponiveis, double ProbabilidadeDeEventos, String CondicaoClimatica ) {
        super("Lago/Rio", "Um grande Lago/Rio que oferece oportunidades de hidratação, pesca e travessia", 2, new ArrayList<>(Arrays.asList("Água, peixes e algas")), 0.4, "Úmido e fresco");
        this.hidratacao = "Água potável, mas existem regiões contaminadas...";
        this.pesca = "Rica fonte de peixes";
        this.terreno = "Plano, bonito e escorregadio nas margens";

        //Atributos herdados
        setNome("Lago/Rio");
        setDescricao("Um grande Lago/Rio que oferece oportunidades de hidratação, pesca e travessia");
        setDificuldadeExploracao(2);
        setRecursosDisponiveis(new ArrayList<>(Arrays.asList("Água, peixes e algas")));
        setProbabilidadeEventos(0.4);
        setCondicaoClimatica("Úmido e fresco");

        ArrayList<String> recursos = new ArrayList<>();
        recursos.add("Peixes e algas");
        recursos.add("Água potável (risco de contaminação)");
        recursos.add("Vegetação Ribeirinha para confecção");
    }

    //Getters e Setters específicos

}
