public class Personagem {
    private String nome;
    private String classe;
    private int nivel;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private String [] inventario;
    private String [] localizacao;

    String getNome () {
        return nome;
    }
    void setNome (String nomeUsuario) {
        this.nome = nomeUsuario;
    }
    String getClasse () {
        return classe;
    }
    void setClasse (String classeUsuario) {
        this.classe = classeUsuario;
    }
    int getNivel () {
        return nivel;
    }
    void setNivel (int nivelUsuario) {
        this.nivel = nivelUsuario;
    }
    int getVida () {
        return vida;
    }
    void setVida (int vidaUsuario) {
        this.vida = vidaUsuario;
    }
    int getFome () {
        return fome;
    }
    void setFome (int fomeUsuario) {
        this.fome = fomeUsuario;
    }
    int getSede () {
        return sede;
    }
    void setSede (int sedeUsuario) {
        this.sede = sedeUsuario;
    }
    int getEnergia () {
        return energia;
    }
    void setEnergia (int energiaUsuario) {
        this.energia = energiaUsuario;
    }
    int getSanidade () {
        return sanidade;
    }
    void setSanidade (int sanidadeUsuario) {
        this.sanidade = sanidadeUsuario;
    }
    String [] getInventario () {
        return inventario;
    }
    void setInventario (String [] inventarioUsuario) {
        this.inventario = inventarioUsuario;
    }
    String [] getLocalizacao () {
        return localizacao;
    }
    void setLocalizacao (String [] localizacaoUsuario) {
        this.localizacao = localizacaoUsuario;
    }
    void atacar() {
        if (getEnergia () == 0) {
            System.out.println ("Jogador não poderá atacar");
        }
    }
    void defender() {
        if (getEnergia () == 0) {
            System.out.println ("Jogador não poderá defender");
        }
    }
    void correr() {
        if (getEnergia () == 0) {
            System.out.println ("Jogador não poderá correr");
        }
    }
    void agachar() {
        if (getEnergia () == 0) {
            System.out.println ("Jogador não poderá agachar");
        }
    }
    void mover() {
        if (getEnergia () == 0) {
        System.out.println ("Jogador não poderá se mover");
    }
    }
    void usarItem () {
    }
    void curar () {
    }
    void descansar () {
    }
    void habilidadeEspecial(String classe) {
        if (classe.equals("Rastreador")) {
        } else if (classe.equals("Mecânico")) {
        } else if (classe.equals("Médico")) {
        } else if (classe.equals("Sobrevivente Nato")) {
        } else {
        }
    }
}
