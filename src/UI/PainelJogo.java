package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Jogo.Jogo;
import Personagem.Superclasse.Personagem;
import Personagem.Inventario.Inventario;
import Ambiente.Superclasse.Ambiente;
import Gerenciadores.GerenciadorDeEventos;
import Item.Superclasse.Item;
import Excecoes.InventarioCheioException;


public class PainelJogo extends JPanel {
    private GerenciadorUI controlador;
    private Jogo meuJogo;

    private JLabel labelStatusNivel;
    private JLabel labelStatusVida;
    private JLabel labelStatusFome;
    private JLabel labelStatusSede;
    private JLabel labelStatusEnergia;
    private JLabel labelStatusSanidade;

    private JPanel painelVisaoAmbiente;
    private Image backgroundImage;

    private JButton botaoMapa;
    private JButton botaoExplorar;
    private JButton botaoMudarAmbiente;
    private JButton botaoRealizarAcao;
    private JButton botaoVerInventario;
    private JButton botaoSairDoJogo;

    private JTextArea areaLog;
    private JScrollPane scrollLog;
    private JLabel imagemAmbiente;

    public PainelJogo(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) {
        this.controlador = ctrl;
        if (this.controlador != null) {
            if (this.controlador.getMeuJogo() != null) {
                this.meuJogo = this.controlador.getMeuJogo();
            } else {
                System.err.println("PainelJogo ERRO: GerenciadorUI não forneceu uma instância de Jogo!");
            }
        } else {
            System.err.println("PainelJogo ERRO: Controlador (GerenciadorUI) é null!");
        }

        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(40, 40, 45));

        JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        painelStatus.setOpaque(false);
        Font fonteStatus = new Font("SansSerif", Font.BOLD, 14);
        Color corStatus = new Color(210, 210, 210);

        labelStatusNivel = new JLabel("Nível: ...");
        labelStatusVida = new JLabel("Vida: ...");
        labelStatusFome = new JLabel("Fome: ...");
        labelStatusSede = new JLabel("Sede: ...");
        labelStatusEnergia = new JLabel("Energia: ...");
        labelStatusSanidade = new JLabel("Sanidade: ...");

        for (JLabel lbl : new JLabel[]{labelStatusNivel, labelStatusVida, labelStatusFome, labelStatusSede, labelStatusEnergia, labelStatusSanidade}) {
            lbl.setFont(fonteStatus);
            lbl.setForeground(corStatus);
            painelStatus.add(lbl);
        }
        add(painelStatus, BorderLayout.NORTH);

        painelVisaoAmbiente = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                Image imgAmbienteAtual = null;
                String nomeAmbienteAtualStr = "Desconhecido";
                if (meuJogo != null && meuJogo.getJogador() != null && meuJogo.getJogador().getAmbienteAtual() != null) {
                    Ambiente ambienteObj = meuJogo.getJogador().getAmbienteAtual();
                    imgAmbienteAtual = ambienteObj.getImagemAmbiente(meuJogo.getJogador());
                    nomeAmbienteAtualStr = ambienteObj.getNome();

                    if (imgAmbienteAtual != null) {
                        int painelW = getWidth();
                        int painelH = getHeight();
                        int imgW = imgAmbienteAtual.getWidth(null);
                        int imgH = imgAmbienteAtual.getHeight(null);

                        if (imgW <= 0 || imgH <= 0) {
                            g2d.setColor(new Color(20, 20, 20));
                            g2d.fillRect(0, 0, painelW, painelH);
                            g2d.setColor(Color.LIGHT_GRAY);
                            g2d.setFont(new Font("SansSerif", Font.ITALIC, 16));
                            String msgErroDim = "Arte do ambiente '" + nomeAmbienteAtualStr + "' com dimensões inválidas.";
                            FontMetrics fm = g2d.getFontMetrics();
                            int msgWidth = fm.stringWidth(msgErroDim);
                            g2d.drawString(msgErroDim, (painelW - msgWidth) / 2, painelH / 2);
                        } else {
                            double painelRatio = (double) painelW / painelH;
                            double imgRatio = (double) imgW / imgH;
                            int drawW = painelW;
                            int drawH = painelH;

                            if (imgRatio > painelRatio) {
                                drawH = (int) (painelW / imgRatio);
                            } else {
                                drawW = (int) (painelH * imgRatio);
                            }
                            int x = (painelW - drawW) / 2;
                            int y = (painelH - drawH) / 2;
                            g2d.drawImage(imgAmbienteAtual, x, y, drawW, drawH, this);
                        }
                    } else {
                        g2d.setColor(new Color(20, 20, 20));
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                        g2d.setColor(Color.LIGHT_GRAY);
                        g2d.setFont(new Font("SansSerif", Font.ITALIC, 16));
                        String msgErroLoad = "Arte do ambiente '" + nomeAmbienteAtualStr + "' não carregada.";
                        FontMetrics fm = g2d.getFontMetrics();
                        int msgWidth = fm.stringWidth(msgErroLoad);
                        g2d.drawString(msgErroLoad, (getWidth() - msgWidth) / 2, getHeight() / 2);
                    }
                } else {
                    g2d.setColor(new Color(25, 25, 25));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.setFont(new Font("SansSerif", Font.BOLD, 18));
                    String msgJogoNaoIniciado = "Jogo não iniciado ou ambiente não definido.";
                    FontMetrics fm = g2d.getFontMetrics();
                    int msgWidth = fm.stringWidth(msgJogoNaoIniciado);
                    g2d.drawString(msgJogoNaoIniciado, (getWidth() - msgWidth) / 2, getHeight() / 2);
                }
                g2d.dispose();
            }
        };
        painelVisaoAmbiente.setBackground(Color.BLACK);

        imagemAmbiente = new JLabel();
        imagemAmbiente.setHorizontalAlignment(SwingConstants.CENTER);
        imagemAmbiente.setVerticalAlignment(SwingConstants.CENTER);

        add(painelVisaoAmbiente, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout(0, 5));
        painelInferior.setOpaque(false);

        areaLog = new JTextArea(7, 0);
        areaLog.setEditable(false);
        areaLog.setLineWrap(true);
        areaLog.setWrapStyleWord(true);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaLog.setBackground(new Color(10, 10, 10, 220));
        areaLog.setForeground(new Color(190, 210, 190));
        areaLog.setMargin(new Insets(5, 8, 5, 8));
        DefaultCaret caret = (DefaultCaret)areaLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollLog = new JScrollPane(areaLog);
        scrollLog.setBorder(BorderFactory.createLineBorder(new Color(80,80,80)));
        painelInferior.add(scrollLog, BorderLayout.CENTER);

        JPanel painelBotoesAcao = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        painelBotoesAcao.setOpaque(false);

        botaoExplorar = new JButton("Explorar");
        botaoMudarAmbiente = new JButton("Mover");
        botaoRealizarAcao = new JButton("Ações");
        botaoVerInventario = new JButton("Inventário");
        botaoSairDoJogo = new JButton("Sair");
        botaoMapa = new JButton("Mapa");

        Font fonteBotaoAcao = new Font("SansSerif", Font.BOLD, 13);
        Dimension tamanhoBotaoAcao = new Dimension(130, 30);

        for (JButton btn : new JButton[]{botaoExplorar, botaoMudarAmbiente, botaoRealizarAcao, botaoVerInventario, botaoMapa, botaoSairDoJogo}) {
            btn.setFont(fonteBotaoAcao);
            btn.setPreferredSize(tamanhoBotaoAcao);
            btn.setBackground(new Color(80, 80, 100));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            painelBotoesAcao.add(btn);
        }
        painelInferior.add(painelBotoesAcao, BorderLayout.SOUTH);
        add(painelInferior, BorderLayout.SOUTH);

        configurarAcoes();
    }

    private void configurarAcoes() {
        botaoExplorar.addActionListener(e -> {
            if (meuJogo != null && meuJogo.getJogador() != null) {
                Personagem jogador = meuJogo.getJogador();
                Ambiente ambienteAtual = jogador.getAmbienteAtual();

                if (ambienteAtual != null) {
                    areaLog.append("\nVocê decide explorar " + ambienteAtual.getNome() + "...\n");

                    SwingUtilities.invokeLater(() -> {
                        try {
                            ambienteAtual.explorarInterface(jogador, areaLog);
                            atualizarTela();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Erro durante a exploração: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    });
                } else {
                    areaLog.append("Ambiente atual não definido para exploração.\n");
                }
            } else {
                areaLog.append("Jogador ou jogo não disponível.\n");
            }
        });

        botaoMudarAmbiente.addActionListener(e -> {
            new TelaMoverAmbientes((String nomeAmbienteEscolhido) -> {
                try {
                    meuJogo.mudarAmbienteViaInterface(nomeAmbienteEscolhido, areaLog, imagemAmbiente, this);

                    // Atualiza status do jogador
                    Personagem jogador = meuJogo.getJogador();
                    jogador.diminuirEnergia(10);
                    jogador.diminuirFome(5);
                    jogador.diminuirSede(5);

                    atualizarTela();

                    // Mensagem do novo ambiente
                    Ambiente ambienteAtual = jogador.getAmbienteAtual();
                    if (ambienteAtual != null) {
                        areaLog.setText("Você está em: " + ambienteAtual.getNome() + ".\n");
                        areaLog.append("------------------------------------------------------\n");
                        areaLog.append("Descrição: " + ambienteAtual.getDescricao() + "\n");
                        areaLog.append("Clima: " + ambienteAtual.getCondicaoClimatica() + "\n\n");
                    }

                    areaLog.append("Você se moveu e perdeu energia, fome e sede.\n");

                } catch (Exception ex) {
                    adicionarLog("Erro ao mudar de ambiente: " + ex.getMessage() + "\n");
                    JOptionPane.showMessageDialog(this,
                            "Erro ao mudar de ambiente:\n" + ex.getMessage(),
                            "Erro de Movimentação", JOptionPane.ERROR_MESSAGE);
                }
            });
        });


        botaoRealizarAcao.addActionListener(e -> {
            if (meuJogo != null && meuJogo.getJogador() != null) {
                String[] tipoDeAcao = {"Ação Comum", "Ação Especial"};
                String tipoEscolhido = (String) JOptionPane.showInputDialog(
                        this,
                        "Qual tipo de ação deseja realizar?",
                        "Escolha de Ação",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        tipoDeAcao,
                        tipoDeAcao[0]
                );

                if (tipoEscolhido == null) return;

                java.util.List<String> acoesDisponiveis;
                String tituloDialogo;

                if (tipoEscolhido.equals("Ação Comum")) {
                    acoesDisponiveis = meuJogo.getAcoesComunsDisponiveis(meuJogo.getJogador());
                    tituloDialogo = "Ação Comum";
                } else {
                    acoesDisponiveis = meuJogo.getAcoesEspeciaisDisponiveis(meuJogo.getJogador());
                    tituloDialogo = "Ação Especial";
                }

                if (acoesDisponiveis.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nenhuma " + tipoEscolhido.toLowerCase() + " disponível no momento.", "Sem Ações", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String acaoEscolhida = (String) JOptionPane.showInputDialog(
                        this,
                        "Escolha uma " + tipoEscolhido.toLowerCase() + ":",
                        tituloDialogo,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        acoesDisponiveis.toArray(new String[0]),
                        acoesDisponiveis.get(0)
                );

                if (acaoEscolhida != null) {
                    if (tipoEscolhido.equals("Ação Comum")) {
                        meuJogo.executarAcaoComumInterface(acaoEscolhida, areaLog);
                    } else {
                        meuJogo.executarAcaoEspecialInterface(acaoEscolhida, areaLog);
                    }
                    atualizarTela();
                }
            } else {
                if (areaLog != null) areaLog.append("Jogador ou Jogo não definido para realizar ação.\n");
            }
        });

        botaoVerInventario.addActionListener(e -> {
            if (meuJogo != null && meuJogo.getJogador() != null) {
                Inventario inv = meuJogo.getJogador().getInventario();
                Personagem jogador = meuJogo.getJogador();
                if (inv != null) {
                    PainelInventario painel = new PainelInventario(inv, jogador);
                    JDialog janela = new JDialog(SwingUtilities.getWindowAncestor(this), "Inventário", Dialog.ModalityType.APPLICATION_MODAL);
                    janela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    janela.setContentPane(painel);
                    janela.pack();
                    janela.setLocationRelativeTo(this);
                    janela.setVisible(true);
                    atualizarTela();
                } else {
                    adicionarLog("Inventário não disponível.");
                }
            } else {
                if (areaLog != null) areaLog.append("Jogador ou Jogo não definido para ver inventário.\n");
            }
        });

        botaoSairDoJogo.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja sair do jogo?", "Sair do Jogo",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        botaoMapa.addActionListener(e -> mostrarMapa());
    }

    public void adicionarLog(String mensagem) {
        if (areaLog != null) {
            areaLog.append(mensagem + "\n");
        } else {
            System.out.println("LOG (UI - areaLog nula): " + mensagem);
        }
    }

    public JTextArea getTextAreaLog() {
        return areaLog;
    }

    public void atualizarTela() {
        if (meuJogo == null || meuJogo.getJogador() == null) {
            labelStatusNivel.setText("Nível: N/A");
            labelStatusVida.setText("Vida: N/A");
            labelStatusFome.setText("Fome: N/A");
            labelStatusSede.setText("Sede: N/A");
            labelStatusEnergia.setText("Energia: N/A");
            labelStatusSanidade.setText("Sanidade: N/A");

            if (painelVisaoAmbiente != null) painelVisaoAmbiente.repaint();
            return;
        }

        Personagem jogador = meuJogo.getJogador();
        labelStatusNivel.setText("Nível: " + jogador.getNivel());
        labelStatusVida.setText("Vida: " + jogador.getVida());
        labelStatusFome.setText("Fome: " + jogador.getFome());
        labelStatusSede.setText("Sede: " + jogador.getSede());
        labelStatusEnergia.setText("Energia: " + jogador.getEnergia());
        labelStatusSanidade.setText("Sanidade: " + jogador.getSanidade());

        if (painelVisaoAmbiente != null) painelVisaoAmbiente.repaint();

        this.requestFocusInWindow();
    }

    public void aoEntrarNaTela() {
        if (meuJogo != null && meuJogo.getJogador() != null && meuJogo.getJogador().getAmbienteAtual() != null) {
            Ambiente ambienteAtual = meuJogo.getJogador().getAmbienteAtual();
            if (areaLog != null) {
                areaLog.setText("Você está em: " + ambienteAtual.getNome() + ".\n");
                areaLog.append("------------------------------------------------------\n");
                areaLog.append(ambienteAtual.getDescricao() + "\n");
                areaLog.append("Clima: " + ambienteAtual.getCondicaoClimatica() + "\n\n");
            }
            atualizarTela();
        } else {
            if (areaLog != null) {
                areaLog.setText("ERRO CRÍTICO AO CARREGAR O JOGO! PERSONAGEM OU AMBIENTE NULO.");
            }
            labelStatusNivel.setText("Nível: ERRO");
            labelStatusVida.setText("Vida: ERRO");
            labelStatusFome.setText("Fome: ERRO");
            labelStatusSede.setText("Sede: ERRO");
            labelStatusEnergia.setText("Energia: ERRO");
            labelStatusSanidade.setText("Sanidade: ERRO");
        }
    }

    private void mostrarMapa() {
        // 1) Carrega o BufferedImage do mapa (final para uso na classe anônima abaixo)
        final BufferedImage imagemOriginal;
        try {
            java.net.URL imgUrl = getClass().getClassLoader().getResource("Resources/mapa2.png");
            if (imgUrl == null) {
                throw new IOException("Arquivo 'Resources/mapa2.png' não foi encontrado no classpath.");
            }
            imagemOriginal = ImageIO.read(imgUrl);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar o mapa:\n" + ex.getMessage(),
                    "Erro de Mapa",
                    JOptionPane.ERROR_MESSAGE
            );
            // Se houver falha ao ler a imagem, interrompe a execução do método
            return;
        }

        // 2) Obtém a resolução total do monitor
        Dimension resolucaoTela = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraTela = resolucaoTela.width;
        int alturaTela = resolucaoTela.height;

        // 3) Cria um JDialog sem bordas que ocupará 100% da tela
        JDialog dialogoMapa = new JDialog(SwingUtilities.getWindowAncestor(this));
        dialogoMapa.setModal(true);
        dialogoMapa.setUndecorated(true);
        dialogoMapa.setBounds(0, 0, larguraTela, alturaTela);

        // 4) Cria um JPanel que desenha o mapa redimensionado e centralizado
        JPanel painelMapa = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (imagemOriginal != null) {
                    int painelW = getWidth();
                    int painelH = getHeight();
                    int imgW = imagemOriginal.getWidth();
                    int imgH = imagemOriginal.getHeight();

                    double painelRatio = (double) painelW / painelH;
                    double imgRatio   = (double) imgW / imgH;

                    int drawW = painelW;
                    int drawH = painelH;
                    if (imgRatio > painelRatio) {
                        drawH = (int) (painelW / imgRatio);
                    } else {
                        drawW = (int) (painelH * imgRatio);
                    }

                    int x = (painelW - drawW) / 2;
                    int y = (painelH - drawH) / 2;
                    g.drawImage(imagemOriginal, x, y, drawW, drawH, this);
                }
            }
        };

        // 5) Garante que o fundo do painel seja preto (para não aparecer nenhum branco)
        painelMapa.setBackground(Color.BLACK);

        // 6) Remove totalmente qualquer borda: NÃO há setBorder(…) aqui

        // 7) Fecha o diálogo ao clicar em qualquer ponto da tela
        painelMapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialogoMapa.dispose();
            }
        });

        // 8) Ajusta o painel para ocupar toda a área do diálogo
        painelMapa.setPreferredSize(new Dimension(larguraTela, alturaTela));
        dialogoMapa.setContentPane(painelMapa);
        dialogoMapa.validate();

        // 9) Exibe o diálogo em tela cheia
        dialogoMapa.setVisible(true);
    }

    public void atualizarImagemAmbiente() {
        if (meuJogo == null || meuJogo.getJogador() == null) {
            System.err.println("Erro: Jogo ou jogador não inicializado.");
            return;
        }

        Ambiente ambiente = meuJogo.getJogador().getAmbienteAtual();
        if (ambiente == null) {
            System.err.println("Erro: Ambiente atual não definido.");
            return;
        }

        // Usa o método definido em cada ambiente (ex: "floresta", "caverna", etc.)
        String tipoAmbiente = ambiente.getTipoImagem();
        int idVisual = meuJogo.getJogador().getIdVisual();
        if (idVisual < 1 || idVisual > 6) {
            System.out.println("ID visual inválido: " + idVisual + " — usando 1 como padrão.");
            idVisual = 1;
        }
        // Segurança para evitar id fora do intervalo
        if (idVisual < 1 || idVisual > 6) {
            System.out.println("Aviso: ID visual inválido (" + idVisual + "), usando 1.");
            idVisual = 1;
        }

        // Caminho da imagem correspondente
        String caminhoImagem = "/Resources/ambientes/" + tipoAmbiente + idVisual + ".png";

        try {
            backgroundImage = new ImageIcon(getClass().getResource(caminhoImagem)).getImage();
            System.out.println("Imagem carregada com sucesso: " + caminhoImagem);
        } catch (Exception e) {
            backgroundImage = null;
            System.err.println("Erro ao carregar imagem: " + caminhoImagem);
            e.printStackTrace();
        }

        // Reforça a atualização visual do painel
        repaint();
    }


    public void setJogador(Personagem jogador) {
        this.meuJogo.setJogador(jogador); // ou this.jogador = jogador;
    }

}