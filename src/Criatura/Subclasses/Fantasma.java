package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class Fantasma extends Criatura {

    public Fantasma() {
        super("Fantasma Lamentador", "Médio", "Assombrar, Toque Gélido");
    }

    @Override
    public void atacar(Personagem alvo) {
        alvo.diminuirSanidade(12);
        System.out.println("Um grito fantasmagórico ecoa... uma presença invisível invade sua mente.");
        System.out.println("Você perdeu 12 de sanidade.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Fantasma atravessa seu corpo, drenando sua energia vital.");
        jogador.diminuirVida(10);
        System.out.println("Você perdeu 10 de vida.");
    }

    @Override
    public void fugir() {
        System.out.println("O fantasma desaparece lentamente no ar, deixando um frio no ambiente.\n");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUma presença gélida invade seus sonhos...");
        System.out.println("Um Fantasma sussurra palavras sombrias e consome parte da sua sanidade!");
        jogador.diminuirSanidade(12);
        System.out.println("Você perdeu 12 de sanidade.");
    }

   //Interface
   @Override
   public void atacarInterface(Personagem jogador, JTextArea areaLog) {
       jogador.diminuirSanidade(15);
       areaLog.append("O Fantasma atravessa seu corpo com um sussurro gelado!\n");
       areaLog.append("Você sente sua mente se fragmentar.\n");
       areaLog.append("Você perdeu 15 de sanidade.\n");
   }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirSanidade(12);
        areaLog.append("O Fantasma sussurra segredos esquecidos direto em sua mente.\n");
        areaLog.append("Imagens do passado e dor tomam conta da sua visão...\n");
        areaLog.append("Você perdeu 12 de sanidade.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("O Fantasma flutua para trás e desaparece lentamente na névoa...\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirSanidade(8);
        jogador.diminuirVida(10);
        areaLog.append("Durante seu descanso, um arrepio percorre sua espinha...\n");
        areaLog.append("O Fantasma atravessa seu corpo, drenando sua energia vital.\n");
        areaLog.append("Você perdeu 8 de sanidade e 10 de vida.\n");
    }

}

