package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TelaPersonagem extends JPanel {
    private GerenciadorUI controlador;
    private Image backgroundImage;
    private List<JButton> botoesPersonagem;

    // << COLOQUE AQUI O NOME CORRETO DO SEU ARQUIVO DE ARTE PRINCIPAL >>
    private final String NOME_ARQUIVO_BACKGROUND = "Personagens.png"; // Exemplo

    public TelaPersonagem(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) {
        this.controlador = ctrl;
        this.botoesPersonagem = new ArrayList<>();

        // Carregar imagem de fundo
        try {
            URL imgUrl = getClass().getResource("/Resources/" + NOME_ARQUIVO_BACKGROUND);
            if (imgUrl == null) imgUrl = getClass().getResource(NOME_ARQUIVO_BACKGROUND);

            if (imgUrl != null) {
                this.backgroundImage = new ImageIcon(imgUrl).getImage();
            } else {
                System.err.println("ERRO GRAVE: Imagem de fundo '" + NOME_ARQUIVO_BACKGROUND + "' NÃO ENCONTRADA!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }

        setLayout(null); // Usaremos layout nulo para posicionamento manual
        setPreferredSize(new Dimension(800, 600)); // Tamanho do painel

        // --- Título "SELECIONE SEU PERSONAGEM" ---
        // Este título já está na sua arte "Personagens.png", então não precisamos de um JLabel para ele.

        // --- Botões Transparentes para os Personagens ---
        // Os valores abaixo são ESTIMATIVAS. Você DEVE ajustá-los baseados na sua arte.
        // Formato: criarBotaoPersonagem(ID_DO_PERSONAGEM, X, Y, LARGURA, ALTURA);

        int larguraBotaoPersonagemEstimada = 170; // << AJUSTE AQUI: Largura de cada retrato
        int alturaBotaoPersonagemEstimada = 200;  // << AJUSTE AQUI: Altura de cada retrato

        // Coordenadas Y estimadas para as linhas
        int yLinha1Estimada = 115; // << AJUSTE AQUI: Y do topo da 1ª linha de personagens
        int gapVerticalEstimado = 30;
        int yLinha2Estimada = yLinha1Estimada + alturaBotaoPersonagemEstimada + gapVerticalEstimado;

        // Coordenadas X estimadas para as colunas
        int margemHorizontalBloco = 85;
        int gapHorizontalEstimado = 50;
        int xColuna1Estimada = margemHorizontalBloco;
        int xColuna2Estimada = xColuna1Estimada + larguraBotaoPersonagemEstimada + gapHorizontalEstimado;
        int xColuna3Estimada = xColuna2Estimada + larguraBotaoPersonagemEstimada + gapHorizontalEstimado;

        // Personagens da Linha de Cima
        criarBotaoPersonagem(0, xColuna1Estimada, yLinha1Estimada, larguraBotaoPersonagemEstimada, alturaBotaoPersonagemEstimada);
        criarBotaoPersonagem(1, xColuna2Estimada, yLinha1Estimada, larguraBotaoPersonagemEstimada, alturaBotaoPersonagemEstimada);
        criarBotaoPersonagem(2, xColuna3Estimada, yLinha1Estimada, larguraBotaoPersonagemEstimada, alturaBotaoPersonagemEstimada);

        // Personagens da Linha de Baixo
        criarBotaoPersonagem(3, xColuna1Estimada, yLinha2Estimada, larguraBotaoPersonagemEstimada, alturaBotaoPersonagemEstimada);
        criarBotaoPersonagem(4, xColuna2Estimada, yLinha2Estimada, larguraBotaoPersonagemEstimada, alturaBotaoPersonagemEstimada);
        criarBotaoPersonagem(5, xColuna3Estimada, yLinha2Estimada, larguraBotaoPersonagemEstimada, alturaBotaoPersonagemEstimada);

        System.out.println("TelaPersonagem: Botões de personagem criados com coordenadas ESTIMADAS.");
        System.out.println("POR FAVOR, AJUSTE OS VALORES DE X, Y, LARGURA, ALTURA no código para cada botão!");

        // --- Botão Voltar (para TelaNome) ---
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 14));

        botaoVoltar.setOpaque(false);
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setForeground(new Color(200, 200, 200));
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltar.setBorder(new EmptyBorder(5, 8, 5, 8));

        // Posicionamento no rodapé ESQUERDO
        int alturaPainel = 600;

        int larguraBotaoVoltar = 100;
        int alturaBotaoVoltar = 30;
        int margemEsquerda = 20;      // << Distância da borda ESQUERDA do painel
        int margemInferior = 20;      // << Distância da borda inferior do painel

        int xBotaoVoltar = margemEsquerda; // Posiciona na margem esquerda
        int yBotaoVoltar = alturaPainel - alturaBotaoVoltar - margemInferior; // Ex: 600 - 30 - 20 = 550

        botaoVoltar.setBounds(xBotaoVoltar, yBotaoVoltar, larguraBotaoVoltar, alturaBotaoVoltar);

        System.out.println("Botao Voltar Posicionado em: X=" + xBotaoVoltar + ", Y=" + yBotaoVoltar);

        botaoVoltar.addActionListener(e -> controlador.irParaTelaNome());
        add(botaoVoltar);
    }

    private void criarBotaoPersonagem(final int personagemId, int x, int y, int largura, int altura) {
        JButton botao = new JButton();
        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBounds(x, y, largura, altura);

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TelaPersonagem: Personagem " + personagemId + " clicado.");
                controlador.personagemSelecionadoParaDetalhes(personagemId);
            }
        });
        botoesPersonagem.add(botao);
        add(botao);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            g2d.dispose();
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 16));
            g.drawString("Imagem de fundo (" + NOME_ARQUIVO_BACKGROUND + ") não carregada.", 50, 50);
        }
    }
}