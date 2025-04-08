package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Urso extends Criatura {

    // Construtor da classe Lobo
    public Urso() {
        super ("Urso", "Médio", "Atacar, rugir ou fugir");
    }

    public void atacar(Personagem alvo) {
        alvo.receberDano (15);
        System.out.println("O urso se levanta e desfere um ataque poderoso!");
    }

    public void rugir() {
        System.out.println("O urso solta um rugido que estremece a floresta, um aviso de fúria iminente.");
    }

    public void fugir() {
        System.out.println("Ferido, o urso recua entre os arbustos e desaparece na floresta");
    }

}
