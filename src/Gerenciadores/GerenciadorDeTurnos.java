package Gerenciadores;

import Excecoes.FomeSedeSanidadeException;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
import Evento.Superclasse.Evento;
import Evento.Subclasses.EventoClimatico;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTurnos {
    private int turnoAtual;
    private final GerenciadorDeEventos gerenciadorDeEventos;
    private int nivelAtual;
    private int turnosParaProximoNivel;
    private final List<Evento> eventosAtivos;
    private final int turnoMaximo = 50; // ou outro valor desejado
    private final int nivelMaximo = 10;

    public GerenciadorDeTurnos(GerenciadorDeEventos gerenciadorDeEventos) {
        this.turnoAtual = 1;
        this.gerenciadorDeEventos = gerenciadorDeEventos;
        this.nivelAtual = 1;
        this.turnosParaProximoNivel = 5; // Primeira meta
        this.eventosAtivos = new ArrayList<>();
    }

    public boolean executarTurno(Personagem personagem, boolean avancarTurno) {
        if (!avancarTurno) return true;

        try {
            // Verifica morte após consumo
            personagem.verificarFomeSedeSanidade();

            // Atualiza eventos climáticos ativos
            atualizarEventosAtivos();

            // Avança turno
            turnoAtual++;

            // Verifica subida de nível
            if (turnoAtual > turnosParaProximoNivel) {
                subirNivel();
            }

            // ✅ Verifica vitória
            if (verificarVitoria(personagem)) {
                System.out.println("✨ Fim do jogo.");
                return false;
            }

            return true;

        } catch (FomeSedeSanidadeException e) {
            System.out.println(e.getMessage());
            System.out.println("☠️ Fim de jogo.");
            return false;
        }
    }

    private void aplicarEventoAleatorio(Personagem personagem) {
        gerenciadorDeEventos.aplicarEventoAleatorioPorAmbiente(personagem);
    }

    private void atualizarEventosAtivos() {
        List<Evento> eventosParaRemover = new ArrayList<>();
        for (Evento evento : eventosAtivos) {
            if (evento instanceof EventoClimatico eventoClimatico) {
                eventoClimatico.diminuirDuracao();
                if (eventoClimatico.getDuracao() <= 0) {
                    eventosParaRemover.add(evento);
                }
            }
        }
        eventosAtivos.removeAll(eventosParaRemover);
    }

    private void subirNivel() {
        nivelAtual++;
        turnosParaProximoNivel += nivelAtual * 5; // Ex: 5, 10, 15...
        System.out.println("\n🎉 PARABÉNS! Você subiu para o nível " + nivelAtual + "!");
    }

    private boolean verificarVitoria(Personagem jogador) {
        if (turnoAtual >= turnoMaximo) {
            System.out.println("\n🎉 Parabéns! Você sobreviveu até o turno " + turnoMaximo + "!");
            return true;
        } else if (nivelAtual >= nivelMaximo) {
            System.out.println("\n🏆 Vitória! Você alcançou o nível máximo de sobrevivência: " + nivelMaximo);
            return true;
        }
        return false;
    }

    // Getters
    public int getTurnoAtual() {
        return turnoAtual;
    }

    public int getNivelAtual() {
        return nivelAtual;
    }
}
