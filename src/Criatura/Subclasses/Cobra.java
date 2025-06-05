package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class Cobra extends Criatura {

    public Cobra() {
        super("Cobra Venenosa", "Moderado", "Mordida venenosa, Silvo ameaçador");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(10);
        System.out.println("A Cobra dá o bote com precisão, cravando suas presas venenosas em sua perna!");
        System.out.println("Você perdeu 10 de vida.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(7);
        System.out.println("A Cobra silva intensamente, fazendo seu corpo congelar de medo e desespero.");
        System.out.println("Você perdeu 7 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("A Cobra desliza entre as pedras e some em meio à vegetação rasteira.\n");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nEm meio ao silêncio do descanso, algo desliza por seu braço...");
        System.out.println("Uma Cobra o pica repentinamente!");
        jogador.diminuirVida(10);
        System.out.println("Você perdeu 10 de vida");
    }

    //Interface
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(8);
        areaLog.append("A Cobra ataca rapidamente e morde sua perna!\n");
        areaLog.append("Você perdeu 8 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(6);
        jogador.diminuirSanidade(3);
        areaLog.append("A Cobra sibila com força e libera um jato de veneno nos seus olhos!\n");
        areaLog.append("Você perdeu 6 de vida e 3 de sanidade.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("A Cobra se enrola rapidamente e desaparece entre os arbustos.\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(10);
        areaLog.append("Você sente algo gelado em sua pele durante o sono...\n");
        areaLog.append("Uma Cobra se arrastou até você e mordeu seu braço!\n");
        areaLog.append("Você perdeu 10 de vida.\n");
    }
}