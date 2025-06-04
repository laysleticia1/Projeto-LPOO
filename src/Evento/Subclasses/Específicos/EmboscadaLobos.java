package Evento.Subclasses.Específicos;

import Criatura.Subclasses.Lobo;
import Evento.Subclasses.EventoCriatura;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

import javax.swing.*;

public class EmboscadaLobos extends EventoCriatura {

    public EmboscadaLobos() {
        // Para new Lobo() funcionar, a classe Lobo deve existir em Criatura.Subclasses
        // e ter um construtor que não requer argumentos, ou os argumentos corretos devem ser passados.
        // Se Lobo tiver apenas o construtor (String tipo, String nivelPerigo, String acoes),
        // você precisará fazer: new Lobo("Lobo Comum", "Médio", "Uivar, Morder, Cercar") por exemplo.
        // Assumindo que Lobo() tem um construtor padrão que define esses valores:
        super("Emboscada de Lobos", "Uma alcateia de lobos famintos salta dos arbustos, cercando você.", 0.6, "vida e sanidade", "Floresta", new Lobo(), 3, "Lutar, fugir ou se esconder");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        // A lógica de interação com a criatura (lobos) já está na superclasse EventoCriatura.
        // Se esta emboscada tem uma mecânica *diferente* da EventoCriatura genérica,
        // essa lógica específica entraria aqui.
        // Caso contrário, se a interação é a mesma, você pode chamar super.executar()
        // ou replicar/modificar a lógica de interação aqui.
        // A implementação original apenas aplicava dano direto, o que é mais simples:
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
        // Similar ao executar(), se a interação da superclasse EventoCriatura for desejada,
        // chame super.executarInterface(jogador, local, areaLog);
        // Se for um resultado direto:
        areaLog.append("Criatura: Lobos famintos\n");
        jogador.diminuirVida(12);
        jogador.diminuirSanidade(5); // Ajustado
        areaLog.append("Você recebeu 12 de dano e perdeu 5 de sanidade.\n");
    }
}
