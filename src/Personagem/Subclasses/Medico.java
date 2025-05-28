package Personagem.Subclasses;

import Interface.Curavel;
import Personagem.Superclasse.Personagem;

public class Medico extends Personagem implements Curavel {

    public Medico(String nomeUsuario) {
        super(nomeUsuario, "Médico");
    }

    @Override
    public void curar(Personagem alvo) {
        int vidaAnterior = alvo.getVida();
        alvo.restaurarVida(20);
        System.out.println(getNome() + " tratou " + alvo.getNome() + ". Vida: " + vidaAnterior + " → " + alvo.getVida());
    }
}
