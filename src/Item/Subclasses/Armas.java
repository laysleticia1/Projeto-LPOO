package Item.Subclasses;

import Item.Superclasse.Item;
import Interface.Usavel;
import Personagem.Superclasse.*;

public class Armas extends Item {
    private String tipo;
    private int dano;
    private int alcance;

    public Armas (String nome, double peso, int durabilidade, String tipo, int dano, int alcance) {
        super(nome,peso,durabilidade);
        this.tipo = tipo;
        this.dano = dano;
        this.alcance = alcance;
    }

    public void usar(Personagem alvo) {
        if (getDurabilidade() > 0) {
            setDurabilidade(getDurabilidade() - 1);
            alvo.receberDano(dano);
            System.out.println("Você atacou com: " + getNome() + " e causou " + dano + " de dano!");
        } else {
            System.out.println("A arma está quebrada.");
        }
    }
}
