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
        // Pode chamar super.executar() se quiser aplicar o impacto definido no construtor via switch da superclasse
        // super.executar(jogador, local);
        // Ou ter uma lógica totalmente customizada aqui:
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo: " + getTipo());
        System.out.println("Duração: " + getDuracao() + " turnos");
        System.out.println("Efeito: " + getEfeitoNoAmbiente());

        jogador.diminuirEnergia(10); // Ajustado
        // Adicionar outros efeitos, como na visibilidade, se aplicável à mecânica do jogo
        System.out.println("O personagem perdeu 10 de energia devido à tempestade.");
    }
    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente.getNome().equalsIgnoreCase("Montanha");
    }

    //Interface
    @Override
    public void executarInterface(Personagem jogador, Ambiente local, JTextArea areaLog) {
        // Pode chamar super.executarInterface() se a lógica for similar
        // super.executarInterface(jogador, local, areaLog);
        // Ou customizar:
        areaLog.append("⛈️ Evento: " + getNomeEvento() + "\n");
        areaLog.append(getDescricao() + "\n");
        areaLog.append("Tipo: " + getTipo() + "\n");
        areaLog.append("Duração: " + getDuracao() + " turnos\n");
        areaLog.append("Efeito: " + getEfeitoNoAmbiente() + "\n");

        jogador.diminuirEnergia(10); // Ajustado
        areaLog.append("Você perdeu 10 de energia devido à tempestade nas montanhas.\n");
        // Adicionar log sobre redução de visibilidade se relevante para o jogador
    }
}