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

    private final String NOME_ARQUIVO_MAPA = "mapa1.png"; // Nome do arquivo do mapa

    public TelaMapa(GerenciadorUI ctrl) {
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

        setLayout(null); // Usamos layout absoluto para posicionar o botão livremente

        botaoContinuarParaJogo = new JButton("Iniciar Exploração em Velkaria");
        botaoContinuarParaJogo.setFont(new Font("Serif", Font.BOLD, 24));
        botaoContinuarParaJogo.setForeground(new Color(230, 220, 190));
        botaoContinuarParaJogo.setOpaque(false);
        botaoContinuarParaJogo.setContentAreaFilled(false);
        botaoContinuarParaJogo.setBorderPainted(false);
        botaoContinuarParaJogo.setFocusPainted(false);
        botaoContinuarParaJogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoContinuarParaJogo.setBorder(new EmptyBorder(10, 15, 10, 15));

        botaoContinuarParaJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador != null) {
                    controlador.irParaPainelJogo();
                }
            }
        });

        add(botaoContinuarParaJogo);
    }

    public void prepararTela() {
        System.out.println("TelaMapa: Preparando para exibir o mapa.");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int largura = getWidth();
        int altura = getHeight();

        if (imagemMapa != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(imagemMapa, 0, 0, largura, altura, this); // Redimensiona a imagem para preencher toda a área
            g2d.dispose();
        } else {
            g.setColor(new Color(100, 120, 80));
            g.fillRect(0, 0, largura, altura);
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 24));
            String msg = "Imagem do mapa (" + NOME_ARQUIVO_MAPA + ") não carregada.";
            FontMetrics fm = g.getFontMetrics();
            g.drawString(msg, (largura - fm.stringWidth(msg)) / 2, altura / 2);
        }

        // Posiciona o botão no topo centralizado
        botaoContinuarParaJogo.setBounds((largura - 360) / 2, 70, 360, 50);
    }
}
