package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import Item.Superclasse.Item;
import Item.Subclasses.*;
import Gerenciadores.GerenciadorDeEventos; // Assumindo que você tem este gerenciador
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Floresta extends Ambiente {
    private String vegetacaoEspecifica;
    private String faunaEspecifica;

    public Floresta() {
        super("Floresta Densa",
                "Local coberto por uma vegetação densa e viva, repleta de árvores altas e sons constantes da natureza. Criaturas selvagens habitam essa região rica em recursos como frutas, madeira e ervas medicinais.",
                2,
                new ArrayList<>(Arrays.asList("Frutas Silvestres", "Madeira de Lei", "Ervas Medicinais")),
                0.4,
                "Chuvoso e Úmido",
                "/Resources/ambientes/floresta.png"); // Caminho da imagem
        this.vegetacaoEspecifica = "Árvores altas, cipós e arbustos densos.";
        this.faunaEspecifica = "Pássaros exóticos, pequenos mamíferos e insetos variados.";
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("\nVocê caminha entre as árvores densas da floresta...");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        Item recurso = coletarItemAleatorio();
        if (recurso != null) {
            System.out.println("\nVocê encontrou: " + recurso.getNome());
            System.out.println("\n🔍 Detalhes do item:");

            if (recurso instanceof Material m) {
                System.out.println("- Tipo: " + m.getTipo());
                System.out.println("- Peso: " + m.getPeso() + " kg");
            } else if (recurso instanceof Alimentos alimento) {
                System.out.println("- Tipo: " + alimento.getTipo());
                System.out.println("- Valor Nutricional: " + alimento.getValorNutricional());
            } else {
                System.out.println("- Peso: " + recurso.getPeso());
                System.out.println("- Durabilidade: " + recurso.getDurabilidade());
            }
            System.out.print("Deseja adicionar este item ao seu inventário? (s/n): ");
            Scanner scanner = new Scanner(System.in); // Cuidado com múltiplos Scanners System.in
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s") || resposta.equals("sim")) {
                try {
                    jogador.adicionarAoInventario(recurso);
                } catch (Exception e) { // Idealmente uma exceção mais específica
                    System.out.println("Não foi possível adicionar o item: " + e.getMessage());
                }
            } else {
                System.out.println("Você deixou o item para trás.");
            }
        } else {
            System.out.println("\nNada útil foi encontrado na floresta desta vez.");
        }

        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos(); // Supondo a existência
        gerenciadorEventos.aplicarEventoAleatorioPorAmbiente(jogador); // Supondo este método
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.4) return null;

        int opcao = (int) (Math.random() * 6); // Ajuste o número de opções conforme seus itens
        switch (opcao) {
            case 0: return new Alimentos("Cogumelo Nutritivo", 0.3, 1, 20, "Fungo", 2);
            case 1: return new Alimentos("Nozes Silvestres", 0.4, 3, 12, "Semente", 5);
            case 2: return new Material("Galho de Árvore Resistente", "Madeira", 0.5, 3, 30);
            case 3: return new Agua("Água de Chuva Coletada", 1.2, 3, "Alta", 0.5, 0.05);
            case 4: return new Remedios("Folhas Medicinais", "Fitoterápico", "Cura pequenos ferimentos.");
            case 5: return new Armas("Graveto Afiado", 0.5, 2, "Curta", 10, 1);
            default: return null;
        }
    }

    public String getVegetacaoEspecifica() {
        return vegetacaoEspecifica;
    }

    public void setVegetacaoEspecifica(String vegetacaoEspecifica) {
        this.vegetacaoEspecifica = vegetacaoEspecifica;
    }

    public String getFaunaEspecifica() {
        return faunaEspecifica;
    }

    public void setFaunaEspecifica(String faunaEspecifica) {
        this.faunaEspecifica = faunaEspecifica;
    }
}

