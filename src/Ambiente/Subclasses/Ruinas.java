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
        System.out.println("Explorando as densas ruinas...");


        // Custo de energia baseado na dificuldade
        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        // 60% de chance de encontrar recurso
        if (Math.random() < 0.6) {
            Item recurso = this.coletarItemAleatorio();
            jogador.adicionarAoInventario(recurso);
            System.out.println("Você encontrou: " + recurso.getNome());

        } else {
            System.out.println("Nada foi encontrado entre as árvores...");
        }

        // 30% de chance de evento ocorrer
        if (Math.random() < this.getProbabilidadeEventos()) {
            Evento evento = new EventoCriatura(
                    "Ataque de Lobo",
                    "Uma alcateia de lobos surge entre os escombros.",
                    0.3,
                    "Ataque físico",
                    "Ruínas",
                    "Criatura",
                    2,
                    "Os lobos cercam e avançam sobre o jogador."
            );

            if (evento.podeOcorrerNoAmbiente(this)) {
                evento.executar(jogador, this);
            }
        }
    }

    @Override
    public Item coletarItemAleatorio() {
        int opcao = (int) (Math.random() * 6); // 0 a 5, total de 6 itens
        switch (opcao) {
            case 0:
                return new Agua("Água Purificada", 0.5, 1, "Alta", 1.0);
            case 1:
                return new Alimentos("Barrinha de Cereal", 0.3, 2, 250, "Doce", 10);
            case 2:
                return new Armas("Adaga Enferrujada", 1.2, 4, "Curta", 15, 1);
            case 3:
                return new Ferramentas("Martelo de Manutenção", 2.0, 6, 75);
            case 4:
                return new Material("Placa de Circuito", 0.8, 3, 60);
            case 5:
                return new Remedios("Analgésico", "Reduz dor por tempo limitado");
            default:
                return new Agua("Água Purificada", 0.5, 1, "Alta", 1.0); // fallback
        }
    }


}
