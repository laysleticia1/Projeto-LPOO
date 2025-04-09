package Evento.Subclasses;

import Evento.Superclasse.Evento;

public class EventoCriatura extends Evento {
    private String tipo;
    private int nivelPerigo;
    private String Acoes;

    public EventoCriatura(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, String tipo, int nivelPerigo, String acoes) {
        super(nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
    }
}