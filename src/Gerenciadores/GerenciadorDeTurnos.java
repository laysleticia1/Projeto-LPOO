package Gerenciadores;

import Excecoes.FomeSedeSanidadeException;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
import Evento.Superclasse.Evento;
import Evento.Subclasses.EventoClimatico;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class GerenciadorDeTurnos {
    private int turnoAtual;
    private final GerenciadorDeEventos gerenciadorDeEventos;
    private int nivelAtual;
    private int turnosParaProximoNivel;
    private final List<Evento> eventosAtivos;
    private final int turnoMaximo = 50;
    private final int nivelMaximo = 10;

    public GerenciadorDeTurnos(GerenciadorDeEventos gerenciadorDeEventos) {
        this.turnoAtual = 1;
        this.gerenciadorDeEventos = gerenciadorDeEventos;
        this.nivelAtual = 1;
        this.turnosParaProximoNivel = 5;
        this.eventosAtivos = new ArrayList<>();
    }

    public boolean executarTurno(Personagem personagem, boolean avancarTurno) {
        if (personagem == null) {
            System.err.println("GerenciadorDeTurnos ERRO: Personagem nulo.");
            return false;
        }
        if (!personagem.estaVivo()) {
            System.out.println("Fim de jogo: Personagem já iniciou o turno sem vida.");
            return false;
        }
        if (!avancarTurno) return true;

        try {
            personagem.verificarFomeSedeSanidade();
            if (!personagem.estaVivo()) {
                System.out.println(personagem.getNome() + " não resistiu às condições básicas (fome/sede/sanidade) no início do turno.");
                return false;
            }

            atualizarEventosAtivos();

            if (avancarTurno) {
                turnoAtual++;
                System.out.println("\n--- Turno " + turnoAtual + " ---");
            }

            if (turnoAtual > turnosParaProximoNivel && nivelAtual < nivelMaximo) {
                subirNivel(personagem);
            }

            if (verificarVitoria(personagem)) {
                return false;
            }

            if (!personagem.estaVivo()) {
                System.out.println(personagem.getNome() + " não sobreviveu aos eventos ou condições deste turno.");
                return false;
            }

            return true;

        } catch (FomeSedeSanidadeException e) {
            System.out.println("☠️ " + e.getMessage());
            return false;
        }
    }

    private void aplicarEventoAleatorio(Personagem personagem) {
        if (gerenciadorDeEventos != null && personagem != null && personagem.getAmbienteAtual() != null) {
            gerenciadorDeEventos.aplicarEventoAleatorioPorAmbiente(personagem);
        }
    }

    private void atualizarEventosAtivos() {
        List<Evento> eventosParaRemover = new ArrayList<>();
        for (Evento evento : eventosAtivos) {
            if (evento instanceof EventoClimatico eventoClimatico) {
                eventoClimatico.diminuirDuracao();
                if (eventoClimatico.getDuracao() <= 0) {
                    eventosParaRemover.add(evento);
                    System.out.println("O evento climático '" + eventoClimatico.getNomeEvento() + "' terminou.");
                }
            }
        }
        eventosAtivos.removeAll(eventosParaRemover);
    }

    private void subirNivel(Personagem personagem) {
        nivelAtual++;
        turnosParaProximoNivel += nivelAtual * 5;
        System.out.println("\n🎉 PARABÉNS! Você subiu para o nível " + nivelAtual + "!");
        if (personagem != null) {
            personagem.setNivel(nivelAtual);
        }
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

    public int getTurnoAtual() {
        return turnoAtual;
    }
    public int getNivelAtual() {
        return nivelAtual;
    }

    public boolean executarTurnoInterface(Personagem personagem, boolean avancarTurno, JTextArea areaLog) {
        if (personagem == null || areaLog == null) {
            if (areaLog != null && personagem == null) areaLog.append("ERRO: Personagem nulo no GerenciadorDeTurnos (interface).\n");
            else if (personagem != null && areaLog == null) System.err.println("ERRO: areaLog nula no GerenciadorDeTurnos (interface).");
            return false;
        }
        if (!personagem.estaVivo()) {
            areaLog.append("☠️ Fim de jogo: Personagem já iniciou o turno sem vida (interface).\n");
            return false;
        }
        if (!avancarTurno) return true;

        try {
            personagem.verificarFomeSedeSanidadeInterface(areaLog);
            if (!personagem.estaVivo()) {
                areaLog.append("☠️ " + personagem.getNome() + " não resistiu às condições básicas (fome/sede/sanidade) no início do turno (interface).\n");
                return false;
            }

            atualizarEventosAtivosInterface(areaLog);

            if (avancarTurno) {
                turnoAtual++;
                areaLog.append("\n--- Turno " + turnoAtual + " ---\n");
            }

            if (turnoAtual > turnosParaProximoNivel && nivelAtual < nivelMaximo) {
                subirNivelInterface(personagem, areaLog);
            }

            if (verificarVitoriaInterface(personagem, areaLog)) {
                areaLog.append("✨ Fim do jogo (Vitória).\n");
                return false;
            }

            if (!personagem.estaVivo()) {
                areaLog.append("☠️ " + personagem.getNome() + " não sobreviveu aos eventos ou condições deste turno (interface).\n");
                return false;
            }

            return true;

        } catch (FomeSedeSanidadeException e) {
            areaLog.append("☠️ " + e.getMessage() + "\n");
            return false;
        }
    }
    private void subirNivelInterface(Personagem personagem, JTextArea areaLog) {
        nivelAtual++;
        turnosParaProximoNivel += nivelAtual * 5;
        areaLog.append("\n🎉 PARABÉNS! Você subiu para o nível " + nivelAtual + "!\n");
        if (personagem != null) {
            personagem.setNivel(nivelAtual);
        }
    }

    private boolean verificarVitoriaInterface(Personagem jogador, JTextArea areaLog) {
        if (turnoAtual >= turnoMaximo) {
            areaLog.append("\n🎉 Parabéns! Você sobreviveu até o turno " + turnoMaximo + "!\n");
            return true;
        } else if (nivelAtual >= nivelMaximo) {
            areaLog.append("\n🏆 Vitória! Você alcançou o nível máximo de sobrevivência: " + nivelMaximo + "\n");
            return true;
        }
        return false;
    }

    private void atualizarEventosAtivosInterface(JTextArea areaLog) {
        List<Evento> eventosParaRemover = new ArrayList<>();
        for (Evento evento : eventosAtivos) {
            if (evento instanceof EventoClimatico eventoClimatico) {
                eventoClimatico.diminuirDuracao();
                if (eventoClimatico.getDuracao() <= 0) {
                    eventosParaRemover.add(evento);
                    areaLog.append("O evento climático '" + eventoClimatico.getNomeEvento() + "' terminou.\n");
                }
            }
        }
        eventosAtivos.removeAll(eventosParaRemover);
    }

    public boolean verificarStatusCriticoInterface(Personagem personagem, JTextArea areaLog) {
        if (personagem == null || areaLog == null) return false;
        try {
            personagem.verificarFomeSedeSanidadeInterface(areaLog);
            if (!personagem.estaVivo()) {
                areaLog.append("☠️ O personagem não está mais vivo após verificar status!\n");
                return false;
            }
            return true;
        } catch (FomeSedeSanidadeException e) {
            areaLog.append("☠️ " + e.getMessage() + "\n");
            return false;
        }
    }
}
