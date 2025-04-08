package Item.Superclasse;

public class Item {
    private String nome;
    private double peso;
    private int durabilidade;

    // Construtor
    public Item (String nome, double peso, int durabilidade) {
    this.nome = nome;
    this.peso = peso;
    this.durabilidade = durabilidade;
    }

    public void usar (Item item) { // FAZER MELHOR !!
    this.durabilidade -= 1;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }
    public int getDurabilidade() {
        return durabilidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }
}



