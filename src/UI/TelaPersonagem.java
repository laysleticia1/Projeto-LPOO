package UI;

import javax.swing.*;
import java.awt.*;

public class TelaPersonagem extends JPanel {
    private GerenciadorUI controlador;
    private Image backgroundImage;
    private JButton[] botoesPersonagem = new JButton[6];

    public TelaPersonagem(JPanel painelPrincipal, GerenciadorUI controlador) {
        this.controlador = controlador;
        setLayout(null);

        try {
            backgroundImage = new ImageIcon(getClass().getResource("/Resources/Personagens.png")).getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem de fundo da tela de personagens.");
        }

        criarBotoesPersonagem();

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(30, 33, 100, 40);
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setOpaque(false);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoVoltar.addActionListener(e -> controlador.irParaTelaNome());
        add(botaoVoltar);
    }

    private void criarBotoesPersonagem() {
        for (int i = 0; i < 6; i++) {
            JButton botao = new JButton();
            botao.setOpaque(false);
            botao.setContentAreaFilled(false);
            botao.setBorderPainted(false);
            botao.setFocusPainted(false);
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            final int idVisual = i + 1; // ID de 1 a 6 corretamente
            botao.addActionListener(e -> controlador.personagemSelecionadoParaDetalhes(idVisual));

            botoesPersonagem[i] = botao;
            add(botao);
        }
    }

    @Override
    public void doLayout() {
        super.doLayout();

        double escalaX = getWidth() / 1536.0;
        double escalaY = getHeight() / 1024.0;

        int[][] posicoes = {
                {120, 190}, {610, 190}, {1100, 190},
                {120, 620}, {610, 620}, {1100, 620}
        };

        for (int i = 0; i < 6; i++) {
            if (botoesPersonagem[i] != null) {
                int x = (int) (posicoes[i][0] * escalaX);
                int y = (int) (posicoes[i][1] * escalaY);
                int largura = (int) (290 * escalaX);
                int altura = (int) (330 * escalaY);
                botoesPersonagem[i].setBounds(x, y, largura, altura);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
