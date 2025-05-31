package Evento.Subclasses.Específicos;

import Evento.Subclasses.EventoClimatico;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

public class TempestadeMontanha extends EventoClimatico {

    public TempestadeMontanha() {
        super(
                "Tempestade nas Alturas",
                "Nuvens escuras encobrem o topo da montanha. Uma tempestade repentina começa, com raios e ventos fortes.",
                1,
                "Redução de energia e visibilidade",
                "Montanha",
                "Tempestade elétrica",
                3,
                "Dificulta locomoção e ações físicas"
        );
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo: Tempestade elétrica");
        System.out.println("Duração: 3 turnos");
        System.out.println("Efeito: Dificulta locomoção e ações físicas");

        jogador.setEnergia(jogador.getEnergia() - 10);
        System.out.println("O personagem perdeu 10 de energia.");
    }
    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente.getNome().equalsIgnoreCase("Montanha");
    }

}
