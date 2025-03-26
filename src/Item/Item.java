package Item;

public class Item {
    private String nomeItem;
    private double peso;
    private int durabilidade;

    // Subclasse Alimento ()
    private int valorNutricional;
    private String[] tipoAlimento;
    private int validade;

    void consumir() {
    }

    // Subclasse Água ()
    private String pureza;
    private double volume;

    void beber() {
    }

    // Subclasse Material (Material outroMaterial)
    private String[] tipoMaterial;
    private int resistencia;

    void combinar() {
    }

    // Subclasse Ferramenta ()
    private String[] tipoFerramenta;
    private int eficiencia;

    void usar() {
    }

    // Subclasse Arma ()
    private String[] tipoArma;
    private int dano;
    private int alcance;

    void atacar(Alvo inimigo) {
    }

    // Subclasse Remédio ()
    private String[] tipoRemedio;
    private String[] efeito;

    void usar() {
    }


    // Relacionamento entre Itens e Personagens
    // Subclasse Inventário ()
    private String[] listaDeItem;
    private double pesoTotal;
    private double espacoDisponivel;

    void adicionarItem(Item item) {
    }

    void removerItem(String nomeItem) {
    }

    void usarItem(String nomeItem) {
    }

}



