package Personagem.Inventario;

import java.util.ArrayList;
import Item.Superclasse.Item;

public class Inventario {
    private ArrayList<Item> listaDeItens;
    private double pesoTotal;
    private double espacoDisponivel;

    public Inventario() {
        this.listaDeItens = new ArrayList<>();
        this.pesoTotal = 0;
        this.espacoDisponivel = 15; // capacidade máxima
    }

    public boolean adicionarItem(Item item) {
        if (pesoTotal + item.getPeso() <= espacoDisponivel) {
            listaDeItens.add(item);
            pesoTotal += item.getPeso();
            System.out.println(item.getNome() + " foi adicionado/a ao inventário.");
            return true;
        } else {
            System.out.println("Não há espaço suficiente para carregar " + item.getNome() + ".");
            return false;
        }
    }

    public void removerItem(String nomeItem) {
        Item itemParaRemover = null;
        for (Item item : listaDeItens) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                itemParaRemover = item;
                break;
            }
        }

        if (itemParaRemover != null) {
            listaDeItens.remove(itemParaRemover);
            pesoTotal -= itemParaRemover.getPeso();
            System.out.println(nomeItem + " foi removido do inventário.");
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    public void usarItem(String nomeItem) {
        Item itemParaUsar = null;

        for (Item item : listaDeItens) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                itemParaUsar = item;
                break;
            }
        }

        if (itemParaUsar != null) {
            System.out.println("Você usou o item: " + itemParaUsar.getNome());
            itemParaUsar.usar(); // Reduz 1 de durabilidade
            System.out.println("Durabilidade restante: " + itemParaUsar.getDurabilidade());

            if (itemParaUsar.getDurabilidade() <= 0) {
                listaDeItens.remove(itemParaUsar);
                pesoTotal -= itemParaUsar.getPeso();
                System.out.println(itemParaUsar.getNome() + " foi completamente consumido/a e removido/a do inventário.");
            }

        } else {
            System.out.println("Item não encontrado no inventário.");
        }
    }


    public void listarItens() {
        if (listaDeItens.isEmpty()) {
            System.out.println("Inventário vazio.");
        } else {
            System.out.println("Itens no inventário:");
            for (Item item : listaDeItens) {
                System.out.println("- " + item.getNome() + " (peso: " + item.getPeso() + ")");
            }
            System.out.printf("Peso total: %.2f / %.2f\n", pesoTotal, espacoDisponivel);
        }
    }

    // Getters e Setters
    public double getEspacoDisponivel() {
        return espacoDisponivel;
    }

    public void setEspacoDisponivel(double espacoDisponivel) {
        this.espacoDisponivel = espacoDisponivel;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public ArrayList<Item> getArrayInventario() {
        return listaDeItens;
    }

    public void setArrayInventario(ArrayList<Item> novaLista) {
        this.listaDeItens = novaLista;
        this.pesoTotal = 0;
        for (Item item : novaLista) {
            this.pesoTotal += item.getPeso();
        }
    }

}
