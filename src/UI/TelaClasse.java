package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
// Removido: import javax.swing.border.TitledBorder; // Não é mais necessário para o JOptionPane da intro
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
    private Image backgroundImageTelaClasse;

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

    // Cores e Fontes
    private Color corTextoDescricao = new Color(175, 144, 40);
    private Font fonteDescricao = new Font("Serif", Font.ITALIC, 16);
    private Font fonteBotoesNavegacao = new Font("SansSerif", Font.BOLD, 18);
    private Color corTextoBotoesNav = new Color(230, 220, 200);

    // Removido: private final String NOME_ARQUIVO_FUNDO_INTRO;

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
                this.backgroundImageTelaClasse = new ImageIcon(imgUrl).getImage();
            } else {
                System.err.println("ERRO GRAVE: Imagem de fundo da TelaClasse '" + nomeArquivoBackground + "' NÃO ENCONTRADA!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.backgroundImageTelaClasse = null;
        }

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        descricoesClasses.put(RASTREADOR, "Mestre em seguir trilhas e identificar perigos na natureza. Encontra recursos com mais facilidade.");
        descricoesClasses.put(MECANICO, "Engenhoso com ferramentas e sucata. Consegue consertar e criar dispositivos úteis para sobrevivência.");
        descricoesClasses.put(MEDICO, "Especialista em primeiros socorros e tratamento de ferimentos. Consegue usar ervas para criar remédios.");
        descricoesClasses.put(SOBREVIVENTE_NATO, "Resistente e adaptável. Possui habilidades instintivas para montar abrigos e fabricar itens básicos.");
        descricoesClasses.put("", "Selecione uma classe ao lado para ver sua descrição detalhada.");

        // << AJUSTE AS COORDENADAS X, Y, LARGURA, ALTURA PARA OS BOTÕES DE CLASSE >>
        criarBotaoClasse(RASTREADOR, 60, 160, 170, 170);
        criarBotaoClasse(MECANICO, 240, 160, 170, 170);
        criarBotaoClasse(MEDICO, 60, 340, 170, 170);
        criarBotaoClasse(SOBREVIVENTE_NATO, 240, 340, 170, 170);

        areaDescricaoClasse = new JTextArea(descricoesClasses.get(""));
        areaDescricaoClasse.setFont(fonteDescricao);
        areaDescricaoClasse.setForeground(corTextoDescricao);
        areaDescricaoClasse.setWrapStyleWord(true); areaDescricaoClasse.setLineWrap(true);
        areaDescricaoClasse.setEditable(false); areaDescricaoClasse.setOpaque(false);
        areaDescricaoClasse.setMargin(new Insets(15, 20, 15, 20));

        scrollDescricao = new JScrollPane(areaDescricaoClasse);
        scrollDescricao.setOpaque(false); scrollDescricao.getViewport().setOpaque(false);
        scrollDescricao.setBounds(455, 190, 285, 280); // << AJUSTE AQUI
        scrollDescricao.setBorder(BorderFactory.createEmptyBorder());
        add(scrollDescricao);

        int painelPrefWidth = getPreferredSize().width;
        int painelPrefHeight = getPreferredSize().height;
        int alturaBotaoNav = 40;
        int larguraBotaoNavCriar = 240;
        int larguraBotaoNavVoltar = 120;
        int margemInferiorNav = 25;
        int yBotoesNav = painelPrefHeight - alturaBotaoNav - margemInferiorNav;
        int margemLateralNav = 30;

        botaoCriarPersonagem = new JButton("Criar Personagem");
        botaoCriarPersonagem.setFont(fonteBotoesNavegacao); botaoCriarPersonagem.setForeground(corTextoBotoesNav);
        botaoCriarPersonagem.setOpaque(false); botaoCriarPersonagem.setContentAreaFilled(false);
        botaoCriarPersonagem.setBorderPainted(false); botaoCriarPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoCriarPersonagem.setBorder(new EmptyBorder(5,10,5,10));
        int xBotaoCriar = painelPrefWidth - larguraBotaoNavCriar - margemLateralNav;
        botaoCriarPersonagem.setBounds(xBotaoCriar, yBotoesNav, larguraBotaoNavCriar, alturaBotaoNav);
        add(botaoCriarPersonagem);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 16));
        botaoVoltar.setOpaque(false); botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorderPainted(false); botaoVoltar.setForeground(corTextoBotoesNav);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR)); botaoVoltar.setBorder(new EmptyBorder(5,10,5,10));
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
                    controlador.irParaTelaNome(); return;
                }
                if (meuJogo == null) {
                    JOptionPane.showMessageDialog(TelaClasse.this, "Erro interno: Instância do jogo não disponível.", "Erro Crítico", JOptionPane.ERROR_MESSAGE); return;
                }

                boolean sucesso = meuJogo.iniciarNovaPartida(nomePersonagem, classeSelecionadaAtual);

                if (sucesso) {
                    System.out.println("Personagem criado com sucesso! Nome: " + meuJogo.getJogador().getNome() + ", Classe: " + meuJogo.getJogador().getClasse());
                    // String textoIntro = meuJogo.getTextoIntroducao(); // Texto da introdução ainda é pego

                    // REMOVIDO: Toda a lógica de criar PainelIntroducaoComArte e mostrar JOptionPane

                    // Navega diretamente para a TelaNarrativa
                    controlador.irParaTelaNarrativa();
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

    private void criarBotaoClasse(final String nomeClasse, int x, int y, int largura, int altura){
        JButton botao = new JButton();
        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBounds(x,y,largura,altura);
        botao.addActionListener(e -> {
            classeSelecionadaAtual = nomeClasse;
            areaDescricaoClasse.setText(descricoesClasses.getOrDefault(nomeClasse, "Descrição não disponível."));
            areaDescricaoClasse.setCaretPosition(0);
            System.out.println("Classe selecionada: "+nomeClasse);
        });
        add(botao);
    }

    public void prepararTela(){
        classeSelecionadaAtual = null;
        if(areaDescricaoClasse!=null && descricoesClasses!=null) {
            areaDescricaoClasse.setText(descricoesClasses.getOrDefault("","Selecione uma classe para ver sua descrição detalhada."));
            areaDescricaoClasse.setCaretPosition(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.backgroundImageTelaClasse !=null){
            Graphics2D g2d=(Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(this.backgroundImageTelaClasse,0,0,getWidth(),getHeight(),this);
            g2d.dispose();
        } else {
            g.setColor(new Color(50,40,30));
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(Color.WHITE);
            g.drawString("Fundo TelaClasse não carregado",50,getHeight()/2);
        }
    }

    // REMOVIDA: Classe interna PainelIntroducaoComArte, pois o JOptionPane não será mais usado desta forma.
}