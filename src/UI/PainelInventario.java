package UI;

import Item.Superclasse.Item;
import Item.Subclasses.*;
import Personagem.Inventario.Inventario;
import Personagem.Superclasse.*;
import Personagem.Subclasses.*;
import Excecoes.InventarioCheioException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.util.List;
import java.util.ArrayList;

public class PainelInventario extends JPanel {
    private BufferedImage background;
    private Inventario inventario;
    private int paginaAtual = 0;
    private final int ITENS_POR_PAGINA = 9;
    private List<JButton> botoesSlots = new ArrayList<>();
    private Personagem jogador;

    private JLabel labelPeso;
    private JTextPane areaDescricao;
    private JButton botaoUsar;
    private JButton botaoRemover;
    private Item itemSelecionado;

    public PainelInventario(Inventario inventario, Personagem jogador) {
        this.inventario = inventario;
        this.jogador = jogador;

        setLayout(null);
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(tela);
        setSize(tela);
        setBounds(0, 0, tela.width, tela.height);

        try {
            background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Resources/inventario.png"));
        } catch (IOException | NullPointerException e) {
            System.err.println("Erro ao carregar o fundo do inventário.");
        }

        JButton btnProxima = new JButton("→");
        btnProxima.setBounds(760, 700, 50, 30);
        btnProxima.setBackground(new Color(80, 50, 30, 180));
        btnProxima.setForeground(Color.WHITE);
        btnProxima.setOpaque(true);
        btnProxima.setBorderPainted(false);
        btnProxima.addActionListener(e -> {
            int totalPaginas = (int) Math.ceil((double) inventario.getTodosItens().size() / ITENS_POR_PAGINA);
            if (paginaAtual + 1 < totalPaginas) {
                paginaAtual++;
                renderizarSlots();
            }
        });
        add(btnProxima);

        JButton btnAnterior = new JButton("←");
        btnAnterior.setBounds(520, 700, 50, 30);
        btnAnterior.setBackground(new Color(80, 50, 30, 180));
        btnAnterior.setForeground(Color.WHITE);
        btnAnterior.setOpaque(true);
        btnAnterior.setBorderPainted(false);
        btnAnterior.addActionListener(e -> {
            if (paginaAtual > 0) {
                paginaAtual--;
                renderizarSlots();
            }
        });
        add(btnAnterior);

        labelPeso = new JLabel();
        labelPeso.setForeground(Color.WHITE);
        labelPeso.setFont(new Font("Serif", Font.BOLD, 18));
        labelPeso.setBounds(600, 700, 300, 30);
        add(labelPeso);

        areaDescricao = new JTextPane();
        areaDescricao.setEditable(false);
        areaDescricao.setOpaque(false);
        areaDescricao.setForeground(new Color(182, 167, 47));
        areaDescricao.setFont(new Font("Serif", Font.ITALIC, 19));
        areaDescricao.setBounds(760, 260, 430, 300);

        StyledDocument doc = areaDescricao.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        add(areaDescricao);

        botaoUsar = new JButton("Usar Item");
        botaoUsar.setBounds(840, 540, 120, 30);
        botaoUsar.setBackground(new Color(100, 70, 40));
        botaoUsar.setForeground(Color.WHITE);
        botaoUsar.setEnabled(false);
        botaoUsar.addActionListener(e -> {
            if (itemSelecionado != null) {
                try {
                    inventario.usarItem(itemSelecionado.getNome(), jogador);
                    JOptionPane.showMessageDialog(this, "Você usou: " + itemSelecionado.getNome());
                    atualizarPainel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao usar item: " + ex.getMessage());
                }
            }
        });
        add(botaoUsar);

        botaoRemover = new JButton("Remover Item");
        botaoRemover.setBounds(980, 540, 140, 30);
        botaoRemover.setBackground(new Color(100, 70, 40));
        botaoRemover.setForeground(Color.WHITE);
        botaoRemover.setEnabled(false);
        botaoRemover.addActionListener(e -> {
            if (itemSelecionado != null) {
                int resposta = JOptionPane.showConfirmDialog(this,
                        "Deseja remover " + itemSelecionado.getNome() + " do inventário?",
                        "Remover Item", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    inventario.removerItem(itemSelecionado.getNome());
                    JOptionPane.showMessageDialog(this, itemSelecionado.getNome() + " foi removido.");
                    atualizarPainel();
                }
            }
        });
        add(botaoRemover);

        renderizarSlots();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

        double pesoAtual = inventario.getPesoTotal();
        double capacidadeMax = inventario.getCapacidadeMaxima();
        labelPeso.setText("Peso: " + String.format("%.2f", pesoAtual) + " / " + capacidadeMax);
    }

    private void renderizarSlots() {
        for (JButton btn : botoesSlots) {
            this.remove(btn);
        }
        botoesSlots.clear();

        List<Item> todosItens = new ArrayList<>();
        for (Map.Entry<String, List<Item>> entry : inventario.getItensPorCategoria().entrySet()) {
            todosItens.addAll(entry.getValue());
        }

        int inicio = paginaAtual * ITENS_POR_PAGINA;
        int fim = Math.min(inicio + ITENS_POR_PAGINA, todosItens.size());
        List<Item> pagina = todosItens.subList(inicio, fim);

        int[][] coordenadas = {
                {155, 180}, {350, 180}, {530, 180},
                {155, 330}, {350, 330}, {530, 330},
                {155, 480}, {350, 480}, {530, 480},
        };
        int largura = 105;
        int altura = 105;

        for (int i = 0; i < pagina.size(); i++) {
            Item item = pagina.get(i);
            int x = coordenadas[i][0];
            int y = coordenadas[i][1];

            JButton slotBtn = new JButton();
            slotBtn.setBounds(x, y, largura, altura);
            slotBtn.setOpaque(false);
            slotBtn.setContentAreaFilled(false);
            slotBtn.setBorderPainted(false);
            slotBtn.setFocusPainted(false);
            slotBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            slotBtn.addActionListener(e -> {
                itemSelecionado = item;
                areaDescricao.setText(item.exibirDetalhesInterface());
                botaoUsar.setEnabled(true);
                botaoRemover.setEnabled(true);
            });

            this.add(slotBtn);
            botoesSlots.add(slotBtn);
        }

        repaint();
        revalidate();
    }

    private void atualizarPainel() {
        itemSelecionado = null;
        areaDescricao.setText("");
        botaoUsar.setEnabled(false);
        botaoRemover.setEnabled(false);
        renderizarSlots();
    }
}
