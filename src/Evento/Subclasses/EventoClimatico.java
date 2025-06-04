package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

import javax.swing.*;

public class EventoClimatico extends Evento {
    private String tipo;
    private int duracao; // em turnos
    private String efeitoNoAmbiente;

    public EventoClimatico(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, String tipo, int duracao, String efeitoNoAmbiente) {
        super (nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
        this.tipo = tipo;
        this.duracao = duracao;
        this.efeitoNoAmbiente = efeitoNoAmbiente;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento Climático: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Efeito no ambiente: " + efeitoNoAmbiente);
        System.out.println("Duração: " + duracao + " turno/s.");
        switch (getImpacto().trim().toLowerCase()) {
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
            default:
                System.out.println("O clima afeta o ambiente: " + efeitoNoAmbiente);
                break;
        }
    }

    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        // Geralmente, eventos climáticos podem ocorrer em muitos lugares,
        // mas subclasses específicas (como TempestadeMontanha) restringem isso.
        return true;
    }

    public void diminuirDuracao() {
        if (this.duracao > 0) {
            this.duracao--;
        }
    }

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

    //Interface
    public void executarInterface(Personagem jogador, Ambiente local, JTextArea areaLog) {
        areaLog.append("☀️ Evento Climático: " + getNomeEvento() + "\n");
        areaLog.append(getDescricao() + "\n");
        areaLog.append("Efeito no ambiente: " + efeitoNoAmbiente + "\n");
        areaLog.append("Duração: " + duracao + " turno/s.\n");

        switch (getImpacto().trim().toLowerCase()) {
            case "vida":
                jogador.diminuirVida(15);
                areaLog.append("Você perdeu 15 de vida!\n");
                break;
            case "sanidade":
                jogador.diminuirSanidade(15);
                areaLog.append("Você perdeu 15 de sanidade!\n");
                break;
            case "energia":
                jogador.diminuirEnergia(15);
                areaLog.append("Você perdeu 15 de energia!\n");
                break;
            default:
                areaLog.append("O clima afeta o ambiente: " + efeitoNoAmbiente + "\n");
                break;
        }
    }
}