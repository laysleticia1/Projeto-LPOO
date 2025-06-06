package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaNome extends JPanel {
    private JTextField campoNome;
    private JButton botaoConfirmar;
    private JButton botaoVoltar;
    private GerenciadorUI controlador;
    private Image backgroundImage;

    public TelaNome(JPanel painelPrincipal, GerenciadorUI controlador) {
        this.controlador = controlador;
        setLayout(null);

        try {
            backgroundImage = new ImageIcon(getClass().getResource("/Resources/telaDoNome.png")).getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem de fundo");
        }

        campoNome = new JTextField();
        campoNome.setFont(new Font("SansSerif", Font.BOLD, 22));
        campoNome.setHorizontalAlignment(JTextField.CENTER);
        campoNome.setOpaque(false);
        campoNome.setBackground(new Color(0, 0, 0, 0)); // Sem fundo
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
                JOptionPane.showMessageDialog(this, "Digite um nome.");
            }
        });
        add(botaoConfirmar);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setOpaque(false);
        botaoVoltar.setForeground(Color.WHITE); // Texto branco visível
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltar.addActionListener(e -> controlador.irParaMenuInicial());
        add(botaoVoltar);
    }

    @Override
    public void doLayout() {
        super.doLayout();

        setCoordenadas(
                410, 500, 700, 70,     // Campo de nome (mantém o mesmo)
                500, 810, 520, 130,     // NOVO tamanho do botão CONFIRMAR
                50, 960, 120, 35       // Botão voltar
        );
    }

    public void setCoordenadas(int xCampo, int yCampo, int wCampo, int hCampo,
                               int xConfirmar, int yConfirmar, int wConfirmar, int hConfirmar,
                               int xVoltar, int yVoltar, int wVoltar, int hVoltar) {

        double escalaX = getWidth() / 1536.0;
        double escalaY = getHeight() / 1024.0;

        int barra = getTopLevelAncestor() instanceof JFrame
                ? ((JFrame) getTopLevelAncestor()).getInsets().top
                : 30;

        campoNome.setBounds(
                (int) (xCampo * escalaX),
                (int) ((yCampo - barra) * escalaY),
                (int) (wCampo * escalaX),
                (int) (hCampo * escalaY)
        );

        botaoConfirmar.setBounds(
                (int) (xConfirmar * escalaX),
                (int) ((yConfirmar - barra) * escalaY),
                (int) (wConfirmar * escalaX),
                (int) (hConfirmar * escalaY)
        );

        botaoVoltar.setBounds(
                (int) (xVoltar * escalaX),
                (int) ((yVoltar - barra) * escalaY),
                (int) (wVoltar * escalaX),
                (int) (hVoltar * escalaY)
        );
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
