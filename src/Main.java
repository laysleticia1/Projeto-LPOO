import Jogo.Jogo;
import javax.swing.*;
import UI.GamePanel;
import UI.GerenciadorUI;
import UI.MenuInicialPanel;
import UI.CriacaoPersonagem;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GerenciadorUI();
            }
        });
        new Jogo().iniciar();
    }
}