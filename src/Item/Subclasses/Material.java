package Item.Subclasses;

import Interface.Usavel;
import Item.Superclasse.Item;
import Personagem.Superclasse.*;

public class Material extends Item {
    private String tipo;
    private int resistencia;

    public Material (String nome,String tipo, double peso, int durabilidade, int resistencia) {
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.resistencia = resistencia;
    }

    public void usar() {
        if (getDurabilidade() > 0) {
            setDurabilidade(getDurabilidade() - 1);
            System.out.println("Você usou o material: " + getNome() + ". Durabilidade restante: " + getDurabilidade());

            if (getDurabilidade() == 0) {
                System.out.println("⚠️ O material se desgastou completamente e não poderá ser reutilizado.");
            }
        } else {
            System.out.println("❌ O material '" + getNome() + "' está inutilizável.");
        }
    }

    public void combinar(Material outroMaterial) {
    }

    public void exibirDetalhes() {
        System.out.println("Informações do material:");
        System.out.println("• Nome: " + getNome());
        System.out.println("• Tipo: " + tipo);
        System.out.println("• Peso: " + getPeso());
        System.out.println("• Durabilidade: " + getDurabilidade());
        System.out.println("• Resistência: " + resistencia);
    }

    @Override
    public String exibirDetalhesInterface() {
        return "Material: " + getNome() +
                "\nTipo: " + tipo +
                "\nPeso: " + getPeso() +
                "\nDurabilidade: " + getDurabilidade() +
                "\nResistência: " + resistencia;
    }

    //Getters and Setters
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
