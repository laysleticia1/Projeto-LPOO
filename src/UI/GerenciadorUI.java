package UI;

import javax.swing.*;
import java.awt.*;
import Jogo.Jogo; // Sua classe principal da lógica do jogo

// Importe todas as suas classes de painel da UI
import UI.MenuInicialPanel;
import UI.TelaNome;
import UI.TelaPersonagem;
import UI.TelaDetalhePersonagem;
import UI.TelaClasse;
import UI.TelaNarrativa;
import UI.PainelJogo; // Import da tela principal do jogo

public class GerenciadorUI {
    private JFrame window;
    private JPanel painelPrincipalCardLayout;
    private Jogo meuJogo;

    // Campos para armazenar dados que transitam entre as telas
    private String nomePersonagemAtual;
    private int idPersonagemSelecionado = -1; // Inicializa com um valor que indique "não selecionado"

    // Instâncias dos painéis (telas) da UI
    private MenuInicialPanel menuInicialPanel;
    private TelaNome telaNome;
    private TelaPersonagem telaPersonagem;
    private TelaDetalhePersonagem telaDetalhePersonagem;
    private TelaClasse telaClasse;
    private TelaNarrativa telaNarrativa;
    private PainelJogo painelJogo; // Tipo correto para a tela principal do jogo

    public GerenciadorUI() {
        this.meuJogo = new Jogo(); // Instancia a lógica principal do jogo
        inicializarUI(); // Configura e exibe a interface gráfica
    }

    private void inicializarUI() {
        window = new JFrame("Última Fronteira"); // Título da sua janela principal
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // Mantém o tamanho da janela fixo

        painelPrincipalCardLayout = new JPanel(new CardLayout());

        // Instanciação de cada painel da UI, passando 'this' (GerenciadorUI) como controlador
        menuInicialPanel = new MenuInicialPanel(painelPrincipalCardLayout, this);
        telaNome = new TelaNome(painelPrincipalCardLayout, this);
        // O primeiro parâmetro de TelaPersonagem e TelaDetalhePersonagem pode ser null
        // se eles não usarem diretamente o painelPrincipalCardLayout para alguma lógica interna.
        telaPersonagem = new TelaPersonagem(null, this);
        telaDetalhePersonagem = new TelaDetalhePersonagem(null, this);
        telaClasse = new TelaClasse(null, this); // Ajuste se TelaClasse usar painelPrincipalCardLayout
        telaNarrativa = new TelaNarrativa(() -> irParaPainelJogo());
        painelJogo = new PainelJogo(null, this); // Instancia a classe PainelJogo correta

        // Adicionando todos os painéis ao painel principal (que usa CardLayout)
        painelPrincipalCardLayout.add(menuInicialPanel, "MENU_INICIAL");
        painelPrincipalCardLayout.add(telaNome, "TELA_NOME");
        painelPrincipalCardLayout.add(telaPersonagem, "TELA_PERSONAGEM");
        painelPrincipalCardLayout.add(telaDetalhePersonagem, "TELA_DETALHE_PERSONAGEM");
        painelPrincipalCardLayout.add(telaClasse, "TELA_CLASSE");
        painelPrincipalCardLayout.add(telaNarrativa, "TELA_NARRATIVA");
        painelPrincipalCardLayout.add(painelJogo, "PAINEL_JOGO"); // Adiciona a instância correta de PainelJogo

        window.add(painelPrincipalCardLayout); // Adiciona o painel com CardLayout à janela
        window.pack(); // Ajusta o tamanho da janela para caber os componentes preferidos
        window.setLocationRelativeTo(null); // Centraliza a janela na tela
        window.setVisible(true); // Torna a janela visível
    }

    // --- Métodos de Navegação e Lógica de Transição ---

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
        if (telaPersonagem != null) {
            // Se TelaPersonagem precisar de alguma preparação, chame aqui
            // Ex: telaPersonagem.prepararTela();
        }
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "TELA_PERSONAGEM");
    }

    public void irParaTelaPersonagem() { // Sobrecarga para voltar sem passar nome
        if (telaPersonagem != null) {
            // Ex: telaPersonagem.prepararTela();
        }
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
            if (telaClasse.isDisplayable() || true) { // Condição para resetar se necessário
                telaClasse.prepararTela();
            }
            CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
            cl.show(painelPrincipalCardLayout, "TELA_CLASSE");
        } else {
            System.err.println("GerenciadorUI ERRO: telaClasse não foi inicializada!");
        }
    }

    public void irParaTelaNarrativa() {
        // Se TelaNarrativa precisar ser atualizada com dados do personagem/classe:
        // if (telaNarrativa != null) {
        //     telaNarrativa.setContexto(nomePersonagemAtual, meuJogo.getJogador().getClasse()); // Método hipotético
        // }
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "TELA_NARRATIVA");
    }

    public void irParaPainelJogo() {
        if (painelJogo != null) {
            painelJogo.aoEntrarNaTela(); // Pede para o PainelJogo carregar/atualizar seus dados
        } else {
            System.err.println("GerenciadorUI ERRO: painelJogo não foi inicializado antes de irParaPainelJogo!");
        }
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "PAINEL_JOGO");
    }

    // --- Getters para as telas acessarem informações ---
    public String getNomePersonagemAtual() {
        return nomePersonagemAtual;
    }

    public int getIdPersonagemSelecionado() {
        return idPersonagemSelecionado;
    }

    public Jogo getMeuJogo() {
        return meuJogo;
    }

    // --- Método Principal (Main) para iniciar a aplicação ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorUI();
            }
        });
    }
}
