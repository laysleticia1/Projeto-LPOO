package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Fantasma extends Criatura {

    public Fantasma() {
        super("Fantasma", "Alto", "Assombrar, Desestabilizar");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(12);
        System.out.println("O Fantasma atravessa seu corpo, causando calafrios e dor espiritual intensa!");
        System.out.println("Você perdeu 12 de vida.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(10);
        System.out.println("O Fantasma sussurra em sua mente, revelando visões aterrorizantes do passado.");
        System.out.println("Você perdeu 10 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("O Fantasma desaparece lentamente em uma névoa fria e silenciosa.");
    }
}