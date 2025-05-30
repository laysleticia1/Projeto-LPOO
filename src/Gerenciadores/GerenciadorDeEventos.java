package Gerenciadores;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
import Criatura.Subclasses.*;
import Evento.Subclasses.EventoClimatico;
import Evento.Subclasses.EventoDescoberta;
import Evento.Subclasses.EventoDoencaFerimento;
import Evento.Subclasses.Específicos.*;
import Evento.Subclasses.EventoCriatura;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class GerenciadorDeEventos {
    private ArrayList<Evento> eventosDisponiveis = new ArrayList<>();
    private ArrayList<String> historicoDeEventos = new ArrayList<>();
    private Random sorteador = new Random();

    public void adicionarEvento(Evento evento) {
        eventosDisponiveis.add(evento);
    }

    public void mostrarHistoricoDeEventos() {
        System.out.println("Histórico de eventos:");
        if (historicoDeEventos.isEmpty()) {
            System.out.println("Nenhum evento ocorreu até agora.");
        } else {
            for (String nome : historicoDeEventos) {
                System.out.println("- " + nome);
            }
        }
    }

    public void aplicarEventoAleatorioPorAmbiente(Personagem jogador) {
        String nomeAmbiente = jogador.getAmbienteAtual().getClass().getSimpleName();
        List<Evento> eventos = eventosPorAmbiente.get(nomeAmbiente);

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Você explora, mas nada acontece por aqui...\n");
            return;
        }

        // 20% de chance de nenhum evento acontecer
        if (new Random().nextDouble() < 0.2) {
            System.out.println("Você explora com cautela... mas tudo está calmo.\n");
            return;
        }

        Evento eventoEscolhido = eventos.get(new Random().nextInt(eventos.size()));
        System.out.println("\n🔸 Um evento inesperado acontece...\n");
        eventoEscolhido.executar(jogador, jogador.getAmbienteAtual());
        historicoDeEventos.add(eventoEscolhido.getNomeEvento());
    }

    private Map<String, List<Evento>> eventosPorAmbiente = new HashMap<>();

    public GerenciadorDeEventos() {
        inicializarEventosPorAmbiente();
    }

    private void inicializarEventosPorAmbiente() {
        eventosPorAmbiente = new HashMap<>();

        eventosPorAmbiente.put("Caverna", new ArrayList<>(List.of(
                new EventoCriatura("Morcego Gigante", "Você escuta um chiado agudo... Um morcego enorme desce do teto em sua direção!", 0.5, "vida", "Caverna", new MorcegoGigante(), 1, "Ataque aéreo"),
                new EventoCriatura("Rato Mutante", "Nos cantos escuros, um rato com olhos brilhantes avança rosnando. Algo está errado com ele.", 0.4, "vida", "Caverna", new RatoMutante(), 1, "Morde com toxinas"),
                new EventoDescoberta("Fóssil Luminoso", "Ao iluminar a parede com sua lanterna, você vê fósseis que brilham com uma luz tênue e azulada.", 0.4, "inventario", "Caverna", "Fóssil", "Minério luminoso", "Necessário picareta"),
                new EventoDoencaFerimento("Gás Tóxico", "Ao avançar, um gás verde começa a sair de uma fenda no chão. Você tenta prender a respiração...", 0.5, "sanidade", "Caverna", "Respiratório", "Antídoto raro"),
                new EventoClimatico("Desabamento", "De repente, um estrondo ecoa e as paredes da caverna começam a ruir sobre você!", 0.3, "vida", "Caverna", "Queda", 1, "Impossibilita locomoção")
        )));

        eventosPorAmbiente.put("Floresta", new ArrayList<>(List.of(
                new EventoCriatura("Cobra", "Entre os galhos baixos, uma cobra escorrega até quase tocar seus pés. É venenosa!", 0.3, "vida", "Floresta", new Cobra(), 1, "Veneno paralisante"),
                new EventoCriatura("Javali", "Você escuta um grunhido e, antes que possa reagir, um javali investe com fúria pela trilha!", 0.3, "vida", "Floresta", new Javali(), 2, "Investida poderosa"),
                new EventoCriatura("Lobo", "Olhos brilhantes entre as árvores. Um lobo surge e rosna ferozmente... ele não está sozinho.", 0.4, "vida", "Floresta", new Lobo(), 2, "Ataca em bando"),
                new EventoDescoberta("Cabana Abandonada", "Em meio à vegetação, surge a silhueta de uma antiga cabana. Lá dentro, vestígios de sobreviventes.", 0.5, "inventario", "Floresta", "Abrigo", "Itens de sobrevivência", "Requer lanterna"),
                new EventoDoencaFerimento("Picada de Inseto", "Insetos quase imperceptíveis picam sua pele, causando uma ardência intensa e febre.", 0.4, "vida", "Floresta", "Cutânea", "Pomada natural"),
                new EventoCriatura("Emboscada de Jaguatirica", "Um som rápido nos galhos acima... A jaguatirica salta e tenta atacar antes de desaparecer na mata!", 0.5, "vida", "Floresta", new Jaguatirica(), 2, "Ataque antes de desaparecer"),
                new EnchenteRapida(),
                new PoeiraToxica(),
                new EmboscadaLobos()
        )));

        eventosPorAmbiente.put("LagoRio", new ArrayList<>(List.of(
                new EventoCriatura("Piranha", "Você pisa na água e logo sente picadas rápidas. Piranhas famintas cercam seus pés!", 0.4, "vida", "LagoRio", new Piranha(), 1, "Ataque em cardume"),
                new EventoCriatura("Sanguessuga", "Algo viscoso gruda em sua perna enquanto você caminha na água rasa. Está sugando sua energia!", 0.4, "vida", "LagoRio", new Sanguessuga(), 1, "Drena energia lentamente"),
                new EventoCriatura("Jacaré", "A superfície do lago se rompe e um enorme jacaré avança das águas escuras!", 0.5, "vida", "LagoRio", new Jacare(), 3, "Mordida surpresa"),
                new EventoDescoberta("Tesouro Submerso", "Na margem, algo metálico brilha sob a lama. Um baú antigo!", 0.4, "inventario", "LagoRio", "Tesouro", "Moedas antigas", "Requer nadar"),
                new EventoDoencaFerimento("Água Contaminada", "A sede vence o nojo, mas logo após beber a água turva, seu estômago se revira.", 0.3, "sanidade", "LagoRio", "Gastrointestinal", "Carvão ativado"),
                new EventoClimatico("Cheia Súbita", "O nível da água começa a subir rapidamente. Uma cheia inesperada está vindo das montanhas!", 0.3, "energia", "LagoRio", "Inundação", 1, "Dificulta movimentação")
        )));

        eventosPorAmbiente.put("Montanha", new ArrayList<>(List.of(
                new EventoCriatura("Lobo da Neve", "Camuflado no branco da montanha, um lobo o observa antes de se aproximar lentamente.", 0.4, "vida", "Montanha", new Lobo(), 2, "Camuflagem na neve"),
                new EventoCriatura("Urso", "O silêncio é rompido por um rugido. Um urso colossal aparece entre as rochas!", 0.5, "vida", "Montanha", new Urso(), 3, "Golpe devastador"),
                new CristalAzul(),
                new EventoDoencaFerimento("Frio Extremo", "O vento cortante e a altitude consomem sua força. Você sente o corpo congelar...", 0.4, "energia", "Montanha", "Climática", "Abrigo aquecido"),
                new TempestadeMontanha()
        )));

        eventosPorAmbiente.put("Ruinas", new ArrayList<>(List.of(
                new EventoCriatura("Corvo", "Um corvo negro voa em círculos sobre você, soltando gritos perturbadores. É como se te seguisse.", 0.3, "sanidade", "Ruinas", new Corvo(), 1, "Som hipnótico"),
                new EventoCriatura("Fantasma", "O ar esfria e uma figura translúcida cruza seu caminho, sussurrando palavras indecifráveis.", 0.4, "sanidade", "Ruinas", new Fantasma(), 2, "Drena sua alma com um sussurro"),
                new EventoDescoberta("Relíquia Sagrada", "Um brilho fraco surge entre escombros. Você se aproxima e encontra uma relíquia antiga...", 0.5, "inventario", "Ruinas", "Relíquia", "Artefato místico", "Somente se sanidade > 50"),
                new EventoDoencaFerimento("Tétano", "Você tropeça e corta o braço em uma viga enferrujada. A ferida começa a latejar...", 0.4, "vida", "Ruinas", "Corte", "Antibiótico"),
                new EventoClimatico("Vento Cortante", "O vento uiva entre as ruínas, lançando areia e fragmentos de pedra contra você.", 0.3, "vida", "Ruinas", "Tempestade de Areia", 1, "Reduz visibilidade")
        )));
    }



}
