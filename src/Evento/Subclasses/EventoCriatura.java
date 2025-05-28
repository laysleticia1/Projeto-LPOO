package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.*;
import Ambiente.Subclasses.*;

public class EventoCriatura extends Evento {
    private String tipo;
    private int nivelPerigo;
    private String acoes;

    public EventoCriatura(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, String tipo, int nivelPerigo, String acoes) {
        super(nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
    }

    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("\nEvento de Criatura: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Criatura: " + tipo);
        System.out.println("Nível de perigo: " + nivelPerigo);
        System.out.println("Ações possíveis: " + acoes);
        System.out.println("Impacto no personagem: " + getImpacto());
    }
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente instanceof Floresta;
    }
}