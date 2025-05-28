package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Lobo extends Criatura {

    // Construtor da classe Lobo
    public Lobo() {
        super ("Lobo", "Médio", "Atacar, fugir ou uivar");
    }

    public void atacar (Personagem alvo) {
        alvo.diminuirVida(15);
        System.out.println("O lobo avança rapidamente e desfere um golpe, cravando os dentes com fúria!");
        System.out.println("Você perdeu 15 de vida.\n");
    }

    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Lobo uivou alto, desestabilizando sua sanidade.");
        System.out.println("Você perdeu 8 de sanidade.\n");
        jogador.diminuirSanidade(8);
    }

    public void fugir() {
        System.out.println("O lobo rosna em retirada e some entre as sombras densas da floresta.\n");
    }

}
