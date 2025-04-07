package Item.Subclasses;

import Item.Superclasse.Item;

public class Material extends Item {
    private String tipo;
    private int resistencia;

    public Material (String nome, double peso, int durabilidade, String tipo,int resistencia) {
        super(nome,peso,durabilidade);
        this.tipo = tipo;
        this.resistencia = resistencia;
    }

    public void combinar(Material outroMaterial) {
    }
}
