package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;

public class Montanha extends Ambiente {
    private String terreno;
    private String clima;
    private String vegetacao;

    //Construtor
    public Montanha (String nome, String descricao, int dificuldadeDeExploracao, ArrayList<String> recursosDisponiveis, double probabilidadeDeEventos, String condicaoClimatica) {
        super("Montanha", "Ambiente elevado, terreno acidentado e grandes variações de temperatura, ALTO RISCO", 8, new ArrayList<>(Arrays.asList("Minérios e pedras preciosas")), 0.6, "Frio e com ventos fortes" );
        this.terreno = "Rochoso e íngreme";
        this.clima = "Grandes variações de temperatura";
        this.vegetacao = "Escassa mas resiste";

        //Atributos herdados
        setNome("Montanha");
        setDescricao("Ambiente elevado com terrenos acidentados e com grandes variações de temperatura");
        setDificuldadeExploracao(8);
        setRecursosDisponiveis(new ArrayList<>(Arrays.asList("Minérios e pedras preciosas")));
        setProbabilidadeEventos(0.6);
        setCondicaoClimatica("Frios com ventos fortes");

        ArrayList<String> recursos = new ArrayList<>();
        recursos.add("Minérios e pedras preciosas");
        recursos.add("Água de Degelo (Precisa ser purificada) ");
        recursos.add("Refúgios naturais em cavernas");
        setRecursosDisponiveis(recursos);
    }
}
