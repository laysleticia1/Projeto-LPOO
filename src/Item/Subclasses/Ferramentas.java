package Item.Subclasses;

import Item.Superclasse.Item;

public class Ferramentas extends Item {
    private String tipo;
    private int eficiencia;

    public Ferramentas (String nome, double peso, int durabilidade, String tipo,int eficiencia) {
        super(nome,peso,durabilidade);
        this.tipo = tipo;
        this.eficiencia = eficiencia;
    }

    public void usar() {
    }
}
