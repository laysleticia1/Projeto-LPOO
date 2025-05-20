package Gerenciadores;

import Ambiente.Superclasse.Ambiente;
import Personagem.Superclasse.Personagem;
import java.util.ArrayList;
import Excecoes.AmbienteInacessivelException;

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

        jogador.setAmbienteAtual(novoAmbiente);
        historicoDeMovimentacao.add(novoAmbiente.getNome());
        System.out.println("Você se moveu para: " + novoAmbiente.getNome());
        System.out.println("Descrição: " + novoAmbiente.getDescricao());
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

