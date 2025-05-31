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
// import UI.PainelJogo; // Se você tiver uma classe separada para o painel do jogo

public class GerenciadorUI {
    private JFrame window;
    private JPanel painelPrincipalCardLayout;
    private Jogo meuJogo;

    // Campos para armazenar dados entre as telas
    private String nomePersonagemAtual;
    private int idPersonagemSelecionado = -1; // Inicializar com valor inválido

    // Instâncias dos painéis da UI
    private MenuInicialPanel menuInicialPanel;
    private TelaNome telaNome;
    private TelaPersonagem telaPersonagem;
    private TelaDetalhePersonagem telaDetalhePersonagem;
    private TelaClasse telaClasse;
    private JPanel painelJogo; // Painel placeholder para a tela principal do jogo

    public GerenciadorUI() {
        this.meuJogo = new Jogo();
        inicializarUI();
    }

    private void inicializarUI() {
        window = new JFrame("Última Fronteira"); // Título da Janela
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        painelPrincipalCardLayout = new JPanel(new CardLayout());

        // Instanciação dos painéis (passando 'this' como controlador)
        // O primeiro parâmetro de TelaPersonagem e TelaDetalhePersonagem pode ser null
        // se eles não usarem diretamente o painelPrincipalCardLayout para lógica interna.
        menuInicialPanel = new MenuInicialPanel(painelPrincipalCardLayout, this);
        telaNome = new TelaNome(painelPrincipalCardLayout, this);
        telaPersonagem = new TelaPersonagem(null, this);
        telaDetalhePersonagem = new TelaDetalhePersonagem(null, this);
        telaClasse = new TelaClasse(painelPrincipalCardLayout, this);

        // Painel de Jogo (Exemplo)
        painelJogo = new JPanel(new BorderLayout());
        JLabel labelJogoPlaceholder = new JLabel("O Jogo Começa Aqui!", SwingConstants.CENTER);
        labelJogoPlaceholder.setFont(new Font("Serif", Font.BOLD, 40));
        painelJogo.add(labelJogoPlaceholder, BorderLayout.CENTER);
        painelJogo.setBackground(new Color(200, 220, 200)); // Cor de exemplo

        // Adicionando os painéis ao CardLayout
        painelPrincipalCardLayout.add(menuInicialPanel, "MENU_INICIAL");
        painelPrincipalCardLayout.add(telaNome, "TELA_NOME");
        painelPrincipalCardLayout.add(telaPersonagem, "TELA_PERSONAGEM");
        painelPrincipalCardLayout.add(telaDetalhePersonagem, "TELA_DETALHE_PERSONAGEM");
        painelPrincipalCardLayout.add(telaClasse, "TELA_CLASSE");
        painelPrincipalCardLayout.add(painelJogo, "PAINEL_JOGO");

        window.add(painelPrincipalCardLayout);
        window.pack(); // Ajusta o tamanho da janela ao conteúdo
        window.setLocationRelativeTo(null); // Centraliza
        window.setVisible(true);
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

    /**
     * Chamado pela TelaNome após o nome ser confirmado para ir para a seleção de personagem.
     * @param nome O nome do personagem escolhido.
     */
    public void irParaTelaPersonagem(String nome) {
        this.nomePersonagemAtual = nome;
        System.out.println("GerenciadorUI: Nome do personagem definido como: " + this.nomePersonagemAtual);
        if (telaPersonagem != null) {
            // Se TelaPersonagem precisar de alguma preparação ao ser exibida com um novo nome
            // Ex: telaPersonagem.prepararTela(); // (já que ela pode pegar o nome via getNomePersonagemAtual)
        }
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "TELA_PERSONAGEM");
    }

    /**
     * Chamado pela TelaDetalhePersonagem para VOLTAR para a tela de seleção de personagem.
     */
    public void irParaTelaPersonagem() {
        if (telaPersonagem != null) {
            // Se TelaPersonagem tiver um método como prepararTela() para resetar ou atualizar algo
            // Ex: telaPersonagem.prepararTela();
        }
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "TELA_PERSONAGEM");
    }


    /**
     * Chamado pela TelaPersonagem quando um personagem é clicado/selecionado para ver detalhes.
     * @param personagemId O ID do personagem selecionado (0 a 5).
     */
    public void personagemSelecionadoParaDetalhes(int personagemId) {
        this.idPersonagemSelecionado = personagemId;
        System.out.println("GerenciadorUI: Personagem ID " + this.idPersonagemSelecionado + " selecionado para detalhes.");
        if (telaDetalhePersonagem != null) {
            telaDetalhePersonagem.mostrarDetalhes(this.idPersonagemSelecionado);
            CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
            cl.show(painelPrincipalCardLayout, "TELA_DETALHE_PERSONAGEM");
        } else {
            System.err.println("GerenciadorUI ERRO: telaDetalhePersonagem não foi inicializada!");
        }
    }

    /**
     * Chamado pela TelaDetalhePersonagem após o jogador confirmar o personagem escolhido.
     * Navega para a tela de seleção de classe.
     * @param personagemId O ID do personagem confirmado.
     */
    public void personagemConfirmado(int personagemId) {
        this.idPersonagemSelecionado = personagemId; // Garante que está atualizado
        System.out.println("GerenciadorUI: Personagem ID " + this.idPersonagemSelecionado + " confirmado.");
        System.out.println("Nome do Jogador: " + this.nomePersonagemAtual);

        if (telaClasse != null) {
            // Se TelaClasse precisar ser preparada com informações:
            // if (telaClasse instanceof TelaClasse) {
            //     ((TelaClasse) telaClasse).prepararTela(this.nomePersonagemAtual, this.idPersonagemSelecionado);
            // }
            CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
            cl.show(painelPrincipalCardLayout, "TELA_CLASSE");
        } else {
            System.err.println("GerenciadorUI ERRO: telaClasse não foi inicializada!");
        }
    }

    /**
     * Chamado pela TelaClasse após a classe ser escolhida e o personagem criado em Jogo.java.
     * Navega para a tela principal do jogo.
     */
    public void irParaPainelJogo() {
        // A lógica de Jogo.iniciarNovaPartida(nome, classe) deve ter sido chamada dentro da TelaClasse
        // antes de chamar este método, usando getNomePersonagemAtual() e getIdPersonagemSelecionado()
        // para obter os dados necessários.
        System.out.println("GerenciadorUI: Navegando para o Painel do Jogo.");
        CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
        cl.show(painelPrincipalCardLayout, "PAINEL_JOGO");
    }

    // --- Getters ---
    public String getNomePersonagemAtual() {
        return nomePersonagemAtual;
    }

    public int getIdPersonagemSelecionado() {
        return idPersonagemSelecionado;
    }

    public Jogo getMeuJogo() {
        return meuJogo;
    }

    // --- Método Principal (Main) ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorUI();
            }
        });
    }
}