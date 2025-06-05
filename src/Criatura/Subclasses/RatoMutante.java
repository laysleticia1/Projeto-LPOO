package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class RatoMutante extends Criatura {

    public RatoMutante() {
        super("Rato Mutante", "Moderado", "Mordida, Contágio Tóxico");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(9);
        System.out.println("O Rato Mutante morde seu tornozelo com dentes anormalmente grandes!");
        System.out.println("Você perdeu 9 de vida.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(7);
        System.out.println("O cheiro fétido do Rato Mutante o faz alucinar.");
        System.out.println("Você perdeu 7 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("O Rato Mutante escapa por rachaduras e buracos nas ruínas.");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUm rangido estranho ecoa pelo chão...");
        System.out.println("Um Rato Mutante roeu sua mochila e mordeu seu braço!");
        jogador.diminuirVida(10);
        System.out.println("Você perdeu 10 de vida");
    }

    //Interface
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(6);
        areaLog.append("O Rato Mutante morde seu tornozelo com dentes anormalmente grandes!\n");
        areaLog.append("Você perdeu 6 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirSanidade(7);
        areaLog.append("O cheiro fétido do Rato Mutante o faz alucinar.\n");
        areaLog.append("Você perdeu 7 de sanidade.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("O Rato Mutante dispara pelos escombros, sumindo em segundos.\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(5);
        areaLog.append("Durante o sono, um Rato Mutante roeu sua mochila... e sua pele.\n");
        areaLog.append("Você perdeu 5 de vida.\n");
    }

}