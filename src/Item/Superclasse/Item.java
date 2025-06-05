package Item.Superclasse;

import Interface.Usavel;
import Personagem.Inventario.*;
import Personagem.Superclasse.*;

import javax.swing.*;

public class Item {
    private String nome;
    private double peso;
    private int durabilidade;

    public Item (String nome, double peso, int durabilidade) {
        this.nome = nome;
        this.peso = peso;
        this.durabilidade = durabilidade;
    }

    public void usar() {
        this.durabilidade--;
        if (durabilidade < 0) durabilidade = 0;
    }

    public void exibirDetalhes() {
        System.out.println("Item genérico: " + nome);
    }

    //Getters and Setters
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
    public String getDescricaoItem() {
        if (this.nome != null && !this.nome.trim().isEmpty()) {
            return "Um item do tipo: " + this.nome + ".";
        }
        return "Um item misterioso.";
    }

    //Interface
    public String exibirDetalhesInterface() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ").append(nome).append("\n");
        sb.append("• Peso: ").append(peso).append("\n");
        sb.append("• Durabilidade: ").append(durabilidade).append("\n");
        return sb.toString();
    }

    public ImageIcon getImagem() {
        try {
            String nomeBase = nome.toLowerCase();

            String nomeArquivo;

            // 💡 Exceção específica para carne de cobra
            if (nomeBase.equals("carne de cobra")) {
                nomeArquivo = "carnecobra";
            }
            // 🥩 Todas as outras carnes genéricas
            else if (nomeBase.startsWith("carne de")) {
                nomeArquivo = "carne";
            }
            // 🌐 Regra geral: remove espaços, acentos e pontuação
            else {
                nomeArquivo = nome.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            }

            String caminho = "Resources/Item/" + nomeArquivo + ".png";

            java.net.URL imagemURL = getClass().getClassLoader().getResource(caminho);
            if (imagemURL != null) {
                return new ImageIcon(imagemURL);
            } else {
                System.err.println("Imagem não encontrada: " + caminho);
                return new ImageIcon(); // ou imagem fallback
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon(); // fallback
        }
    }

    public ImageIcon getImagemIcone() {
        if (nome == null) return null;

        String nomeArquivo = nome.replaceAll("\\s+", "").toLowerCase() + ".png";
        String caminho = "/Resources/Item/" + nomeArquivo;

        java.net.URL imgURL = getClass().getResource(caminho);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("⚠️ Imagem não encontrada para: " + nome + " (" + caminho + ")");
            return null;
        }
    }


    public String gerarDescricaoDetalhada() {
        return "- Nome: " + nome + "\n- Peso: " + peso + " kg";
    }


}


