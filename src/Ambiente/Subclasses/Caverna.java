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

public class Caverna extends Ambiente {
    private String luminosidade;
    private String criaturas;
    private String hidratacao;

    //Construtor
    public Caverna () {
        super("Caverna", "Local subterrâneo, escuro e silencioso. Túneis estreitos e paredes úmidas escondem rochas valiosas, minérios raros, mas também perigos ocultos. Um ambiente hostil, mas promissor.", 7, new ArrayList<>(Arrays.asList("Rochas e minérios raros")), 0.65, "Úmido e escuro" );
       this.luminosidade = "Pouca luz";
       this.criaturas = "Presença de criaturas desconhecidas...";
       this.hidratacao = "Água de gotejamento";

    }
    @Override
    public void explorar(Personagem jogador) {
        System.out.println("Você adentra a escuridão úmida de uma caverna profunda...");

        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        if (Math.random() < 0.5) {
            Item recurso = new Ferramentas("Picareta Improvisada", 2.5, 4, 65);
            jogador.adicionarAoInventario(recurso);
            System.out.println("Você encontrou: " + recurso.getNome());
        } else {
            System.out.println("A caverna não revelou nada desta vez.");
        }

        if (Math.random() < this.getProbabilidadeEventos()) {
            Evento evento = new EventoCriatura(
                    "Desabamento Parcial",
                    "As paredes da caverna começam a ruir!",
                    0.3,
                    "Desmoronamento",
                    "Caverna",
                    "Ambiente",
                    4,
                    "Você precisa fugir rapidamente ou será soterrado."
            );
            if (evento.podeOcorrerNoAmbiente(this)) evento.executar(jogador, this);
        }
    }
    @Override
    public Item coletarItemAleatorio() {
        int opcao = (int) (Math.random() * 3);
        switch (opcao) {
            case 0:
                return new Ferramentas("Lanterna Improvisada", 1.5, 3, 30);
            case 1:
                return new Material("Cristal de Caverna", 0.8, 2, 90);
            default:
                return new Agua("Gotejamento de Rocha", 0.4, 1, "Duvidosa", 0.5);
        }
    }

}
