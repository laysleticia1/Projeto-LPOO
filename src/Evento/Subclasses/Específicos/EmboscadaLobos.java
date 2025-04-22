package Evento.Subclasses.Específicos;

import Evento.Subclasses.EventoCriatura;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

public class EmboscadaLobos extends EventoCriatura {

    public EmboscadaLobos() {
        super(
                "Emboscada de Lobos",
                "Uma alcateia de lobos famintos salta dos arbustos, cercando o jogador.",
                0.6,
                "Causa dano e reduz sanidade",
                "Floresta",
                "Lobos famintos",
                3,
                "Lutar, fugir ou se esconder"
        );
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Criatura: Lobos famintos");
        System.out.println("Impacto: " + getImpacto());

        jogador.receberDano(12);
        jogador.setSanidade(jogador.getSanidade() - 5);
        System.out.println("O personagem recebeu 12 de dano e perdeu 5 de sanidade.");
    }
}
