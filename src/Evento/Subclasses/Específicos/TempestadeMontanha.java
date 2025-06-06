package Evento.Subclasses.Específicos;

import Evento.Subclasses.EventoClimatico;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

import javax.swing.*;

public class TempestadeMontanha extends EventoClimatico {

    public TempestadeMontanha() {
        super("Tempestade nas Alturas",
                "Nuvens escuras encobrem o topo da montanha. Uma tempestade repentina começa, com raios e ventos fortes.",
                0.5, // Probabilidade
                "energia", // Impacto primário para o switch da superclasse, se aplicável
                "Montanha", // Condição de ativação (ambiente)
                "Tempestade Elétrica Severa", // tipo específico do clima
                3, // duracao
                "Dificulta locomoção, ações físicas e reduz visibilidade drasticamente"); // efeitoNoAmbiente
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo: " + getTipo());
        System.out.println("Duração: " + getDuracao() + " turnos");
        System.out.println("Efeito: " + getEfeitoNoAmbiente());

        jogador.diminuirEnergia(10); // Ajustado
        System.out.println("O personagem perdeu 10 de energia devido à tempestade.");
    }
    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente.getNome().equalsIgnoreCase("Montanha");
    }

    //Interface
    @Override
    public void executarInterface(Personagem jogador, Ambiente local, JTextArea areaLog) {

        areaLog.append("⛈️ Evento: " + getNomeEvento() + "\n");
        areaLog.append(getDescricao() + "\n");
        areaLog.append("Tipo: " + getTipo() + "\n");
        areaLog.append("Duração: " + getDuracao() + " turnos\n");
        areaLog.append("Efeito: " + getEfeitoNoAmbiente() + "\n");

        jogador.diminuirEnergia(10); // Ajustado
        areaLog.append("Você perdeu 10 de energia devido à tempestade nas montanhas.\n");
    }
}