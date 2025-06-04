package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret; // Para auto-scroll do log
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Jogo.Jogo;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
// GerenciadorDeAmbientes não será diretamente acessado aqui para respeitar a não-modificação de Jogo.java

public class PainelJogo extends JPanel {
    private GerenciadorUI controlador;
    private Jogo meuJogo;

    // Componentes de Status
    private JLabel labelStatusVida;
    private JLabel labelStatusFome;
    private JLabel labelStatusSede;
    private JLabel labelStatusEnergia;
    private JLabel labelStatusSanidade;

    // Painel para a visão principal (arte do ambiente)
    private JPanel painelVisaoAmbiente;

    // Botões de Ação Principais
    private JButton botaoExplorar;
    private JButton botaoMudarAmbiente;
    private JButton botaoRealizarAcao;
    private JButton botaoVerInventario;
    private JButton botaoSairDoJogo;

    // Área de Log
    private JTextArea areaLog;
    private JScrollPane scrollLog;

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

        // --- PAINEL DE STATUS (NORTE) ---
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

        // --- PAINEL DE VISÃO DO AMBIENTE (CENTRO) ---
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
                    // Assumindo que você ajustou Ambiente.java para ter getImagemAmbiente()
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

        // --- PAINEL DE LOG E AÇÕES (SUL) ---
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

        Font fonteBotaoAcao = new Font("SansSerif", Font.BOLD, 13);
        Dimension tamanhoBotaoAcao = new Dimension(130, 30);

        for (JButton btn : new JButton[]{botaoExplorar, botaoMudarAmbiente, botaoRealizarAcao, botaoVerInventario, botaoSairDoJogo}) {
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
                ambienteAtual.explorar(meuJogo.getJogador());
                meuJogo.getJogador().consumirRecursosBasicos();
                atualizarTela();
            }
        });

        botaoMudarAmbiente.addActionListener(e -> {
            adicionarLog("Tentando mudar de ambiente...");
            JOptionPane.showMessageDialog(this,
                    "A escolha de ambiente pela UI requer ajustes na classe Jogo.java\n" +
                            "para expor os destinos ou processar a escolha.\n" +
                            "(A lógica de console 'menuAmbientes' existe em Jogo.java)",
                    "Mudar de Ambiente", JOptionPane.INFORMATION_MESSAGE);
            // Para fins de teste, você poderia chamar um método público em Jogo que usa o scanner,
            // mas isso não é ideal para UI.
            // meuJogo.chamarMenuAmbientesConsole(); // Se tal método público existisse
            atualizarTela();
        });

        botaoRealizarAcao.addActionListener(e -> {
            adicionarLog("Quais ações realizar aqui?");
            if (meuJogo != null && meuJogo.getJogador() != null) {
                // Este método em Jogo.java imprime as ações no console.
                meuJogo.apresentarAcoesPorAmbiente(meuJogo.getJogador());
                String escolhaAcaoStr = JOptionPane.showInputDialog(this, "Digite o número da ação (veja console):");
                // Aqui precisaria de um método em Jogo.java para processar essa escolha de ação
                // Ex: meuJogo.processarEscolhaAcao(escolhaAcaoStr);
                adicionarLog("Ação '" + escolhaAcaoStr + "' selecionada. Lógica de processamento via UI pendente.");
                atualizarTela();
            }
        });

        botaoVerInventario.addActionListener(e -> {
            if (meuJogo != null && meuJogo.getJogador() != null) {
                adicionarLog("Verificando o inventário...");
                // Este método em Personagem.java imprime no console.
                meuJogo.getJogador().visualizarInventario();
                JOptionPane.showMessageDialog(this, "Seu inventário foi listado no console.", "Inventário", JOptionPane.INFORMATION_MESSAGE);
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
            if(painelVisaoAmbiente != null) painelVisaoAmbiente.repaint();
            // Atualizar labels de status para "N/A" ou similar
            labelStatusVida.setText("Vida: N/A");
            labelStatusFome.setText("Fome: N/A");
            labelStatusSede.setText("Sede: N/A");
            labelStatusEnergia.setText("Energia: N/A");
            labelStatusSanidade.setText("Sanidade: N/A");
            return;
        }

        Personagem jogador = meuJogo.getJogador();

        // CORRIGIDO: Exibe apenas a vida atual, pois não há getVidaMaxima() em Personagem.java
        labelStatusVida.setText("Vida: " + jogador.getVida());

        labelStatusFome.setText("Fome: " + jogador.getFome());
        labelStatusSede.setText("Sede: " + jogador.getSede());
        labelStatusEnergia.setText("Energia: " + jogador.getEnergia());
        labelStatusSanidade.setText("Sanidade: " + jogador.getSanidade());

        if (painelVisaoAmbiente != null) {
            painelVisaoAmbiente.repaint();
        }
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
            // Labels de status podem ser setados para erro também
            labelStatusVida.setText("Vida: ERRO");
            labelStatusFome.setText("Fome: ERRO");
            labelStatusSede.setText("Sede: ERRO");
            labelStatusEnergia.setText("Energia: ERRO");
            labelStatusSanidade.setText("Sanidade: ERRO");
        }
    }
}
