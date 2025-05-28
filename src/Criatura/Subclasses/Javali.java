package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;


public class Javali extends Criatura {

    public Javali() {
        super("Javali", "Moderado", "Investida, Grunhido Intimidador");
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
}