package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import Jogo.Jogo;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TelaClasse extends JPanel {
    private GerenciadorUI controlador;
    private Jogo meuJogo;
    private Image backgroundImage;

    private JTextArea areaDescricaoClasse;
    private JScrollPane scrollDescricao;
    private JButton botaoCriarPersonagem;
    private JButton botaoVoltar;

    private final Map<String, String> descricoesClasses = new HashMap<>();
    private String classeSelecionadaAtual = null;

    private static final String RASTREADOR = "Rastreador";
    private static final String MECANICO = "Mecânico";
    private static final String MEDICO = "Médico";
    private static final String SOBREVIVENTE_NATO = "Sobrevivente Nato";

    private Color corTextoDescricao = new Color(175, 144, 40);
    private Font fonteDescricao = new Font("Serif", Font.ITALIC, 16);
    private Font fonteBotoesNavegacao = new Font("SansSerif", Font.BOLD, 18); // Aumentei um pouco para o "Criar Personagem"
    private Color corTextoBotoesNav = new Color(230, 220, 200); // Usaremos esta para o texto do botão

    public TelaClasse(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) {
        this.controlador = ctrl;
        if (this.controlador != null) {
            this.meuJogo = controlador.getMeuJogo();
        } else {
            System.err.println("TelaClasse ERRO: Controlador é null!");
        }

        String nomeArquivoBackground = "escolhaClasse.png"; // << SEU ARQUIVO DE IMAGEM DE FUNDO

        try {
            URL imgUrl = getClass().getResource("/Resources/" + nomeArquivoBackground);
            if (imgUrl == null) imgUrl = getClass().getResource(nomeArquivoBackground);
            if (imgUrl != null) {
                this.backgroundImage = new ImageIcon(imgUrl).getImage();
            } else {
                System.err.println("ERRO GRAVE: Imagem de fundo '" + nomeArquivoBackground + "' NÃO ENCONTRADA!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        descricoesClasses.put(RASTREADOR, "Mestre em seguir trilhas e identificar perigos na natureza. Encontra recursos com mais facilidade.");
        descricoesClasses.put(MECANICO, "Engenhoso com ferramentas e sucata. Consegue consertar e criar dispositivos úteis para sobrevivência.");
        descricoesClasses.put(MEDICO, "Especialista em primeiros socorros e tratamento de ferimentos. Consegue usar ervas para criar remédios.");
        descricoesClasses.put(SOBREVIVENTE_NATO, "Resistente e adaptável. Possui habilidades instintivas para montar abrigos e fabricar itens básicos.");
        descricoesClasses.put("", "Selecione uma classe ao lado para ver sua descrição detalhada.");

        // --- Botões Transparentes para as Classes ---
        // << VOCÊ PRECISA MEDIR E AJUSTAR X, Y, LARGURA, ALTURA PARA CADA BOTÃO DE CLASSE >>
        criarBotaoClasse(RASTREADOR, 60, 160, 170, 170);
        criarBotaoClasse(MECANICO, 240, 160, 170, 170);
        criarBotaoClasse(MEDICO, 60, 340, 170, 170);
        criarBotaoClasse(SOBREVIVENTE_NATO, 240, 340, 170, 170);

        // --- Área de Descrição da Classe ---
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
        scrollDescricao.setBounds(455, 190, 285, 280); // << AJUSTE AQUI: Posição e Tamanho
        scrollDescricao.setBorder(BorderFactory.createEmptyBorder());
        add(scrollDescricao);

        // --- Botões de Navegação (Criar Personagem e Voltar) ---
        int painelPrefWidth = getPreferredSize().width;
        int painelPrefHeight = getPreferredSize().height;

        int alturaBotaoNav = 40;
        int larguraBotaoNavCriar = 240; // Um pouco mais largo para "Criar Personagem"
        int larguraBotaoNavVoltar = 120; // Menor para "Voltar"
        int margemInferiorNav = 25;
        int yBotoesNav = painelPrefHeight - alturaBotaoNav - margemInferiorNav;
        int margemLateralNav = 30;

        // Botão Criar Personagem (AGORA SÓ TEXTO)
        botaoCriarPersonagem = new JButton("Criar Personagem");
        botaoCriarPersonagem.setFont(fonteBotoesNavegacao);
        botaoCriarPersonagem.setForeground(corTextoBotoesNav); // Cor do texto
        botaoCriarPersonagem.setOpaque(false);                 // Não pinta o fundo
        botaoCriarPersonagem.setContentAreaFilled(false);      // Não preenche a área de conteúdo
        botaoCriarPersonagem.setBorderPainted(false);          // Sem borda
        botaoCriarPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Adiciona um padding para o texto não ficar colado se houver alguma interação visual de clique
        botaoCriarPersonagem.setBorder(new EmptyBorder(5,10,5,10));


        int xBotaoCriar = painelPrefWidth - larguraBotaoNavCriar - margemLateralNav;
        botaoCriarPersonagem.setBounds(xBotaoCriar, yBotoesNav, larguraBotaoNavCriar, alturaBotaoNav);
        add(botaoCriarPersonagem);

        // Botão Voltar (Mantém estilo sutil)
        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 16)); // Fonte um pouco diferente para diferenciar
        botaoVoltar.setOpaque(false);
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setForeground(corTextoBotoesNav);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltar.setBorder(new EmptyBorder(5,10,5,10));
        int xBotaoVoltar = margemLateralNav;
        botaoVoltar.setBounds(xBotaoVoltar, yBotoesNav, larguraBotaoNavVoltar, alturaBotaoNav);
        add(botaoVoltar);

        // Action Listeners
        botaoCriarPersonagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (classeSelecionadaAtual == null || classeSelecionadaAtual.isEmpty()) {
                    JOptionPane.showMessageDialog(TelaClasse.this, "Por favor, selecione uma classe.", "Classe Não Selecionada", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String nomePersonagem = controlador.getNomePersonagemAtual();
                if (nomePersonagem == null || nomePersonagem.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(TelaClasse.this, "Erro: Nome do personagem não definido.", "Erro Crítico", JOptionPane.ERROR_MESSAGE);
                    controlador.irParaTelaNome();
                    return;
                }
                if (meuJogo == null) {
                    JOptionPane.showMessageDialog(TelaClasse.this, "Erro interno: Instância do jogo não disponível.", "Erro Crítico", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean sucesso = meuJogo.iniciarNovaPartida(nomePersonagem, classeSelecionadaAtual);
                if (sucesso) {
                    String textoIntro = meuJogo.getTextoIntroducao();
                    JTextArea textAreaIntro = new JTextArea(textoIntro);
                    textAreaIntro.setWrapStyleWord(true);
                    textAreaIntro.setLineWrap(true);
                    textAreaIntro.setEditable(false);
                    textAreaIntro.setOpaque(false);
                    textAreaIntro.setBackground(new Color(0,0,0,0));
                    JScrollPane scrollPaneIntro = new JScrollPane(textAreaIntro);
                    scrollPaneIntro.setPreferredSize(new Dimension(500, 300));
                    scrollPaneIntro.setBorder(null);
                    JOptionPane.showMessageDialog(TelaClasse.this, scrollPaneIntro, "Introdução", JOptionPane.INFORMATION_MESSAGE, null);

                    controlador.irParaPainelJogo();
                } else {
                    JOptionPane.showMessageDialog(TelaClasse.this, "Ocorreu um erro ao iniciar a partida.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador != null && controlador.getIdPersonagemSelecionado() != -1) {
                    controlador.personagemSelecionadoParaDetalhes(controlador.getIdPersonagemSelecionado());
                } else if (controlador != null) {
                    controlador.irParaTelaPersonagem();
                }
            }
        });

        prepararTela();
    }

    private void criarBotaoClasse(final String nomeClasse, int x, int y, int largura, int altura) {
        JButton botao = new JButton();
        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBounds(x, y, largura, altura);

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classeSelecionadaAtual = nomeClasse;
                areaDescricaoClasse.setText(descricoesClasses.getOrDefault(nomeClasse, "Descrição não disponível."));
                areaDescricaoClasse.setCaretPosition(0);
                System.out.println("Classe selecionada: " + nomeClasse);
                // Aqui você pode adicionar um feedback visual para a classe selecionada,
                // por exemplo, mudando a borda de um painel ao redor da arte da classe, etc.
            }
        });
        add(botao);
    }

    public void prepararTela() {
        classeSelecionadaAtual = null;
        if (areaDescricaoClasse != null && descricoesClasses != null) {
            areaDescricaoClasse.setText(descricoesClasses.getOrDefault("", "Selecione uma classe ao lado para ver sua descrição detalhada."));
            areaDescricaoClasse.setCaretPosition(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            g2d.dispose();
        } else {
            g.setColor(new Color(50, 40, 30));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 16));
            g.drawString("Imagem de fundo (escolhaClasse.png) não carregada.", 50, getHeight() / 2);
        }
    }
}