package Criatura.Superclasse;

public class Criatura {
    private String tipoDeCriatura;
    private String nivelDePerigo;
    private String opcoesDeAcao;

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

