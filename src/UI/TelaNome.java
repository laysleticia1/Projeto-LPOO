package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class TelaNome extends JPanel {
    private GerenciadorUI controlador;
    private Image backgroundImage;

    private JTextField campoNome;
    private JButton botaoConfirmar;
    private JButton botaoVoltarMenu;

    // Cores
    private final Color corTextoBege = new Color(230, 220, 200);
    private final Color corFundoCampo = new Color(35, 45, 35);
    private final Color corBordaCampo = new Color(90, 100, 90);

    public TelaNome(JPanel painelPrincipal, GerenciadorUI ctrl) {
        this.controlador = ctrl;

        // Carrega imagem de fundo
        try {
            URL imgUrl = getClass().getResource("/Resources/telaDoNome.png");
            if (imgUrl == null) imgUrl = getClass().getResource("telaDoNome.png");
            if (imgUrl != null) this.backgroundImage = new ImageIcon(imgUrl).getImage();
            else System.err.println("ERRO GRAVE: Imagem de fundo não encontrada!");
        } catch (Exception e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }

        setPreferredSize(new Dimension(800, 600));
        setLayout(null); // Layout absoluto

        // CAMPO DE TEXTO CENTRALIZADO
        campoNome = new JTextField();
        campoNome.setFont(new Font("SansSerif", Font.PLAIN, 20));
        campoNome.setHorizontalAlignment(JTextField.CENTER);
        campoNome.setBackground(corFundoCampo);
        campoNome.setForeground(corTextoBege);
        campoNome.setCaretColor(corTextoBege);
        Border bordaExterna = BorderFactory.createLineBorder(corBordaCampo, 1);
        Border bordaInterna = new EmptyBorder(8, 15, 8, 15);
        campoNome.setBorder(new CompoundBorder(bordaExterna, bordaInterna));
        campoNome.setBounds(220, 270, 360, 50); // CENTRALIZADO
        add(campoNome);

        // BOTÃO TRANSPARENTE SOBRE A ARTE "CONFIRMAR"
        botaoConfirmar = new JButton();
        botaoConfirmar.setOpaque(false);
        botaoConfirmar.setContentAreaFilled(false);
        botaoConfirmar.setBorderPainted(false);
        botaoConfirmar.setFocusPainted(false);
        botaoConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoConfirmar.setBounds(255, 455, 280, 60);  // POSIÇÃO EXATA CONFORME A ARTE
        add(botaoConfirmar);

        // BOTÃO "VOLTAR AO MENU" NO RODAPÉ
        botaoVoltarMenu = new JButton("Voltar ao Menu");
        botaoVoltarMenu.setFont(new Font("SansSerif", Font.PLAIN, 14));
        botaoVoltarMenu.setOpaque(false);
        botaoVoltarMenu.setContentAreaFilled(false);
        botaoVoltarMenu.setBorderPainted(false);
        botaoVoltarMenu.setForeground(corTextoBege);
        botaoVoltarMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltarMenu.setBorder(new EmptyBorder(5, 10, 5, 10));
        botaoVoltarMenu.setBounds(320, 550, 160, 30); // PARTE INFERIOR DA TELA
        add(botaoVoltarMenu);

        // AÇÕES
        botaoConfirmar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            if (nome.isEmpty() || nome.length() > 15) {
                JOptionPane.showMessageDialog(this, "Nome inválido (1-15 caracteres).", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            controlador.irParaTelaPersonagem(nome);
        });

        botaoVoltarMenu.addActionListener(e -> controlador.irParaMenuInicial());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 16));
            g.drawString("Arte de fundo não carregada!", 50, getHeight() / 2);
        }
    }

    public void resetarTela() {
        campoNome.setText("");
        campoNome.requestFocusInWindow();
    }
}



