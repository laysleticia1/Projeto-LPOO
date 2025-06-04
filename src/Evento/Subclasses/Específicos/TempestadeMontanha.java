package Evento.Subclasses.Específicos;

import Evento.Subclasses.EventoClimatico;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

import javax.swing.*;

public class TempestadeMontanha extends EventoClimatico {

    public TempestadeMontanha() {
        super("Tempestade nas Alturas", "Nuvens escuras encobrem o topo da montanha. Uma tempestade repentina começa, com raios e ventos fortes.", 1, "Redução de energia e visibilidade", "Montanha", "Tempestade elétrica", 3, "Dificulta locomoção e ações físicas");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo: Tempestade elétrica");
        System.out.println("Duração: 3 turnos");
        System.out.println("Efeito: Dificulta locomoção e ações físicas");

        jogador.setEnergia(jogador.getEnergia() - 10);
        System.out.println("O personagem perdeu 10 de energia.");
    }
    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente.getNome().equalsIgnoreCase("Montanha");
    }

    //Interface
    public void executarInterface(Personagem jogador, Ambiente local, JTextArea areaLog) {
        areaLog.append("Evento: " + getNomeEvento() + "\n");
        areaLog.append(getDescricao() + "\n");
        areaLog.append("Tipo: Tempestade elétrica\n");
        areaLog.append("Duração: 3 turnos\n");
        areaLog.append("Efeito: Dificulta locomoção e ações físicas\n");

        jogador.setEnergia(jogador.getEnergia() - 10);
        areaLog.append("Você perdeu 10 de energia devido à tempestade nas montanhas.\n");
    }
}
