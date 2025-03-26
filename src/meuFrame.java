import javax.swing.*;

public class meuFrame extends JFrame {
    public meuFrame(){
        setTitle("PROJETO LPOO");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton botao = new JButton("Iniciar");
        add(botao);

        setVisible(true);
    }

    public static void main(String[] args){
        new meuFrame();
    }

}
