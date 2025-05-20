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
        System.out.println("Você caminha pela floresta úmida, cercado por vegetação densa...");

        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        if (Math.random() < 0.7) {
            Item recurso = new Alimentos("Raízes Nutritivas", 0.4, 2, 200, "Raiz", 3);
            jogador.adicionarAoInventario(recurso);
            System.out.println("Você encontrou: " + recurso.getNome());
        } else {
            System.out.println("A busca por recursos na floresta foi em vão.");
        }

        if (Math.random() < this.getProbabilidadeEventos()) {
            Evento evento = new EventoCriatura(
                    "Emboscada de Jaguatirica",
                    "Uma jaguatirica salta dos arbustos!",
                    0.2,
                    "Ataque físico",
                    "Floresta",
                    "Criatura",
                    3,
                    "Ela ataca antes de desaparecer na vegetação."
            );
            if (evento.podeOcorrerNoAmbiente(this)) evento.executar(jogador, this);
        }
    }

    @Override
    public Item coletarItemAleatorio() {
        int opcao = (int) (Math.random() * 3);
        switch (opcao) {
            case 0:
                return new Alimentos("Cogumelo Nutritivo", 0.3, 1, 150, "Fungo", 2);
            case 1:
                return new Alimentos("Fruta Selvagem", 0.2, 2, 100, "Fruta", 3);
            default:
                return new Material("Galho de Árvore", 0.5, 3, 30);
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

