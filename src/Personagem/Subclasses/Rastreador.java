package Personagem.Subclasses;

import Ambiente.Superclasse.*;
import Ambiente.Subclasses.*;
import Personagem.Superclasse.Personagem;
import Personagem.Subclasses.*;
import Item.Subclasses.*;
import Item.Superclasse.*;
import Excecoes.InventarioCheioException;

import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

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

        // Mostra detalhes do item com base no tipo
        if (itemEncontrado instanceof Alimentos a) a.exibirDetalhes();
        else if (itemEncontrado instanceof Material m) m.exibirDetalhes();
        else if (itemEncontrado instanceof Remedios r) r.exibirDetalhes();
        else if (itemEncontrado instanceof Ferramentas f) f.exibirDetalhes();
        else if (itemEncontrado instanceof Armas a) a.exibirDetalhes();
        else if (itemEncontrado instanceof Agua ag) ag.exibirDetalhes();

        // Pergunta se o jogador quer guardar o item
        Scanner scanner = new Scanner(System.in);
        System.out.print("Deseja adicionar ao inventário? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            try {
                getInventario().adicionarItem(itemEncontrado);
                System.out.println(itemEncontrado.getNome() + " foi adicionado ao inventário.");
            } catch (InventarioCheioException e) {
                System.out.println("Inventário cheio. Item não foi adicionado: " + itemEncontrado.getNome());
            }
        } else {
            System.out.println("Você optou por deixar o item para trás.");
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
            System.out.println("Você procurou, mas não encontrou nenhum recurso útil.");
        }
    }

    //Interface
    public void farejarTrilhaInterface(Ambiente ambiente, JTextArea areaLog) {
        Item itemEncontrado = null;

        if (ambiente instanceof Floresta) {
            areaLog.append(getNome() + " identificou trilhas de animais e encontrou frutas silvestres.\n");
            itemEncontrado = new Alimentos("Frutas Silvestres", 1.0, 2, 5, "Fruta", 2);
        } else if (ambiente instanceof Caverna) {
            areaLog.append(getNome() + " farejou pegadas humanas e achou uma tocha esquecida.\n");
            itemEncontrado = new Ferramentas("Tocha", 1.5, 3, 3);
        } else if (ambiente instanceof LagoRio) {
            areaLog.append(getNome() + " encontrou pegadas e redes de pesca abandonadas.\n");
            itemEncontrado = new Material("Rede de Pesca", "Fibra Natural", 1.2, 3, 2);
        } else if (ambiente instanceof Montanha) {
            areaLog.append(getNome() + " observou rastros entre as pedras e achou uma bota reforçada.\n");
            itemEncontrado = new Ferramentas("Bota Reforçada", 2.0, 4, 3);
        } else if (ambiente instanceof Ruinas) {
            areaLog.append(getNome() + " seguiu marcas e descobriu uma adaga antiga.\n");
            itemEncontrado = new Armas("Adaga Antiga", 1.0, 5, "faca", 3, 1);
        } else {
            areaLog.append("Ambiente inóspito para rastreamento avançado.\n");
            return;
        }

        if (itemEncontrado != null) {
            areaLog.append("\n" + itemEncontrado.getNome() + " encontrado.\n");
            String detalhes = "";

            if (itemEncontrado instanceof Alimentos a) detalhes = a.exibirDetalhesInterface();
            else if (itemEncontrado instanceof Material m) detalhes = m.exibirDetalhesInterface();
            else if (itemEncontrado instanceof Remedios r) detalhes = r.exibirDetalhesInterface();
            else if (itemEncontrado instanceof Ferramentas f) detalhes = f.exibirDetalhesInterface();
            else if (itemEncontrado instanceof Armas a) detalhes = a.exibirDetalhesInterface();
            else if (itemEncontrado instanceof Agua ag) detalhes = ag.exibirDetalhesInterface();

            areaLog.append(detalhes + "\n");

            int escolha = JOptionPane.showConfirmDialog(null,
                    "Deseja adicionar este item ao seu inventário?",
                    "Item Encontrado",
                    JOptionPane.YES_NO_OPTION);

            if (escolha == JOptionPane.YES_OPTION) {
                try {
                    getInventario().adicionarItem(itemEncontrado);
                    areaLog.append("✔️ " + itemEncontrado.getNome() + " foi adicionado ao inventário.\n");
                } catch (InventarioCheioException e) {
                    areaLog.append("❌ Inventário cheio. Não foi possível adicionar: " + itemEncontrado.getNome() + "\n");
                }
            } else {
                areaLog.append("Você deixou o item para trás.\n");
            }
        }
    }
    public void identificarPegadasInterface(Ambiente ambiente, JTextArea areaLog) {
        areaLog.append("Você examina o solo em busca de pegadas...\n");
        if (ambiente instanceof Floresta) {
            areaLog.append("Pegadas de javali encontradas. Um som distante confirma sua aproximação.\n");
        } else if (ambiente instanceof Montanha) {
            areaLog.append("Pegadas de urso detectadas entre as rochas.\n");
        } else if (ambiente instanceof Caverna) {
            areaLog.append("Você identifica marcas de morcegos e ratos.\n");
        } else if (ambiente instanceof Ruinas) {
            areaLog.append("Pegadas humanas... recentes. Alguém passou por aqui.\n");
        } else if (ambiente instanceof LagoRio) {
            areaLog.append("Marcas de patas de animal aquático: possível cardume agitado.\n");
        } else {
            areaLog.append("Ambiente desconhecido. Nenhuma pista visível.\n");
        }
    }
    public void procurarRecursosInterface(Ambiente ambiente, Personagem jogador, JTextArea areaLog) {
        Random random = new Random();
        Item encontrado = null;

        if (ambiente instanceof Floresta) {
            int escolha = random.nextInt(100);
            encontrado = escolha < 50 ? new Alimentos("Frutas Silvestres", 0.3, 3, 10, "Fruta", 2) :
                    escolha < 80 ? new Ferramentas("Galho Afiado", 0.7, 2, 1) :
                            new Remedios("Erva Medicinal", "Planta", "Cura leve");

        } else if (ambiente instanceof Montanha) {
            int escolha = random.nextInt(100);
            encontrado = escolha < 50 ? new Material("Minério Bruto", "Metal", 2.5, 4, 6) :
                    escolha < 85 ? new Alimentos("Raízes Comestíveis", 0.6, 5, 8, "Raiz", 3) :
                            new Agua("Gotas de Neve", 1.0, 1, "Pura", 0.5, 0.1);

        } else if (ambiente instanceof LagoRio) {
            int escolha = random.nextInt(100);
            encontrado = escolha < 60 ? new Agua("Água de Rio", 1.2, 2, "Filtrada", 1.0, 0.05) :
                    escolha < 90 ? new Alimentos("Peixe Pequeno", 0.9, 3, 12, "Proteína", 1) :
                            new Material("Pedras Lisas", "Pedra", 1.5, 6, 4);

        } else if (ambiente instanceof Caverna) {
            int escolha = random.nextInt(100);
            encontrado = escolha < 40 ? new Material("Cristal Brilhante", "Cristal", 1.3, 5, 8) :
                    escolha < 75 ? new Alimentos("Fungo Luminescente", 0.4, 2, 7, "Cogumelo", 1) :
                            new Ferramentas("Rocha Afiada", 1.0, 3, 2);

        } else if (ambiente instanceof Ruinas) {
            int escolha = random.nextInt(100);
            encontrado = escolha < 50 ? new Ferramentas("Ferramenta Enferrujada", 1.5, 2, 1) :
                    escolha < 80 ? new Alimentos("Enlatado Velho", 0.8, 10, 15, "Conserva", 5) :
                            new Armas("Canivete Antigo", 1.0, 4, "Faca", 6, 1);
        }

        if (encontrado != null) {
            areaLog.append("\n🔍 Você encontrou: " + encontrado.getNome() + "\n");

            String detalhes = "";
            if (encontrado instanceof Alimentos alimento) detalhes = alimento.exibirDetalhesInterface();
            else if (encontrado instanceof Agua agua) detalhes = agua.exibirDetalhesInterface();
            else if (encontrado instanceof Remedios remedio) detalhes = remedio.exibirDetalhesInterface();
            else if (encontrado instanceof Armas arma) detalhes = arma.exibirDetalhesInterface();
            else if (encontrado instanceof Ferramentas ferramenta) detalhes = ferramenta.exibirDetalhesInterface();
            else if (encontrado instanceof Material material) detalhes = material.exibirDetalhesInterface();

            areaLog.append(detalhes + "\n");

            int escolha = JOptionPane.showConfirmDialog(null,
                    "Deseja adicionar este item ao seu inventário?",
                    "Item Encontrado",
                    JOptionPane.YES_NO_OPTION);

            if (escolha == JOptionPane.YES_OPTION) {
                try {
                    jogador.getInventario().adicionarItem(encontrado);
                    areaLog.append("✔️ " + encontrado.getNome() + " foi adicionado ao inventário.\n");
                } catch (InventarioCheioException e) {
                    areaLog.append("❌ " + e.getMessage() + "\n");
                }
            } else {
                areaLog.append("Você deixou o item para trás.\n");
            }
        } else {
            areaLog.append("Você procurou, mas não encontrou nenhum recurso útil.\n");
        }

    }

    public Item procurarRecursosRetornandoItem(Ambiente ambiente, Personagem jogador) {
        Random random = new Random();
        int chance = random.nextInt(100);

        if (ambiente instanceof Floresta) {
            if (chance < 40)
                return new Alimentos("Frutas Silvestres", 0.3, 3, 10, "Fruta", 2);
            else if (chance < 70)
                return new Ferramentas("Galho Afiado", 0.7, 2, 1);
            else if (chance < 90)
                return new Remedios("Erva Medicinal", "Planta", "Cura leve");
            else
                return new Agua("Orvalho Fresco", 0.5, 1, "Pura", 0.3, 0.1);

        } else if (ambiente instanceof Montanha) {
            if (chance < 45)
                return new Material("Minério Bruto", "Metal", 2.5, 4, 6);
            else if (chance < 75)
                return new Alimentos("Raízes Comestíveis", 0.6, 5, 8, "Raiz", 3);
            else if (chance < 95)
                return new Agua("Gotas de Neve", 1.0, 1, "Pura", 0.5, 0.1);
            else
                return new Armas("Lança Improvisada", 2.0, 3, "Lança", 8, 2);

        } else if (ambiente instanceof LagoRio) {
            if (chance < 50)
                return new Agua("Água de Rio", 1.2, 2, "Filtrada", 1.0, 0.05);
            else if (chance < 80)
                return new Alimentos("Peixe Pequeno", 0.9, 3, 12, "Proteína", 1);
            else
                return new Material("Pedras Lisas", "Pedra", 1.5, 6, 4);

        } else if (ambiente instanceof Caverna) {
            if (chance < 40)
                return new Material("Cristal Brilhante", "Cristal", 1.3, 5, 8);
            else if (chance < 75)
                return new Alimentos("Fungo Luminescente", 0.4, 2, 7, "Cogumelo", 1);
            else
                return new Ferramentas("Rocha Afiada", 1.0, 3, 2);

        } else if (ambiente instanceof Ruinas) {
            if (chance < 50)
                return new Ferramentas("Ferramenta Enferrujada", 1.5, 2, 1);
            else if (chance < 80)
                return new Alimentos("Enlatado Velho", 0.8, 10, 15, "Conserva", 5);
            else
                return new Armas("Canivete Antigo", 1.0, 4, "Faca", 6, 1);
        }

        return null;
    }



}
