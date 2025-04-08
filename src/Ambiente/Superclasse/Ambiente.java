package Ambiente.Superclasse;

import Personagem.Superclasse.Personagem;
import java.util.ArrayList;

public abstract class Ambiente {
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;
    private ArrayList<String> recursosDisponiveis;
    private double probabilidaDeEventos;
    private String condicaoClimatica;

    //Construtor
    public Ambiente (String nome, String descricao, int dificuldadeExploracao, ArrayList<String> recursosDisponiveis, double probabilidadeEventos, String condicaoClimatica) {
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.recursosDisponiveis = recursosDisponiveis;
        this.probabilidaDeEventos = probabilidaDeEventos;
        this.condicaoClimatica = condicaoClimatica;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nomeAmbiente){
        this.nome = nomeAmbiente;
    }
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricaoAmbiente){
        this.descricao = descricaoAmbiente;
    }
    public int getDificuldadeExploracao(){
        return dificuldadeExploracao;
    }
    public void setDificuldadeExploracao(int dificuldade){
        this.dificuldadeExploracao = dificuldade;
    }
    public ArrayList<String>  getRecursosDisponiveis(){
        return recursosDisponiveis;
    }
    public void setRecursosDisponiveis(ArrayList<String>  recursosDisponiveis){
        this.recursosDisponiveis = recursosDisponiveis;
    }
    public double getProbabilidadeEventos(){
        return probabilidaDeEventos;
    }
    public void setProbabilidadeEventos(double probabilidade){
        this.probabilidaDeEventos = probabilidade;
    }
    public String getCondicaoClimatica(){
        return condicaoClimatica;
    }
    public void setCondicaoClimatica(String condicao){
        this.condicaoClimatica = condicao;
    }

    public void explorar(Personagem jogador){
    }
    public void gerarEvento(){
    }
    public void modificarClima(){
    }
}
