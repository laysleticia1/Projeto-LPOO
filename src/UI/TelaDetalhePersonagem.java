package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.awt.Graphics2D; // Adicionado para consistência, embora já usado em paintComponent
import java.awt.RenderingHints; // Adicionado para consistência

public class TelaDetalhePersonagem extends JPanel {
    private GerenciadorUI controlador;
    private Image artePersonagemImg; // Para a arte específica do personagem selecionado
    private Image backgroundImage;   // Para a imagem de fundo DESTA TELA
    private int personagemIdAtual;

    private JLabel nomePersonagemLabel;
    private JTextArea descricaoPersonagemArea;
    private JLabel arteLabel;

    private final int LARGURA_MAX_ARTE_DETALHE = 380;
    private final int ALTURA_MAX_ARTE_DETALHE = 480;

    // Cores e Fontes
    private Color corTextoBegeClaro = new Color(220, 210, 200); // Para textos gerais
    private Color corNomePersonagem = new Color(230, 200, 180); // Destaque para o nome
    private Font fonteBotaoRodape = new Font("SansSerif", Font.BOLD, 16);


    public TelaDetalhePersonagem(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) {
        this.controlador = ctrl;

        // << COLOQUE AQUI O NOME DO SEU ARQUIVO DE IMAGEM DE FUNDO PARA ESTA TELA >>
        String nomeArquivoBgTelaDetalhe = "backDescricao.png"; // Exemplo

        try {
            URL imgUrlBg = getClass().getResource("/Resources/" + nomeArquivoBgTelaDetalhe);
            if (imgUrlBg == null) imgUrlBg = getClass().getResource(nomeArquivoBgTelaDetalhe);

            if (imgUrlBg != null) {
                this.backgroundImage = new ImageIcon(imgUrlBg).getImage();
            } else {
                System.err.println("AVISO: Imagem de fundo da TelaDetalhePersonagem '" + nomeArquivoBgTelaDetalhe + "' não encontrada.");
                this.backgroundImage = null; // Continuará sem imagem de fundo se não encontrar
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }

        setLayout(new BorderLayout(15, 15));
        setPreferredSize(new Dimension(800, 600));
        // A cor de fundo sólida só será visível se a imagem de fundo não carregar
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 20)); // Ajuste de margens

        // --- Painel da Arte do Personagem (Centro) ---
        arteLabel = new JLabel();
        arteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        arteLabel.setVerticalAlignment(SwingConstants.CENTER);
        // É importante que o arteLabel seja transparente se a arte do personagem tiver fundo transparente
        // e você quiser que o backgroundImage da tela apareça. JLabels são opacos por padrão.
        // Se a arte do personagem preencher todo o espaço, não precisa se preocupar.
        // arteLabel.setOpaque(false); // Descomente se necessário
        add(arteLabel, BorderLayout.CENTER);

        // --- Painel de Informações (Direita) ---
        JPanel painelDireita = new JPanel(new BorderLayout(10, 10));
        painelDireita.setOpaque(false); // Para que o backgroundImage da tela apareça
        painelDireita.setPreferredSize(new Dimension(340, 0)); // Largura ajustada

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
        add(painelDireita, BorderLayout.EAST);

        // --- Painel do Rodapé para Botões de Navegação ---
        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.setOpaque(false);
        painelRodape.setBorder(new EmptyBorder(15, 0, 5, 0)); // Margem acima e abaixo do rodapé

        // Botão Voltar (Rodapé Esquerdo)
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(fonteBotaoRodape);
        botaoVoltar.setOpaque(false);
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setForeground(corTextoBegeClaro);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltar.setBorder(new EmptyBorder(5,10,5,10));
        botaoVoltar.addActionListener(e -> controlador.irParaTelaPersonagem());
        JPanel painelBotaoVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
        painelBotaoVoltar.setOpaque(false);
        painelBotaoVoltar.add(botaoVoltar);
        painelRodape.add(painelBotaoVoltar, BorderLayout.WEST);

        // Botão Confirmar Personagem (Rodapé Direito)
        JButton botaoConfirmarPersonagem = new JButton("Confirmar Personagem");
        botaoConfirmarPersonagem.setFont(fonteBotaoRodape);
        // Estilo para o botão confirmar (pode ser diferente do voltar)
        // botaoConfirmarPersonagem.setBackground(new Color(70, 90, 120));
        // botaoConfirmarPersonagem.setForeground(Color.WHITE);
        botaoConfirmarPersonagem.addActionListener(e -> controlador.personagemConfirmado(personagemIdAtual));
        JPanel painelBotaoConfirmar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0,0));
        painelBotaoConfirmar.setOpaque(false);
        painelBotaoConfirmar.add(botaoConfirmarPersonagem);
        painelRodape.add(painelBotaoConfirmar, BorderLayout.EAST);

        add(painelRodape, BorderLayout.SOUTH);
    }

    public void mostrarDetalhes(int personagemId) {
        this.personagemIdAtual = personagemId;

        // << VOCÊ PRECISA PREENCHER ESTES ARRAYS COM OS DADOS REAIS >>
        String[] artesEspecificasFiles = {
                "p1.png", "p2.png", "p3.png",
                "p4.png", "p5.png", "p6.png"
        };
        String[] nomesArquetipos = {
                "Guerreiro das Montanhas", "Feiticeira da Floresta", "Rastreador Silencioso",
                "Engenheira de Sucata", "Sábio do Lago", "Exploradora das Ruínas"
        };
        String[] descricoesArquetipos = {
                "Vindo das cordilheiras implacáveis...", "Envolta nos mistérios da floresta...",
                "Movendo-se como uma sombra...", "Com uma mente brilhante...",
                "Das profundezas tranquilas...", "Destemida e curiosa..."
                // << COLOQUE AS DESCRIÇÕES COMPLETAS AQUI >>
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
        // Desenha a imagem de fundo da TELA
        if (this.backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            g2d.dispose();
        } else {
            // Fallback se a imagem de fundo da TELA não carregar
            // A cor de fundo definida no setBackground() do construtor será usada.
        }
    }
}