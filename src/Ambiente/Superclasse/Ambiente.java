package Ambiente.Superclasse;

import Personagem.Superclasse.Personagem;
import java.util.ArrayList;
import java.util.Collections;
import Item.Superclasse.*;
import Interface.Exploravel;
import Interface.Coletavel;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.net.URL;
import Item.Subclasses.*;
import Evento.Superclasse.*;
import Evento.Subclasses.*;


public abstract class Ambiente implements Exploravel, Coletavel {
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;
    private ArrayList<String> recursosDisponiveis;
    private double probabilidadeDeEventos;
    private String condicaoClimatica;
    protected String caminhoImagemAmbiente;
    protected transient Image imagemAmbienteCarregada;
    private String caminhoImagem;


    public Ambiente(String nome, String descricao, int dificuldadeExploracao, ArrayList<String> recursosDisponiveis, double probabilidadeEventos, String condicaoClimatica, String caminhoImagem) { // Novo parâmetro
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.recursosDisponiveis = recursosDisponiveis;
        this.probabilidadeDeEventos = probabilidadeEventos;
        this.condicaoClimatica = condicaoClimatica;
        this.caminhoImagemAmbiente = caminhoImagem;
        this.imagemAmbienteCarregada = null;
    }

    public abstract void explorar(Personagem jogador);

    public void gerarEvento() {
    }

    public void modificarClima() {
    }

    public String coletarRecursoAleatorio() {
        if (recursosDisponiveis == null || recursosDisponiveis.isEmpty()) return "nada por perto"; // Checagem de nulo adicionada

        Collections.shuffle(recursosDisponiveis);
        return recursosDisponiveis.get(0);
    }

    public abstract Item coletarItemAleatorio();
    public boolean estaAcessivel(){
        return this.dificuldadeExploracao <= 70; // Exemplo, pode ser mais complexo
    }


    //Getters and Setters
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

    // Interface
    public Image getImagemAmbiente() {
        if (imagemAmbienteCarregada == null && caminhoImagemAmbiente != null && !caminhoImagemAmbiente.isEmpty()) {
            try {
                URL imgUrl = getClass().getResource(caminhoImagemAmbiente);

                if (imgUrl != null) {
                    imagemAmbienteCarregada = new ImageIcon(imgUrl).getImage();
                    System.out.println("Imagem do ambiente '" + nome + "' carregada de: " + imgUrl.getPath());
                } else {
                    System.err.println("ERRO ao carregar imagem do ambiente '" + nome + "': Arquivo não encontrado em " + caminhoImagemAmbiente);

                }
            } catch (Exception e) {
                System.err.println("Exceção ao carregar imagem do ambiente '" + nome + "' de " + caminhoImagemAmbiente);
                e.printStackTrace();
                imagemAmbienteCarregada = null;
            }
        }
        return imagemAmbienteCarregada;
    }
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
}