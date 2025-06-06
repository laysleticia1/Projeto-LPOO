package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Jogo.Jogo;

public class CriacaoPersonagem extends JPanel {
    private JPanel painelPrincipalCardLayout;
    private Jogo meuJogo;

    private JTextField campoNome;
    private JButton botaoConfirmarNome;

    private JLabel labelClasse;
    private JComboBox<String> comboBoxClasse;
    private JTextPane areaDescricaoClasse;
    private JScrollPane scrollDescricao;

    private JButton botaoCriarPersonagem;
    private JButton botaoVoltar;

    private String nomeConfirmado;
    private int idVisualEscolhido;

    private final Map<String, String> descricoesClasses = new HashMap<>();

    public CriacaoPersonagem(JPanel painelPrincipal, Jogo jogo) {
        this.painelPrincipalCardLayout = painelPrincipal;
        this.meuJogo = jogo;

        descricoesClasses.put("Rastreador", "Mestre em seguir trilhas e identificar perigos na natureza. Encontra recursos com mais facilidade.");
        descricoesClasses.put("Mecânico", "Engenhoso com ferramentas e sucata. Consegue consertar e criar dispositivos úteis para sobrevivência.");
        descricoesClasses.put("Médico", "Especialista em primeiros socorros e tratamento de ferimentos. Consegue usar ervas para criar remédios.");
        descricoesClasses.put("Sobrevivente Nato", "Resistente e adaptável. Possui habilidades instintivas para montar abrigos e fabricar itens básicos.");
        descricoesClasses.put("", "Selecione uma classe para ver a descrição.");

        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(210, 210, 200));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelTitulo = new JLabel("Criação de Personagem");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelNome = new JLabel("Nome do Personagem:");
        labelNome.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelNome, gbc);

        campoNome = new JTextField(20);
        campoNome.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoNome, gbc);

        botaoConfirmarNome = new JButton("Confirmar Nome");
        botaoConfirmarNome.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        add(botaoConfirmarNome, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        labelClasse = new JLabel("Escolha sua Classe:");
        labelClasse.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelClasse, gbc);

        String[] classes = {"", "Rastreador", "Mecânico", "Médico", "Sobrevivente Nato"};
        comboBoxClasse = new JComboBox<>(classes);
        comboBoxClasse.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(comboBoxClasse, gbc);

        areaDescricaoClasse = new JTextPane();
        areaDescricaoClasse.setFont(new Font("SansSerif", Font.ITALIC, 19));
        areaDescricaoClasse.setEditable(false);
        areaDescricaoClasse.setOpaque(false);
        areaDescricaoClasse.setForeground(new Color(182, 167, 47));
        areaDescricaoClasse.setText(descricoesClasses.get(""));
        scrollDescricao = new JScrollPane(areaDescricaoClasse);
        scrollDescricao.setOpaque(false);
        scrollDescricao.getViewport().setOpaque(false);

        areaDescricaoClasse.revalidate();
        areaDescricaoClasse.repaint();

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollDescricao, gbc);
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        labelClasse.setEnabled(false);
        comboBoxClasse.setEnabled(false);
        areaDescricaoClasse.setEnabled(false);

        JPanel painelBotoesFinais = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoesFinais.setOpaque(false);

        botaoCriarPersonagem = new JButton("Criar Personagem");
        botaoCriarPersonagem.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoCriarPersonagem.setPreferredSize(new Dimension(220, 50));
        botaoCriarPersonagem.setEnabled(false);
        painelBotoesFinais.add(botaoCriarPersonagem);

        botaoVoltar = new JButton("Voltar ao Menu");
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setPreferredSize(new Dimension(200, 50));
        painelBotoesFinais.add(botaoVoltar);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(painelBotoesFinais, gbc);

        botaoConfirmarNome.addActionListener(e -> {
            nomeConfirmado = campoNome.getText().trim();
            if (nomeConfirmado.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, digite um nome para o personagem.",
                        "Nome Inválido", JOptionPane.ERROR_MESSAGE);
                return;
            }

            campoNome.setEnabled(false);
            botaoConfirmarNome.setEnabled(false);

            labelClasse.setEnabled(true);
            comboBoxClasse.setEnabled(true);
            areaDescricaoClasse.setEnabled(true);

            botaoCriarPersonagem.setEnabled(true);
            comboBoxClasse.requestFocusInWindow();
        });

        comboBoxClasse.addActionListener(e -> {
            if (comboBoxClasse.isEnabled()) {
                String classeSelecionada = (String) comboBoxClasse.getSelectedItem();
                String texto = descricoesClasses.getOrDefault(classeSelecionada, "Descrição não disponível.");
                areaDescricaoClasse.setText(texto);

                StyledDocument doc = areaDescricaoClasse.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
        });

        botaoCriarPersonagem.addActionListener(e -> {
            String classeSelecionada = (String) comboBoxClasse.getSelectedItem();

            if (classeSelecionada == null || classeSelecionada.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, selecione uma classe para o personagem.",
                        "Classe Inválida", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean sucesso = meuJogo.iniciarNovaPartida(nomeConfirmado, classeSelecionada, idVisualEscolhido);

            if (sucesso) {
                System.out.println("Personagem criado com sucesso! Nome: " +
                        meuJogo.getJogador().getNome() + ", Classe: " + meuJogo.getJogador().getClasse());
                CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
                cl.show(painelPrincipalCardLayout, "TELA_NARRATIVA");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Ocorreu um erro ao criar o personagem. Verifique o console para mais detalhes.",
                        "Erro na Criação", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> {
            resetarPainel();
            CardLayout cl = (CardLayout) painelPrincipalCardLayout.getLayout();
            cl.show(painelPrincipalCardLayout, "MENU_INICIAL");
        });
    }

    private void resetarPainel() {
        campoNome.setText("");
        campoNome.setEnabled(true);
        botaoConfirmarNome.setEnabled(true);

        labelClasse.setEnabled(false);
        comboBoxClasse.setSelectedIndex(0);
        comboBoxClasse.setEnabled(false);
        areaDescricaoClasse.setText(descricoesClasses.get(""));
        areaDescricaoClasse.setEnabled(false);

        botaoCriarPersonagem.setEnabled(false);
        nomeConfirmado = null;
    }

    // ✅ Método público para definir idVisual
    public void setIdVisualEscolhido(int idVisual) {
        this.idVisualEscolhido = idVisual;
    }
}
