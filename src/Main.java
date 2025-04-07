import java.util.Scanner;

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


    }
}

