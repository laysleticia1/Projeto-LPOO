package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.*;
import Ambiente.Subclasses.*;
import Criatura.Subclasses.*;
import Criatura.Superclasse.*;

public class EventoCriatura extends Evento {
    private String tipo;
    private int nivelPerigo;
    private String acoes;
    private Criatura criatura;

    public EventoCriatura(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, Criatura criatura, int nivelPerigo, String acoes) {
        super(nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
        this.criatura = criatura;
        this.nivelPerigo = nivelPerigo;
        this.acoes = acoes;
    }


    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("⚔️ Criatura encontrada: " + criatura.getClass().getSimpleName());
        criatura.atacar(jogador);
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Ação especial: " + acoes);
    }

    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente instanceof Floresta;
    }
}