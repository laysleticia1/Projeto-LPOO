package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Removido: import Jogo.Jogo; // Não é mais necessário importar Jogo diretamente aqui
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MenuInicialPanel extends JPanel {
    private JPanel painelPrincipalCardLayout;
    // ### CORREÇÃO PRINCIPAL AQUI ###
    private GerenciadorUI controlador; // Campo para armazenar a referência ao GerenciadorUI
    private Image backgroundImage;

    // ### CORREÇÃO PRINCIPAL AQUI ###
    public MenuInicialPanel(JPanel painelPrincipal, GerenciadorUI ctrl) { // Construtor agora aceita GerenciadorUI
        this.painelPrincipalCardLayout = painelPrincipal;
        this.controlador = ctrl; // Armazena a referência ao GerenciadorUI

        // Carrega a imagem de fundo
        try {
            // Tenta carregar do diretório /Resources/ primeiro
            URL imgUrl = getClass().getResource("/Resources/menuInicial.png");

            // Se não encontrar, tenta carregar do mesmo diretório da classe UI (como fallback)
            if (imgUrl == null) {
                imgUrl = getClass().getResource("menuInicial.png");
                if (imgUrl != null) {
                    System.out.println("Imagem de fundo carregada (fallback): " + imgUrl);
                }
            } else {
                System.out.println("Imagem de fundo carregada com sucesso de: " + imgUrl);
            }


            if (imgUrl != null) {
                this.backgroundImage = new ImageIcon(imgUrl).getImage();
            } else {
                System.err.println("Erro ao carregar a imagem de fundo: menuInicial.png não encontrada.");
                System.err.println("Verifique se o arquivo está no classpath: /Resources/menuInicial.png ou dentro do pacote UI.");
                this.backgroundImage = null;
            }
        } catch (Exception e) {
            System.err.println("Exceção ao carregar a imagem de fundo:");
            e.printStackTrace();
            this.backgroundImage = null;
        }

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(800, 600));

        JButton botaoIniciar = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getModel().isPressed()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(new Color(0, 0, 0, 50)); // Efeito de escurecimento sutil
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    g2.dispose();
                }
            }
        };

        botaoIniciar.setText(""); // Botão sem texto, depende da imagem de fundo
        Dimension buttonSize = new Dimension(260, 70); // Tamanho do botão
        botaoIniciar.setPreferredSize(buttonSize);
        botaoIniciar.setMinimumSize(buttonSize);
        botaoIniciar.setMaximumSize(buttonSize);

        // Configurações para tornar o botão "invisível" e usar a imagem de fundo
        botaoIniciar.setOpaque(false);
        botaoIniciar.setContentAreaFilled(false);
        botaoIniciar.setBorderPainted(false);
        botaoIniciar.setFocusPainted(false); // Remove a borda de foco ao clicar
        botaoIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Muda o cursor para mãozinha

        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ### CORREÇÃO PRINCIPAL AQUI ###
                // Usa o controlador (GerenciadorUI) para navegar para a primeira tela de criação
                controlador.irParaTelaNome();
            }
        });

        // Posicionamento do botão (ajuste os Insets conforme a sua imagem de fundo)
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 0; // Se houver outros componentes, ajuste gridy
        gbcButton.weightx = 1.0; // Centraliza horizontalmente
        gbcButton.weighty = 1.0; // Centraliza verticalmente
        gbcButton.anchor = GridBagConstraints.CENTER; // Ancoragem ao centro
        gbcButton.insets = new Insets(120, 0, 0, 0); // Margem superior para posicionar o botão
        // Se o botão estiver desalinhado com sua imagem, ajuste o valor de Insets.
        // new Insets(top, left, bottom, right)

        add(botaoIniciar, gbcButton);

        // Você pode adicionar outros botões (Carregar, Sair) de forma similar, ajustando gbcButton.gridy e os Insets.
        // Exemplo: Botão Sair (simplificado)
        JButton botaoSair = new JButton("Sair do Jogo"); // Ou deixe vazio se usar imagem
        // ... (configure o botaoSair de forma similar ao botaoIniciar se for um botão invisível)
        // ou defina uma aparência padrão:
        botaoSair.setFont(new Font("SansSerif", Font.BOLD, 16));
        botaoSair.setPreferredSize(new Dimension(200, 50));
        // ... listener para botaoSair ...
        botaoSair.addActionListener(e -> System.exit(0));

        GridBagConstraints gbcSair = new GridBagConstraints();
        gbcSair.gridx = 0;
        gbcSair.gridy = 1; // Posição abaixo do botão iniciar
        gbcSair.anchor = GridBagConstraints.CENTER;
        gbcSair.insets = new Insets(20, 0, 0, 0); // Espaço abaixo do botão iniciar
        // add(botaoSair, gbcSair); // Descomente para adicionar o botão Sair
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            g2d.dispose();
        } else {
            // Fallback caso a imagem não carregue
            g.setColor(new Color(220, 220, 220)); // Um cinza claro para o fundo
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.RED); // Cor do texto de erro
            g.setFont(new Font("SansSerif", Font.BOLD, 16));
            String errorMsg = "Imagem de fundo (menuInicial.png) não carregada.";
            FontMetrics fm = g.getFontMetrics();
            int stringWidth = fm.stringWidth(errorMsg);
            g.drawString(errorMsg, (this.getWidth() - stringWidth) / 2, this.getHeight() / 2);
        }
    }
}