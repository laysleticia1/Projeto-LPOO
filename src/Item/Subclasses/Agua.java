package Item.Subclasses;

import Item.Superclasse.Item;

public class Agua extends Item {
    private String pureza;
    private double volume;

    public Agua(String nome, double peso, int durabilidade, String pureza, double volume) {
        super(nome,peso,durabilidade);
        this.pureza = pureza;
        this.volume = volume;
    }

    public void beber() {
    }
}
