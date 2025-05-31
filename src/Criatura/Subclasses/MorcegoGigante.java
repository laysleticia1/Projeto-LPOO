package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class MorcegoGigante extends Criatura {

    public MorcegoGigante() {
        super("Morcego Gigante", "Alto", "Voo Rasante, Grito Ultrasônico");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(12);
        System.out.println("O Morcego Gigante mergulha do teto da caverna e arranha com suas garras afiadas!");
        System.out.println("Você perdeu 16 de vida.");
    }
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(6);
        System.out.println("O Morcego Gigante mergulha do teto da caverna e arranha com suas garras afiadas!");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(9);
        System.out.println("O Morcego solta um grito ultrasônico que reverbera dentro da sua cabeça.");
        System.out.println("Você perdeu 9 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("O Morcego Gigante voa para longe, escondendo-se nas sombras da caverna.");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nAlgo se agita no teto... você nem consegue levantar!");
        System.out.println("Um Morcego Gigante mergulha em você enquanto dorme.");
        jogador.diminuirVida(14);
        System.out.println("Você perdeu 14 de vida");
    }

}
