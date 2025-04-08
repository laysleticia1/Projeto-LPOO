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
    public Ruinas(String nome, String descricao, int dificuldadeDeExploracao, ArrayList<String> recursosDisponiveis, double probabilidadeDeEventos, String condicaoClimatica) {
        super("Ruínas", "Restos de uma civilização antiga com mistérios e perigos", 6, new ArrayList<>(Arrays.asList("Artefatos antigos, Estruturas colapsadas, inscrições misteriosas")), 0.6, "Seco, com fortes ventos");
        this.estrutura = "Estruturas dos antepassados que jaziam nessas terras";
        this.sobreviventes = "Sinais de antigos habitantes ou exploradores";
        this.clima = "Seco e empoeirado, com fortes ventos";

        setNome("Ruínas");
        setDescricao("Restos de uma civilização antiga com mistérios e perigos");
        setDificuldadeExploracao(8);
        setProbabilidadeEventos(0.6);
        setCondicaoClimatica("Seco e instável");


        ArrayList<String> recursos = new ArrayList<>();
        recursos.add("Artefatos antigos");
        recursos.add("Paredes com inscriçÕes misteriosas");
        recursos.add("Áreas com armadilhas ocultas");
        setRecursosDisponiveis(recursos);

    }



}
