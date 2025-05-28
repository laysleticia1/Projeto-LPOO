package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class MorcegoGigante extends Criatura {

    public MorcegoGigante() {
        super("Morcego Gigante", "Alto", "Voo Rasante, Grito Ultrasônico");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(16);
        System.out.println("O Morcego Gigante mergulha do teto da caverna e arranha com suas garras afiadas!");
        System.out.println("Você perdeu 16 de vida.\n");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(9);
        System.out.println("O Morcego solta um grito ultrasônico que reverbera dentro da sua cabeça.");
        System.out.println("Você perdeu 9 de sanidade.\n");
    }

    @Override
    public void fugir() {
        System.out.println("O Morcego Gigante voa para longe, escondendo-se nas sombras da caverna.\n");
    }
}
