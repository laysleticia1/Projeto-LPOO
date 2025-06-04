package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import Item.Superclasse.Item;
import Item.Subclasses.*;
import Gerenciadores.GerenciadorDeEventos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Caverna extends Ambiente {
    private String luminosidadeAmbiente;
    private String tipoDeCriaturas;
    private String fonteDeHidratacao;

    public Caverna() {
        super("Fissura de Narzug", "Um abismo venenoso, profundo e sussurrante. O ar é pesado e irrespirável, e as rochas parecem vigiar. Sons ecoam do fundo, chamando... ou alertando.", 7, new ArrayList<>(Arrays.asList("Minério de Ferro", "Cristais", "Fungos Luminosos")), 0.65, "Úmido, Frio e Escuro", "/Resources/Ambientes/caverna.png");
        this.luminosidadeAmbiente = "Penumbra, requer fonte de luz para exploração profunda.";
        this.tipoDeCriaturas = "Morcegos, aranhas gigantes e criaturas adaptadas à escuridão.";
        this.fonteDeHidratacao = "Gotejamento de água das estalactites, pureza duvidosa.";
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("\nVocê adentra a escuridão úmida da caverna...");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        Item recurso = coletarItemAleatorio();
        if (recurso != null) {
            System.out.println("\nVocê encontrou: " + recurso.getNome());
            System.out.println("\n🔍 Detalhes do item:");

            if (recurso instanceof Material m) {
                System.out.println("- Tipo: " + m.getTipo());
                System.out.println("- Peso: " + m.getPeso() + " kg");
                System.out.println("- Durabilidade: " + m.getDurabilidade());
                System.out.println("- Resistência: " + m.getResistencia());
            } else if (recurso instanceof Agua a) {
                System.out.println("- Tipo: Água");
                System.out.println("- Pureza: " + a.getPureza());
                System.out.println("- Volume: " + a.getPeso() + " L");
                System.out.println("- Risco de contaminação: " + (a.getChanceContaminacao() * 100) + "%");
            } else if (recurso instanceof Ferramentas f) {
                System.out.println("- Tipo: " + f.getTipo());
                System.out.println("- Durabilidade: " + f.getDurabilidade());
                System.out.println("- Eficácia: " + f.getEficiencia());
                System.out.println("- Peso: " + f.getPeso() + " kg");
            } else if (recurso instanceof Armas arma) {
                System.out.println("- Tipo: " + arma.getTipo());
                System.out.println("- Dano: " + arma.getDano());
                System.out.println("- Durabilidade: " + arma.getDurabilidade());
                System.out.println("- Alcance: " + arma.getAlcance());
            } else if (recurso instanceof Alimentos alimento) {
                System.out.println("- Tipo: " + alimento.getTipo());
                System.out.println("- Peso: " + alimento.getPeso() + " kg");
                System.out.println("- Valor Nutricional: " + alimento.getValorNutricional());
                System.out.println("- Validade: " + alimento.getValidade() + " dia/s");
                System.out.println("- Durabilidade: " + alimento.getDurabilidade());
            } else if (recurso instanceof Remedios r) {
                System.out.println("- Nome: " + r.getNome());
                System.out.println("- Tipo: " + r.getTipo());
                System.out.println("- Efeito: " + r.getEfeito());
            } else {
                System.out.println("- Peso: " + recurso.getPeso());
                System.out.println("- Durabilidade: " + recurso.getDurabilidade());
            }
            System.out.print("Deseja adicionar este item ao seu inventário? (s/n): ");
            Scanner sc = new Scanner(System.in);
            String resposta = sc.nextLine().trim().toLowerCase();
            if (resposta.equals("s") || resposta.equals("sim")) {
                try {
                    jogador.adicionarAoInventario(recurso);
                } catch (Exception e) {
                    System.out.println("Não foi possível adicionar o item: " + e.getMessage());
                }
            } else {
                System.out.println("Você deixou o item para trás.");
            }
        } else {
            System.out.println("\nA caverna não revelou segredos desta vez.");
        }

        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        gerenciadorEventos.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.5) return null;

        int opcao = (int) (Math.random() * 9);
        switch (opcao) {
            case 0:
                return new Ferramentas("Tocha Simples", 1.5, 3, 30);
            case 1:
                return new Material("Cristal de Caverna", "Mineral", 0.8, 2, 90);
            case 2:
                return new Agua("Água de Gotejamento", 0.4, 1, "Duvidosa", 0.5, 0.7);
            case 3:
                return new Armas("Picareta Enferrujada", 2.0, 2, "Pesada", 20, 1); // Dano ligeiramente menor
            case 4: // Antiga PedrasPreciosas, agora Material
                return new Material("Pequeno Cristal Brilhante", "Gema", 0.1, 1, 10);
            case 5:
                return new Alimentos("Cogumelo Sombrio", 0.3, 1, -15, "Fungo", 1); // Efeito negativo
            case 6:
                return new Alimentos("Musgo de Parede", 0.2, 1, 5, "Vegetal", 2);
            case 7:
                return new Alimentos("Larvas Translúcidas", 0.1, 3, 12, "Inseto", 4);
            case 8:
                return new Material("Sucata Variada", "Metal Enferrujado", 1.0, 3, 40);
            default:
                return null;
        }
    }

    //Getters and Setters
    public String getLuminosidadeAmbiente() { return luminosidadeAmbiente; }
    public void setLuminosidadeAmbiente(String luminosidadeAmbiente) { this.luminosidadeAmbiente = luminosidadeAmbiente; }
    public String getTipoDeCriaturas() { return tipoDeCriaturas; }
    public void setTipoDeCriaturas(String tipoDeCriaturas) { this.tipoDeCriaturas = tipoDeCriaturas; }
    public String getFonteDeHidratacao() { return fonteDeHidratacao; }
    public void setFonteDeHidratacao(String fonteDeHidratacao) { this.fonteDeHidratacao = fonteDeHidratacao; }

    //Interface
    public void explorarInterface(Personagem jogador) {
        JOptionPane.showMessageDialog(null, "Você adentra a escuridão úmida da caverna...");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        Item recurso = coletarItemAleatorio();
        if (recurso != null) {
            StringBuilder detalhes = new StringBuilder();
            detalhes.append("Você encontrou: ").append(recurso.getNome()).append("\n\n🔍 Detalhes do item:\n");

            if (recurso instanceof Material m) {
                detalhes.append("- Tipo: ").append(m.getTipo()).append("\n");
                detalhes.append("- Peso: ").append(m.getPeso()).append(" kg\n");
                detalhes.append("- Durabilidade: ").append(m.getDurabilidade()).append("\n");
                detalhes.append("- Resistência: ").append(m.getResistencia()).append("\n");
            } else if (recurso instanceof Agua a) {
                detalhes.append("- Tipo: Água\n");
                detalhes.append("- Pureza: ").append(a.getPureza()).append("\n");
                detalhes.append("- Volume: ").append(a.getPeso()).append(" L\n");
                detalhes.append("- Risco de contaminação: ").append(a.getChanceContaminacao() * 100).append("%\n");
            } else if (recurso instanceof Ferramentas f) {
                detalhes.append("- Tipo: ").append(f.getTipo()).append("\n");
                detalhes.append("- Durabilidade: ").append(f.getDurabilidade()).append("\n");
                detalhes.append("- Eficácia: ").append(f.getEficiencia()).append("\n");
                detalhes.append("- Peso: ").append(f.getPeso()).append(" kg\n");
            } else if (recurso instanceof Armas arma) {
                detalhes.append("- Tipo: ").append(arma.getTipo()).append("\n");
                detalhes.append("- Dano: ").append(arma.getDano()).append("\n");
                detalhes.append("- Durabilidade: ").append(arma.getDurabilidade()).append("\n");
                detalhes.append("- Alcance: ").append(arma.getAlcance()).append("\n");
            } else if (recurso instanceof Alimentos alimento) {
                detalhes.append("- Tipo: ").append(alimento.getTipo()).append("\n");
                detalhes.append("- Peso: ").append(alimento.getPeso()).append(" kg\n");
                detalhes.append("- Valor Nutricional: ").append(alimento.getValorNutricional()).append("\n");
                detalhes.append("- Validade: ").append(alimento.getValidade()).append(" dia/s\n");
                detalhes.append("- Durabilidade: ").append(alimento.getDurabilidade()).append("\n");
            } else if (recurso instanceof Remedios r) {
                detalhes.append("- Nome: ").append(r.getNome()).append("\n");
                detalhes.append("- Tipo: ").append(r.getTipo()).append("\n");
                detalhes.append("- Efeito: ").append(r.getEfeito()).append("\n");
            } else {
                detalhes.append("- Peso: ").append(recurso.getPeso()).append("\n");
                detalhes.append("- Durabilidade: ").append(recurso.getDurabilidade()).append("\n");
            }

            int opcao = JOptionPane.showConfirmDialog(null, detalhes.toString() + "\nDeseja adicionar este item ao seu inventário?", "Item Encontrado", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                try {
                    jogador.adicionarAoInventario(recurso);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "❌ Não foi possível adicionar o item: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Você deixou o item para trás.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "A caverna não revelou segredos desta vez.");
        }

        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        gerenciadorEventos.aplicarEventoAleatorioPorAmbiente(jogador);
    }
}
