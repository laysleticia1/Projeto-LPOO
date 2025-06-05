package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class Piranha extends Criatura {

    public Piranha() {
        super("Piranha Voraz", "Moderado", "Mordida Rápida, Enxame Repentino");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(13);
        System.out.println("Uma Piranha salta da água e morde seu braço violentamente!");
        System.out.println("Você perdeu 13 de vida.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirVida(8); // Ação especial também causa dano de vida
        System.out.println("Um enxame de piranhas o cerca, mordendo com fúria por todos os lados!");
        System.out.println("Você perdeu 8 de vida.");
    }

    @Override
    public void fugir() {
        System.out.println("As piranhas mergulham de volta para as profundezas do lago.");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nVocê sonha com água... e acorda em dor!");
        System.out.println("Piranhas o cercaram enquanto dormia nas margens do rio.");
        jogador.diminuirVida(12);
        System.out.println("Você perdeu 12 de vida");
    }

    //Interfaqe
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(13);
        areaLog.append("Uma Piranha salta da água e morde seu braço violentamente!\n");
        areaLog.append("Você perdeu 13 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(8);
        jogador.diminuirSanidade(4);
        areaLog.append("Um enxame de piranhas o cerca, mordendo com fúria por todos os lados!\n");
        areaLog.append("Você perdeu 8 de vida e 4 de sanidade.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("As Piranhas recuam momentaneamente, desaparecendo sob as águas turvas.\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(12);
        areaLog.append("Você sonha com água... e acorda em dor!\n");
        areaLog.append("Piranhas o cercaram enquanto dormia nas margens do rio.\n");
        areaLog.append("Você perdeu 12 de vida.\n");
    }


}