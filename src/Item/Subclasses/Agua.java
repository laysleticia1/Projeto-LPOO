package Item.Subclasses;

import Item.Superclasse.Item;
import Personagem.Superclasse.*;
import Interface.Usavel;
import Personagem.Superclasse.*;
import Personagem.Subclasses.*;

public class Agua extends Item {
    private String pureza;
    private double volume;
    private double chanceContaminacao;

    public Agua(String nome, double peso, int durabilidade, String pureza, double volume,double chanceContaminacao) {
        super(nome,peso,durabilidade);
        this.pureza = pureza;
        this.volume = volume;
        this.chanceContaminacao = chanceContaminacao;
    }

    public void usar(Personagem alvo) {
        if (getDurabilidade() > 0) {
            setDurabilidade(getDurabilidade() - 1);
            alvo.setSede(alvo.getSede() + 20);
            System.out.println("Você bebeu água: " + getNome() + "\n");
            if (Math.random() < chanceContaminacao) {
                System.out.println("Você começou a se sentir mal... talvez a água estivesse contaminada.");
                System.out.println("Você perdeu 10 de vida\n");
                alvo.diminuirVida(5);
            }
        } else {
            System.out.println("A água já foi consumida.\n");
        }
    }

    public void exibirDetalhes() {
        System.out.println("Informações da água:");
        System.out.println("• Nome: " + getNome());
        System.out.println("• Peso: " + getPeso());
        System.out.println("• Durabilidade: " + getDurabilidade());
        System.out.println("• Pureza: " + pureza);
        System.out.println("• Volume: " + volume + "L");
        System.out.println("• Chance de contaminação: " + chanceContaminacao + "%");
    }

    @Override
    public String exibirDetalhesInterface() {
        return "Água: " + getNome() +
                "\nPeso: " + getPeso() +
                "\nDurabilidade: " + getDurabilidade() +
                "\nPureza: " + pureza +
                "\nVolume: " + volume + " L" +
                "\nChance de Contaminação: " + chanceContaminacao + "%";
    }

    //Getters and Setters
    public double getChanceContaminacao() {
        return chanceContaminacao;
    }
    public void setChanceContaminacao(double chanceContaminacao) {
        this.chanceContaminacao = chanceContaminacao;
    }
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public String getPureza() {
        return pureza;
    }
    public void setPureza(String pureza) {
        this.pureza = pureza;
    }
}
