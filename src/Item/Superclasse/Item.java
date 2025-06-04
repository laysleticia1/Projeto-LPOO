package Item.Superclasse;

import Interface.Usavel;
import Personagem.Inventario.*;
import Personagem.Superclasse.*;

import javax.swing.*;

public class Item {
    private String nome;
    private double peso;
    private int durabilidade;

    public Item (String nome, double peso, int durabilidade) {
        this.nome = nome;
        this.peso = peso;
        this.durabilidade = durabilidade;
    }

    public void usar() {
        this.durabilidade--;
        if (durabilidade < 0) durabilidade = 0;
    }

    public void exibirDetalhes() {
        System.out.println("Item genérico: " + nome);
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }
    public int getDurabilidade() {
        return durabilidade;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public double getPeso() {
        return peso;
    }

    public String getDescricaoItem() {
        if (this.nome != null && !this.nome.trim().isEmpty()) {
            return "Um item do tipo: " + this.nome + ".";
        }
        return "Um item misterioso.";
    }

    public String exibirDetalhesInterface() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ").append(nome).append("\n");
        sb.append("• Peso: ").append(peso).append("\n");
        sb.append("• Durabilidade: ").append(durabilidade).append("\n");
        return sb.toString();
    }
}


