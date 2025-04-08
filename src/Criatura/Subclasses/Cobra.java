package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Cobra extends Criatura {

    // Construtor da classe Cobra
    public Cobra() {
        super ("Cobra", "Alto", "Atacar ou fugir");
    }

    public void atacar (Personagem alvo) {
        System.out.println("A cobra crava as presas na pele e injeta seu veneno letal. Uma onda de tontura e fraqueza se espalha como fogo lento pelo corpo do personagem");

        alvo.setVida(alvo.getVida() - 20);
        alvo.setEnergia(alvo.getEnergia() - 10);
        alvo.setSanidade(alvo.getSanidade() - 10);
    }

    public void esconder() {
        System.out.println("Sem deixar rastros, a cobra se esgueira pela relva e desaparece nas entranhas da floresta.");
    }
}
