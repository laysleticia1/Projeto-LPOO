package Personagem.Inventario;

import Item.Superclasse.Item;
import Item.Subclasses.Alimentos;
import Item.Subclasses.Agua;
import Item.Subclasses.Remedios;
import Personagem.Superclasse.Personagem;
import java.util.*;
import Excecoes.InventarioCheioException;

public class Inventario {
    private final int capacidadeMaxima;
    private int quantidadeAtual;
    private final Map<String, List<Item>> itensPorCategoria;
    private double pesoTotal = 0;
    private double espacoDisponivel = 100.0; // valor fictício, pode ser ajustado

    public Inventario(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeAtual = 0;
        this.itensPorCategoria = new TreeMap<>();
    }

    public boolean adicionarItem(Item item) throws InventarioCheioException {
        if (quantidadeAtual >= capacidadeMaxima) {
            throw new InventarioCheioException("❌ Inventário cheio! Não é possível adicionar o item: " + item.getNome());
        }

        String categoria = item.getClass().getSimpleName();
        itensPorCategoria.putIfAbsent(categoria, new ArrayList<>());
        itensPorCategoria.get(categoria).add(item);

        quantidadeAtual++;
        pesoTotal += item.getPeso();

        return true;
    }

    public void listarItens() {
        if (quantidadeAtual == 0) {
            System.out.println("O inventário está vazio.");
            return;
        }

        System.out.println("Itens no Inventário:");
        for (Map.Entry<String, List<Item>> entrada : itensPorCategoria.entrySet()) {
            System.out.println("🔸 Categoria: " + entrada.getKey());
            for (Item item : entrada.getValue()) {
                item.exibirDetalhes();
            }
        }
        System.out.printf("Peso total: %.2f / %.2f\n", pesoTotal, espacoDisponivel);
    }

    public void usarItem(String nomeItem, Personagem jogador) {
        Item itemParaUsar = null;

        for (List<Item> lista : itensPorCategoria.values()) {
            for (Item item : lista) {
                if (item.getNome().equalsIgnoreCase(nomeItem)) {
                    itemParaUsar = item;
                    break;
                }
            }
            if (itemParaUsar != null) break;
        }

        if (itemParaUsar != null) {
            System.out.println("Você usou o item: " + itemParaUsar.getNome());

            if (itemParaUsar instanceof Alimentos a) {
                jogador.restaurarFome(a.getValorNutricional());
                jogador.restaurarSanidade(2);
            } else if (itemParaUsar instanceof Agua ag) {
                jogador.restaurarSede((int) ag.getVolume());
                jogador.restaurarSanidade(1);
            } else if (itemParaUsar instanceof Remedios r) {
                jogador.restaurarVida(10);
                jogador.restaurarSanidade(5);
            }

            itemParaUsar.usar();
            System.out.println("Durabilidade restante: " + itemParaUsar.getDurabilidade());

            if (itemParaUsar.getDurabilidade() <= 0) {
                removerItem(itemParaUsar);
                pesoTotal -= itemParaUsar.getPeso();
                System.out.println(itemParaUsar.getNome() + " foi completamente consumido e removido do inventário.");
            }
        } else {
            System.out.println("Item não encontrado no inventário.");
        }
    }

    public boolean removerItem(Item item) {
        String categoria = item.getClass().getSimpleName();
        List<Item> lista = itensPorCategoria.get(categoria);

        if (lista != null && lista.remove(item)) {
            quantidadeAtual--;
            if (lista.isEmpty()) {
                itensPorCategoria.remove(categoria);
            }
            System.out.println("Item removido: " + item.getNome());
            return true;
        }

        System.out.println("❌ Item não encontrado no inventário.");
        return false;
    }

    public boolean removerItem(String nomeItem) {
        for (List<Item> lista : itensPorCategoria.values()) {
            for (Item item : lista) {
                if (item.getNome().equalsIgnoreCase(nomeItem)) {
                    return removerItem(item);
                }
            }
        }
        System.out.println("❌ Item com nome '" + nomeItem + "' não encontrado.");
        return false;
    }

    public List<Item> getTodosItens() {
        List<Item> todos = new ArrayList<>();
        for (List<Item> lista : itensPorCategoria.values()) {
            todos.addAll(lista);
        }
        return todos;
    }

    //Getters and Setters
    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    public Map<String, List<Item>> getItensPorCategoria() {return itensPorCategoria;}
    public double getPesoTotal() {return pesoTotal;}
}
