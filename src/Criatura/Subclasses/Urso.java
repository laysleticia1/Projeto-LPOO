package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Urso extends Criatura {

    // Construtor da classe Lobo
    public Urso() {
        super ("Urso Montanhês", "Alto", "Investida brutal, Roar paralisante");
    }

    public void atacar(Personagem alvo) {
        alvo.diminuirVida (15);
        System.out.println("Um urso imenso ruge e desfere uma patada devastadora contra seu peito!");
        System.out.println("Você perdeu 15 de vida.");
    }
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(7);
        System.out.println("Um urso imenso ruge e desfere uma patada devastadora contra seu peito!");
    }
    public void acaoEspecial(Personagem jogador) {
        System.out.println("O urso solta um rugido que estremece a floresta, fazendo você congelar de medo.");
        System.out.println("Você perdeu 10 sanidade.");
        jogador.diminuirSanidade(10);
    }

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

}
