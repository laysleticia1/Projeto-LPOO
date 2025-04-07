package Evento.Subclasses;

import Evento.Superclasse.Evento;

public class EventoClimatico extends Evento {
    private String tipo;
    private int duracao;
    private String efeitoNoAmbiente;

    public EventoClimatico(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, String tipo, int duracao, String efeitoNoAmbiente) {
        super (nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
    }
}