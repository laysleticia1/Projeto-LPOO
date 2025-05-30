package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Fantasma extends Criatura {

    // Construtor da classe Fantasma
    public Fantasma() {
        super("Fantasma", "Médio", "Assombrar, atravessar ou desaparecer");
    }

    public void atacar(Personagem alvo) {
        alvo.diminuirSanidade(12);
        System.out.println("O fantasma emite um grito agudo que ecoa em sua mente!");
        System.out.println("Você perdeu 12 de sanidade.\n");
    }

    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Fantasma atravessa seu corpo, drenando sua energia vital.");
        jogador.diminuirVida(10);
        System.out.println("Você perdeu 10 de vida.\n");
    }

    public void fugir() {
        System.out.println("O fantasma desaparece lentamente no ar, deixando um frio no ambiente.\n");
    }
}

