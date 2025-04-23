package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

public abstract class EventoDoencaFerimento extends Evento {
    private String tipoDeCondicao;
    private String impactoCondicao;
    private String curaDisponivel;

    public EventoDoencaFerimento(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, String tipoDeCondicao, String impactoSecundario, String curaDisponivel) {
        super(nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento de Doença/Ferimento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Condição: " + tipoDeCondicao );
        System.out.println("Impacto: " + impactoCondicao);
        System.out.println("Cura: " + curaDisponivel);
        System.out.println("Impacto geral: " + getImpacto());
    }

}
