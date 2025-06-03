package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MenuInicialPanel extends JPanel {
    private JPanel painelPrincipalCardLayout;
    private GerenciadorUI controlador;
    private Image backgroundImage;
    private JButton botaoIniciar;

    private final int baseLargura = 1280;
    private final int baseAltura = 853;

    public MenuInicialPanel(JPanel painelPrincipal, GerenciadorUI ctrl) {
        this.painelPrincipalCardLayout = painelPrincipal;
        this.controlador = ctrl;

        setLayout(null);

        // 👇 ADICIONA AQUI O LISTENER DE CLIQUE DIREITO
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Clique em: X=" + e.getX() + " Y=" + e.getY());
                }
            }
        });

        try {
            URL imgUrl = getClass().getResource("/Resources/menuInicial.png");
            if (imgUrl == null) imgUrl = getClass().getResource("menuInicial.png");
            if (imgUrl != null) {
                backgroundImage = new ImageIcon(imgUrl).getImage();
            } else {
                System.err.println("Imagem não encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        botaoIniciar = new JButton();
        botaoIniciar.setText("");
        botaoIniciar.setOpaque(false);
        botaoIniciar.setContentAreaFilled(false);
        botaoIniciar.setBorderPainted(false);
        botaoIniciar.setFocusPainted(false);
        botaoIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botaoIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.irParaTelaNome();
            }
        });

        add(botaoIniciar);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        // Chamada direta e simples:
        setBotaoIniciarCoordenadas(600, 540, 340, 78);
    }


    public void setBotaoIniciarCoordenadas(int xBase, int yBase, int larguraBase, int alturaBase) {
        double escalaX = getWidth() / 1280.0;
        double escalaY = getHeight() / 853.0;

        int correcaoBarra = getTopLevelAncestor() instanceof JFrame
                ? ((JFrame) getTopLevelAncestor()).getInsets().top
                : 30;

        int x = (int) (xBase * escalaX);
        int y = (int) ((yBase - correcaoBarra) * escalaY);
        int largura = (int) (larguraBase * escalaX);
        int altura = (int) (alturaBase * escalaY);

        botaoIniciar.setBounds(x, y, largura, altura);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
