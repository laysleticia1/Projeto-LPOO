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
    @Override
    public void explorar(Personagem jogador) {
        System.out.println("Você escala encostas rochosas em busca de algo útil...");

        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        if (Math.random() < 0.4) {
            Item recurso = new Material("Fragmento de Rocha Rara", 1.2, 3, 80);
            jogador.adicionarAoInventario(recurso);
            System.out.println("Você encontrou: " + recurso.getNome());
        } else {
            System.out.println("As pedras não revelaram nada útil desta vez.");
        }

        if (Math.random() < this.getProbabilidadeEventos()) {
            Evento evento = new EventoClimatico(
                    "Nevasca",
                    "Uma nevasca repentina cobre tudo ao redor.",
                    0.25,
                    "Reduz energia",
                    "Montanha",
                    "Nevasca",
                    2,
                    "Você precisa buscar abrigo ou sofrerá congelamento."
            );
            if (evento.podeOcorrerNoAmbiente(this)) evento.executar(jogador, this);
        }
    }
    @Override
    public Item coletarItemAleatorio() {
        int opcao = (int) (Math.random() * 3);
        switch (opcao) {
            case 0:
                return new Material("Rocha Metálica", 1.8, 4, 75);
            case 1:
                return new Ferramentas("Pederneira", 0.7, 5, 40);
            default:
                return new Agua("Água de Degelo", 1.0, 1, "Potável", 1.0);
        }
    }

}
