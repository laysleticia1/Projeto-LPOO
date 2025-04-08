package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;

public class Caverna extends Ambiente {
    private String luminosidade;
    private String criaturas;
    private String hidratacao;

    //Construtor
    public Caverna (String nome, String descricao, int DificuldaDeExploracao, ArrayList<String> recursosDisponiveis, double ProbabilidadeDeEventos, String CondicaoClimatica ) {
        super("Caverna", "Caverna subterrânea e escura", 7, new ArrayList<>(Arrays.asList("Rochas e minérios raros")), 0.65, "Úmido e escuro" );
       this.luminosidade = "Pouca luz";
       this.criaturas = "Presença de criaturas desconhecidas...";
       this.hidratacao = "Água de gotejamento";

       //Atributos herdados
        setNome("Caverna");
        setDescricao("Caverna subterrânea e escura e com vários riscos");
        setDificuldadeExploracao(7);
        setRecursosDisponiveis(new ArrayList<>(Arrays.asList("Pedras e metais preciosos")));
        setProbabilidadeEventos(0.65);
        setCondicaoClimatica("Úmido e escuro");

        ArrayList<String> recursos = new ArrayList<>();
        recursos.add("Rochas e minérios raros");
        recursos.add("Pequenos lagos subterrâneos");
        recursos.add("Ossos e vestígios de antigos exploradores");
        setRecursosDisponiveis(recursos);
    }
}
