package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Jacare extends Criatura {

    // Construtor da classe Jacare
    public Jacare() {
        super("Jacaré", "Alto", "Mordida, cauda giratória ou mergulhar");
    }

    public void atacar(Personagem alvo) {
        alvo.diminuirVida(20);
        System.out.println("O jacaré salta da água e morde com força brutal!");
        System.out.println("Você perdeu 20 de vida.\n");
    }

    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Jacaré girou com sua cauda poderosa, lançando água e desorientando você.");
        jogador.diminuirEnergia(10);
        jogador.diminuirSanidade(10);
        System.out.println("Você perdeu 10 de energia e 10 de sanidade.\n");
    }

    public void fugir() {
        System.out.println("O jacaré mergulha silenciosamente, desaparecendo nas águas turvas.\n");
    }
}
