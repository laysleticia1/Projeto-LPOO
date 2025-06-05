import Jogo.Jogo;
import javax.swing.*;
import UI.GerenciadorUI;

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