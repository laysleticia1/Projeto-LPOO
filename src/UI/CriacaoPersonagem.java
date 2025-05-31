package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import Jogo.Jogo;

public class CriacaoPersonagem extends JPanel {
    private JPanel painelPrincipalCardLayout;
    private Jogo meuJogo;

    private JTextField campoNome;
    private JButton botaoConfirmarNome; // Botão para confirmar o nome

    private JLabel labelClasse; // Será habilitado depois
    private JComboBox<String> comboBoxClasse; // Será habilitado depois
    private JTextArea areaDescricaoClasse; // Será habilitado depois
    private JScrollPane scrollDescricao; // Para a área de descrição

    private JButton botaoCriarPersonagem; // Botão final para criar o personagem
    private JButton botaoVoltar;

    private String nomeConfirmado; // Para guardar o nome após a primeira confirmação

    private final Map<String, String> descricoesClasses = new HashMap<>();

    public CriacaoPersonagem(JPanel painelPrincipal, Jogo jogo) {
        this.painelPrincipalCardLayout = painelPrincipal;
        this.meuJogo = jogo;

        // Populando as descrições
        descricoesClasses.put("Rastreador", "Mestre em seguir trilhas e identificar perigos na natureza. Encontra recursos com mais facilidade.");
        descricoesClasses.put("Mecânico", "Engenhoso com ferramentas e sucata. Consegue consertar e criar dispositivos úteis para sobrevivência.");
        descricoesClasses.put("Médico", "Especialista em primeiros socorros e tratamento de ferimentos. Consegue usar ervas para criar remédios.");
        descricoesClasses.put("Sobrevivente Nato", "Resistente e adaptável. Possui habilidades instintivas para montar abrigos e fabricar itens básicos.");
        descricoesClasses.put("", "Selecione uma classe para ver a descrição.");

        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(210, 210, 200));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título da Tela
        JLabel labelTitulo = new JLabel("Criação de Personagem");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // --- Etapa 1: Nome do Personagem ---
        JLabel labelNome = new JLabel("Nome do Personagem:");
        labelNome.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelNome, gbc);

        campoNome = new JTextField(20);
        campoNome.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoNome, gbc);

        botaoConfirmarNome = new JButton("Confirmar Nome");
        botaoConfirmarNome.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 1; // Colocando na mesma coluna do campo de nome
        gbc.gridy = 2; // Linha abaixo do nome
        gbc.anchor = GridBagConstraints.EAST; // Alinhar botão à direita dentro da sua célula
        gbc.fill = GridBagConstraints.NONE;   // Botão não estica
        add(botaoConfirmarNome, gbc);
        // Resetar âncora e preenchimento para os próximos componentes
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Etapa 2: Seleção de Classe (inicialmente desabilitada) ---
        labelClasse = new JLabel("Escolha sua Classe:");
        labelClasse.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3; // Próxima linha
        add(labelClasse, gbc);

        String[] classes = {"", "Rastreador", "Mecânico", "Médico", "Sobrevivente Nato"};
        comboBoxClasse = new JComboBox<>(classes);
        comboBoxClasse.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(comboBoxClasse, gbc);

        areaDescricaoClasse = new JTextArea(5, 30);
        areaDescricaoClasse.setFont(new Font("SansSerif", Font.ITALIC, 14));
        areaDescricaoClasse.setWrapStyleWord(true);
        areaDescricaoClasse.setLineWrap(true);
        areaDescricaoClasse.setEditable(false);
        areaDescricaoClasse.setOpaque(false); // Para melhor visualização com o fundo do painel
        areaDescricaoClasse.setText(descricoesClasses.get(""));
        scrollDescricao = new JScrollPane(areaDescricaoClasse);
        gbc.gridx = 0;
        gbc.gridy = 4; // Próxima linha
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH; // Área de texto pode expandir
        gbc.weightx = 1.0; // Peso para expansão horizontal
        gbc.weighty = 1.0; // Peso para expansão vertical
        add(scrollDescricao, gbc);
        // Resetar pesos e fill para os próximos componentes
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ou NONE se não quiser que os botões estiquem

        // Inicialmente, componentes da Etapa 2 estão desabilitados
        labelClasse.setEnabled(false);
        comboBoxClasse.setEnabled(false);
        areaDescricaoClasse.setEnabled(false);
        // O JScrollPane não tem um 'setEnabled' que o desabilite visualmente da mesma forma,
        // mas desabilitar o JTextArea dentro dele já tem o efeito desejado.

        // --- Botões Finais ---
        // Usar um painel para agrupar os botões finais pode ajudar no layout
        JPanel painelBotoesFinais = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Centraliza botões com espaçamento
        painelBotoesFinais.setOpaque(false); // Faz o painel de botões transparente

        botaoCriarPersonagem = new JButton("Criar Personagem");
        botaoCriarPersonagem.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoCriarPersonagem.setPreferredSize(new Dimension(220, 50)); // Ajuste o tamanho conforme necessário
        botaoCriarPersonagem.setEnabled(false); // Inicialmente desabilitado
        painelBotoesFinais.add(botaoCriarPersonagem);

        botaoVoltar = new JButton("Voltar ao Menu");
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setPreferredSize(new Dimension(200, 50));
        painelBotoesFinais.add(botaoVoltar);

        gbc.gridx = 0;
        gbc.gridy = 5; // Próxima linha
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza o painel de botões
        gbc.fill = GridBagConstraints.NONE; // Painel de botões não estica
        add(painelBotoesFinais, gbc);


        // --- Action Listeners ---

        // Ação do Botão "Confirmar Nome"
        botaoConfirmarNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomeConfirmado = campoNome.getText().trim();
                if (nomeConfirmado.isEmpty()) {
                    JOptionPane.showMessageDialog(CriacaoPersonagem.this, // Ou apenas 'this' se estiver no contexto da classe
                            "Por favor, digite um nome para o personagem.",
                            "Nome Inválido", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Nome confirmado: desabilita campo de nome e botão de confirmar nome
                campoNome.setEnabled(false);
                botaoConfirmarNome.setEnabled(false);

                // Habilita a seleção de classe e o botão final de criar personagem
                labelClasse.setEnabled(true);
                comboBoxClasse.setEnabled(true);
                areaDescricaoClasse.setEnabled(true);
                // scrollDescricao.setEnabled(true); // Não é necessário para JScrollPane
                botaoCriarPersonagem.setEnabled(true);

                comboBoxClasse.requestFocusInWindow(); // Move o foco para a seleção de classe
            }
        });

        // Atualiza descrição quando a classe muda no ComboBox
        comboBoxClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxClasse.isEnabled()) {
                    String classeSelecionada = (String) comboBoxClasse.getSelectedItem();
                    areaDescricaoClasse.setText(descricoesClasses.getOrDefault(classeSelecionada, "Descrição não disponível."));
                }
            }
        });

        // Ação do Botão "Criar Personagem"
        botaoCriarPersonagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Nome já está em 'nomeConfirmado'
                String classeSelecionada = (String) comboBoxClasse.getSelectedItem();

                if (classeSelecionada == null || classeSelecionada.isEmpty()) {
                    JOptionPane.showMessageDialog(CriacaoPersonagem.this,
                            "Por favor, selecione uma classe para o personagem.",
                            "Classe Inválida", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Chamar método na classe Jogo para criar o personagem e configurar a partida
                boolean sucesso = meuJogo.iniciarNovaPartida(nomeConfirmado, classeSelecionada);

                if (sucesso) {
                    System.out.println("Personagem criado com sucesso! Nome: " + meuJogo.getJogador().getNome() + ", Classe: " + meuJogo.getJogador().getClasse());
                    String textoIntro = meuJogo.getTextoIntroducao();

                    // Mostra a introdução em uma caixa de diálogo
                    JTextArea textAreaIntro = new JTextArea(textoIntro);
                    textAreaIntro.setWrapStyleWord(true);
                    textAreaIntro.setLineWrap(true);
                    textAreaIntro.setEditable(false);
                    textAreaIntro.setOpaque(false);
                    textAreaIntro.setBackground(new Color(0,0,0,0));
                    JScrollPane scrollPaneIntro = new JScrollPane(textAreaIntro);
                    scrollPaneIntro.setPreferredSize(new Dimension(500, 300));
                    scrollPaneIntro.setBorder(null); // Remove a borda do JScrollPane se desejar

                    JOptionPane.showMessageDialog(CriacaoPersonagem.this, scrollPaneIntro, "Introdução", JOptionPane.INFORMATION_MESSAGE, null);

                    // Aqui você mudaria para a tela principal do jogo
                    // CardLayout cl = (CardLayout) (painelPrincipalCardLayout.getLayout());
                    // cl.show(painelPrincipalCardLayout, "PAINEL_JOGO"); // Descomente quando PAINEL_JOGO existir
                    System.out.println("A interface mudaria para PAINEL_JOGO aqui.");

                    // Opcional: Resetar este painel se o usuário puder criar outro personagem sem voltar ao menu
                    // resetarPainel();
                } else {
                    JOptionPane.showMessageDialog(CriacaoPersonagem.this,
                            "Ocorreu um erro ao criar o personagem. Verifique o console para mais detalhes.",
                            "Erro na Criação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do Botão "Voltar ao Menu"
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetarPainel(); // Chama o método para limpar e redefinir o estado do painel
                CardLayout cl = (CardLayout) (painelPrincipalCardLayout.getLayout());
                cl.show(painelPrincipalCardLayout, "MENU_INICIAL");
            }
        });
    }

    // Método para resetar o painel ao seu estado inicial
    private void resetarPainel() {
        campoNome.setText("");
        campoNome.setEnabled(true);
        botaoConfirmarNome.setEnabled(true);

        labelClasse.setEnabled(false);
        comboBoxClasse.setSelectedIndex(0); // Seleciona a opção vazia/default
        comboBoxClasse.setEnabled(false);
        areaDescricaoClasse.setText(descricoesClasses.get("")); // Descrição padrão
        areaDescricaoClasse.setEnabled(false);
        // scrollDescricao.setEnabled(false); // Não necessário para JScrollPane

        botaoCriarPersonagem.setEnabled(false);
        nomeConfirmado = null; // Limpa o nome confirmado
    }
}