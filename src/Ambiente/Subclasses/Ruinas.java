package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;
import Evento.Subclasses.*;
import Personagem.Superclasse.*;
import Personagem.Subclasses.*;
import Item.Superclasse.*;
import Item.Subclasses.*;
import java.util.List;
import java.util.Random;
import Evento.Subclasses.*;
import Evento.Superclasse.*;
import Gerenciadores.*;
import java.util.Scanner;

public class Ruinas extends Ambiente {
    private String estrutura;
    private String sobreviventes;
    private String clima;

    //Construtor
    public Ruinas() {
        super("Ruínas", "Local marcado por vestígios de uma civilização antiga. Estruturas em ruínas, inscrições enigmáticas e artefatos esquecidos compõem o cenário. Um ambiente misterioso e potencialmente perigoso.", 6, new ArrayList<>(Arrays.asList("Artefatos antigos, Estruturas colapsadas, inscrições misteriosas")), 0.6, "Seco, com fortes ventos");
        this.estrutura = "Estruturas dos antepassados que jaziam nessas terras";
        this.sobreviventes = "Sinais de antigos habitantes ou exploradores";
        this.clima = "Seco e empoeirado, com fortes ventos";

    }

    public void explorar(Personagem jogador) {
        System.out.println("\nVocê adentra a escuridão úmida de uma caverna profunda...");
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
            } else if (recurso instanceof Remedios r) {
                System.out.println("- Nome: " + r.getNome());
                System.out.println("- Tipo: " + r.getTipo());
                System.out.println("- Efeito: " + r.getEfeito());
            } else if (recurso instanceof Alimentos alimento) {
                System.out.println("- Tipo: " + alimento.getTipo());
                System.out.println("- Peso: " + alimento.getPeso() + " kg");
                System.out.println("- Valor Nutricional: " + alimento.getValorNutricional());
                System.out.println("- Validade: " + alimento.getValidade() + " dia/s");
                System.out.println("- Durabilidade: " + alimento.getDurabilidade());
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
            System.out.println("\nA caverna não revelou nada desta vez.");
        }

        GerenciadorDeEventos gerenciador = new GerenciadorDeEventos();
        gerenciador.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.4) return null;

        int opcao = (int) (Math.random() * 7); // aumentamos para 5 opções
        switch (opcao) {
            case 0:
                return new Ferramentas("Lanterna Improvisada", 1.5, 3, 30);
            case 1:
                return new Material("Cristal de Caverna", "Mineral", 0.8, 2, 90);
            case 2:
                return new Agua("Água de Gotejamento de Rocha", 0.4, 1, "Duvidosa", 0.5, 0.7);
            case 3:
                return new Remedios("Elixir Antigo", "Histórico", "Restaura parte da vida e da sanidade");
            case 4:
                return new Armas("Espada Cerimonial", 2.5, 3, "Longa", 30, 2);
            case 5:
                return new Alimentos("Raiz Ressecada", 0.5, 2, 10, "Raiz", 6);
            case 6:
                return new Alimentos("Resto de Conserva Apodrecida", 0.5, 1, -8, "Restos", 1);
            default:
            return null;
        }
    }

}
