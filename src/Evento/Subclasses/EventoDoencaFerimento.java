package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
import Ambiente.Subclasses.*;

public class EventoDoencaFerimento extends Evento {
    private String tipoDeCondicao;
    private String curaDisponivel;

    public EventoDoencaFerimento(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, String tipoDeCondicao, String curaDisponivel) {
        super(nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
        this.tipoDeCondicao = tipoDeCondicao;
        this.curaDisponivel = curaDisponivel;
    }

    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento de Doença/Ferimento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Condição: " + getTipoDeCondicao());
        System.out.println("Cura: " + getCuraDisponivel());
        switch (getImpacto().trim().toLowerCase()) {
            case "vida":
                jogador.diminuirVida(15);
                System.out.println("Você perdeu 15 de vida!");
                break;
            case "sanidade":
                jogador.diminuirSanidade(15);
                System.out.println("Você perdeu 15 de sanidade!");
                break;
            case "energia":
                jogador.diminuirEnergia(15);
                System.out.println("Você perdeu 15 de energia!");
                break;
        }
    }

    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        // Pode ocorrer em Montanha ou Floresta, por exemplo
        return ambiente instanceof Montanha || ambiente instanceof Floresta;
    }

    //Getters and Setters

    public String getCuraDisponivel() {
        return curaDisponivel;
    }

    public void setCuraDisponivel(String curaDisponivel) {
        this.curaDisponivel = curaDisponivel;
    }

    public String getTipoDeCondicao() {
        return tipoDeCondicao;
    }

    public void setTipoDeCondicao(String tipoDeCondicao) {
        this.tipoDeCondicao = tipoDeCondicao;
    }
}
