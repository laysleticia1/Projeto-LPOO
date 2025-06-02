package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.*;
import Ambiente.Subclasses.*;
import Criatura.Subclasses.*;
import Criatura.Superclasse.*;
import java.util.Random;
import java.util.Scanner;

public class EventoCriatura extends Evento {
    private String tipo;
    private int nivelPerigo;
    private String acoes;
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

        // Ataque surpresa primeiro
        System.out.println("\nAntes que você possa reagir...");
        criatura.atacar(jogador);

        // Escolha do jogador após o ataque
        Scanner scanner = new Scanner(System.in);
        System.out.println("Com o coração acelerado e o corpo ferido, você precisa decidir seu próximo movimento:");
        System.out.println("1 - Revidar com toda sua força");
        System.out.println("2 - Ergue os braços e assume uma postura defensiva");
        System.out.println("3 - Lança um último olhar de desespero e corre com tudo que resta em direção à saída");
        System.out.println("4 - Permanecer parado, observando a criatura... talvez entenda seu padrão de ataque");

        System.out.print("\nSua escolha: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                System.out.println("\nCom sangue nos olhos, você revida com um golpe instintivo!");
                criatura.fugir();
                break;

            case 2:
                System.out.println("\nVocê se encolhe, protegendo o rosto e o peito com os braços.");
                System.out.println("O impacto é menor, mas doloroso. Você aproveita a brecha para escapar da criatura.\n");
                jogador.restaurarVida(4);
                jogador.fugir();
                break;

            case 3:
                System.out.println("\n🏃 Um impulso de puro instinto de sobrevivência toma conta de você... e seus pés disparam como relâmpagos!");
                if (new Random().nextDouble() < 0.7) {
                    System.out.println("Seus passos são rápidos o bastante. A criatura hesita por um instante, e você consegue escapar ileso... por enquanto.\n");
                    jogador.fugir();
                } else {
                    System.out.println("Você tropeça por um momento... e a criatura aproveita e te alcança!");
                    criatura.atacar(jogador);
                }
                break;

            case 4:
                System.out.println("\nVocê permanece imóvel, encarando a criatura com curiosidade e coragem...");
                System.out.println("Por um instante, ela parece hesitar... mas então desencadeia uma ação misteriosa!");
                criatura.acaoEspecial(jogador);
                jogador.fugir();
                break;

            default:
                System.out.println("\nA indecisão te domina... e a criatura não desperdiça a chance.");
                criatura.atacar(jogador);
                break;
        }
    }

    public void executarDuranteDescanso(Personagem jogador, Ambiente ambiente) {
        System.out.println("\n🔸 Um ataque inesperado durante o descanso!");
        criatura.ataqueDuranteDescanso(jogador);
    }

    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente instanceof Floresta;
    }
}
