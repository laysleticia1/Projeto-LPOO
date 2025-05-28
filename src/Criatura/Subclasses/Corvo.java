package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Corvo extends Criatura {

    public Corvo() {
        super("Corvo", "Baixo", "Bicar, Grito perturbador");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(5);
        System.out.println("O Corvo mergulha em sua direção e começa a bicar sua cabeça com agilidade e raiva!");
        System.out.println("Você perdeu 5 de vida.\n");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(6);
        System.out.println("O Corvo emite um grito estridente e perturbador que ecoa por sua mente.");
        System.out.println("Você perdeu 6 de sanidade.\n");
    }

    @Override
    public void fugir() {
        System.out.println("Com um bater de asas veloz, o Corvo desaparece entre as copas das árvores.\n");
    }
}

