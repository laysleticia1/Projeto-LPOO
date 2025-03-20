public class Evento {
    private String nomeEvento;
    private String descricao;
    private double probabilidadeDeOcorrencia;
    private String impacto;
    private String condicaoDeAtivacao;

    String getNomeEvento () {
        return nomeEvento;
    }
    void setNomeEvento (String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
    String getDescricao () {
        return descricao;
    }
    void setDescricao (String descricao) {
        this.descricao = descricao;
    }
    double getProbabilidadeDeOcorrencia () {
        return probabilidadeDeOcorrencia;
    }
    void setProbabilidadeDeOcorrencia (double probabilidadeDeOcorrencia) {
        this.probabilidadeDeOcorrencia = probabilidadeDeOcorrencia;
    }
    String getImpacto () {
        return impacto;
    }
    void setImpacto (String impacto) {
        this.impacto = impacto;
    }
    String getCondicaoDeAtivacao () {
        return condicaoDeAtivacao;
    }
    void setCondicaoDeAtivacao (String condicaoDeAtivacao) {
        this.condicaoDeAtivacao = condicaoDeAtivacao;
    }

    void executar (Personagem jogador, Ambiente local) {
    }

    // Subclasse EventoClimatico
    private String clima;
    private int duracao;
    private String efeitoNoAmbiente;

    // Subclasse EventoDescoberta
    private String tipoDeDescoberta;
    private String recursosEncontrados;
    private String condicaoEspecial;

    // Subclasse EventoDoencaFerimento
    private String tipoDeCondicao;
    private String impacto;
    private String curaDisponivel;

    // Classe GerenciadorDeEventos
    private String [] listaDeEventosPossiveis;
    private double probabilidadeDeOcorrencia;
    private String historicoDeEventos;

    void sortearEvento (Ambiente local) {
    }
    void aplicarEvento (Personagem jogador) {
    }
    void removerEvento (Evento evento) {
    }

}

