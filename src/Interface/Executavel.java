package Interface;

import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;

public interface Executavel {
    void executar(Personagem jogador, Ambiente local);
}