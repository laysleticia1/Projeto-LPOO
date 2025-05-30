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
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(5);
        System.out.println("O Rato Mutante morde seu tornozelo com dentes anormalmente grandes!");
    }
    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(7);
        System.out.println("O cheiro fétido do Rato Mutante o faz alucinar.");
        System.out.println("Você perdeu 7 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("O Rato Mutante escapa por rachaduras e buracos nas ruínas.");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUm rangido estranho ecoa pelo chão...");
        System.out.println("Um Rato Mutante roeu sua mochila e mordeu seu braço!");
        jogador.diminuirVida(10);
        System.out.println("Você perdeu 10 de vida");
    }

}