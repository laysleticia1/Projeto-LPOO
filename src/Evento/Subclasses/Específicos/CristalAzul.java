package Evento.Subclasses.Específicos;

import Evento.Subclasses.EventoDescoberta;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

public class CristalAzul extends EventoDescoberta {

    public CristalAzul() {
        super(
                "Descoberta do Cristal Azul",
                "Um brilho intenso entre as rochas revela um raro cristal mágico embutido na parede.",
                0.5,
                "Pode ser usado como luz ou moeda de troca",
                "Caverna",
                "Descoberta mineral",
                "Cristal mágico de luz azulada",
                "Necessário ferramenta para extração"
        );
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo: Descoberta mineral");
        System.out.println("Recurso: Cristal mágico de luz azulada");
        System.out.println("Condição: Necessário ferramenta para extração");
        System.out.println("Impacto: " + getImpacto());
        // Você pode adicionar lógica futura para adicionar item ao inventário
    }
}
