package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Corvo extends Criatura {

    // Construtor da classe Corvo
    public Corvo() {
        super ("Corvo", "Baixo", "Atacar ou voar");
    }

    public void atacar(Personagem alvo) {
        System.out.println("O corvo mergulha das sombras e seu grasnar perfura a mente do personagem, despedaçando sua sanidade com seu grasnar sombrio.");
        alvo.setSanidade(alvo.getSanidade() - 10);
    }

    public void voar() {
        System.out.println("Com um bater de asas silencioso, o corvo se ergue aos céus e desaparece entre as nuvens escuras.");
    }

}
