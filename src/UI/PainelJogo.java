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

    // Componentes de Status
    private JLabel labelStatusNivel; // Mantido da branch main
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

        // Usando BorderLayout da branch main
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
                    imgAmbiente = ambiente.getImagemAmbiente(); // Assumindo que Ambiente tem getImagemAmbiente() que retorna Image
                    nomeAmbiente = ambiente.getNome();

                    // Lógica para desenhar a imagem do ambiente, se existir
                    if (imgAmbiente != null) {
                        // Calcula a proporção para manter o aspect ratio
                        int painelW = getWidth();
                        int painelH = getHeight();
                        int imgW = imgAmbiente.getWidth(null);
                        int imgH = imgAmbiente.getHeight(null);

                        if (imgW <= 0 || imgH <= 0) { // Imagem inválida ou não carregada
                             g2d.setColor(new Color(20, 20, 20));
                            g2d.fillRect(0, 0, painelW, painelH);
                            g2d.setColor(Color.LIGHT_GRAY);
                            g2d.setFont(new Font("SansSerif", Font.ITALIC, 16));
                            String msgErro = "Arte do ambiente '" + nomeAmbiente + "' com dimensões inválidas.";
                            FontMetrics fm = g2d.getFontMetrics();
                            int msgWidth = fm.stringWidth(msgErro);
                            g2d.drawString(msgErro, (painelW - msgWidth) / 2, painelH / 2);
                        } else {
                            double painelRatio = (double) painelW / painelH;
                            double imgRatio = (double) imgW / imgH;

                            int drawW = painelW;
                            int drawH = painelH;

                            if (imgRatio > painelRatio) { // Imagem mais larga que o painel
                                drawH = (int) (painelW / imgRatio);
                            } else { // Imagem mais alta que o painel (ou proporção igual)
                                drawW = (int) (painelH * imgRatio);
                            }
                            int x = (painelW - drawW) / 2;
                            int y = (painelH - drawH) / 2;
                            g2d.drawImage(imgAmbiente, x, y, drawW, drawH, this);
                        }
                    } else { // Se imgAmbiente for null
                        g2d.setColor(new Color(20, 20, 20));
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                        g2d.setColor(Color.LIGHT_GRAY);
                        g2d.setFont(new Font("SansSerif", Font.ITALIC, 16));
                        String msgErro = "Arte do ambiente '" + nomeAmbiente + "' não carregada.";
                        FontMetrics fm = g2d.getFontMetrics();
                        int msgWidth = fm.stringWidth(msgErro);
                        g2d.drawString(msgErro, (getWidth() - msgWidth) / 2, getHeight() / 2);
                    }
                } else { // Se meuJogo ou jogador ou ambienteAtual for null
                    g2d.setColor(new Color(25, 25, 25));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.setFont(new Font("SansSerif", Font.BOLD, 18));
                    String msg = "Jogo não iniciado ou ambiente não definido.";
                    FontMetrics fm = g2d.getFontMetrics();
                    int msgWidth = fm.stringWidth(msg);
                    g2d.drawString(msg, (getWidth() - msgWidth) / 2, getHeight() / 2);
                }
                g2d.dispose();
            }
        };
        painelVisaoAmbiente.setBackground(Color.BLACK); // Cor de fundo caso a imagem não preencha tudo

        // imagemAmbiente JLabel não é mais usado para desenhar diretamente a imagem de fundo,
        // pois o paintComponent do painelVisaoAmbiente cuida disso.
        // Pode ser removido ou usado para outros propósitos se necessário.
        // Se for removido, ajuste o add(painelVisaoAmbiente, BorderLayout.CENTER);
        // e a remoção da sua instanciação. Por ora, vou mantê-lo mas não adicioná-lo diretamente.
        // O código original da branch 'main' adicionava um JLabel ao BorderLayout.CENTER.
        // A lógica de desenho customizado em paintComponent é mais flexível para scaling.
        // Para manter a estrutura da 'main' que usa um JLabel para a imagem:
        imagemAmbiente = new JLabel(); // Instanciado, mas a imagem será setada em atualizarTela
        imagemAmbiente.setHorizontalAlignment(SwingConstants.CENTER);
        imagemAmbiente.setVerticalAlignment(SwingConstants.CENTER);
        // painelVisaoAmbiente.add(imagemAmbiente, BorderLayout.CENTER); // Removido para usar paintComponent

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

                // Lógica da branch eventos_interface para explorar
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
                // Consumir recursos básicos e atualizar tela é feito pelo GerenciadorDeTurnos chamado dentro de dispararEventoExploracaoInterface
                // Se não for, descomentar as linhas abaixo e ajustar GerenciadorDeEventos
                // jogador.consumirRecursosBasicos(); 
                // areaLog.append("Recursos básicos consumidos.\n");

                atualizarTela();
            } else {
                if (areaLog != null) {
                    areaLog.append("Não é possível explorar agora (jogador, jogo ou ambiente atual não definido).\n");
                }
            }
        });

        botaoMudarAmbiente.addActionListener(e -> {
            // Lógica da branch main para mudar ambiente
            adicionarLog("Abrindo mapa de ambientes...");
            if (meuJogo == null) {
                adicionarLog("Instância do jogo não disponível para mover ambientes.");
                JOptionPane.showMessageDialog(this, "Erro: Jogo não inicializado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Assegure que TelaMoverAmbientes está acessível e correta
            new TelaMoverAmbientes(meuJogo.getGerenciadorDeAmbientes().getAmbientes(), nomeAmbienteEscolhido -> {
                try {
                    meuJogo.mudarAmbienteViaInterface(nomeAmbienteEscolhido, areaLog, imagemAmbiente);
                    // A mensagem "Você se moveu para..." já é adicionada por mudarAmbienteViaInterface
                    atualizarTela(); 
                } catch (Exception ex) {
                    adicionarLog("Erro ao mudar de ambiente: " + ex.getMessage());
                    JOptionPane.showMessageDialog(this,
                            "Erro ao mudar de ambiente:\n" + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });
        });

        botaoRealizarAcao.addActionListener(e -> {
            // Lógica da branch main para realizar ação
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

                if (tipoEscolhido == null) return; // Usuário cancelou

                java.util.List<String> acoesDisponiveis;
                String tituloDialogo;

                if (tipoEscolhido.equals("Ação Comum")) {
                    acoesDisponiveis = meuJogo.getAcoesComunsDisponiveis(meuJogo.getJogador());
                    tituloDialogo = "Ação Comum";
                } else { // Ação Especial
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
                    } else { // Ação Especial
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
                // Lógica da branch main (passa jogador)
                Personagem jogador = meuJogo.getJogador();
                if (inv != null) {
                    PainelInventario painel = new PainelInventario(inv, jogador, areaLog); // Passa areaLog
                    JDialog janela = new JDialog(SwingUtilities.getWindowAncestor(this), "Inventário", Dialog.ModalityType.APPLICATION_MODAL);
                    janela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    janela.setContentPane(painel);
                    janela.pack();
                    janela.setLocationRelativeTo(this);
                    janela.setVisible(true);
                    atualizarTela(); // Atualiza caso um item seja usado/removido no inventário
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
                // Adicionar qualquer lógica de salvamento aqui antes de sair, se necessário
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
            // Lógica combinada: define N/A para todos os status
            labelStatusNivel.setText("Nível: N/A");
            labelStatusVida.setText("Vida: N/A");
            labelStatusFome.setText("Fome: N/A");
            labelStatusSede.setText("Sede: N/A");
            labelStatusEnergia.setText("Energia: N/A");
            labelStatusSanidade.setText("Sanidade: N/A");
            
            // Limpa a imagem do ambiente se estiver usando o JLabel para isso
            // if (imagemAmbiente != null) imagemAmbiente.setIcon(null); 
            
            // Repinta o painel de visão do ambiente (que pode ter sua própria lógica de "estado nulo")
            if (painelVisaoAmbiente != null) painelVisaoAmbiente.repaint();
            return;
        }

        Personagem jogador = meuJogo.getJogador();
        // Lógica combinada: atualiza todos os status, incluindo nível
        labelStatusNivel.setText("Nível: " + jogador.getNivel());
        labelStatusVida.setText("Vida: " + jogador.getVida() + "/" + jogador.getVidaMaxima());
        labelStatusFome.setText("Fome: " + jogador.getFome() + "/" + jogador.getFomeMaxima());
        labelStatusSede.setText("Sede: " + jogador.getSede() + "/" + jogador.getSedeMaxima());
        labelStatusEnergia.setText("Energia: " + jogador.getEnergia() + "/" + jogador.getEnergiaMaxima());
        labelStatusSanidade.setText("Sanidade: " + jogador.getSanidade() + "/" + jogador.getSanidadeMaxima());

        // Atualiza a imagem do ambiente diretamente no painelVisaoAmbiente
        // Se você estiver usando um JLabel 'imagemAmbiente' dentro de 'painelVisaoAmbiente'
        // e quiser continuar com essa abordagem, a lógica seria:
        /*
        if (imagemAmbiente != null && jogador.getAmbienteAtual() != null) {
            Image img = jogador.getAmbienteAtual().getImagemAmbiente(); // Supondo que retorna Image
            if (img != null) {
                // Redimensionar a imagem para caber no JLabel/Painel sem distorcer
                int panelWidth = painelVisaoAmbiente.getWidth();
                int panelHeight = painelVisaoAmbiente.getHeight();
                if (panelWidth > 0 && panelHeight > 0) {
                    Image scaledImg = img.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH); // Ou SCALE_DEFAULT
                    imagemAmbiente.setIcon(new ImageIcon(scaledImg));
                } else {
                     imagemAmbiente.setIcon(new ImageIcon(img)); // Sem redimensionamento se o painel não tem tamanho
                }
            } else {
                imagemAmbiente.setIcon(null); // Limpa se não houver imagem
            }
        }
        */
        // Como estamos usando paintComponent para desenhar a imagem, apenas repintar é suficiente.
        if (painelVisaoAmbiente != null) painelVisaoAmbiente.repaint();
        
        this.requestFocusInWindow(); // Lógica combinada
    }

    public void aoEntrarNaTela() {
        if (meuJogo != null && meuJogo.getJogador() != null && meuJogo.getJogador().getAmbienteAtual() != null) {
            Ambiente ambienteAtual = meuJogo.getJogador().getAmbienteAtual();
            if (areaLog != null) { // Checagem adicional para areaLog
                areaLog.setText("Você está em: " + ambienteAtual.getNome() + ".\n");
                areaLog.append("------------------------------------------------------\n");
                areaLog.append(ambienteAtual.getDescricao() + "\n");
                areaLog.append("Clima: " + ambienteAtual.getCondicaoClimatica() + "\n\n");
            }
            atualizarTela();
        } else {
            if (areaLog != null) { // Checagem adicional para areaLog
                areaLog.setText("ERRO CRÍTICO AO CARREGAR O JOGO! PERSONAGEM OU AMBIENTE NULO.");
            }
            // Lógica combinada: atualiza todos os status para ERRO
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
        dialogoMapa.setSize(1000, 700); // Tamanho padrão do mapa
        dialogoMapa.setLocationRelativeTo(this);

        try {
            java.net.URL imgUrl = getClass().getClassLoader().getResource("Resources/mapa2.png");
            if (imgUrl == null) {
                throw new IOException("Arquivo de mapa 'Resources/mapa2.png' não encontrado no classpath.");
            }
            BufferedImage imagemOriginal = ImageIO.read(imgUrl);
            
            // Painel para desenhar a imagem, permitindo redimensionamento dinâmico se a janela for redimensionável
            JPanel painelMapa = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (imagemOriginal != null) {
                        // Desenha a imagem escalada para preencher o painel mantendo a proporção
                        int painelW = getWidth();
                        int painelH = getHeight();
                        int imgW = imagemOriginal.getWidth();
                        int imgH = imagemOriginal.getHeight();

                        double painelRatio = (double) painelW / painelH;
                        double imgRatio = (double) imgW / imgH;

                        int drawW = painelW;
                        int drawH = painelH;

                        if (imgRatio > painelRatio) { // Imagem mais larga
                            drawH = (int) (painelW / imgRatio);
                        } else { // Imagem mais alta
                            drawW = (int) (painelH * imgRatio);
                        }
                        int x = (painelW - drawW) / 2;
                        int y = (painelH - drawH) / 2;
                        g.drawImage(imagemOriginal, x, y, drawW, drawH, this);
                    }
                }
            };
            painelMapa.setPreferredSize(new Dimension(imagemOriginal.getWidth(), imagemOriginal.getHeight())); // Define o tamanho preferido inicial

            dialogoMapa.setContentPane(new JScrollPane(painelMapa)); // Adiciona scroll se a imagem for maior que a janela
            dialogoMapa.pack(); // Ajusta o tamanho da janela ao conteúdo (se JScrollPane permitir)
            if (dialogoMapa.getWidth() > 1000 || dialogoMapa.getHeight() > 700) { // Limita ao tamanho máximo se pack() exceder
                 dialogoMapa.setSize(Math.min(dialogoMapa.getWidth(), 1000), Math.min(dialogoMapa.getHeight(), 700));
            }
             dialogoMapa.setLocationRelativeTo(this); // Centraliza novamente após pack/setSize
            dialogoMapa.setVisible(true);

        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar o mapa: " + ex.getMessage(), "Erro de Mapa", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Os métodos realizarAcaoComumUI() e realizarAcaoEspecialUI() da branch main
    // foram integrados na lógica do ActionListener de botaoRealizarAcao,
    // portanto, não são adicionados aqui como métodos separados para evitar redundância.
}
