package Item.Subclasses;

import Item.Superclasse.Item;

public class Alimentos extends Item {
    private int valorNutricional;
    private String tipo;
    private int validade;

    public Alimentos (String nome, double peso, int durabilidade, int valorNutricional, String tipo, int validade) {
        super (nome, peso, durabilidade);
        this.valorNutricional = valorNutricional;
        this.tipo = tipo;
        this.validade = validade;
    }

    public void consumir() {
    }
}
