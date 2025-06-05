package Criatura.Superclasse;

import Personagem.Superclasse.Personagem;

import javax.swing.*;

public abstract class Criatura {
    private String tipoDeCriatura;
    private String nivelDePerigo;
    private String opcoesDeAcao;

    public Criatura(String tipoDeCriatura, String nivelDePerigo, String opcoesDeAcao) {
        this.tipoDeCriatura = tipoDeCriatura;
        this.nivelDePerigo = nivelDePerigo;
        this.opcoesDeAcao = opcoesDeAcao;
    }

    public abstract void atacar(Personagem jogador);
    public abstract void acaoEspecial(Personagem jogador);
    public abstract void fugir();
    public abstract void ataqueDuranteDescanso(Personagem jogador);


    // Getters and Setters
    public String getTipoDeCriatura() {
        return tipoDeCriatura;
    }
    public void setTipoCriatura(String tipoDeCriatura) {
        this.tipoDeCriatura = tipoDeCriatura;
    }
    public String getNivelDePerigo() { // Alterado para public para consistência
        return nivelDePerigo;
    }
    public void setNivelDePerigo(String nivelDePerigo) {
        this.nivelDePerigo = nivelDePerigo;
    }
    public String getOpcoesDeAcao() { // Alterado para public para consistência
        return opcoesDeAcao;
    }
    public void setOpcoesDeAcao(String opcoesDeAcao) {
        this.opcoesDeAcao = opcoesDeAcao;
    }

    //Interface
    public abstract void atacarInterface(Personagem jogador, JTextArea areaLog);
    public abstract void acaoEspecialInterface(Personagem jogador, JTextArea areaLog);
    public abstract void fugirInterface(JTextArea areaLog);
    public abstract void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog);
}