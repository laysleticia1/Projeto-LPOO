package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TelaNarrativa extends JPanel {
    private Image imagemFundo; // Seu pergaminho
    private JButton botaoContinuar;
    private JTextArea areaNarrativa;
    private JScrollPane scrollPane;

    public TelaNarrativa(Runnable acaoAoContinuar) {
        // << Substitua "pergaminho.png" pelo NOME EXATO do seu arquivo de imagem >>
        String nomeImagemFundo = "pergaminho.png";
        try {
            URL imgUrl = getClass().getResource("/Resources/" + nomeImagemFundo);
            if (imgUrl == null) {
                imgUrl = getClass().getResource(nomeImagemFundo); // Tenta no mesmo pacote
            }
            if (imgUrl != null) {
                imagemFundo = new ImageIcon(imgUrl).getImage();
            } else {
                System.err.println("ERRO AO CARREGAR IMAGEM DE FUNDO (PERGAMINHO): " + nomeImagemFundo + " não encontrada.");
                imagemFundo = null;
            }
        } catch (Exception e) {
            System.err.println("Exceção ao carregar imagem do pergaminho: " + e.getMessage());
            imagemFundo = null;
        }

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600)); // Tamanho do painel da tela

        // Área de texto narrativa
        areaNarrativa = new JTextArea();
        areaNarrativa.setText(getTextoNarrativaPadrao()); // Carrega seu texto narrativo
        areaNarrativa.setFont(new Font("Serif", Font.PLAIN, 18)); // Ajuste a fonte como desejar
        areaNarrativa.setForeground(new Color(50, 40, 30)); // Cor escura para texto em pergaminho claro
        areaNarrativa.setOpaque(false); // JTextArea transparente para o pergaminho aparecer atrás
        areaNarrativa.setEditable(false);
        areaNarrativa.setLineWrap(true);
        areaNarrativa.setWrapStyleWord(true);

        // << PASSO 1: AJUSTE AS MARGENS INTERNAS DA JTextArea >>
        // (topo, esquerda, baixo, direita) em pixels.
        // Aumente os valores de 'esquerda' e 'direita' para que o bloco de texto
        // fique mais estreito dentro da área do pergaminho.
        // Aumente 'topo' e 'baixo' para o texto não colar nas bordas superior/inferior da área de texto.
        areaNarrativa.setMargin(new Insets(60, 90, 60, 90)); // Exemplo: 60px topo/baixo, 90px laterais

        areaNarrativa.setCaretPosition(0); // Garante que o texto comece do topo

        scrollPane = new JScrollPane(areaNarrativa);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false); // Viewport também transparente

        // Para depuração visual da área do scrollPane (descomente para ver uma borda vermelha):
        // scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        // Quando estiver ok, use uma borda vazia ou nenhuma:
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Esconder as barras de rolagem (rolagem com mouse ainda funciona)
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // << PASSO 2: AJUSTE AS DIMENSÕES DO JScrollPane (ÁREA DE TEXTO VISÍVEL) >>
        // Esta dimensão define a "janela" retangular onde o texto será visível e rolável.
        // Meça a parte central e "plana" do seu pergaminho.png onde o texto deve ficar.
        // A LARGURA e ALTURA devem ser MENORES que as dimensões totais do seu pergaminho.png
        // para que as bordas enroladas do pergaminho fiquem "fora" desta área de texto.
        // Exemplo: Para um pergaminho que ocupa quase 800x600, a área de texto pode ser:
        scrollPane.setPreferredSize(new Dimension(700, 350)); // Ex: Largura 500, Altura 350

        // Painel para centralizar o scrollPane (área de texto)
        JPanel painelTextoCentralizado = new JPanel(new GridBagLayout());
        painelTextoCentralizado.setOpaque(false); // Transparente
        GridBagConstraints gbcTexto = new GridBagConstraints();
        // gbcTexto.insets = new Insets(20,0,0,0); // Opcional: Se quiser empurrar o bloco de texto um pouco para baixo
        painelTextoCentralizado.add(scrollPane, gbcTexto);

        add(painelTextoCentralizado, BorderLayout.CENTER);

        // Dentro do construtor de TelaNarrativa.java

        // ... (código da área de texto e JScrollPane) ...

        // Botão continuar
        botaoContinuar = new JButton("Continuar Jornada");
        botaoContinuar.setFont(new Font("Serif", Font.BOLD, 18));
        botaoContinuar.setBackground(new Color(139, 69, 19));
        botaoContinuar.setForeground(new Color(240, 230, 200));
        botaoContinuar.setFocusPainted(false);
        botaoContinuar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 50, 0), 2),
                new EmptyBorder(8, 20, 8, 20)
        ));
        botaoContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botaoContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoAoContinuar != null) {
                    acaoAoContinuar.run();
                }
            }
        });

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setOpaque(false);
        // << AJUSTE AQUI: Margem do painel do botão >>
        // Diminuindo o valor 'baixo' (terceiro parâmetro) para mover o botão mais para baixo.
        // Antes era: new EmptyBorder(10, 0, 50, 0)
        painelBotao.setBorder(new EmptyBorder(10, 0, 20, 0)); // Ex: margem inferior de 20px
        painelBotao.add(botaoContinuar);
        add(painelBotao, BorderLayout.SOUTH);


        // Garante que o scroll esteja no topo após a interface ser montada
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (scrollPane != null) {
                    scrollPane.getVerticalScrollBar().setValue(0);
                }
                if (areaNarrativa != null) { // Checagem adicional
                    areaNarrativa.setCaretPosition(0);
                }
            }
        });
    }

    private String getTextoNarrativaPadrao() {
        return """
               Durante gerações, o reino de Velkaria foi considerado inabitável — uma terra marcada por tragédias antigas, abandonada após uma guerra que os próprios registros se recusam a descrever. Tudo que restou foram os mitos… e as fronteiras proibidas.
               Velkaria foi palco de um conflito brutal entre homens, feras e forças arcanas. Quando a guerra cessou, o reino se partiu: cidades ruíram, rios mudaram, o clima enlouqueceu — e o nome Velkaria tornou-se maldito.
               Hoje, o que resta são cinco regiões perigosas, cada uma marcada por horrores únicos:
                • Vhaldrak: montanhas congeladas, cheias de ecos de guerra e predadores famintos.
                • Elvaron: floresta viva, densa e enlouquecedora, onde poucos retornam.
                • Myndros: planícies alagadas, com águas traiçoeiras e criaturas ocultas.
                • Narzug: uma fenda profunda, tóxica e cheia de sons perturbadores.
                • Thargor: ruínas da antiga capital, assombradas por vozes, visões e mistérios.
               
               Você desperta no chão frio, sem memórias claras, cercado por um silêncio que observa. Não sabe como chegou aqui. Só sabe de uma coisa:
               
               Você está dentro da Última Fronteira.
               E a única escolha que resta…
               É sobreviver.
               """;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            // Desenha a imagem de fundo (pergaminho) cobrindo todo o painel
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        } else {
            // Fallback: Cor de fundo se a imagem do pergaminho não carregar
            g.setColor(new Color(230, 220, 190));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 16));
            g.drawString("Fundo de pergaminho não carregado.", 20,20);
        }
    }
}