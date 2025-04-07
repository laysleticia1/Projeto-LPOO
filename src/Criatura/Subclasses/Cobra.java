package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Cobra extends Criatura {

    // Construtor da classe Cobra
    public Cobra() {
        setTipoCriatura("Cobra");
        setNivelDePerigo("Médio");
        setOpcoesDeAcao("Atacar, Envenenar, Esconder");
    }

    // Método
    public void atacar() {
        System.out.println("A cobra da um bote venenosa...");
    }

    // Método
    public void envenenar(Personagem alvo) {
        System.out.println("A cobra injeta veneno! O personagem começa a sentir seus efeitos...");

        alvo.setVida(alvo.getVida() - 20);
        alvo.setEnergia(alvo.getEnergia() - 10);
        alvo.setSanidade(alvo.getSanidade() - 5);

        System.out.println("Efeitos do veneno: -20 de vida, -10 de energia, -5 de sanidade.");
        System.out.println("Vida atual de " + alvo.getNome() + ": " + alvo.getVida());
        System.out.println("Energia atual de " + alvo.getNome() + ": " + alvo.getEnergia());
        System.out.println("Sanidade atual de " + alvo.getNome() + ": " + alvo.getSanidade());
    }

    //Método
    public void esconder() {
        System.out.println("A cobra foge e se esconde");
    }
}
