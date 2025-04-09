package Ambiente.Subclasses;

import Ambiente.Superclasse.Ambiente;
import java.util.ArrayList;
import java.util.Arrays;

public class Caverna extends Ambiente {
    private String luminosidade;
    private String criaturas;
    private String hidratacao;

    //Construtor
    public Caverna () {
        super("Caverna", "Local subterrâneo, escuro e silencioso. Túneis estreitos e paredes úmidas escondem rochas valiosas, minérios raros, mas também perigos ocultos. Um ambiente hostil, mas promissor.", 7, new ArrayList<>(Arrays.asList("Rochas e minérios raros")), 0.65, "Úmido e escuro" );
       this.luminosidade = "Pouca luz";
       this.criaturas = "Presença de criaturas desconhecidas...";
       this.hidratacao = "Água de gotejamento";

    }
}
