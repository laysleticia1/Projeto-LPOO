package Personagem;

import Item.Item;

public class Inventario {
    private String[] listaDeItens;
    private double pesoTotal;
    private double espacoDisponivel;

    void adicionarItem(Item item) {
    }

    void removerItem(String nomeItem) {
    }

    void usarItem(String nomeItem) {
    }

    public double getEspacoDisponivel() {
        return espacoDisponivel;
    }

    public void setEspacoDisponivel(double espacoDisponivel) {
        this.espacoDisponivel = espacoDisponivel;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }


}
