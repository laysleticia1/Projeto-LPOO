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

        int opcao = (int) (Math.random() * 6);
        switch (opcao) {
            case 0:
                return new Alimentos("Cogumelo Nutritivo", 0.3, 1, 150, "Fungo", 2);
            case 1:
                return new Alimentos("Fruta Selvagem", 0.2, 2, 100, "Fruta", 3);
            case 2:
                return new Material("Galho de Árvore", "Madeira", 0.5, 3, 30);
            case 3:
                return new Agua("Coco Verde", 1.2, 3, "Alta", 0.5, 0.0);
            case 4:
                return new Remedios("Pomada de Folhas", "Fitoterápico", "Cura pequenos ferimentos e alivia irritações");
            case 5:
                return new Armas("Faca de Caça", 1.0, 4, "Curta", 20, 2);
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

