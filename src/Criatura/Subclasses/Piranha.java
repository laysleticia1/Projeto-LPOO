package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Piranha extends Criatura {

    public Piranha() {
        super("Piranha Voraz", "Moderado", "Mordida Rápida, Enxame Repentino");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(13);
        System.out.println("Uma Piranha salta da água e morde seu braço violentamente!");
        System.out.println("Você perdeu 13 de vida.");
    }

    @Override
    public String atacarParaUI(Personagem jogador) {
        atacar(jogador);
        return "Uma Piranha salta da água e morde seu braço violentamente!\nVocê perdeu 13 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(6);
        System.out.println("Uma Piranha salta da água e morde seu braço violentamente!");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "Uma Piranha tenta uma mordida rápida!\nVocê perdeu 6 de vida."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirVida(8); // Ação especial também causa dano de vida
        System.out.println("Um enxame de piranhas o cerca, mordendo com fúria por todos os lados!");
        System.out.println("Você perdeu 8 de vida.");
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "Um enxame de piranhas o cerca, mordendo com fúria por todos os lados!\nVocê perdeu 8 de vida.";
    }

    @Override
    public void fugir() {
        System.out.println("As piranhas mergulham de volta para as profundezas do lago.");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "As piranhas mergulham de volta para as profundezas do lago.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nVocê sonha com água... e acorda em dor!");
        System.out.println("Piranhas o cercaram enquanto dormia nas margens do rio.");
        jogador.diminuirVida(12);
        System.out.println("Você perdeu 12 de vida");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Você sonha com água... e acorda em dor!\nPiranhas o cercaram enquanto dormia nas margens do rio.\nVocê perdeu 12 de vida.";
    }
}