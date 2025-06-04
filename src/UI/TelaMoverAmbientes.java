package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.Consumer;
import javax.imageio.ImageIO;

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
            imagemOriginal = ImageIO.read(new File("src/Resources/mapa3.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar imagem: " + e.getMessage());
            return;
        }

        imagemEscalada = new JLabel();
        imagemEscalada.setBounds(0, 0, 1, 1);
        layeredPane.add(imagemEscalada, Integer.valueOf(0));

        // Botões invisíveis
        layeredPane.add(criarBotao("Montanhas de Vhaldrak", 85, 65, 300, 300, callback), Integer.valueOf(1));
        layeredPane.add(criarBotao("Floresta de Elvarron", 520, 65, 300, 300, callback), Integer.valueOf(1));
        layeredPane.add(criarBotao("Planícies de Myndros", 950, 65, 300, 300, callback), Integer.valueOf(1));
        layeredPane.add(criarBotao("Fissura de Narzug", 100, 450, 500, 200, callback), Integer.valueOf(1));
        layeredPane.add(criarBotao("Ruínas de Thargor", 770, 450, 500, 200, callback), Integer.valueOf(1));

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int largura = getContentPane().getWidth();
                int altura = getContentPane().getHeight();
                imagemEscalada.setBounds(0, 0, largura, altura);
                atualizarImagemFundo(largura, altura);
            }
        });

        setVisible(true);
    }

    private void atualizarImagemFundo(int largura, int altura) {
        Image img = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        imagemEscalada.setIcon(new ImageIcon(img));
    }

    private JButton criarBotao(String nome, int x, int y, int largura, int altura, Consumer<String> callback) {
        JButton botao = new JButton(); // sem nome visível
        botao.setBounds(x, y, largura, altura);
        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorder(null); // sem borda
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 👈 mostra o dedo ao passar
        botao.addActionListener(e -> {
            callback.accept(nome);
            dispose();
        });
        return botao;
    }
}
