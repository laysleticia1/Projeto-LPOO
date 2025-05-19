package Personagem.Superclasse;

import Ambiente.Superclasse.Ambiente;
import Personagem.Inventario.Inventario;
import Item.Superclasse.Item;

public class Personagem {
    private String nome;
    private String classe;
    private int nivel;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private Inventario inventario;
    private String [] localizacao;
    private int x, y; // Coordenadas
    private int velocidade;
    private Ambiente ambienteAtual;

    public Personagem (String nomeUsuario, String classeUsuario) { // Construtor
        this.nome = nomeUsuario;
        this.classe = classeUsuario;
        nivel = 1;
        vida = 100;
        fome = 100;
        sede = 100;
        energia = 100;
        sanidade = 100;
        this.inventario = new Inventario ();
        x = 0;
        y = 0;
        velocidade = 10;
    }

    public void atacar() {
        if (energia >= 4) {
            energia -= 4;
            System.out.println("Jogador está atacando (-4 energia)");
        } else {
            System.out.println("Jogador não tem energia suficiente para atacar.");
        }
    }
    public void defender() {
        if (energia >= 2) {
            energia -= 2;
            System.out.println("Jogador está defendendo (-2 energia)");
        } else {
            System.out.println("Jogador não tem energia suficiente para defender.");
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
        //Atualiza a localização do personagem
        setLocalizacao(new String [] {novoAmbiente.getNome()});
        //Exibe uma mensagem no terminal
        System.out.println(nome + " se moveu para: " + novoAmbiente.getNome());
        //Descrição do Ambiente
        System.out.println(novoAmbiente.getDescricao());
    }
    public void usarItem(String nomeItem) {
        if (getEnergia() > 0) {
            inventario.usarItem(nomeItem);
        } else {
            System.out.println("Jogador não poderá usar item, pois o nivel de energia está baixo.");
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
    public void getStatus () {
        System.out.println ("Nome: " + getNome());
        System.out.println ("Nível: " + getNivel());
        System.out.println ("Vida: " + getVida());
        System.out.println ("Fome: " + getFome());
        System.out.println ("Sede: " + getSede());
        System.out.println ("Energia: " + getEnergia());
        System.out.println ("Sanidade: " + getSanidade());
    }

    public void menuPrincipal () {
        System.out.println("\nMENU:");
        System.out.println("1 - Ver status");
        System.out.println("2 - Ver inventário");
        System.out.println("3 - Usar item");
        System.out.println("4 - Mudar de ambiente");
        System.out.println("0 - Sair do jogo");
    }
    public void visualizarInventario() {
        inventario.listarItens();
    }

    public void diminuirEnergia(int valor) {
        this.energia -= valor;
        if (this.energia < 0) {
            this.energia = 0;
        }
    }
    public void adicionarAoInventario(Item item) {
        inventario.adicionarItem(item);
    }


    // Getters e Setters
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
    public Inventario getInventario () {
        return inventario;
    }
    public void setInventario (Inventario inventarioUsuario) {
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
    public void setAmbienteAtual(Ambiente ambiente) {
        this.ambienteAtual = ambiente;
    }
    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

}

