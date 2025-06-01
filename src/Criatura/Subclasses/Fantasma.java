package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Fantasma extends Criatura {

    // Construtor da classe Fantasma
    public Fantasma() {
        super("Fantasma", "Médio", "Assombrar, atravessar ou desaparecer");
    }

    public void atacar(Personagem alvo) {
        alvo.diminuirSanidade(12);
        System.out.println("Um grito fantasmagórico ecoa... uma presença invisível invade sua mente.");
        System.out.println("Você perdeu 12 de sanidade.");
    }
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirSanidade(5);
        System.out.println("Um grito fantasmagórico ecoa... uma presença invisível invade sua mente.");
    }

    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Fantasma atravessa seu corpo, drenando sua energia vital.");
        jogador.diminuirVida(10);
        System.out.println("Você perdeu 10 de vida.");
    }

    public void fugir() {
        System.out.println("O fantasma desaparece lentamente no ar, deixando um frio no ambiente.\n");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUma presença gélida invade seus sonhos...");
        System.out.println("Um Fantasma sussurra palavras sombrias e consome parte da sua sanidade!");
        jogador.diminuirSanidade(12);
        System.out.println("Você perdeu 12 de vida");
    }

}

