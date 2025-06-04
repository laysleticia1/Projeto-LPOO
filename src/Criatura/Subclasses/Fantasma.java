package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

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
    public String atacarParaUI(Personagem alvo) {
        atacar(alvo);
        return "Um grito fantasmagórico ecoa... uma presença invisível invade sua mente.\nVocê perdeu 12 de sanidade.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirSanidade(5);
        System.out.println("Um grito fantasmagórico ecoa... uma presença invisível invade sua mente.");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "Um sussurro gélido arrepia sua espinha.\nVocê perdeu 5 de sanidade."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Fantasma atravessa seu corpo, drenando sua energia vital.");
        jogador.diminuirVida(10);
        System.out.println("Você perdeu 10 de vida.");
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "O Fantasma atravessa seu corpo, drenando sua energia vital.\nVocê perdeu 10 de vida.";
    }

    @Override
    public void fugir() {
        System.out.println("O fantasma desaparece lentamente no ar, deixando um frio no ambiente.\n");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "O fantasma desaparece lentamente no ar, deixando um frio no ambiente.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUma presença gélida invade seus sonhos...");
        System.out.println("Um Fantasma sussurra palavras sombrias e consome parte da sua sanidade!");
        // Originalmente seu System.out dizia "perdeu 12 de vida" mas diminuirSanidade(12) era chamado.
        // Assumindo que era para diminuir sanidade conforme a mensagem:
        jogador.diminuirSanidade(12);
        System.out.println("Você perdeu 12 de sanidade.");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Uma presença gélida invade seus sonhos...\nUm Fantasma sussurra palavras sombrias e consome parte da sua sanidade!\nVocê perdeu 12 de sanidade.";
    }
}

