package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TelaMapa extends JPanel {
    private GerenciadorUI controlador;
    private Image imagemMapa;
    private JButton botaoContinuarParaJogo;

    private final String NOME_ARQUIVO_MAPA = "mapa1.png"; // << COLOQUE O NOME DO SEU ARQUIVO DE MAPA

    public TelaMapa(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) {
        this.controlador = ctrl;

        try {
            URL imgUrl = getClass().getResource("/Resources/" + NOME_ARQUIVO_MAPA);
            if (imgUrl == null) {
                imgUrl = getClass().getResource(NOME_ARQUIVO_MAPA);
            }
            if (imgUrl != null) {
                this.imagemMapa = new ImageIcon(imgUrl).getImage();
            } else {
                System.err.println("ERRO AO CARREGAR IMAGEM DO MAPA: " + NOME_ARQUIVO_MAPA + " não encontrada.");
                this.imagemMapa = null;
            }
        } catch (Exception e) {
            System.err.println("Exceção ao carregar imagem do mapa: " + e.getMessage());
            this.imagemMapa = null;
        }

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Painel Central (vazio e transparente) para ocupar a região CENTER
        JPanel painelCentralPlaceholder = new JPanel();
        painelCentralPlaceholder.setOpaque(false);
        add(painelCentralPlaceholder, BorderLayout.CENTER);

        // --- Botão para continuar para o jogo ---
        botaoContinuarParaJogo = new JButton("Iniciar Exploração em Velkaria");
        botaoContinuarParaJogo.setFont(new Font("Serif", Font.BOLD, 18)); // Fonte

        // Estilo: Apenas texto, sem fundo ou borda pintada
        botaoContinuarParaJogo.setForeground(new Color(230, 220, 190)); // Cor do texto (bege claro)
        botaoContinuarParaJogo.setOpaque(false);
        botaoContinuarParaJogo.setContentAreaFilled(false);
        botaoContinuarParaJogo.setBorderPainted(false);
        botaoContinuarParaJogo.setFocusPainted(false);
        botaoContinuarParaJogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Adiciona um padding em volta do texto para aumentar a área de clique sutilmente
        botaoContinuarParaJogo.setBorder(new EmptyBorder(10, 15, 10, 15));


        botaoContinuarParaJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador != null) {
                    controlador.irParaPainelJogo();
                }
            }
        });

        // Painel para o botão, para controlar o espaçamento no rodapé
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Botão centralizado horizontalmente
        painelBotao.setOpaque(false); // Transparente para o mapa de fundo aparecer

        // << AJUSTE AQUI O EMPTYBORDER DO PAINEL DO BOTÃO >>
        // (topo, esquerda, baixo, direita)
        // Aumente 'topo' para mais espaço entre o mapa e o botão.
        // Diminua 'baixo' para o botão ficar mais próximo da borda inferior da tela.
        painelBotao.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Ex: 20px acima, 20px abaixo
        painelBotao.add(botaoContinuarParaJogo);

        add(painelBotao, BorderLayout.SOUTH);
    }

    public void prepararTela() {
        System.out.println("TelaMapa: Preparando para exibir o mapa.");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemMapa != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(imagemMapa, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        } else {
            g.setColor(new Color(100, 120, 80));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 24));
            String msg = "Imagem do mapa (" + NOME_ARQUIVO_MAPA + ") não carregada.";
            FontMetrics fm = g.getFontMetrics();
            g.drawString(msg, (getWidth() - fm.stringWidth(msg)) / 2, getHeight() / 2);
        }
    }
}