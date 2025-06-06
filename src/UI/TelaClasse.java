package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import Jogo.Jogo;

public class TelaClasse extends JPanel {
    private GerenciadorUI controlador;
    private Jogo meuJogo;
    private Image backgroundImageTelaClasse;
    private int idVisualSelecionado;

    private JTextArea areaDescricaoClasse;
    private JScrollPane scrollDescricao;
    private JButton botaoCriarPersonagem;
    private JButton botaoVoltar;

    private final Map<String, String> descricoesClasses = new HashMap<>();
    private String classeSelecionadaAtual = null;

    private final java.util.List<JButton> botoesClasse = new java.util.ArrayList<>();

    private static final String RASTREADOR = "Rastreador";
    private static final String MECANICO = "Mecânico";
    private static final String MEDICO = "Médico";
    private static final String SOBREVIVENTE_NATO = "Sobrevivente Nato";

    private Color corTextoDescricao = new Color(175, 144, 40);
    private Font fonteDescricao = new Font("Serif", Font.ITALIC, 21);
    private Font fonteBotoesNavegacao = new Font("SansSerif", Font.BOLD, 18);
    private Color corTextoBotoesNav = new Color(230, 220, 200);

    public TelaClasse(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) {
        this.controlador = ctrl;
        if (controlador != null) {
            this.meuJogo = controlador.getMeuJogo();
        }

        try {
            URL imgUrl = getClass().getResource("/Resources/escolhaClasse.png");
            if (imgUrl == null) imgUrl = getClass().getResource("escolhaClasse.png");
            if (imgUrl != null) {
                backgroundImageTelaClasse = new ImageIcon(imgUrl).getImage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        descricoesClasses.put(RASTREADOR, "Mestre em seguir trilhas e identificar perigos na natureza. Encontra recursos com mais facilidade.");
        descricoesClasses.put(MECANICO, "Engenhoso com ferramentas e sucata. Consegue consertar e criar dispositivos úteis para sobrevivência.");
        descricoesClasses.put(MEDICO, "Especialista em primeiros socorros e tratamento de ferimentos. Consegue usar ervas para criar remédios.");
        descricoesClasses.put(SOBREVIVENTE_NATO, "Resistente e adaptável. Possui habilidades instintivas para montar abrigos e fabricar itens básicos.");
        descricoesClasses.put("", "Selecione uma classe ao lado para ver sua descrição detalhada.");

        criarBotaoClasse(RASTREADOR, 160, 160, 170, 170);
        criarBotaoClasse(MECANICO, 470, 160, 170, 170);
        criarBotaoClasse(MEDICO, 150, 420, 170, 170);
        criarBotaoClasse(SOBREVIVENTE_NATO, 470, 420, 170, 170);

        areaDescricaoClasse = new JTextArea(descricoesClasses.get(""));
        areaDescricaoClasse.setFont(fonteDescricao);
        areaDescricaoClasse.setForeground(corTextoDescricao);
        areaDescricaoClasse.setWrapStyleWord(true);
        areaDescricaoClasse.setLineWrap(true);
        areaDescricaoClasse.setEditable(false);
        areaDescricaoClasse.setOpaque(false);
        areaDescricaoClasse.setMargin(new Insets(15, 20, 15, 20));

        scrollDescricao = new JScrollPane(areaDescricaoClasse);
        scrollDescricao.setOpaque(false);
        scrollDescricao.getViewport().setOpaque(false);
        scrollDescricao.setBorder(BorderFactory.createEmptyBorder());
        scrollDescricao.setBounds(850, 240, 300, 300);
        add(scrollDescricao);

        botaoCriarPersonagem = new JButton("Criar Personagem");
        botaoCriarPersonagem.setFont(fonteBotoesNavegacao);
        botaoCriarPersonagem.setForeground(corTextoBotoesNav);
        botaoCriarPersonagem.setOpaque(false);
        botaoCriarPersonagem.setContentAreaFilled(false);
        botaoCriarPersonagem.setBorderPainted(false);
        botaoCriarPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoCriarPersonagem.setBorder(new EmptyBorder(5, 10, 5, 10));
        botaoCriarPersonagem.setBounds(1070, 670, 240, 40);
        add(botaoCriarPersonagem);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 16));
        botaoVoltar.setOpaque(false);
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setForeground(corTextoBotoesNav);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltar.setBorder(new EmptyBorder(5, 10, 5, 10));
        botaoVoltar.setBounds(19, 670, 120, 40);
        add(botaoVoltar);

        botaoCriarPersonagem.addActionListener(e -> {
            if (classeSelecionadaAtual == null || classeSelecionadaAtual.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma classe.");
                return;
            }

            String nome = controlador.getNomePersonagemAtual();
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome do personagem não definido.");
                controlador.irParaTelaNome();
                return;
            }

            // Garante que o idVisualSelecionado esteja atualizado corretamente
            this.idVisualSelecionado = controlador.getIdVisualEscolhido();
            if (idVisualSelecionado < 1 || idVisualSelecionado > 6) {
                JOptionPane.showMessageDialog(this, "Erro: ID visual inválido.");
                return;
            }

            // Inicia o jogo com o valor certo de visual
            if (meuJogo == null || !meuJogo.iniciarNovaPartida(nome, classeSelecionadaAtual, idVisualSelecionado)) {
                JOptionPane.showMessageDialog(this, "Erro ao iniciar o jogo.");
                return;
            }

            controlador.irParaTelaNarrativa();
        });



        botaoVoltar.addActionListener(e -> {
            if (controlador != null && controlador.getIdPersonagemSelecionado() != -1) {
                controlador.personagemSelecionadoParaDetalhes(controlador.getIdPersonagemSelecionado());
            } else if (controlador != null) {
                controlador.irParaTelaPersonagem();
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Clique em: X=" + e.getX() + " Y=" + e.getY());
                }
            }
        });

        prepararTela(controlador.getIdVisualEscolhido());
    }


    private void criarBotaoClasse(final String nomeClasse, int x, int y, int largura, int altura) {
        JButton botao = new JButton();
        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBounds(x, y, largura, altura);
        botao.addActionListener(e -> {
            classeSelecionadaAtual = nomeClasse;
            areaDescricaoClasse.setText(descricoesClasses.getOrDefault(nomeClasse, "Descrição não disponível."));
            areaDescricaoClasse.setCaretPosition(0);
        });
        botoesClasse.add(botao);
        add(botao);
    }

    public void prepararTela(int idVisual) {
        this.idVisualSelecionado = idVisual;
        classeSelecionadaAtual = null;
        areaDescricaoClasse.setText(descricoesClasses.get(""));
        areaDescricaoClasse.setCaretPosition(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImageTelaClasse != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(backgroundImageTelaClasse, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
