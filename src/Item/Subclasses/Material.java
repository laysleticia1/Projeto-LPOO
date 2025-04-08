package Item.Subclasses;

import Item.Superclasse.Item;

public class Material extends Item {
    private String tipo;
    private int resistencia;

    public Material (String tipo, double peso, int durabilidade, int resistencia) {
        super("Material", peso, durabilidade);
        this.tipo = tipo;
        this.resistencia = resistencia;
    }

    public void combinar(Material outroMaterial) {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
}
