package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TelaDetalhePersonagem extends JPanel {
    private GerenciadorUI controlador; // Referência ao controlador principal
    private Image imagemCompleta;
    private int personagemIdAtual;

    private final String[] arquivosImagens = {
            "PersonagemSelecionado1.png",
            "PersonagemSelecionado2.png",
            "PersonagemSelecionado3.png",
            "PersonagemSelecionado4.png",
            "PersonagemSelecionado5.png",
            "PersonagemSelecionado6.png"
    };

    public TelaDetalhePersonagem(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) {
        this.controlador = ctrl;
        setLayout(null);
        setPreferredSize(new Dimension(1152, 768));

        // Botão "Selecionar"
        JButton botaoSelecionar = new JButton();
        botaoSelecionar.setBounds(460, 630, 470, 70); // Botão invisível sobre "SELECTED"
        botaoSelecionar.setContentAreaFilled(false);
        botaoSelecionar.setBorderPainted(false);
        botaoSelecionar.setFocusPainted(false);
        botaoSelecionar.setOpaque(false);
        botaoSelecionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoSelecionar.addActionListener(e -> {
            // Confirma o personagem com idVisual (de 1 a 6)
            controlador.personagemConfirmado(personagemIdAtual);
        });
        add(botaoSelecionar);

        // Botão "Voltar"
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(155, 700, 100, 40);
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setOpaque(false);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoVoltar.addActionListener(e -> controlador.irParaTelaPersonagem());
        add(botaoVoltar);
    }

    public void mostrarDetalhes(int personagemId) {
        this.personagemIdAtual = personagemId;

        if (personagemId >= 1 && personagemId <= arquivosImagens.length) {
            String nomeArquivo = arquivosImagens[personagemId - 1];
            try {
                URL imgUrl = getClass().getResource("/Resources/" + nomeArquivo);
                if (imgUrl == null) imgUrl = getClass().getResource(nomeArquivo);
                if (imgUrl != null) {
                    imagemCompleta = new ImageIcon(imgUrl).getImage();
                } else {
                    System.err.println("Imagem não encontrada: " + nomeArquivo);
                    imagemCompleta = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.printStackTrace();
                imagemCompleta = null;
            }
        } else {
            imagemCompleta = null;
        }

        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemCompleta != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(imagemCompleta, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
