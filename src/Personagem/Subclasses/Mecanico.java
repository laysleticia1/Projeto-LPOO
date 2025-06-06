package Personagem.Subclasses;

import Personagem.Superclasse.*;
import Item.Superclasse.*;
import Item.Subclasses.*;
import Personagem.Inventario.*;

import java.util.Scanner;
import java.util.List;
import javax.swing.*;

public class Mecanico extends Personagem {

    public Mecanico(String nomeUsuario) {
        super(nomeUsuario, "Mecânico");
    }

    public void consertarEquipamento() {
        List<Item> itens = getInventario().getTodosItens();

        Item ferramentaOuMaterial = null;

        for (Item item : itens) {
            if (item instanceof Ferramentas || item instanceof Material) {
                ferramentaOuMaterial = item;
                break;
            }
        }

        if (ferramentaOuMaterial == null) {
            System.out.println("Você precisa de ferramentas ou materiais para consertar um equipamento.");
            return;
        }

        System.out.println("Você ajusta engrenagens, reaperta parafusos e reativa partes do seu equipamento utilizando: " + ferramentaOuMaterial.getNome());
        System.out.println("Conserto realizado com sucesso.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Deseja consumir " + ferramentaOuMaterial.getNome() + " após o conserto? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            getInventario().removerItem(ferramentaOuMaterial);
            System.out.println(ferramentaOuMaterial.getNome() + " foi consumido no processo.");
        } else {
            System.out.println(ferramentaOuMaterial.getNome() + " foi guardado novamente.");
        }
    }


    public void montarDispositivoImprovizado(Item item) {
        System.out.println("Usando " + item.getNome() + ", você improvisa uma armadilha defensiva.");
        this.diminuirSanidade(2);
        System.out.println("Você perdeu 2 de sanidade no processo, mas ganhou proteção.");
    }

    public void melhorarArma() {
        Inventario inventario = getInventario();

        Ferramentas ferramenta = null;
        Armas arma = null;

        for (Item item : inventario.getTodosItens()) {
            if (item instanceof Ferramentas && ferramenta == null) {
                ferramenta = (Ferramentas) item;
            } else if (item instanceof Armas && arma == null) {
                arma = (Armas) item;
            }
        }

        if (ferramenta != null && arma != null) {
            System.out.println(getNome() + " está melhorando a arma com uma ferramenta...");

            arma.setDurabilidade(arma.getDurabilidade() + 3);
            arma.setDano(arma.getDano() + 2);

            inventario.removerItem(ferramenta.getNome());

            System.out.println("Melhoria concluída!");
            System.out.println("Novo dano: " + arma.getDano());
            System.out.println("Nova durabilidade: " + arma.getDurabilidade());
        } else {
            System.out.println("Você precisa ter uma ferramenta e uma arma no inventário.");
        }
    }

    //Interface
    public void consertarEquipamentoInterface(JTextArea areaLog) {
        List<Item> itens = getInventario().getTodosItens();
        Item ferramentaOuMaterial = null;

        for (Item item : itens) {
            if (item instanceof Ferramentas || item instanceof Material) {
                ferramentaOuMaterial = item;
                break;
            }
        }

        if (ferramentaOuMaterial == null) {
            areaLog.append("❌ Você precisa de ferramentas ou materiais para consertar um equipamento.\n");
            return;
        }

        areaLog.append("Você ajusta engrenagens, reaperta parafusos e reativa partes do seu equipamento utilizando: " + ferramentaOuMaterial.getNome() + "\n");
        areaLog.append("Conserto realizado com sucesso.\n");

        int escolha = JOptionPane.showConfirmDialog(null,
                "Deseja consumir " + ferramentaOuMaterial.getNome() + " após o conserto?",
                "Consumo de Item",
                JOptionPane.YES_NO_OPTION);

        if (escolha == JOptionPane.YES_OPTION) {
            getInventario().removerItem(ferramentaOuMaterial);
            areaLog.append(ferramentaOuMaterial.getNome() + " foi consumido no processo.\n");
        } else {
            areaLog.append(ferramentaOuMaterial.getNome() + " foi guardado novamente.\n");
        }
    }

    public void montarDispositivoImprovizadoInterface(Item item, JTextArea areaLog) {
        areaLog.append("Usando " + item.getNome() + ", você improvisa uma armadilha defensiva.\n");
        this.diminuirSanidade(2);
        areaLog.append("Você perdeu 2 de sanidade no processo, mas ganhou proteção.\n");
    }

    public void melhorarArmaInterface(JTextArea areaLog) {
        Inventario inventario = getInventario();
        Ferramentas ferramenta = null;
        Armas arma = null;

        for (Item item : inventario.getTodosItens()) {
            if (item instanceof Ferramentas && ferramenta == null) {
                ferramenta = (Ferramentas) item;
            } else if (item instanceof Armas && arma == null) {
                arma = (Armas) item;
            }
        }

        if (ferramenta != null && arma != null) {
            areaLog.append(getNome() + " está melhorando a arma com uma ferramenta...\n");

            arma.setDurabilidade(arma.getDurabilidade() + 3);
            arma.setDano(arma.getDano() + 2);
            inventario.removerItem(ferramenta.getNome());

            areaLog.append("Melhoria concluída!\n");
            areaLog.append("Novo dano: " + arma.getDano() + " | Nova durabilidade: " + arma.getDurabilidade() + "\n");
        } else {
            areaLog.append("❌ Você precisa ter uma ferramenta e uma arma no inventário.\n");
        }
    }

}
