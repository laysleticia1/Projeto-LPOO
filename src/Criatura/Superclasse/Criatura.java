package Criatura.Superclasse;

import Personagem.Superclasse.Personagem;

public abstract class Criatura {
    private String tipoDeCriatura;
    private String nivelDePerigo;
    private String opcoesDeAcao;

    public Criatura(String tipoDeCriatura, String nivelDePerigo, String opcoesDeAcao) {
        this.tipoDeCriatura = tipoDeCriatura;
        this.nivelDePerigo = nivelDePerigo;
        this.opcoesDeAcao = opcoesDeAcao;
    }

    // SEUS MÉTODOS ABSTRATOS EXISTENTES (NÃO ALTERADOS)
    public abstract void atacar(Personagem jogador);
    public abstract void acaoEspecial(Personagem jogador);
    public abstract void fugir();
    public abstract void ataqueReduzido(Personagem jogador);
    public abstract void ataqueDuranteDescanso(Personagem jogador);

    // NOVOS MÉTODOS ABSTRATOS PARA A INTERFACE GRÁFICA (UI)
    // As subclasses precisarão implementar estes métodos para retornar as descrições das ações.
    public abstract String atacarParaUI(Personagem jogador);
    public abstract String acaoEspecialParaUI(Personagem jogador);
    public abstract String fugirParaUI();
    public abstract String ataqueReduzidoParaUI(Personagem jogador);
    public abstract String ataqueDuranteDescansoParaUI(Personagem jogador);

    // GETTERS E SETTERS (SEUS MÉTODOS EXISTENTES)
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
}