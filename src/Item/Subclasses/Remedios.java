package Item.Subclasses;

import Item.Superclasse.Item;
import Personagem.Superclasse.Personagem;
import Interface.Usavel;
import Interface.Curavel;

public class Remedios extends Item implements Usavel, Curavel{
    private String tipo;
    private String efeito;

    public Remedios(String tipo, String efeito) {
        super("Remédio",0.5, 1);
        this.tipo = tipo;
        this.efeito = efeito;
    }

    public void usar(Personagem alvo) {
        if (getDurabilidade() > 0) {
            setDurabilidade(getDurabilidade() - 1);
            curar(alvo);
        } else {
            System.out.println("Este remédio já foi utilizado e não pode ser usado novamente.");
        }
    }
    public void curar(Personagem alvo) {
        int cura = 20;
        int vidaAnterior = alvo.getVida();
        alvo.setVida(vidaAnterior + cura);
        System.out.println("Você tomou o remédio de tipo: " + tipo +
                ". Efeito aplicado: " + efeito +
                ". Uma sensação de alívio percorre seu corpo, restaurando " +
                cura + " pontos de vida.");
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }
}
