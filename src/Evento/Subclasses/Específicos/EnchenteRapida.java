package Evento.Subclasses.Específicos;

import Evento.Subclasses.EventoClimatico;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

import javax.swing.*;

public class EnchenteRapida extends EventoClimatico {

    public EnchenteRapida() {
        super("Enchente Rápida", "As águas calmas do lago transbordam repentinamente, alagando as margens e tornando o terreno perigoso.", 0.75, "energia e velocidade", "Lago/Rio", "Enchente", 2, "O terreno alagado dificulta a locomoção e pode arrastar itens leves");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo de clima: Enchente");
        System.out.println("Duração: " + getDuracao() + " turno/s");
        System.out.println("Efeito: " + getEfeitoNoAmbiente());

        jogador.diminuirEnergia(7); // Ajustado
        jogador.setVelocidade(jogador.getVelocidade() - 2); // Mantido, assumindo que não há diminuirVelocidade

        System.out.println("Você perdeu 7 de energia e sua velocidade foi reduzida por causa da enchente!");
    }
    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        // Supondo que o nome do ambiente é "Lago/Rio" ou similar.
        // Se sua classe Ambiente se chama LagoRio, seria: ambiente instanceof LagoRio
        return ambiente.getNome().equalsIgnoreCase("LagoRio") || ambiente.getNome().equalsIgnoreCase("Lago/Rio");
    }

    //Interface
    @Override
    public void executarInterface(Personagem jogador, Ambiente local, JTextArea areaLog) {
        areaLog.append("🌊 Evento: " + getNomeEvento() + "\n");
        areaLog.append(getDescricao() + "\n");
        areaLog.append("Tipo de clima: Enchente\n");
        areaLog.append("Duração: " + getDuracao() + " turno/s\n");
        areaLog.append("Efeito: " + getEfeitoNoAmbiente() + "\n");

        jogador.diminuirEnergia(7); // Ajustado
        jogador.setVelocidade(jogador.getVelocidade() - 2);

        areaLog.append("Você perdeu 7 de energia e sua velocidade foi reduzida por causa da enchente!\n");
    }
}