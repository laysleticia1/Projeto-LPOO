package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

public class EventoClimatico extends Evento {
    private String tipo;
    private int duracao;
    private String efeitoNoAmbiente;

    public EventoClimatico(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, String tipo, int duracao, String efeitoNoAmbiente) {
        super (nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
    }


    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento Climático: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo de clima: " + tipo);
        System.out.println("Duração esperada: " + duracao + " turnos");
        System.out.println("Efeito no ambiente: " + efeitoNoAmbiente);
        System.out.println("Impacto geral: " + getImpacto());
    }

    public int getDuracao() {
        return duracao;
    }

    public String getEfeitoNoAmbiente() {
        return efeitoNoAmbiente;
    }

    public String getTipo() {
        return tipo;
    }

}