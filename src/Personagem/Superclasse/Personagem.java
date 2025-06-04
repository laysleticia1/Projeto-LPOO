package Personagem.Superclasse;

import Ambiente.Superclasse.Ambiente;
import Excecoes.InventarioCheioException;
import Personagem.Inventario.Inventario;
import Personagem.Subclasses.*;
import Item.Superclasse.Item;
import Interface.Movivel;
import Excecoes.FomeSedeSanidadeException;
import javax.swing.JTextArea;

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
    private int x, y;
    private int velocidade;
    private Ambiente ambienteAtual;

    private final int VIDA_MAXIMA = 100;
    private final int FOME_MAXIMA = 100;
    private final int SEDE_MAXIMA = 100;
    private final int ENERGIA_MAXIMA = 100;
    private final int SANIDADE_MAXIMA = 100;

    public Personagem(String nomeUsuario, String classeUsuario) {
        this.nome = nomeUsuario;
        this.classe = classeUsuario;
        this.nivel = 1;
        this.vida = VIDA_MAXIMA;
        this.fome = FOME_MAXIMA;
        this.sede = SEDE_MAXIMA;
        this.energia = ENERGIA_MAXIMA;
        this.sanidade = SANIDADE_MAXIMA;
        this.inventario = new Inventario(15);
        this.x = 0;
        this.y = 0;
        this.velocidade = 10;
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
        if (this.vida > VIDA_MAXIMA) {
            this.vida = VIDA_MAXIMA;
        }
    }
    public void restaurarEnergia(double quantidade) {
        this.energia += quantidade;
        if (this.energia > ENERGIA_MAXIMA) this.energia = ENERGIA_MAXIMA;
        System.out.println("Energia recuperada em " + (int)quantidade + ". Energia atual: " + this.energia);
    }
    public void restaurarFome(int quantidade) {
        this.fome += quantidade;
        if (this.fome > FOME_MAXIMA) {
            this.fome = FOME_MAXIMA;
        }
        System.out.println("Fome recuperada em " + quantidade + ". Fome atual: " + this.fome);
    }
    public void restaurarSede(int quantidade) {
        this.sede += quantidade;
        if (this.sede > SEDE_MAXIMA) this.sede = SEDE_MAXIMA;
        System.out.println("Sede recuperada em " + quantidade + ". Sede atual: " + this.sede);
    }
    public void restaurarSanidade(int quantidade) {
        this.sanidade += quantidade;
        if (this.sanidade > SANIDADE_MAXIMA) this.sanidade = SANIDADE_MAXIMA;
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
    @Override
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
            if (this.inventario != null) {
                inventario.usarItem(nomeItem, this);
            } else {
                System.out.println("Inventário não inicializado.");
            }
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
        this.energia += 20;
        if (this.energia > ENERGIA_MAXIMA) {
            this.energia = ENERGIA_MAXIMA;
        }
        this.sanidade += 10;
        if (this.sanidade > SANIDADE_MAXIMA) {
            this.sanidade = SANIDADE_MAXIMA;
        }
    }

    public void getStatus () {
        System.out.println ("Nome: " + getNome());
        System.out.println ("Nível: " + getNivel());
        System.out.println ("Vida: " + getVida() + "/" + getVidaMaxima());
        System.out.println ("Fome: " + getFome() + "/" + getFomeMaxima());
        System.out.println ("Sede: " + getSede() + "/" + getSedeMaxima());
        System.out.println ("Energia: " + getEnergia() + "/" + getEnergiaMaxima());
        System.out.println ("Sanidade: " + getSanidade() + "/" + getSanidadeMaxima());
    }

    public void visualizarInventario() {
        if (this.inventario != null) {
            inventario.listarItens();
        } else {
            System.out.println("Inventário não inicializado.");
        }
    }

    public void adicionarAoInventario(Item item) {
        if (this.inventario != null) {
            try{
                inventario.adicionarItem(item);
            } catch (InventarioCheioException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Inventário não inicializado. Não é possível adicionar item.");
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
        boolean perdeuVidaGeral = false;

        if (fome <= 0) {
            diminuirVida(10);
            System.out.println("⚠️ Sua fome chegou a zero! Você perdeu 10 de vida.");
            perdeuVidaGeral = true;
        }
        if (sede <= 0) {
            diminuirVida(10);
            System.out.println("⚠️ Sua sede chegou a zero! Você perdeu 10 de vida.");
            perdeuVidaGeral = true;
        }
        if (sanidade <= 0) {
            diminuirVida(10);
            System.out.println("⚠️ Sua sanidade chegou a zero! Você perdeu 10 de vida.");
            perdeuVidaGeral = true;
        }

        if (perdeuVidaGeral) {
            System.out.println("❤️ Vida atual: " + vida);
        }

        if (vida <= 0) {
            throw new FomeSedeSanidadeException("Você morreu por falta de recursos básicos.");
        }
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
        if (this.vida < 0) this.vida = 0;
        if (this.vida > VIDA_MAXIMA) this.vida = VIDA_MAXIMA;
    }
    public int getFome () {
        return fome;
    }
    public void setFome (int fomeUsuario) {
        this.fome = fomeUsuario;
        if (this.fome < 0) this.fome = 0;
        if (this.fome > FOME_MAXIMA) this.fome = FOME_MAXIMA;
    }
    public int getSede () {
        return sede;
    }
    public void setSede (int sedeUsuario) {
        this.sede = sedeUsuario;
        if (this.sede < 0) this.sede = 0;
        if (this.sede > SEDE_MAXIMA) this.sede = SEDE_MAXIMA;
    }
    public int getEnergia () {
        return energia;
    }
    public void setEnergia (int energiaUsuario) {
        this.energia = energiaUsuario;
        if (this.energia < 0) this.energia = 0;
        if (this.energia > ENERGIA_MAXIMA) this.energia = ENERGIA_MAXIMA;
    }
    public int getSanidade () {
        return sanidade;
    }
    public void setSanidade (int sanidadeUsuario) {
        this.sanidade = sanidadeUsuario;
        if (this.sanidade < 0) this.sanidade = 0;
        if (this.sanidade > SANIDADE_MAXIMA) this.sanidade = SANIDADE_MAXIMA;
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

    public int getVidaMaxima() {
        return VIDA_MAXIMA;
    }

    public int getFomeMaxima() {
        return FOME_MAXIMA;
    }

    public int getSedeMaxima() {
        return SEDE_MAXIMA;
    }

    public int getEnergiaMaxima() {
        return ENERGIA_MAXIMA;
    }

    public int getSanidadeMaxima() {
        return SANIDADE_MAXIMA;
    }

    public void verificarFomeSedeSanidadeInterface(JTextArea areaLog) throws FomeSedeSanidadeException {
        boolean perdeuVida = false;

        if (fome <= 0) {
            vida -= 10;
            if (vida < 0) vida = 0;
            areaLog.append("⚠️ Sua fome chegou a zero! Você perdeu 10 de vida.\n");
            perdeuVida = true;
        }
        if (sede <= 0) {
            vida -= 10;
            if (vida < 0) vida = 0;
            areaLog.append("⚠️ Sua sede chegou a zero! Você perdeu 10 de vida.\n");
            perdeuVida = true;
        }
        if (sanidade <= 0) {
            vida -= 10;
            if (vida < 0) vida = 0;
            areaLog.append("⚠️ Sua sanidade chegou a zero! Você perdeu 10 de vida.\n");
            perdeuVida = true;
        }

        if (perdeuVida) {
            areaLog.append("❤️ Vida atual: " + vida + "\n");
        }

        if (vida <= 0) {
            throw new FomeSedeSanidadeException("☠️ Você morreu por falta de recursos básicos.");
        }
    }
}