package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class Lobo extends Criatura {

    public Lobo() {
        super("Lobo Cinzento", "Médio", "Atacar, Fugir ou Uivar");
    }

    @Override
    public void atacar(Personagem alvo) {
        alvo.diminuirVida(15);
        System.out.println("O lobo avança rapidamente e desfere um golpe, cravando os dentes com fúria!");
        System.out.println("Você perdeu 15 de vida.");
    }


    @Override
    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Lobo uivou alto, desestabilizando sua sanidade.");
        System.out.println("Você perdeu 8 de sanidade.");
        jogador.diminuirSanidade(8);
    }

    @Override
    public void fugir() {
        System.out.println("O lobo rosna em retirada e some entre as sombras densas da floresta.");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nDurante seu descanso, um Lobo se aproxima silenciosamente...");
        System.out.println("Antes que você possa reagir, ele crava os dentes com ferocidade!");
        jogador.diminuirVida(15);
        System.out.println("Você perdeu 15 de vida");
    }

    //Interface
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(14);
        areaLog.append("O Lobo rosna e avança em sua direção, cravando os dentes em sua perna!\n");
        areaLog.append("Você perdeu 14 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirSanidade(10);
        areaLog.append("O Lobo começa a uivar para a lua...\n");
        areaLog.append("Um som que ecoa por toda a floresta, deixando você paralisado de medo.\n");
        areaLog.append("Você perdeu 10 de sanidade.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("O lobo rosna em retirada e some entre as sombras densas da floresta.\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(12);
        areaLog.append("Durante seu descanso, você é surpreendido por um Lobo faminto!\n");
        areaLog.append("Ele morde seu braço e foge correndo.\n");
        areaLog.append("Você perdeu 12 de vida.\n");
    }

}