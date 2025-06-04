package Gerenciadores;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
// Imports para classes específicas de Criatura usadas em inicializarEventosPorAmbiente
// Se elas estiverem no pacote Criatura.Subclasses, o import Criatura.Subclasses.* as cobrirá.
// Caso contrário, importe individualmente ou ajuste o wildcard.
import Criatura.Subclasses.MorcegoGigante;
import Criatura.Subclasses.RatoMutante;
import Criatura.Subclasses.Cobra;
import Criatura.Subclasses.Javali;
import Criatura.Subclasses.Lobo;
import Criatura.Subclasses.Jaguatirica;
import Criatura.Subclasses.Piranha;
import Criatura.Subclasses.Sanguessuga;
import Criatura.Subclasses.Jacare;
import Criatura.Subclasses.Urso;
import Criatura.Subclasses.Corvo;
import Criatura.Subclasses.Fantasma;
// Imports para classes de Evento (gerais e específicas)
import Evento.Subclasses.EventoClimatico;
import Evento.Subclasses.EventoDescoberta;
import Evento.Subclasses.EventoDoencaFerimento;
import Evento.Subclasses.EventoCriatura;
import Evento.Subclasses.Específicos.*; // Para EnchenteRapida, PoeiraToxica, etc.

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class GerenciadorDeEventos {
    private ArrayList<Evento> eventosDisponiveis = new ArrayList<>(); // Populado via adicionarEvento()
    private ArrayList<String> historicoDeEventos = new ArrayList<>();
    private Random sorteador = new Random();

    // Mapa para armazenar eventos por nome da classe do ambiente (como você está usando getSimpleName())
    private Map<String, List<Evento>> eventosPorAmbiente = new HashMap<>();

    public GerenciadorDeEventos() {
        inicializarEventosPorAmbiente();
    }

    public void adicionarEvento(Evento evento) {
        eventosDisponiveis.add(evento);
    }

    public void mostrarHistoricoDeEventos() { // Para console
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
        Ambiente ambienteAtual = jogador.getAmbienteAtual();
        if (ambienteAtual == null) {
            System.out.println("Erro: Ambiente atual do jogador não definido para evento aleatório.");
            return;
        }
        String nomeClasseAmbiente = ambienteAtual.getClass().getSimpleName();
        List<Evento> eventos = eventosPorAmbiente.get(nomeClasseAmbiente);

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Você explora " + ambienteAtual.getNome() + ", mas nada acontece por aqui...\n");
            return;
        }

        if (sorteador.nextDouble() < 0.2) {
            System.out.println("Você explora com cautela... mas tudo está calmo em " + ambienteAtual.getNome() + ".\n");
            return;
        }

        Evento eventoEscolhido = eventos.get(sorteador.nextInt(eventos.size()));
        System.out.println("\n🔸 Um evento inesperado acontece em " + ambienteAtual.getNome() + "...\n");
        eventoEscolhido.executar(jogador, ambienteAtual); // Chama o executar de console do evento
        historicoDeEventos.add(eventoEscolhido.getNomeEvento());
    }

    private void inicializarEventosPorAmbiente() {
        eventosPorAmbiente = new HashMap<>();

        // Caverna (Nome da classe do ambiente: "Caverna")
        eventosPorAmbiente.put("Caverna", new ArrayList<>(List.of(
                new EventoCriatura("Morcego Gigante", "Você escuta um chiado agudo... Um morcego enorme desce do teto em sua direção!", 0.5, "vida", "Caverna", new MorcegoGigante(), 1, "Ataque aéreo"),
                new EventoCriatura("Rato Mutante", "Nos cantos escuros, um rato com olhos brilhantes avança rosnando. Algo está errado com ele.", 0.4, "vida", "Caverna", new RatoMutante(), 1, "Morde com toxinas"),
                new EventoDescoberta("Fóssil Luminoso", "Ao iluminar a parede com sua lanterna, você vê fósseis que brilham com uma luz tênue e azulada.", 0.4, "inventario", "Caverna", "Fóssil", "Minério luminoso", "Necessário picareta"),
                new EventoDoencaFerimento("Gás Tóxico", "Ao avançar, um gás verde começa a sair de uma fenda no chão. Você tenta prender a respiração...", 0.5, "sanidade", "Caverna", "Respiratório", "Antídoto raro"),
                new EventoClimatico("Desabamento", "Fragmentos de pedra começam a cair do teto...", 0.3, "vida", "Caverna", "Queda de Rochas", 1, "Pode bloquear saídas ou causar ferimentos graves"),
                new EventoDoencaFerimento("Espeleotermia", "O ar úmido e estagnado aquece de forma incomum...", 0.4, "energia", "Caverna", "Térmica", "Descanso imediato"),
                new EventoDescoberta("Cristais Sonoros", "Você ouve um leve som metálico vindo das rochas...", 0.3, "inventario", "Caverna", "Cristal", "Minério luminoso", "Necessário picareta")
        )));

        // Floresta (Nome da classe do ambiente: "Floresta")
        eventosPorAmbiente.put("Floresta", new ArrayList<>(List.of(
                new EventoCriatura("Cobra", "Entre os galhos baixos, uma cobra escorrega...", 0.3, "vida", "Floresta", new Cobra(), 1, "Veneno paralisante"),
                new EventoCriatura("Javali Selvagem", "Você escuta um grunhido e um javali investe!", 0.3, "vida", "Floresta", new Javali(), 2, "Investida poderosa"),
                new EmboscadaLobos(), // Evento Específico
                new EventoDescoberta("Cabana Abandonada", "Em meio à vegetação, surge uma antiga cabana...", 0.5, "inventario", "Floresta", "Abrigo", "Itens de sobrevivência", "Requer lanterna"),
                new EventoDoencaFerimento("Picada de Inseto Misterioso", "Insetos picam sua pele, causando ardência e febre.", 0.4, "vida", "Floresta", "Cutânea", "Pomada natural"),
                new EventoCriatura("Jaguatirica Sorrateira", "Um som rápido nos galhos... A jaguatirica salta!", 0.5, "vida", "Floresta", new Jaguatirica(), 2, "Ataque antes de desaparecer"),
                // new EnchenteRapida(), // Enchente Rápida é mais típica de Lago/Rio
                new EventoClimatico("Chuva Torrencial na Floresta", "Gotas pesadas transformam a floresta em um lamaçal.", 0.3, "energia", "Floresta", "Chuva Intensa", 1, "Movimentação reduzida"),
                new EventoDoencaFerimento("Contato com Erva Tóxica", "Você roça em uma planta pegajosa e sente a pele arder.", 0.3, "vida", "Floresta", "Cutânea", "Lavar com água limpa"),
                new EventoDescoberta("Frutas Exóticas da Floresta", "Você encontra frutas de coloração vibrante...", 0.5, "inventario", "Floresta", "Alimento", "Carne de Javali", "Verificar frescor") // "Carne de Javali" como recurso parece estranho para Frutas Exóticas, ajuste se necessário.
        )));

        // LagoRio (Nome da classe do ambiente: "LagoRio")
        eventosPorAmbiente.put("LagoRio", new ArrayList<>(List.of(
                new EventoCriatura("Cardume de Piranhas", "Você pisa na água e sente picadas rápidas!", 0.4, "vida", "LagoRio", new Piranha(), 1, "Ataque em cardume"),
                new EventoCriatura("Sanguessuga Gigante", "Algo viscoso gruda em sua perna na água rasa!", 0.4, "energia", "LagoRio", new Sanguessuga(), 1, "Drena energia lentamente"),
                new EventoCriatura("Jacaré das Águas Escuras", "A superfície do lago se rompe e um enorme jacaré avança!", 0.5, "vida", "LagoRio", new Jacare(), 3, "Mordida surpresa"),
                new EventoDescoberta("Tesouro Submerso no Lago", "Na margem, algo metálico brilha sob a lama. Um baú!", 0.4, "inventario", "LagoRio", "Tesouro", "Moedas antigas", "Requer nadar"),
                new EventoDoencaFerimento("Infecção por Água Contaminada", "Ao caminhar pelas águas turvas, sua pele coça e feridas surgem.", 0.3, "vida", "LagoRio", "Cutânea", "Carvão ativado"),
                new EnchenteRapida(), // Evento Específico
                new EventoDescoberta("Concha Misteriosa do Rio", "Algo reluz entre as pedras submersas. Uma concha grande!", 0.4, "inventario", "LagoRio", "Relíquia", "Artefato místico", "Somente se sanidade > 40"),
                new EventoClimatico("Nevoeiro Denso Sobre as Águas", "Uma névoa espessa cobre a água, dificultando a visão.", 0.3, "sanidade", "LagoRio", "Nevoeiro", 1, "Reduz visibilidade drasticamente")
        )));

        // Montanha (Nome da classe: "Montanha")
        eventosPorAmbiente.put("Montanha", new ArrayList<>(List.of(
                new EventoCriatura("Lobo da Neve Astuto", "Camuflado no branco, um lobo o observa...", 0.4, "vida", "Montanha", new Lobo(), 2, "Camuflagem na neve"),
                new EventoCriatura("Urso das Geleiras", "Um rugido ecoa! Um urso colossal aparece!", 0.5, "vida", "Montanha", new Urso(), 3, "Golpe devastador"),
                new EventoDescoberta("Veio de Cristal Azul", "Entre as fendas geladas, um cristal azul reluz.", 0.5, "inventario", "Montanha", "Cristal", "Cristal mágico de luz azulada", "Necessário ferramenta para extração"),
                new EventoDoencaFerimento("Hipotermia Severa", "O vento cortante e a altitude consomem sua força...", 0.4, "energia", "Montanha", "Climática", "Abrigo aquecido e fogo"),
                new TempestadeMontanha(), // Evento Específico
                new EventoClimatico("Tempestade Elétrica Alpina", "O céu escurece. Relâmpagos iluminam a neblina gélida.", 0.4, "vida", "Montanha", "Tempestade Elétrica", 2, "Risco de queimaduras ou atordoamento"),
                new EventoClimatico("Nevasca Implacável", "Flocos de neve se transformam em uma nevasca que encobre tudo.", 0.4, "energia", "Montanha", "Nevasca", 2, "Reduz mobilidade e visibilidade")
        )));

        // Ruinas (Nome da classe: "Ruinas")
        eventosPorAmbiente.put("Ruinas", new ArrayList<>(List.of(
                new EventoCriatura("Bando de Corvos Agourentos", "Corvos negros voam em círculos, soltando gritos perturbadores.", 0.3, "sanidade", "Ruinas", new Corvo(), 1, "Som hipnótico"),
                new EventoCriatura("Aparição Espectral", "O ar esfria e uma figura translúcida cruza seu caminho.", 0.4, "sanidade", "Ruinas", new Fantasma(), 2, "Drena sua alma com um sussurro"),
                new EventoDescoberta("Relíquia Sagrada Oculta", "Um brilho fraco entre escombros revela uma relíquia antiga.", 0.5, "inventario", "Ruinas", "Relíquia", "Artefato místico", "Somente se sanidade > 50"),
                new EventoDoencaFerimento("Corte Infectado nas Ruínas", "Você tropeça e corta o braço em metal enferrujado.", 0.4, "vida", "Ruinas", "Corte", "Antibiótico e bandagens"),
                new EventoClimatico("Ventos Cortantes das Ruínas", "Ráfagas violentas varrem as ruínas, carregando poeira e estilhaços.", 0.3, "vida", "Ruinas", "Tempestade de Detritos", 1, "Redução de visibilidade e risco de ferimentos"),
                new PoeiraToxica(), // Evento Específico
                new EventoDescoberta("Inscrições Antigas Decifradas", "Num pedestal coberto de musgo, inscrições revelam segredos.", 0.4, "inventario", "Ruinas", "Artefato", "Conhecimento perdido", "Somente se sanidade > 60") // "Artefato místico" estava repetido, mudei para "Conhecimento perdido" como exemplo
        )));
    }

    public void aplicarEventoCriaturaDuranteDescanso(Personagem jogador) {
        Ambiente ambienteAtual = jogador.getAmbienteAtual();
        if (ambienteAtual == null) return;
        String nomeClasseAmbiente = ambienteAtual.getClass().getSimpleName();
        List<Evento> eventosDoAmbiente = eventosPorAmbiente.get(nomeClasseAmbiente);

        if (eventosDoAmbiente != null) {
            List<EventoCriatura> criaturasNoAmbiente = new ArrayList<>();
            for (Evento e : eventosDoAmbiente) {
                if (e instanceof EventoCriatura) {
                    criaturasNoAmbiente.add((EventoCriatura) e);
                }
            }

            if (!criaturasNoAmbiente.isEmpty()) {
                EventoCriatura eventoCriaturaEscolhido = criaturasNoAmbiente.get(sorteador.nextInt(criaturasNoAmbiente.size()));
                if (sorteador.nextDouble() < eventoCriaturaEscolhido.getProbabilidadeDeOcorrencia() * 0.5) { // Chance reduzida durante descanso
                    eventoCriaturaEscolhido.executarDuranteDescanso(jogador, ambienteAtual);
                    historicoDeEventos.add(eventoCriaturaEscolhido.getNomeEvento());
                }
            }
        }
    }

    public void aplicarEventoClimaticoDuranteDescanso(Personagem jogador) {
        Ambiente ambienteAtual = jogador.getAmbienteAtual();
        if (ambienteAtual == null) return;
        String nomeClasseAmbiente = ambienteAtual.getClass().getSimpleName();
        List<Evento> eventosDoAmbiente = eventosPorAmbiente.get(nomeClasseAmbiente);

        if (eventosDoAmbiente != null) {
            List<EventoClimatico> climaticosNoAmbiente = new ArrayList<>();
            for (Evento e : eventosDoAmbiente) {
                if (e instanceof EventoClimatico) {
                    climaticosNoAmbiente.add((EventoClimatico) e);
                }
            }

            if (!climaticosNoAmbiente.isEmpty()) {
                EventoClimatico eventoEscolhido = climaticosNoAmbiente.get(sorteador.nextInt(climaticosNoAmbiente.size()));
                if (sorteador.nextDouble() < eventoEscolhido.getProbabilidadeDeOcorrencia() * 0.6) { // Chance um pouco maior para clima
                    System.out.println("\n🔸 O clima muda repentinamente durante o descanso!\n");
                    eventoEscolhido.executar(jogador, ambienteAtual);
                    historicoDeEventos.add(eventoEscolhido.getNomeEvento());
                }
            }
        }
    }

    //Interface
    public void aplicarEventoAdversoInterface(Personagem jogador, JTextArea areaLog) {
        int chance = sorteador.nextInt(100);

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
            areaLog.append("Nenhum pequeno imprevisto ocorreu desta vez.\n");
        }
    }

    public void dispararEventoExploracaoInterface(Personagem jogador, JTextArea areaLog) {
        Ambiente ambienteAtual = jogador.getAmbienteAtual();
        if (ambienteAtual == null) {
            areaLog.append("Erro: Ambiente atual do jogador não definido.\n");
            return;
        }
        String nomeClasseAmbiente = ambienteAtual.getClass().getSimpleName();
        List<Evento> eventosDoAmbiente = eventosPorAmbiente.get(nomeClasseAmbiente);

        if (eventosDoAmbiente == null || eventosDoAmbiente.isEmpty()) {
            areaLog.append("Você explora " + ambienteAtual.getNome() + ", mas nada acontece por aqui...\n");
            return;
        }

        // Chance geral de NENHUM evento complexo acontecer (ex: 40%)
        if (sorteador.nextDouble() < 0.40) {
            areaLog.append("Você explora com atenção... mas o ambiente " + ambienteAtual.getNome() + " parece calmo no momento.\n");
            // Opcional: Mesmo se nenhum evento principal ocorrer, pode haver um pequeno evento adverso
            if (sorteador.nextDouble() < 0.25) { // 25% de chance de um pequeno evento adverso
                aplicarEventoAdversoInterface(jogador, areaLog);
            }
            return;
        }

        Evento eventoEscolhido = eventosDoAmbiente.get(sorteador.nextInt(eventosDoAmbiente.size()));

        // Verifica a probabilidade de ocorrência do evento específico escolhido
        if (sorteador.nextDouble() < eventoEscolhido.getProbabilidadeDeOcorrencia()) {
            areaLog.append("\n🔸 Algo inesperado acontece durante sua exploração em " + ambienteAtual.getNome() + "...\n");

            if (eventoEscolhido instanceof EventoClimatico) {
                ((EventoClimatico) eventoEscolhido).executarInterface(jogador, ambienteAtual, areaLog);
            } else if (eventoEscolhido instanceof EventoCriatura) {
                ((EventoCriatura) eventoEscolhido).executarInterface(jogador, ambienteAtual, areaLog);
            } else if (eventoEscolhido instanceof EventoDescoberta) {
                ((EventoDescoberta) eventoEscolhido).executarInterface(jogador, ambienteAtual, areaLog);
            } else if (eventoEscolhido instanceof EventoDoencaFerimento) {
                ((EventoDoencaFerimento) eventoEscolhido).executarInterface(jogador, ambienteAtual, areaLog);
            } else {
                areaLog.append("Evento (genérico): " + eventoEscolhido.getNomeEvento() + "\n");
                areaLog.append(eventoEscolhido.getDescricao() + "\n");
                // Idealmente, todos os eventos teriam um método executarInterface
            }
            historicoDeEventos.add(eventoEscolhido.getNomeEvento());
        } else {
            areaLog.append("Você sentiu que algo poderia acontecer em " + ambienteAtual.getNome() +", mas o perigo passou... por ora.\n");
            if (sorteador.nextDouble() < 0.15) { // Opcional: pequena chance de evento adverso menor
                aplicarEventoAdversoInterface(jogador, areaLog);
            }
        }
    }

    public void aplicarEventoDuranteDescansoInterface(Personagem jogador, Ambiente ambienteAtual, JTextArea areaLog) {
        if (ambienteAtual == null) {
            areaLog.append("Erro: Ambiente atual do jogador não definido para evento de descanso.\n");
            return;
        }

        String nomeClasseAmbiente = ambienteAtual.getClass().getSimpleName();
        List<Evento> eventosDoAmbiente = eventosPorAmbiente.get(nomeClasseAmbiente);
        boolean algumEventoOcorreu = false;

        if (eventosDoAmbiente == null || eventosDoAmbiente.isEmpty()) {
            areaLog.append("Seu descanso foi tranquilo (nenhum evento específico para este ambiente).\n");
            return;
        }

        // Tenta evento de Criatura durante o descanso
        List<EventoCriatura> criaturasNoAmbiente = new ArrayList<>();
        for (Evento ev : eventosDoAmbiente) {
            if (ev instanceof EventoCriatura) {
                criaturasNoAmbiente.add((EventoCriatura) ev);
            }
        }
        if (!criaturasNoAmbiente.isEmpty()) {
            if (sorteador.nextDouble() < 0.30) { // 30% de chance de encontro com criatura
                EventoCriatura eventoCriaturaEscolhido = criaturasNoAmbiente.get(sorteador.nextInt(criaturasNoAmbiente.size()));
                if (sorteador.nextDouble() < eventoCriaturaEscolhido.getProbabilidadeDeOcorrencia()) {
                    eventoCriaturaEscolhido.executarDuranteDescansoInterface(jogador, ambienteAtual, areaLog);
                    historicoDeEventos.add(eventoCriaturaEscolhido.getNomeEvento());
                    algumEventoOcorreu = true;
                }
            }
        }

        // Se não houve evento de criatura, tenta evento Climático
        if (!algumEventoOcorreu) {
            List<EventoClimatico> climaticosNoAmbiente = new ArrayList<>();
            for (Evento ev : eventosDoAmbiente) {
                if (ev instanceof EventoClimatico) {
                    climaticosNoAmbiente.add((EventoClimatico) ev);
                }
            }
            if (!climaticosNoAmbiente.isEmpty()) {
                if (sorteador.nextDouble() < 0.25) { // 25% de chance de evento climático
                    EventoClimatico eventoClimaticoEscolhido = climaticosNoAmbiente.get(sorteador.nextInt(climaticosNoAmbiente.size()));
                    if (sorteador.nextDouble() < eventoClimaticoEscolhido.getProbabilidadeDeOcorrencia()) {
                        areaLog.append("\n🔸 O clima muda repentinamente durante o seu descanso!\n");
                        // Considerar se EventoClimatico deveria ter um executarDuranteDescansoInterface específico
                        // ou se executarInterface é suficiente.
                        eventoClimaticoEscolhido.executarInterface(jogador, ambienteAtual, areaLog);
                        historicoDeEventos.add(eventoClimaticoEscolhido.getNomeEvento());
                        algumEventoOcorreu = true;
                    }
                }
            }
        }

        // Se nenhum evento específico ocorreu, pode haver um evento adverso menor
        if (!algumEventoOcorreu && sorteador.nextDouble() < 0.15) { // 15% de chance de um pequeno problema
            aplicarEventoAdversoInterface(jogador, areaLog);
            algumEventoOcorreu = true; // Consideramos que algo ocorreu
        }

        if (!algumEventoOcorreu) {
            areaLog.append("Seu descanso foi tranquilo e revigorante desta vez.\n");
        }
    }
}
