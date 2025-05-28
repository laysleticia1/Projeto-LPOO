package Criatura.Superclasse;

import Personagem.Superclasse.*;
import Personagem.Subclasses.*;

public abstract class Criatura {
    private String tipoDeCriatura;
    private String nivelDePerigo;
    private String opcoesDeAcao;

    public Criatura (String tipoDeCriatura, String nivelDePerigo, String opcoesDeAcao) {
        this.tipoDeCriatura = tipoDeCriatura;
        this.nivelDePerigo = nivelDePerigo;
        this.opcoesDeAcao = opcoesDeAcao;
    }

    public abstract void atacar(Personagem jogador);
    public abstract void acaoEspecial(Personagem jogador);
    public abstract void fugir();

    // Gettes and Setters
    String getTipoDeCriatura() {
        return tipoDeCriatura;
    }
    public void setTipoCriatura(String tipoDeCriatura) {
        this.tipoDeCriatura = tipoDeCriatura;
    }
    String getNivelDePerigo() {
        return nivelDePerigo;
    }
    public void setNivelDePerigo(String nivelDePerigo){
        this.nivelDePerigo = nivelDePerigo;
    }
    String getOpcoesDeAcao() {
        return opcoesDeAcao;
    }
    public void setOpcoesDeAcao(String opcoesDeAcao) {
        this.opcoesDeAcao = opcoesDeAcao;
    }
}

