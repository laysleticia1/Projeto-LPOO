package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Lobo extends Criatura {

    // Construtor da classe Lobo
    public Lobo() {
        super ("Lobo", "Médio", "Atacar, fugir ou uivar");
    }

    public void ataqueLobo(Personagem alvo) {
        alvo.receberDano(15);
        System.out.println("O lobo avança rapidamente e desfere um golpe, cravando os dentes com fúria!");
    }

    public void fugir() {
        System.out.println("O lobo rosna em retirada e some entre as sombras densas da floresta.");
    }

    public void uivar() {
        System.out.println("Auuuu! O lobo ergue o focinho à lua, convocando sua alcateia com um uivo que ecoa na noite.");
    }
}
