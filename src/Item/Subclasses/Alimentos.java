package Item.Subclasses;

import Item.Superclasse.Item;
import Interface.Usavel;
import Personagem.Superclasse.*;

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

    public void exibirDetalhes() {
        System.out.println("Informações do alimento:");
        System.out.println("• Nome: " + getNome());
        System.out.println("• Peso: " + getPeso());
        System.out.println("• Durabilidade: " + getDurabilidade());
        System.out.println("• Valor Nutricional: " + valorNutricional);
    }

    public void usar(Personagem alvo) {
        if (getDurabilidade() > 0) {
            setDurabilidade(getDurabilidade() - 1);
            alvo.setFome(alvo.getFome() + valorNutricional); // exemplo
            System.out.println("Você consumiu: " + getNome());
        } else {
            System.out.println("Este alimento está estragado.");
        }
    }

    //Gettes and Setters
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getValidade() {
        return validade;
    }
    public void setValidade(int validade) {
        this.validade = validade;
    }
    public int getValorNutricional() {
        return valorNutricional;
    }
    public void setValorNutricional(int valorNutricional) {
        this.valorNutricional = valorNutricional;
    }

    //Interface
    @Override
    public String exibirDetalhesInterface() {
        return "Alimento: " + getNome() +
                "\nTipo: " + tipo +
                "\nPeso: " + getPeso() +
                "\nDurabilidade: " + getDurabilidade() +
                "\nValor Nutricional: " + valorNutricional +
                "\nValidade (dias): " + validade;
    }

}
