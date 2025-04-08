package Item.Subclasses;

import Item.Superclasse.Item;

public class Ferramentas extends Item {
    private String tipo;
    private int eficiencia;

    public Ferramentas (String tipo, double peso, int durabilidade, int eficiencia) {
        super("Ferramenta",peso,durabilidade);
        this.tipo = tipo;
        this.eficiencia = eficiencia;
    }

    public void usar() {
        this.setDurabilidade (this.getDurabilidade() - 2);

    }
}
