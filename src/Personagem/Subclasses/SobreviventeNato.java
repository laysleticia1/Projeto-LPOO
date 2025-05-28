package Personagem.Subclasses;

import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.*;
import Ambiente.Subclasses.*;
import Excecoes.InventarioCheioException;
import Item.Subclasses.Armas;
import Item.Superclasse.Item;
import Personagem.Inventario.Inventario;


public class SobreviventeNato extends Personagem{

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
        System.out.println("Você se sente um pouco mais seguro. (+10 de sanidade e energia)");
        restaurarSanidade(10);
        restaurarEnergia(10);
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
            inventario.usarItem("Sucata");

            // Cria uma nova arma improvisada
            Armas lancaImprovisada = new Armas(
                    "Lança Improvisada", // nome
                    2.5,                 // peso
                    5,                   // durabilidade
                    "lança",             // tipo
                    4,                   // dano
                    1                    // alcance
            );

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

}
