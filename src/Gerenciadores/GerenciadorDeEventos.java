package Gerenciadores;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

import java.util.ArrayList;
import java.util.Random;

public class GerenciadorDeEventos {
    private ArrayList<Evento> eventosDisponiveis = new ArrayList<>();
    private ArrayList<String> historicoDeEventos = new ArrayList<>();
    private Random sorteador = new Random();

    public void adicionarEvento(Evento evento) {
        eventosDisponiveis.add(evento);
    }

    public void aplicarEventoAleatorio(Personagem personagem) {
        Ambiente ambienteAtual = personagem.getAmbienteAtual();
        ArrayList<Evento> eventosCompativeis = new ArrayList<>();

        for (Evento evento : eventosDisponiveis) {
            if (evento.podeOcorrerNoAmbiente(ambienteAtual)) {
                eventosCompativeis.add(evento);
            }
        }

        if (eventosCompativeis.isEmpty()) {
            System.out.println("Nenhum evento ocorreu neste turno.");
            return;
        }

        Evento eventoSelecionado = eventosCompativeis.get(sorteador.nextInt(eventosCompativeis.size()));

        eventoSelecionado.executar(personagem, ambienteAtual);
        historicoDeEventos.add(eventoSelecionado.getNomeEvento());
    }


    public void mostrarHistoricoDeEventos() {
        System.out.println("Histórico de eventos:");
        if (historicoDeEventos.isEmpty()) {
            System.out.println("Nenhum evento ocorreu até agora.");
        } else {
            for (String nome : historicoDeEventos) {
                System.out.println("- " + nome);
            }
        }
    }
}
