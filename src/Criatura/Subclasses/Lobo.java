package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Lobo extends Criatura {

    public Lobo() {
        super("Lobo Cinzento", "Médio", "Atacar, Fugir ou Uivar");
    }

    @Override
    public void atacar(Personagem alvo) {
        alvo.diminuirVida(15);
        System.out.println("O lobo avança rapidamente e desfere um golpe, cravando os dentes com fúria!");
        System.out.println("Você perdeu 15 de vida.");
    }

    @Override
    public String atacarParaUI(Personagem alvo) {
        atacar(alvo);
        return "O lobo avança rapidamente e desfere um golpe, cravando os dentes com fúria!\nVocê perdeu 15 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(8);
        System.out.println("O lobo avança rapidamente e desfere um golpe, cravando os dentes com fúria!");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "O lobo tenta um ataque mais contido!\nVocê perdeu 8 de vida."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Lobo uivou alto, desestabilizando sua sanidade.");
        System.out.println("Você perdeu 8 de sanidade.");
        jogador.diminuirSanidade(8);
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "O Lobo uivou alto, desestabilizando sua sanidade.\nVocê perdeu 8 de sanidade.";
    }

    @Override
    public void fugir() {
        System.out.println("O lobo rosna em retirada e some entre as sombras densas da floresta.");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "O lobo rosna em retirada e some entre as sombras densas da floresta.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nDurante seu descanso, um Lobo se aproxima silenciosamente...");
        System.out.println("Antes que você possa reagir, ele crava os dentes com ferocidade!");
        jogador.diminuirVida(15);
        System.out.println("Você perdeu 15 de vida");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Durante seu descanso, um Lobo se aproxima silenciosamente...\nAntes que você possa reagir, ele crava os dentes com ferocidade!\nVocê perdeu 15 de vida.";
    }
}