package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import Item.Superclasse.Item;
import Item.Subclasses.*;
import Gerenciadores.GerenciadorDeEventos; // Assumindo que você tem este gerenciador
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Floresta extends Ambiente {
    private String vegetacaoEspecifica;
    private String faunaEspecifica;

    public Floresta() {
        super("Floresta de Elvarron", "Local coberto por uma vegetação densa e viva, onde trilhas somem e sombras observam. Rica em frutas, ervas e madeira, mas lar de criaturas furtivas e segredos que afetam até a mente dos viajantes.", 2, new ArrayList<>(Arrays.asList("Frutas Silvestres", "Madeira de Lei", "Ervas Medicinais")), 0.4, "Chuvoso e Úmido", "/Resources/ambientes/floresta.png"); // Caminho da imagem
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

    //Getters and Setters
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

    //Interface
    public void explorarInterface(Personagem jogador) {
        StringBuilder mensagem = new StringBuilder();

        mensagem.append("Você começa a explorar a floresta...\n");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        Item recurso = coletarItemAleatorio();
        if (recurso != null) {
            mensagem.append("\nVocê encontrou: ").append(recurso.getNome()).append("\n");

            if (recurso instanceof Material m) {
                mensagem.append("- Tipo: ").append(m.getTipo()).append("\n")
                        .append("- Peso: ").append(m.getPeso()).append(" kg\n")
                        .append("- Durabilidade: ").append(m.getDurabilidade()).append("\n")
                        .append("- Resistência: ").append(m.getResistencia()).append("\n");
            } else if (recurso instanceof Agua a) {
                mensagem.append("- Tipo: Água\n")
                        .append("- Pureza: ").append(a.getPureza()).append("\n")
                        .append("- Volume: ").append(a.getPeso()).append(" L\n")
                        .append("- Risco de contaminação: ").append(a.getChanceContaminacao() * 100).append("%\n");
            } else if (recurso instanceof Ferramentas f) {
                mensagem.append("- Tipo: ").append(f.getTipo()).append("\n")
                        .append("- Durabilidade: ").append(f.getDurabilidade()).append("\n")
                        .append("- Eficácia: ").append(f.getEficiencia()).append("\n")
                        .append("- Peso: ").append(f.getPeso()).append(" kg\n");
            } else if (recurso instanceof Armas arma) {
                mensagem.append("- Tipo: ").append(arma.getTipo()).append("\n")
                        .append("- Dano: ").append(arma.getDano()).append("\n")
                        .append("- Durabilidade: ").append(arma.getDurabilidade()).append("\n")
                        .append("- Alcance: ").append(arma.getAlcance()).append("\n");
            } else if (recurso instanceof Alimentos alimento) {
                mensagem.append("- Tipo: ").append(alimento.getTipo()).append("\n")
                        .append("- Peso: ").append(alimento.getPeso()).append(" kg\n")
                        .append("- Valor Nutricional: ").append(alimento.getValorNutricional()).append("\n")
                        .append("- Validade: ").append(alimento.getValidade()).append(" dia/s\n")
                        .append("- Durabilidade: ").append(alimento.getDurabilidade()).append("\n");
            } else if (recurso instanceof Remedios r) {
                mensagem.append("- Nome: ").append(r.getNome()).append("\n")
                        .append("- Tipo: ").append(r.getTipo()).append("\n")
                        .append("- Efeito: ").append(r.getEfeito()).append("\n");
            } else {
                mensagem.append("- Peso: ").append(recurso.getPeso()).append("\n")
                        .append("- Durabilidade: ").append(recurso.getDurabilidade()).append("\n");
            }

            int opcao = JOptionPane.showConfirmDialog(null,
                    mensagem + "\nDeseja adicionar este item ao seu inventário?",
                    "Item Encontrado", JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.YES_OPTION) {
                try {
                    jogador.adicionarAoInventario(recurso);
                    JOptionPane.showMessageDialog(null, "✅ Item adicionado com sucesso!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "❌ Não foi possível adicionar o item: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Você deixou o item para trás.");
            }

        } else {
            mensagem.append("\nA floresta não revelou nada útil desta vez...");
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }

        new Gerenciadores.GerenciadorDeEventos().aplicarEventoAleatorioPorAmbiente(jogador);
    }

}

