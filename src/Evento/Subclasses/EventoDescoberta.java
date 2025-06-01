package Evento.Subclasses;

import Evento.Superclasse.Evento;
import Personagem.Superclasse.Personagem;
import Ambiente.Superclasse.Ambiente;
import Ambiente.Subclasses.*;
import java.util.Scanner;
import Item.Superclasse.*;
import Item.Subclasses.*;

public class EventoDescoberta extends Evento {
    private String tipoDeDescoberta;
    private String recursosEncontrados;
    private String condicaoEspecial;

    public EventoDescoberta(String nome, String descricao, double probabilidadeDeOcorrencia, String impacto, String condicaoAtivacao, String tipoDeDescoberta, String recursosEncontrados, String condicaoEspecial ) {
        super(nome, descricao, probabilidadeDeOcorrencia, impacto, condicaoAtivacao);
        this.tipoDeDescoberta = tipoDeDescoberta;
        this.recursosEncontrados = recursosEncontrados;
        this.condicaoEspecial = condicaoEspecial;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("\n🔎 Evento de Descoberta: " + getNomeEvento());
        System.out.println(getDescricao());
        System.out.println("Tipo de Descoberta: " + tipoDeDescoberta);
        System.out.println("Recursos: " + recursosEncontrados);
        System.out.println("Condição Especial: " + condicaoEspecial);

        if (getImpacto().equalsIgnoreCase("inventario")) {
            Item itemDescoberto = null;

            // Criação do item com base no recurso encontrado
            switch (recursosEncontrados.toLowerCase()) {
                case "minério luminoso":
                    itemDescoberto = new Material("Minério Luminoso", "Cristal", 1.0, 1, 85);
                    break;
                case "itens de sobrevivência":
                    itemDescoberto = new Remedios("Kit de Sobrevivência", "Emergencial", "Recupera parcialmente fome e energia");
                    break;
                case "moedas antigas":
                    itemDescoberto = new Material("Moedas Antigas", "Tesouro", 0.4, 1, 100);
                    break;
                case "artefato místico":
                    itemDescoberto = new Material("Artefato Místico", "Relíquia", 2.0, 1, 95);
                    break;
                case "fóssil luminoso":
                    itemDescoberto = new Material("Fóssil Luminoso", "Cristal", 1.2, 1, 70);
                    break;
                case "carne de javali":
                    itemDescoberto = new Alimentos("Carne de Javali", 0.6, 60, 25, "Carne", 4);
                    break;
                case "abrigo":
                    itemDescoberto = new Material("Abrigo Portátil", "Abrigo", 1.5, 1, 80);
                    break;
                case "cristal":
                    itemDescoberto = new Material("Cristal Vibrante", "Cristal", 1.0, 1, 90);
                    break;
                case "relíquia":
                    itemDescoberto = new Material("Relíquia Antiga", "Relíquia", 1.5, 1, 98);
                    break;
                case "alimento":
                    itemDescoberto = new Alimentos("Fruta Exótica", 0.3, 40, 15, "Fruta", 2);
                    break;
                case "artefato":
                    itemDescoberto = new Material("Artefato Ritualístico", "Artefato", 1.8, 1, 92);
                    break;
                case "tesouro":
                    itemDescoberto = new Material("Baú de Tesouro", "Tesouro", 3.0, 1, 100);
                    break;
                case "cristal mágico de luz azulada":
                    itemDescoberto = new Material("Cristal Azul Brilhante", "Cristal", 1.0, 1, 95);
                    break;
                default:
                    System.out.println("Nada útil foi encontrado nesta descoberta.");
                    return;
            }

            System.out.println("\nVocê encontrou: " + itemDescoberto.getNome());
            System.out.print("Deseja adicionar este item ao seu inventário? (s/n): ");
            Scanner scanner = new Scanner(System.in);
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("s") || resposta.equals("sim")) {
                jogador.adicionarAoInventario(itemDescoberto);
                System.out.println("✅ Item adicionado com sucesso ao inventário!");

            } else {
                System.out.println("Você optou por deixar o item para trás.");
            }
        }
    }

    public boolean podeOcorrerNoAmbiente(Ambiente ambiente) {
        // Suponha que só ocorre em Ruínas
        return ambiente instanceof Ruinas;
    }
}
