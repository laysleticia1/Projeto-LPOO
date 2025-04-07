package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;

public class Urso extends Criatura {

    // Construtor da classe Lobo
    public Urso() {
        setTipoCriatura("Urso");
        setNivelDePerigo("Muito alto");
            setOpcoesDeAcao("Atacar, Rugir, Ignorar");
    }

    //Método
    public void atacar() {
        System.out.println("O urso se levanta e desfere um ataque poderoso!");
    }

    //Método
    public void rugir() {
        System.out.println("O urso solta um rugido ameaçador");
    }

    //Método
    public void ignorar() {
        System.out.println("O urso decide que não vale o esforço");
    }

}
