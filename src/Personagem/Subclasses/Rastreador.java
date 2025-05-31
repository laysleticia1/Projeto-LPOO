package Personagem.Subclasses;

import Ambiente.Superclasse.*;
import Ambiente.Subclasses.*;
import Personagem.Superclasse.Personagem;
import Item.Subclasses.*;
import Item.Superclasse.*;
import Excecoes.InventarioCheioException;
import java.util.Random;

public class Rastreador extends Personagem{

    public Rastreador(String nomeUsuario) {
        super(nomeUsuario, "Rastreador");
    }

    public void identificarPegadas(Ambiente ambiente) {
        System.out.println("Você examina o solo em busca de pegadas...");
        if (ambiente instanceof Floresta) {
            System.out.println("Pegadas de javali encontradas. Um som distante confirma sua aproximação.");
        } else if (ambiente instanceof Montanha) {
            System.out.println("Pegadas de urso detectadas entre as rochas.");
        } else if (ambiente instanceof Caverna) {
            System.out.println("Você identifica marcas de morcegos e ratos.");
        } else if (ambiente instanceof Ruinas) {
            System.out.println("Pegadas humanas... recentes. Alguém passou por aqui.");
        } else if (ambiente instanceof LagoRio) {
            System.out.println("Marcas de patas de animal aquático: possível cardume agitado.");
        }
    }

    public void farejarTrilha(Ambiente ambiente) {
        Item itemEncontrado = null;

        if (ambiente instanceof Floresta) {
            System.out.println(getNome() + " identificou trilhas de animais e encontrou frutas silvestres.");
            itemEncontrado = new Alimentos("Frutas Silvestres", 1.0, 2, 5, "Fruta", 2);
        } else if (ambiente instanceof Caverna) {
            System.out.println(getNome() + " farejou pegadas humanas e achou uma tocha esquecida.");
            itemEncontrado = new Ferramentas("Tocha", 1.5, 3, 3);
        } else if (ambiente instanceof LagoRio) {
            System.out.println(getNome() + " encontrou pegadas e redes de pesca abandonadas.");
            itemEncontrado = new Material("Rede de Pesca", "Fibra Natural", 1.2, 3, 2);
        } else if (ambiente instanceof Montanha) {
            System.out.println(getNome() + " observou rastros entre as pedras e achou uma bota reforçada.");
            itemEncontrado = new Ferramentas("Bota Reforçada", 2.0, 4, 3);
        } else if (ambiente instanceof Ruinas) {
            System.out.println(getNome() + " seguiu marcas e descobriu uma adaga antiga.");
            itemEncontrado = new Armas("Adaga Antiga", 1.0, 5, "faca", 3, 1);
        } else {
            System.out.println("Ambiente inóspito para rastreamento avançado.");
            return;
        }

        // Adiciona o item encontrado ao inventário
        try {
            getInventario().adicionarItem(itemEncontrado);
            System.out.println("Item encontrado e adicionado ao inventário: " + itemEncontrado.getNome());
        } catch (InventarioCheioException e) {
            System.out.println("Inventário cheio. Item não foi adicionado: " + itemEncontrado.getNome());
        }
    }

    public void procurarRecursos(Ambiente ambiente, Personagem jogador) {
        Random random = new Random();
        Item encontrado = null;

        if (ambiente instanceof Floresta) {
            int escolha = random.nextInt(100);
            if (escolha < 50) {
                encontrado = new Alimentos("Frutas Silvestres", 0.3, 3, 10, "Fruta", 2);
            } else if (escolha < 80) {
                encontrado = new Ferramentas("Galho Afiado", 0.7, 2, 1);
            } else {
                encontrado = new Remedios("Erva Medicinal", "Planta", "Cura leve");
            }

        } else if (ambiente instanceof Montanha) {
            int escolha = random.nextInt(100);
            if (escolha < 50) {
                encontrado = new Material("Minério Bruto", "Metal", 2.5, 4, 6);
            } else if (escolha < 85) {
                encontrado = new Alimentos("Raízes Comestíveis", 0.6, 5, 8, "Raiz", 3);
            } else {
                encontrado = new Agua("Gotas de Neve", 1.0, 1, "Pura", 0.5, 0.1);
            }

        } else if (ambiente instanceof LagoRio) {
            int escolha = random.nextInt(100);
            if (escolha < 60) {
                encontrado = new Agua("Água de Rio", 1.2, 2, "Filtrada", 1.0, 0.05);
            } else if (escolha < 90) {
                encontrado = new Alimentos("Peixe Pequeno", 0.9, 3, 12, "Proteína", 1);
            } else {
                encontrado = new Material("Pedras Lisas", "Pedra", 1.5, 6, 4);
            }

        } else if (ambiente instanceof Caverna) {
            int escolha = random.nextInt(100);
            if (escolha < 40) {
                encontrado = new Material("Cristal Brilhante", "Cristal", 1.3, 5, 8);
            } else if (escolha < 75) {
                encontrado = new Alimentos("Fungo Luminescente", 0.4, 2, 7, "Cogumelo", 1);
            } else {
                encontrado = new Ferramentas("Rocha Afiada", 1.0, 3, 2);
            }

        } else if (ambiente instanceof Ruinas) {
            int escolha = random.nextInt(100);
            if (escolha < 50) {
                encontrado = new Ferramentas("Ferramenta Enferrujada", 1.5, 2, 1);
            } else if (escolha < 80) {
                encontrado = new Alimentos("Enlatado Velho", 0.8, 10, 15, "Conserva", 5);
            } else {
                encontrado = new Armas("Canivete Antigo", 1.0, 4, "Faca", 6, 1);
            }

        } else {
            System.out.println("Ambiente desconhecido. Você não encontrou nada.");
            return;
        }

        if (encontrado != null) {
            System.out.println("🔍 Você encontrou: " + encontrado.getNome());

            // Detalhes específicos por tipo
            if (encontrado instanceof Alimentos alimento) {
                System.out.println("→ Tipo: " + alimento.getTipo() + " | Nutrição: " + alimento.getValorNutricional() + " | Validade: " + alimento.getValidade());
            } else if (encontrado instanceof Agua agua) {
                System.out.println("→ Pureza: " + agua.getPureza() + " | Volume: " + agua.getVolume() + "L | Chance de contaminação: " + agua.getChanceContaminacao());
            } else if (encontrado instanceof Remedios remedio) {
                System.out.println("→ Tipo: " + remedio.getTipo() + " | Efeito: " + remedio.getEfeito());
            } else if (encontrado instanceof Armas arma) {
                System.out.println("→ Tipo: " + arma.getTipo() + " | Dano: " + arma.getDano() + " | Alcance: " + arma.getAlcance());
            } else if (encontrado instanceof Ferramentas ferramenta) {
                System.out.println("→ Eficiência: " + ferramenta.getEficiencia());
            } else if (encontrado instanceof Material material) {
                System.out.println("→ Tipo: " + material.getTipo() + " | Resistência: " + material.getResistencia());
            }

            try {
                jogador.getInventario().adicionarItem(encontrado);
            } catch (InventarioCheioException e) {
                System.out.println("❌ " + e.getMessage());
            }
        } else {
            System.out.println("🥀 Você procurou, mas não encontrou nenhum recurso útil.");
        }
    }


}
