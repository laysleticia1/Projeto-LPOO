
package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret; // Para auto-scroll do log
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Jogo.Jogo;
import Personagem.Superclasse.Personagem;
import Personagem.Subclasses.*;
import Personagem.Inventario.*;
import Ambiente.Superclasse.Ambiente;
// GerenciadorDeAmbientes não será diretamente acessado aqui para respeitar a não-modificação de Jogo.java

public class PainelJogo extends JPanel {
    private GerenciadorUI controlador;
    private Jogo meuJogo;

    // Componentes de Status
    private JLabel labelStatusNivel;
    private JLabel labelStatusVida;
    private JLabel labelStatusFome;
    private JLabel labelStatusSede;
    private JLabel labelStatusEnergia;
    private JLabel labelStatusSanidade;

    // Painel para a visão principal (arte do ambiente)
    private JPanel painelVisaoAmbiente;

    // Botões de Ação Principais
    private JButton botaoMapa;
    private JButton botaoExplorar;
    private JButton botaoMudarAmbiente;
    private JButton botaoRealizarAcao;
    private JButton botaoVerInventario;
    private JButton botaoSairDoJogo;

    // Área de Log
    private JTextArea areaLog;
    private JScrollPane scrollLog;
    private JLabel imagemAmbiente;

    public PainelJogo(JPanel painelPrincipalCardLayoutIgnorado, GerenciadorUI ctrl) {
        this.controlador = ctrl;
        if (this.controlador != null) {
            this.meuJogo = controlador.getMeuJogo();
        } else {
            System.err.println("PainelJogo ERRO: Controlador é null!");
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

                Image imgAmbiente = null;
                String nomeAmbiente = "Desconhecido";
                if (meuJogo != null && meuJogo.getJogador() != null && meuJogo.getJogador().getAmbienteAtual() != null) {
                    Ambiente ambiente = meuJogo.getJogador().getAmbienteAtual();
                    imgAmbiente = ambiente.getImagemAmbiente();
                    nomeAmbiente = ambiente.getNome();
                }

                if (imgAmbiente == null) {
                    g2d.setColor(new Color(20, 20, 20));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.setFont(new Font("SansSerif", Font.ITALIC, 16));
                    String msgErro = "Arte do ambiente '" + nomeAmbiente + "' não carregada.";
                    FontMetrics fm = g2d.getFontMetrics();
                    int msgWidth = fm.stringWidth(msgErro);
                    g2d.drawString(msgErro, (getWidth() - msgWidth) / 2, getHeight() / 2);
                }

                g2d.dispose();
            }
        };
        painelVisaoAmbiente.setBackground(Color.BLACK);

        imagemAmbiente = new JLabel();
        imagemAmbiente.setHorizontalAlignment(SwingConstants.CENTER);
        imagemAmbiente.setVerticalAlignment(SwingConstants.CENTER);
        painelVisaoAmbiente.add(imagemAmbiente, BorderLayout.CENTER);
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
                Ambiente ambienteAtual = meuJogo.getJogador().getAmbienteAtual();
                adicionarLog("Você explora " + ambienteAtual.getNome() + "...");
                // A lógica de explorar em Ambiente.java imprime no console.
                // A UI não terá um retorno direto além do que o método em Ambiente fizer.
                ambienteAtual.explorar(meuJogo.getJogador());
                meuJogo.getJogador().consumirRecursosBasicos();
                atualizarTela();
            }
        });

        botaoMudarAmbiente.addActionListener(e -> {
            adicionarLog("Abrindo mapa de ambientes...");

            new TelaMoverAmbientes(nomeAmbienteEscolhido -> {
                try {
                    meuJogo.mudarAmbienteViaInterface(nomeAmbienteEscolhido, areaLog, imagemAmbiente);
                    adicionarLog("Você se moveu para: " + nomeAmbienteEscolhido);
                    atualizarTela(); // Atualiza status e ambiente após mudança
                } catch (Exception ex) {
                    adicionarLog("Erro ao mudar de ambiente: " + ex.getMessage());
                    JOptionPane.showMessageDialog(this,
                            "Erro ao mudar de ambiente:\n" + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
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

                String[] opcoes;

                if (tipoEscolhido.equals("Ação Comum")) {
                    Ambiente ambiente = meuJogo.getJogador().getAmbienteAtual();
                    opcoes = switch (ambiente.getClass().getSimpleName()) {
                        case "Floresta" -> new String[]{"Coletar frutas", "Coletar madeira e cipós"};
                        case "Montanha" -> new String[]{"Escalar abrigo", "Procurar itens congelados"};
                        case "LagoRio" -> new String[]{"Beber água", "Pescar"};
                        case "Caverna" -> new String[]{"Acender tochas", "Buscar minérios"};
                        case "Ruinas" -> new String[]{"Vasculhar suprimentos", "Analisar símbolos"};
                        default -> new String[]{"Explorar o local"};
                    };

                    String acaoEscolhida = (String) JOptionPane.showInputDialog(
                            this,
                            "Escolha uma ação:",
                            "Ação Comum",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            opcoes,
                            opcoes[0]
                    );

                    if (acaoEscolhida != null) {
                        meuJogo.executarAcaoComumInterface(acaoEscolhida, areaLog);
                        atualizarTela();
                    }

                } else if (tipoEscolhido.equals("Ação Especial")) {
                    Personagem jogador = meuJogo.getJogador();

                    if (jogador instanceof Rastreador)
                        opcoes = new String[]{"Identificar pegadas", "Farejar trilha", "Procurar recursos"};
                    else if (jogador instanceof Mecanico)
                        opcoes = new String[]{"Consertar equipamento", "Melhorar arma"};
                    else if (jogador instanceof Medico)
                        opcoes = new String[]{"Curar a si mesmo", "Curar outro personagem", "Preparar remédio natural"};
                    else if (jogador instanceof SobreviventeNato)
                        opcoes = new String[]{"Fabricar lança", "Caçar animais"};
                    else
                        opcoes = new String[]{"Sem ações disponíveis"};

                    String acaoEspecial = (String) JOptionPane.showInputDialog(
                            this,
                            "Escolha uma ação especial:",
                            "Ação Especial",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            opcoes,
                            opcoes[0]
                    );

                    if (acaoEspecial != null) {
                        meuJogo.executarAcaoEspecialInterface(acaoEspecial, areaLog);
                        atualizarTela();
                    }
                }
            }
        });




        botaoVerInventario.addActionListener(e -> {
            if (meuJogo != null && meuJogo.getJogador() != null) {
                Inventario inv = meuJogo.getJogador().getInventario();
                Personagem jogador = meuJogo.getJogador();
                PainelInventario painel = new PainelInventario(inv, jogador);
                JDialog janela = new JDialog(SwingUtilities.getWindowAncestor(this), "Inventário", Dialog.ModalityType.APPLICATION_MODAL);
                janela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                janela.setContentPane(painel);
                janela.pack();
                janela.setLocationRelativeTo(this);
                janela.setVisible(true);
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
            System.out.println("LOG (UI): " + mensagem);
        }
    }

    public void atualizarTela() {
        if (meuJogo == null || meuJogo.getJogador() == null) {
            labelStatusNivel.setText("Nível: N/A");
            labelStatusVida.setText("Vida: N/A");
            labelStatusFome.setText("Fome: N/A");
            labelStatusSede.setText("Sede: N/A");
            labelStatusEnergia.setText("Energia: N/A");
            labelStatusSanidade.setText("Sanidade: N/A");
            if (imagemAmbiente != null) imagemAmbiente.setIcon(null);
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

        if (imagemAmbiente != null && jogador.getAmbienteAtual() != null) {
            Image imagem = jogador.getAmbienteAtual().getImagemAmbiente();
            imagemAmbiente.setIcon(imagem != null ? new ImageIcon(imagem) : null);
        }

        if (painelVisaoAmbiente != null) painelVisaoAmbiente.repaint();
        this.requestFocusInWindow();
    }

    public void aoEntrarNaTela() {
        if (meuJogo != null && meuJogo.getJogador() != null && meuJogo.getJogador().getAmbienteAtual() != null) {
            Ambiente ambienteAtual = meuJogo.getJogador().getAmbienteAtual();
            areaLog.setText("Você está em: " + ambienteAtual.getNome() + ".\n");
            areaLog.append("------------------------------------------------------\n");
            areaLog.append(ambienteAtual.getDescricao() + "\n");
            areaLog.append("Clima: " + ambienteAtual.getCondicaoClimatica() + "\n\n");
            atualizarTela();
        } else {
            areaLog.setText("ERRO CRÍTICO AO CARREGAR O JOGO! PERSONAGEM OU AMBIENTE NULO.");
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
        dialogoMapa.setSize(1000, 700); // Tamanho fixo
        dialogoMapa.setLocationRelativeTo(this);

        try {
            BufferedImage imagemOriginal = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Resources/mapa2.png"));
            Image imagemRedimensionada = imagemOriginal.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
            JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
            dialogoMapa.setContentPane(new JPanel(new BorderLayout()));
            dialogoMapa.getContentPane().add(labelImagem, BorderLayout.CENTER);
            dialogoMapa.setVisible(true);
        } catch (IOException | NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o mapa.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void realizarAcaoComumUI() {
        if (meuJogo != null && meuJogo.getJogador() != null) {
            java.util.List<String> acoes = meuJogo.getAcoesComunsDisponiveis(meuJogo.getJogador());
            String escolha = (String) JOptionPane.showInputDialog(this, "Escolha uma ação comum:",
                    "Ação Comum", JOptionPane.PLAIN_MESSAGE, null,
                    acoes.toArray(), acoes.get(0));
            if (escolha != null) {
                meuJogo.executarAcaoComumInterface(escolha, areaLog);
                atualizarTela();
            }
        }
    }

    private void realizarAcaoEspecialUI() {
        if (meuJogo != null && meuJogo.getJogador() != null) {
            java.util.List<String> acoes = meuJogo.getAcoesEspeciaisDisponiveis(meuJogo.getJogador());
            String escolha = (String) JOptionPane.showInputDialog(this, "Escolha uma ação especial:",
                    "Ação Especial", JOptionPane.PLAIN_MESSAGE, null,
                    acoes.toArray(), acoes.get(0));
            if (escolha != null) {
                meuJogo.executarAcaoEspecialInterface(escolha, areaLog);
                atualizarTela();
            }
        }
    }

}