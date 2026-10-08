package org.arthur;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.arthur.metodos.HashProdutos;
import org.arthur.metodos.Movimentos;
import org.arthur.model.Produto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static void main() throws IOException {
        IO.println(String.format("Hello and welcome!"));

        // Carrega o arquivo JSON do estoque
        Path path = Paths.get("src", "main", "arquivos", "estoque.json");
        File file = path.toFile();

        ObjectMapper objectMapper = new ObjectMapper();
        HashProdutos hash = new HashProdutos();
        HashMap<String, Produto> memoria = new HashMap<String, Produto>();

        try {
            // Lê o arquivo JSON e converte para uma árvore de nós (JsonNode)
            JsonNode rootNode = objectMapper.readTree(file);

            // Obtém o array de estoque do JSON
            JsonNode estoqueArray = rootNode.get("estoque");

            // Inicializa o HashMap com os produtos do JSON
            memoria = hash.iniciar(estoqueArray);

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println();
            System.out.println("======================================");
            System.out.println("        MENU DE MOVIMENTAÇÕES");
            System.out.println("======================================");
            System.out.println("1 - Dar entrada de mercadoria");
            System.out.println("2 - Dar saída de mercadoria");
            System.out.println("3 - Listar movimentações");
            System.out.println("0 - Sair");
            System.out.println("======================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            int quantidadeMovimentar = 0;
            Produto produto;
            Movimentos movimentos;
            String codigoProduto;

            switch (opcao) {
                case 1:
                    System.out.println();
                    System.out.println("Opção selecionada: Entrada de mercadoria");

                    System.out.print("Digite o código do produto: ");
                    codigoProduto = scanner.next();

                    System.out.print("Digite a quantidade a ser adicionada: ");
                    quantidadeMovimentar = scanner.nextInt();

                    produto = memoria.get(codigoProduto);

                    movimentos = Movimentos.getInstance();
                    movimentos.entradaProduto(produto, quantidadeMovimentar);

                    break;

                case 2:
                    System.out.println();
                    System.out.println("Opção selecionada: Saida de mercadoria");

                    System.out.print("Digite o código do produto: ");
                    codigoProduto = scanner.next();

                    System.out.print("Digite a quantidade a ser removida: ");
                    quantidadeMovimentar = scanner.nextInt();

                    produto = memoria.get(codigoProduto);

                    movimentos = Movimentos.getInstance();
                    movimentos.saidaProduto(produto, quantidadeMovimentar);

                    break;


                case 3:
                    System.out.println();
                    System.out.println("Opção selecionada: Listar movimentações");
                    movimentos = Movimentos.getInstance();
                    movimentos.listarMovimentacoes();
                    break;

                case 0:
                    System.out.println();
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println();
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 0);

        scanner.close();


    }
}