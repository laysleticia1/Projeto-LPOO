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

public class LagoRio extends Ambiente {
    private String hidratacao;
    private String pesca;
    private String terreno;

    //Construtor
    public LagoRio () {
        super("Lago/Rio", "Local de águas calmas e cristalinas. Um lago ou rio que oferece oportunidades de hidratação, pesca e travessia. A presença de peixes e algas torna o ambiente fértil e estratégico.", 2, new ArrayList<>(Arrays.asList("Água, peixes e algas")), 0.4, "Úmido e fresco");
        this.hidratacao = "Água potável, mas existem regiões contaminadas...";
        this.pesca = "Rica fonte de peixes";
        this.terreno = "Plano, bonito e escorregadio nas margens";

    }
    public void explorar(Personagem jogador) {
        System.out.println("\nVocê caminha pela margem do lago tranquilo...");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        Item recurso = coletarItemAleatorio();
        if (recurso != null) {
            System.out.println("\nVocê encontrou: " + recurso.getNome());
            System.out.println("Deseja adicionar ao inventário? (s/n)");
            Scanner sc = new Scanner(System.in);
            if (sc.nextLine().trim().equalsIgnoreCase("s")) {
                jogador.adicionarAoInventario(recurso);
            } else {
                System.out.println("Você deixou o item para trás.");
            }
        } else {
            System.out.println("\nNenhum recurso encontrado desta vez.");
        }

        GerenciadorDeEventos gerenciador = new GerenciadorDeEventos();
        gerenciador.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.5) return null;

        int opcao = (int) (Math.random() * 4);
        switch (opcao) {
            case 0:
                return new Agua("Água do Lago", 1.2, 1, "Contaminada", 2.0, 0.4);
            case 1:
                return new Alimentos("Peixe Cru", 1.0, 2, 300, "Carne", 1);
            case 2:
                return new Material("Corda Natural", "Fibra Vegetal", 0.6, 3, 40);
            case 3:
                return new Ferramentas("Rede de Pesca", 1.3, 4, 55);
            case 4:
                return new Armas("Adaga Enferrujada", 1.2, 4, "Curta", 15, 1);
            default:
            return null;
        }
    }

    //Getters and Setters

    public String getHidratacao() {
        return hidratacao;
    }

    public void setHidratacao(String hidratacao) {
        this.hidratacao = hidratacao;
    }

    public String getPesca() {
        return pesca;
    }

    public void setPesca(String pesca) {
        this.pesca = pesca;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }
}
