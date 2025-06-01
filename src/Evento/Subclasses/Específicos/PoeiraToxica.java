package Evento.Subclasses.Específicos;

import Evento.Subclasses.EventoDoencaFerimento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

public class PoeiraToxica extends EventoDoencaFerimento {
    public PoeiraToxica() {
        super(
                "Poeira Tóxica Ancestral",
                "Ao entrar em uma sala selada das ruínas, uma nuvem de poeira densa sobe e entra pelas vias respiratórias.",
                0.4,
                "vida",
                "Ruinas",
                "Respiratória",
                "Uso de máscara ou repouso"
        );
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Condição: Intoxicação respiratória");
        System.out.println("Sintomas: Tontura, náusea e perda de foco");
        System.out.println("Cura: Uso de máscara ou repouso");

        jogador.setVida(jogador.getVida() - 10);
        jogador.setSanidade(jogador.getSanidade() - 5);
        System.out.println("O personagem perdeu 10 de vida e 5 de sanidade.");
    }
    @Override
    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        return ambiente.getNome().equalsIgnoreCase("Ruínas");
    }

}
