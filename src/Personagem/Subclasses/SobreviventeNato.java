package Personagem.Subclasses;

import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.*;
import Ambiente.Subclasses.*;
import Excecoes.InventarioCheioException;
import Item.Subclasses.*;
import Item.Superclasse.*;
import Personagem.Inventario.Inventario;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SobreviventeNato extends Personagem {

    public SobreviventeNato(String nomeUsuario) {
        super(nomeUsuario, "SobreviventeNato");
    }

    public void montarAbrigoImprovisado(Ambiente ambiente) {
        if (ambiente instanceof Caverna) {
            System.out.println(getNome() + " usa pedras e sombras da caverna para se esconder e descansar.");
        } else if (ambiente instanceof Floresta) {
            System.out.println(getNome() + " reúne galhos e folhas da floresta para construir um abrigo camuflado.");
        } else if (ambiente instanceof LagoRio) {
            System.out.println(getNome() + " monta um abrigo simples próximo ao rio, usando juncos e lama.");
        } else if (ambiente instanceof Montanha) {
            System.out.println(getNome() + " se protege com rochas e fendas na encosta da montanha.");
        } else if (ambiente instanceof Ruinas) {
            System.out.println(getNome() + " usa os escombros das ruínas para montar um abrigo temporário.");
        } else {
            System.out.println("Ambiente desconhecido. Não foi possível montar abrigo.");
            return;
        }

        // Bônus de recuperação ou efeito especial
        System.out.println("Você se sente um pouco mais seguro. (+15 de sanidade e +25 de energia)");
        restaurarSanidade(15);
        restaurarEnergia(25);
    }

    public void fabricarLanca() {
        Inventario inventario = getInventario();

        // Verifica se tem "Sucata"
        Item sucata = null;
        for (Item item : inventario.getArrayInventario()) {
            if (item.getNome().equalsIgnoreCase("Sucata")) {
                sucata = item;
                break;
            }
        }

        if (sucata != null) {
            // Usa a sucata
            inventario.usarItem("Sucata", this);

            // Cria uma nova arma improvisada
            Armas lancaImprovisada = new Armas(
                    "Lança Improvisada", 2.5, 5, "lança", 4, 1);

            try {
                inventario.adicionarItem(lancaImprovisada);
                System.out.println(getNome() + " improvisou uma lança com a sucata!");
            } catch (InventarioCheioException e) {
                System.out.println("Não foi possível adicionar a lança ao inventário: " + e.getMessage());
            }

        } else {
            System.out.println("Você precisa de sucata para fabricar uma lança.");
        }
    }

    public void cacarAnimais() {
        Ambiente ambiente = getAmbienteAtual();
        List<Alimentos> opcoes = new ArrayList<>();

        if (ambiente instanceof Floresta) {
            opcoes.add(new Alimentos("Carne de Javali", 1.2, 3, 30, "Javali", 2));
            opcoes.add(new Alimentos("Carne de Cobra", 0.7, 2, 20, "Cobra", 1));
            opcoes.add(new Alimentos("Carne de Lobo", 1.1, 3, 35, "Lobo", 2));
        } else if (ambiente instanceof Montanha) {
            opcoes.add(new Alimentos("Carne de Urso", 1.5, 4, 40, "Urso", 2));
            opcoes.add(new Alimentos("Carne de Lobo da Neve", 1.2, 3, 30, "Lobo da Neve", 2));
        } else if (ambiente instanceof Caverna) {
            opcoes.add(new Alimentos("Carne de Morcego", 0.6, 2, 15, "Morcego", 1));
            opcoes.add(new Alimentos("Carne de Rato Mutante", 0.9, 2, -10, "Rato Mutante", 1));
        } else if (ambiente instanceof LagoRio) {
            opcoes.add(new Alimentos("Carne de Piranha", 0.9, 2, 15, "Piranha", 1));
            opcoes.add(new Alimentos("Carne de Jacaré", 1.3, 3, 25, "Jacaré", 2));
        } else if (ambiente instanceof Ruinas) {
            opcoes.add(new Alimentos("Carne de Corvo", 0.8, 2, 15, "Corvo", 1));
        } else {
            System.out.println("Ambiente desconhecido. Nenhum animal disponível para caça.");
            return;
        }

        Alimentos carneObtida = opcoes.get(new Random().nextInt(opcoes.size()));
        System.out.println("Você caçou e obteve: " + carneObtida.getNome());
        carneObtida.exibirDetalhes();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Deseja adicionar ao inventário? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            this.adicionarAoInventario(carneObtida);
            System.out.println(carneObtida.getNome() + " adicionada ao inventário com sucesso.");
        } else {
            System.out.println("Você optou por deixar a carne para trás.");
        }

    }
}