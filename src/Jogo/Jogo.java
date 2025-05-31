package Jogo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Ambiente.Superclasse.Ambiente;
import Ambiente.Subclasses.*;
import Excecoes.AmbienteInacessivelException;
import Excecoes.InventarioCheioException;
import Excecoes.FomeSedeSanidadeException;
import Gerenciadores.GerenciadorDeAmbientes;
import Gerenciadores.GerenciadorDeEventos;
import Evento.Subclasses.Específicos.*;
import Personagem.Superclasse.Personagem;
import Item.Superclasse.*;
import Item.Subclasses.*;
import Personagem.Subclasses.*;

public class Jogo {
    private Scanner scanner = new Scanner(System.in);
    private GerenciadorDeAmbientes gerenciador = new GerenciadorDeAmbientes();
    private GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
    private Personagem jogador;
    private Ambiente floresta, caverna, lagorio, montanha, ruinas;

    public void iniciar() {
        System.out.println("\nBEM-VINDO AO JOGO DE SOBREVIVÊNCIA - ÚLTIMA FRONTEIRA");

        System.out.println("\nSelecione a opção escolhida: ");
        System.out.println("1 - Iniciar Jogo");
        System.out.println("2 - Sair");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> {
                criarPersonagem();
                configurarAmbientes();
                configurarEventos();
                introducao();
                loopJogo();  // Aqui o jogo acontece

            }

            case 2 -> {
                System.out.println("Você decidiu não embarcar nesta aventura... Até a próxima!");
                return;
            }

            default -> {
                System.out.println("Opção inválida. Reinicie o jogo para tentar novamente.");
            }
        }

    }

    public void apresentarAcoesPorAmbiente(Personagem jogador) {
        Ambiente ambiente = jogador.getAmbienteAtual();

        System.out.println("\n🔹 Ações disponíveis neste local:");

        if (ambiente instanceof Floresta) {
            System.out.println("1 - Coletar frutas");
            System.out.println("2 - Procurar abrigo improvisado");
        } else if (ambiente instanceof Montanha) {
            System.out.println("1 - Escalar para encontrar abrigo natural");
            System.out.println("2 - Procurar itens congelados no alto");
        } else if (ambiente instanceof LagoRio) {
            System.out.println("1 - Beber água diretamente");
            System.out.println("2 - Pescar");
        } else if (ambiente instanceof Caverna) {
            System.out.println("1 - Acender tochas e explorar");
            System.out.println("2 - Buscar minerais úteis");
        } else if (ambiente instanceof Ruinas) {
            System.out.println("1 - Vasculhar ruínas por suprimentos antigos");
            System.out.println("2 - Analisar símbolos misteriosos");
        } else {
            System.out.println("1 - Explorar o local");
        }

        System.out.println("3 - Usar item");
        System.out.println("4 - Passar turno");
    }

    private void criarPersonagem() {
        System.out.println("Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();

        System.out.println("Escolha a classe do personagem");
        System.out.println("1 - Rastreador");
        System.out.println("2 - Mecânico");
        System.out.println("3 - Médico");
        System.out.println("4 - Sobrevivente Nato");

        int escolha = -1;
        while (escolha < 1 || escolha > 4) {
            System.out.print("Digite um número de 1 a 4: ");
            escolha = scanner.nextInt();
        }
        scanner.nextLine();

        switch (escolha) {
            case 1 -> jogador = new Rastreador(nome);
            case 2 -> jogador = new Mecanico(nome);
            case 3 -> jogador = new Medico(nome);
            case 4 -> jogador = new SobreviventeNato(nome);
        };
    }

    private void configurarAmbientes() {
        floresta = new Floresta();
        caverna = new Caverna();
        lagorio = new LagoRio();
        montanha = new Montanha();
        ruinas = new Ruinas();

        gerenciador.adicionarAmbiente(floresta);
        gerenciador.adicionarAmbiente(caverna);
        gerenciador.adicionarAmbiente(lagorio);
        gerenciador.adicionarAmbiente(montanha);
        gerenciador.adicionarAmbiente(ruinas);

        ArrayList<Ambiente> ambientesDisponiveis = gerenciador.getAmbientes();
        Ambiente ambienteInicial = ambientesDisponiveis.get(new Random().nextInt(ambientesDisponiveis.size()));

        jogador.setAmbienteAtual(ambienteInicial);
        gerenciador.registrarAmbienteInicial(ambienteInicial);
    }

    private void configurarEventos() {
        gerenciadorEventos.adicionarEvento(new EmboscadaLobos());
        gerenciadorEventos.adicionarEvento(new EnchenteRapida());
        gerenciadorEventos.adicionarEvento(new PoeiraToxica());
        gerenciadorEventos.adicionarEvento(new TempestadeMontanha());
    }

    private void introducao() {
        System.out.println("\n>>>>> JOGO INICIADO <<<<<\n");
        System.out.println(jogador.getNome() + " desperta lentamente, sem saber como chegou naquele lugar.");
        System.out.println("Está sozinho/a, cercado/a por um ambiente desconhecido e cheio de perigos.");
        System.out.println("Será preciso explorar, coletar recursos e tomar boas decisões para sobreviver.");
        System.out.println("\n🔹 Ambiente inicial: " + jogador.getAmbienteAtual().getNome());
        System.out.println("Descrição: " + jogador.getAmbienteAtual().getDescricao());
        System.out.println("Clima: " + jogador.getAmbienteAtual().getCondicaoClimatica());

    }

    private void loopJogo() {
        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1 - Ver status");
            System.out.println("2 - Ver inventário");
            System.out.println("3 - Usar item");
            System.out.println("4 - Mudar de ambiente");
            System.out.println("5 - Realizar ações");
            System.out.println("6 - Explorar o ambiente");
            System.out.println("7 - Ação especial da sua classe");
            System.out.println("8 - Remover item do inventário");
            System.out.println("0 - Sair do jogo");

            int escolhaMenu = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (escolhaMenu) {
                    case 1 -> jogador.getStatus();
                    case 2 -> jogador.visualizarInventario();
                    case 3 -> {
                        System.out.print("Digite o nome do item que deseja usar: ");
                        String itemUsar = scanner.nextLine();
                        jogador.usarItem(itemUsar);
                        jogador.diminuirFome(1);
                        jogador.diminuirSede(2);
                    }
                    case 4 -> {
                        menuAmbientes();
                        jogador.diminuirFome(8);
                        jogador.diminuirSede(10);
                    }
                    case 5 -> {
                        realizarAcoes();
                        jogador.diminuirFome(3);
                        jogador.diminuirSede(4);
                    }
                    case 6 -> {
                        explorarAmbiente();
                        jogador.diminuirFome(3);
                        jogador.diminuirSede(4);
                    }
                    case 7 -> {
                        if (jogador instanceof Rastreador rastreador) {
                            System.out.println("1 - Identificar pegadas");
                            System.out.println("2 - Farejar trilha");
                            System.out.println("3 - Procurar recursos no ambiente");
                            int escolha = scanner.nextInt();
                            scanner.nextLine();
                            if (escolha == 1) rastreador.identificarPegadas(jogador.getAmbienteAtual());
                            else if (escolha == 2) rastreador.farejarTrilha(jogador.getAmbienteAtual());
                            else if (escolha == 3) rastreador.procurarRecursos(jogador.getAmbienteAtual(), jogador);
                            else System.out.println("Opção inválida.");
                        } else if (jogador instanceof Mecanico mecanico) {
                            System.out.println("1 - Consertar equipamento");
                            System.out.println("2 - Melhorar arma");
                            int escolha = scanner.nextInt();
                            scanner.nextLine();
                            if (escolha == 1) mecanico.consertarEquipamento();
                            else if (escolha == 2) mecanico.melhorarArma();
                            else System.out.println("Opção inválida.");
                        } else if (jogador instanceof Medico medico) {
                            System.out.println("1 - Curar a si mesmo");
                            System.out.println("2 - Curar outro personagem");
                            System.out.println("3 - Preparar remédio natural");
                            int escolha = scanner.nextInt();
                            scanner.nextLine();
                            if (escolha == 1) {
                                medico.autoCurarFerimentosLeves();
                            } else if (escolha == 2) {
                                System.out.println("Ainda não há outro personagem disponível.");
                            } else if (escolha == 3) {
                                medico.prepararRemedioNatural();
                            } else {
                                System.out.println("Opção inválida.");
                            }
                        } else if (jogador instanceof SobreviventeNato sobrevivente) {
                            System.out.println("1 - Montar abrigo improvisado");
                            System.out.println("2 - Fabricar lança");
                            System.out.println("3 - Caçar animais");
                            int escolha = scanner.nextInt();
                            scanner.nextLine();
                            if (escolha == 1) sobrevivente.montarAbrigoImprovisado(jogador.getAmbienteAtual());
                            else if (escolha == 2) sobrevivente.fabricarLanca();
                            else if (escolha == 3) sobrevivente.cacarAnimais();
                            else System.out.println("Opção inválida.");
                        } else {
                            System.out.println("Sua classe não possui ações especiais definidas.");
                        }
                        jogador.diminuirFome(2);
                        jogador.diminuirSede(3);
                    }
                    case 8 -> {
                        System.out.print("Digite o nome do item que deseja remover: ");
                        String itemRemover = scanner.nextLine();
                        jogador.getInventario().removerItem(itemRemover);
                    }

                    case 0 -> {
                        gerenciador.mostrarHistorico();
                        gerenciadorEventos.mostrarHistoricoDeEventos();
                        System.out.println("Obrigado por jogar!");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }

                // Verificação de sobrevivência com exceção
                jogador.verificarFomeSedeSanidade();

            } catch (FomeSedeSanidadeException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("O jogador não resistiu.");
                return;
            }

        }
    }

    private void explorarAmbiente() {
        System.out.print("\nVocê decide explorar a área ao redor...");
        jogador.getAmbienteAtual().explorar(jogador);
    }

    private void realizarAcoes() {
        apresentarAcoesPorAmbiente(jogador);

        System.out.print("\nEscolha uma ação: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        Ambiente ambiente = jogador.getAmbienteAtual();

        switch (escolha) {
            case 1 -> {
                if (ambiente instanceof Floresta) {
                    System.out.println("Você encontra frutas frescas da floresta.");
                    Alimentos frutas = new Alimentos("Frutas", 0.5, 3, 15, "Fruta", 3);
                    System.out.print("Deseja coletar " + frutas.getNome() + "? (s/n): ");
                    String resposta = scanner.nextLine().trim().toLowerCase();
                    if (resposta.equals("s") || resposta.equals("sim")) {
                        try {
                            jogador.getInventario().adicionarItem(frutas);
                        } catch (InventarioCheioException e) {
                            System.out.println("Inventário cheio! Não foi possível adicionar Frutas.");
                        }
                    } else {
                        System.out.println("Você deixou as frutas para trás.");
                    }

                } else if (ambiente instanceof Montanha) {
                    System.out.println("Você escala e encontra uma caverna para abrigo.");
                    Material pedra = new Material("Pedra Afiada", "Pedra", 1.0, 1, 30);
                    System.out.print("Deseja coletar " + pedra.getNome() + "? (s/n): ");
                    String resposta = scanner.nextLine().trim().toLowerCase();
                    if (resposta.equals("s") || resposta.equals("sim")) {
                        try {
                            jogador.getInventario().adicionarItem(pedra);
                        } catch (InventarioCheioException e) {
                            System.out.println("Inventário cheio! Não foi possível adicionar Pedra Afiada.");
                        }
                    } else {
                        System.out.println("Você deixou a pedra para trás.");
                    }

                } else if (ambiente instanceof LagoRio) {
                    System.out.println("Você bebe água do lago, se hidratando e recuperando energia e sede.");
                    jogador.restaurarEnergia(15);
                    jogador.restaurarSede(15);

                } else if (ambiente instanceof Caverna) {
                    System.out.println("Você acende tochas e encontra minérios.");
                    Material minerio = new Material("Minério Brilhante", "Cristal", 2.0, 1, 50);
                    System.out.print("Deseja coletar " + minerio.getNome() + "? (s/n): ");
                    String resposta = scanner.nextLine().trim().toLowerCase();
                    if (resposta.equals("s") || resposta.equals("sim")) {
                        try {
                            jogador.getInventario().adicionarItem(minerio);
                        } catch (InventarioCheioException e) {
                            System.out.println("Inventário cheio! Não foi possível adicionar Minério Brilhante.");
                        }
                    } else {
                        System.out.println("Você deixou o minério para trás.");
                    }

                } else if (ambiente instanceof Ruinas) {
                    System.out.println("Você vasculha e encontra uma bolacha antiga e rachada.");
                    Alimentos bolacha = new Alimentos("Bolacha Rachada", 0.3, 1, -5, "Industrial", 1);
                    System.out.print("Deseja coletar " + bolacha.getNome() + "? (s/n): ");
                    String resposta = scanner.nextLine().trim().toLowerCase();
                    if (resposta.equals("s") || resposta.equals("sim")) {
                        try {
                            jogador.getInventario().adicionarItem(bolacha);
                        } catch (InventarioCheioException e) {
                            System.out.println("Inventário cheio! Não foi possível adicionar Bolacha Rachada.");
                        }
                    } else {
                        System.out.println("Você deixou a bolacha para trás.");
                    }

                } else {
                    System.out.println("Você observa atentamente o local.");
                }
            }

            case 2 -> {
                if (ambiente instanceof Floresta) {
                    System.out.println("Você monta um abrigo improvisado com galhos.");
                } else if (ambiente instanceof Montanha) {
                    System.out.println("Você encontra restos de equipamentos congelados.");
                    Item equipamento = new Item("Equipamento Congelado", 3.0, 1);
                    System.out.print("Deseja coletar " + equipamento.getNome() + "? (s/n): ");
                    String resposta = scanner.nextLine().trim().toLowerCase();
                    if (resposta.equals("s") || resposta.equals("sim")) {
                        try {
                            jogador.getInventario().adicionarItem(equipamento);
                        } catch (InventarioCheioException e) {
                            System.out.println("Inventário cheio! Não foi possível adicionar Equipamento Congelado.");
                        }
                    } else {
                        System.out.println("Você deixou o equipamento para trás.");
                    }
                } else if (ambiente instanceof LagoRio) {
                    System.out.println("Você pesca um peixe pequeno.");
                    Alimentos peixe = new Alimentos("Peixe", 1.2, 1, 20, "Peixe", 2);
                    System.out.print("Deseja coletar " + peixe.getNome() + "? (s/n): ");
                    String resposta = scanner.nextLine().trim().toLowerCase();
                    if (resposta.equals("s") || resposta.equals("sim")) {
                        try {
                            jogador.getInventario().adicionarItem(peixe);
                        } catch (InventarioCheioException e) {
                            System.out.println("Inventário cheio! Não foi possível adicionar Peixe.");
                        }
                    } else {
                        System.out.println("Você deixou o peixe para trás.");
                    }
                } else if (ambiente instanceof Caverna) {
                    System.out.println("Você encontra carvão e ferramentas antigas.");
                    Item carvao = new Item("Carvão", 1.0, 2);
                    System.out.print("Deseja coletar " + carvao.getNome() + "? (s/n): ");
                    String resposta = scanner.nextLine().trim().toLowerCase();
                    if (resposta.equals("s") || resposta.equals("sim")) {
                        try {
                            jogador.getInventario().adicionarItem(carvao);
                        } catch (InventarioCheioException e) {
                            System.out.println("Inventário cheio! Não foi possível adicionar Carvão.");
                        }
                    } else {
                        System.out.println("Você deixou o carvão para trás.");
                    }
                } else if (ambiente instanceof Ruinas) {
                    System.out.println("Você estuda símbolos e ganha conhecimento.");
                } else {
                    System.out.println("Você caminha sem rumo definido.");
                }
            }

            case 3 -> {
                System.out.print("Digite o nome do item que deseja usar: ");
                String itemUsar = scanner.nextLine();
                jogador.usarItem(itemUsar);

            }

            case 4 -> {
                System.out.println("Você decide apenas descansar e observar o ambiente.");
                jogador.descansar();
            }

            default -> {
                System.out.println("Ação inválida para este ambiente.");
            }
        }

        jogador.consumirRecursosBasicos();
        try {
            jogador.verificarFomeSedeSanidade();
        } catch (FomeSedeSanidadeException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("O jogador não resistiu.");
            return;
        }

        System.out.println("\n--- Inventário atualizado ---");
        jogador.visualizarInventario();
    }

    private void menuAmbientes() {
        while (true) {
            System.out.println("\nEscolha para qual ambiente deseja mudar: ");
            System.out.println("1 - Floresta");
            System.out.println("2 - Caverna");
            System.out.println("3 - Lago/Rio");
            System.out.println("4 - Montanha");
            System.out.println("5 - Ruinas");
            System.out.println("0 - Voltar ao menu");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) break;

            switch (opcao) {
                case 1 -> {
                    try {
                        gerenciador.mudarAmbiente(jogador, floresta);
                    } catch (AmbienteInacessivelException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        gerenciador.mudarAmbiente(jogador, caverna);
                    } catch (AmbienteInacessivelException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        gerenciador.mudarAmbiente(jogador, lagorio);
                    } catch (AmbienteInacessivelException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        gerenciador.mudarAmbiente(jogador, montanha);
                    } catch (AmbienteInacessivelException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        gerenciador.mudarAmbiente(jogador, ruinas);
                    } catch (AmbienteInacessivelException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                }
                default -> System.out.println("Opção inválida.");
            }

            System.out.println("\nDeseja se mover para outro ambiente? (Sim/Não)");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("Sim")) break;
        }
    }
}
