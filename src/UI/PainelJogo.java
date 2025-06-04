package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import javax.imageio.ImageIO;
// Removido import java.io.File se não usado diretamente
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Jogo.Jogo;
import Personagem.Superclasse.Personagem;
// Removido Personagem.Subclasses.* se não usado diretamente aqui
import Personagem.Inventario.Inventario;
import Ambiente.Superclasse.Ambiente;
import Gerenciadores.GerenciadorDeEventos; // Import necessário

public class PainelJogo extends JPanel {
    private GerenciadorUI controlador;
    private Jogo meuJogo;

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

    public PainelJogo(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) {
        this.controlador = ctrl;
        if (this.controlador != null) {
            // Tentativa de obter Jogo a partir de GerenciadorUI
            if (this.controlador.getMeuJogo() != null) { // Assumindo que getMeuJogo() existe em GerenciadorUI
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

        labelStatusVida = new JLabel("Vida: ...");
        labelStatusFome = new JLabel("Fome: ...");
        labelStatusSede = new JLabel("Sede: ...");
        labelStatusEnergia = new JLabel("Energia: ...");
        labelStatusSanidade = new JLabel("Sanidade: ...");

        for (JLabel lbl : new JLabel[]{labelStatusVida, labelStatusFome, labelStatusSede, labelStatusEnergia, labelStatusSanidade}) {
            lbl.setFont(fonteStatus);
            lbl.setForeground(corStatus);
            painelStatus.add(lbl);
        }
        add(painelStatus, BorderLayout.NORTH);

        painelVisaoAmbiente = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                Image imgAmbiente = null;
                String nomeAmbiente = "Desconhecido";
                if (meuJogo != null && meuJogo.getJogador() != null && meuJogo.getJogador().getAmbienteAtual() != null) {
                    Ambiente ambiente = meuJogo.getJogador().getAmbienteAtual();
                    imgAmbiente = ambiente.getImagemAmbiente();
                    nomeAmbiente = ambiente.getNome();
                }

                if (imgAmbiente != null) {
                    g2d.drawImage(imgAmbiente, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g2d.setColor(new Color(20,20,20));
                    g2d.fillRect(0,0,getWidth(),getHeight());
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.setFont(new Font("SansSerif", Font.ITALIC, 16));
                    String msgErro = "Arte do ambiente '" + nomeAmbiente + "' não carregada.";
                    FontMetrics fm = g2d.getFontMetrics();
                    int msgWidth = fm.stringWidth(msgErro);
                    g2d.drawString(msgErro, (getWidth() - msgWidth) / 2 , getHeight() / 2);
                }
                g2d.dispose();
            }
        };
        painelVisaoAmbiente.setBackground(Color.BLACK);
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
                // Tentativa de obter o GerenciadorDeEventos através da instância de Jogo
                if (meuJogo.getGerenciadorDeEventos() != null) {
                    gerenciadorEventos = meuJogo.getGerenciadorDeEventos();
                }
                // Se não conseguiu via Jogo, tenta via GerenciadorUI (controlador) diretamente
                // Isso só faria sentido se GerenciadorUI também gerenciasse uma instância de GerenciadorDeEventos
                else if (controlador != null && controlador.getGerenciadorDeEventos() != null) { // Assumindo que getGerenciadorDeEventos() existe em GerenciadorUI
                    gerenciadorEventos = controlador.getGerenciadorDeEventos();
                }

                if (gerenciadorEventos != null) {
                    gerenciadorEventos.dispararEventoExploracaoInterface(jogador, areaLog);
                } else {
                    areaLog.append("Sistema de eventos (UI) não pôde ser acionado (gerenciador não encontrado).\n");
                }

                jogador.consumirRecursosBasicos();
                areaLog.append("Recursos básicos consumidos.\n");

                atualizarTela();
            } else {
                if (areaLog != null) {
                    areaLog.append("Não é possível explorar agora (jogador, jogo ou ambiente atual não definido).\n");
                }
            }
        });

        botaoMudarAmbiente.addActionListener(e -> {
            adicionarLog("Tentando mudar de ambiente...");
            JOptionPane.showMessageDialog(this,
                    "A funcionalidade 'Mudar de Ambiente' pela UI precisa ser implementada.",
                    "Mudar de Ambiente", JOptionPane.INFORMATION_MESSAGE);
            atualizarTela();
        });

        botaoRealizarAcao.addActionListener(e -> {
            adicionarLog("Quais ações realizar aqui?");
            if (meuJogo != null && meuJogo.getJogador() != null) {
                // A lógica de apresentar e processar ações específicas do ambiente para UI precisa ser desenvolvida.
                // A chamada abaixo é para console:
                // meuJogo.apresentarAcoesPorAmbiente(meuJogo.getJogador());
                String escolhaAcaoStr = JOptionPane.showInputDialog(this, "Digite a ação desejada (lógica UI pendente):");
                adicionarLog("Ação '" + escolhaAcaoStr + "' selecionada. Lógica de processamento via UI pendente.");
                atualizarTela();
            }
        });

        botaoVerInventario.addActionListener(e -> {
            if (meuJogo != null && meuJogo.getJogador() != null) {
                Inventario inv = meuJogo.getJogador().getInventario();
                if (inv != null) {
                    PainelInventario painel = new PainelInventario(inv); // Assumindo que PainelInventario existe
                    JDialog janela = new JDialog(SwingUtilities.getWindowAncestor(this), "Inventário", Dialog.ModalityType.APPLICATION_MODAL);
                    janela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    janela.setContentPane(painel);
                    janela.pack();
                    janela.setLocationRelativeTo(this);
                    janela.setVisible(true);
                } else {
                    adicionarLog("Inventário não disponível.");
                }
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
            if(painelVisaoAmbiente != null) painelVisaoAmbiente.repaint();
            labelStatusVida.setText("Vida: N/A");
            labelStatusFome.setText("Fome: N/A");
            labelStatusSede.setText("Sede: N/A");
            labelStatusEnergia.setText("Energia: N/A");
            labelStatusSanidade.setText("Sanidade: N/A");
            return;
        }

        Personagem jogador = meuJogo.getJogador();

        labelStatusVida.setText("Vida: " + jogador.getVida());
        labelStatusFome.setText("Fome: " + jogador.getFome());
        labelStatusSede.setText("Sede: " + jogador.getSede());
        labelStatusEnergia.setText("Energia: " + jogador.getEnergia());
        labelStatusSanidade.setText("Sanidade: " + jogador.getSanidade());

        if (painelVisaoAmbiente != null) {
            painelVisaoAmbiente.repaint();
        }
        this.requestFocusInWindow(); // Tenta trazer foco para o painel
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
            // Tenta carregar a imagem como recurso do classpath
            java.net.URL imgUrl = getClass().getClassLoader().getResource("Resources/mapa2.png");
            if (imgUrl == null) {
                // Se não encontrar como recurso, tenta como arquivo (ajuste o caminho se necessário)
                // Este caminho é relativo à execução do JAR ou ao diretório de trabalho.
                // Pode ser necessário um caminho absoluto ou uma lógica mais robusta para encontrar o arquivo.
                // imgUrl = new File("Resources/mapa2.png").toURI().toURL();
                throw new IOException("Arquivo de mapa 'Resources/mapa2.png' não encontrado no classpath.");
            }
            BufferedImage imagemOriginal = ImageIO.read(imgUrl);
            Image imagemRedimensionada = imagemOriginal.getScaledInstance(dialogoMapa.getWidth(), dialogoMapa.getHeight(), Image.SCALE_SMOOTH);
            JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
            dialogoMapa.setContentPane(new JPanel(new BorderLayout())); // Garante que o painel de conteúdo seja novo
            dialogoMapa.getContentPane().add(labelImagem, BorderLayout.CENTER);
            dialogoMapa.setVisible(true);
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace(); // Para depuração
            JOptionPane.showMessageDialog(this, "Erro ao carregar o mapa: " + ex.getMessage(), "Erro de Mapa", JOptionPane.ERROR_MESSAGE);
        }
    }
}