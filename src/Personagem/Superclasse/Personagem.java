package Personagem.Superclasse;

import Ambiente.Superclasse.Ambiente;
import Excecoes.InventarioCheioException;
import Personagem.Inventario.Inventario;
import Personagem.Subclasses.*;
import Item.Superclasse.Item;
import Interface.Movivel;
import Excecoes.FomeSedeSanidadeException;

public class Personagem implements Movivel {
    private String nome;
    private String classe;
    private int nivel;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private Inventario inventario;
    private String[] localizacao;
    private int x, y; // Coordenadas
    private int velocidade;
    private Ambiente ambienteAtual;

    public Personagem(String nomeUsuario, String classeUsuario) { // Construtor
        this.nome = nomeUsuario;
        this.classe = classeUsuario;
        nivel = 1;
        vida = 100;
        fome = 100;
        sede = 100;
        energia = 100;
        sanidade = 100;
        this.inventario = new Inventario();
        x = 0;
        y = 0;
        velocidade = 10;
    }

    public void diminuirFome(int quantidade) {
        this.fome -= quantidade;
        if (this.fome < 0) {
            this.fome = 0;
        }
    }

    public void diminuirSede (int quantidade) {
        this.sede -= quantidade;
        if (this.sede < 0) {
            this.sede = 0;
        }
    }

    public void diminuirVida (int quantidade) {
        this.vida -= quantidade;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public void diminuirEnergia(int valor) {
        this.energia -= valor;
        if (this.energia < 0) {
            this.energia = 0;
        }
    }

    public void diminuirSanidade(int quantidade) {
        this.sanidade -= quantidade;
        if (this.sanidade < 0) this.sanidade = 0;
    }

    public void restaurarVida(int quantidade) {
        this.vida += quantidade;

        if (this.vida > 100) {
            this.vida = 100;
        }
    }
    public void restaurarEnergia(double quantidade) {
        this.energia += quantidade;
        if (energia > 100) this.energia = 100;
        System.out.println("Energia recuperada em " + quantidade + ". Energia atual: " + energia);
    }
    public void restaurarFome(int quantidade) {
        this.fome += quantidade;
        if (this.fome > 100) {
            this.fome = 100;
        }
        if (fome > 100) fome = 100;
        System.out.println("Fome recuperada em " + quantidade + ". Fome atual: " + this.fome);
    }
    public void restaurarSede(int quantidade) {
        this.sede += quantidade;
        if (sede > 100) this.sede = 100;
        System.out.println("Sede recuperada em " + quantidade + ". Sede atual: " + this.sede);
    }
    public void restaurarSanidade(int quantidade) {
        this.sanidade += quantidade;
        if (sanidade > 100) this.sanidade = 100;
        System.out.println("Sanidade recuperada em " + quantidade + ". Sanidade atual: " + this.sanidade);
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
        if (energia >= 5) {
            energia -= 5;
        } else {
            System.out.println("Jogador não tem energia suficiente para defender.");
        }
    }
    public void fugir() {
        int custoFuga = 15;
        energia -= custoFuga;

        if (energia < 0) {
            energia = 0;
        }
        System.out.println("Você perdeu 15 de energia.");
        if (energia == 0) {
            System.out.println("Você está exausto... não conseguirá fugir de novo até descansar ou se alimentar.");
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
    public void moverParaAmbiente(Ambiente novoAmbiente) {
        int custoMovimento = 7;

        if (this.energia < custoMovimento) {
            System.out.println("\nVocê está exausto demais para se mover.");
            System.out.println("Energia atual: " + energia + " | Necessário: " + custoMovimento);
            return;
        }

        this.energia -= custoMovimento;
        this.ambienteAtual = novoAmbiente;

        System.out.println("\n🌍 Você se moveu para o ambiente: " + novoAmbiente.getNome());
    }

    public void usarItem(String nomeItem) {
        if (getEnergia() > 0) {
            inventario.usarItem(nomeItem, this);
        } else {
            System.out.println("Jogador não poderá usar item, pois o nível de energia está baixo.");
        }
    }

    public void curar () {
        if (getEnergia() >= 0) {
            System.out.println("Jogador está se curando");
        }
    }
    public void descansar() {
        if (getEnergia() >= 0 && getEnergia() <= 100) {
            this.energia += 20;
            if (this.energia > 100) {
                this.energia = 100;
            }
        }

        if (getSanidade() >= 0 && getSanidade() <= 100) {
            this.sanidade += 10;
            if (this.sanidade > 100) {
                this.sanidade = 100;
            }
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

    public void visualizarInventario() {
        inventario.listarItens();
    }

    public void adicionarAoInventario(Item item) {
        try{
            inventario.adicionarItem(item);
        } catch (InventarioCheioException e) {
            System.out.println("Inventário cheio! " + e.getMessage());
        }
    }

    public void consumirRecursosBasicos() {
        diminuirFome(3);
        diminuirSede(4);

        System.out.println("\n🔋 Consumo diário aplicado:");
        System.out.println("Vida: " + vida);
        System.out.println("Energia: " + energia);
        System.out.println("Fome: " + fome);
        System.out.println("Sede: " + sede);
    }


    public void verificarFomeSedeSanidade() throws FomeSedeSanidadeException {
        boolean perdeuVida = false;

        if (fome <= 0) {
            vida -= 10;
            System.out.println("⚠️ Sua fome chegou a zero! Você perdeu 10 de vida.");
            perdeuVida = true;
        }
        if (sede <= 0) {
            vida -= 10;
            System.out.println("⚠️ Sua sede chegou a zero! Você perdeu 10 de vida.");
            perdeuVida = true;
        }
        if (sanidade <= 0) {
            vida -= 10;
            System.out.println("⚠️ Sua sanidade chegou a zero! Você perdeu 10 de vida.");
            perdeuVida = true;
        }

        if (perdeuVida) {
            System.out.println("❤️ Vida atual: " + vida);
        }

        if (vida <= 0) {
            throw new FomeSedeSanidadeException("Você morreu por falta de recursos básicos.");
        }
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
