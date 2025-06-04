package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Javali extends Criatura {

    public Javali() {
        super("Javali Selvagem", "Moderado", "Investida, Grunhido Intimidador");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(14);
        System.out.println("O Javali investe com força, atingindo seu tronco com suas presas afiadas!");
        System.out.println("Você perdeu 14 de vida.");
    }

    @Override
    public String atacarParaUI(Personagem jogador) {
        atacar(jogador);
        return "O Javali investe com força, atingindo seu tronco com suas presas afiadas!\nVocê perdeu 14 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(6);
        System.out.println("O Javali investe com força, atingindo seu tronco com suas presas afiadas!");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "O Javali tenta uma investida menos potente!\nVocê perdeu 6 de vida."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(6);
        System.out.println("O Javali solta um grunhido feroz, fazendo seu coração acelerar.");
        System.out.println("Você perdeu 6 de sanidade.");
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "O Javali solta um grunhido feroz, fazendo seu coração acelerar.\nVocê perdeu 6 de sanidade.";
    }

    @Override
    public void fugir() {
        System.out.println("Ferido, o Javali corre pela mata e desaparece entre os arbustos.");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "Ferido, o Javali corre pela mata e desaparece entre os arbustos.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nVocê ouve um barulho surdo durante o sono...");
        System.out.println("Um Javali investe com força total contra você enquanto dorme!");
        jogador.diminuirVida(18);
        System.out.println("Você perdeu 18 de vida");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Você ouve um barulho surdo durante o sono...\nUm Javali investe com força total contra você enquanto dorme!\nVocê perdeu 18 de vida.";
    }
}