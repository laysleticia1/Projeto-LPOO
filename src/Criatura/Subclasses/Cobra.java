package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Cobra extends Criatura {

    public Cobra() {
        super("Cobra Venenosa", "Moderado", "Mordida venenosa, Silvo ameaçador");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(10);
        System.out.println("A Cobra dá o bote com precisão, cravando suas presas venenosas em sua perna!");
        System.out.println("Você perdeu 10 de vida.");
    }

    @Override
    public String atacarParaUI(Personagem jogador) {
        atacar(jogador);
        return "A Cobra dá o bote com precisão, cravando suas presas venenosas em sua perna!\nVocê perdeu 10 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(5);
        System.out.println("A Cobra dá o bote com precisão, cravando suas presas venenosas em sua perna!");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "A Cobra tenta um bote rápido, mas menos potente!\nVocê perdeu 5 de vida."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(7);
        System.out.println("A Cobra silva intensamente, fazendo seu corpo congelar de medo e desespero.");
        System.out.println("Você perdeu 7 de sanidade.");
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "A Cobra silva intensamente, fazendo seu corpo congelar de medo e desespero.\nVocê perdeu 7 de sanidade.";
    }

    @Override
    public void fugir() {
        System.out.println("A Cobra desliza entre as pedras e some em meio à vegetação rasteira.\n");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "A Cobra desliza entre as pedras e some em meio à vegetação rasteira.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nEm meio ao silêncio do descanso, algo desliza por seu braço...");
        System.out.println("Uma Cobra o pica repentinamente!");
        jogador.diminuirVida(10);
        System.out.println("Você perdeu 10 de vida");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Em meio ao silêncio do descanso, algo desliza por seu braço...\nUma Cobra o pica repentinamente!\nVocê perdeu 10 de vida.";
    }
}