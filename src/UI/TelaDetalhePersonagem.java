package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TelaDetalhePersonagem extends JPanel {
    private GerenciadorUI controlador; // << AJUSTADO para GerenciadorUI
    private Image artePersonagemImg;
    private Image backgroundImage;
    private int personagemIdAtual;

    private JLabel nomePersonagemLabel;
    private JTextArea descricaoPersonagemArea;
    private JLabel arteLabel; // Usaremos JLabel para exibir a arte do personagem

    private final int LARGURA_MAX_ARTE_DETALHE = 380;
    private final int ALTURA_MAX_ARTE_DETALHE = 480;

    private Color corTextoBegeClaro = new Color(220, 210, 200);
    private Color corNomePersonagem = new Color(230, 200, 180);
    private Font fonteBotaoRodape = new Font("SansSerif", Font.BOLD, 16);

    public TelaDetalhePersonagem(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) { // << AJUSTADO para GerenciadorUI
        this.controlador = ctrl;

        String nomeArquivoBgTelaDetalhe = "backDescricao.png"; // << SEU ARQUIVO DE IMAGEM DE FUNDO
        try {
            URL imgUrlBg = getClass().getResource("/Resources/" + nomeArquivoBgTelaDetalhe);
            if (imgUrlBg == null) imgUrlBg = getClass().getResource(nomeArquivoBgTelaDetalhe);
            if (imgUrlBg != null) this.backgroundImage = new ImageIcon(imgUrlBg).getImage();
            else System.err.println("AVISO: Imagem de fundo da TelaDetalhePersonagem '" + nomeArquivoBgTelaDetalhe + "' não encontrada.");
        } catch (Exception e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }

        setLayout(new BorderLayout(15, 15));
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 20));

        arteLabel = new JLabel(); // JLabel para a arte específica do personagem
        arteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        arteLabel.setVerticalAlignment(SwingConstants.CENTER);
        arteLabel.setPreferredSize(new Dimension(LARGURA_MAX_ARTE_DETALHE, ALTURA_MAX_ARTE_DETALHE));
        add(arteLabel, BorderLayout.CENTER);

        JPanel painelDireita = new JPanel(new BorderLayout(10, 10));
        painelDireita.setOpaque(false);
        painelDireita.setPreferredSize(new Dimension(340, 0));
        add(painelDireita, BorderLayout.EAST);

        nomePersonagemLabel = new JLabel("Nome do Jogador", SwingConstants.CENTER);
        nomePersonagemLabel.setFont(new Font("Serif", Font.BOLD, 28));
        nomePersonagemLabel.setForeground(corNomePersonagem);
        nomePersonagemLabel.setBorder(new EmptyBorder(10, 0, 15, 0));
        painelDireita.add(nomePersonagemLabel, BorderLayout.NORTH);

        descricaoPersonagemArea = new JTextArea("Carregando descrição...");
        descricaoPersonagemArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        descricaoPersonagemArea.setWrapStyleWord(true);
        descricaoPersonagemArea.setLineWrap(true);
        descricaoPersonagemArea.setEditable(false);
        descricaoPersonagemArea.setOpaque(false);
        descricaoPersonagemArea.setForeground(corTextoBegeClaro);
        JScrollPane scrollDescricao = new JScrollPane(descricaoPersonagemArea);
        scrollDescricao.setOpaque(false);
        scrollDescricao.getViewport().setOpaque(false);
        scrollDescricao.setBorder(BorderFactory.createEmptyBorder());
        painelDireita.add(scrollDescricao, BorderLayout.CENTER);

        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.setOpaque(false);
        painelRodape.setBorder(new EmptyBorder(15, 0, 5, 0));
        add(painelRodape, BorderLayout.SOUTH);

        JButton botaoVoltar = new JButton("Escolher Outro"); // Texto pode ser "Voltar" também
        botaoVoltar.setFont(fonteBotaoRodape);
        botaoVoltar.setOpaque(false);
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setForeground(corTextoBegeClaro);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltar.setBorder(new EmptyBorder(5,10,5,10));
        botaoVoltar.addActionListener(e -> controlador.irParaTelaPersonagem()); // Chamada correta
        JPanel painelBotaoVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
        painelBotaoVoltar.setOpaque(false);
        painelBotaoVoltar.add(botaoVoltar);
        painelRodape.add(painelBotaoVoltar, BorderLayout.WEST);

        JButton botaoConfirmarPersonagem = new JButton("Confirmar Personagem");
        botaoConfirmarPersonagem.setFont(fonteBotaoRodape);
        botaoConfirmarPersonagem.addActionListener(e -> controlador.personagemConfirmado(personagemIdAtual));
        JPanel painelBotaoConfirmar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0,0));
        painelBotaoConfirmar.setOpaque(false);
        painelBotaoConfirmar.add(botaoConfirmarPersonagem);
        painelRodape.add(painelBotaoConfirmar, BorderLayout.EAST);
    }

    public void mostrarDetalhes(int personagemId) {
        this.personagemIdAtual = personagemId;

        // << PREENCHA COM SEUS DADOS REAIS >>
        String[] artesEspecificasFiles = {
                "p1.png", "p2.png", "p3.png", "p4.png", "p5.png", "p6.png" // Nomes dos seus arquivos de arte
        };
        String[] nomesArquetipos = { // Nomes dos TIPOS de personagem
                "Arquétipo 1", "Arquétipo 2", "Arquétipo 3", "Arquétipo 4", "Arquétipo 5", "Arquétipo 6"
        };
        String[] descricoesArquetipos = {
                "Descrição do Arquétipo 1...", "Descrição do Arquétipo 2...", // etc.
        };

        String nomeDoJogadorEscolhido = controlador.getNomePersonagemAtual();

        if (personagemId >= 0 && personagemId < artesEspecificasFiles.length) {
            String nomeArquivoArteEsp = artesEspecificasFiles[personagemId];
            try {
                URL imgUrlEsp = getClass().getResource("/Resources/" + nomeArquivoArteEsp);
                if (imgUrlEsp == null) imgUrlEsp = getClass().getResource(nomeArquivoArteEsp);

                if (imgUrlEsp != null) {
                    ImageIcon originalIcon = new ImageIcon(imgUrlEsp);
                    Image img = originalIcon.getImage();

                    int originalWidth = img.getWidth(null);
                    int originalHeight = img.getHeight(null);
                    int newWidth = originalWidth;
                    int newHeight = originalHeight;

                    if (originalWidth > LARGURA_MAX_ARTE_DETALHE) {
                        newWidth = LARGURA_MAX_ARTE_DETALHE;
                        newHeight = (newWidth * originalHeight) / originalWidth;
                    }
                    if (newHeight > ALTURA_MAX_ARTE_DETALHE) {
                        newHeight = ALTURA_MAX_ARTE_DETALHE;
                        newWidth = (newHeight * originalWidth) / originalHeight;
                    }

                    if (newWidth <= 0) newWidth = 1; // Evita erro
                    if (newHeight <= 0) newHeight = 1; // Evita erro

                    artePersonagemImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    arteLabel.setIcon(new ImageIcon(artePersonagemImg));
                    arteLabel.setText(null);
                } else {
                    arteLabel.setIcon(null);
                    arteLabel.setText("Arte de '" + (personagemId < nomesArquetipos.length ? nomesArquetipos[personagemId] : "") + "' não encontrada.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                arteLabel.setIcon(null);
                arteLabel.setText("Erro ao carregar arte.");
            }

            if (nomeDoJogadorEscolhido != null && !nomeDoJogadorEscolhido.trim().isEmpty()) {
                nomePersonagemLabel.setText(nomeDoJogadorEscolhido);
            } else {
                nomePersonagemLabel.setText(personagemId < nomesArquetipos.length ? nomesArquetipos[personagemId] : "Personagem");
            }

            descricaoPersonagemArea.setText(personagemId < descricoesArquetipos.length ? descricoesArquetipos[personagemId] : "Descrição não disponível.");
            descricaoPersonagemArea.setCaretPosition(0);
        } else {
            arteLabel.setIcon(null);
            arteLabel.setText("Personagem inválido.");
            nomePersonagemLabel.setText("Desconhecido");
            descricaoPersonagemArea.setText("Sem informações.");
        }
        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            g2d.dispose();
        }
    }
}