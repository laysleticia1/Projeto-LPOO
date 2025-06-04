package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente; // Import genérico
import Ambiente.Subclasses.Floresta; // Exemplo de import específico se "instanceof Floresta" for usado
// Adicione outros imports de Ambiente.Subclasses.* se necessário para outros instanceof
import Criatura.Superclasse.Criatura;
// Import Criatura.Subclasses.* se você usar 'new AlgumaCriaturaEspecifica()' diretamente aqui,
// caso contrário, a instância de Criatura virá pelo construtor.

import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

public class EventoCriatura extends Evento {
    // private String tipo; // Campo não utilizado, considere remover se não tiver planos para ele.
    private int nivelPerigo;
    private String acoes; // Descrição das possíveis ações, pode ser usado para UI ou flavor text
    private Criatura criatura;

    public EventoCriatura(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, Criatura criatura, int nivelPerigo, String acoes) {
        super(nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
        this.criatura = criatura;
        this.nivelPerigo = nivelPerigo;
        this.acoes = acoes;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Um som estranho interrompe sua exploração...");
        System.out.println("Você está cara a cara com um(a) " + criatura.getTipoDeCriatura() + "!");

        System.out.println("\nAntes que você possa reagir...");
        criatura.atacar(jogador); // Lógica de ataque da criatura (console)

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCom o coração acelerado e o corpo ferido, você precisa decidir seu próximo movimento:");
        System.out.println("1 - Revidar com toda sua força");
        System.out.println("2 - Ergue os braços e assume uma postura defensiva");
        System.out.println("3 - Lança um último olhar de desespero e corre com tudo que resta em direção à saída");
        System.out.println("4 - Permanecer parado, observando a criatura... talvez entenda seu padrão de ataque");

        System.out.print("\nSua escolha: ");
        int escolha = -1;
        if(scanner.hasNextInt()){
            escolha = scanner.nextInt();
        }
        scanner.nextLine(); // Consumir nova linha

        switch (escolha) {
            case 1:
                System.out.println("\nCom sangue nos olhos, você revida com um golpe instintivo!");
                // Ação do jogador aqui. A criatura pode reagir ou fugir.
                // Ex: jogador.atacar(criatura); (se existir tal método)
                // Ex: criatura.reagirAoAtaque(jogador);
                // A lógica de `criatura.fugir()` aqui parece que a criatura foge se o jogador revida.
                criatura.fugir();
                break;
            case 2:
                System.out.println("\nVocê se encolhe, protegendo o rosto e o peito com os braços.");
                System.out.println("O impacto é menor, mas doloroso. Você aproveita a brecha para escapar da criatura.\n");
                jogador.restaurarVida(4); // Efeito da defesa
                jogador.fugir(); // Jogador tenta fugir
                break;
            case 3:
                System.out.println("\nUm impulso de puro instinto de sobrevivência toma conta de você... e seus pés disparam como relâmpagos!");
                if (new Random().nextDouble() < 0.7) { // Chance de sucesso na fuga
                    System.out.println("Seus passos são rápidos o bastante. A criatura hesita por um instante, e você consegue escapar ileso... por enquanto.\n");
                    jogador.fugir(); // Confirma a fuga
                } else {
                    System.out.println("Você tropeça por um momento... e a criatura aproveita e te alcança!");
                    criatura.atacar(jogador); // Criatura ataca novamente
                }
                break;
            case 4:
                System.out.println("\nVocê permanece imóvel, encarando a criatura com curiosidade e coragem...");
                System.out.println("Por um instante, ela parece hesitar... mas então desencadeia uma ação misteriosa!");
                criatura.acaoEspecial(jogador); // Ação especial da criatura
                jogador.fugir(); // Jogador tenta fugir depois
                break;
            default:
                System.out.println("\nA indecisão te domina... e a criatura não desperdiça a chance.");
                criatura.atacar(jogador); // Criatura ataca
                break;
        }
    }

    public void executarDuranteDescanso(Personagem jogador, Ambiente ambiente) {
        System.out.println("\n🔸 Um ataque inesperado durante o descanso!");
        criatura.ataqueDuranteDescanso(jogador); // Método da criatura para ataque durante descanso (console)
    }

    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        // Exemplo: Este evento genérico de criatura pode ocorrer em Florestas.
        // As subclasses específicas (como EmboscadaLobos) podem ter suas próprias lógicas.
        // Para 'ambiente instanceof Floresta' funcionar, a classe Floresta deve existir
        // e ser importada ou estar no mesmo pacote (o que não é o caso aqui).
        // Assumindo que Floresta está em Ambiente.Subclasses e é importada.
        return ambiente instanceof Floresta;
    }

    //Interface
    public void executarInterface(Personagem jogador, Ambiente local, JTextArea areaLog) {
        areaLog.append("Um som estranho interrompe sua exploração...\n");
        areaLog.append("Você está cara a cara com um(a) " + criatura.getTipoDeCriatura() + "!\n\n");
        areaLog.append("Antes que você possa reagir...\n");
        areaLog.append(criatura.atacarParaUI(jogador) + "\n"); // Usa método da criatura para UI

        areaLog.append("\nCom o coração acelerado e o corpo ferido, você precisa decidir seu próximo movimento:\n");
        String[] opcoes = {
                "Revidar com toda sua força",
                "Assumir postura defensiva",
                "Correr desesperadamente",
                "Observar a criatura"
        };

        int escolha = JOptionPane.showOptionDialog(
                null, // parentComponent
                "O que você vai fazer contra " + criatura.getTipoDeCriatura() + "?", // message
                "Encontro com Criatura!", // title
                JOptionPane.DEFAULT_OPTION, // optionType
                JOptionPane.WARNING_MESSAGE, // messageType
                null, // icon
                opcoes, // options
                opcoes[0] // initialValue
        );

        switch (escolha) {
            case 0: // Revidar
                areaLog.append("\n🗡️Com sangue nos olhos, você revida com um golpe instintivo!\n");
                // Ação do jogador (ex: jogador.atacar(criatura) teria que ter um ...ParaUI)
                // Se a criatura foge como resultado:
                areaLog.append(criatura.fugirParaUI() + "\n");
                break;
            case 1: // Defender
                areaLog.append("\n🛡️Você se encolhe, protegendo o rosto e o peito com os braços.\n");
                areaLog.append("O impacto é menor, mas doloroso. Você aproveita a brecha para escapar da criatura.\n");
                jogador.restaurarVida(4);
                areaLog.append("Você restaurou 4 de vida.\n");
                // jogador.fugir(); // Se o jogador foge, precisa de uma representação para UI
                areaLog.append("Você tenta se afastar da criatura...\n");
                break;
            case 2: // Correr
                areaLog.append("\n🏃Um impulso de puro instinto de sobrevivência toma conta de você...\n");
                if (new Random().nextDouble() < 0.7) {
                    areaLog.append("Seus passos são rápidos o bastante. Você consegue escapar ileso... por enquanto.\n");
                    // jogador.fugir();
                } else {
                    areaLog.append("Você tropeça por um momento... a criatura te alcança!\n");
                    areaLog.append(criatura.atacarParaUI(jogador) + "\n");
                }
                break;
            case 3: // Observar
                areaLog.append("\n👁️Você permanece imóvel, encarando a criatura com coragem...\n");
                areaLog.append("Por um instante, ela hesita... mas então desencadeia uma ação misteriosa!\n");
                areaLog.append(criatura.acaoEspecialParaUI(jogador) + "\n");
                // jogador.fugir();
                areaLog.append("Após a ação da criatura, você recua...\n");
                break;
            default: // Nenhuma escolha ou fechou o diálogo
                areaLog.append("\nA indecisão te domina... e a criatura não desperdiça a chance.\n");
                areaLog.append(criatura.atacarParaUI(jogador) + "\n");
                break;
        }
    }

    public void executarDuranteDescansoInterface(Personagem jogador, Ambiente ambiente, JTextArea areaLog) {
        areaLog.append("\n🔸 Um ataque inesperado durante o descanso!\n");
        areaLog.append(criatura.ataqueDuranteDescansoParaUI(jogador) + "\n"); // Usa método da criatura para UI
    }
}
