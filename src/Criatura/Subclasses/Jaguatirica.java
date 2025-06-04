package Criatura.Subclasses;

import Personagem.Superclasse.Personagem;
import Criatura.Superclasse.Criatura;

public class Jaguatirica extends Criatura {

    public Jaguatirica() {
        super("Jaguatirica Sorrateira", "Alto", "Salto Rápido, Camuflagem, Ataque Surpresa");
    }

    @Override
    public void atacar(Personagem personagem) {
        System.out.println("Com um salto veloz dos galhos, uma jaguatirica ataca seu ombro pelas costas!");
        personagem.diminuirVida(12);
        System.out.println("Você perdeu 12 de vida.");
    }

    @Override
    public String atacarParaUI(Personagem personagem) {
        atacar(personagem);
        return "Com um salto veloz dos galhos, uma jaguatirica ataca seu ombro pelas costas!\nVocê perdeu 12 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(6);
        System.out.println("Com um salto veloz dos galhos, uma jaguatirica ataca seu ombro pelas costas!");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "A jaguatirica tenta um ataque rápido, mas você desvia de parte do impacto!\nVocê perdeu 6 de vida."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem personagem) {
        System.out.println("A jaguatirica desaparece na vegetação, dificultando contra-ataques.");
        personagem.diminuirSanidade(7); // Efeito da ação especial
        // System.out.println("Você se sente observado e perde 7 de sanidade."); // Adicionar se quiser mais feedback
    }

    @Override
    public String acaoEspecialParaUI(Personagem personagem) {
        acaoEspecial(personagem);
        return "A jaguatirica desaparece na vegetação, deixando você tenso e alerta.\nVocê perdeu 7 de sanidade.";
    }

    @Override
    public void fugir() {
        System.out.println("A jaguatirica se esconde silenciosamente nos arbustos.");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "A jaguatirica se esconde silenciosamente nos arbustos.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUm som furtivo passa por você... tarde demais!");
        System.out.println("Uma Jaguatirica salta das sombras e ataca antes de desaparecer.");
        jogador.diminuirVida(17);
        System.out.println("Você perdeu 17 de vida");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Um som furtivo passa por você... tarde demais!\nUma Jaguatirica salta das sombras e ataca antes de desaparecer.\nVocê perdeu 17 de vida.";
    }
}