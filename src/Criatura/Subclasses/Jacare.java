package Criatura.Subclasses;

import Criatura.Superclasse.Criatura;
import Personagem.Superclasse.Personagem;

import javax.swing.*;

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
    public void acaoEspecial(Personagem jogador) {
        System.out.println("O Jacaré girou com sua cauda poderosa, lançando água e desorientando você.");
        jogador.diminuirEnergia(10);
        jogador.diminuirSanidade(10);
        System.out.println("Você perdeu 10 de energia e 10 de sanidade.");
    }

    @Override
    public void fugir() {
        System.out.println("O jacaré mergulha silenciosamente, desaparecendo nas águas turvas.");
    }

    @Override
    public void ataqueDuranteDescanso(Personagem jogador) {
        System.out.println("\nVocê acorda com o estalo de mandíbulas perto demais...");
        System.out.println("Um Jacaré tenta arrastá-lo durante o descanso!");
        jogador.diminuirVida(20);
        System.out.println("Você perdeu 20 de vida");
    }

    //Interface
    @Override
    public void atacarInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(16);
        areaLog.append("O Jacaré surge da água e crava os dentes na sua perna!\n");
        areaLog.append("Você grita de dor ao ser arrastado por alguns segundos.\n");
        areaLog.append("Você perdeu 16 de vida.\n");
    }

    @Override
    public void acaoEspecialInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(10);
        jogador.diminuirEnergia(8);
        areaLog.append("O Jacaré desfere um ataque giratório violento, o famoso ‘Death Roll’!\n");
        areaLog.append("Você sente os ossos chacoalharem. Perdeu vida e energia.\n");
        areaLog.append("Você perdeu 10 de vida e 8 de energia.\n");
    }

    @Override
    public void fugirInterface(JTextArea areaLog) {
        areaLog.append("O Jacaré se arrasta de volta para as águas lamacentas, desaparecendo silenciosamente...\n");
    }

    @Override
    public void ataqueDuranteDescansoInterface(Personagem jogador, JTextArea areaLog) {
        jogador.diminuirVida(18);
        areaLog.append("Enquanto você dormia perto da margem, um Jacaré realiza um ataque surpresa.\n");
        areaLog.append("Você acorda com uma mordida dolorosa na perna!\n");
        areaLog.append("Você perdeu 18 de vida.\n");
    }

}