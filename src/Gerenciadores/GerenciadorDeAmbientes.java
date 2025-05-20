package Gerenciadores;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import java.util.ArrayList;

public class GerenciadorDeAmbientes {
    private ArrayList<Ambiente> ambientesDisponiveis = new ArrayList<>();
    private ArrayList<String> historicoDeMovimentacao = new ArrayList<>();

    public void adicionarAmbiente(Ambiente ambiente) {
        ambientesDisponiveis.add(ambiente);
    }

    public void mudarAmbiente(Personagem jogador, Ambiente novoAmbiente) {

        //Caso o jogador escolha o mesmo ambiente vai avisar, evitando movimentação inútil
        if (jogador.getAmbienteAtual() != null && jogador.getAmbienteAtual().getNome().equals(novoAmbiente.getNome())) {
            System.out.println("Você já está nesse ambiente!");
            return;
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

    //Método para a lista de ambientes disponíveis
    public ArrayList<Ambiente> getAmbientes() {
        return ambientesDisponiveis;
    }

    //Método para registrar o ambiente inicial no histórico
    public void registrarAmbienteInicial(Ambiente ambiente){
        historicoDeMovimentacao.add(ambiente.getNome());
    }
}

