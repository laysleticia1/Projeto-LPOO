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

public class Ruinas extends Ambiente {
    private String tipoDeEstrutura;
    private String misteriosPresentes;

    public Ruinas() {
        super("Ruínas de Thargor", "Local marcado por vestígios de uma civilização antiga, envoltos em ecos e enigmas. Há estruturas em ruínas, artefatos valiosos e registros esquecidos por toda parte, mas portas surgem e somem, e visões distorcem a realidade.", 6, new ArrayList<>(Arrays.asList("Artefatos Antigos", "Fragmentos de Escrita", "Poeira Mágica")), 0.55, "Seco, com ventos uivantes entre as estruturas", "/Resources/Ambientes/ruina.png");
        this.tipoDeEstrutura = "Templos desmoronados, torres quebradas e passagens subterrâneas.";
        this.misteriosPresentes = "Inscrições em uma língua esquecida, armadilhas antigas e ecos do passado.";
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("\nVocê explora as ruínas silenciosas de uma era esquecida...");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());
        Item recurso = coletarItemAleatorio();
        if (recurso != null) {
            System.out.println("\nVocê encontrou: " + recurso.getNome());
            // ... (lógica de detalhes e coleta)
        } else {
            System.out.println("\nAs ruínas não revelaram nada de valor desta vez.");
        }
        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        gerenciadorEventos.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.4) return null;

        int opcao = (int) (Math.random() * 5);
        switch (opcao) {
            case 0: return new Ferramentas("Ferramenta de Escavação Antiga", 1.8, 3, 45);
            case 1: return new Material("Fragmento de Artefato", "Relíquia", 0.5, 1, 60);
            case 2: return new Agua("Água empoeirada de um Cântaro Quebrado", 0.3, 1, "Sede", 0.2, 0.8);
            case 3: return new Remedios("Pó Brilhante Misterioso", "Desconhecido", "Efeito incerto, pode ser bom ou ruim.");
            case 4: return new Armas("Adaga Cerimonial Lascada", 0.8, 2, "Curta", 18, 1);
            default: return null;
        }
    }

    //Getters and Setters
    public String getTipoDeEstrutura() { return tipoDeEstrutura; }
    public String getMisteriosPresentes() { return misteriosPresentes; }

    //Interface
    @Override
    public void explorarInterface(Personagem jogador, JTextArea areaLog) {
        areaLog.append("Você caminha entre os escombros antigos das ruínas...\n");
        areaLog.append("As pedras estão cobertas de musgo e inscrições antigas brilham levemente sob a luz.\n");
        areaLog.append("O silêncio é quebrado por corvos distantes, e o ar é carregado de memórias esquecidas.\n");
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
            JOptionPane.showMessageDialog(null, "As ruínas guardaram seus segredos desta vez...");
        }

        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        gerenciadorEventos.aplicarEventoAleatorioPorAmbienteInterface(jogador, areaLog);
    }

    @Override
    public String getTipoImagem() {
        return "ruina";
    }

}
