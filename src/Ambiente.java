public abstract class Ambiente {
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;
    private String [] recursosDisponiveis;
    private double probabilidadeEventos;
    private String condicaoClimatica;

    String getNome(){
        return nome;
    }
    void setNome(String nomeAmbiente){
        this.nome = nomeAmbiente;
    }
    String getDescricao(){
        return descricao;
    }
    void setDescricao(String descricaoAmbiente){
        this.descricao = descricaoAmbiente;
    }
    int getDificuldadeExploracao(){
        return dificuldadeExploracao;
    }
    void setDificuldadeExploracao(int dificuldade){
        this.dificuldadeExploracao = dificuldade;
    }
    String[] getRecursosDisponiveis(){
        return recursosDisponiveis;
    }
    void setRecursosDisponiveis(String[] recursosDisponiveis){
        this.recursosDisponiveis = recursosDisponiveis;
    }
    double getProbabilidadeEventos(){
        return probabilidadeEventos;
    }
    void setProbabilidadeEventos(double probabilidade){
        this.probabilidadeEventos = probabilidade;
    }
    String getCondicaoClimatica(){
        return condicaoClimatica;
    }
    void setCondicaoClimatica(String condicao){
        this.condicaoClimatica = condicao;
    }

    void explorar(Personagem jogador){
    }
    void gerarEvento(){
    }
    void modificarClima(){
    }
}
