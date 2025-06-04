package Gerenciadores;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import java.util.ArrayList;
import Excecoes.AmbienteInacessivelException;

import javax.swing.*;

public class GerenciadorDeAmbientes {
    private ArrayList<Ambiente> ambientesDisponiveis = new ArrayList<>();
    private ArrayList<String> historicoDeMovimentacao = new ArrayList<>();

    public void adicionarAmbiente(Ambiente ambiente) {
        ambientesDisponiveis.add(ambiente);
    }

    public void mudarAmbiente(Personagem jogador, Ambiente novoAmbiente) throws AmbienteInacessivelException {

        //Caso o jogador escolha o mesmo ambiente vai avisar, evitando movimentação inútil
        if (jogador.getAmbienteAtual() != null && jogador.getAmbienteAtual().getNome().equals(novoAmbiente.getNome())) {
            System.out.println("Você já está nesse ambiente!");
            return;
        }
        //Lançamento da Exceção se não for acessível
        if (!novoAmbiente.estaAcessivel()){
            throw new AmbienteInacessivelException("O ambiente " + novoAmbiente.getNome() + " está inacessível no momento");
        }

        jogador.moverParaAmbiente(novoAmbiente);
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

    //Interface

    public void mudarAmbienteInterface(Personagem jogador, Ambiente novoAmbiente, JTextArea areaLog) throws AmbienteInacessivelException {
        if (jogador.getAmbienteAtual() != null && jogador.getAmbienteAtual().getNome().equals(novoAmbiente.getNome())) {
            areaLog.append("Você já está nesse ambiente!\n");
            return;
        }

        if (!novoAmbiente.estaAcessivel()) {
            throw new AmbienteInacessivelException("O ambiente " + novoAmbiente.getNome() + " está inacessível no momento");
        }

        jogador.moverParaAmbiente(novoAmbiente);
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
        historicoDeMovimentacao.add(ambiente.getNome());
    }
}

