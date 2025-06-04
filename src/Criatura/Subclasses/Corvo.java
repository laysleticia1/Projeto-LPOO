package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Corvo extends Criatura {

    public Corvo() {
        super("Corvo Agourento", "Baixo", "Bicar, Grito perturbador");
    }

    @Override
    public void atacar(Personagem jogador) {
        jogador.diminuirVida(10); // No seu código original o dano impresso era 10, mas diminuirVida era 8 no ataqueDuranteDescanso. Ajustei para 10 aqui.
        System.out.println("Um corvo negro mergulha em você com um grasnado estridente, arranhando seu rosto com as garras.");
        System.out.println("Você perdeu 10 de vida.");
    }

    @Override
    public String atacarParaUI(Personagem jogador) {
        atacar(jogador);
        return "Um corvo negro mergulha em você com um grasnado estridente, arranhando seu rosto com as garras.\nVocê perdeu 10 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(5);
        System.out.println("Um corvo negro mergulha em você com um grasnado estridente, arranhando seu rosto com as garras.");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "O corvo tenta uma bicada rápida!\nVocê perdeu 5 de vida."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        jogador.diminuirSanidade(6);
        System.out.println("O Corvo emite um grito estridente e perturbador que ecoa por sua mente.");
        System.out.println("Você perdeu 6 de sanidade.");
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "O Corvo emite um grito estridente e perturbador que ecoa por sua mente.\nVocê perdeu 6 de sanidade.";
    }

    @Override
    public void fugir() {
        System.out.println("Com um bater de asas veloz, o Corvo desaparece entre as copas das árvores.\n");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "Com um bater de asas veloz, o Corvo desaparece entre as copas das árvores.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nUm grasnado sombrio ecoa no seu sonho...");
        System.out.println("Você acorda com arranhões no rosto — um Corvo o atacou enquanto dormia!");
        // Originalmente seu System.out dizia "perdeu 8 de vida" mas diminuirSanidade(8) era chamado.
        // Assumindo que era para diminuir vida conforme a mensagem:
        jogador.diminuirVida(8);
        System.out.println("Você perdeu 8 de vida.");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Um grasnado sombrio ecoa no seu sonho...\nVocê acorda com arranhões no rosto — um Corvo o atacou enquanto dormia!\nVocê perdeu 8 de vida.";
    }
}
