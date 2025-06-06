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

public class LagoRio extends Ambiente {
    private String qualidadeAgua;
    private String vidaAquatica;
    private String caracteristicaTerrenoMargem;

    public LagoRio() {
        super("Planícies de Myndros", "Um paraíso aquático sereno, mas sob as águas calmas espreitam redemoinhos, toxinas e predadores invisíveis. Rica em peixes e pontos de hidratação, é um lugar fértil — e mortal para os despreparados.", 2, new ArrayList<>(Arrays.asList("Água Fresca", "Peixes Comestíveis", "Algas Nutritivas", "Pedras Lisas")), 0.35, "Ameno e Refrescante", "/Resources/Ambientes/lagorio.png");
        this.qualidadeAgua = "Majoritariamente potável, mas algumas áreas podem estar estagnadas.";
        this.vidaAquatica = "Cardumes de peixes pequenos, alguns peixes maiores e plantas aquáticas.";
        this.caracteristicaTerrenoMargem = "Areia fina e seixos rolados, com vegetação ribeirinha.";
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("\nVocê caminha pela margem serena do " + getNome().toLowerCase() + "...");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());
        Item recurso = coletarItemAleatorio();
        if (recurso != null) {
            System.out.println("\nVocê encontrou: " + recurso.getNome());
        } else {
            System.out.println("\nNenhum recurso encontrado desta vez próximo à água.");
        }
        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        gerenciadorEventos.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public void explorarInterface(Personagem jogador, JTextArea areaLog) {
        areaLog.append("Você se aproxima das margens do lago e observa o brilho da água em movimento...\n");
        areaLog.append("Pequenos redemoinhos se formam entre as pedras, enquanto pássaros pescam ao longe.\n");

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
            JOptionPane.showMessageDialog(null, "O lago hoje está calmo... não havia nada por perto.");
        }

        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        gerenciadorEventos.aplicarEventoAleatorioPorAmbienteInterface(jogador, areaLog);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.3) return null;

        int opcao = (int) (Math.random() * 5);
        switch (opcao) {
            case 0: return new Agua("Água Cristalina do Lago", 1.5, 1, "Pura", 2.0, 0.01);
            case 1: return new Alimentos("Peixe Fresco Pequeno", 0.7, 2, 25, "Carne", 1);
            case 2: return new Material("Pedra Lisa de Rio", "Pedra", 0.4, 3, 20);
            case 3: return new Ferramentas("Vara de Pesca Improvisada", 0.8, 2, 40);
            case 4: return new Alimentos("Algas Verdes Nutritivas", 0.2, 1, 8, "Vegetal", 2);
            default: return null;
        }
    }
    //Getters and Setters
    public String getQualidadeAgua() { return qualidadeAgua; }
    public String getVidaAquatica() { return vidaAquatica; }
    public String getCaracteristicaTerrenoMargem() { return caracteristicaTerrenoMargem; }

    //Interface
    @Override
    public String getTipoImagem() {
        return "lagorio";
    }


}
