package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class Javali extends Criatura {

    public Javali() {
        super("Javali Selvagem", "Moderado", "Investida, Grunhido Intimidador");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(14);
        System.out.println("O Javali investe com força, atingindo seu tronco com suas presas afiadas!");
        System.out.println("Você perdeu 14 de vida.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(6);
        System.out.println("O Javali solta um grunhido feroz, fazendo seu coração acelerar.");
        System.out.println("Você perdeu 6 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("Ferido, o Javali corre pela mata e desaparece entre os arbustos.");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nVocê ouve um barulho surdo durante o sono...");
        System.out.println("Um Javali investe com força total contra você enquanto dorme!");
        jogador.diminuirVida(18);
        System.out.println("Você perdeu 18 de vida");
    }

    //Interface
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(11);
        areaLog.append("Um Javali enfurecido investe contra você e acerta em cheio suas costelas!\n");
        areaLog.append("Você sente a dor do impacto e tenta se manter em pé.\n");
        areaLog.append("Você perdeu 11 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirEnergia(10);
        areaLog.append("O Javali começa a cavar o chão e resfolega com força...\n");
        areaLog.append("Subitamente, ele lança barro e folhas para todos os lados, cegando sua visão temporariamente.\n");
        areaLog.append("Você tenta se defender, mas se cansa com a confusão.\n");
        areaLog.append("Você perdeu 10 de energia.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("O Javali bufa alto e sai correndo floresta adentro...\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(13);
        areaLog.append("Durante seu descanso, um Javali selvagem te encontra e te acerta com a cabeça!\n");
        areaLog.append("Você rola pelo chão atordoado.\n");
        areaLog.append("Você perdeu 13 de vida.\n");
    }

}