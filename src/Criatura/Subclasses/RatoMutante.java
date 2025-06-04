package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class RatoMutante extends Criatura {

    public RatoMutante() {
        super("Rato Mutante", "Moderado", "Mordida, Contágio Tóxico");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(9);
        System.out.println("O Rato Mutante morde seu tornozelo com dentes anormalmente grandes!");
        System.out.println("Você perdeu 9 de vida.");
    }

    @Override
    public String atacarParaUI(Personagem jogador) {
        atacar(jogador);
        return "O Rato Mutante morde seu tornozelo com dentes anormalmente grandes!\nVocê perdeu 9 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(5);
        System.out.println("O Rato Mutante morde seu tornozelo com dentes anormalmente grandes!");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "O Rato Mutante tenta uma mordida menos potente!\nVocê perdeu 5 de vida."; // Ajuste a mensagem se a original não indicar dano
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(7);
        System.out.println("O cheiro fétido do Rato Mutante o faz alucinar.");
        System.out.println("Você perdeu 7 de sanidade.");
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "O cheiro fétido do Rato Mutante o faz alucinar.\nVocê perdeu 7 de sanidade.";
    }

    @Override
    public void fugir() {
        System.out.println("O Rato Mutante escapa por rachaduras e buracos nas ruínas.");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "O Rato Mutante escapa por rachaduras e buracos nas ruínas.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUm rangido estranho ecoa pelo chão...");
        System.out.println("Um Rato Mutante roeu sua mochila e mordeu seu braço!");
        jogador.diminuirVida(10);
        System.out.println("Você perdeu 10 de vida");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Um rangido estranho ecoa pelo chão...\nUm Rato Mutante roeu sua mochila e mordeu seu braço!\nVocê perdeu 10 de vida.";
    }
}