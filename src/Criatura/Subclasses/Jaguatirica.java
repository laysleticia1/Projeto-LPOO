package Criatura.Subclasses;

import Personagem.Superclasse.Personagem;
import Criatura.Superclasse.Criatura;

public class Jaguatirica extends Criatura {

    public Jaguatirica() {
        super("Jaguatirica", "Alto", "Salto Rápido, Camuflagem, Ataque Surpresa");
    }

    @Override
    public void atacar(Personagem personagem) {
        System.out.println("Com um salto veloz dos galhos, uma jaguatirica ataca seu ombro pelas costas!");
        personagem.diminuirVida(12);
        System.out.println("Você perdeu 12 de vida.");
    }

    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(6);
        System.out.println("Com um salto veloz dos galhos, uma jaguatirica ataca seu ombro pelas costas!");
    }

    @Override
    public void acaoEspecial(Personagem personagem) {
        System.out.println("A jaguatirica desaparece na vegetação, dificultando contra-ataques.");
        personagem.diminuirSanidade(7);
    }

    @Override
    public void fugir() {
        System.out.println("A jaguatirica se esconde silenciosamente nos arbustos.");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUm som furtivo passa por você... tarde demais!");
        System.out.println("Uma Jaguatirica salta das sombras e ataca antes de desaparecer.");
        jogador.diminuirVida(17);
        System.out.println("Você perdeu 17 de vida");
    }

}
