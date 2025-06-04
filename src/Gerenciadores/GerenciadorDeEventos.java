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

import javax.swing.*;

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
                new EventoClimatico("Desabamento", "Fragmentos de pedra começam a cair do teto... um som surdo antecede o caos. As paredes tremem e a escuridão é engolida por poeira e pedras.", 0.3, "vida", "Caverna", "Queda de Rochas", 1, "Pode bloquear saídas ou causar ferimentos graves"),
                new EventoDoencaFerimento("Espeleotermia", "O ar úmido e estagnado aquece de forma incomum, fazendo você suar intensamente e sentir tonturas.", 0.4, "energia", "Caverna", "Térmica", "Descanso imediato"),
                new EventoDescoberta("Cristais Sonoros", "Você ouve um leve som metálico vindo das rochas. Ao tocar, descobre pequenos cristais que vibram com o som.", 0.3, "inventario", "Caverna", "Cristal", "Minério luminoso", "Necessário picareta")
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
                new EmboscadaLobos(),
                new EventoClimatico("Chuva Torrencial", "Gotas pesadas começam a cair. Em instantes, a floresta se transforma em um lamaçal escorregadio e escuro. Seus passos afundam no barro.", 0.3, "energia", "Floresta", "Chuva Intensa", 1, "Movimentação reduzida e risco de quedas"),
                new EventoDoencaFerimento("Erva Tóxica", "Sem perceber, você roça em uma planta coberta de uma substância pegajosa. Pouco depois, sente a pele arder.", 0.3, "vida", "Floresta", "Cutânea", "Lavar com água limpa"),
                new EventoDescoberta("Frutas Exóticas", "Entre os arbustos, você encontra frutas de coloração vibrante. Parecem nutritivas, mas exalam um cheiro estranho.", 0.5, "inventario", "Floresta", "Alimento", "Carne de Javali", "Verificar frescor")
        )));

        eventosPorAmbiente.put("LagoRio", new ArrayList<>(List.of(
                new EventoCriatura("Piranha", "Você pisa na água e logo sente picadas rápidas. Piranhas famintas cercam seus pés!", 0.4, "vida", "LagoRio", new Piranha(), 1, "Ataque em cardume"),
                new EventoCriatura("Sanguessuga", "Algo viscoso gruda em sua perna enquanto você caminha na água rasa. Está sugando sua energia!", 0.4, "vida", "LagoRio", new Sanguessuga(), 1, "Drena energia lentamente"),
                new EventoCriatura("Jacaré", "A superfície do lago se rompe e um enorme jacaré avança das águas escuras!", 0.5, "vida", "LagoRio", new Jacare(), 3, "Mordida surpresa"),
                new EventoDescoberta("Tesouro Submerso", "Na margem, algo metálico brilha sob a lama. Um baú antigo!", 0.4, "inventario", "LagoRio", "Tesouro", "Moedas antigas", "Requer nadar"),
                new EventoDoencaFerimento("Água Contaminada", "Ao caminhar pelas águas turvas do lago, sua pele começa a coçar intensamente. Logo surgem feridas ardentes — a contaminação penetrou pelos poros. A dor é imediata, e a água está longe de ser segura.", 0.3, "vida", "LagoRio", "Cutânea", "Carvão ativado"),
                new EventoClimatico("Cheia Súbita", "Você ouve um rugido distante... e logo percebe: a correnteza ganha força! Uma enxurrada desce das montanhas, inundando tudo ao redor!", 0.3, "energia", "LagoRio", "Inundação", 1, "Movimentação severamente dificultada"),
                new EventoDescoberta("Concha Misteriosa", "Algo reluz entre as pedras submersas. Ao puxar, é uma concha grande com padrões desconhecidos.", 0.4, "inventario", "LagoRio", "Relíquia", "Artefato místico", "Somente se sanidade > 40"),
                new EventoClimatico("Nevoeiro Denso", "Uma névoa espessa se espalha pela superfície da água, tornando difícil enxergar até poucos metros à frente.", 0.3, "sanidade", "LagoRio", "Nevoeiro", 1, "Reduz visibilidade drasticamente")
        )));

        eventosPorAmbiente.put("Montanha", new ArrayList<>(List.of(
                new EventoCriatura("Lobo da Neve", "Camuflado no branco da montanha, um lobo o observa antes de se aproximar lentamente.", 0.4, "vida", "Montanha", new Lobo(), 2, "Camuflagem na neve"),
                new EventoCriatura("Urso", "O silêncio é rompido por um rugido. Um urso colossal aparece entre as rochas!", 0.5, "vida", "Montanha", new Urso(), 3, "Golpe devastador"),
                new EventoDescoberta("Cristal Azul Brilhante", "Entre as fendas geladas da montanha, um cristal azul reluz com intensidade, refletindo a luz do sol nas encostas nevadas.", 0.5, "inventario", "Montanha", "Cristal", "Cristal mágico de luz azulada", "Necessário ferramenta para extração"),
                new EventoDoencaFerimento("Frio Extremo", "O vento cortante e a altitude consomem sua força. Você sente o corpo congelar...", 0.4, "energia", "Montanha", "Climática", "Abrigo aquecido"),
                new TempestadeMontanha(),
                new EventoClimatico("Tempestade Elétrica", "O céu escurece repentinamente. Relâmpagos iluminam a neblina gélida, e trovões explodem nas montanhas. Um raio atinge próximo a você!", 0.4, "vida", "Montanha", "Tempestade Elétrica", 2, "Pode causar queimaduras ou atordoamento"),
                new EventoClimatico("Nevasca Súbita", "Flocos de neve aumentam repentinamente, se transformando em uma nevasca que encobre seus passos e dificulta a respiração.", 0.4, "energia", "Montanha", "Nevasca", 2, "Reduz mobilidade e visibilidade")
        )));

        eventosPorAmbiente.put("Ruinas", new ArrayList<>(List.of(
                new EventoCriatura("Corvo", "Um corvo negro voa em círculos sobre você, soltando gritos perturbadores. É como se te seguisse.", 0.3, "sanidade", "Ruinas", new Corvo(), 1, "Som hipnótico"),
                new EventoCriatura("Fantasma", "O ar esfria e uma figura translúcida cruza seu caminho, sussurrando palavras indecifráveis.", 0.4, "sanidade", "Ruinas", new Fantasma(), 2, "Drena sua alma com um sussurro"),
                new EventoDescoberta("Relíquia Sagrada", "Um brilho fraco surge entre escombros. Você se aproxima e encontra uma relíquia antiga...", 0.5, "inventario", "Ruinas", "Relíquia", "Artefato místico", "Somente se sanidade > 50"),
                new EventoDoencaFerimento("Tétano", "Você tropeça e corta o braço em uma viga enferrujada. A ferida começa a latejar...", 0.4, "vida", "Ruinas", "Corte", "Antibiótico"),
                new EventoClimatico("Vento Cortante", "Ráfagas violentas varrem as ruínas, carregando poeira, estilhaços e grãos finos que cortam como lâminas sua pele e visão.", 0.3, "vida", "Ruinas", "Tempestade de Areia", 1, "Redução severa de visibilidade e risco de ferimentos"),
                new EventoDoencaFerimento("Poeira Antiga", "Ao abrir uma porta de pedra, uma nuvem de poeira antiga invade seus pulmões, causando tosse e tontura.", 0.3, "sanidade", "Ruinas", "Respiratório", "Antídoto raro"),
                new EventoDescoberta("Inscrições Perdidas", "Num pedestal coberto de musgo, você encontra inscrições quase apagadas que falam de um antigo ritual.", 0.4, "inventario", "Ruinas", "Artefato", "Artefato místico", "Somente se sanidade > 60")
        )));
    }

    public void aplicarEventoCriaturaDuranteDescanso(Personagem jogador) {
        String nomeAmbiente = jogador.getAmbienteAtual().getClass().getSimpleName();
        List<Evento> eventos = eventosPorAmbiente.get(nomeAmbiente);

        if (eventos != null) {
            List<Evento> criaturas = new ArrayList<>();

            for (Evento e : eventos) {
                if (e instanceof EventoCriatura) {
                    criaturas.add(e);
                }
            }

            if (!criaturas.isEmpty()) {
                Evento eventoBase = criaturas.get(new Random().nextInt(criaturas.size()));
                if (eventoBase instanceof EventoCriatura eventoCriatura) {
                    eventoCriatura.executarDuranteDescanso(jogador, jogador.getAmbienteAtual());
                    historicoDeEventos.add(eventoCriatura.getNomeEvento());
                }
            }
        }
    }

    public void aplicarEventoClimaticoDuranteDescanso(Personagem jogador) {
        String nomeAmbiente = jogador.getAmbienteAtual().getClass().getSimpleName();
        List<Evento> eventos = eventosPorAmbiente.get(nomeAmbiente);

        if (eventos != null) {
            List<Evento> climaticos = new ArrayList<>();

            for (Evento e : eventos) {
                if (e instanceof EventoClimatico) {
                    climaticos.add(e);
                }
            }

            if (!climaticos.isEmpty()) {
                Evento eventoEscolhido = climaticos.get(new Random().nextInt(climaticos.size()));
                System.out.println("\n🔸 O clima muda repentinamente durante o descanso!\n");
                eventoEscolhido.executar(jogador, jogador.getAmbienteAtual());
                historicoDeEventos.add(eventoEscolhido.getNomeEvento());
            }
        }
    }

    //Interface
    public void aplicarEventoInterface(Personagem jogador, JTextArea areaLog) {
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 15) {
            jogador.diminuirVida(10);
            areaLog.append("Você pisou em uma armadilha escondida! (-10 de vida)\n");
        } else if (chance < 30) {
            jogador.diminuirSanidade(15);
            areaLog.append("O ambiente te deixou perturbado... (-15 de sanidade)\n");
        } else if (chance < 40) {
            jogador.diminuirEnergia(20);
            areaLog.append("Você se esforçou demais e está exausto. (-20 de energia)\n");
        } else {
            areaLog.append("Nenhum evento adverso ocorreu desta vez.\n");
        }
    }

    public void aplicarEventoDuranteDescansoInterface(Personagem jogador, Ambiente ambienteAtual, JTextArea areaLog) {
        for (Evento evento : eventosDisponiveis) {
            if (evento instanceof EventoCriatura criatura) {
                if (criatura.podeOcorrerNoAmbiente(ambienteAtual)) {
                    if (new Random().nextDouble() < evento.getProbabilidadeDeOcorrencia()) {
                        criatura.executarDuranteDescansoInterface(jogador, ambienteAtual, areaLog);
                        break;
                    }
                }
            }
        }
    }
}
