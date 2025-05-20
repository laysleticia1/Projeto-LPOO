package Ambiente.Superclasse;

import java.util.Collections;
import Personagem.Superclasse.Personagem;
import java.util.ArrayList;
import Item.Superclasse.*;
import Interface.Exploravel;
import Interface.Coletavel;

public abstract class Ambiente implements Exploravel, Coletavel {
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;
    private ArrayList<String> recursosDisponiveis;
    private double probabilidadeDeEventos;
    private String condicaoClimatica;

    //Construtor
    public Ambiente(String nome, String descricao, int dificuldadeExploracao, ArrayList<String> recursosDisponiveis, double probabilidadeEventos, String condicaoClimatica) {
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.recursosDisponiveis = recursosDisponiveis;
        this.probabilidadeDeEventos = probabilidadeDeEventos;
        this.condicaoClimatica = condicaoClimatica;
    }

    public abstract void explorar(Personagem jogador);

    public void gerarEvento() {
    }

    public void modificarClima() {
    }

    public String coletarRecursoAleatorio() {
        if (recursosDisponiveis.isEmpty()) return "nada";

        Collections.shuffle(recursosDisponiveis); // embaralha a lista
        return recursosDisponiveis.get(0);        // pega o primeiro após embaralhar
    }

    public abstract Item coletarItemAleatorio();

    // Getter e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nomeAmbiente) {
        this.nome = nomeAmbiente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricaoAmbiente) {
        this.descricao = descricaoAmbiente;
    }

    public int getDificuldadeExploracao() {
        return dificuldadeExploracao;
    }

    public void setDificuldadeExploracao(int dificuldade) {
        this.dificuldadeExploracao = dificuldade;
    }

    public ArrayList<String> getRecursosDisponiveis() {
        return recursosDisponiveis;
    }

    public void setRecursosDisponiveis(ArrayList<String> recursosDisponiveis) {
        this.recursosDisponiveis = recursosDisponiveis;
    }

    public double getProbabilidadeEventos() {
        return probabilidadeDeEventos;
    }

    public void setProbabilidadeEventos(double probabilidade) {
        this.probabilidadeDeEventos = probabilidade;
    }

    public String getCondicaoClimatica() {
        return condicaoClimatica;
    }

    public void setCondicaoClimatica(String condicao) {
        this.condicaoClimatica = condicao;
    }
    public boolean estaAcessivel(){
        return this.dificuldadeExploracao <= 70;
    }
}


