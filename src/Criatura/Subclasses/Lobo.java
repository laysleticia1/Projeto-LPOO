package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;

public class Lobo extends Criatura {

    // Construtor da classe Lobo
    public Lobo() {
        setTipoCriatura("Lobo");
        setNivelDePerigo("Médio");
        setOpcoesDeAcao("Atacar, Fugir, Uivar");
    }

    // Método
    public void atacar() {
        System.out.println("O lobo avança e tenta atacar");
    }

    // Método
    public void fugir() {
        System.out.println("O lobo recua e corre para a floresta");
    }

    // Método
    public void uivar() {
        System.out.println("Auuuu! O lobo uiva chamando a sua alcateia");
    }
}
