package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Urso extends Criatura {

    public Urso() {
        super("Urso Montanhês Feroz", "Alto", "Investida brutal, Rugido paralisante");
    }

    @Override
    public void atacar(Personagem alvo) {
        alvo.diminuirVida(15);
        System.out.println("Um urso imenso ruge e desfere uma patada devastadora contra seu peito!");
        System.out.println("Você perdeu 15 de vida.");
    }

    @Override
    public String atacarParaUI(Personagem alvo) {
        atacar(alvo);
        return "Um urso imenso ruge e desfere uma patada devastadora contra seu peito!\nVocê perdeu 15 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(7);
        System.out.println("Um urso imenso ruge e desfere uma patada devastadora contra seu peito!");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "O urso tenta uma patada menos brutal!\nVocê perdeu 7 de vida."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        System.out.println("O urso solta um rugido que estremece a floresta, fazendo você congelar de medo.");
        System.out.println("Você perdeu 10 de sanidade.");
        jogador.diminuirSanidade(10);
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "O urso solta um rugido que estremece a floresta, fazendo você congelar de medo.\nVocê perdeu 10 de sanidade.";
    }

    @Override
    public void fugir() {
        System.out.println("Ferido, o urso recua entre os arbustos e desaparece na floresta");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "Ferido, o urso recua entre os arbustos e desaparece na floresta.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nO chão treme... você mal consegue abrir os olhos...");
        System.out.println("Um Urso colossal golpeia sua direção com brutalidade!");
        jogador.diminuirVida(25);
        System.out.println("Você perdeu 25 de vida");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "O chão treme... você mal consegue abrir os olhos...\nUm Urso colossal golpeia sua direção com brutalidade!\nVocê perdeu 25 de vida.";
    }
}