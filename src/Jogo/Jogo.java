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
import Gerenciadores.*;

public class Jogo {
    private Scanner scanner = new Scanner(System.in);
    private GerenciadorDeAmbientes gerenciador = new GerenciadorDeAmbientes();
    private GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
    private Personagem jogador; // Este é o nosso jogador
    private Ambiente floresta, caverna, lagorio, montanha, ruinas;
    private GerenciadorDeTurnos gerenciadorDeTurnos;

    public Jogo() {
        System.out.println("Iniciando Jogo"); // Seu construtor original
    }

    public boolean iniciarNovaPartida(String nomePersonagem, String classeSelecionada) {
        // 'nomePersonagem' e 'classeSelecionada' são parâmetros, então estão declarados.
        if (nomePersonagem == null || nomePersonagem.trim().isEmpty()) {
            System.err.println("Nome do personagem não pode ser vazio! ");
            return false;
        }

        switch (classeSelecionada) {
            case "Rastreador":
                this.jogador = new Rastreador(nomePersonagem); // Usa o parâmetro 'nomePersonagem'
                break;
            case "Mecânico":
                this.jogador = new Mecanico(nomePersonagem);   // Usa o parâmetro 'nomePersonagem'
                break;
            case "Médico":
                this.jogador = new Medico(nomePersonagem);     // Usa o parâmetro 'nomePersonagem'
                break;
            case "Sobrevivente Nato":
                this.jogador = new SobreviventeNato(nomePersonagem); // Usa o parâmetro 'nomePersonagem'
                break;
            default:
                System.err.println("Classe de personagem desconhecida: " + classeSelecionada);
                return false;
        }

        // Usa os getters da classe Personagem. Se 'Personagem' tiver getNome() e getClasse(), está OK.
        System.out.println("Personagem criado: " + this.jogador.getNome() + " - Classe: " + this.jogador.getClasse());

        configurarAmbientes();
        configurarEventos();

        return true;
    }

    public String getTextoIntroducao() {
        if (this.jogador == null || this.jogador.getAmbienteAtual() == null) {
            return "Erro: Personagem ou ambiente inicial não configurado!";
        }
        // Todos usam getters, o que deve ser seguro.
        return "\n>>>>> JOGO INICIADO <<<<<\n" +
                this.jogador.getNome() + " desperta lentamente, sem saber como chegou naquele lugar.\n" +
                "Está sozinho/a, cercado/a por um ambiente desconhecido e cheio de perigos.\n" +
                "Será preciso explorar, coletar recursos e tomar boas decisões para sobreviver.\n" +
                "\n🔹 Ambiente inicial: " + this.jogador.getAmbienteAtual().getNome() + "\n" +
                "Descrição: " + this.jogador.getAmbienteAtual().getDescricao() + "\n" +
                "Clima: " + this.jogador.getAmbienteAtual().getCondicaoClimatica();
    }

    public Personagem getJogador() {
        return jogador;
    }

    public void iniciar() {
        System.out.println("\nBEM-VINDO AO JOGO DE SOBREVIVÊNCIA - ÚLTIMA FRONTEIRA");
        System.out.println("\nSelecione a opção escolhida: ");
        System.out.println("1 - Iniciar Jogo");
        System.out.println("2 - Sair");

        int option = -1;
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
        } else {
            System.out.println("Entrada inválida.");
            if (scanner.hasNextLine()) scanner.next();
            return;
        }
        scanner.nextLine();

        switch (option) {

            case 1:
                // Chama o método que usa 'String nome' e 'String classe' locais
                criarPersonagemOriginalConsole(); // Renomeado para clareza, usa subclasses
                if (this.jogador != null) {
                    configurarAmbientes();
                    configurarEventos();
                    introducaoOriginalConsole(); // Renomeado para clareza
                    loopJogo();
                }
                break;
            case 2:   

                System.out.println("Você decidiu não embarcar nesta aventura... Até a próxima!");
                return;
            default:
                System.out.println("Opção inválida. Reinicie o jogo para tentar novamente.");
        }
    }

    private void criarPersonagemOriginalConsole() {
        System.out.println("Digite o nome do seu personagem: ");
        String nomePersonagem = scanner.nextLine(); // 'nomePersonagem' é local
        if (nomePersonagem.trim().isEmpty()){
            System.out.println("Nome inválido. Personagem não criado.");
            this.jogador = null;
            return;
        }

        System.out.println("Escolha a classe do seu personagem: ");
        System.out.println("1 - Rastreador");
        System.out.println("2 - Mecânico");
        System.out.println("3 - Médico");
        System.out.println("4 - Sobrevivente Nato");

        int escolha = -1;
        while (true) {
            System.out.print("Digite um número de 1 a 4: ");
            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                if (escolha >= 1 && escolha <= 4) {
                    break;
                } else {
                    System.out.println("Número fora do intervalo (1-4). Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida, por favor digite um número.");
                if (scanner.hasNextLine()) scanner.next();
            }
        }
        scanner.nextLine();

        switch (escolha) {
            case 1:
                this.jogador = new Rastreador(nomePersonagem); // Usa 'nomePersonagem'
                break;
            case 2:
                this.jogador = new Mecanico(nomePersonagem);   // Usa 'nomePersonagem'
                break;
            case 3:
                this.jogador = new Medico(nomePersonagem);     // Usa 'nomePersonagem'
                break;
            case 4:
                this.jogador = new SobreviventeNato(nomePersonagem); // Usa 'nomePersonagem'
                break;
        }
        if (this.jogador != null) {
            System.out.println("Personagem criado: " + this.jogador.getNome() + " - Classe: " + this.jogador.getClasse());
        }
    }

    // Seu método `criarPersonagem` original que era chamado por `iniciar()` na sua versão.
    // Ele usa 'nome' e 'classe' locais e cria um Personagem genérico.
    // Se `iniciar()` deve criar subclasses, ele deveria chamar `criarPersonagemOriginalConsole()`.
    // Mantido para mínima alteração conforme solicitado.
    private void criarPersonagem() {
        System.out.println("Digite o nome do seu personagem (método original 'criarPersonagem'): ");
        String nome = scanner.nextLine(); // 'nome' é local aqui

        System.out.println("Escolha a classe do personagem (método original 'criarPersonagem')");
        System.out.println("1 - Rastreador");
        System.out.println("2 - Mecânico");
        System.out.println("3 - Médico");
        System.out.println("4 - Sobrevivente Nato");

        int escolha = -1;
        while (escolha < 1 || escolha > 4) {
            System.out.print("Digite um número de 1 a 4: ");
            if(scanner.hasNextInt()){
                escolha = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida.");
                if (scanner.hasNextLine()) scanner.next();
                escolha = -1;
            }
        }
        scanner.nextLine();

        String classe = switch (escolha) { // 'classe' é local aqui
            case 1 -> "Rastreador";
            case 2 -> "Mecânico";
            case 3 -> "Médico";
            case 4 -> "Sobrevivente Nato";
            default -> "Sobrevivente";
        };
        // 'nome' e 'classe' são locais e estão em escopo aqui.
        this.jogador = new Personagem(nome, classe); // Cria Personagem genérico
        System.out.println("Personagem criado: " + this.jogador.getNome() + " - Classe: " + this.jogador.getClasse());
    }

    // Introdução para o console
    private void introducaoOriginalConsole() {
        if (this.jogador == null || this.jogador.getAmbienteAtual() == null) {
            System.err.println("Erro: Jogador ou ambiente não configurado.");
            return;
        }
        System.out.println("\n>>>>> JOGO INICIADO <<<<<\n");
        System.out.println(this.jogador.getNome() + " desperta lentamente..."); // Usa getter
        // ... (resto do seu texto de introdução)
        System.out.println("\n🔹 Ambiente inicial: " + this.jogador.getAmbienteAtual().getNome());
        System.out.println("Descrição: " + this.jogador.getAmbienteAtual().getDescricao());
        System.out.println("Clima: " + this.jogador.getAmbienteAtual().getCondicaoClimatica());

        };
    }

    // --- MÉTODOS COMUNS (usados por GUI e/ou Console após 'jogador' ser definido) ---
    private void configurarAmbientes() {
        if (this.jogador == null) {
            System.err.println("Jogador não foi inicializado antes de configurar ambientes.");
            return;
        }
        // ... (seu código de configuração de ambientes) ...
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
        if (ambientesDisponiveis.isEmpty()) {
            System.err.println("Nenhum ambiente disponível para definir como inicial.");
            return;
        }
        Ambiente ambienteInicial = ambientesDisponiveis.get(new Random().nextInt(ambientesDisponiveis.size()));
        this.jogador.setAmbienteAtual(ambienteInicial);
        if (gerenciador != null) {
            gerenciador.registrarAmbienteInicial(ambienteInicial);
        }
    }

    private void configurarEventos() {

        if (gerenciadorEventos == null) {
            gerenciadorEventos = new GerenciadorDeEventos();
        }
        // ... (seu código de configuração de eventos) ...
        gerenciadorEventos.adicionarEvento(new CristalAzul());
        gerenciadorEventos.adicionarEvento(new EmboscadaLobos());
        gerenciadorEventos.adicionarEvento(new EnchenteRapida());
        gerenciadorEventos.adicionarEvento(new PoeiraToxica());
        gerenciadorEventos.adicionarEvento(new TempestadeMontanha());
    }

    public void apresentarAcoesPorAmbiente(Personagem jogadorParam) {
        if (jogadorParam == null || jogadorParam.getAmbienteAtual() == null) {
            System.out.println("Não é possível apresentar ações: jogador ou ambiente não definido.");
            return;
        }
        Ambiente ambiente = jogadorParam.getAmbienteAtual();
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

    private void loopJogo() {
        if (this.jogador == null) {
            System.err.println("Loop do jogo não pode iniciar: jogador não foi criado.");
            return;
        }
        boolean jogando = true;
        while (jogando) {
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
            if (scanner.hasNextInt()) {
                escolhaMenu = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida.");
                if (scanner.hasNextLine()) scanner.next();
                continue;
            }
            scanner.nextLine();

            try {
                switch (escolhaMenu) {
                    case 1: if(this.jogador!=null) this.jogador.getStatus(); break;
                    case 2: if(this.jogador!=null) this.jogador.visualizarInventario(); break;
                    case 3:
                        System.out.print("Digite o nome do item que deseja usar: ");
                        String itemUsar = scanner.nextLine();
                        
                        jogador.usarItem(itemUsar);
                        jogador.diminuirFome(1);
                        jogador.diminuirSede(2);
                    }
                    case 4 -> {
                        System.out.print("Digite o nome do item que deseja remover: ");
                        String itemRemover = scanner.nextLine();
                        jogador.getInventario().removerItem(itemRemover);
                    }
                    case 5 -> {
                        menuAmbientes();
                        jogador.diminuirFome(8);
                        jogador.diminuirSede(10);
                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                    }
                    case 6 -> {
                        explorarAmbiente();
                        jogador.diminuirFome(3);
                        jogador.diminuirSede(4);
                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                    }
                    case 7 -> {
                        realizarAcoes();
                        jogador.diminuirFome(3);
                        jogador.diminuirSede(4);
                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                    }
                    case 8 -> {
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
                            if (escolha == 1) medico.autoCurarFerimentosLeves();
                            else if (escolha == 2) System.out.println("Ainda não há outro personagem disponível.");
                            else if (escolha == 3) medico.prepararRemedioNatural();
                            else System.out.println("Opção inválida.");
                        } else if (jogador instanceof SobreviventeNato sobrevivente) {
                            System.out.println("1 - Fabricar lança");
                            System.out.println("2 - Caçar animais");
                            int escolha = scanner.nextInt();
                            scanner.nextLine();
                            if (escolha == 1) sobrevivente.fabricarLanca();
                            else if (escolha == 2) sobrevivente.cacarAnimais();
                            else System.out.println("Opção inválida.");
                        }
                        jogador.diminuirFome(2);
                        jogador.diminuirSede(3);
                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                    }
                    case 9 -> {
                        if (jogador instanceof SobreviventeNato sobrevivente) {
                            sobrevivente.montarAbrigoImprovisado(jogador.getAmbienteAtual());
                            System.out.println("Você descansou com segurança por ter montado um abrigo improvisado.");
                        } else {
                            System.out.println("Você se deita para descansar...");
                            double chance = Math.random();
                            if (chance < 0.25) {
                                gerenciadorEventos.aplicarEventoCriaturaDuranteDescanso(jogador);
                            } else if (chance < 0.50) {
                                gerenciadorEventos.aplicarEventoClimaticoDuranteDescanso(jogador);
                            } else {
                                System.out.println("O descanso foi tranquilo.");
                            }
                        }

                        jogador.descansar(); // descanso em si (recuperação)
                        jogador.consumirRecursosBasicos(); // consumo após descansar

                        if (!gerenciadorDeTurnos.executarTurno(jogador, true)) return;
                    }

                    case 0 -> {
                        gerenciador.mostrarHistorico();
                        gerenciadorEventos.mostrarHistoricoDeEventos();
                        
                        System.out.println("Obrigado por jogar!");
                        jogando = false;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

                if (jogando && this.jogador != null) {
                    this.jogador.verificarFomeSedeSanidade();
                }

            } catch (FomeSedeSanidadeException e) {
                System.out.println(e.getMessage());
                if (this.jogador != null && this.jogador.getVida() <= 0) {
                    System.out.println(this.jogador.getNome() + " não resistiu...");
                    jogando = false;
                }

            } catch (RuntimeException e) {
                System.err.println("Erro inesperado no loop do jogo: " + e.getMessage());
                e.printStackTrace();
                jogando = false;
            }
        }
    }

    private void explorarAmbiente() {

        if (this.jogador == null) {
            System.err.println("Não é possível explorar: jogador não definido.");
            return;
        }
        System.out.println("\nVocê decide explorar a área ao redor...");
        if (gerenciadorEventos != null) {
            gerenciadorEventos.aplicarEventoAleatorio(this.jogador);
        }
        if(this.jogador != null) this.jogador.consumirRecursosBasicos();

    private void realizarAcoes() {
        if (this.jogador == null || this.jogador.getAmbienteAtual() == null) {
            System.err.println("Não é possível realizar ações: jogador ou ambiente não configurado.");
            return;
        }
        apresentarAcoesPorAmbiente(this.jogador); // Passa o jogador atual

        System.out.print("\nEscolha uma ação (console): ");
        int escolha = -1;
        if(scanner.hasNextInt()){
            escolha = scanner.nextInt();
        } else {
            System.out.println("Entrada inválida.");
            if (scanner.hasNextLine()) scanner.next();
            return;
        }
        scanner.nextLine();

        Ambiente ambiente = this.jogador.getAmbienteAtual();

        switch (escolha) {
            case 1:
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

        if (this.jogador != null) {
            this.jogador.consumirRecursosBasicos();
            System.out.println("\n--- Inventário atualizado ---");
            this.jogador.visualizarInventario();
        }
    }

    private void menuAmbientes() {
        if (this.jogador == null) {
            System.err.println("Não é possível mudar de ambiente: jogador não definido.");
            return;
        }
        boolean movendo = true;
        while(movendo) {
            System.out.println("\nEscolha para qual ambiente deseja mudar: ");
            System.out.println("1 - Floresta");
            System.out.println("2 - Caverna");
            // ... (resto das opções)
            System.out.println("0 - Voltar ao menu");

            int opcao = -1;
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida.");
                if(scanner.hasNextLine()) scanner.next();
                continue;
            }
            scanner.nextLine();

            if (opcao == 0) break;

            Ambiente proximoAmbiente = null;
            switch (opcao) {
                case 1: proximoAmbiente = floresta; break;
                case 2: proximoAmbiente = caverna; break;
                case 3: proximoAmbiente = lagorio; break;
                case 4: proximoAmbiente = montanha; break;
                case 5: proximoAmbiente = ruinas; break;
                default: System.out.println("Opção inválida."); continue;
            }

            if (proximoAmbiente != null && gerenciador != null && this.jogador != null) {
                try {
                    gerenciador.mudarAmbiente(this.jogador, proximoAmbiente);
                } catch (AmbienteInacessivelException e) {
                    System.out.println("⚠️ " + e.getMessage());
                }
            }

            System.out.println("\nDeseja se mover para outro ambiente? (Sim/Não)");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("Sim")) {
                movendo = false;
            }
        }
    }
}
