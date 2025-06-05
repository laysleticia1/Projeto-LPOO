package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

public class Sanguessuga extends Criatura {

    public Sanguessuga() {
        super("Sanguessuga Gigante", "Baixo", "Sucção, Infecção");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(6);
        System.out.println("A Sanguessuga se prende à sua pele e começa a sugar seu sangue!");
        System.out.println("Você perdeu 6 de vida.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirVida(4); // Ação especial causa dano de vida (infecção)
        System.out.println("A ferida causada pela Sanguessuga infecciona, causando dor intensa.");
        System.out.println("Você perdeu 4 de vida.");
    }

    @Override
    public void fugir() {
        System.out.println("Satisfeita, a Sanguessuga se solta e desliza para a água escura.\n");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nVocê acorda exausto... algo está sugando sua força.");
        System.out.println("Uma Sanguessuga está presa ao seu corpo!");
        jogador.diminuirEnergia(10);
        jogador.diminuirVida(5);
        System.out.println("Você perdeu 5 de vida e 10 de energia.");
    }

    //Interface
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(6);
        areaLog.append("A Sanguessuga se prende à sua pele e começa a sugar seu sangue!\n");
        areaLog.append("Você perdeu 6 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(10);
        areaLog.append("A Sanguessuga se contorce violentamente e injeta uma substância viscosa em sua pele!\n");
        areaLog.append("Uma queimação intensa se espalha, indicando o início de uma infecção.\n");
        areaLog.append("Você perdeu 10 de vida.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("A Sanguessuga se desprende com um movimento repentino e desliza para as águas escuras, desaparecendo sem deixar vestígios.\\n\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirEnergia(10);
        jogador.diminuirVida(5);
        areaLog.append("Você acorda exausto... algo está sugando sua força.\n");
        areaLog.append("Uma Sanguessuga está presa ao seu corpo!\n");
        areaLog.append("Você perdeu 5 de vida e 10 de energia.\n");
    }

}
