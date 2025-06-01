package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Piranha extends Criatura {

    public Piranha() {
        super("Piranha", "Moderado", "Mordida Rápida, Enxame Repentino");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(13);
        System.out.println("Uma Piranha salta da água e morde seu braço violentamente!");
        System.out.println("Você perdeu 13 de vida.");
    }
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(6);
        System.out.println("Uma Piranha salta da água e morde seu braço violentamente!");
    }
    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirVida(8);
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

}

