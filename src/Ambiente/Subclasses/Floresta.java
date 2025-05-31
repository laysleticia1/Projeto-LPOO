package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;
import Evento.Superclasse.*;
import Evento.Subclasses.*;
import Personagem.Superclasse.*;
import Personagem.Subclasses.*;
import Item.Superclasse.*;
import Item.Subclasses.*;
import Criatura.Subclasses.*;
import Gerenciadores.*;
import java.util.Scanner;


public class Floresta extends Ambiente {
    private String vegetacao;
    private String fauna;
    private String clima;

    //Construtor
    public Floresta () {
        super("Floresta", "Local coberto por uma vegetação densa e viva, repleta de árvores altas e sons constantes da natureza. Criaturas selvagens habitam essa região rica em recursos como frutas, madeira e ervas medicinais.", 2, new ArrayList<>(Arrays.asList("Frutas, Madeira, Ervas Medicinais")), 0.4, "Chuvoso");
        this.vegetacao = "Vegetação densa e úmida";
        this.fauna = "Lobos, cervos, passáros exóticos";
        this.clima = "Chuvoso";
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
            Scanner scanner = new Scanner(System.in);
            String resposta = scanner.nextLine().trim().toLowerCase();
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
            System.out.println("\nNada útil foi encontrado na floresta.");
        }

        GerenciadorDeEventos gerenciador = new GerenciadorDeEventos();
        gerenciador.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.4) return null;

        int opcao = (int) (Math.random() * 9);
        switch (opcao) {
            case 0:
                return new Alimentos("Cogumelo Nutritivo", 0.3, 1, 20, "Fungo", 2);
            case 1:
                return new Alimentos("Nozes Silvestres", 0.4, 3, 12, "Semente", 5);
            case 2:
                return new Material("Galho de Árvore", "Madeira", 0.5, 3, 30);
            case 3:
                return new Agua("Coco Verde", 1.2, 3, "Alta", 0.5, 0.0);
            case 4:
                return new Remedios("Pomada de Folhas", "Fitoterápico", "Cura pequenos ferimentos e alivia irritações");
            case 5:
                return new Armas("Faca de Caça", 1.0, 4, "Curta", 20, 2);
            case 6:
                return new Alimentos("Bagas Amargas", 0.2, 2, -10, "Fruta", 1);
            case 7:
                return new Alimentos("Mel Silvestre", 0.3, 5, 20, "Doce Natural", 10);
            case 8:
                return new Material("Sucata", "Metal Enferrujado", 1.0, 3, 40);
            default:
            return null;
        }
    }

    //Setter e Getters
    public String getVegetacao() {
        return vegetacao;
    }

    public void setVegetacao(String vegetacao) {
        this.vegetacao = vegetacao;
    }

    public String getFauna() {
        return fauna;
    }

    public void setFauna(String fauna) {
        this.fauna = fauna;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
        setCondicaoClimatica(clima);
    }
}

