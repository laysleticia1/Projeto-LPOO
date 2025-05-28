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
        System.out.println("Você perdeu 13 de vida.\n");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirVida(8);
        System.out.println("Um enxame de piranhas o cerca, mordendo com fúria por todos os lados!");
        System.out.println("Você perdeu 8 de vida.\n");
    }

    @Override
    public void fugir() {
        System.out.println("As piranhas mergulham de volta para as profundezas do lago.\n");
    }
}
