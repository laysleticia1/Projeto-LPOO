package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class Corvo extends Criatura {

    public Corvo() {
        super("Corvo Agourento", "Baixo", "Bicar, Grito perturbador");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(10);
        System.out.println("Um corvo negro mergulha em você com um grasnado estridente, arranhando seu rosto com as garras.");
        System.out.println("Você perdeu 10 de vida.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(6);
        System.out.println("O Corvo emite um grito estridente e perturbador que ecoa por sua mente.");
        System.out.println("Você perdeu 6 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("Com um bater de asas veloz, o Corvo desaparece entre as copas das árvores.\n");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUm grasnado sombrio ecoa no seu sonho...");
        System.out.println("Você acorda com arranhões no rosto — um Corvo o atacou enquanto dormia!");
        jogador.diminuirVida(8);
        System.out.println("Você perdeu 8 de vida.");
    }

    //Interface
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(10);
        areaLog.append("Um corvo negro mergulha em você com um grasnado estridente, arranhando seu rosto com as garras.\n");
        areaLog.append("Você perdeu 10 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirSanidade(8);
        areaLog.append("O Corvo emite um grito estridente e perturbador que ecoa por sua mente.\n");
        areaLog.append("Você perdeu 8 de sanidade.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("Com um bater de asas veloz, o Corvo alça voo desaparecendo nas nuvens.\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(8);
        areaLog.append("Um grasnado sombrio ecoa no seu sonho...\n");
        areaLog.append("Você acorda com arranhões no rosto — um Corvo o atacou enquanto dormia!\n");
        areaLog.append("Você perdeu 8 de vida.\n");
    }

}
