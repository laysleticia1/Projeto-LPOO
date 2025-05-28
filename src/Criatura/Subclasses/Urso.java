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
        System.out.println("O urso se levanta e desfere um ataque violento!");
        System.out.println("Você perdeu 15 de vida.\n");
    }

    public void acaoEspecial(Personagem jogador) {
        System.out.println("O urso solta um rugido que estremece a floresta, fazendo você congelar de medo.");
        System.out.println("Você perdeu 10 sanidade.\n");
        jogador.diminuirSanidade(10);
    }

    public void fugir() {
        System.out.println("Ferido, o urso recua entre os arbustos e desaparece na floresta\n");
    }

}
