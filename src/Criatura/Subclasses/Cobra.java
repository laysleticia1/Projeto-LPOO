package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Cobra extends Criatura {

    public Cobra() {
        super("Cobra", "Moderado", "Mordida venenosa, Silvo ameaçador");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(10);
        System.out.println("A Cobra dá o bote com precisão, cravando suas presas venenosas em sua perna!");
        System.out.println("Você perdeu 10 de vida.");
    }
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(5);
        System.out.println("A Cobra dá o bote com precisão, cravando suas presas venenosas em sua perna!");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(7);
        System.out.println("A Cobra silva intensamente, fazendo seu corpo congelar de medo e desespero.");
        System.out.println("Você perdeu 7 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("A Cobra desliza entre as pedras e some em meio à vegetação rasteira.\n");
    }
}
