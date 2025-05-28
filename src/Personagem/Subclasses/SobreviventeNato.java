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

    public void cacarPequenosAnimais() {
        Ambiente ambiente = getAmbienteAtual();
        String animal = "";
        String carne = "";
        double peso = 1.0;

        if (ambiente instanceof Floresta) {
            animal = "javali";
            carne = "Carne de Javali";
            peso = 1.2;
        } else if (ambiente instanceof Montanha) {
            animal = "corvo";
            carne = "Carne de Corvo";
            peso = 0.8;
        } else if (ambiente instanceof Caverna) {
            animal = "morcego gigante";
            carne = "Carne de Morcego";
            peso = 0.6;
        } else if (ambiente instanceof LagoRio) {
            animal = "piranha";
            carne = "Carne de Piranha";
            peso = 0.9;
        } else if (ambiente instanceof Ruinas) {
            animal = "rato mutante";
            carne = "Carne de Rato Mutante";
            peso = 1.0;
        } else {
            System.out.println("Ambiente desconhecido. Nenhum animal disponível para caça.");
            return;
        }

        System.out.println(getNome() + " caçou um(a) " + animal + " e obteve alimento.");
        try {
            getInventario().adicionarItem(new Item(carne, peso, 1));
        } catch (InventarioCheioException e) {
            System.out.println("Inventário cheio. Não foi possível adicionar a carne.");
        }
        restaurarFome(25);
        restaurarEnergia(10);
    }
}