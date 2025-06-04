package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class MorcegoGigante extends Criatura {

    public MorcegoGigante() {
        super("Morcego Gigante da Caverna", "Alto", "Voo Rasante, Grito Ultrasônico");
    }

    @Override
    public void atacar(Personagem jogador) {
        // Seu código original tinha diminuirVida(12) mas imprimia 16. Vou usar 12.
        jogador.diminuirVida(12);
        System.out.println("O Morcego Gigante mergulha do teto da caverna e arranha com suas garras afiadas!");
        System.out.println("Você perdeu 12 de vida."); // Ajustado para consistência
    }

    @Override
    public String atacarParaUI(Personagem jogador) {
        atacar(jogador);
        return "O Morcego Gigante mergulha do teto da caverna e arranha com suas garras afiadas!\nVocê perdeu 12 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(6);
        System.out.println("O Morcego Gigante mergulha do teto da caverna e arranha com suas garras afiadas!");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "O Morcego Gigante faz um voo rasante rápido!\nVocê perdeu 6 de vida."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(9);
        System.out.println("O Morcego solta um grito ultrasônico que reverbera dentro da sua cabeça.");
        System.out.println("Você perdeu 9 de sanidade.");
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "O Morcego solta um grito ultrasônico que reverbera dentro da sua cabeça.\nVocê perdeu 9 de sanidade.";
    }

    @Override
    public void fugir() {
        System.out.println("O Morcego Gigante voa para longe, escondendo-se nas sombras da caverna.");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "O Morcego Gigante voa para longe, escondendo-se nas sombras da caverna.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nAlgo se agita no teto... você nem consegue levantar!");
        System.out.println("Um Morcego Gigante mergulha em você enquanto dorme.");
        jogador.diminuirVida(14);
        System.out.println("Você perdeu 14 de vida");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Algo se agita no teto... você nem consegue levantar!\nUm Morcego Gigante mergulha em você enquanto dorme.\nVocê perdeu 14 de vida.";
    }
}