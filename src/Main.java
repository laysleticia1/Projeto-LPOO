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


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BEM-VINDO AO JOGO DE SOBREVIÊNCIA ÚLTIMA FRONTEIRA");

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
        Ambiente floresta = new Floresta("Floresta", "Floresta densa e habitada", 2, new ArrayList<>(Arrays.asList("Frutas, Madeira, Ervas Medicinais")), 0.4, "Chuvos");
        Ambiente caverna = new Caverna("Caverna", "Caverna subterrânea e escura", 7, new ArrayList<>(Arrays.asList("Rochas e minérios raros")), 0.65, "Úmido e escuro" );
        Ambiente lagorio = new LagoRio("Lago/Rio", "Um grande Lago/Rio que oferece oportunidades de hidratação, pesca e travessia", 2, new ArrayList<>(Arrays.asList("Água, peixes e algas")), 0.4, "Úmido e fresco");
        Ambiente montanha = new Montanha("Montanha", "Ambiente elevado, terreno acidentado e grandes variações de temperatura, ALTO RISCO", 8, new ArrayList<>(Arrays.asList("Minérios e pedras preciosas")), 0.6, "Frio e com ventos fortes" );
        Ambiente ruinas = new Ruinas("Ruínas", "Restos de uma civilização antiga com mistérios e perigos", 6, new ArrayList<>(Arrays.asList("Artefatos antigos, Estruturas colapsadas, inscrições misteriosas")), 0.6, "Seco, com fortes ventos");

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
        System.out.println("\nVocê acorda em um local desconhecido...");
        System.out.println("Ambiente inicial: " + ambienteInicial.getNome());
        System.out.println(ambienteInicial.getDescricao());

        //Loop de Movimentação
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nEscolha para onde quer se mover: ");
            System.out.println("1 - Floresta");
            System.out.println("2 - Caverna");
            System.out.println("3 - LagoRio");
            System.out.println("4 - Montanha");
            System.out.println("5 - Ruinas");
            System.out.println("0 - Sair do jogo");

            int opcao = scanner.nextInt();
            scanner.nextLine(); //Limpa o buffer

            switch (opcao) {
                case 1 -> gerenciador.mudarAmbiente(jogador, floresta);
                case 2 -> gerenciador.mudarAmbiente(jogador, caverna);
                case 3 -> gerenciador.mudarAmbiente(jogador, lagorio);
                case 4 -> gerenciador.mudarAmbiente(jogador, montanha);
                case 5 -> gerenciador.mudarAmbiente(jogador, ruinas);
                case 0 -> {
                    continuar = false;
                    gerenciador.mostrarHistorico();
                    System.out.println("Obrigado por jogar!");
                }
                default -> {
                    System.out.println("Opção Inválida");
                    continue;
                }
            }

            System.out.println("\nDeseja se mover para outro ambiente?");
            String resposta = scanner.nextLine();

            if(!resposta.equalsIgnoreCase("Sim")){
                System.out.println("Você decide explorar por mais um tempo...");
                continuar = false;
                gerenciador.mostrarHistorico();
                System.out.println("Obrigado por jogar!");
            }
        }

        scanner.close();
    }
}

