package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import Item.Superclasse.Item;
import Item.Subclasses.*;
import Gerenciadores.GerenciadorDeEventos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LagoRio extends Ambiente {
    private String qualidadeAgua;
    private String vidaAquatica;
    private String caracteristicaTerrenoMargem;

    public LagoRio() {
        super("Lago Sereno",
                "Local de águas calmas e cristalinas. Um lago ou rio que oferece oportunidades de hidratação, pesca e travessia. A presença de peixes e algas torna o ambiente fértil e estratégico.",
                2, // Dificuldade baixa
                new ArrayList<>(Arrays.asList("Água Fresca", "Peixes Comestíveis", "Algas Nutritivas", "Pedras Lisas")),
                0.35, // Probabilidade de Eventos
                "Ameno e Refrescante",
                "/Resources/Ambientes/lagorio.png"); // Caminho da imagem
        this.qualidadeAgua = "Majoritariamente potável, mas algumas áreas podem estar estagnadas.";
        this.vidaAquatica = "Cardumes de peixes pequenos, alguns peixes maiores e plantas aquáticas.";
        this.caracteristicaTerrenoMargem = "Areia fina e seixos rolados, com vegetação ribeirinha.";
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("\nVocê caminha pela margem serena do " + getNome().toLowerCase() + "...");
        jogador.diminuirEnergia(this.getDificuldadeExploracao());
        Item recurso = coletarItemAleatorio();
        if (recurso != null) {
            System.out.println("\nVocê encontrou: " + recurso.getNome());
            // ... (lógica de detalhes e coleta)
        } else {
            System.out.println("\nNenhum recurso encontrado desta vez próximo à água.");
        }
        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        gerenciadorEventos.aplicarEventoAleatorioPorAmbiente(jogador);
    }

    @Override
    public Item coletarItemAleatorio() {
        double chanceEncontrar = Math.random();
        if (chanceEncontrar < 0.3) return null; // Chance um pouco maior de encontrar algo

        int opcao = (int) (Math.random() * 5);
        switch (opcao) {
            case 0: return new Agua("Água Cristalina do Lago", 1.5, 1, "Pura", 2.0, 0.01);
            case 1: return new Alimentos("Peixe Fresco Pequeno", 0.7, 2, 25, "Carne", 1);
            case 2: return new Material("Pedra Lisa de Rio", "Pedra", 0.4, 3, 20);
            case 3: return new Ferramentas("Vara de Pesca Improvisada", 0.8, 2, 40);
            case 4: return new Alimentos("Algas Verdes Nutritivas", 0.2, 1, 8, "Vegetal", 2);
            default: return null;
        }
    }

    public String getQualidadeAgua() { return qualidadeAgua; }
    public String getVidaAquatica() { return vidaAquatica; }
    public String getCaracteristicaTerrenoMargem() { return caracteristicaTerrenoMargem; }
}
