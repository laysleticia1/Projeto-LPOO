package Evento.Subclasses.Específicos;

import Evento.Subclasses.EventoClimatico;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

public class EnchenteRapida extends EventoClimatico {

    public EnchenteRapida() {
        super(
                "Enchente Rápida",
                "As águas calmas do lago transbordam repentinamente, alagando as margens e tornando o terreno perigoso.",
                0.75,
                "Reduz energia e velocidade",
                "Lago/Rio",
                "Enchente",
                2,
                "O terreno alagado dificulta a locomoção e pode arrastar itens leves"
        );
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo de clima: Enchente");
        System.out.println("Duração: " + getDuracao() + " turnos");
        System.out.println("Efeito: " + getEfeitoNoAmbiente());
        System.out.println("Impacto: " + getImpacto());

        jogador.setEnergia(jogador.getEnergia() - 7);
        jogador.setVelocidade(jogador.getVelocidade() - 2);

        System.out.println("⚠️ Você perdeu 7 de energia e 2 de velocidade por causa da enchente!");
    }
    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente.getNome().equalsIgnoreCase("Lago/Rio");
    }

}
