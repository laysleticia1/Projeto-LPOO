package Personagem.Inventario;

import java.util.List;
import java.util.ArrayList;
import Item.Superclasse.Item;

public class Inventario {
    private List<Item> listaDeItens;
    private double pesoTotal;
    private double espacoDisponivel;

    public Inventario () {
        this.listaDeItens = new ArrayList<> ();
        this.pesoTotal = 0;
        this.espacoDisponivel = 50;
    }

    public void adicionarItem(Item item) {
    }

    public void removerItem(String nomeItem) {
    }

    public void usarItem(String nomeItem) {
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
