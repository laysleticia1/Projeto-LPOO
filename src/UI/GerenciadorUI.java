package UI;

import javax.swing.*;
import java.awt.*;
import Jogo.Jogo;

// Importa todas as classes de painel
import UI.MenuInicialPanel;
import UI.TelaNome;
import UI.TelaPersonagem;
import UI.TelaDetalhePersonagem;
import UI.TelaClasse;
import UI.TelaNarrativa;
import UI.TelaMapa;
import UI.PainelJogo;

public class GerenciadorUI {
    private JFrame window;
    private JPanel painelPrincipalCardLayout;
    private Jogo meuJogo;

    private String nomePersonagemAtual;
    private int idPersonagemSelecionado = -1;

    private MenuInicialPanel menuInicialPanel;
    private TelaNome telaNome;
    private TelaPersonagem telaPersonagem;
    private TelaDetalhePersonagem telaDetalhePersonagem;
    private TelaClasse telaClasse;
    private TelaNarrativa telaNarrativa;
    private TelaMapa telaMapa;
    private PainelJogo painelJogo;

    public GerenciadorUI() {
        this.meuJogo = new Jogo();
        inicializarUI();
    }

    private void inicializarUI() {
        window = new JFrame("Última Fronteira");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tela cheia (sem redimensionar)
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        painelPrincipalCardLayout = new JPanel(new CardLayout());

        menuInicialPanel = new MenuInicialPanel(painelPrincipalCardLayout, this);
        telaNome = new TelaNome(painelPrincipalCardLayout, this);
        telaPersonagem = new TelaPersonagem(null, this);
        telaDetalhePersonagem = new TelaDetalhePersonagem(null, this);
        telaClasse = new TelaClasse(null, this);
        telaNarrativa = new TelaNarrativa(() -> irParaTelaMapa());
        telaMapa = new TelaMapa(null, this);
        painelJogo = new PainelJogo(null, this);

        painelPrincipalCardLayout.add(menuInicialPanel, "MENU_INICIAL");
        painelPrincipalCardLayout.add(telaNome, "TELA_NOME");
        painelPrincipalCardLayout.add(telaPersonagem, "TELA_PERSONAGEM");
        painelPrincipalCardLayout.add(telaDetalhePersonagem, "TELA_DETALHE_PERSONAGEM");
        painelPrincipalCardLayout.add(telaClasse, "TELA_CLASSE");
        painelPrincipalCardLayout.add(telaNarrativa, "TELA_NARRATIVA");
        painelPrincipalCardLayout.add(telaMapa, "TELA_MAPA");
        painelPrincipalCardLayout.add(painelJogo, "PAINEL_JOGO");

        window.add(painelPrincipalCardLayout);
        window.setVisible(true);
    }

    // Métodos de navegação
    public void irParaMenuInicial() {
        mostrarTela("MENU_INICIAL");
    }

    public void irParaTelaNome() {
        this.nomePersonagemAtual = null;
        this.idPersonagemSelecionado = -1;
        if (telaNome != null) telaNome.resetarTela();
        mostrarTela("TELA_NOME");
    }

    public void irParaTelaPersonagem(String nome) {
        this.nomePersonagemAtual = nome;
        mostrarTela("TELA_PERSONAGEM");
    }

    public void irParaTelaPersonagem() {
        mostrarTela("TELA_PERSONAGEM");
    }

    public void personagemSelecionadoParaDetalhes(int personagemId) {
        this.idPersonagemSelecionado = personagemId;
        if (telaDetalhePersonagem != null) {
            telaDetalhePersonagem.mostrarDetalhes(personagemId);
            mostrarTela("TELA_DETALHE_PERSONAGEM");
        }
    }

    public void personagemConfirmado(int personagemId) {
        this.idPersonagemSelecionado = personagemId;
        if (telaClasse != null) {
            telaClasse.prepararTela();
            mostrarTela("TELA_CLASSE");
        }
    }

    public void irParaTelaNarrativa() {
        mostrarTela("TELA_NARRATIVA");
    }

    public void irParaTelaMapa() {
        if (telaMapa != null) {
            telaMapa.prepararTela();
            mostrarTela("TELA_MAPA");
        }
    }

    public void irParaTelaClasse() {
        if (telaClasse != null) {
            telaClasse.prepararTela();
            mostrarTela("TELA_CLASSE");
        }
    }

    public void irParaPainelJogo() {
        if (painelJogo != null) painelJogo.aoEntrarNaTela();
        mostrarTela("PAINEL_JOGO");
    }

    private void mostrarTela(String nome) {
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, nome);
    }

    // Getters
    public String getNomePersonagemAtual() { return nomePersonagemAtual; }
    public int getIdPersonagemSelecionado() { return idPersonagemSelecionado; }
    public Jogo getMeuJogo() { return meuJogo; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GerenciadorUI());
    }
    public void mudarAmbienteViaUI(String nomeAmbiente) {
        try {
            meuJogo.mudarAmbiente(nomeAmbiente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window,
                    "Erro ao mudar de ambiente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
