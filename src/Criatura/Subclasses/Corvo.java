package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Corvo extends Criatura {

    public Corvo() {
        super("Corvo", "Baixo", "Bicar, Grito perturbador");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(10);
        System.out.println("Um corvo negro mergulha em você com um grasnado estridente, arranhando seu rosto com as garras.");
        System.out.println("Você perdeu 10 de vida.");
    }

    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(5); // valor fixo reduzido
        System.out.println("Um corvo negro mergulha em você com um grasnado estridente, arranhando seu rosto com as garras.");
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(6);
        System.out.println("O Corvo emite um grito estridente e perturbador que ecoa por sua mente.");
        System.out.println("Você perdeu 6 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("Com um bater de asas veloz, o Corvo desaparece entre as copas das árvores.\n");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUm grasnado sombrio ecoa no seu sonho...");
        System.out.println("Você acorda com arranhões no rosto — um Corvo o atacou enquanto dormia!");
        jogador.diminuirSanidade(8);
        System.out.println("Você perdeu 8 de vida");
    }

}

