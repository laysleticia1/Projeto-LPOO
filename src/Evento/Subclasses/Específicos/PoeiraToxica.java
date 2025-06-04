package Evento.Subclasses.Específicos;

import Evento.Subclasses.EventoDoencaFerimento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

import javax.swing.*;

public class PoeiraToxica extends EventoDoencaFerimento {
    public PoeiraToxica() {
        super("Poeira Tóxica Ancestral", "Ao entrar em uma sala selada das ruínas, uma nuvem de poeira densa sobe e entra pelas vias respiratórias.", 0.4, "vida e sanidade", "Ruinas", "Respiratória Aguda", "Uso de máscara ou repouso prolongado");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Condição: " + getTipoDeCondicao());
        System.out.println("Sintomas: Tontura, náusea e perda de foco");
        System.out.println("Cura: " + getCuraDisponivel());

        jogador.diminuirVida(10);    // Ajustado
        jogador.diminuirSanidade(5); // Ajustado
        System.out.println("O personagem perdeu 10 de vida e 5 de sanidade.");
    }
    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente.getNome().equalsIgnoreCase("Ruinas");
    }

    //Interface
    @Override
    public void executarInterface(Personagem jogador, Ambiente local, JTextArea areaLog) {
        areaLog.append("😷 Evento: " + getNomeEvento() + "\n");
        areaLog.append(getDescricao() + "\n");
        areaLog.append("Condição: " + getTipoDeCondicao() + "\n");
        areaLog.append("Sintomas: Tontura, náusea e perda de foco\n");
        areaLog.append("Cura: " + getCuraDisponivel() + "\n");

        jogador.diminuirVida(10);    // Ajustado
        jogador.diminuirSanidade(5); // Ajustado

        areaLog.append("Você perdeu 10 de vida e 5 de sanidade devido à exposição à poeira tóxica.\n");
    }
}
