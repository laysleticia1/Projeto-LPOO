package Personagem.Subclasses;

import Interface.Curavel;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
import Item.Superclasse.Item;
import Item.Subclasses.Remedios;
import Personagem.Inventario.Inventario;

public class Medico extends Personagem implements Curavel {

    public Medico(String nomeUsuario) {
        super(nomeUsuario, "Médico");
    }

    public void curar(Personagem alvo) {
        int vidaAntes = alvo.getVida();
        alvo.restaurarVida(20);
        System.out.println(getNome() + " curou " + alvo.getNome() + ": " + vidaAntes + " → " + alvo.getVida());
    }

    public void autoCurarFerimentosLeves() {
        Inventario inventario = getInventario();
        Item remedio = null;

        for (Item item : inventario.getArrayInventario()) {
            if (item.getNome().equalsIgnoreCase("Remédio Natural") || item.getNome().equalsIgnoreCase("Remédio")) {
                remedio = item;
                break;
            }
        }

        if (remedio != null) {
            inventario.usarItem(remedio.getNome());
            this.restaurarVida(25);
            System.out.println("Você usou um " + remedio.getNome() + " e recuperou 25 de vida.");
        } else {
            System.out.println("Você não possui nenhum remédio para se curar.");
        }
    }

    public void prepararRemedioNatural() {
        Inventario inventario = getInventario();
        Item agua = null;
        Item alimento = null;

        for (Item item : inventario.getArrayInventario()) {
            if (item.getNome().equalsIgnoreCase("Água") && agua == null) {
                agua = item;
            }
            if (item.getNome().equalsIgnoreCase("Frutas Silvestres") && alimento == null) {
                alimento = item;
            }
        }

        if (agua != null && alimento != null) {
            inventario.usarItem("Água");
            inventario.usarItem("Frutas Silvestres");

            Remedios remedio = new Remedios("Remédio Natural", "Fitoterápico", "Alivia dores leves e melhora a sanidade");
            try {
                inventario.adicionarItem(remedio);
                System.out.println("Você preparou um remédio natural usando água e frutas silvestres.");
            } catch (Exception e) {
                System.out.println("Não foi possível adicionar o remédio ao inventário.");
            }
        } else {
            System.out.println("Você precisa de Água e Frutas Silvestres para preparar o remédio.");
        }
    }
}