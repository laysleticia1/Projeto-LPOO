package Jogo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Ambiente.Superclasse.Ambiente;
import Ambiente.Subclasses.Caverna;
import Ambiente.Subclasses.Floresta;
import Ambiente.Subclasses.LagoRio;
import Ambiente.Subclasses.Montanha;
import Ambiente.Subclasses.Ruinas;
import Excecoes.AmbienteInacessivelException;
import Excecoes.InventarioCheioException;
import Excecoes.MortePorFomeOuSedeException;
import Gerenciadores.GerenciadorDeAmbientes;
import Gerenciadores.GerenciadorDeEventos;
import Evento.Subclasses.Específicos.*;
import Personagem.Inventario.Inventario;
import Personagem.Superclasse.Personagem;
import Item.Superclasse.Item;

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
        scanner.nextLine(); // limpar buffer

        switch (option) {
            case 1 -> {
                criarPersonagem();
                configurarAmbientes();
                configurarEventos();
                introducao();
                loopJogo();
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
        scanner.nextLine(); // limpar buffer

        String classe = switch (escolha) {
            case 1 -> "Rastreador";
            case 2 -> "Mecânico";
            case 3 -> "Médico";
            case 4 -> "Sobrevivente Nato";
            default -> "Sobrevivente";
        };

        jogador = new Personagem(nome, classe);
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
        gerenciadorEventos.adicionarEvento(new CristalAzul());
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
        System.out.println("\nAmbiente inicial: " + jogador.getAmbienteAtual().getNome());
        System.out.println("Descrição: " + jogador.getAmbienteAtual().getDescricao());
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
                    }
                    case 4 -> menuAmbientes();
                    case 5 -> realizarAcoes();
                    case 6 -> explorarAmbiente();
                    case 0 -> {
                        gerenciador.mostrarHistorico();
                        gerenciadorEventos.mostrarHistoricoDeEventos();
                        System.out.println("Obrigado por jogar!");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }

                jogador.aumentarFome(10);
                jogador.aumentarSede(10);

            } catch (MortePorFomeOuSedeException e) {
                System.out.println("Morte" + e.getMessage());
                System.out.println("O jogador não resistiu.");
                return;
            }
        }
    }

        private void explorarAmbiente() {
            System.out.println("\nVocê decide explorar a área ao redor...");
            gerenciadorEventos.aplicarEventoAleatorio(jogador);
        }

    private void realizarAcoes() {
        System.out.println("\n--- Realizando Ações do Personagem ---");
        jogador.atacar();
        jogador.defender();
        jogador.correr();
        jogador.moverCima();
        jogador.moverDireita();
        jogador.agachar();

        System.out.println("\n--- Adicionando Itens ao Inventário ---");
        try {
            jogador.getInventario().adicionarItem(new Item("Lanterna", 1.5, 10));
            jogador.getInventario().adicionarItem(new Item("Garrafa de Água", 0.8, 10));
            jogador.getInventario().adicionarItem(new Item("Kit Médico", 2.0, 10));
            jogador.getInventario().adicionarItem(new Item( "Espada", 3.0, 10));
            jogador.getInventario().adicionarItem(new Item("Escudo", 4.0,10));
        } catch (InventarioCheioException e) {
            System.out.println("Erro ao adicionar item: " + e.getMessage());
        }

        System.out.println(" ");
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
