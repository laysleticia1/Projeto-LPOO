package UI;

import javax.swing.*;
import java.awt.*;
import Jogo.Jogo;

// Importe todas as suas classes de painel da UI
import UI.MenuInicialPanel;
import UI.TelaNome;
import UI.TelaPersonagem;
import UI.TelaDetalhePersonagem;
import UI.TelaClasse;
import UI.TelaNarrativa;
import UI.TelaMapa; // <<< --- ADICIONE A IMPORTAÇÃO DA TELA MAPA
import UI.PainelJogo;

public class GerenciadorUI {
    private JFrame window;
    private JPanel painelPrincipalCardLayout;
    private Jogo meuJogo;

    private String nomePersonagemAtual;
    private int idPersonagemSelecionado = -1;

    // Instâncias dos painéis da UI
    private MenuInicialPanel menuInicialPanel;
    private TelaNome telaNome;
    private TelaPersonagem telaPersonagem;
    private TelaDetalhePersonagem telaDetalhePersonagem;
    private TelaClasse telaClasse;
    private TelaNarrativa telaNarrativa;
    private TelaMapa telaMapa; // <<< --- DECLARE A INSTÂNCIA DA TELA MAPA
    private PainelJogo painelJogo;

    public GerenciadorUI() {
        this.meuJogo = new Jogo();
        inicializarUI();
    }

    private void inicializarUI() {
        window = new JFrame("Última Fronteira");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        painelPrincipalCardLayout = new JPanel(new CardLayout());

        menuInicialPanel = new MenuInicialPanel(painelPrincipalCardLayout, this);
        telaNome = new TelaNome(painelPrincipalCardLayout, this);
        telaPersonagem = new TelaPersonagem(null, this);
        telaDetalhePersonagem = new TelaDetalhePersonagem(null, this);
        telaClasse = new TelaClasse(null, this);

        // <<< --- AÇÃO DO BOTÃO "CONTINUAR" DA TELA NARRATIVA AGORA VAI PARA TELA MAPA --- >>>
        telaNarrativa = new TelaNarrativa(() -> irParaTelaMapa()); // Alterado aqui

        telaMapa = new TelaMapa(null, this); // <<< --- INSTANCIE A TELA MAPA
        painelJogo = new PainelJogo(null, this);

        painelPrincipalCardLayout.add(menuInicialPanel, "MENU_INICIAL");
        painelPrincipalCardLayout.add(telaNome, "TELA_NOME");
        painelPrincipalCardLayout.add(telaPersonagem, "TELA_PERSONAGEM");
        painelPrincipalCardLayout.add(telaDetalhePersonagem, "TELA_DETALHE_PERSONAGEM");
        painelPrincipalCardLayout.add(telaClasse, "TELA_CLASSE");
        painelPrincipalCardLayout.add(telaNarrativa, "TELA_NARRATIVA");
        painelPrincipalCardLayout.add(telaMapa, "TELA_MAPA"); // <<< --- ADICIONE A TELA MAPA AO CARDLAYOUT
        painelPrincipalCardLayout.add(painelJogo, "PAINEL_JOGO");

        window.add(painelPrincipalCardLayout);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // --- Métodos de Navegação ---
    // (irParaMenuInicial, irParaTelaNome, irParaTelaPersonagem (ambos),
    // personagemSelecionadoParaDetalhes, personagemConfirmado permanecem os mesmos)
    // ...

    public void irParaMenuInicial() {
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "MENU_INICIAL");
    }

    public void irParaTelaNome() {
        this.nomePersonagemAtual = null;
        this.idPersonagemSelecionado = -1;
        if (telaNome != null) {
            telaNome.resetarTela();
        }
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "TELA_NOME");
    }

    public void irParaTelaPersonagem(String nome) {
        this.nomePersonagemAtual = nome;
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "TELA_PERSONAGEM");
    }

    public void irParaTelaPersonagem() {
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "TELA_PERSONAGEM");
    }

    public void personagemSelecionadoParaDetalhes(int personagemId) {
        this.idPersonagemSelecionado = personagemId;
        if (telaDetalhePersonagem != null) {
            telaDetalhePersonagem.mostrarDetalhes(this.idPersonagemSelecionado);
            CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
            cl.show(painelPrincipalCardLayout, "TELA_DETALHE_PERSONAGEM");
        } else {
            System.err.println("GerenciadorUI ERRO: telaDetalhePersonagem não foi inicializada!");
        }
    }

    public void personagemConfirmado(int personagemId) {
        this.idPersonagemSelecionado = personagemId;
        if (telaClasse != null) {
            if (telaClasse.isDisplayable() || true) {
                telaClasse.prepararTela();
            }
            CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
            cl.show(painelPrincipalCardLayout, "TELA_CLASSE");
        } else {
            System.err.println("GerenciadorUI ERRO: telaClasse não foi inicializada!");
        }
    }

    public void irParaTelaNarrativa() {
        // Se TelaNarrativa precisar ser atualizada com dados, você pode chamar um método aqui
        // Ex: if (telaNarrativa != null) telaNarrativa.prepararNarrativa(controlador.getNomePersonagemAtual(), ...);
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "TELA_NARRATIVA");
    }

    // <<< --- NOVO MÉTODO DE NAVEGAÇÃO PARA A TELA MAPA --- >>>
    public void irParaTelaMapa() {
        System.out.println("GerenciadorUI: Navegando para a Tela Mapa.");
        if (telaMapa != null) {
            telaMapa.prepararTela(); // Chama o método para preparar a tela do mapa
            CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
            cl.show(painelPrincipalCardLayout, "TELA_MAPA");
        } else {
            System.err.println("GerenciadorUI ERRO: telaMapa não foi inicializada!");
        }
    }

    public void irParaPainelJogo() {
        // System.out.println("GerenciadorUI: Navegando para o Painel do Jogo.");
        if (painelJogo != null) {
            painelJogo.aoEntrarNaTela();
        } else {
            System.err.println("GerenciadorUI ERRO: painelJogo não foi inicializado antes de irParaPainelJogo!");
        }
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "PAINEL_JOGO");
    }

    // --- Getters ---
    // (getNomePersonagemAtual, getIdPersonagemSelecionado, getMeuJogo permanecem os mesmos)
    // ...
    public String getNomePersonagemAtual() { return nomePersonagemAtual; }
    public int getIdPersonagemSelecionado() { return idPersonagemSelecionado; }
    public Jogo getMeuJogo() { return meuJogo; }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorUI();
            }
        });
    }
}
