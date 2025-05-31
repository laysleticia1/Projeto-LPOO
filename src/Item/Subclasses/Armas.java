package Item.Subclasses;

import Item.Superclasse.Item;
import Interface.Usavel;
import Personagem.Superclasse.*;

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

    public void usar(Personagem alvo) {
        if (getDurabilidade() > 0) {
            setDurabilidade(getDurabilidade() - 1);
            alvo.diminuirVida(dano);
            System.out.println("Você atacou com: " + getNome() + " e causou " + dano + " de dano!");
        } else {
            System.out.println("A arma está quebrada.");
        }
    }

    public void exibirDetalhes() {
        System.out.println("⚔ Informações da arma:");
        System.out.println("• Nome: " + getNome());
        System.out.println("• Peso: " + getPeso());
        System.out.println("• Durabilidade: " + getDurabilidade());
        System.out.println("• Tipo: " + tipo);
        System.out.println("• Dano: " + dano);
        System.out.println("• Alcance: " + alcance);
    }

    //Getters and Setters
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

}

