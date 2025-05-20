package Item.Subclasses;

import Item.Superclasse.Item;
import Personagem.Superclasse.*;
import Interface.Usavel;

public class Agua extends Item implements Usavel{
    private String pureza;
    private double volume;

    public Agua(String nome, double peso, int durabilidade, String pureza, double volume) {
        super(nome,peso,durabilidade);
        this.pureza = pureza;
        this.volume = volume;
    }

    public void usar(Personagem alvo) {
        if (getDurabilidade() > 0) {
            setDurabilidade(getDurabilidade() - 1);
            alvo.setSede(alvo.getSede() + 20);
            System.out.println("Você bebeu água: " + getNome());
        } else {
            System.out.println("A água já foi consumida.");
        }
    }
}
