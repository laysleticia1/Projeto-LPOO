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
import Gerenciadores.*;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

public class Caverna extends Ambiente {
    private String luminosidade;
    private String criaturas;
    private String hidratacao;

    //Construtor
    public Caverna() {
        super("Caverna", "Local subterrâneo, escuro e silencioso. Túneis estreitos e paredes úmidas escondem rochas valiosas, minérios raros, mas também perigos ocultos. Um ambiente hostil, mas promissor.", 7, new ArrayList<>(Arrays.asList("Rochas e minérios raros")), 0.65, "Úmido e escuro");
        this.luminosidade = "Pouca luz";
        this.criaturas = "Presença de criaturas desconhecidas...";
        this.hidratacao = "Água de gotejamento";

    }

    public void explorar(Personagem jogador) {
        System.out.println("\nVocê adentra a escuridão úmida de uma caverna profunda...");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        Item recurso = coletarItemAleatorio();
        if (recurso != null) {
            System.out.println("\nVocê encontrou: " + recurso.getNome());
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

        int opcao = (int) (Math.random() * 4);
        switch (opcao) {
            case 0:
                return new Ferramentas("Lanterna Improvisada", 1.5, 3, 30);
            case 1:
                return new Material("Cristal de Caverna", "Mineral", 0.8, 2, 90);
            case 2:
                return new Agua("Gotejamento de Rocha", 0.4, 1, "Duvidosa", 0.5, 0.7);
            case 3:
                return new Armas("Picareta Afiada", 2.0, 4, "Pesada", 25, 1);
            default:
                return null;
        }
    }
}
