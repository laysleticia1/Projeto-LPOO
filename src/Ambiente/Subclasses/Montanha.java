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

public class Montanha extends Ambiente {
    private String terreno;
    private String clima;
    private String vegetacao;

    //Construtor
    public Montanha () {
        super("Montanha", "Local elevado e desafiador. A montanha apresenta terreno acidentado, variações bruscas de temperatura e ventos intensos. Esconde valiosos minérios e pedras preciosas entre os riscos naturais.", 8, new ArrayList<>(Arrays.asList("Minérios e pedras preciosas")), 0.6, "Frio e com ventos fortes");
        this.terreno = "Rochoso e íngreme";
        this.clima = "Grandes variações de temperatura";
        this.vegetacao = "Escassa mas resiste";
    }
    public void explorar(Personagem jogador) {
        System.out.println("\nVocê sobe pelas trilhas íngremes da montanha...");

        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        Item itemEncontrado = coletarItemAleatorio();
        if (itemEncontrado != null) {
            System.out.println("\nVocê encontrou um item: " + itemEncontrado.getNome());
            System.out.println("Deseja coletar esse item? (s/n)");
            Scanner scanner = new Scanner(System.in);
            String escolha = scanner.nextLine();
            if (escolha.equalsIgnoreCase("s")) {
                jogador.adicionarAoInventario(itemEncontrado);
            } else {
                System.out.println("Você decidiu deixar o item para trás.");
            }
        } else {
            System.out.println("\nHoje não foi possível encontrar nenhum item útil na montanha.");
        }

        GerenciadorDeEventos gerenciador = new GerenciadorDeEventos();
        gerenciador.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.2) return null; // 20% de chance de não encontrar nada

        int opcao = (int) (Math.random() * 6); // aumentamos para 6 opções
        switch (opcao) {
            case 0:
                return new Material("Rocha Metálica", "Mineral", 1.8, 4, 75);
            case 1:
                return new Ferramentas("Pederneira", 0.7, 5, 40);
            case 2:
                return new Agua("Água de Degelo", 1.0, 1, "Potável", 1.0, 0.6);
            case 3:
                return new Ferramentas("Bastão de Escalada", 1.2, 3, 60);
            case 4:
                return new Remedios("Pomada Térmica", "Térmico", "Alivia queimaduras e dores musculares");
            case 5:
                return new Armas("Faca de Sobrevivência", 1.5, 4, "Curta", 20, 1);
            default:
                return null;
        }
    }



}
