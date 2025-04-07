package Item.Subclasses;

import Item.Superclasse.Item;

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

    public void atacar() {  // Faltando alvo
    }
}
