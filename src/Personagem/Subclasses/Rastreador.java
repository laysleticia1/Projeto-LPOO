package Personagem.Subclasses;

import Ambiente.Superclasse.*;
import Ambiente.Subclasses.*;
import Personagem.Superclasse.Personagem;
import Item.Subclasses.*;
import Item.Superclasse.*;
import Excecoes.InventarioCheioException;

public class Rastreador extends Personagem{

    public Rastreador(String nomeUsuario) {
        super(nomeUsuario, "Rastreador");
    }

    public void identificarPegadas(Ambiente ambiente) {
        System.out.println("Você examina o solo em busca de pegadas...");
        if (ambiente instanceof Floresta) {
            System.out.println("Pegadas de javali encontradas. Um som distante confirma sua aproximação.");
        } else if (ambiente instanceof Montanha) {
            System.out.println("Pegadas de urso detectadas entre as rochas.");
        } else if (ambiente instanceof Caverna) {
            System.out.println("Você identifica marcas de morcegos e ratos.");
        } else if (ambiente instanceof Ruinas) {
            System.out.println("Pegadas humanas... recentes. Alguém passou por aqui.");
        } else if (ambiente instanceof LagoRio) {
            System.out.println("Marcas de patas de animal aquático: possível cardume agitado.");
        }
    }

    public void farejarTrilha(Ambiente ambiente) {
        Item itemEncontrado = null;

        if (ambiente instanceof Floresta) {
            System.out.println(getNome() + " identificou trilhas de animais e encontrou frutas silvestres.");
            itemEncontrado = new Alimentos("Frutas Silvestres", 1.0, 2, 5, "Fruta", 2);
        } else if (ambiente instanceof Caverna) {
            System.out.println(getNome() + " farejou pegadas humanas e achou uma tocha esquecida.");
            itemEncontrado = new Ferramentas("Tocha", 1.5, 3, 3);
        } else if (ambiente instanceof LagoRio) {
            System.out.println(getNome() + " encontrou pegadas e redes de pesca abandonadas.");
            itemEncontrado = new Material("Rede de Pesca", "Fibra Natural", 1.2, 3, 2);
        } else if (ambiente instanceof Montanha) {
            System.out.println(getNome() + " observou rastros entre as pedras e achou uma bota reforçada.");
            itemEncontrado = new Ferramentas("Bota Reforçada", 2.0, 4, 3);
        } else if (ambiente instanceof Ruinas) {
            System.out.println(getNome() + " seguiu marcas e descobriu uma adaga antiga.");
            itemEncontrado = new Armas("Adaga Antiga", 1.0, 5, "faca", 3, 1);
        } else {
            System.out.println("Ambiente inóspito para rastreamento avançado.");
            return;
        }

        // Adiciona o item encontrado ao inventário
        try {
            getInventario().adicionarItem(itemEncontrado);
            System.out.println("Item encontrado e adicionado ao inventário: " + itemEncontrado.getNome());
        } catch (InventarioCheioException e) {
            System.out.println("Inventário cheio. Item não foi adicionado: " + itemEncontrado.getNome());
        }
    }
}
