package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL; // Necessário para getClass().getResource()

public class TelaNome extends JPanel {
    private JTextField campoNome;
    private JButton botaoConfirmar; // Este é o botão "CONFIRMAR" (laranja)
    private JButton botaoVoltar;
    private GerenciadorUI controlador;
    private Image backgroundImage;

    // Dimensões base para o design (largura e altura da sua imagem de fundo 'telaDoNome.png')
    private final int BASE_LARGURA_DESIGN = 1536; // Confirme se sua imagem tem essa largura
    private final int BASE_ALTURA_DESIGN = 1024; // Confirme se sua imagem tem essa altura

    public TelaNome(JPanel painelPrincipal, GerenciadorUI controlador) {
        this.controlador = controlador;
        setLayout(null); // Usando layout nulo para posicionamento manual

        // Carrega o fundo
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

        // --- CAMPO DE NOME (A BARRA PRETA ONDE SE ESCREVE) ---
        campoNome = new JTextField();
        campoNome.setFont(new Font("SansSerif", Font.BOLD, 28)); // Aumentado para 28
        campoNome.setHorizontalAlignment(JTextField.CENTER);
        campoNome.setOpaque(true);
        campoNome.setBackground(new Color(0, 0, 0, 80));
        campoNome.setForeground(Color.WHITE);
        campoNome.setCaretColor(Color.WHITE);
        campoNome.setBorder(null);
        add(campoNome);

        // --- BOTÃO CONFIRMAR (O BOTÃO LARANJA NA PARTE INFERIOR) ---
        // AQUI ESTÁ A MUDANÇA CRÍTICA: O BOTÃO NÃO TERÁ TEXTO PRÓPRIO.
        // Ele vai confiar no texto que já está na imagem de fundo.
        botaoConfirmar = new JButton(); // AGORA SEM NENHUM TEXTO DEFINIDO NO CONSTRUTOR DO BOTÃO
        // Remova a linha abaixo se ela existir no seu código atual para este botão
        // botaoConfirmar.setFont(new Font("SansSerif", Font.BOLD, 30)); // REMOVIDO, pois não terá texto

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

        // --- BOTÃO VOLTAR (O PEQUENO NO CANTO INFERIOR ESQUERDO) ---
        // Mantido o texto e o ajuste de fonte/tamanho.
        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 20));
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBackground(new Color(0, 0, 0, 80));
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setBorder(null);
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

        // --- APLICANDO COORDENADAS E TAMANHOS ESCALADOS ---
        // Campo de nome: Altura aumentada para 100
        campoNome.setBounds(
                (int) (410 * escalaX),
                (int) ((470 - correcaoBarra) * escalaY),
                (int) (700 * escalaX),
                (int) (100 * escalaY)
        );

        // Botão confirmar (laranja): Coordenadas base (mantidas)
        botaoConfirmar.setBounds(
                (int) (500 * escalaX),
                (int) ((810 - correcaoBarra) * escalaY),
                (int) (520 * escalaX),
                (int) (130 * escalaY)
        );

        // Botão voltar: Largura e altura ajustadas
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