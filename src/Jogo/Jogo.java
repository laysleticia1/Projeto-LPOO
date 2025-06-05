package Jogo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import Ambiente.Superclasse.Ambiente;
import Ambiente.Subclasses.*;
import Excecoes.AmbienteInacessivelException;
import Excecoes.InventarioCheioException;
import Excecoes.FomeSedeSanidadeException;
import Gerenciadores.GerenciadorDeAmbientes;
import Gerenciadores.GerenciadorDeEventos;
import Gerenciadores.GerenciadorDeTurnos;
import Evento.Subclasses.Específicos.*;
import Personagem.Superclasse.Personagem;
import Item.Superclasse.*;
import Item.Subclasses.*;
import Personagem.Subclasses.*;
import UI.PainelJogo;

public class Jogo {
    private Scanner scanner = new Scanner(System.in);
    private GerenciadorDeAmbientes gerenciador = new GerenciadorDeAmbientes();
    private GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
    private Personagem jogador;
    private Ambiente floresta, caverna, lagorio, montanha, ruinas;
    private GerenciadorDeTurnos gerenciadorDeTurnos;

    public void iniciar() {
        System.out.println("\nBEM-VINDO AO JOGO DE SOBREVIVÊNCIA - ÚLTIMA FRONTEIRA");
        System.out.println("\nSelecione a opção escolhida: ");
        System.out.println("1 - Iniciar Jogo");
        System.out.println("2 - Sair");

        int option = -1;
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
        }
        scanner.nextLine();

        switch (option) {
            case 1:
                criarPersonagem();
                configurarAmbientes();
                configurarEventos();
                if (this.gerenciadorEventos != null) {
                    gerenciadorDeTurnos = new GerenciadorDeTurnos(this.gerenciadorEventos);
                } else {
                    System.err.println("ERRO: GerenciadorDeEventos não foi inicializado antes de GerenciadorDeTurnos!");
                    return;
                }
                introducao();
                loopJogo();
                break;
            case 2:
                System.out.println("Você decidiu não embarcar nesta aventura... Até a próxima!");
                return;
            default:
                System.out.println("Opção inválida. Reinicie o jogo para tentar novamente.");
                break;
        }
    }

    public void apresentarAcoesPorAmbiente(Personagem jogador) {
        if (jogador == null || jogador.getAmbienteAtual() == null) return;
        Ambiente ambiente = jogador.getAmbienteAtual();
        System.out.println("\n🔹 Ações disponíveis neste local:");

        if (ambiente instanceof Floresta) {
            System.out.println("1 - Coletar frutas");
            System.out.println("2 - Coletar madeira e cipós");
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
            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }
        scanner.nextLine();

        switch (escolha) {
            case 1: jogador = new Rastreador(nome); break;
            case 2: jogador = new Mecanico(nome); break;
            case 3: jogador = new Medico(nome); break;
            case 4: jogador = new SobreviventeNato(nome); break;
            default: jogador = new SobreviventeNato(nome); break;
        }
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
        if (jogador != null && ambientesDisponiveis != null && !ambientesDisponiveis.isEmpty()) {
            Ambiente ambienteInicial = ambientesDisponiveis.get(new Random().nextInt(ambientesDisponiveis.size()));
            jogador.setAmbienteAtual(ambienteInicial);
            gerenciador.registrarAmbienteInicial(ambienteInicial);
        } else {
            System.err.println("Erro: Jogador ou lista de ambientes não disponível para definir ambiente inicial.");
        }
    }

    private void configurarEventos() {
        if (gerenciadorEventos == null) {
            gerenciadorEventos = new GerenciadorDeEventos();
        }
        gerenciadorEventos.adicionarEvento(new EmboscadaLobos());
        gerenciadorEventos.adicionarEvento(new EnchenteRapida());
        gerenciadorEventos.adicionarEvento(new PoeiraToxica());
        gerenciadorEventos.adicionarEvento(new TempestadeMontanha());
    }

    private void introducao() {
        if (jogador == null) {
            System.err.println("ERRO: Jogador não inicializado para a introdução.");
            return;
        }
        System.out.println("\n>>>>> JOGO INICIADO <<<<<\n");
        System.out.println(jogador.getNome() + " desperta lentamente, sem saber como chegou naquele lugar.");
        System.out.println("Está sozinho/a, cercado/a por um ambiente desconhecido e cheio de perigos.");
        System.out.println("Será preciso explorar, coletar recursos e tomar boas decisões para sobreviver.");
        if (jogador.getAmbienteAtual() != null) {
            System.out.println("\n🔹 Ambiente inicial: " + jogador.getAmbienteAtual().getNome());
            System.out.println("Descrição: " + jogador.getAmbienteAtual().getDescricao());
            System.out.println("Clima: " + jogador.getAmbienteAtual().getCondicaoClimatica());
        } else {
            System.out.println("\n🔹 Ambiente inicial: Indefinido (ERRO NA CONFIGURAÇÃO)");
        }
    }

    private void loopJogo() {
        if (jogador == null || gerenciadorDeTurnos == null) {
            System.err.println("ERRO: Jogo não pode iniciar o loop principal. Jogador ou GerenciadorDeTurnos nulo.");
            return;
        }
        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1 - Ver status");
            System.out.println("2 - Ver inventário");
            System.out.println("3 - Usar item");
            System.out.println("4 - Remover item do inventário");
            System.out.println("5 - Mudar de ambiente");
            System.out.println("6 - Explorar ambiente");
            System.out.println("7 - Realizar ação comum");
            System.out.println("8 - Realizar ação especial");
            System.out.println("9 - Descansar");
            System.out.println("0 - Sair do jogo");

            int escolhaMenu = -1;
            if (scanner.hasNextInt()){
                escolhaMenu = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida, tente novamente.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            try {
                switch (escolhaMenu) {
                    case 1: jogador.getStatus(); break;
                    case 2: jogador.visualizarInventario(); break;
                    case 3:
                        System.out.print("Digite o nome do item que deseja usar: ");
                        String itemUsar = scanner.nextLine();
                        jogador.usarItem(itemUsar);
                        jogador.diminuirFome(1);
                        jogador.diminuirSede(2);
                        break;
                    case 4:
                        System.out.print("Digite o nome do item que deseja remover: ");
                        String itemRemover = scanner.nextLine();
                        if (jogador.getInventario() != null) {
                            jogador.getInventario().removerItem(itemRemover);
                        }
                        break;
                    case 5:
                        menuAmbientes();
                        jogador.diminuirFome(8);
                        jogador.diminuirSede(10);
                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                        break;
                    case 6:
                        explorarAmbiente();
                        jogador.diminuirFome(3);
                        jogador.diminuirSede(4);
                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                        break;
                    case 7:
                        realizarAcoes();
                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                        break;
                    case 8:
                        if (jogador instanceof Rastreador rastreador) {
                            System.out.println("1 - Identificar pegadas");
                            System.out.println("2 - Farejar trilha");
                            System.out.println("3 - Procurar recursos no ambiente");
                            int escolhaAcaoR = scanner.nextInt(); scanner.nextLine();
                            if (escolhaAcaoR == 1) rastreador.identificarPegadas(jogador.getAmbienteAtual());
                            else if (escolhaAcaoR == 2) rastreador.farejarTrilha(jogador.getAmbienteAtual());
                            else if (escolhaAcaoR == 3) rastreador.procurarRecursos(jogador.getAmbienteAtual(), jogador);
                            else System.out.println("Opção inválida.");
                        } else if (jogador instanceof Mecanico mecanico) {
                            System.out.println("1 - Consertar equipamento");
                            System.out.println("2 - Melhorar arma");
                            int escolhaAcaoM = scanner.nextInt(); scanner.nextLine();
                            if (escolhaAcaoM == 1) mecanico.consertarEquipamento();
                            else if (escolhaAcaoM == 2) mecanico.melhorarArma();
                            else System.out.println("Opção inválida.");
                        } else if (jogador instanceof Medico medico) {
                            System.out.println("1 - Curar a si mesmo");
                            System.out.println("2 - Curar outro personagem");
                            System.out.println("3 - Preparar remédio natural");
                            int escolhaAcaoMd = scanner.nextInt(); scanner.nextLine();
                            if (escolhaAcaoMd == 1) medico.autoCurarFerimentosLeves();
                            else if (escolhaAcaoMd == 2) System.out.println("Ainda não há outro personagem disponível.");
                            else if (escolhaAcaoMd == 3) medico.prepararRemedioNatural();
                            else System.out.println("Opção inválida.");
                        } else if (jogador instanceof SobreviventeNato sobrevivente) {
                            System.out.println("1 - Fabricar lança");
                            System.out.println("2 - Caçar animais");
                            int escolhaAcaoS = scanner.nextInt(); scanner.nextLine();
                            if (escolhaAcaoS == 1) sobrevivente.fabricarLanca();
                            else if (escolhaAcaoS == 2) sobrevivente.cacarAnimais();
                            else System.out.println("Opção inválida.");
                        }
                        jogador.diminuirFome(2);
                        jogador.diminuirSede(3);
                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                        break;
                    case 9:
                        if (jogador instanceof SobreviventeNato sobrevivente) {
                            sobrevivente.montarAbrigoImprovisado(jogador.getAmbienteAtual());
                            System.out.println("Você descansou com segurança por ter montado um abrigo improvisado.");
                        } else {
                            System.out.println("Você se deita para descansar...");
                            double chance = Math.random();
                            if (gerenciadorEventos != null) {
                                if (chance < 0.25) {
                                    gerenciadorEventos.aplicarEventoCriaturaDuranteDescanso(jogador);
                                } else if (chance < 0.50) {
                                    gerenciadorEventos.aplicarEventoClimaticoDuranteDescanso(jogador);
                                } else {
                                    System.out.println("O descanso foi tranquilo.");
                                }
                            } else {
                                System.out.println("O descanso foi tranquilo (gerenciador de eventos não disponível).");
                            }
                        }
                        jogador.descansar();
                        jogador.consumirRecursosBasicos();
                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                        break;
                    case 0:
                        if (gerenciador != null) gerenciador.mostrarHistorico();
                        if (gerenciadorEventos != null) gerenciadorEventos.mostrarHistoricoDeEventos();
                        System.out.println("Obrigado por jogar!");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                jogador.verificarFomeSedeSanidade();
            } catch (FomeSedeSanidadeException e) {
                System.out.println(e.getMessage());
                System.out.println("O jogador não resistiu. Fim de jogo.");
                return;
            } catch (RuntimeException e) {
                System.out.println("Um erro inesperado ocorreu: " + e.getMessage());
                e.printStackTrace();
                System.out.println("O jogador não resistiu. Fim de jogo.");
                return;
            }
        }
    }

    private void explorarAmbiente() {
        System.out.print("\nVocê decide explorar a área ao redor...");
        if (jogador != null && jogador.getAmbienteAtual() != null) {
            if (gerenciadorEventos != null) {
                gerenciadorEventos.aplicarEventoAleatorioPorAmbiente(jogador);
            } else {
                System.out.println(" (Gerenciador de eventos não disponível para esta ação).");
            }
        } else {
            System.out.println("Erro: Jogador ou Ambiente atual não definido para exploração.");
        }
    }

    private void realizarAcoes() {
        if (jogador == null) return;
        apresentarAcoesPorAmbiente(jogador);
        System.out.print("\nEscolha uma ação: ");
        int escolha = -1;
        if(scanner.hasNextInt()){
            escolha = scanner.nextInt();
        } else {
            System.out.println("Entrada inválida.");
            scanner.next();
            return;
        }
        scanner.nextLine();

        Ambiente ambiente = jogador.getAmbienteAtual();
        if (ambiente == null) {
            System.out.println("Erro: Ambiente atual não definido para realizar ações.");
            return;
        }

        switch (escolha) {
            case 1:
                if (ambiente instanceof Floresta) {
                    System.out.println("Você encontra frutas frescas da floresta.");
                    Alimentos frutas = new Alimentos("Frutas Silvestres", 0.5, 20, 10, "Fruta", 3);
                    adicionarItemColetado(frutas);
                } else if (ambiente instanceof Montanha) {
                    System.out.println("Você escala e encontra uma pedra afiada, útil como ferramenta ou arma improvisada.");
                    Material pedra = new Material("Pedra Afiada", "Ferramenta básica", 1.0, 1, 30);
                    adicionarItemColetado(pedra);
                } else if (ambiente instanceof LagoRio) {
                    System.out.println("Você bebe água fresca do lago/rio, se hidratando.");
                    jogador.restaurarSede(25);
                    jogador.restaurarEnergia(5);
                } else if (ambiente instanceof Caverna) {
                    System.out.println("Você acende uma tocha e encontra um veio de minério brilhante.");
                    Material minerio = new Material("Minério Brilhante", "Recurso valioso", 2.0, 1, 50);
                    adicionarItemColetado(minerio);
                } else if (ambiente instanceof Ruinas) {
                    System.out.println("Você vasculha os escombros e encontra uma antiga ração de emergência, ainda selada.");
                    Alimentos racao = new Alimentos("Ração de Emergência Antiga", 0.3, 30, 5, "Industrializado", 100);
                    adicionarItemColetado(racao);
                } else {
                    System.out.println("Você observa atentamente o local, mas não encontra nada de especial para interagir desta forma.");
                }
                break;
            case 2:
                if (ambiente instanceof Floresta) {
                    System.out.println("Você encontra madeira resistente e cipós entre as árvores.");
                    Material madeira = new Material("Madeira Bruta", "Madeira", 2.0, 1, 30);
                    Material cipo = new Material("Cipó", "Fibra", 0.8, 1, 15);
                    adicionarItemColetado(madeira);
                    adicionarItemColetado(cipo);
                } else if (ambiente instanceof Montanha) {
                    System.out.println("Você procura por itens úteis congelados e encontra um pedaço de couro antigo preservado no gelo.");
                    Material couro = new Material("Couro Antigo Congelado", "Material para vestimentas", 1.2, 1, 45);
                    adicionarItemColetado(couro);
                } else if (ambiente instanceof LagoRio) {
                    System.out.println("Você tenta pescar com as mãos... e com sorte consegue um pequeno peixe!");
                    Alimentos peixe = new Alimentos("Peixe Pequeno Cru", 0.4, 15, 5, "Carne de Peixe", 1);
                    adicionarItemColetado(peixe);
                } else if (ambiente instanceof Caverna) {
                    System.out.println("Você busca por minerais e encontra alguns fragmentos de carvão.");
                    Material carvao = new Material("Carvão", "Combustível", 0.5, 5, 20);
                    adicionarItemColetado(carvao);
                } else if (ambiente instanceof Ruinas) {
                    System.out.println("Você analisa símbolos misteriosos e sente sua mente se expandir um pouco, mas também um arrepio...");
                    jogador.restaurarSanidade(5);
                    jogador.diminuirEnergia(5);
                }
                break;
            case 3:
                System.out.print("Digite o nome do item que deseja usar: ");
                String itemUsar = scanner.nextLine();
                jogador.usarItem(itemUsar);
                break;
            default:
                System.out.println("Ação inválida para este ambiente ou escolha.");
                break;
        }
        jogador.diminuirFome(5);
        jogador.diminuirSede(7);
        try {
            jogador.verificarFomeSedeSanidade();
        } catch (FomeSedeSanidadeException e) {
            System.out.println(e.getMessage());
            System.out.println("O jogador não resistiu. Fim de jogo.");
        } catch (RuntimeException e) {
            System.out.println("Um erro inesperado ocorreu: " + e.getMessage());
            e.printStackTrace();
            System.out.println("O jogador não resistiu. Fim de jogo.");
        }
    }

    private void adicionarItemColetado(Item item) {
        if (jogador == null || item == null) return;
        System.out.println("🔸 Item encontrado: " + item.getNome());
        System.out.println("Descrição: " + item.getDescricaoItem());

        System.out.print("Deseja coletar " + item.getNome() + "? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        if (resposta.equals("s") || resposta.equals("sim")) {
            try {
                if (jogador.getInventario() != null) {
                    jogador.getInventario().adicionarItem(item);
                }
            } catch (InventarioCheioException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Você deixou o item para trás.");
        }
    }

    private void menuAmbientes() {
        if (jogador == null || gerenciador == null) return;
        while (true) {
            System.out.println("\nEscolha para qual ambiente deseja mudar: ");
            gerenciador.listarAmbientesNumerados();
            System.out.println("0 - Voltar ao menu");

            int opcao = -1;
            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            if (opcao == 0) break;

            Ambiente destino = gerenciador.getAmbientePorIndice(opcao -1);
            if(destino != null) {
                try {
                    gerenciador.mudarAmbiente(jogador, destino);
                    if (jogador.getAmbienteAtual() == destino) break;
                } catch (AmbienteInacessivelException e) {
                    System.out.println("⚠️ " + e.getMessage());
                }
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    public boolean iniciarNovaPartida(String nome, String classe) {
        try {
            switch (classe) {
                case "Rastreador": jogador = new Rastreador(nome); break;
                case "Mecânico": jogador = new Mecanico(nome); break;
                case "Médico": jogador = new Medico(nome); break;
                case "Sobrevivente Nato": jogador = new SobreviventeNato(nome); break;
                default:
                    System.err.println("Classe inválida fornecida para iniciarNovaPartida: " + classe);
                    return false;
            }
            configurarAmbientes();
            configurarEventos();
            if (this.gerenciadorEventos != null) {
                gerenciadorDeTurnos = new GerenciadorDeTurnos(this.gerenciadorEventos);
            } else {
                System.err.println("ERRO: GerenciadorDeEventos não inicializado ao configurar GerenciadorDeTurnos em nova partida.");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao iniciar nova partida: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String getTextoIntroducao() {
        if (jogador == null || jogador.getAmbienteAtual() == null) {
            return "Erro: Jogador ou ambiente atual não inicializado para introdução.";
        }
        return jogador.getNome() + " desperta em meio ao desconhecido, sem lembranças de como chegou ali.\n"
                + "O ambiente ao redor parece hostil, mas também cheio de possibilidades.\n\n"
                + "🔹 Ambiente inicial: " + jogador.getAmbienteAtual().getNome() + "\n"
                + "Descrição: " + jogador.getAmbienteAtual().getDescricao() + "\n"
                + "Clima: " + jogador.getAmbienteAtual().getCondicaoClimatica() + "\n\n"
                + "É hora de começar a lutar pela sobrevivência...";
    }

    public Personagem getJogador() {
        return jogador;
    }

    public GerenciadorDeAmbientes getGerenciadorDeAmbientes() {
        return this.gerenciador;
    }

    public GerenciadorDeEventos getGerenciadorDeEventos() {
        return this.gerenciadorEventos;
    }

    public Ambiente getAmbientePorNome(String nome) {
        if (gerenciador == null || gerenciador.getAmbientes() == null) return null;
        for (Ambiente ambiente : gerenciador.getAmbientes()) {
            if (ambiente.getNome().equalsIgnoreCase(nome)) {
                return ambiente;
            }
        }
        return null;
    }

    public void mudarAmbiente(String nomeAmbiente) throws AmbienteInacessivelException {
        Ambiente novoAmbiente = getAmbientePorNome(nomeAmbiente);
        if (novoAmbiente == null) {
            throw new AmbienteInacessivelException("Ambiente '" + nomeAmbiente + "' não encontrado.");
        }
        if (jogador == null) {
            throw new IllegalStateException("Jogador não inicializado para mudar de ambiente.");
        }
        gerenciador.mudarAmbiente(jogador, novoAmbiente);
    }

    public void mudarAmbienteViaInterface(String nomeAmbiente, JTextArea areaLog, JLabel imagemAmbienteLabel) {
        if (jogador == null) {
            areaLog.append("Erro: Jogador não inicializado.\n");
            return;
        }
        if (gerenciador == null) {
            areaLog.append("Erro: Gerenciador de Ambientes não inicializado.\n");
            return;
        }
        if (gerenciadorDeTurnos == null) {
            areaLog.append("Erro: Gerenciador de Turnos não inicializado.\n");
            return;
        }


        Ambiente novoAmbiente = getAmbientePorNome(nomeAmbiente);
        if (novoAmbiente == null) {
            areaLog.append("Ambiente '" + nomeAmbiente + "' não encontrado.\n");
            return;
        }

        try {
            gerenciador.mudarAmbienteInterface(jogador, novoAmbiente, areaLog);

            Ambiente ambienteAtualizado = jogador.getAmbienteAtual();
            if (ambienteAtualizado != null && imagemAmbienteLabel != null && ambienteAtualizado.getCaminhoImagem() != null && !ambienteAtualizado.getCaminhoImagem().isEmpty()) {
                try {
                    java.net.URL imgUrl = getClass().getResource(ambienteAtualizado.getCaminhoImagem());
                    if (imgUrl != null) {
                        ImageIcon icon = new ImageIcon(imgUrl);
                        imagemAmbienteLabel.setIcon(icon);
                        imagemAmbienteLabel.setText(null);
                    } else {
                        areaLog.append("Aviso: Imagem para " + ambienteAtualizado.getNome() + " não encontrada em '" + ambienteAtualizado.getCaminhoImagem() + "'.\n");
                        imagemAmbienteLabel.setIcon(null);
                        imagemAmbienteLabel.setText("Imagem não encontrada");
                    }
                } catch (Exception e) {
                    areaLog.append("Erro ao carregar imagem para " + ambienteAtualizado.getNome() + ": " + e.getMessage() + "\n");
                    imagemAmbienteLabel.setIcon(null);
                    imagemAmbienteLabel.setText("Erro ao carregar imagem");
                }
            } else if (imagemAmbienteLabel != null) {
                imagemAmbienteLabel.setIcon(null);
                imagemAmbienteLabel.setText("Ambiente sem imagem definida");
            }

            if (!gerenciadorDeTurnos.executarTurnoInterface(jogador, true, areaLog)) {
                areaLog.append("O jogo terminou devido às condições do jogador.\n");
            }
        } catch (AmbienteInacessivelException e) {
            areaLog.append("Não foi possível mover: " + e.getMessage() + "\n");
        } catch (Exception e) {
            areaLog.append("Erro inesperado ao mudar de ambiente: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }


    public Ambiente getAmbienteAtual() {
        return jogador != null ? jogador.getAmbienteAtual() : null;
    }

    public void executarAcaoInterface(int escolha, JTextArea areaLog) {
        if (jogador == null || jogador.getAmbienteAtual() == null) {
            areaLog.append("Erro: Jogador ou ambiente não definido para executar ação.\n");
            return;
        }
        areaLog.append("Ação " + escolha + " selecionada (lógica específica da ação na UI a ser implementada aqui ou em métodos dedicados).\n");
    }

    public List<String> getAcoesComunsDisponiveis(Personagem jogador) {
        List<String> acoes = new ArrayList<>();
        if (jogador == null || jogador.getAmbienteAtual() == null) return acoes;
        Ambiente ambiente = jogador.getAmbienteAtual();

        if (ambiente instanceof Floresta) {
            acoes.add("Coletar frutas");
            acoes.add("Coletar madeira e cipós");
        } else if (ambiente instanceof Montanha) {
            acoes.add("Escalar para encontrar abrigo natural");
            acoes.add("Procurar itens congelados no alto");
        } else if (ambiente instanceof LagoRio) {
            acoes.add("Beber água diretamente");
            acoes.add("Pescar");
        } else if (ambiente instanceof Caverna) {
            acoes.add("Acender tochas e explorar");
            acoes.add("Buscar minerais úteis");
        } else if (ambiente instanceof Ruinas) {
            acoes.add("Vasculhar ruínas por suprimentos antigos");
            acoes.add("Analisar símbolos misteriosos");
        } else {
            acoes.add("Explorar o local");
        }
        acoes.add("Descansar");

        return acoes;
    }

    public void executarAcaoComumInterface(String nomeAcao, JTextArea areaLog) {
        if (jogador == null || jogador.getAmbienteAtual() == null || areaLog == null) {
            if (areaLog != null) areaLog.append("Erro: Jogador ou ambiente não definido, ou área de log nula.\n");
            return;
        }
        Ambiente ambiente = jogador.getAmbienteAtual();
        boolean acaoRealizada = false;

        try {
            if (ambiente instanceof Floresta) {
                if (nomeAcao.equals("Coletar frutas")) {
                    areaLog.append("Você encontra frutas frescas da floresta.\n");
                    Alimentos frutas = new Alimentos("Frutas Silvestres", 0.5, 20, 10, "Fruta", 3);
                    String mensagem = "🔍 Você encontrou: " + frutas.getNome() + "\n\n" + frutas.exibirDetalhesInterface() + "\n\nDeseja adicionar este item ao inventário?";
                    int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Item Encontrado", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            jogador.getInventario().adicionarItem(frutas);
                            areaLog.append("Item '" + frutas.getNome() + "' adicionado ao inventário.\n");
                        } catch (InventarioCheioException e) {
                            areaLog.append("⚠️ Inventário cheio! Não foi possível adicionar o item.\n");
                        }
                    } else {
                        areaLog.append("Você decidiu não pegar o item.\n");
                    }
                    acaoRealizada = true;
                } else if (nomeAcao.equals("Coletar madeira e cipós")) {
                    areaLog.append("Você encontra madeira resistente e cipós entre as árvores.\n");
                    Material madeira = new Material("Madeira Resistente", "Recurso de construção", 2.0, 1, 30);
                    Material cipo = new Material("Cipó Resistente", "Material para amarras", 0.8, 1, 15);
                    for (Item item : new Item[]{madeira, cipo}) {
                        String mensagem = "🔍 Você encontrou: " + item.getNome() + "\n\n" + item.exibirDetalhesInterface() + "\n\nDeseja adicionar este item ao inventário?";
                        int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Item Encontrado", JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            try {
                                jogador.getInventario().adicionarItem(item);
                                areaLog.append("Item '" + item.getNome() + "' adicionado ao inventário.\n");
                            } catch (InventarioCheioException e) {
                                areaLog.append("⚠️ Inventário cheio! Não foi possível adicionar o item.\n");
                            }
                        } else {
                            areaLog.append("Você decidiu não pegar o item: " + item.getNome() + "\n");
                        }
                    }
                    acaoRealizada = true;
                }
            } else if (ambiente instanceof Montanha) {
                if (nomeAcao.equals("Escalar para encontrar abrigo natural")) {
                    areaLog.append("Você escala a montanha e encontra um abrigo natural entre as rochas.\n");
                    Material pedra = new Material("Pedra Afiada", "Ferramenta básica", 1.0, 1, 30);
                    String mensagem = "🔍 Você encontrou: " + pedra.getNome() + "\n\n" + pedra.exibirDetalhesInterface() + "\n\nDeseja adicionar este item ao inventário?";
                    int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Item Encontrado", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            jogador.getInventario().adicionarItem(pedra);
                            areaLog.append("Item '" + pedra.getNome() + "' adicionado ao inventário.\n");
                        } catch (InventarioCheioException e) {
                            areaLog.append("⚠️ Inventário cheio! Não foi possível adicionar o item.\n");
                        }
                    } else {
                        areaLog.append("Você decidiu não pegar o item.\n");
                    }
                    acaoRealizada = true;
                } else if (nomeAcao.equals("Procurar itens congelados no alto")) {
                    areaLog.append("Você encontra um pedaço de couro antigo preservado no gelo.\n");
                    Material couro = new Material("Couro Antigo Congelado", "Material para vestimentas", 1.2, 1, 45);
                    String mensagem = "🔍 Você encontrou: " + couro.getNome() + "\n\n" + couro.exibirDetalhesInterface() + "\n\nDeseja adicionar este item ao inventário?";
                    int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Item Encontrado", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            jogador.getInventario().adicionarItem(couro);
                            areaLog.append("Item '" + couro.getNome() + "' adicionado ao inventário.\n");
                        } catch (InventarioCheioException e) {
                            areaLog.append("⚠️ Inventário cheio! Não foi possível adicionar o item.\n");
                        }
                    } else {
                        areaLog.append("Você decidiu não pegar o item.\n");
                    }
                    acaoRealizada = true;
                }
            } else if (ambiente instanceof LagoRio) {
                if (nomeAcao.equals("Beber água diretamente")) {
                    areaLog.append("Você bebe água fresca do lago/rio, se hidratando.\n");
                    jogador.restaurarSede(25);
                    jogador.restaurarEnergia(5);
                    acaoRealizada = true;
                } else if (nomeAcao.equals("Pescar")) {
                    areaLog.append("Você tenta pescar com as mãos... e com sorte consegue um pequeno peixe!\n");
                    Alimentos peixe = new Alimentos("Peixe Pequeno Cru", 0.4, 15, 5, "Carne de Peixe", 1);
                    String mensagem = "🔍 Você encontrou: " + peixe.getNome() + "\n\n" + peixe.exibirDetalhesInterface() + "\n\nDeseja adicionar este item ao inventário?";
                    int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Item Encontrado", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            jogador.getInventario().adicionarItem(peixe);
                            areaLog.append("Item '" + peixe.getNome() + "' adicionado ao inventário.\n");
                        } catch (InventarioCheioException e) {
                            areaLog.append("⚠️ Inventário cheio! Não foi possível adicionar o item.\n");
                        }
                    } else {
                        areaLog.append("Você decidiu não pegar o item.\n");
                    }
                    acaoRealizada = true;
                }
            } else if (ambiente instanceof Caverna) {
                if (nomeAcao.equals("Acender tochas e explorar")) {
                    areaLog.append("Você acende uma tocha e encontra um veio de minério brilhante.\n");
                    Material minerio = new Material("Minério Brilhante", "Recurso valioso", 2.0, 1, 50);
                    String mensagem = "🔍 Você encontrou: " + minerio.getNome() + "\n\n" + minerio.exibirDetalhesInterface() + "\n\nDeseja adicionar este item ao inventário?";
                    int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Item Encontrado", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            jogador.getInventario().adicionarItem(minerio);
                            areaLog.append("Item '" + minerio.getNome() + "' adicionado ao inventário.\n");
                        } catch (InventarioCheioException e) {
                            areaLog.append("⚠️ Inventário cheio! Não foi possível adicionar o item.\n");
                        }
                    } else {
                        areaLog.append("Você decidiu não pegar o item.\n");
                    }
                    acaoRealizada = true;
                } else if (nomeAcao.equals("Buscar minerais úteis")) {
                    areaLog.append("Você busca por minerais e encontra alguns fragmentos de carvão.\n");
                    Material carvao = new Material("Carvão", "Combustível", 0.5, 5, 20);
                    String mensagem = "🔍 Você encontrou: " + carvao.getNome() + "\n\n" + carvao.exibirDetalhesInterface() + "\n\nDeseja adicionar este item ao inventário?";
                    int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Item Encontrado", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            jogador.getInventario().adicionarItem(carvao);
                            areaLog.append("Item '" + carvao.getNome() + "' adicionado ao inventário.\n");
                        } catch (InventarioCheioException e) {
                            areaLog.append("⚠️ Inventário cheio! Não foi possível adicionar o item.\n");
                        }
                    } else {
                        areaLog.append("Você decidiu não pegar o item.\n");
                    }
                    acaoRealizada = true;
                }
            } else if (ambiente instanceof Ruinas) {
                if (nomeAcao.equals("Vasculhar ruínas por suprimentos antigos")) {
                    areaLog.append("Você vasculha os escombros e encontra uma antiga ração de emergência, ainda selada.\n");
                    Alimentos racao = new Alimentos("Ração de Emergência Antiga", 0.3, 30, 5, "Industrializado", 100);
                    String mensagem = "🔍 Você encontrou: " + racao.getNome() + "\n\n" + racao.exibirDetalhesInterface() + "\n\nDeseja adicionar este item ao inventário?";
                    int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Item Encontrado", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            jogador.getInventario().adicionarItem(racao);
                            areaLog.append("Item '" + racao.getNome() + "' adicionado ao inventário.\n");
                        } catch (InventarioCheioException e) {
                            areaLog.append("⚠️ Inventário cheio! Não foi possível adicionar o item.\n");
                        }
                    } else {
                        areaLog.append("Você decidiu não pegar o item.\n");
                    }
                    acaoRealizada = true;
                } else if (nomeAcao.equals("Analisar símbolos misteriosos")) {
                    areaLog.append("Você analisa símbolos misteriosos e sente sua mente se expandir, mas também um arrepio...\n");
                    jogador.restaurarSanidade(5);
                    jogador.diminuirEnergia(5);
                    acaoRealizada = true;
                }
            }

            if (nomeAcao.equals("Descansar")) {
                if (jogador instanceof SobreviventeNato sobrevivente) {
                    areaLog.append("Você monta um abrigo improvisado e descansa com segurança.\n");
                    sobrevivente.montarAbrigoImprovisado(jogador.getAmbienteAtual());
                } else {
                    areaLog.append("Você se deita para descansar...\n");
                    if (gerenciadorEventos != null) {
                        gerenciadorEventos.aplicarEventoDuranteDescansoInterface(jogador, jogador.getAmbienteAtual(), areaLog);
                    } else {
                        areaLog.append("O descanso foi tranquilo (sem eventos).\n");
                    }
                }
                jogador.descansar();
                jogador.consumirRecursosBasicos();
                acaoRealizada = true;
            }

            if (nomeAcao.equals("Explorar o local")) {
                areaLog.append("Você observa atentamente o ambiente.\n");
                if (gerenciadorEventos != null) {
                    gerenciadorEventos.dispararEventoExploracaoInterface(jogador, areaLog);
                }
                acaoRealizada = true;
            }

            if (acaoRealizada) {
                jogador.diminuirFome(5);
                jogador.diminuirSede(7);
                jogador.verificarFomeSedeSanidadeInterface(areaLog);
                if (gerenciadorDeTurnos != null) {
                    if (!gerenciadorDeTurnos.executarTurnoInterface(jogador, true, areaLog)) {
                        areaLog.append("O jogo terminou devido às condições do jogador.\n");
                    }
                }
            } else {
                areaLog.append("❌ Ação '" + nomeAcao + "' não aplicável ou não reconhecida neste ambiente.\n");
            }

        } catch (FomeSedeSanidadeException e) {
            areaLog.append("⚠️ " + e.getMessage() + "\n");
            if (jogador.getVida() <= 0) {
                areaLog.append("O jogador não resistiu. Fim de jogo.\n");
            }
        } catch (Exception e) {
            areaLog.append("🚫 Erro inesperado ao executar ação: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    public List<String> getAcoesEspeciaisDisponiveis(Personagem jogador) {
        List<String> acoes = new ArrayList<>();
        if (jogador == null) return acoes;

        if (jogador instanceof Rastreador) {
            acoes.add("Identificar pegadas");
            acoes.add("Farejar trilha");
            acoes.add("Procurar recursos (especial)");
        } else if (jogador instanceof Mecanico) {
            acoes.add("Consertar equipamento");
            acoes.add("Melhorar arma");
        } else if (jogador instanceof Medico) {
            acoes.add("Auto-curar ferimentos leves");
            acoes.add("Preparar remédio natural");
        } else if (jogador instanceof SobreviventeNato) {
            acoes.add("Fabricar lança");
            acoes.add("Caçar animais (especial)");
        }
        return acoes;
    }

    public void executarAcaoEspecialInterface(String nomeAcao, JTextArea areaLog) {
        if (jogador == null || areaLog == null) {
            if (areaLog != null) areaLog.append("Erro: Jogador não inicializado.\n");
            return;
        }
        boolean acaoRealizada = false;
        try {
            if (jogador instanceof Rastreador rastreador) {
                switch (nomeAcao) {
                    case "Identificar pegadas":
                        areaLog.append(jogador.getNome() + " se agacha e observa o solo em busca de rastros...\n");
                        rastreador.identificarPegadasInterface(jogador.getAmbienteAtual(), areaLog);
                        acaoRealizada = true;
                        break;

                    case "Farejar trilha":
                        areaLog.append(jogador.getNome() + " fareja o ar e examina o ambiente em busca de pistas...\n");
                        rastreador.farejarTrilhaInterface(jogador.getAmbienteAtual(), areaLog);
                        acaoRealizada = true;
                        break;

                    case "Procurar recursos (especial)":
                        areaLog.append(jogador.getNome() + " realiza uma busca cuidadosa por recursos escondidos...\n");
                        rastreador.procurarRecursosInterface(jogador.getAmbienteAtual(), jogador, areaLog);
                        acaoRealizada = true;
                        break;
                }
            } else if (jogador instanceof Mecanico mecanico) {
                switch (nomeAcao) {
                    case "Consertar equipamento":
                        mecanico.consertarEquipamentoInterface(areaLog);
                        acaoRealizada = true;
                        break;

                    case "Melhorar arma":
                        mecanico.melhorarArmaInterface(areaLog);
                        acaoRealizada = true;
                        break;
                }
            } else if (jogador instanceof Medico medico) {
                switch (nomeAcao) {
                    case "Auto-curar ferimentos leves":
                        medico.autoCurarFerimentosLevesInterface(areaLog);
                        acaoRealizada = true;
                        break;

                    case "Preparar remédio natural":
                        medico.prepararRemedioNaturalInterface(areaLog);
                        acaoRealizada = true;
                        break;
                }
            } else if (jogador instanceof SobreviventeNato sobrevivente) {
                switch (nomeAcao) {
                    case "Fabricar lança":
                        sobrevivente.fabricarLancaInterface(areaLog);
                        acaoRealizada = true;
                        break;

                    case "Caçar animais (especial)":
                        areaLog.append(jogador.getNome() + " se embrenha na mata em busca de caça...\n");
                        sobrevivente.cacarAnimaisInterface(areaLog);
                        acaoRealizada = true;
                        break;
                }
            }

            if (acaoRealizada) {
                jogador.diminuirFome(2);
                jogador.diminuirSede(3);
                jogador.verificarFomeSedeSanidadeInterface(areaLog);
                if (gerenciadorDeTurnos != null) {
                    if (!gerenciadorDeTurnos.executarTurnoInterface(jogador, true, areaLog)) {
                        areaLog.append("O jogo terminou devido às condições do jogador.\n");
                    }
                }
            } else {
                areaLog.append("❌ Ação especial '" + nomeAcao + "' não reconhecida para " + jogador.getClasse() + ".\n");
            }
        } catch (FomeSedeSanidadeException e) {
            areaLog.append("⚠️ " + e.getMessage() + "\n");
            if (jogador.getVida() <= 0) {
                areaLog.append("O jogador não resistiu. Fim de jogo.\n");
            }
        } catch (Exception e) {
            areaLog.append("Erro inesperado ao executar ação especial: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }


}

