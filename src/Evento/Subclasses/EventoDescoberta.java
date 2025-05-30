package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
import Ambiente.Subclasses.*;

public class EventoDescoberta extends Evento {
    private String tipoDeDescoberta;
    private String recursosEncontrados;
    private String condicaoEspecial;

    public EventoDescoberta(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoAtivacao, String tipoDeDescoberta, String recursosEncontrados, String condicaoEspecial ) {
        super(nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoAtivacao);
        this.tipoDeDescoberta = tipoDeDescoberta;
        this.recursosEncontrados = recursosEncontrados;
        this.condicaoEspecial = condicaoEspecial;
    }

    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento de Descoberta: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo de Descoberta: " + tipoDeDescoberta);
        System.out.println("Recursos: " + recursosEncontrados);
        System.out.println("Condicao: " + condicaoEspecial);
        System.out.println("Impacto no jogo: " + getImpacto());
    }
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        // Suponha que só ocorre em Ruínas
        return ambiente instanceof Ruinas;
    }
}
