package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class MorcegoGigante extends Criatura {

    public MorcegoGigante() {
        super("Morcego Gigante da Caverna", "Alto", "Voo Rasante, Grito Ultrasônico");
    }

    @Override
    public void atacar(Personagem jogador) {

        jogador.diminuirVida(12);
        System.out.println("O Morcego Gigante mergulha do teto da caverna e arranha com suas garras afiadas!");
        System.out.println("Você perdeu 12 de vida."); // Ajustado para consistência
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(9);
        System.out.println("O Morcego solta um grito ultrasônico que reverbera dentro da sua cabeça.");
        System.out.println("Você perdeu 9 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("O Morcego Gigante voa para longe, escondendo-se nas sombras da caverna.");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nAlgo se agita no teto... você nem consegue levantar!");
        System.out.println("Um Morcego Gigante mergulha em você enquanto dorme.");
        jogador.diminuirVida(14);
        System.out.println("Você perdeu 14 de vida");
    }

    //Interface
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(9);
        areaLog.append("O Morcego Gigante mergulha das sombras e arranha seu rosto com as garras afiadas!\n");
        areaLog.append("Você perdeu 9 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirEnergia(10);
        areaLog.append("O Morcego emite um chiado estridente que desorienta seus sentidos!\n");
        areaLog.append("Você se sente fraco e confuso...\n");
        areaLog.append("Você perdeu 10 de energia.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("Com um bater de asas ensurdecedor, o Morcego Gigante se afasta para a escuridão.\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(8);
        areaLog.append("Você acorda assustado com o som de asas e sente algo rasgando sua pele!\n");
        areaLog.append("Um Morcego Gigante te atacou durante o sono.\n");
        areaLog.append("Você perdeu 8 de vida.\n");
    }

}