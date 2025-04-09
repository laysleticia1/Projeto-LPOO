import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import Personagem.Superclasse.Personagem;
import java.util.ArrayList;
import Ambiente.Superclasse.Ambiente;
import Ambiente.Subclasses.Floresta;
import Ambiente.Subclasses.Caverna;
import Ambiente.Subclasses.Montanha;
import Ambiente.Subclasses.LagoRio;
import Ambiente.Subclasses.Ruinas;
import Gerenciadores.GerenciadorDeAmbientes;
import Personagem.Inventario.Inventario;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BEM-VINDO AO JOGO DE SOBREVIVÊNCIA - ÚLTIMA FRONTEIRA");

        System.out.println("\nSelecione a opção escolhida: ");
        System.out.println("1 - Iniciar");
        System.out.println("2 - Sair");

        int option = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        switch (option) {
            case 1 -> {
                System.out.println("Digite o nome do seu personagem: ");
                String nome = scanner.nextLine();

                System.out.println("Escolha a classe do personagem");
                System.out.println("1 - Rastreador");
                System.out.println("2 - Mecânico");
                System.out.println("3 - Médico");
                System.out.println("4 - Sobrevivente Nato");

                //Forçar que o úsuario digite um número de 1 a 4
                int escolha = -1;
                while (escolha < 1 || escolha > 4) {
                    System.out.print("Digite um número de 1 a 4: ");
                    escolha = scanner.nextInt();
                }

                String classe = "";
                switch (escolha) {
                    case 1 -> classe = "Rastreador";
                    case 2 -> classe = "Mecânico";
                    case 3 -> classe = "Médico";
                    case 4 -> classe = "Sobrevivente Nato";
                }

                Personagem jogador = new Personagem(nome, classe);

                //Criação dos Ambientes
                Ambiente floresta = new Floresta();
                Ambiente caverna = new Caverna();
                Ambiente lagorio = new LagoRio();
                Ambiente montanha = new Montanha();
                Ambiente ruinas = new Ruinas();

                //Gerenciador de Ambientes
                GerenciadorDeAmbientes gerenciador = new GerenciadorDeAmbientes();
                gerenciador.adicionarAmbiente(floresta);
                gerenciador.adicionarAmbiente(caverna);
                gerenciador.adicionarAmbiente(lagorio);
                gerenciador.adicionarAmbiente(montanha);
                gerenciador.adicionarAmbiente(ruinas);

                //Adicionando ambiente inicial random
                Random random = new Random();
                ArrayList<Ambiente> ambientesDisponiveis = gerenciador.getAmbientes();
                Ambiente ambienteInicial = ambientesDisponiveis.get(random.nextInt(ambientesDisponiveis.size()));

                //Definir ambiente inicial
                jogador.setAmbienteAtual(ambienteInicial);
                gerenciador.registrarAmbienteInicial(ambienteInicial);

                //Mensagem do início
                System.out.println("\n>>>>> JOGO INICIADO <<<<<\n");
                System.out.println(jogador.getNome() + " desperta lentamente, sem saber como chegou ali.");
                System.out.println("Está sozinho/a, cercado/a por um ambiente desconhecido e cheio de perigos.");
                System.out.println("Será preciso explorar, coletar recursos e tomar boas decisões para sobreviver.");
                System.out.println("\nAmbiente inicial: " + ambienteInicial.getNome());
                System.out.println("Descrição: " + ambienteInicial.getDescricao());

                //Loop
                boolean continuar = true;

                while (true) {
                    jogador.menuPrincipal();

                    int escolhaMenu = scanner.nextInt();
                    scanner.nextLine();

                    //Criar inventário
                    Inventario inventario = new Inventario ();

                    switch (escolhaMenu) {
                        case 1 -> jogador.getStatus(); // ou System.out.println(jogador);
                        case 2 -> jogador.visualizarInventario();
                        case 3 -> {
                            System.out.print("Digite o nome do item que deseja usar: ");
                            String itemUsar = scanner.nextLine();
                            jogador.usarItem();  // EM CONSTRUÇÃO !!!!!!!
                        }
                        case 4 -> {
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
                                    case 1 -> gerenciador.mudarAmbiente(jogador, floresta);
                                    case 2 -> gerenciador.mudarAmbiente(jogador, caverna);
                                    case 3 -> gerenciador.mudarAmbiente(jogador, lagorio);
                                    case 4 -> gerenciador.mudarAmbiente(jogador, montanha);
                                    case 5 -> gerenciador.mudarAmbiente(jogador, ruinas);
                                    default -> System.out.println("Opção inválida.");
                                }

                                System.out.println("\nDeseja se mover para outro ambiente? (Sim/Não)");
                                String resposta = scanner.nextLine();
                                if (!resposta.equalsIgnoreCase("Sim")) break;
                            }
                        }
                        case 0 -> {
                            gerenciador.mostrarHistorico();
                            System.out.println("Obrigado por jogar!");
                            return;
                        }
                        default -> System.out.println("Opção inválida.");
                    }
                }
            }
            case 2 -> {
                System.out.println("Você decide não embarcar nesta aventura... Até a próxima!");
                return; // Encerra o programa
            }

            default -> {
                System.out.println("Opção inválida. Reinicie o jogo para tentar novamente.");
                break;
            }
        }


    }
}