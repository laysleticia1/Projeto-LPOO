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

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("Você anda pela margem lamacenta de um rio tranquilo...");

        jogador.diminuirEnergia(this.getDificuldadeExploracao());

        if (Math.random() < 0.65) {
            Item recurso = new Agua("Água do Rio", 1.0, 1, "Contaminada", 1.5);
            jogador.adicionarAoInventario(recurso);
            System.out.println("Você coletou: " + recurso.getNome());
        } else {
            System.out.println("Nada foi encontrado nas águas do rio.");
        }

        if (Math.random() < this.getProbabilidadeEventos()) {
            Evento evento = new EventoCriatura(
                    "Ataque de Piranhas",
                    "Enquanto se aproxima da água, piranhas avançam!",
                    0.3,
                    "Ferimentos",
                    "LagoRio",
                    "Criatura",
                    3,
                    "Você sofre cortes e deve se afastar rapidamente."
            );
            if (evento.podeOcorrerNoAmbiente(this)) evento.executar(jogador, this);
        }
    }
    @Override
    public Item coletarItemAleatorio() {
        int opcao = (int) (Math.random() * 3);
        switch (opcao) {
            case 0:
                return new Agua("Água do Lago", 1.2, 1, "Contaminada", 2.0);
            case 1:
                return new Alimentos("Peixe Cru", 1.0, 2, 300, "Carne", 1);
            default:
                return new Material("Corda Natural", 0.6, 3, 40);
        }
    }


    //Getters e Setters específicos

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
