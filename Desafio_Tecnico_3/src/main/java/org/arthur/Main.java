package org.arthur;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {

        // Taxa de juros diária (2,5%)
        double juros = 0.025;
        // Scanner para leitura de dados do usuário
        Scanner scanner = new Scanner(System.in);
        // Obtém a data atual do sistema
        LocalDate dataAtual = LocalDate.now();

        // Solicita um valor em reais ao usuário
        System.out.println("Digite um valor em reais (R$):");
        double valorReais = scanner.nextDouble();
        scanner.nextLine(); // Consome a quebra de linha

        // Solicita uma data no formato dd/mm/aaaa ao usuário
        System.out.println("Digite a data de vencimento no formato dd/mm/aaaa:");
        String data = scanner.nextLine();

        // Define o formato de data esperado (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInformada;
        try {
            // Faz o parse da string para LocalDate
            dataInformada = LocalDate.parse(data, formatter);

            // Verifica se a data é menor que a data atual
            if (dataInformada.isBefore(dataAtual)) {
                System.out.println("Erro: A data informada não pode ser anterior à data atual.");
                scanner.close();
                return;
            }
        } catch (java.time.format.DateTimeParseException e) {
            // Tratamento de erro para datas inválidas ou em formato incorreto
            System.out.println("Erro: Data inválida ou impossível. Por favor, verifique o formato dd/mm/aaaa.");
            scanner.close();
            return;
        }

        // Calcula o valor dos juros diários
        double valorDoJurosDiario = valorReais * juros;
        // Calcula a quantidade de dias entre a data atual e a data informada
        long dias = ChronoUnit.DAYS.between(dataAtual, dataInformada);
        // Calcula o valor total com os juros aplicados
        double valorTotal = valorReais + (valorDoJurosDiario * dias);

        // Exibe o resultado
        System.out.println("\n--- RESULTADO ---");
        System.out.printf("Valor inicial: R$ %.2f%n", valorReais);
        System.out.printf("Taxa de juros: %.2f%% ao dia%n", juros * 100);
        System.out.printf("Período: %d dia(s)%n", dias);
        System.out.printf("Valor dos juros: R$ %.2f%n", valorDoJurosDiario * dias);
        System.out.printf("Valor total: R$ %.2f%n", valorTotal);

        scanner.close();
    }
}