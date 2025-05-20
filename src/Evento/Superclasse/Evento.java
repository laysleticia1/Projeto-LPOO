package Evento.Superclasse;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import Interface.Executavel;
import Interface.Condicional;

public abstract class Evento implements Executavel, Condicional {
    private String nome;
    private String descricao;
    private double probabilidadeDeOcorrencia;
    private String impacto;
    private String condicaoDeAtivacao;

    //Construtor
    public Evento (String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.probabilidadeDeOcorrencia = probabilidadeDeOcorrencia;
        this.impacto = impacto;
        this.condicaoDeAtivacao = condicaoDeAtivacao;
    }

    public String getNomeEvento () {
        return nome;
    }
    public void setNomeEvento (String nomeEvento) {
        this.nome = nomeEvento;
    }
    public String getDescricao () {
        return descricao;
    }
    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }
    public double getProbabilidadeDeOcorrencia () {
        return probabilidadeDeOcorrencia;
    }
    public void setProbabilidadeDeOcorrencia (double probabilidadeDeOcorrencia) {
        this.probabilidadeDeOcorrencia = probabilidadeDeOcorrencia;
    }

    public String getImpacto () {
        return impacto;
    }
    void setImpacto (String impacto) {
        this.impacto = impacto;
    }
    public String getCondicaoDeAtivacao () {
        return condicaoDeAtivacao;
    }
    public void setCondicaoDeAtivacao (String condicaoDeAtivacao) {
        this.condicaoDeAtivacao = condicaoDeAtivacao;
    }

    void sortearEvento (Ambiente local) {
    }
    void aplicarEvento (Personagem jogador) {
    }
    void removerEvento (Evento evento) {
    }

    public abstract void executar(Personagem jogador, Ambiente local);

    public abstract boolean podeOcorrerNoAmbiente(Ambiente ambiente);
}

