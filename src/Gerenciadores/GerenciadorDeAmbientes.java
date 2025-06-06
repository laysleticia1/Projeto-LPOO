package Gerenciadores;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import java.util.ArrayList;
import Excecoes.AmbienteInacessivelException;

import javax.swing.*;

public class GerenciadorDeAmbientes {
    private ArrayList<Ambiente> ambientesDisponiveis = new ArrayList<>();
    private ArrayList<String> historicoDeMovimentacao = new ArrayList<>();
    private Ambiente ambienteAtual;

    public void adicionarAmbiente(Ambiente ambiente) {
        ambientesDisponiveis.add(ambiente);
    }

    public void mudarAmbiente(Personagem jogador, Ambiente novoAmbiente) throws AmbienteInacessivelException {
        if (jogador.getAmbienteAtual() != null && jogador.getAmbienteAtual().getNome().equals(novoAmbiente.getNome())) {
            System.out.println("Você já está nesse ambiente!");
            return;
        }
        if (!novoAmbiente.estaAcessivel()){
            throw new AmbienteInacessivelException("O ambiente " + novoAmbiente.getNome() + " está inacessível no momento");
        }

        jogador.moverParaAmbiente(novoAmbiente);
        this.ambienteAtual = novoAmbiente; // Atualiza o ambienteAtual ao mudar
        historicoDeMovimentacao.add(novoAmbiente.getNome());
        System.out.println("Descrição: " + novoAmbiente.getDescricao());
        System.out.println("Energia restante após se mover: " + jogador.getEnergia());
    }

    public void mostrarHistorico() {
        System.out.println("\nHistórico de movimentação:");
        for (String nome : historicoDeMovimentacao) {
            System.out.println("- " + nome);
        }
    }

    public void mudarAmbienteInterface(Personagem jogador, Ambiente novoAmbiente, JTextArea areaLog) throws AmbienteInacessivelException {
        if (jogador.getAmbienteAtual() != null && jogador.getAmbienteAtual().getNome().equals(novoAmbiente.getNome())) {
            areaLog.append("Você já está nesse ambiente!\n");
            return;
        }

        if (!novoAmbiente.estaAcessivel()) {
            throw new AmbienteInacessivelException("O ambiente " + novoAmbiente.getNome() + " está inacessível no momento");
        }

        jogador.moverParaAmbiente(novoAmbiente);
        this.ambienteAtual = novoAmbiente; // Atualiza o ambienteAtual ao mudar
        historicoDeMovimentacao.add(novoAmbiente.getNome());

        areaLog.append("Descrição: " + novoAmbiente.getDescricao() + "\n");
        areaLog.append("Energia restante após se mover: " + jogador.getEnergia() + "\n");
    }

    public void mostrarHistoricoInterface(JTextArea areaLog) {
        areaLog.append("\nHistórico de movimentação:\n");
        for (String nome : historicoDeMovimentacao) {
            areaLog.append("- " + nome + "\n");
        }
    }

    public ArrayList<Ambiente> getAmbientes() {
        return ambientesDisponiveis;
    }

    public void registrarAmbienteInicial(Ambiente ambiente){
        if (ambiente != null) {
            this.ambienteAtual = ambiente; // Define o ambiente atual inicial
            historicoDeMovimentacao.add(ambiente.getNome());
        }
    }

    public void listarAmbientesNumerados() {
        if (this.ambientesDisponiveis == null || this.ambientesDisponiveis.isEmpty()) {
            System.out.println("Nenhum ambiente disponível para listar.");
            return;
        }
        System.out.println("Ambientes disponíveis:");
        for (int i = 0; i < this.ambientesDisponiveis.size(); i++) {
            Ambiente ambiente = this.ambientesDisponiveis.get(i);
            if (ambiente != null) {
                System.out.println((i + 1) + " - " + ambiente.getNome());
            }
        }
    }

    public Ambiente getAmbientePorIndice(int indice) {

        if (this.ambientesDisponiveis == null || indice < 0 || indice >= this.ambientesDisponiveis.size()) {
            System.err.println("Índice de ambiente inválido: " + indice); // Adicionado um log de erro
            return null;
        }
        return this.ambientesDisponiveis.get(indice);
    }

    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }
}

