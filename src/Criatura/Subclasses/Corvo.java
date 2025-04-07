package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;

public class Corvo extends Criatura {

    // Construtor da classe Corvo
    public Corvo() {
        setTipoCriatura("Corvo");
        setNivelDePerigo("Baixo");
        setOpcoesDeAcao("Espionar, Fazer barulho, Voar");
    }

    // Método
    public void espionar() {
        System.out.println("O corvo observa de longe...");
    }

    // Método
    public void fazerBarulho() {
        System.out.println("O corvo alerta as outras criaturas com seu grasnar!");
    }

    // Método
    public void voar() {
        System.out.println("O corvo bate asas e desaparece no céu");
    }
}
