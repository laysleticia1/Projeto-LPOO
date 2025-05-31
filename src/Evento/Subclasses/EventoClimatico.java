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
        this.tipo = tipo;
        this.duracao = duracao;
        this.efeitoNoAmbiente = efeitoNoAmbiente;
    }

    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento Climático: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Efeito no ambiente: " + efeitoNoAmbiente);
        System.out.println("Duração: " + duracao + " turno/s.");
        switch (getImpacto().toLowerCase()) {
            case "vida":
                jogador.diminuirVida(15);
                System.out.println("Você perdeu 15 de vida!");
                break;
            case "sanidade":
                jogador.diminuirSanidade(15);
                System.out.println("Você perdeu 15 de sanidade!");
                break;
            case "energia":
                jogador.diminuirEnergia(15);
                System.out.println("Você perdeu 15 de energia!");
                break;
        }
    }

    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return true;
    }

    public void diminuirDuracao() { duracao--;}

    //Getters and Setters
    public int getDuracao() {
        return duracao;
    }

    public String getEfeitoNoAmbiente() {
        return efeitoNoAmbiente;
    }

    public void setEfeitoNoAmbiente(String efeitoNoAmbiente) {
        this.efeitoNoAmbiente = efeitoNoAmbiente;
    }


    public String getTipo() {
        return tipo;
    }

}