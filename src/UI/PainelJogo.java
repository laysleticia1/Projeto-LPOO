package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Jogo.Jogo;
import Personagem.Superclasse.Personagem;
import Personagem.Inventario.Inventario;
import Ambiente.Superclasse.Ambiente;
import Gerenciadores.GerenciadorDeEventos;

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
                    imgAmbienteAtual = ambienteObj.getImagemAmbiente();
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

        areaLog = new JTextArea(5, 0);
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
            if (meuJogo != null && meuJogo.getJogador() != null && meuJogo.getJogador().getAmbienteAtual() != null) {
                Personagem jogador = meuJogo.getJogador();
                Ambiente ambienteAtual = jogador.getAmbienteAtual();

                adicionarLog("Você explora " + ambienteAtual.getNome() + "...");

                GerenciadorDeEventos gerenciadorEventos = null;
                if (meuJogo.getGerenciadorDeEventos() != null) {
                    gerenciadorEventos = meuJogo.getGerenciadorDeEventos();
                } else if (controlador != null && controlador.getGerenciadorDeEventos() != null) {
                    gerenciadorEventos = controlador.getGerenciadorDeEventos();
                }

                if (gerenciadorEventos != null) {
                    gerenciadorEventos.dispararEventoExploracaoInterface(jogador, areaLog);
                } else {
                    areaLog.append("Sistema de eventos (UI) não pôde ser acionado (gerenciador não encontrado).\n");
                }

                atualizarTela();
            } else {
                if (areaLog != null) {
                    areaLog.append("Não é possível explorar agora (jogador, jogo ou ambiente atual não definido).\n");
                }
            }
        });

        botaoMudarAmbiente.addActionListener(e -> {
            new TelaMoverAmbientes((String nomeAmbienteEscolhido) -> {
                try {
                    meuJogo.mudarAmbienteViaInterface(nomeAmbienteEscolhido, areaLog, imagemAmbiente);

                    meuJogo.getJogador().diminuirEnergia(10);
                    meuJogo.getJogador().diminuirFome(5);
                    meuJogo.getJogador().diminuirSede(5);

                    atualizarTela();

                    // Mensagem completa do novo ambiente
                    Ambiente ambienteAtual = meuJogo.getJogador().getAmbienteAtual();
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
        labelStatusVida.setText("Vida: " + jogador.getVida() + "/" + jogador.getVidaMaxima());
        labelStatusFome.setText("Fome: " + jogador.getFome() + "/" + jogador.getFomeMaxima());
        labelStatusSede.setText("Sede: " + jogador.getSede() + "/" + jogador.getSedeMaxima());
        labelStatusEnergia.setText("Energia: " + jogador.getEnergia() + "/" + jogador.getEnergiaMaxima());
        labelStatusSanidade.setText("Sanidade: " + jogador.getSanidade() + "/" + jogador.getSanidadeMaxima());

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
        JDialog dialogoMapa = new JDialog(SwingUtilities.getWindowAncestor(this), "Mapa de Velkaria", Dialog.ModalityType.APPLICATION_MODAL);
        dialogoMapa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogoMapa.setSize(1000, 700);
        dialogoMapa.setLocationRelativeTo(this);

        try {
            java.net.URL imgUrl = getClass().getClassLoader().getResource("Resources/mapa2.png");
            if (imgUrl == null) {
                throw new IOException("Arquivo de mapa 'Resources/mapa2.png' não encontrado no classpath.");
            }
            BufferedImage imagemOriginal = ImageIO.read(imgUrl);

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
                        g.drawImage(imagemOriginal, x, y, drawW, drawH, this);
                    }
                }
            };
            if (imagemOriginal != null) {
                painelMapa.setPreferredSize(new Dimension(imagemOriginal.getWidth(), imagemOriginal.getHeight()));
            } else {
                painelMapa.setPreferredSize(new Dimension(800, 600));
            }

            dialogoMapa.setContentPane(new JScrollPane(painelMapa));
            dialogoMapa.pack();
            if (dialogoMapa.getWidth() > 1000 || dialogoMapa.getHeight() > 700) {
                dialogoMapa.setSize(Math.min(dialogoMapa.getWidth(), 1000), Math.min(dialogoMapa.getHeight(), 700));
            }
            dialogoMapa.setLocationRelativeTo(this);
            dialogoMapa.setVisible(true);

        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar o mapa: " + ex.getMessage(), "Erro de Mapa", JOptionPane.ERROR_MESSAGE);
        }
    }
}