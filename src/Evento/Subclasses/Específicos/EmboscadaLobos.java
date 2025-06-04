package Evento.Subclasses.Específicos;

import Criatura.Subclasses.Lobo;
import Evento.Subclasses.EventoCriatura;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

import javax.swing.*;

public class EmboscadaLobos extends EventoCriatura {

    public EmboscadaLobos() {

        super("Emboscada de Lobos", "Uma alcateia de lobos famintos salta dos arbustos, cercando você.", 0.6, "vida e sanidade", "Floresta", new Lobo(), 3, "Lutar, fugir ou se esconder");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());

        System.out.println("Criatura: Lobos famintos");
        jogador.diminuirVida(12);
        jogador.diminuirSanidade(5); // Ajustado para usar o método de Personagem
        System.out.println("Você recebeu 12 de dano e perdeu 5 de sanidade.");
    }

    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente.getNome().equalsIgnoreCase("Floresta");
    }

    //Interface
    @Override
    public void executarInterface(Personagem jogador, Ambiente local, JTextArea areaLog) {
        areaLog.append("🐺 Evento: " + getNomeEvento() + "\n");
        areaLog.append(getDescricao() + "\n");

        areaLog.append("Criatura: Lobos famintos\n");
        jogador.diminuirVida(12);
        jogador.diminuirSanidade(5); // Ajustado
        areaLog.append("Você recebeu 12 de dano e perdeu 5 de sanidade.\n");
    }
}
