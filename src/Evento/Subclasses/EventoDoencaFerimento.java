package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
import Ambiente.Subclasses.Montanha; // Exemplo de import específico
import Ambiente.Subclasses.Floresta; // Exemplo de import específico
// Adicione outros imports de Ambiente.Subclasses.* se necessário

import javax.swing.*;

public class EventoDoencaFerimento extends Evento {
    private String tipoDeCondicao; // Ex: "Respiratória", "Cutânea", "Intoxicação Alimentar"
    private String curaDisponivel; // Ex: "Antídoto", "Repouso", "Ervas Medicinais"

    public EventoDoencaFerimento(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoDeAtivacao, String tipoDeCondicao, String curaDisponivel) {
        super(nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoDeAtivacao);
        this.tipoDeCondicao = tipoDeCondicao;
        this.curaDisponivel = curaDisponivel;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento de Doença/Ferimento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Condição: " + getTipoDeCondicao());
        System.out.println("Cura sugerida: " + getCuraDisponivel());
        switch (getImpacto().trim().toLowerCase()) {
            case "vida":
                jogador.diminuirVida(15);
                System.out.println("Você perdeu 15 de vida devido a " + getNomeEvento() + "!");
                break;
            case "sanidade":
                jogador.diminuirSanidade(15);
                System.out.println("Você perdeu 15 de sanidade devido a " + getNomeEvento() + "!");
                break;
            case "energia":
                jogador.diminuirEnergia(15);
                System.out.println("Você perdeu 15 de energia devido a " + getNomeEvento() + "!");
                break;
            default:
                System.out.println("Este evento afeta: " + getImpacto());
                break;
        }
    }

    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        // Para 'ambiente instanceof Montanha' etc. funcionar, essas classes devem existir
        // e ser importadas ou estar no mesmo pacote.
        // Assumindo que estão em Ambiente.Subclasses e são importadas.
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

    //Interface
    public void executarInterface(Personagem jogador, Ambiente local, JTextArea areaLog) {
        areaLog.append("🩸 Evento de Doença/Ferimento: " + getNomeEvento() + "\n");
        areaLog.append(getDescricao() + "\n");
        areaLog.append("Condição: " + getTipoDeCondicao() + "\n");
        areaLog.append("Cura sugerida: " + getCuraDisponivel() + "\n");

        switch (getImpacto().trim().toLowerCase()) {
            case "vida":
                jogador.diminuirVida(15);
                areaLog.append("Você perdeu 15 de vida!\n");
                break;
            case "sanidade":
                jogador.diminuirSanidade(15);
                areaLog.append("Você perdeu 15 de sanidade!\n");
                break;
            case "energia":
                jogador.diminuirEnergia(15);
                areaLog.append("Você perdeu 15 de energia!\n");
                break;
            default:
                areaLog.append("Este evento afeta: " + getImpacto() + "\n");
                break;
        }
    }
}
