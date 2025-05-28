package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Sanguessuga extends Criatura {

    public Sanguessuga() {
        super("Sanguessuga", "Baixo", "Sucção, Infecção");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(6);
        System.out.println("A Sanguessuga se prende à sua pele e começa a sugar seu sangue!");
        System.out.println("Você perdeu 6 de vida.\n");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirVida(4);
        System.out.println("A ferida causada pela Sanguessuga infecciona, causando dor intensa.");
        System.out.println("Você perdeu 4 de vida.\n");
    }

    @Override
    public void fugir() {
        System.out.println("Satisfeita, a Sanguessuga se solta e desliza para a água escura.\n");
    }
}

