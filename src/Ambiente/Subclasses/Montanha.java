package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import Item.Superclasse.Item;
import Item.Subclasses.*;
import Gerenciadores.GerenciadorDeEventos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;

public class Montanha extends Ambiente {
    private String terrenoEspecifico;
    private String vegetacaoTipica;

    public Montanha() {
        super("Montanhas de Vhaldrak",
                "Local elevado e desafiador. A montanha apresenta terras geladas e traiçoeiras, com penhascos irregulares e ventos cortantes que ecoam antigos lamentos. Entre nevascas e perigos constantes, jazem minérios raros e pedras preciosas — recompensas guardadas pelo frio e pela morte.", 8, new ArrayList<>(Arrays.asList("Minério de Prata", "Pedras Preciosas", "Ervas Raras de Altitude")), 0.6, "Frio e com ventos fortes", "/Resources/Ambientes/montanha.png");
        this.terrenoEspecifico = "Rochoso, íngreme e com precipícios.";
        this.vegetacaoTipica = "Escassa, composta por arbustos resistentes e líquens.";
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("\nVocê sobe pelas trilhas íngremes da montanha...");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        Item itemEncontrado = coletarItemAleatorio();
        if (itemEncontrado != null) {
            System.out.println("\nVocê encontrou um item: " + itemEncontrado.getNome());
            System.out.println("\n🔍 Detalhes do item:");

            if (itemEncontrado instanceof Material m) {
                System.out.println("- Tipo: " + m.getTipo());
                System.out.println("- Peso: " + m.getPeso() + " kg");
            } else if (itemEncontrado instanceof Agua a) {
                System.out.println("- Tipo: Água");
                System.out.println("- Pureza: " + a.getPureza());
            } else if (itemEncontrado instanceof Ferramentas f) {
                System.out.println("- Tipo: " + f.getTipo());
                System.out.println("- Eficácia: " + f.getEficiencia());
            } else if (itemEncontrado instanceof Armas arma) {
                System.out.println("- Tipo: " + arma.getTipo());
                System.out.println("- Dano: " + arma.getDano());
            } else if (itemEncontrado instanceof Remedios r) {
                System.out.println("- Efeito: " + r.getEfeito());
            } else if (itemEncontrado instanceof Alimentos alimento) {
                System.out.println("- Tipo: " + alimento.getTipo());
                System.out.println("- Valor Nutricional: " + alimento.getValorNutricional());
            } else {
                System.out.println("- Peso: " + itemEncontrado.getPeso());
            }
            System.out.println("Deseja coletar esse item? (s/n)");
            Scanner scanner = new Scanner(System.in);
            String escolha = scanner.nextLine();
            if (escolha.equalsIgnoreCase("s")) {
                try {
                    jogador.adicionarAoInventario(itemEncontrado);
                } catch (Exception e) {
                    System.out.println("Não foi possível adicionar o item: " + e.getMessage());
                }
            } else {
                System.out.println("Você decidiu deixar o item para trás.");
            }
        } else {
            System.out.println("\nHoje não foi possível encontrar nenhum item útil na montanha.");
        }

        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        gerenciadorEventos.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.2) return null;

        int opcao = (int) (Math.random() * 9);
        switch (opcao) {
            case 0:
                return new Material("Rocha Metálica", "Mineral", 1.8, 4, 75);
            case 1:
                return new Ferramentas("Pederneira Reforçada", 0.7, 5, 50);
            case 2:
                return new Agua("Água de Degelo Limpa", 1.0, 1, "Potável", 1.0, 0.3);
            case 3: // Antiga PedrasPreciosas, agora Material
                return new Material("Ametista Bruta", "Gema Preciosa", 0.3, 1, 50);
            case 4:
                return new Remedios("Unguento Aquecedor", "Térmico", "Protege do frio intenso por um período.");
            case 5:
                return new Armas("Machado de Escalada", 2.5, 3, "Pesada", 22, 1);
            case 6:
                return new Alimentos("Frutas Congeladas de Altitude", 0.3, 4, 10, "Fruta Congelada", 15);
            case 7:
                return new Alimentos("Carne Seca de Animal Montanhês", 0.5, 3, 30, "Carne Seca", 20);
            case 8:
                return new Material("Fragmento de Obsidiana", "Rocha Vulcânica", 0.7, 2, 60);
            default:
                return null;
        }
    }

    //Getters and Setters
    public String getTerrenoEspecifico() { return terrenoEspecifico; }
    public void setTerrenoEspecifico(String terrenoEspecifico) { this.terrenoEspecifico = terrenoEspecifico; }
    public String getVegetacaoTipica() { return vegetacaoTipica; }
    public void setVegetacaoTipica(String vegetacaoTipica) { this.vegetacaoTipica = vegetacaoTipica; }

    //Interface
    @Override
    public void explorarInterface(Personagem jogador, JTextArea areaLog) {
        areaLog.append("Você começa a escalar os terrenos íngremes e gélidos das Montanhas de Vhaldrak...\n");
        areaLog.append("O ar é rarefeito, e o vento frio corta o rosto como lâminas de gelo.\n");
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
            JOptionPane.showMessageDialog(null, "As montanhas estavam silenciosas... nada foi encontrado desta vez.");
        }

        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        gerenciadorEventos.aplicarEventoAleatorioPorAmbienteInterface(jogador, areaLog);
    }

    @Override
    public String getTipoImagem() {
        return "montanha";
    }

}
