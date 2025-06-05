package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class Urso extends Criatura {

    public Urso() {
        super("Urso Montanhês Feroz", "Alto", "Investida brutal, Rugido paralisante");
    }

    @Override
    public void atacar(Personagem alvo) {
        alvo.diminuirVida(15);
        System.out.println("Um urso imenso ruge e desfere uma patada devastadora contra seu peito!");
        System.out.println("Você perdeu 15 de vida.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        System.out.println("O urso solta um rugido que estremece a floresta, fazendo você congelar de medo.");
        System.out.println("Você perdeu 10 de sanidade.");
        jogador.diminuirSanidade(10);
    }

    @Override
    public void fugir() {
        System.out.println("Ferido, o urso recua entre os arbustos e desaparece na floresta");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nO chão treme... você mal consegue abrir os olhos...");
        System.out.println("Um Urso colossal golpeia sua direção com brutalidade!");
        jogador.diminuirVida(25);
        System.out.println("Você perdeu 25 de vida");
    }

    //Interface
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(15);
        areaLog.append("O Urso investe com brutalidade, suas garras rasgam seu braço!\n");
        areaLog.append("Você perdeu 15 de vida.\n");
    }

    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirSanidade(10);
        jogador.diminuirEnergia(5);
        areaLog.append("O Urso se ergue nas patas traseiras, rugindo com fúria primal!\n");
        areaLog.append("Você sente um medo paralisante e se afasta em pânico.\n");
        areaLog.append("Você perdeu 10 de sanidade e 5 de energia.\n");
    }

    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("O Urso rosna e se afasta lentamente, mas ainda vigia você de longe.\n");
    }

    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        areaLog.append("\nVocê ouve um estalo no meio do sono...\n");
        areaLog.append("Antes que possa reagir, um Urso te ataca entre as sombras!\n");
        jogador.diminuirVida(14);
        areaLog.append("Você perdeu 14 de vida.\n");
    }
}