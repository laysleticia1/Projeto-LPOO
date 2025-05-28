package Gerenciadores;

import Excecoes.FomeSedeSanidadeException;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

public class GerenciadorDeTurnos {
    private int turnoAtual;
    private GerenciadorDeEventos gerenciadorDeEventos;

    public GerenciadorDeTurnos(GerenciadorDeEventos gerenciadorDeEventos) {
        this.turnoAtual = 1;
        this.gerenciadorDeEventos = gerenciadorDeEventos;
    }

    public boolean executarTurno(Personagem personagem) {
        System.out.println("\n===== TURNO " + turnoAtual + " =====");

        try{
            //Consumo de recursos
            personagem.consumirRecursosBasicos();

            //Verifica morte após consumo
            personagem.verificarFomeSedeSanidade();

            //Atualiza ambiente
            atualizarAmbiente(personagem.getAmbienteAtual());

            //Aplica evento aleatório
            aplicarEventoAleatorio(personagem);

            turnoAtual++;
            return true;
        } catch (FomeSedeSanidadeException e) {
            System.out.println("" + e.getMessage());
            System.out.println("Fim de jogo.");
            return false;
        }
    }

    private void aplicarEventoAleatorio(Personagem personagem) {
        System.out.println("Verificando evento...");
        gerenciadorDeEventos.aplicarEventoAleatorio(personagem);
    }

    private void atualizarAmbiente(Ambiente ambiente) {
        System.out.println("Atualizando ambiente atual...");
        System.out.println("Clima: " + ambiente.getCondicaoClimatica());
        // Aqui você pode adicionar efeitos futuros do clima
    }

    public int getTurnoAtual() {
        return turnoAtual;
    }
}