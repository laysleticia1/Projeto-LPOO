package Item.Subclasses;

import Item.Superclasse.Item;

public class Remedios extends Item {
    private String tipo;
    private String efeito;

    public Remedios(String nome, double peso, int durabilidade, String tipo, String efeito) {
        super(nome,peso,durabilidade);
        this.tipo = tipo;
        this.efeito = efeito;
    }

    public void usar() {
    }
}
