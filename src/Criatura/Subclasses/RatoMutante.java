package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class RatoMutante extends Criatura {

    public RatoMutante() {
        super("Rato Mutante", "Moderado", "Mordida, Contágio Tóxico");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(9);
        System.out.println("O Rato Mutante morde seu tornozelo com dentes anormalmente grandes!");
        System.out.println("Você perdeu 9 de vida.\n");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(7);
        System.out.println("O cheiro fétido do Rato Mutante o faz alucinar.");
        System.out.println("Você perdeu 7 de sanidade.\n");
    }

    @Override
    public void fugir() {
        System.out.println("O Rato Mutante escapa por rachaduras e buracos nas ruínas.\n");
    }
}