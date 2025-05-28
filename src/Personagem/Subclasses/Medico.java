package Personagem.Subclasses;

import Interface.Curavel;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.*;
import Ambiente.Subclasses.*;

public class Medico extends Personagem implements Curavel {

    public Medico(String nomeUsuario) {
        super(nomeUsuario, "Médico");
    }

    public void curar(Personagem alvo) {
        int vidaAnterior = alvo.getVida();
        alvo.restaurarVida(20);
        System.out.println(getNome() + " tratou " + alvo.getNome() + ". Vida: " + vidaAnterior + " → " + alvo.getVida());
    }

    public void prepararRemedioNatural(Ambiente ambiente) {
        if (ambiente instanceof Floresta || ambiente instanceof LagoRio) {
            this.restaurarVida(10);
            System.out.println("Você preparou um extrato natural e recuperou 10 de vida.");
        } else {
            System.out.println("Você não encontrou os ingredientes certos neste ambiente.");
        }
    }
}
