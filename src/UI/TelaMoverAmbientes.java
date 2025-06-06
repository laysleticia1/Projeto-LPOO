package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream; // Para getResourceAsStream
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import java.io.IOException;

public class TelaMoverAmbientes extends JFrame {

    private BufferedImage imagemOriginal;
    private JLabel imagemEscalada;
    private JLayeredPane layeredPane;

    public TelaMoverAmbientes(Consumer<String> callback) {
        setTitle("Escolha seu destino");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        setLayout(null);

        try {
            InputStream imgStream = getClass().getResourceAsStream("/Resources/mapa3.png");
            if (imgStream == null) {
                throw new IOException("Arquivo de mapa '/Resources/mapa3.png' não encontrado no classpath.");
            }
            imagemOriginal = ImageIO.read(imgStream);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar imagem do mapa: " + e.getMessage(), "Erro de Imagem", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        imagemEscalada = new JLabel();
        imagemEscalada.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(imagemEscalada, Integer.valueOf(0));

        layeredPane.add(criarBotao("Montanhas de Vhaldrak", 85, 65, 300, 300, callback), Integer.valueOf(1));
        layeredPane.add(criarBotao("Floresta de Elvarron", 520, 65, 300, 300, callback), Integer.valueOf(1));
        layeredPane.add(criarBotao("Planícies de Myndros", 950, 65, 300, 300, callback), Integer.valueOf(1));
        layeredPane.add(criarBotao("Fissura de Narzug", 100, 450, 500, 200, callback), Integer.valueOf(1));
        layeredPane.add(criarBotao("Ruínas de Thargor", 770, 450, 500, 200, callback), Integer.valueOf(1));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int largura = getContentPane().getWidth();
                int altura = getContentPane().getHeight();
                if (largura > 0 && altura > 0) {
                    imagemEscalada.setBounds(0, 0, largura, altura);
                    atualizarImagemFundo(largura, altura);
                }
            }
        });

        if (getWidth() > 0 && getHeight() > 0) {
            atualizarImagemFundo(getWidth(), getHeight());
        }


        setVisible(true);
    }

    private void atualizarImagemFundo(int largura, int altura) {
        if (imagemOriginal != null && largura > 0 && altura > 0) {
            Image img = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            imagemEscalada.setIcon(new ImageIcon(img));
        }
    }

    private JButton criarBotao(String nomeAmbiente, int x, int y, int largura, int altura, Consumer<String> callback) {
        JButton botao = new JButton();
        botao.setActionCommand(nomeAmbiente);
        botao.setBounds(x, y, largura, altura);
        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.addActionListener(e -> {
            callback.accept(nomeAmbiente);
            dispose();
        });
        return botao;
    }
}