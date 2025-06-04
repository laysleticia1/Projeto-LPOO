package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

public class Jacare extends Criatura {

    public Jacare() {
        super("Jacaré Feroz", "Alto", "Mordida Esmagadora, Giro da Morte");
    }

    @Override
    public void atacar(Personagem alvo) {
        alvo.diminuirVida(20);
        System.out.println("Das águas escuras, um jacaré salta e crava os dentes em sua perna!");
        System.out.println("Você perdeu 20 de vida.");
    }

    @Override
    public String atacarParaUI(Personagem alvo) {
        atacar(alvo);
        return "Das águas escuras, um jacaré salta e crava os dentes em sua perna!\nVocê perdeu 20 de vida.";
    }

    @Override
    public void ataqueReduzido(Personagem jogador) {
        jogador.diminuirVida(10);
        System.out.println("Das águas escuras, um jacaré salta e crava os dentes em sua perna!");
    }

    @Override
    public String ataqueReduzidoParaUI(Personagem jogador) {
        ataqueReduzido(jogador);
        return "O jacaré tenta uma mordida rápida, mas ainda perigosa!\nVocê perdeu 10 de vida."; // Ajuste
    }

    @Override
    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Jacaré girou com sua cauda poderosa, lançando água e desorientando você.");
        jogador.diminuirEnergia(10);
        jogador.diminuirSanidade(10);
        System.out.println("Você perdeu 10 de energia e 10 de sanidade.");
    }

    @Override
    public String acaoEspecialParaUI(Personagem jogador) {
        acaoEspecial(jogador);
        return "O Jacaré girou com sua cauda poderosa, lançando água e desorientando você.\nVocê perdeu 10 de energia e 10 de sanidade.";
    }

    @Override
    public void fugir() {
        System.out.println("O jacaré mergulha silenciosamente, desaparecendo nas águas turvas.");
    }

    @Override
    public String fugirParaUI() {
        fugir();
        return "O jacaré mergulha silenciosamente, desaparecendo nas águas turvas.";
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nVocê acorda com o estalo de mandíbulas perto demais...");
        System.out.println("Um Jacaré tenta arrastá-lo durante o descanso!");
        jogador.diminuirVida(20);
        System.out.println("Você perdeu 20 de vida");
    }

    @Override
    public String ataqueDuranteDescansoParaUI(Personagem jogador) {
        ataqueDuranteDescanso(jogador);
        return "Você acorda com o estalo de mandíbulas perto demais...\nUm Jacaré tenta arrastá-lo durante o descanso!\nVocê perdeu 20 de vida.";
    }
}