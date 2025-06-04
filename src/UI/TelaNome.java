package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class TelaNome extends JPanel {
    private JTextField campoNome;
    private JButton botaoConfirmar;
    private JButton botaoVoltar;
    private GerenciadorUI controlador;
    private Image backgroundImage;

    private final int BASE_LARGURA_DESIGN = 1536;
    private final int BASE_ALTURA_DESIGN = 1024;

    public TelaNome(JPanel painelPrincipal, GerenciadorUI controlador) {
        this.controlador = controlador;
        setLayout(null);

        try {
            URL imgUrl = getClass().getResource("/Resources/telaDoNome.png");
            if (imgUrl != null) {
                backgroundImage = new ImageIcon(imgUrl).getImage();
            } else {
                System.err.println("Erro: Imagem de fundo 'telaDoNome.png' não encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar imagem de fundo: " + e.getMessage());
        }

        campoNome = new JTextField();
        campoNome.setFont(new Font("SansSerif", Font.BOLD, 28));
        campoNome.setHorizontalAlignment(JTextField.CENTER);
        campoNome.setOpaque(true);
        campoNome.setBackground(new Color(0, 0, 0, 80));
        campoNome.setForeground(Color.WHITE);
        campoNome.setCaretColor(Color.WHITE);
        campoNome.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        add(campoNome);

        botaoConfirmar = new JButton();
        botaoConfirmar.setOpaque(false);
        botaoConfirmar.setContentAreaFilled(false);
        botaoConfirmar.setBorderPainted(false);
        botaoConfirmar.setFocusPainted(false);
        botaoConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoConfirmar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            if (!nome.isEmpty()) {
                controlador.irParaTelaPersonagem(nome);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, digite um nome para continuar.", "Nome Vazio", JOptionPane.WARNING_MESSAGE);
            }
        });
        add(botaoConfirmar);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 20));
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setOpaque(false);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltar.addActionListener(e -> controlador.irParaMenuInicial());
        add(botaoVoltar);
    }

    @Override
    public void doLayout() {
        super.doLayout();

        double escalaX = getWidth() / (double) BASE_LARGURA_DESIGN;
        double escalaY = getHeight() / (double) BASE_ALTURA_DESIGN;

        int correcaoBarra = 0;
        if (getTopLevelAncestor() instanceof JFrame) {
            correcaoBarra = ((JFrame) getTopLevelAncestor()).getInsets().top;
        }

        campoNome.setBounds(
                (int) (410 * escalaX),
                (int) ((470 - correcaoBarra) * escalaY),
                (int) (700 * escalaX),
                (int) (100 * escalaY)
        );

        botaoConfirmar.setBounds(
                (int) (500 * escalaX),
                (int) ((810 - correcaoBarra) * escalaY),
                (int) (520 * escalaX),
                (int) (130 * escalaY)
        );

        botaoVoltar.setBounds(
                (int) (50 * escalaX),
                (int) ((960 - correcaoBarra) * escalaY),
                (int) (150 * escalaX),
                (int) (45 * escalaY)
        );

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void resetarTela() {
        campoNome.setText("");
    }
}