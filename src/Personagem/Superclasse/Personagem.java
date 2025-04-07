package Personagem.Superclasse;

import Ambiente.Superclasse.Ambiente;

public class Personagem {
    private String nome;
    private String classe;
    private int nivel;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private String [] inventario;
    private String [] localizacao;
    private int x, y; // Coordenadas
    private int velocidade;

    public Personagem (String nomeUsuario, String classeUsuario) { // Construtor
        this.nome = nomeUsuario;
        this.classe = classeUsuario;
        nivel = 1;
        vida = 100;
        fome = 100;
        sede = 100;
        energia = 100;
        sanidade = 100;
        x = 0;
        y = 0;
        velocidade = 10;
    }
    public String getNome () {
        return nome;
    }
    public void setNome (String nomeUsuario) {
        this.nome = nomeUsuario;
    }
    public String getClasse () {
        return classe;
    }
    public void setClasse (String classeUsuario) {
        this.classe = classeUsuario;
    }
    public int getNivel () {
        return nivel;
    }
    public void setNivel (int nivelUsuario) {
        this.nivel = nivelUsuario;
    }
    public int getVida () {
        return vida;
    }
    public void setVida (int vidaUsuario) {
        this.vida = vidaUsuario;
    }
    public int getFome () {
        return fome;
    }
    public void setFome (int fomeUsuario) {
        this.fome = fomeUsuario;
    }
    public int getSede () {
        return sede;
    }
    public void setSede (int sedeUsuario) {
        this.sede = sedeUsuario;
    }
    public int getEnergia () {
        return energia;
    }
    public void setEnergia (int energiaUsuario) {
        this.energia = energiaUsuario;
    }
    public int getSanidade () {
        return sanidade;
    }
    public void setSanidade (int sanidadeUsuario) {
        this.sanidade = sanidadeUsuario;
    }
    public String [] getInventario () {
        return inventario;
    }
    public void setInventario (String [] inventarioUsuario) {
        this.inventario = inventarioUsuario;
    }
    public String [] getLocalizacao () {
        return localizacao;
    }
    public void setLocalizacao (String [] localizacaoUsuario) {
        this.localizacao = localizacaoUsuario;
    }
    public int getVelocidade() {
        return velocidade;
    }
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void atacar() {
        if (getEnergia () > 0) {
            System.out.println ("Jogador está atacando");
        }
        else {
            System.out.println ("Jogador não poderá atacar");
        }
    }
    public void defender() {
        if (getEnergia () > 0) {
            System.out.println ("Jogador está defendendo");
        }
        else {
            System.out.println ("Jogador não poderá defender");
        }
    }
    public void correr() {
        if (getEnergia () > 0) {
            System.out.println ("Jogador está correndo");
        }
        else {
            System.out.println ("Jogador não poderá correr");
        }
    }
    public void agachar() {
        if (getEnergia () > 0) {
            System.out.println ("Jogador está agachado");
        }
        else {
            System.out.println ("Jogador não poderá agachar");
        }
    }
    public void moverDireita() {
        if (getEnergia () > 0) {
        x++;
        System.out.println ("Jogador se moveu para a direita");
        }
        else {
            System.out.println ("Jogador não poderá se mover");
        }
    }
    public void moverEsquerda() {
        if (getEnergia () > 0) {
            x--;
            System.out.println ("Jogador se moveu para a esquerda");
        }
        else {
            System.out.println ("Jogador não poderá se mover");
        }
    }
    public void moverCima() {
        if (getEnergia () > 0) {
            y++;
            System.out.println ("Jogador se moveu para cima");
        }
        else {
            System.out.println ("Jogador não poderá se mover");
        }
    }
    public void moverBaixo() {
        if (getEnergia () > 0) {
            y--;
            System.out.println ("Jogador se moveu para baixo");
        }
        else {
            System.out.println ("Jogador não poderá se mover");
        }
    }
    public void moverAmbiente (Ambiente novoAmbiente) {
    if (getEnergia () >= 5) {

        this.energia -= 5;
    }
    else {
        System.out.println ("Jogador não poderá mover de ambiente");
        }
    }
    public void usarItem () {
        if (getEnergia () > 0) {
            System.out.println ("Jogador está usando item");
        }
        else {
            System.out.println ("Jogador não poderá usar item");
        }
    }
    public void curar () {
        if (getEnergia() >= 0) {
            System.out.println("Jogador está se curando");
        }
    }
    public void descansar () {
            if (getEnergia() >= 0) {
                System.out.println ("Jogador está descansando");
            }
    }
    public void receberDano (int dano) {
        this.vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
    }
    public void habilidadeEspecial(String classe) {
        if (classe.equals("Rastreador")) {
        } else if (classe.equals("Mecânico")) {
        } else if (classe.equals("Médico")) {
        } else if (classe.equals("Sobrevivente Nato")) {
        } else {
        }
    }
}
