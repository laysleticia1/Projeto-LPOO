package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import Item.Superclasse.Item;
import Item.Subclasses.*;
import Gerenciadores.GerenciadorDeEventos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ruinas extends Ambiente {
    private String tipoDeEstrutura;
    private String misteriosPresentes;

    public Ruinas() {
        super("Ruínas Ancestrais",
                "Local marcado por vestígios de uma civilização antiga. Estruturas em ruínas, inscrições enigmáticas e artefatos esquecidos compõem o cenário. Um ambiente misterioso e potencialmente perigoso.",
                6,
                new ArrayList<>(Arrays.asList("Artefatos Antigos", "Fragmentos de Escrita", "Poeira Mágica")),
                0.55, // Probabilidade de evento ligeiramente menor
                "Seco, com ventos uivantes entre as estruturas",
                "/Resources/Ambientes/ruina.png"); // Caminho da imagem
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

    public String getTipoDeEstrutura() { return tipoDeEstrutura; }
    public String getMisteriosPresentes() { return misteriosPresentes; }
}
