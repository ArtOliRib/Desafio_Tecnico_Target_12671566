package org.arthur;

import org.arthur.leitura.LeitorConsole;
import org.arthur.metodo.CalculadoraJuros;
import org.arthur.metodo.CalculadoraJuros.Resultado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {

    public static void main(String[] args) {

        // Taxa de juros diária (2,5%)
        double juros = 0.025;
        // Leitor do console
        LeitorConsole leitor = new LeitorConsole();
        // Obtém a data atual do sistema
        LocalDate dataAtual = LocalDate.now();

        // Solicita um valor em reais ao usuário
        System.out.println("Digite um valor em reais (R$):");
        double valorReais = leitor.lerDecimal();
        leitor.lerLinha(); // Consome a quebra de linha

        // Solicita uma data no formato dd/mm/aaaa ao usuário
        System.out.println("Digite a data de vencimento no formato dd/mm/aaaa:");
        String data = leitor.lerLinha();

        // Define o formato de data esperado (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInformada;
        try {
            // Faz o parse da string para LocalDate
            dataInformada = LocalDate.parse(data, formatter);

            // Verifica se a data é menor que a data atual
            if (dataInformada.isBefore(dataAtual)) {
                System.out.println("Erro: A data informada não pode ser anterior à data atual.");
                leitor.fechar();
                return;
            }
        } catch (DateTimeParseException e) {
            // Tratamento de erro para datas inválidas ou em formato incorreto
            System.out.println("Erro: Data inválida ou impossível. Por favor, verifique o formato dd/mm/aaaa.");
            leitor.fechar();
            return;
        }

        // Calcula os juros pelo módulo do método
        Resultado resultado = CalculadoraJuros.calcular(valorReais, juros, dataAtual, dataInformada);

        // Exibe o resultado
        System.out.println("\n--- RESULTADO ---");
        System.out.printf("Valor inicial: R$ %.2f%n", resultado.getValorInicial());
        System.out.printf("Taxa de juros: %.2f%% ao dia%n", resultado.getTaxaDiaria() * 100);
        System.out.printf("Período: %d dia(s)%n", resultado.getDias());
        System.out.printf("Valor dos juros: R$ %.2f%n", resultado.getValorJuros());
        System.out.printf("Valor total: R$ %.2f%n", resultado.getValorTotal());

        leitor.fechar();
    }
}
