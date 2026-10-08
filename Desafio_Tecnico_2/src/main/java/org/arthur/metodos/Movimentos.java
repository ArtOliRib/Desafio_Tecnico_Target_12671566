package org.arthur.metodos;

import com.fasterxml.jackson.databind.JsonNode;
import org.arthur.model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Classe responsável por gerenciar as movimentações de entrada e saída de produtos.
 * Implementa o padrão Singleton para garantir uma única instância de controle de movimentações.
 */
public class Movimentos {

    // Identificador sequencial único para cada movimentação
    private int idenficadorMovimento;

    // Instância única da classe (padrão Singleton)
    private static Movimentos instance;

    // Lista que armazena as descrições de todas as movimentações realizadas
    private ArrayList<String> descricaoMovimento = new ArrayList<String>();

    /**
     * Construtor privado para implementação do padrão Singleton.
     * Inicializa o identificador de movimentação em 0.
     */
    private Movimentos() {
        this.idenficadorMovimento = 0;
    }

    /**
     * Retorna a instância única da classe Movimentos.
     * Cria a instância caso ainda não exista.
     *
     * @return A instância única de Movimentos
     */
    public static Movimentos getInstance() {
        if (instance == null) {
            instance = new Movimentos();
        }
        return instance;
    }

    /**
     * Registra uma entrada de produto no estoque.
     * Incrementa a quantidade do produto e registra a movimentação.
     *
     * @param produto O produto que terá entrada no estoque
     * @param valor   A quantidade a ser adicionada ao estoque
     */
    public void entradaProduto(Produto produto, int valor) {
        // Calcula a nova quantidade após a entrada
        int novaQuantidade = produto.getQuantidade() + valor;
        Scanner scanner = new Scanner(System.in);

        // Atualiza o identificador único da movimentação e gera a descrição do que foi movimentado
        this.idenficadorMovimento += 1;

        String movimentacao = "#" + this.idenficadorMovimento
                + " Entrada de produto: "
                + produto.getDescricao()
                + " - Quantidade adicionada ao estoque: "
                + valor;

        // Adiciona a descrição da movimentação ao histórico
        descricaoMovimento.add(movimentacao);

        System.out.println("A movimentação " + movimentacao + " foi realizada com sucesso! Quantidade nova: " + novaQuantidade);

        // Realiza a modificação da quantidade de produtos na memória
        produto.setQuantidade(novaQuantidade);

        System.out.println();
        System.out.print("Pressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    /**
     * Registra uma saída de produto do estoque.
     * Decrementa a quantidade do produto e registra a movimentação.
     *
     * @param produto O produto que terá saída do estoque
     * @param valor   A quantidade a ser removida do estoque
     */
    public void saidaProduto(Produto produto, int valor) {
        // Realiza a operação do movimento de saída de produtos
        int novaQuantidade = produto.getQuantidade() - valor;
        Scanner scanner = new Scanner(System.in);

        // Atualiza o identificador único da movimentação e gera a descrição do que foi movimentado
        this.idenficadorMovimento += 1;

        String movimentacao = "#" + this.idenficadorMovimento
                + " Saida de produto: "
                + produto.getDescricao()
                + " - Quantidade retirada do estoque: "
                + valor;

        // Realiza a adição da descrição na memória de modificação
        descricaoMovimento.add(movimentacao);

        System.out.println("A movimentação " + movimentacao + " foi realizada com sucesso! Quantidade nova: " + novaQuantidade);

        // Realiza a modificação da quantidade de produtos na memória
        produto.setQuantidade(novaQuantidade);

        System.out.println();
        System.out.print("Pressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    /**
     * Lista todas as movimentações registradas no sistema.
     * Exibe as movimentações em ordem cronológica ou uma mensagem caso não haja movimentações.
     */
    public void listarMovimentacoes() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("======================================");
        System.out.println("        LISTA DE MOVIMENTAÇÕES");
        System.out.println("======================================");

        // Verifica se há movimentações registradas
        if (descricaoMovimento.isEmpty()) {
            System.out.println("Nenhuma movimentação foi realizada ainda.");
        } else {
            // Percorre e exibe todas as movimentações registradas
            for (String movimentacao : descricaoMovimento) {
                System.out.println(movimentacao);
            }
        }

        System.out.println("======================================");
        System.out.println();
        System.out.print("Pressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }
}