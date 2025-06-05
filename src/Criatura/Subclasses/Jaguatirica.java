package Criatura.Subclasses;

import Personagem.Superclasse.Personagem;
import Criatura.Superclasse.Criatura;

import javax.swing.*;

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
    public void acaoEspecial(Personagem personagem) {
        System.out.println("A jaguatirica desaparece na vegetação, dificultando contra-ataques.");
        personagem.diminuirSanidade(7); // Efeito da ação especial
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

    //Interface
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(14);
        areaLog.append("A Jaguatirica salta das sombras e crava as garras em seu ombro!\n");
        areaLog.append("Você cambaleia para trás, tentando afastá-la.\n");
        areaLog.append("Você perdeu 14 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirSanidade(10);
        jogador.diminuirEnergia(6);
        areaLog.append("A Jaguatirica emite um rugido agudo e começa a circular você...\n");
        areaLog.append("Seu instinto te faz tremer, e a pressão psicológica te desgasta.\n");
        areaLog.append("Você perdeu 10 de sanidade e 6 de energia.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("A Jaguatirica solta um último rosnado antes de sumir entre as árvores...\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(16);
        areaLog.append("Você é despertado por um peso em seu peito... A Jaguatirica está sobre você!\n");
        areaLog.append("Ela arranha seu rosto antes de fugir. Um ataque furtivo enquanto dormia.\n");
        areaLog.append("Você perdeu 16 de vida.\n");
    }

}