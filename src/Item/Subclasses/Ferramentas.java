package Item.Subclasses;

import Item.Superclasse.Item;
import Interface.Usavel;
import Personagem.Superclasse.*;

public class Ferramentas extends Item {
    private String tipo;
    private int eficiencia;

    public Ferramentas(String nome, double peso, int durabilidade, int eficiencia) {
        super(nome, peso, durabilidade);
        this.eficiencia = eficiencia;
    }


    public void usar(Personagem alvo) {
        if (getDurabilidade() > 0) {
            setDurabilidade(getDurabilidade() - 1);
            System.out.println("Você usou a ferramenta: " + getNome());
        } else {
            System.out.println("A ferramenta quebrou.");
        }
    }

    public void exibirDetalhes() {
        System.out.println("🛠 Informações da ferramenta:");
        System.out.println("• Nome: " + getNome());
        System.out.println("• Peso: " + getPeso());
        System.out.println("• Durabilidade: " + getDurabilidade());
        System.out.println("• Eficiência: " + eficiencia);
    }

    //Getters and Setters

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getEficiencia() {
        return eficiencia;
    }
    public void setEficiencia(int eficiencia) {
        this.eficiencia = eficiencia;
    }
}
