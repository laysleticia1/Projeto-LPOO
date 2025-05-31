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
        System.out.println("Você caminha pela margem do lago tranquilo...");
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
            } else {
                System.out.println("- Peso: " + recurso.getPeso());
                System.out.println("- Durabilidade: " + recurso.getDurabilidade());
            }            System.out.println("Deseja adicionar ao inventário? (s/n)");
            Scanner sc = new Scanner(System.in);
            if (sc.nextLine().trim().equalsIgnoreCase("s")) {
                jogador.adicionarAoInventario(recurso);
            } else {
                System.out.println("Você deixou o item para trás.");
            }
        } else {
            System.out.println("Nenhum recurso encontrado desta vez.");
        }

        GerenciadorDeEventos gerenciador = new GerenciadorDeEventos();
        gerenciador.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.5) return null;

        int opcao = (int) (Math.random() * 7);
        switch (opcao) {
            case 0:
                return new Agua("Água do Lago", 1.2, 1, "Contaminada", 2.0, 0.4);
            case 1:
                return new Alimentos("Peixe Cru Contaminado", 0.9, 2, -15, "Carne", 1); // alimento ruim
            case 2:
                return new Material("Corda Natural", "Fibra Vegetal", 0.6, 3, 40);
            case 3:
                return new Ferramentas("Rede de Pesca", 1.3, 4, 55);
            case 4:
                return new Armas("Adaga Enferrujada", 1.2, 4, "Curta", 15, 1);
            case 5:
                return new Alimentos("Raízes Aquáticas", 0.5, 2, 8, "Raiz", 3);
            case 6:
                return new Alimentos("Ovos de Pássaros Riberinhos", 0.3, 2, 10, "Ovo", 2);
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
